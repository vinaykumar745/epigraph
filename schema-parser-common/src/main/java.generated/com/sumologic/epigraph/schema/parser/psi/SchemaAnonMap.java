// This is a generated file. Not intended for manual editing.
package com.sumologic.epigraph.schema.parser.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SchemaAnonMap extends PsiElement {

  @Nullable
  SchemaDefaultOverride getDefaultOverride();

  @NotNull
  List<SchemaTypeRef> getTypeRefList();

  @Nullable
  PsiElement getBracketLeft();

  @Nullable
  PsiElement getBracketRight();

  @Nullable
  PsiElement getComma();

  @NotNull
  PsiElement getMap();

}