/*
 * Copyright 2017 Sumo Logic
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

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.nio.ContentEncoder;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.entity.HttpAsyncContentProducer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ws.epigraph.data.Data;
import ws.epigraph.errors.ErrorValue;
import ws.epigraph.http.CommonHttpUtil;
import ws.epigraph.http.ContentType;
import ws.epigraph.http.Headers;
import ws.epigraph.http.MimeTypes;
import ws.epigraph.invocation.ErrorValueInvocationError;
import ws.epigraph.invocation.OperationInvocationContext;
import ws.epigraph.invocation.InvocationResult;
import ws.epigraph.projections.StepsAndProjection;
import ws.epigraph.projections.op.OpProjection;
import ws.epigraph.projections.req.ReqProjection;
import ws.epigraph.refs.TypesResolver;
import ws.epigraph.service.operations.ReadOperationResponse;
import ws.epigraph.util.IOUtil;
import ws.epigraph.wire.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.function.Function;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class FormatBasedServerProtocol implements ServerProtocol {
  private static final Logger LOG = LoggerFactory.getLogger(FormatBasedServerProtocol.class);

  private final @NotNull FormatReader.Factory<? extends ReqFormatReader> reqReaderFactory;
  private final @NotNull FormatWriter.Factory<? extends ReqFormatWriter> reqWriterFactory;
  private final @NotNull FormatWriter.Factory<? extends OpFormatWriter> opWriterFactory;

  private final @NotNull Charset requestCharset; //charset to be used for requests
  private final @NotNull TypesResolver typesResolver;

  public FormatBasedServerProtocol(
      final @NotNull FormatFactories formatFactories,
      final @NotNull Charset requestCharset,
      final @NotNull TypesResolver resolver) {

    this.requestCharset = requestCharset;

    reqReaderFactory = formatFactories.reqReaderFactory();
    reqWriterFactory = formatFactories.reqWriterFactory();
    opWriterFactory = formatFactories.opWriterFactory();
    typesResolver = resolver;
  }

  @Override
  public @NotNull String[] mimeTypes() {
    // here we assume that all formatFactories use the same format, i.e. we don't mix json/xml
    return new String[]{
        reqReaderFactory.format().mimeType(),
        MimeTypes.TEXT // for plain text messages
    };
  }

  @Override
  public HttpContentProducer createRequestContentProducer(
      final @Nullable StepsAndProjection<ReqProjection<?,?>> reqInputProjection,
      final @NotNull OpProjection<?,?> opInputProjection,
      final @NotNull Data inputData,
      final @NotNull OperationInvocationContext operationInvocationContext) {

    final Function<ContentEncoder, ContentWriter> producerFunc;
    final String mimeType;

    if (reqInputProjection == null) {
      mimeType = opWriterFactory.format().mimeType();
      producerFunc = ce -> () -> {
        ContentEncodingOutputStream cos = new ContentEncodingOutputStream(ce);
        OpFormatWriter writer = opWriterFactory.newFormatWriter(cos, requestCharset);
        writer.writeData(opInputProjection, 0, inputData);
        writer.close();
        cos.close();
      };
    } else {
      mimeType = reqWriterFactory.format().mimeType();
      producerFunc = ce -> () -> {
        ContentEncodingOutputStream cos = new ContentEncodingOutputStream(ce);
        ReqFormatWriter writer = reqWriterFactory.newFormatWriter(cos, requestCharset);
        writer.writeData(
            reqInputProjection.projection(),
            reqInputProjection.pathSteps() - 1, // minus one for field
            inputData
        );
        writer.close();
        cos.close();
      };
    }

    return new HttpContentProducer(
        ContentType.get(mimeType, requestCharset),
        new MyHttpAsyncContentProducer(producerFunc)
    );
  }

  @Override
  public HttpContentProducer updateRequestContentProducer(
      final @Nullable StepsAndProjection<ReqProjection<?,?>> reqUpdateProjection,
      final @NotNull OpProjection<?,?> opInputProjection,
      final @NotNull Data inputData,
      final @NotNull OperationInvocationContext operationInvocationContext) {

    final Function<ContentEncoder, ContentWriter> producerFunc;
    final String mimeType;

    if (reqUpdateProjection == null) {
      mimeType = opWriterFactory.format().mimeType();
      producerFunc = ce -> () -> {
        ContentEncodingOutputStream cos = new ContentEncodingOutputStream(ce);
        OpFormatWriter writer = opWriterFactory.newFormatWriter(cos, requestCharset);
        writer.writeData(opInputProjection, 0, inputData);
        writer.close();
        cos.close();
      };
    } else {
      mimeType = reqWriterFactory.format().mimeType();
      producerFunc = ce -> () -> {
        ContentEncodingOutputStream cos = new ContentEncodingOutputStream(ce);
        ReqFormatWriter writer = reqWriterFactory.newFormatWriter(cos, requestCharset);
        writer.writeData(
            reqUpdateProjection.projection(),
            reqUpdateProjection.pathSteps() - 1, // minus one for field
            inputData
        );
        writer.close();
        cos.close();
      };
    }

    return new HttpContentProducer(
        ContentType.get(mimeType, requestCharset),
        new MyHttpAsyncContentProducer(producerFunc)
    );
  }

  @Override
  public HttpContentProducer customRequestContentProducer(
      final StepsAndProjection<ReqProjection<?,?>> reqInputProjection,
      final @NotNull OpProjection<?,?> opInputProjection,
      final @NotNull Data inputData,
      final @NotNull OperationInvocationContext operationInvocationContext) {

    return createRequestContentProducer(reqInputProjection, opInputProjection, inputData, operationInvocationContext);
  }

  @Override
  public InvocationResult<ReadOperationResponse<Data>> readResponse(
      final @NotNull StepsAndProjection<ReqProjection<?,?>> projection,
      final @NotNull OperationInvocationContext operationInvocationContext,
      final @NotNull HttpResponse httpResponse,
      final int okStatusCode) {

    int statusCode = httpResponse.getStatusLine().getStatusCode();
    Charset charset = CommonHttpUtil.getCharset(
        Optional.ofNullable(httpResponse.getFirstHeader(Headers.ACCEPT_CHARSET)).map(Header::getValue).orElse(null),
        Optional.ofNullable(httpResponse.getFirstHeader(Headers.ACCEPT)).map(Header::getValue).orElse(null)
    );

    try {
      if (statusCode == okStatusCode) { // should we support multiple options here? Or accept any 2xx?
        try (InputStream inputStream = httpResponse.getEntity().getContent()) {
          ReqFormatReader formatReader =
              reqReaderFactory.newFormatReader(inputStream, charset, typesResolver);

          Data data = formatReader.readData(projection.projection(), projection.pathSteps());
          return InvocationResult.success(new ReadOperationResponse<>(data));
        } catch (FormatException e) {
          return InvocationResult.failure(new MalformedOutputInvocationError(e));
        }
      } else {
        @NotNull Charset responseCharset = Util.defaultCharset;

        Header contentTypeHeader = httpResponse.getEntity().getContentType();
        if (contentTypeHeader == null) // broken server?
          return readPlainTextError(httpResponse, statusCode, responseCharset);

        ContentType contentType = Util.parseContentType(contentTypeHeader.getValue());
        responseCharset = Optional.ofNullable(contentType.charset()).orElse(responseCharset);

        if (contentType.mimeType().equals(MimeTypes.JSON)) {
          ErrorValue error = readError(httpResponse, responseCharset);
          return InvocationResult.failure(new ErrorValueInvocationError(error));
        } else
          return readPlainTextError(httpResponse, statusCode, responseCharset);

      }
    } catch (IOException e) {
      if (operationInvocationContext.isDebug())
        LOG.error("Error reading operation response", e);

      return InvocationResult.failure(new IOExceptionInvocationError(e));
    }
  }

  private @NotNull InvocationResult<ReadOperationResponse<Data>> readPlainTextError(
      @NotNull HttpResponse httpResponse,
      int statusCode, @NotNull Charset responseCharset) throws IOException {

    try (InputStream is = httpResponse.getEntity().getContent()) {
      return InvocationResult.failure(
          new ServerSideInvocationError(statusCode, IOUtil.readInputStream(is, responseCharset).trim())
      );
    }
  }

  private @NotNull ErrorValue readError(@NotNull HttpResponse response, @NotNull Charset charset) throws IOException {
    // read response text fully first as we might need to use it twice

    StringBuilder textBuilder = new StringBuilder();

    try (
        InputStream inputStream = response.getEntity().getContent();
        Reader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(charset.name())))
    ) {
      for (int c = reader.read(); c != -1; c = reader.read()) { textBuilder.append((char) c); }
    }

    String string = textBuilder.toString();
    try {
      ReqFormatReader formatReader = reqReaderFactory.newFormatReader(
          new ByteArrayInputStream(string.getBytes(charset)), charset, typesResolver
      );
      return formatReader.readError();
    } catch (FormatException ignored) { // log it? not all messages are guaranteed to be in proper format
      return new ErrorValue(response.getStatusLine().getStatusCode(), string);
    }
  }

  private interface ContentWriter {
    void write() throws IOException;
  }

  private static class MyHttpAsyncContentProducer implements HttpAsyncContentProducer {
    private final Function<ContentEncoder, ContentWriter> producerFunc;
    ContentWriter writer;

    MyHttpAsyncContentProducer(final Function<ContentEncoder, ContentWriter> producerFunc) {
      this.producerFunc = producerFunc;
      writer = null;
    }

    @Override
    public void produceContent(final ContentEncoder encoder, final IOControl ioctrl) throws IOException {
      writer = producerFunc.apply(encoder);
      writer.write();
    }

    @Override
    public boolean isRepeatable() { return true; }

    @Override
    public void close() { }
  }
}
