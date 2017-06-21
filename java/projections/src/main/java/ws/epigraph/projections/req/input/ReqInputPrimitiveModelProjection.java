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

package ws.epigraph.projections.req.input;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ws.epigraph.lang.TextLocation;
import ws.epigraph.projections.req.Directives;
import ws.epigraph.projections.gen.GenPrimitiveModelProjection;
import ws.epigraph.projections.req.ReqParams;
import ws.epigraph.types.PrimitiveTypeApi;

import java.util.List;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class ReqInputPrimitiveModelProjection
    extends ReqInputModelProjection<ReqInputModelProjection<?, ?, ?>, ReqInputPrimitiveModelProjection, PrimitiveTypeApi>
    implements GenPrimitiveModelProjection<ReqInputModelProjection<?, ?, ?>, ReqInputPrimitiveModelProjection, PrimitiveTypeApi> {

  public ReqInputPrimitiveModelProjection(
      @NotNull PrimitiveTypeApi model,
      @NotNull ReqParams params,
      @NotNull Directives directives,
      @Nullable List<ReqInputPrimitiveModelProjection> tails,
      @NotNull TextLocation location) {
    super(model, params, directives, tails, location);
  }

  public ReqInputPrimitiveModelProjection(final @NotNull PrimitiveTypeApi model, final @NotNull TextLocation location) {
    super(model, location);
  }

  @Override
  protected ReqInputPrimitiveModelProjection merge(
      final @NotNull PrimitiveTypeApi model,
      final @NotNull List<ReqInputPrimitiveModelProjection> modelProjections,
      final @NotNull ReqParams mergedParams,
      final @NotNull Directives mergedDirectives,
      final @Nullable ReqInputModelProjection<?, ?, ?> mergedMetaProjection,
      final @Nullable List<ReqInputPrimitiveModelProjection> mergedTails) {

    return new ReqInputPrimitiveModelProjection(
        model,
        mergedParams,
        mergedDirectives,
        mergedTails,
        TextLocation.UNKNOWN
    );
  }
}
