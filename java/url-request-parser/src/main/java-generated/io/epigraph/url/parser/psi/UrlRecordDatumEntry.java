// This is a generated file. Not intended for manual editing.
package io.epigraph.url.parser.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface UrlRecordDatumEntry extends PsiElement {

  @Nullable
  UrlDataValue getDataValue();

  @NotNull
  UrlQid getQid();

  @NotNull
  PsiElement getColon();

  @Nullable
  PsiElement getComma();

}
