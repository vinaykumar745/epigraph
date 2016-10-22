package io.epigraph.projections.op.input;

import io.epigraph.data.ListDatum;
import io.epigraph.lang.TextLocation;
import io.epigraph.projections.Annotations;
import io.epigraph.projections.gen.GenListModelProjection;
import io.epigraph.types.ListType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class OpInputListModelProjection
    extends OpInputModelProjection<OpInputListModelProjection, ListType, ListDatum>
    implements GenListModelProjection<
    OpInputVarProjection,
    OpInputTagProjectionEntry,
    OpInputModelProjection<?, ?, ?>,
    OpInputListModelProjection,
    ListType
    > {

  @NotNull
  private OpInputVarProjection itemsProjection;

  public OpInputListModelProjection(
      @NotNull ListType model,
      boolean required,
      @Nullable ListDatum defaultValue,
      @Nullable Annotations annotations,
      @Nullable OpInputListModelProjection metaProjection,
      @NotNull OpInputVarProjection itemsProjection,
      @NotNull TextLocation location) {
    super(model, required, defaultValue, annotations, metaProjection, location);
    this.itemsProjection = itemsProjection;
  }

  @NotNull
  public OpInputVarProjection itemsProjection() { return itemsProjection; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    OpInputListModelProjection that = (OpInputListModelProjection) o;
    return Objects.equals(itemsProjection, that.itemsProjection);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), itemsProjection);
  }
}
