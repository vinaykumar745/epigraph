// This is a generated file. Not intended for manual editing.
package io.epigraph.url.parser.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface UrlReqUpdateSingleTagProjection extends PsiElement {

  @NotNull
  List<UrlReqAnnotation> getReqAnnotationList();

  @NotNull
  List<UrlReqParam> getReqParamList();

  @Nullable
  UrlReqUpdateModelMeta getReqUpdateModelMeta();

  @NotNull
  UrlReqUpdateModelProjection getReqUpdateModelProjection();

  @Nullable
  UrlTagName getTagName();

  @Nullable
  PsiElement getColon();

}
