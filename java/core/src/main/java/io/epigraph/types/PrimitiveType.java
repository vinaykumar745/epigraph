/* Created by yegor on 7/22/16. */

package io.epigraph.types;

import io.epigraph.names.QualifiedTypeName;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public abstract class PrimitiveType extends DatumType {

  protected PrimitiveType(
      QualifiedTypeName name,
      List<? extends PrimitiveType> immediateSupertypes,
      boolean polymorphic
  ) {
    super(name, immediateSupertypes, polymorphic);
  }

  @Override
  public @NotNull QualifiedTypeName name() { return (QualifiedTypeName) super.name(); }

  @Override
  @SuppressWarnings("unchecked")
  public @NotNull List<? extends PrimitiveType> immediateSupertypes() {
    return (List<? extends PrimitiveType>) super.immediateSupertypes();
  }

  @Override
  @SuppressWarnings("unchecked")
  public @NotNull Collection<? extends PrimitiveType> supertypes() {
    return (Collection<? extends PrimitiveType>) super.supertypes();
  }


  public interface Raw extends DatumType.Raw {}

}
