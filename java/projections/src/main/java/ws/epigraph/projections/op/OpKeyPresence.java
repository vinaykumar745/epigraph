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

package ws.epigraph.projections.op;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public enum OpKeyPresence {
  OPTIONAL, REQUIRED, FORBIDDEN;

  @Contract(pure = true)
  @Nullable
  public String getPrettyPrinterString() {
    if (this == REQUIRED) return "required";
    if (this == FORBIDDEN) return "forbidden";
    return null;
  }

  @Nullable
  public static OpKeyPresence merge(OpKeyPresence p1, OpKeyPresence p2) {
    if (p1 == OPTIONAL) return p2;
    if (p2 == OPTIONAL) return p1;
    if (p1 == p2) return p1;

    return null; // not compatible
  }
}