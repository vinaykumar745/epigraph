// This is a generated file. Not intended for manual editing.
package io.epigraph.url.parser.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface UrlUrl extends PsiElement {

  @NotNull
  UrlQid getQid();

  @NotNull
  UrlReqOutputTrunkVarProjection getReqOutputTrunkVarProjection();

  @NotNull
  List<UrlRequestParam> getRequestParamList();

  @NotNull
  PsiElement getSlash();

}
