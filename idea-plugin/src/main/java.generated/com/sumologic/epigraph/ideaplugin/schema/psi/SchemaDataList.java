// This is a generated file. Not intended for manual editing.
package com.sumologic.epigraph.ideaplugin.schema.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SchemaDataList extends SchemaDataValue {

  @NotNull
  List<SchemaDataValue> getDataValueList();

  @NotNull
  PsiElement getBracketLeft();

  @Nullable
  PsiElement getBracketRight();

}
