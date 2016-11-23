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

package ws.epigraph.projections.req.path;

import ws.epigraph.lang.TextLocation;
import ws.epigraph.projections.Annotations;
import ws.epigraph.projections.gen.GenMapModelProjection;
import ws.epigraph.projections.req.ReqParams;
import ws.epigraph.types.MapType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class ReqMapModelPath
    extends ReqModelPath<ReqMapModelPath, MapType>
    implements GenMapModelProjection<
    ReqVarPath,
    ReqTagPath,
    ReqModelPath<?, ?>,
    ReqMapModelPath,
    MapType
    > {

  @NotNull
  private final ReqPathKeyProjection key;
  @NotNull
  private final ReqVarPath valuesProjection;

  public ReqMapModelPath(
      @NotNull MapType model,
      @NotNull ReqParams params,
      @NotNull Annotations annotations,
      @NotNull ReqPathKeyProjection key,
      @NotNull ReqVarPath valuesProjection,
      @NotNull TextLocation location) {

    super(model, params, annotations, location);
    this.key = key;
    this.valuesProjection = valuesProjection;
  }

  @NotNull
  public ReqVarPath itemsProjection() { return valuesProjection; }

  @NotNull
  public ReqPathKeyProjection key() { return key; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    ReqMapModelPath that = (ReqMapModelPath) o;
    return Objects.equals(key, that.key) &&
           Objects.equals(valuesProjection, that.valuesProjection);
  }

  @Override
  public int hashCode() { return Objects.hash(super.hashCode(), key, valuesProjection); }
}