/*
 * Copyright 2018 Sumo Logic
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ws.epigraph.client.http;

import org.apache.http.HttpHost;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ws.epigraph.data.Data;
import ws.epigraph.invocation.DefaultOperationInvocationContext;
import ws.epigraph.invocation.InvocationResult;
import ws.epigraph.invocation.OperationInvocationContext;
import ws.epigraph.printers.DataPrinter;
import ws.epigraph.refs.StaticTypesResolver;
import ws.epigraph.refs.TypesResolver;
import ws.epigraph.schema.ResourceDeclaration;
import ws.epigraph.schema.operations.*;
import ws.epigraph.service.Service;
import ws.epigraph.service.ServiceInitializationException;
import ws.epigraph.service.operations.*;
import ws.epigraph.tests.*;
import ws.epigraph.tests._resources.users.UsersResourceDeclaration;
import ws.epigraph.util.EBean;
import ws.epigraph.wire.json.JsonFormatFactories;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.TestCase.*;
import static ws.epigraph.client.http.RequestFactory.*;
import static ws.epigraph.test.TestUtil.lines;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public abstract class AbstractHttpClientTest {
  // todo implement using generated clients (once available)
  // todo test params

  protected static final AtomicInteger UNIQUE_PORT = new AtomicInteger(8888);
  protected static final String HOST = "localhost";
  protected static final int TIMEOUT = 100; // ms
  protected static final Charset CHARSET = StandardCharsets.UTF_8;

  protected static final TypesResolver resolver = StaticTypesResolver.instance();
  protected static final ResourceDeclaration resourceDeclaration = UsersResourceDeclaration.INSTANCE;
  protected static CloseableHttpAsyncClient httpClient;

  protected final HttpHost httpHost = new HttpHost(HOST, port());
  protected final ServerProtocol serverProtocol = new FormatBasedServerProtocol(
      JsonFormatFactories.INSTANCE,
      CHARSET,
      resolver
  );

  protected abstract int port();

  @Test
  public void testSimpleRead() throws ExecutionException, InterruptedException {
    testRead(
        UsersResourceDeclaration.readOperationDeclaration,
        "[1,2](:record(firstName))",
        "( 1: < record: { firstName: 'First1' } >, 2: < record: { firstName: 'First2' } > )"
    );
  }

  @Test
  public void testSimpleReadWithDefault() throws ExecutionException, InterruptedException {
    testRead(
        UsersResourceDeclaration.readOperationDeclaration,
        "[1,2]",
        lines(
            "(",
            "  1: <",
            "    record: {",
            "      firstName: 'First1',",
            "      lastName: 'Last1',",
            "      friendsMap: ( 'Alfred': < id: 1, record: { firstName: ERROR(404, 'not found') } > ),",
            "      friendRecordMap: ( 'Alfred': { firstName: ERROR(404, 'not found') } )",
            "    }",
            "  >,",
            "  2: <",
            "    record: {",
            "      firstName: 'First2',",
            "      lastName: 'Last2',",
            "      friendsMap: ( 'Alfred': < id: 1, record: { firstName: ERROR(404, 'not found') } > ),",
            "      friendRecordMap: ( 'Alfred': { firstName: ERROR(404, 'not found') } )",
            "    }",
            "  >",
            ")"
        )
    );
  }

  @Test
  public void testReadWithPath() throws ExecutionException, InterruptedException {
    testRead(
        UsersResourceDeclaration.readOperationDeclaration,
        "/1:record/firstName",
        "( 1: < record: { firstName: 'First1' } > )"
    );
  }

  @Test
  public void testReadWithMeta() throws ExecutionException, InterruptedException {
    testRead(
        UsersResourceDeclaration.readOperationDeclaration,
        "@(start,count);start=1;count=2[1,2]:record(firstName)", // backend doesn't actually take params into account
        "( 1: < record: { firstName: 'First1' } >, 2: < record: { firstName: 'First2' } > )@{ start: 1, count: 2 }"
    );
  }

  @Test
  public void testMalformedUrl() throws ExecutionException, InterruptedException {
    testReadError(
        UsersResourceDeclaration.readOperationDeclaration,
        "[1,2](:record(firstName))",
        400,
        "Resource 'xxx' not found. Supported resources: {/user, /users}",
        "/xxx"
    );
  }

  @Test
  public void testPathError() throws ExecutionException, InterruptedException {
    testReadError(
        UsersResourceDeclaration.bestFriendReadOperationDeclaration,
        "/12:record/bestFriend:record(firstName)",
        404,
        "{\"ERROR\":404,\"message\":\"User with id 12 not found\"}",
        null
    );
  }

  @Test
  public void testPathRead() throws ExecutionException, InterruptedException {
    testRead(
        UsersResourceDeclaration.bestFriendReadOperationDeclaration,
        "/1:record/bestFriend:record(firstName)",
        "< record: { firstName: 'First2' } >"
    );
  }

  @Test
  public void testFailedRequired() throws ExecutionException, InterruptedException {
    testReadError(
        UsersResourceDeclaration.readOperationDeclaration,
        "[666]+:+id",
        412,
        "{\"ERROR\":412,\"message\":\"[666] : Required tag 'id' is a [404] error: User '666' not found\"}",
        null
    );
  }

  @Test
  public void testSimpleCreateWithoutProjection() throws ExecutionException, InterruptedException {
    testSimpleCreate(null);
  }

  @Test
  public void testSimpleCreateWithProjection() throws ExecutionException, InterruptedException {
    testSimpleCreate("*(firstName)");
  }

  private void testSimpleCreate(@Nullable String inputProjection) throws ExecutionException, InterruptedException {
    String key = testCreate(
        UsersResourceDeclaration.createOperationDeclaration,
        null,
        inputProjection,
        PersonRecord_List.type.createDataBuilder().set(
            PersonRecord_List.create().add(PersonRecord.create().setFirstName("testCreate"))
        ),
        "*",
        "\\[ (\\d+) \\]"
    );

    testRead(
        UsersResourceDeclaration.readOperationDeclaration,
        "[" + key + "](:record(firstName))",
        "( " + key + ": < record: { firstName: 'testCreate' } > )"
    );

    testDelete(
        UsersResourceDeclaration.deleteOperationDeclaration,
        null,
        "[" + key + "]",
        "[*]",
        "( )"
    );

    testRead(
        UsersResourceDeclaration.readOperationDeclaration,
        "[" + key + "](:record(firstName))",
        "( " + key + ": < record: ERROR(404, 'User '" + key + "' not found') > )"
    );

  }

  @Test
  public void testCreateWithPath() throws ExecutionException, InterruptedException {
    testCreate(
        UsersResourceDeclaration.friendsCreateOperationDeclaration,
        "/1:record/friends",
        null,
        Person_List.Type.instance().createDataBuilder()
            .set(Person_List.create()
                .add(Person.create().setId(PersonId.create(2)))
                .add(Person.create().setId(PersonId.create(3)))
                .add(Person.create().setId(PersonId.create(22)))
            ),
        "*:id",
        "\\[ < id: ERROR\\(400, 'Friend with id 2 already exists'\\) >, < id: 3 >, < id: ERROR\\(404, 'User with id 22 not found'\\) > \\]"
    );
  }

  @Test
  public void testUpdateWithoutProjection() throws ExecutionException, InterruptedException {
    testSimpleUpdate(null);
  }

  @Test
  public void testUpdateWithProjection() throws ExecutionException, InterruptedException {
    testSimpleUpdate("[1,22]:record(firstName)");
  }

  private void testSimpleUpdate(String updateProjection) throws ExecutionException, InterruptedException {
    testUpdate(
        UsersResourceDeclaration.updateOperationDeclaration,
        null,
        updateProjection,
        PersonMap.Type.instance().createDataBuilder()
            .set(PersonMap.create()
                .put$(PersonId.create(1), Person.create().setRecord(PersonRecord.create().setFirstName("Alfred2")))
                .put$(PersonId.create(22), Person.create().setRecord(PersonRecord.create().setFirstName("Alfred2")))
            ),
        "[*](code,message)",
        "( 22: { code: 404, message: 'User with id 22 not found' } )"
    );

    // change back
    testUpdate(
        UsersResourceDeclaration.updateOperationDeclaration,
        null,
        updateProjection,
        PersonMap.Type.instance().createDataBuilder()
            .set(PersonMap.create()
                .put$(PersonId.create(1), Person.create().setRecord(PersonRecord.create().setFirstName("First1")))
            ),
        "[*](code,message)",
        "( )"
    );
  }

  @Test
  public void testUpdateWithPath() throws ExecutionException, InterruptedException {
    testUpdate(
        UsersResourceDeclaration.bestFriendUpdateOperationDeclaration,
        "/1:record/bestFriend",
        null,
        Person.create().setId(PersonId.create(3)),
        "(code,message)",
        "null"
    );

    testRead(
        UsersResourceDeclaration.readOperationDeclaration,
        "/1:record/bestFriend:id",
        "( 1: < record: { bestFriend: < id: 3 > } > )"
    );

    // change it back
    testUpdate(
        UsersResourceDeclaration.bestFriendUpdateOperationDeclaration,
        "/1:record/bestFriend",
        null,
        Person.create().setId(PersonId.create(2)),
        "(code,message)",
        "null"
    );

    testUpdate(
        UsersResourceDeclaration.bestFriendUpdateOperationDeclaration,
        "/21:record/bestFriend",
        null,
        Person.create().setId(PersonId.create(3)),
        "(code,message)",
        "{ code: 404, message: 'User with id 21 not found' }"
    );

    testUpdate(
        UsersResourceDeclaration.bestFriendUpdateOperationDeclaration,
        "/1:record/bestFriend",
        null,
        Person.create().setId(PersonId.create(31)),
        "(code,message)",
        "{ code: 404, message: 'User with id 31 not found' }"
    );

  }

  @Test
  public void testDeleteWithPath() throws ExecutionException, InterruptedException {
    testRead(
        UsersResourceDeclaration.readOperationDeclaration,
        "/8:record/bestFriend:id",
        "( 8: < record: { bestFriend: < id: 9 > } > )"
    );

    testDelete(
        UsersResourceDeclaration.bestFriendDeleteOperationDeclaration,
        "/8:record/bestFriend",
        "",
        "(code,message)",
        "null"
    );

    testRead(
        UsersResourceDeclaration.readOperationDeclaration,
        "/8:record/bestFriend:id",
        "( 8: < record: { bestFriend: < id: null > } > )"
        // todo double check if this is correct. Technically we asked for it?
    );

    // change it back
    testUpdate(
        UsersResourceDeclaration.bestFriendUpdateOperationDeclaration,
        "/8:record/bestFriend",
        null,
        Person.create().setId(PersonId.create(9)),
        "(code,message)",
        "null"
    );

    testDelete(
        UsersResourceDeclaration.bestFriendDeleteOperationDeclaration,
        "/28:record/bestFriend",
        "",
        "(code,message)",
        "{ code: 404, message: 'User with id 28 not found' }"
    );
  }

  @Test
  public void testCustomWithPath() throws ExecutionException, InterruptedException {
    String key = testCreate(
        UsersResourceDeclaration.createOperationDeclaration,
        null,
        null,
        PersonRecord_List.type.createDataBuilder().set(
            PersonRecord_List.create().add(PersonRecord.create().setFirstName("first").setLastName("last"))
        ),
        "*",
        "\\[ (\\d+) \\]"
    );

    testCustom(
        UsersResourceDeclaration.capitalizeCustomOperationDeclaration,
        "/" + key,
        null,
        null,
        "(firstName,lastName)",
        "null"
    );

//    testCustom(
//        UsersResourceDeclaration.capitalizeCustomOperationDeclaration,
//        "/" + key,
//        ";useLowerCase=true",
//        null,
//        "(firstName,lastName)",
//        "null"
//    );

    testCustom(
        UsersResourceDeclaration.capitalizeCustomOperationDeclaration,
        "/" + key,
        "(lastName)",
        null,
        "(firstName,lastName)",
        "{ firstName: 'FIRST', lastName: 'last' }"
    );

//    testCustom(
//        UsersResourceDeclaration.capitalizeCustomOperationDeclaration,
//        "/" + key,
//        ";useLowerCase=false(lastName)",
//        null,
//        "(firstName,lastName)",
//        "{ firstName: 'first', lastName: 'last' }"
//    );

    testDelete(
        UsersResourceDeclaration.deleteOperationDeclaration,
        null,
        "[" + key + "]",
        "[*]",
        "( )"
    );
  }

  @Test
  public void testComplexParams() throws ExecutionException, InterruptedException {
    testCustom(
        UsersResourceDeclaration.echoCustomOperationDeclaration,
        null,
        ";param=ws.epigraph.tests.PersonMap(1:<id:1,record:{id:1,firstName:'{foo}\\'+\"[bar]',friends:[<id:2>]}>)",
        null,
        "[*]:(id,record(id,firstName,bestFriend:id,friends*:id))",
        "( 1: < id: 1, record: { id: 1, firstName: '{foo}\\'+\"[bar]', friends: [ < id: 2 > ] } > )"
    );
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  protected void testRead(
      @NotNull ReadOperationDeclaration operationDeclaration,
      @NotNull String requestString,
      @NotNull String expectedDataPrint) throws ExecutionException, InterruptedException {

    InvocationResult<ReadOperationResponse<Data>> invocationResult =
        runReadOperation(operationDeclaration, requestString, null);

    checkReadResult(expectedDataPrint, false, invocationResult);
  }

  protected void testReadError(
      @NotNull ReadOperationDeclaration operationDeclaration,
      @NotNull String requestString,
      int expectedStatusCode,
      @NotNull String expectedError,
      @Nullable String uriOverride) throws ExecutionException, InterruptedException {

    InvocationResult<ReadOperationResponse<Data>> invocationResult =
        runReadOperation(operationDeclaration, requestString, uriOverride);

    invocationResult.consume(
        ror -> fail("Expected request to fail, got: " + printData(ror.getData())),

        oir -> {
          assertEquals(oir.message(), expectedStatusCode, oir.statusCode());
          assertEquals(expectedError, oir.message());
        }
    );
  }

  protected @NotNull InvocationResult<ReadOperationResponse<Data>> runReadOperation(
      @NotNull ReadOperationDeclaration operationDeclaration,
      @NotNull String requestString,
      @Nullable String requestUri) throws ExecutionException, InterruptedException {

    RemoteReadOperationInvocation inv = new RemoteReadOperationInvocation(
        httpHost,
        httpClient,
        resourceDeclaration.fieldName(),
        operationDeclaration,
        serverProtocol,
        CHARSET
    ) {
      @Override
      protected @NotNull String composeUri(final @NotNull ReadOperationRequest request) {
        return requestUri == null ? super.composeUri(request) : requestUri;
      }
    };

    OperationInvocationContext opctx = new DefaultOperationInvocationContext(true, new EBean());
    ReadOperationRequest request = constructReadRequest(
        resourceDeclaration.fieldType(),
        operationDeclaration,
        requestString,
        resolver
    );

    return inv.invoke(request, opctx).get();
  }

  protected String testCreate(
      @NotNull CreateOperationDeclaration operationDeclaration,
      @Nullable String path,
      @Nullable String inputProjection,
      @NotNull Data inputData,
      @NotNull String outputProjection,
      @NotNull String expectedDataPrint) throws ExecutionException, InterruptedException {

    InvocationResult<ReadOperationResponse<Data>> invocationResult = runCreateOperation(
        operationDeclaration,
        path,
        inputProjection,
        inputData,
        outputProjection
    );

    return checkReadResult(expectedDataPrint, true, invocationResult);
  }

  protected void testUpdate(
      @NotNull UpdateOperationDeclaration operationDeclaration,
      @Nullable String path,
      @Nullable String updateProjection,
      @NotNull Data inputData,
      @NotNull String outputProjection,
      @NotNull String expectedDataPrint) throws ExecutionException, InterruptedException {

    InvocationResult<ReadOperationResponse<Data>> invocationResult = runUpdateOperation(
        operationDeclaration,
        path,
        updateProjection,
        inputData,
        outputProjection
    );

    checkReadResult(expectedDataPrint, false, invocationResult);
  }

  protected void testDelete(
      @NotNull DeleteOperationDeclaration operationDeclaration,
      @Nullable String path,
      @NotNull String deleteProjection,
      @NotNull String outputProjection,
      @NotNull String expectedDataPrint) throws ExecutionException, InterruptedException {

    InvocationResult<ReadOperationResponse<Data>> invocationResult = runDeleteOperation(
        operationDeclaration,
        path,
        deleteProjection,
        outputProjection
    );

    checkReadResult(expectedDataPrint, false, invocationResult);
  }

  protected void testCustom(
      @NotNull CustomOperationDeclaration operationDeclaration,
      @Nullable String path,
      @Nullable String inputProjection,
      @Nullable Data inputData,
      @NotNull String outputProjection,
      @NotNull String expectedDataPrint) throws ExecutionException, InterruptedException {

    InvocationResult<ReadOperationResponse<Data>> invocationResult = runCustomOperation(
        operationDeclaration,
        path,
        inputProjection,
        inputData,
        outputProjection
    );

    checkReadResult(expectedDataPrint, false, invocationResult);
  }

  private String checkReadResult(
      final @NotNull String expectedDataPrint,
      boolean isRegexExpected,
      final InvocationResult<ReadOperationResponse<Data>> invocationResult) {

    invocationResult.onFailure(
        oir -> fail(String.format("[%d] %s", oir.statusCode(), oir.message()))
    );

    return invocationResult.mapSuccess(
        ror -> {
          Data data = ror.getData();
          String dataToString = printData(data);
          if (isRegexExpected) {
            Pattern pattern = Pattern.compile(expectedDataPrint);
            Matcher matcher = pattern.matcher(dataToString);
            assertTrue(dataToString, matcher.matches());

            if (matcher.groupCount() == 1) {
              return matcher.group(1);
            }

          } else
            assertEquals(expectedDataPrint, dataToString);

          return dataToString;
        }
    ).result();

  }

  protected @NotNull InvocationResult<ReadOperationResponse<Data>> runCreateOperation(
      @NotNull CreateOperationDeclaration operationDeclaration,
      @Nullable String path,
      @Nullable String inputProjectionString,
      @NotNull Data requestInput,
      @NotNull String outputProjectionString) throws ExecutionException, InterruptedException {

    RemoteCreateOperationInvocation inv = new RemoteCreateOperationInvocation(
        httpHost,
        httpClient,
        resourceDeclaration.fieldName(),
        operationDeclaration,
        serverProtocol,
        CHARSET
    );

    OperationInvocationContext opctx = new DefaultOperationInvocationContext(true, new EBean());
    CreateOperationRequest request = constructCreateRequest(
        resourceDeclaration.fieldType(),
        operationDeclaration,
        path,
        inputProjectionString,
        requestInput,
        outputProjectionString,
        resolver
    );

    return inv.invoke(request, opctx).get();
  }

  protected @NotNull InvocationResult<ReadOperationResponse<Data>> runUpdateOperation(
      @NotNull UpdateOperationDeclaration operationDeclaration,
      @Nullable String path,
      @Nullable String updateProjectionString,
      @NotNull Data requestInput,
      @NotNull String outputProjectionString) throws ExecutionException, InterruptedException {

    RemoteUpdateOperationInvocation inv = new RemoteUpdateOperationInvocation(
        httpHost,
        httpClient,
        resourceDeclaration.fieldName(),
        operationDeclaration,
        serverProtocol,
        CHARSET
    );

    OperationInvocationContext opctx = new DefaultOperationInvocationContext(true, new EBean());
    UpdateOperationRequest request = constructUpdateRequest(
        resourceDeclaration.fieldType(),
        operationDeclaration,
        path,
        updateProjectionString,
        requestInput,
        outputProjectionString,
        resolver
    );

    return inv.invoke(request, opctx).get();
  }

  protected @NotNull InvocationResult<ReadOperationResponse<Data>> runDeleteOperation(
      @NotNull DeleteOperationDeclaration operationDeclaration,
      @Nullable String path,
      @NotNull String deleteProjectionString,
      @NotNull String outputProjectionString) throws ExecutionException, InterruptedException {

    RemoteDeleteOperationInvocation inv = new RemoteDeleteOperationInvocation(
        httpHost,
        httpClient,
        resourceDeclaration.fieldName(),
        operationDeclaration,
        serverProtocol,
        CHARSET
    );

    OperationInvocationContext opctx = new DefaultOperationInvocationContext(true, new EBean());
    DeleteOperationRequest request = constructDeleteRequest(
        resourceDeclaration.fieldType(),
        operationDeclaration,
        path,
        deleteProjectionString,
        outputProjectionString,
        resolver
    );

    return inv.invoke(request, opctx).get();
  }

  protected @NotNull InvocationResult<ReadOperationResponse<Data>> runCustomOperation(
      @NotNull CustomOperationDeclaration operationDeclaration,
      @Nullable String path,
      @Nullable String inputProjectionString,
      @Nullable Data requestInput,
      @NotNull String outputProjectionString) throws ExecutionException, InterruptedException {

    RemoteCustomOperationInvocation inv = new RemoteCustomOperationInvocation(
        httpHost,
        httpClient,
        resourceDeclaration.fieldName(),
        operationDeclaration,
        serverProtocol,
        CHARSET
    );

    OperationInvocationContext opctx = new DefaultOperationInvocationContext(true, new EBean());
    CustomOperationRequest request = constructCustomRequest(
        resourceDeclaration.fieldType(),
        operationDeclaration,
        path,
        inputProjectionString,
        requestInput,
        outputProjectionString,
        resolver
    );

    return inv.invoke(request, opctx).get();
  }

  private String printData(final Data data) {
    String dataToString;
    try {
      StringWriter sw = new StringWriter();
      DataPrinter<IOException> printer = DataPrinter.toString(120, false, sw);
      printer.print(data);
      dataToString = sw.toString();
    } catch (IOException e) {
      e.printStackTrace();
      fail(e.toString());
      dataToString = null;
    }
    return dataToString;
  }

  protected static @NotNull Service buildUsersService() throws ServiceInitializationException {
    return new Service(
        "users",
        Arrays.asList(
            new UserResourceFactory().getUserResource(),
            new UsersResourceFactory(new UsersStorage()).getUsersResource()
        )
    );
  }

  @BeforeClass
  public static void startClient() {
    httpClient = HttpAsyncClients.createDefault();
    httpClient.start();
  }

  @AfterClass
  public static void stopClient() throws IOException { httpClient.close(); }
}
