// This is a generated file. Not intended for manual editing.
package io.epigraph.url.parser.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface UrlReqOutputComaFieldProjection extends PsiElement {

  @NotNull
  UrlQid getQid();

  @NotNull
  List<UrlReqAnnotation> getReqAnnotationList();

  @NotNull
  UrlReqOutputComaVarProjection getReqOutputComaVarProjection();

  @NotNull
  List<UrlReqParam> getReqParamList();

  @Nullable
  PsiElement getPlus();

}
