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

package ws.epigraph.projections.op.output;

import ws.epigraph.lang.TextLocation;
import ws.epigraph.projections.Annotations;
import ws.epigraph.projections.gen.GenMapModelProjection;
import ws.epigraph.projections.gen.GenModelProjection;
import ws.epigraph.projections.op.OpKeyPresence;
import ws.epigraph.projections.op.OpParams;
import ws.epigraph.types.DatumType;
import ws.epigraph.types.MapType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class OpOutputMapModelProjection
    extends OpOutputModelProjection<OpOutputMapModelProjection, MapType>
    implements GenMapModelProjection<
    OpOutputVarProjection,
    OpOutputTagProjectionEntry,
    OpOutputModelProjection<?, ?>,
    OpOutputMapModelProjection,
    MapType
    > {

  @NotNull
  private final OpOutputKeyProjection keyProjection;
  @NotNull
  private final OpOutputVarProjection itemsProjection;

  public OpOutputMapModelProjection(
      @NotNull MapType model,
      @NotNull OpParams params,
      @NotNull Annotations annotations,
      @Nullable OpOutputMapModelProjection metaProjection,
      @NotNull OpOutputKeyProjection keyProjection,
      @NotNull OpOutputVarProjection itemsProjection,
      @NotNull TextLocation location) {
    super(model, params, annotations, metaProjection, location);
    this.itemsProjection = itemsProjection;
    this.keyProjection = keyProjection;
  }

  @NotNull
  public OpOutputKeyProjection keyProjection() { return keyProjection; }

  @NotNull
  public OpOutputVarProjection itemsProjection() { return itemsProjection; }

  /* static */
  @Override
  protected OpOutputMapModelProjection merge(
      @NotNull final DatumType model,
      @NotNull final List<? extends GenModelProjection<?, ?>> modelProjections,
      @NotNull final OpParams mergedParams,
      @NotNull final Annotations mergedAnnotations,
      @Nullable final OpOutputMapModelProjection mergedMetaProjection) {

    MapType mapType = (MapType) model;

    List<OpParams> keysParams = new ArrayList<>(modelProjections.size());
    List<Annotations> keysAnnotations = new ArrayList<>(modelProjections.size());
    OpKeyPresence mergedKeysPresence = null;
    List<OpOutputVarProjection> itemsProjectionsToMerge = new ArrayList<>(modelProjections.size());

    OpOutputMapModelProjection prevProjection = null;
    for (final GenModelProjection<?, ?> projection : modelProjections) {
      OpOutputMapModelProjection mmp = (OpOutputMapModelProjection) projection;

      @NotNull final OpOutputKeyProjection keyProjection = mmp.keyProjection();
      keysParams.add(keyProjection.params());
      keysAnnotations.add(keyProjection.annotations());
      final OpKeyPresence presence = keyProjection.presence();

      if (mergedKeysPresence == null) mergedKeysPresence = presence;
      else {
        @Nullable OpKeyPresence newKeysPresence = OpKeyPresence.merge(mergedKeysPresence, presence);
        if (newKeysPresence == null)
          throw new IllegalArgumentException(
              String.format(
                  "Can't merge key projection defined at %s and key projection defined at %s: incompatible keys presence modes",
                  prevProjection.location(),
                  mmp.location()
              )
          );
        mergedKeysPresence = newKeysPresence;
      }

      itemsProjectionsToMerge.add(mmp.itemsProjection());

      prevProjection = mmp;
    }

    assert mergedKeysPresence != null; // modelProjections should have at least one element
    assert !itemsProjectionsToMerge.isEmpty();

    return new OpOutputMapModelProjection(
        mapType,
        mergedParams,
        mergedAnnotations,
        mergedMetaProjection,
        new OpOutputKeyProjection(
            mergedKeysPresence,
            OpParams.merge(keysParams),
            Annotations.merge(keysAnnotations),
            TextLocation.UNKNOWN
        ),
        itemsProjectionsToMerge.get(0).merge(itemsProjectionsToMerge),
        TextLocation.UNKNOWN
    );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    OpOutputMapModelProjection that = (OpOutputMapModelProjection) o;
    return Objects.equals(itemsProjection, that.itemsProjection) &&
           Objects.equals(keyProjection, that.keyProjection);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), itemsProjection, keyProjection);
  }
}