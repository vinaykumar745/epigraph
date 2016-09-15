package io.epigraph.lang.gdata;

import io.epigraph.lang.Fqn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class GDataRecord extends GDataVarValue {
  @NotNull
  private final LinkedHashMap<String, GDataValue> fields;

  public GDataRecord(@Nullable Fqn typeRef, @NotNull LinkedHashMap<String, GDataValue> fields) {
    super(typeRef);
    this.fields = fields;
  }

  @NotNull
  public LinkedHashMap<String, GDataValue> fields() {
    return fields;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    GDataRecord gDataMap = (GDataRecord) o;
    return Objects.equals(fields, gDataMap.fields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), fields);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (typeRef() != null) sb.append(typeRef());
    sb.append('{');
    sb.append(fields.entrySet()
                    .stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining(", ")));
    sb.append('}');
    return sb.toString();
  }
}
