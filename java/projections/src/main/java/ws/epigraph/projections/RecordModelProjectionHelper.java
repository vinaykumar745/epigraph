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

package ws.epigraph.projections;

import org.jetbrains.annotations.NotNull;
import ws.epigraph.projections.gen.GenRecordModelProjection;
import ws.epigraph.types.RecordType;

import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Objects;
import java.util.Set;

/**
 * Helper class for {@link ws.epigraph.projections.gen.GenRecordModelProjection} implementations.
 *
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class RecordModelProjectionHelper {
  // this ought to be an abstract base class, but Java doesn't support multiple inheritance.
  // Concrete implementation would have to extend both abstract record model class and model projection base class

  private static final
  ThreadLocal<IdentityHashMap<GenRecordModelProjection<?, ?, ?, ?, ?, ?, ?>, GenRecordModelProjection<?, ?, ?, ?, ?, ?, ?>>>
      equalsVisited = new ThreadLocal<>();

  /**
   * Recursion-aware helper for implementing {@code equals}.
   * <p/>
   * Usage pattern:
   * <blockquote><pre><code>
   *   boolean equals(Object o) {
   *     if (!super.equals(o)) return false;
   *     return RecordModelProjectionHelper.equals(this, o);
   *   }
   * </code></pre></blockquote>
   *
   * @param rmp {@code this} class
   * @param o object to compare to
   *
   * @return {@code true} iff equals
   */
  public static boolean equals(GenRecordModelProjection<?, ?, ?, ?, ?, ?, ?> rmp, Object o) {
    if (rmp == o) return true;
    if (o == null || rmp.getClass() != o.getClass()) return false;
    GenRecordModelProjection<?, ?, ?, ?, ?, ?, ?> that = (GenRecordModelProjection<?, ?, ?, ?, ?, ?, ?>) o;

    IdentityHashMap<GenRecordModelProjection<?, ?, ?, ?, ?, ?, ?>, GenRecordModelProjection<?, ?, ?, ?, ?, ?, ?>>
        visitedMap = equalsVisited.get();

    boolean mapWasNull = visitedMap == null;
    if (mapWasNull) {
      visitedMap = new IdentityHashMap<>();
      equalsVisited.set(visitedMap);
    } else {
      if (that == visitedMap.get(rmp)) return true;
      if (visitedMap.containsKey(rmp)) return false;
    }
    visitedMap.put(rmp, that);
    boolean res = Objects.equals(rmp.fieldProjections(), that.fieldProjections());
    if (mapWasNull) equalsVisited.remove();
    return res;
  }

  public static void checkFieldsBelongsToModel(@NotNull Collection<String> fieldNames, @NotNull RecordType model) {
    final Set<String> modelFieldNames = model.fieldsMap().keySet();
    for (String fieldName : fieldNames) {
      if (!modelFieldNames.contains(fieldName))
        throw new IllegalArgumentException(
            String.format("Field '%s' does not belong to record model '%s'. Known fields: %s",
                          fieldName, model.name(), ProjectionUtils.listFields(modelFieldNames)
            )
        );
    }

  }
}