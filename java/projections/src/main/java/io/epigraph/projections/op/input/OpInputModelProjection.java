package io.epigraph.projections.op.input;

import de.uka.ilkd.pp.DataLayouter;
import de.uka.ilkd.pp.PrettyPrintable;
import io.epigraph.data.Datum;
import io.epigraph.types.DatumType;
import io.epigraph.util.pp.DataPrettyPrinter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public abstract class OpInputModelProjection<M extends DatumType, D extends Datum>
    implements PrettyPrintable {
  @NotNull
  protected final M model;
  protected final boolean required;
  @Nullable
  protected final D defaultValue;

  public OpInputModelProjection(@NotNull M model, boolean required, @Nullable D defaultValue) {
    this.model = model;
    this.required = required;
    this.defaultValue = defaultValue;
  }

  public M model() { return model; }

  public boolean required() { return required; }

  @Nullable
  public D defaultValue() { return defaultValue; }

  @Override
  public <Exc extends Exception> void prettyPrint(DataLayouter<Exc> l) throws Exc {
    prettyPrintModel(l);
    prettyPrintDefaultValueBlock(l);
  }

  protected <Exc extends Exception> void prettyPrintModel(DataLayouter<Exc> l) throws Exc {
    if (required) l.print("+");
    l.print(model.name().toString());
  }

  protected <Exc extends Exception> void prettyPrintDefaultValueBlock(DataLayouter<Exc> l) throws Exc {
    if (defaultValue != null) {
      l.beginCInd().print(" {");
      prettyPrintDefaultValue(l);
      l.end().brk().print("}");
    }
  }

  protected <E extends Exception> void prettyPrintDefaultValue(DataLayouter<E> l) throws E {
    assert defaultValue != null;
    l.print("default:").brk().print(defaultValue);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OpInputModelProjection<?, ?> that = (OpInputModelProjection<?, ?>) o;
    return required == that.required &&
           Objects.equals(model, that.model) &&
           Objects.equals(defaultValue, that.defaultValue);
  }

  @Override
  public int hashCode() { return Objects.hash(model, required, defaultValue); }

  @Override
  public String toString() { return DataPrettyPrinter.prettyPrint(this); }
}
