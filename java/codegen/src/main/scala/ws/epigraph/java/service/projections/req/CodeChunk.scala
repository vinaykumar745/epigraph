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

package ws.epigraph.java.service.projections.req

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
sealed case class CodeChunk(code: String, imports: Set[String]) {
  def +(other: CodeChunk): CodeChunk = CodeChunk(
    if (code.isEmpty) other.code
    else if (other.code.isEmpty) code
    else code + "\n" + other.code,
    imports ++ other.imports
  )
}

object CodeChunk {
  val empty = CodeChunk("", Set())

  def apply(code: String): CodeChunk = CodeChunk(code, Set())
}