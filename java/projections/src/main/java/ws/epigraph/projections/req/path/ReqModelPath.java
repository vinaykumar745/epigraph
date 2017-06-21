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

package ws.epigraph.projections.req.path;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ws.epigraph.lang.TextLocation;
import ws.epigraph.projections.req.Directives;
import ws.epigraph.projections.ModelNormalizationContext;
import ws.epigraph.projections.req.AbstractReqModelProjection;
import ws.epigraph.projections.req.ReqParams;
import ws.epigraph.types.DatumTypeApi;

import java.util.List;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public abstract class ReqModelPath<
    MP extends ReqModelPath</*MP*/?, /*SMP*/?, ?>,
    SMP extends ReqModelPath</*MP*/?, SMP, ?>,
    M extends DatumTypeApi>
    extends AbstractReqModelProjection<MP, SMP, M> {

  protected ReqModelPath(
      @NotNull M model,
      @NotNull ReqParams params,
      @NotNull Directives directives,
      @NotNull TextLocation location) {
    super(model, params, null, directives, null, location);
  }

  @Override
  protected @NotNull ModelNormalizationContext<M, SMP> newNormalizationContext() {
    throw new RuntimeException("Path references not supported");
  }

  @Override
  protected SMP merge(
      final @NotNull M model,
      final @NotNull List<SMP> modelProjections,
      final @NotNull ReqParams mergedParams,
      final @NotNull Directives mergedDirectives,
      final @Nullable MP mergedMetaProjection,
      final @Nullable List<SMP> mergedTails) {
    throw new RuntimeException("Path normalization not supported");
  }
}
