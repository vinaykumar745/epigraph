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

package ws.epigraph.projections.req.delete;

import ws.epigraph.lang.TextLocation;
import ws.epigraph.projections.Annotations;
import ws.epigraph.projections.RecordModelProjectionHelper;
import ws.epigraph.projections.gen.GenRecordModelProjection;
import ws.epigraph.projections.req.ReqParams;
import ws.epigraph.types.RecordTypeApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class ReqDeleteRecordModelProjection
    extends ReqDeleteModelProjection<ReqDeleteRecordModelProjection, RecordTypeApi>
    implements GenRecordModelProjection<
    ReqDeleteVarProjection,
    ReqDeleteTagProjectionEntry,
    ReqDeleteModelProjection<?, ?>,
    ReqDeleteRecordModelProjection,
    ReqDeleteFieldProjectionEntry,
    ReqDeleteFieldProjection,
    RecordTypeApi
    > {

  private final @NotNull Map<String, ReqDeleteFieldProjectionEntry> fieldProjections;

  public ReqDeleteRecordModelProjection(
      @NotNull RecordTypeApi model,
      @NotNull ReqParams params,
      @NotNull Annotations annotations,
      @NotNull Map<String, ReqDeleteFieldProjectionEntry> fieldProjections,
      @NotNull TextLocation location) {
    super(model, params, annotations, location);
    this.fieldProjections = fieldProjections;

    RecordModelProjectionHelper.checkFieldsBelongsToModel(fieldProjections.keySet(), model);
  }

  @Override
  public @NotNull Map<String, ReqDeleteFieldProjectionEntry> fieldProjections() { return fieldProjections; }

  @Override
  public @Nullable ReqDeleteFieldProjectionEntry fieldProjection(@NotNull String fieldName) {
    return fieldProjections.get(fieldName);
  }

  @Override
  public boolean equals(Object o) {
    return super.equals(o) && RecordModelProjectionHelper.equals(this, o);
  }

  @Override
  public int hashCode() {
    return super.hashCode() * 31 + fieldProjections.size();
  }
}
