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

package ws.epigraph.projections.op.input;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ws.epigraph.gdata.GMapDatum;
import ws.epigraph.lang.TextLocation;
import ws.epigraph.projections.Annotations;
import ws.epigraph.projections.gen.GenMapModelProjection;
import ws.epigraph.projections.gen.ProjectionReferenceName;
import ws.epigraph.projections.op.OpParams;
import ws.epigraph.types.MapTypeApi;

import java.util.List;
import java.util.Objects;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class OpInputMapModelProjection
    extends OpInputModelProjection<OpInputModelProjection<?, ?, ?, ?>, OpInputMapModelProjection, MapTypeApi, GMapDatum>
    implements GenMapModelProjection<
    OpInputVarProjection,
    OpInputTagProjectionEntry,
    OpInputModelProjection<?, ?, ?, ?>,
    OpInputMapModelProjection,
    MapTypeApi
    > {

  private /*final @NotNull*/ @Nullable OpInputKeyProjection keyProjection;
  private /*final @NotNull*/ @Nullable OpInputVarProjection itemsProjection;

  public OpInputMapModelProjection(
      @NotNull MapTypeApi model,
      boolean required,
      @Nullable GMapDatum defaultValue,
      @NotNull OpParams params,
      @NotNull Annotations annotations,
      @Nullable OpInputModelProjection<?, ?, ?, ?> metaProjection,
      @NotNull OpInputKeyProjection keyProjection,
      @NotNull OpInputVarProjection itemsProjection,
      @Nullable List<OpInputMapModelProjection> tails,
      @NotNull TextLocation location) {
    super(model, required, defaultValue, params, annotations, metaProjection, tails, location);
    this.keyProjection = keyProjection;
    this.itemsProjection = itemsProjection;
  }

  @Override
  public @NotNull OpInputMapModelProjection newReference(
      final @NotNull MapTypeApi type,
      final @NotNull TextLocation location) {
    return new OpInputMapModelProjection(type, location);
  }

  public OpInputMapModelProjection(final @NotNull MapTypeApi model, final @NotNull TextLocation location) {
    super(model, location);
  }

  public @NotNull OpInputKeyProjection keyProjection() {
    assert isResolved();
    assert keyProjection != null;
    return keyProjection;
  }

  @Override
  public @NotNull OpInputVarProjection itemsProjection() {
    assert isResolved();
    assert itemsProjection != null;
    return itemsProjection;
  }

  @Override
  public void resolve(final ProjectionReferenceName name, final @NotNull OpInputMapModelProjection value) {
    super.resolve(name, value);
    this.keyProjection = value.keyProjection();
    this.itemsProjection = value.itemsProjection();
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    final OpInputMapModelProjection that = (OpInputMapModelProjection) o;
    return Objects.equals(keyProjection, that.keyProjection) &&
           Objects.equals(itemsProjection, that.itemsProjection);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), keyProjection, itemsProjection);
  }
}
