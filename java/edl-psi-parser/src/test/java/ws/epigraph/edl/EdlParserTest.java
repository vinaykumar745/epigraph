/*
 * Copyright 2016 Sumo Logic
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

package ws.epigraph.edl;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import ws.epigraph.edl.parser.EdlPsiParser;
import ws.epigraph.psi.EpigraphPsiUtil;
import ws.epigraph.refs.SimpleTypesResolver;
import ws.epigraph.refs.TypesResolver;
import ws.epigraph.edl.parser.SchemaParserDefinition;
import ws.epigraph.edl.parser.psi.SchemaFile;
import ws.epigraph.tests.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static ws.epigraph.test.TestUtil.*;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class EdlParserTest {
  private final TypesResolver resolver = new SimpleTypesResolver(
      PersonId.type,
      Person.type,
      User.type,
      UserId.type,
      UserRecord.type,
      String_Person_Map.type,
      epigraph.String.type,
      epigraph.Boolean.type
  );

  @Test
  public void testEmpty() throws IOException {
    testParse(
        lines(
            "namespace test",
            "import ws.epigraph.tests.Person"
        ),
        "namespace test"
    );
  }

  @Test
  public void testEmptyResource() throws IOException {
    testParse(
        lines(
            "namespace ws.epigraph.tests",
            "resource users: map[String,Person] { }"
        ),
        lines(
            "namespace ws.epigraph.tests",
            "resource users: map[epigraph.String,ws.epigraph.tests.Person] { }"
        )
    );
  }

  @Test
  public void testResource() throws IOException {
    testParse(
        lines(
            "namespace test",
            "import ws.epigraph.tests.Person",
            "import ws.epigraph.tests.UserRecord",
            "resource users : map[String,Person] {",
            "  READ {",
            "    doc = \"dome doc string\"",
            "    outputProjection {",
            "      ;superUser: UserRecord (id) = { id : 1337 } {",
            "        doc = \"super user account\"",
            "      }",
            "    } [required]( :`record` (id, firstName) )",
            "  }",
            "  readWithPath READ {",
            "    path / .",
            "    outputProjection :`record` (id, firstName)",
            "  }",
            "  CREATE {",
            "    inputProjection []( :`record` ( firstName, lastName) )",
            "    outputType Boolean",
            "    outputProjection", // empty projection
            "  }",
            "  UPDATE {",
            "    doc = \"dome doc string\"",
            "    inputProjection []( :`record` ( firstName, lastName) )",
            "    outputProjection [forbidden]( :id )",
            "  }",
            "  customUpdate UPDATE {",
            "    doc = \"dome doc string\"",
            "    inputProjection []( :`record` ( firstName, lastName) )",
            "    outputProjection [forbidden]( :id )",
            "  }",
            "  DELETE {",
            "    deleteProjection [forbidden]( +:`record` ( firstName ) )",
            "    outputType Boolean",
            "  }",
            "  customOp CUSTOM {",
            "    method POST",
            "    doc = \"dome doc string\"",
            "    path / . :`record` / bestFriend",
            "    inputType map[String,Person]",
            "    inputProjection []( :`record` ( firstName, lastName) )",
            "    outputType map[String,Person]",
            "    outputProjection [forbidden]( :id )",
            "  }",
            "}"
        ),
        lines(
            "namespace test",
            "resource users: map[epigraph.String,ws.epigraph.tests.Person]",
            "{",
            "  READ",
            "  {",
            "    doc = \"dome doc string\",",
            "    outputType map[epigraph.String,ws.epigraph.tests.Person],",
            "    outputProjection {",
            "      ;superUser: ws.epigraph.tests.UserRecord ( id ) =",
            "        { id: 1337 } { doc = \"super user account\" }",
            "    } [ required ]( :`record` ( id, firstName ) )",
            "  }",
            "  readWithPath READ",
            "  {",
            "    path / .,",
            "    outputType ws.epigraph.tests.Person,",
            "    outputProjection :`record` ( id, firstName )",
            "  }",
            "  CREATE",
            "  {",
            "    inputType map[epigraph.String,ws.epigraph.tests.Person],",
            "    inputProjection []( :`record` ( firstName, lastName ) ),",
            "    outputType epigraph.Boolean",
            "  }",
            "  UPDATE",
            "  {",
            "    doc = \"dome doc string\",",
            "    inputType map[epigraph.String,ws.epigraph.tests.Person],",
            "    inputProjection []( :`record` ( firstName, lastName ) ),",
            "    outputType map[epigraph.String,ws.epigraph.tests.Person],",
            "    outputProjection [ forbidden ]( :id )",
            "  }",
            "  customUpdate UPDATE",
            "  {",
            "    doc = \"dome doc string\",",
            "    inputType map[epigraph.String,ws.epigraph.tests.Person],",
            "    inputProjection []( :`record` ( firstName, lastName ) ),",
            "    outputType map[epigraph.String,ws.epigraph.tests.Person],",
            "    outputProjection [ forbidden ]( :id )",
            "  }",
            "  DELETE",
            "  {",
            "    deleteProjection [ forbidden ]( +:`record` ( firstName ) ),",
            "    outputType epigraph.Boolean",
            "  }",
            "  customOp CUSTOM",
            "  {",
            "    method POST,",
            "    doc = \"dome doc string\",",
            "    path / . :`record` / bestFriend,",
            "    inputType map[epigraph.String,ws.epigraph.tests.Person],",
            "    inputProjection []( :`record` ( firstName, lastName ) ),",
            "    outputType map[epigraph.String,ws.epigraph.tests.Person],",
            "    outputProjection [ forbidden ]( :id )",
            "  } }"
        )
    );
  }

  private void testParse(String idlStr, String expected) {
    Edl edl = parseEdl(idlStr, resolver);
    assertEquals(expected, printEdl(edl));
  }

  private static @NotNull Edl parseEdl(@NotNull String text, @NotNull TypesResolver resolver) {
    EpigraphPsiUtil.ErrorsAccumulator errorsAccumulator = new EpigraphPsiUtil.ErrorsAccumulator();

    @NotNull SchemaFile psiFile =
        (SchemaFile) EpigraphPsiUtil.parseFile("test.epigraph", text, SchemaParserDefinition.INSTANCE, errorsAccumulator);

    failIfHasErrors(psiFile, errorsAccumulator);

    return runPsiParser(errors -> EdlPsiParser.parseEdl(psiFile, resolver, errors));
  }

}
