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

/* Created by yegor on 7/22/16. */

package ws.epigraph.names;

import ws.epigraph.data.Immutable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public final class NamespaceName extends QualifiedName implements Immutable {

  private @Nullable String toString = null;

  public NamespaceName(@Nullable NamespaceName namespaceName, String localName) { super(namespaceName, localName); }

  public static @Nullable NamespaceName from(@NotNull String... segments) {
    switch (segments.length) {
      case 0:
        return null;
      default:
        return new NamespaceName(from(Arrays.copyOf(segments, segments.length - 1)), segments[segments.length - 1]);
    }
  }

  @Override
  public @NotNull String toString() {
    if (toString == null) toString = super.toString();
    return toString;
  }

}
