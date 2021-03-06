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

package ws.epigraph.url.parser;

import org.jetbrains.annotations.NotNull;
import ws.epigraph.gdata.GDatum;
import ws.epigraph.schema.ResourcesSchema;
import ws.epigraph.schema.SchemasPsiProcessingContext;
import ws.epigraph.schema.parser.ResourcesSchemaPsiParser;
import ws.epigraph.psi.EpigraphPsiUtil;
import ws.epigraph.refs.TypesResolver;
import ws.epigraph.schema.parser.SchemaParserDefinition;
import ws.epigraph.schema.parser.psi.SchemaFile;

import java.util.Map;
import java.util.TreeMap;

import static ws.epigraph.test.TestUtil.*;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public final class RequestUrlPsiParserTestUtil {
  private RequestUrlPsiParserTestUtil() {}

  static @NotNull String printParameters(final Map<String, GDatum> parameters) {
    TreeMap<String, GDatum> sortedParams = new TreeMap<>(parameters);
    StringBuilder sb = new StringBuilder();
    for (final Map.Entry<String, GDatum> entry : sortedParams.entrySet()) {
      sb.append(sb.length() == 0 ? "{" : ", ");
      sb.append(entry.getKey());
      sb.append(" = ");
      sb.append(printGDatum(entry.getValue()));
    }
    if (sb.length() > 0) sb.append("}");
    return sb.toString();
  }

  static @NotNull ResourcesSchema parseIdl(@NotNull String text, @NotNull TypesResolver resolver) {
    EpigraphPsiUtil.ErrorsAccumulator errorsAccumulator = new EpigraphPsiUtil.ErrorsAccumulator();

    @NotNull SchemaFile psiFile =
        (SchemaFile) EpigraphPsiUtil.parseFile("test.idl", text, SchemaParserDefinition.INSTANCE, errorsAccumulator);

    failIfHasErrors(psiFile, errorsAccumulator);

    return runPsiParser(true, context -> {
      SchemasPsiProcessingContext schemasPsiProcessingContext = new SchemasPsiProcessingContext();
      ResourcesSchema schema = ResourcesSchemaPsiParser.parseResourcesSchema(
          psiFile,
          resolver,
          schemasPsiProcessingContext
      );
      schemasPsiProcessingContext.ensureAllReferencesResolved();
      context.setMessages(schemasPsiProcessingContext.messages());
      return schema;
    });
  }
}
