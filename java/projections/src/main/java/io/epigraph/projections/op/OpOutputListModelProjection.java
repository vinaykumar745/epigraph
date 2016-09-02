package io.epigraph.projections.op;

import de.uka.ilkd.pp.DataLayouter;
import io.epigraph.types.ListType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class OpOutputListModelProjection extends OpOutputModelProjection<ListType, OpOutputListModelProjection> {
  @NotNull
  private OpOutputVarProjection itemsProjection;

  public OpOutputListModelProjection(@NotNull ListType model,
                                     boolean required,
                                     @Nullable Set<OpParam> params,
                                     @NotNull OpOutputVarProjection itemsProjection,
                                     @Nullable LinkedHashSet<OpOutputListModelProjection> polymorphicTail) {
    super(model, required, params, polymorphicTail);
    this.itemsProjection = itemsProjection;
  }

  @NotNull
  public OpOutputVarProjection itemsProjection() { return itemsProjection; }

  @Override
  protected OpOutputListModelProjection mergedProjection(@NotNull ListType model,
                                                           boolean mergedRequired,
                                                           @Nullable Set<OpParam> mergedParams,
                                                           @NotNull Collection<OpOutputListModelProjection> projectionsToMerge) {
    throw new UnsupportedOperationException("todo");
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    OpOutputListModelProjection that = (OpOutputListModelProjection) o;
    return Objects.equals(itemsProjection, that.itemsProjection);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), itemsProjection);
  }

  @Override
  public <Exc extends Exception> void prettyPrint(DataLayouter<Exc> l) throws Exc {
    prettyPrintModel(l);
    l.beginCInd().print(" {");

    if (params != null && !params.isEmpty()) {
      l.brk().beginCInd().print("params: {").brk();
      prettyPrintParams(l, params);
      l.end().brk().print("}");
    }

    l.brk().beginCInd().print("items:").brk();
    l.print(itemsProjection);
    l.end();

    l.end().brk().print("}");
    if (polymorphicTail != null && !polymorphicTail.isEmpty()) prettyPrintTail(l, polymorphicTail);
  }
}
