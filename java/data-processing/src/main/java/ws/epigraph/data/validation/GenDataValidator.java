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

package ws.epigraph.data.validation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ws.epigraph.data.*;
import ws.epigraph.projections.gen.*;
import ws.epigraph.types.FieldApi;
import ws.epigraph.types.Field;
import ws.epigraph.types.Tag;
import ws.epigraph.types.TagApi;

import java.util.*;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public abstract class GenDataValidator<
    P extends GenProjection<? extends P, TP, EP, ? extends MP>,
    EP extends GenEntityProjection<EP, TP, MP>,
    TP extends GenTagProjectionEntry<TP, MP>,
    MP extends GenModelProjection<EP, TP, /*MP*/?, /*SMP*/?, /*TMP*/? /*M*/>,
    RMP extends GenRecordModelProjection<P, TP, EP, MP, RMP, FPE, FP, ?>,
    MMP extends GenMapModelProjection<P, TP, EP, MP, MMP, ?>,
    LMP extends GenListModelProjection<P, TP, EP, MP, LMP, ?>,
    PMP extends GenPrimitiveModelProjection<EP, TP, MP, PMP, ?>,
    FPE extends GenFieldProjectionEntry<P, TP, MP, FP>,
    FP extends GenFieldProjection<P, TP, MP, FP>
    > {

  protected final @NotNull DataValidationContext context = new DataValidationContext();
  protected final @NotNull Map<Data, Set<P>> visited = new IdentityHashMap<>();

  public @NotNull List<? extends DataValidationError> errors() { return context.errors(); }

  public void validateData(@Nullable Data data, @NotNull P projection) {
    if (data == null) return;

    P normalizedProjection = projection.normalizedForType(data.type());

    Set<P> checkedProjections = visited.get(data);
    if (checkedProjections == null) {
      checkedProjections = Collections.newSetFromMap(new IdentityHashMap<P, Boolean>());
    } else if (checkedProjections.contains(normalizedProjection))
      return;

    checkedProjections.add(normalizedProjection);

    validateDataOnly(data, normalizedProjection);

    for (final TP tagProjection : normalizedProjection.tagProjections().values()) {
      final TagApi tag = tagProjection.tag();
      context.withStackItem(new DataValidationContext.TagStackItem(tag), () -> {
        final Datum datum = data._raw().getDatum((Tag) tag);
        if (datum != null)
          validateDatum(datum, tagProjection.modelProjection());
      });
    }

    checkedProjections.remove(normalizedProjection);
    if (checkedProjections.isEmpty())
      visited.remove(data);
  }

  protected void validateDataOnly(@NotNull Data data, @NotNull P projection) {
    if (projection.isEntityProjection())
      validateDataOnly(data, projection.asEntityProjection());
  }

  protected void validateDataOnly(@NotNull Data data, @NotNull EP entityProjection) {}

  @SuppressWarnings("unchecked")
  public void validateDatum(@NotNull Datum datum, @NotNull MP projection) {
    MP normalizedProjection = (MP) projection.normalizedForType(datum.type());

    validateDatumOnly(datum, normalizedProjection);

    switch (normalizedProjection.type().kind()) {
      case RECORD:
        validateRecordDatum((RecordDatum) datum, (RMP) normalizedProjection);
        break;
      case MAP:
        validateMapDatum((MapDatum) datum, (MMP) normalizedProjection);
        break;
      case LIST:
        validateListDatum((ListDatum) datum, (LMP) normalizedProjection);
        break;
      case PRIMITIVE:
        validatePrimitiveDatum((PrimitiveDatum<?>) datum, (PMP) normalizedProjection);
        break;
      default:
        throw new RuntimeException(
            "Unsupported model kind: " + normalizedProjection.type().kind().getClass().getName());
    }
  }

  protected void validateDatumOnly(@NotNull Datum datum, @NotNull MP projection) {}

  public void validateRecordDatum(@NotNull RecordDatum datum, @NotNull RMP projection) {
    validateRecordDatumOnly(datum, projection);

    for (final FPE fpe : projection.fieldProjections().values()) {
      final FieldApi field = fpe.field();

      context.withStackItem(new DataValidationContext.FieldStackItem(field), () -> {
        final Data data = datum._raw().getData((Field) field);
        if (data != null)
          validateData(data, fpe.fieldProjection().projection());
      });
    }
  }

  protected void validateRecordDatumOnly(@NotNull RecordDatum datum, @NotNull RMP projection) { }

  public void validateMapDatum(@NotNull MapDatum datum, @NotNull MMP projection) {
    validateMapDatumOnly(datum, projection);

    for (final Map.Entry<Datum.Imm, ? extends Data> entry : datum._raw().elements().entrySet()) {
      final Datum.Imm key = entry.getKey();

      context.withStackItem(new DataValidationContext.MapKeyStackItem(key), () -> {
        validateData(entry.getValue(), projection.itemsProjection());
      });
    }
  }

  protected void validateMapDatumOnly(@NotNull MapDatum datum, @NotNull MMP projection) {}

  public void validateListDatum(@NotNull ListDatum datum, @NotNull LMP projection) {
    validateListDatumOnly(datum, projection);

    int index = 0;
    for (final Data data : datum._raw().elements()) {
      context.withStackItem(new DataValidationContext.ListIndexStackItem(index++), () -> {
        validateData(data, projection.itemsProjection());
      });
    }
  }

  protected void validateListDatumOnly(@NotNull ListDatum datum, @NotNull LMP projection) { }

  public void validatePrimitiveDatum(@NotNull PrimitiveDatum<?> datum, @NotNull PMP projection) { }

}
