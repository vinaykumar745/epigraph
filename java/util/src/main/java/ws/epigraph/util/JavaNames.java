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

/* Created by yegor on 10/8/16. */

package ws.epigraph.util;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public final class JavaNames {

  /**
   * Strings that are not allowed as legal Java identifiers.
   * (https://docs.oracle.com/javase/specs/jls/se8/html/jls-3.html#jls-3.8)
   */
  public static final Set<? extends String> reserved = Unmodifiable.set(new LinkedHashSet<>(Arrays.asList(

      // keywords (https://docs.oracle.com/javase/specs/jls/se8/html/jls-3.html#jls-3.9)
      "abstract",
      "assert",
      "boolean",
      "break",
      "byte",
      "case",
      "catch",
      "char",
      "class",
      "const",
      "continue",
      "default",
      "do",
      "double",
      "else",
      "enum",
      "extends",
      "final",
      "finally",
      "float",
      "for",
      "goto",
      "if",
      "implements",
      "import",
      "instanceof",
      "int",
      "interface",
      "long",
      "native",
      "new",
      "package",
      "private",
      "protected",
      "public",
      "return",
      "short",
      "static",
      "strictfp",
      "super",
      "switch",
      "synchronized",
      "this",
      "throw",
      "throws",
      "transient",
      "try",
      "void",
      "volatile",
      "while",

      // boolean literals (https://docs.oracle.com/javase/specs/jls/se8/html/jls-3.html#jls-3.10.3)
      "false",
      "true",

      // null literal (https://docs.oracle.com/javase/specs/jls/se8/html/jls-3.html#jls-3.10.7)
      "null"

  )));

  private JavaNames() {}

  public static @NotNull String javaName(@NotNull String name) { return reserved.contains(name) ? name + '_' : name; }

}
