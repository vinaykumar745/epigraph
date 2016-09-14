package io.epigraph.projections.op.input;

import de.uka.ilkd.pp.DataLayouter;
import io.epigraph.data.RecordDatum;
import io.epigraph.types.RecordType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class OpInputRecordModelProjection extends OpInputModelProjection<RecordType, RecordDatum> {
  private static final ThreadLocal<IdentityHashMap<OpInputRecordModelProjection, OpInputRecordModelProjection>>
      equalsVisited = new ThreadLocal<>();

  @Nullable
  private LinkedHashSet<OpInputFieldProjection> fieldProjections;

  public OpInputRecordModelProjection(@NotNull RecordType model,
                                      boolean required,
                                      @Nullable RecordDatum defaultValue,
                                      @Nullable LinkedHashSet<OpInputFieldProjection> fieldProjections) {
    super(model, required, defaultValue);
    this.fieldProjections = fieldProjections;

    Collection<@NotNull ? extends RecordType.Field> fields = model.fields();
    if (fieldProjections != null) {
      for (OpInputFieldProjection fieldProjection : fieldProjections) {
        RecordType.Field field = fieldProjection.getField();
        if (!fields.contains(field))
          throw new IllegalArgumentException(
              String.format("Field '%s' does not belong to record model '%s'. Known fields: %s",
                            field.name(), model.name(), listFields(fields)
              )
          );
      }
    }
  }

  private static String listFields(@NotNull Collection<? extends RecordType.Field> fields) {
    return fields.stream().map(RecordType.Field::name).collect(Collectors.joining(", "));
  }

  @NotNull
  public static LinkedHashSet<OpInputFieldProjection> fields(OpInputFieldProjection... fieldProjections) {
    return new LinkedHashSet<>(Arrays.asList(fieldProjections));
  }

  @Nullable
  public LinkedHashSet<OpInputFieldProjection> fieldProjections() { return fieldProjections; }

  public void addFieldProjection(@NotNull OpInputFieldProjection fieldProjection) {
    if (fieldProjections == null) fieldProjections = new LinkedHashSet<>();
    fieldProjections.add(fieldProjection);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    OpInputRecordModelProjection that = (OpInputRecordModelProjection) o;

    IdentityHashMap<OpInputRecordModelProjection, OpInputRecordModelProjection> visitedMap = equalsVisited.get();
    boolean mapWasNull = visitedMap == null;
    if (mapWasNull) {
      visitedMap = new IdentityHashMap<>();
      equalsVisited.set(visitedMap);
    } else {
      if (that == visitedMap.get(this)) return true;
      if (visitedMap.containsKey(this)) return false;
    }
    visitedMap.put(this, that);
    boolean res = Objects.equals(fieldProjections, that.fieldProjections);
    if (mapWasNull) equalsVisited.remove();
    return res;
  }

  @Override
  public int hashCode() {
    return super.hashCode() * 31 + (fieldProjections == null ? 13 : fieldProjections.size());
  }

  @Override
  public <Exc extends Exception> void prettyPrint(DataLayouter<Exc> l) throws Exc {
    prettyPrintModel(l);
    l.beginCInd().print(" {");

    prettyPrintDefaultValueBlock(l);

    if (fieldProjections != null && !fieldProjections.isEmpty()) {
      l.brk().beginIInd().print("fields: {");
      for (OpInputFieldProjection fieldProjection : fieldProjections) {
        l.nl().print(fieldProjection);
      }
      l.end().brk().print("}");
    }

    l.end().brk().print("}");
  }
}