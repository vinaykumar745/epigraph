// This is a generated file. Not intended for manual editing.
package io.epigraph.schema.parser.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SchemaDataPrimitive extends SchemaDataValue {

  @Nullable
  SchemaFqnTypeRef getFqnTypeRef();

  @Nullable
  PsiElement getParenLeft();

  @Nullable
  PsiElement getParenRight();

  @Nullable
  PsiElement getBoolean();

  @Nullable
  PsiElement getNumber();

  @Nullable
  PsiElement getString();

}
