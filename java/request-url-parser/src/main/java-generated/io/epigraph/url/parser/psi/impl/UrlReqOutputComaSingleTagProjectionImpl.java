// This is a generated file. Not intended for manual editing.
package io.epigraph.url.parser.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.epigraph.url.lexer.UrlElementTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import io.epigraph.url.parser.psi.*;

public class UrlReqOutputComaSingleTagProjectionImpl extends ASTWrapperPsiElement implements UrlReqOutputComaSingleTagProjection {

  public UrlReqOutputComaSingleTagProjectionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull UrlVisitor visitor) {
    visitor.visitReqOutputComaSingleTagProjection(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof UrlVisitor) accept((UrlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<UrlReqAnnotation> getReqAnnotationList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, UrlReqAnnotation.class);
  }

  @Override
  @NotNull
  public UrlReqOutputComaModelProjection getReqOutputComaModelProjection() {
    return findNotNullChildByClass(UrlReqOutputComaModelProjection.class);
  }

  @Override
  @Nullable
  public UrlReqOutputModelMeta getReqOutputModelMeta() {
    return findChildByClass(UrlReqOutputModelMeta.class);
  }

  @Override
  @NotNull
  public List<UrlReqParam> getReqParamList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, UrlReqParam.class);
  }

  @Override
  @Nullable
  public UrlTagName getTagName() {
    return findChildByClass(UrlTagName.class);
  }

  @Override
  @Nullable
  public PsiElement getColon() {
    return findChildByType(U_COLON);
  }

  @Override
  @Nullable
  public PsiElement getPlus() {
    return findChildByType(U_PLUS);
  }

}