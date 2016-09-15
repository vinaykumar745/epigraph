package io.epigraph.lang.gdata;

import io.epigraph.lang.Fqn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class GDataList extends GDataVarValue {
  @NotNull
  private final List<GDataValue> values;

  public GDataList(@Nullable Fqn typeRef, @NotNull List<GDataValue> values) {
    super(typeRef);
    this.values = values;
  }

  @NotNull
  public List<GDataValue> values() {
    return values;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    GDataList gDataList = (GDataList) o;
    return Objects.equals(values, gDataList.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), values);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (typeRef() != null) sb.append(typeRef());
    sb.append('[');
    sb.append(values.stream().map(Object::toString).collect(Collectors.joining(", ")));
    sb.append(']');
    return sb.toString();
  }
}
