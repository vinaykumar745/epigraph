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

package ws.epigraph.projections.abs;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ws.epigraph.lang.TextLocation;
import ws.epigraph.projections.Annotations;
import ws.epigraph.projections.gen.GenModelProjection;
import ws.epigraph.types.DatumType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public abstract class AbstractModelProjection<MP extends GenModelProjection</*MP*/?, ?>, M extends DatumType>
    implements GenModelProjection<MP, M> {
  @NotNull
  protected final M model;
  @Nullable
  protected final MP metaProjection;
  @NotNull
  protected final Annotations annotations;
  @NotNull
  private final TextLocation location;

  public AbstractModelProjection(
      @NotNull M model,
      @Nullable MP metaProjection,
      @NotNull Annotations annotations,
      @NotNull TextLocation location) {
    this.model = model;
    this.metaProjection = metaProjection;
    this.annotations = annotations;
    this.location = location;
  }

  @NotNull
  public M model() { return model; }

  @Nullable
  @Override
  public MP metaProjection() { return metaProjection; }

  @NotNull
  public Annotations annotations() { return annotations; }

  @SuppressWarnings("unchecked")
  @Nullable
  @Override
  /* static */
  public MP merge(
      @NotNull final DatumType model,
      @NotNull final List<? extends GenModelProjection<?, ?>> modelProjections) {

    if (modelProjections.isEmpty()) return null;
    if (modelProjections.size() == 1) return (MP) modelProjections.get(0);

    List<Annotations> annotationsList = new ArrayList<>();
    List<MP> metaProjectionsList = new ArrayList<>();

    for (final GenModelProjection<?, ?> p : modelProjections) {
      AbstractModelProjection<MP, ?> mp = (AbstractModelProjection<MP, ?>) p;
      annotationsList.add(mp.annotations());
      metaProjectionsList.add(mp.metaProjection());
    }

    final MP mergedMetaProjection;
    if (metaProjectionsList.isEmpty()) mergedMetaProjection = null;
    else {
      final MP mp = metaProjectionsList.get(0);
      DatumType metaModel = model/*.metaModel()*/; // TODO should get meta-model type here
      mergedMetaProjection = (MP) mp.merge(metaModel, metaProjectionsList);
    }

    return merge(
        model,
        modelProjections,
        Annotations.merge(annotationsList),
        mergedMetaProjection
    );
  }

  @Nullable
  protected MP merge(
      @NotNull final DatumType model,
      @NotNull final List<? extends GenModelProjection<?, ?>> modelProjections,
      @NotNull Annotations mergedAnnotations,
      @Nullable MP mergedMetaProjection) {

    throw new RuntimeException("unimplemented"); // todo
  }

  @NotNull
  public TextLocation location() { return location; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractModelProjection<?, ?> that = (AbstractModelProjection<?, ?>) o;
    return Objects.equals(model, that.model) &&
           Objects.equals(metaProjection, that.metaProjection) &&
           Objects.equals(annotations, that.annotations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(model, metaProjection, annotations);
  }
}