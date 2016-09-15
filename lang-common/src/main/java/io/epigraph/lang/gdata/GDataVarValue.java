package io.epigraph.lang.gdata;

import io.epigraph.lang.Fqn;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public abstract class GDataVarValue extends GDataValue {
  @Nullable
  private final Fqn typeRef;

  protected GDataVarValue(@Nullable Fqn typeRef) {this.typeRef = typeRef;}

  @Nullable
  public Fqn typeRef() {
    return typeRef;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GDataVarValue that = (GDataVarValue) o;
    return Objects.equals(typeRef, that.typeRef);
  }

  @Override
  public int hashCode() {
    return Objects.hash(typeRef);
  }
}

