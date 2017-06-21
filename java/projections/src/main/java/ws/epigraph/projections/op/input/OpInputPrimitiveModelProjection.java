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
import ws.epigraph.annotations.Annotations;
import ws.epigraph.gdata.GPrimitiveDatum;
import ws.epigraph.lang.TextLocation;
import ws.epigraph.projections.gen.GenPrimitiveModelProjection;
import ws.epigraph.projections.op.OpParams;
import ws.epigraph.types.PrimitiveTypeApi;

import java.util.List;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class OpInputPrimitiveModelProjection
    extends OpInputModelProjection<OpInputModelProjection<?, ?, ?, ?>, OpInputPrimitiveModelProjection, PrimitiveTypeApi, GPrimitiveDatum>
    implements GenPrimitiveModelProjection<OpInputModelProjection<?, ?, ?, ?>, OpInputPrimitiveModelProjection, PrimitiveTypeApi> {

  public OpInputPrimitiveModelProjection(
      @NotNull PrimitiveTypeApi model,
      boolean required,
      @Nullable GPrimitiveDatum defaultValue,
      @NotNull OpParams params,
      @NotNull Annotations annotations,
      @Nullable OpInputModelProjection<?, ?, ?, ?> metaProjection,
      @Nullable List<OpInputPrimitiveModelProjection> tails,
      @NotNull TextLocation location) {
    super(model, required, defaultValue, params, annotations, metaProjection, tails, location);
  }

  public OpInputPrimitiveModelProjection(final @NotNull PrimitiveTypeApi model, final @NotNull TextLocation location) {
    super(model, location);
  }

  @Override
  protected OpInputPrimitiveModelProjection merge(
      final @NotNull PrimitiveTypeApi model,
      final boolean mergedRequired,
      final @Nullable GPrimitiveDatum mergedDefault,
      final @NotNull List<OpInputPrimitiveModelProjection> modelProjections,
      final @NotNull OpParams mergedParams,
      final @NotNull Annotations mergedAnnotations,
      final @Nullable OpInputModelProjection<?, ?, ?, ?> mergedMetaProjection,
      final @Nullable List<OpInputPrimitiveModelProjection> mergedTails) {

    return new OpInputPrimitiveModelProjection(
        model,
        mergedRequired,
        mergedDefault,
        mergedParams,
        mergedAnnotations,
        mergedMetaProjection,
        mergedTails,
        TextLocation.UNKNOWN
    );
  }
}
