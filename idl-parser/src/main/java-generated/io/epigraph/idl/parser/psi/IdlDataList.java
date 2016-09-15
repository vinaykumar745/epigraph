// This is a generated file. Not intended for manual editing.
package io.epigraph.idl.parser.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface IdlDataList extends IdlVarValue {

  @NotNull
  List<IdlDataValue> getDataValueList();

  @Nullable
  IdlFqnTypeRef getFqnTypeRef();

  @NotNull
  PsiElement getBracketLeft();

  @Nullable
  PsiElement getBracketRight();

}
