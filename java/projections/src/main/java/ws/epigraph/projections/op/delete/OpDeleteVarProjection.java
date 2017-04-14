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

package ws.epigraph.projections.op.delete;

import ws.epigraph.lang.TextLocation;
import ws.epigraph.projections.VarNormalizationContext;
import ws.epigraph.projections.abs.AbstractVarProjection;
import ws.epigraph.projections.gen.ProjectionReferenceName;
import ws.epigraph.types.TypeApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ws.epigraph.types.TypeKind;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class OpDeleteVarProjection extends AbstractVarProjection<
    OpDeleteVarProjection,
    OpDeleteTagProjectionEntry,
    OpDeleteModelProjection<?, ?, ?>
    > {

  private /*final*/ boolean canDelete;

  public OpDeleteVarProjection(
      @NotNull TypeApi type,
      boolean canDelete,
      @NotNull Map<String, OpDeleteTagProjectionEntry> tagProjections,
      boolean parenthesized,
      @Nullable List<OpDeleteVarProjection> polymorphicTails,
      @NotNull TextLocation location) {
    super(type, tagProjections, parenthesized, polymorphicTails, location);
    this.canDelete = canDelete;
  }

  public OpDeleteVarProjection(final TypeApi type, final TextLocation location) {
    super(type, location);
  }

  public boolean canDelete() {
    assertResolved();
    return canDelete;
  }

  @Override
  public void resolve(final @Nullable ProjectionReferenceName name, final @NotNull OpDeleteVarProjection value) {
    super.resolve(name, value);
    this.canDelete = value.canDelete();
  }

  @Override
  protected OpDeleteVarProjection merge(
      final @NotNull TypeApi effectiveType,
      final @NotNull List<OpDeleteVarProjection> varProjections,
      final @NotNull Map<String, OpDeleteTagProjectionEntry> mergedTags,
      final boolean mergedParenthesized,
      final List<OpDeleteVarProjection> mergedTails) {

    boolean canDelete = varProjections.stream().anyMatch(OpDeleteVarProjection::canDelete);
    return new OpDeleteVarProjection(effectiveType, canDelete, mergedTags, mergedParenthesized, mergedTails, TextLocation.UNKNOWN);
  }

  @Override
  protected @NotNull VarNormalizationContext<OpDeleteVarProjection> newNormalizationContext() {
    return new VarNormalizationContext<>(
        t -> new OpDeleteVarProjection(t, location())
    );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    OpDeleteVarProjection that = (OpDeleteVarProjection) o;
    return canDelete == that.canDelete;
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), canDelete);
  }
}
