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

/* Created by yegor on 7/6/16. */

package ws.epigraph.scala;

import ws.epigraph.compiler.*;
import scala.collection.JavaConversions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ScalaSchemaGenerator {

  private final CContext ctx;

  private final Path outputRoot;

  public ScalaSchemaGenerator(CContext ctx, Path outputRoot) {
    this.ctx = ctx;
    this.outputRoot = outputRoot;
  }

  public ScalaSchemaGenerator(CContext ctx, File outputRoot) {
    this(ctx, outputRoot.toPath());
  }

  public void generate() throws IOException {

    Path tmpRoot = ScalaGenUtils
        .rmrf(outputRoot.resolveSibling(outputRoot.getFileName().toString() + "~tmp"), outputRoot.getParent());

    for (CNamespace namespace : ctx.namespaces().values()) {
      new NamespaceGen(namespace).writeUnder(tmpRoot);
    }

    for (CSchemaFile schemaFile : ctx.schemaFiles().values()) {
      for (CTypeDef typeDef : JavaConversions.asJavaIterable(schemaFile.typeDefs())) {

        switch (typeDef.kind()) {

          case ENTITY:
            //new VarTypeGen((CVarTypeDef) typeDef).writeUnder(tmpRoot);
            break;

          case RECORD:
            new RecordGen((CRecordTypeDef) typeDef).writeUnder(tmpRoot);
            break;

          case MAP:
            break;

          case LIST:
            new ListGen((CListTypeDef) typeDef).writeUnder(tmpRoot);
            break;

          case ENUM:
            break;

          case STRING:
          case INTEGER:
          case LONG:
          case DOUBLE:
          case BOOLEAN:
            new PrimitiveGen((CPrimitiveTypeDef) typeDef).writeUnder(tmpRoot);
            break;

          default:
            throw new UnsupportedOperationException(typeDef.kind().toString());
        }
      }
    }

    ScalaGenUtils.move(tmpRoot, outputRoot, outputRoot.getParent()); // move new root to final location

  }

//  public static void main(String... args) throws IOException {
//    new ScalaSchemaGenerator(
//        SchemaCompiler.testcompile(),
//        Paths.get("scala/codegen-test/src/main/scala")
//    ).generate();
//  }


}
