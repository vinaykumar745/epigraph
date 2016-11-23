// This is a generated file. Not intended for manual editing.
package ws.epigraph.url.parser.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ws.epigraph.url.lexer.UrlElementTypes.*;
import ws.epigraph.url.parser.psi.*;

public class UrlDeleteUrlImpl extends UrlUrlImpl implements UrlDeleteUrl {

  public UrlDeleteUrlImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull UrlVisitor visitor) {
    visitor.visitDeleteUrl(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof UrlVisitor) accept((UrlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public UrlQid getQid() {
    return findNotNullChildByClass(UrlQid.class);
  }

  @Override
  @NotNull
  public UrlReqDeleteFieldProjection getReqDeleteFieldProjection() {
    return findNotNullChildByClass(UrlReqDeleteFieldProjection.class);
  }

  @Override
  @NotNull
  public UrlReqFieldPath getReqFieldPath() {
    return findNotNullChildByClass(UrlReqFieldPath.class);
  }

  @Override
  @Nullable
  public UrlReqOutputTrunkFieldProjection getReqOutputTrunkFieldProjection() {
    return findChildByClass(UrlReqOutputTrunkFieldProjection.class);
  }

  @Override
  @NotNull
  public List<UrlRequestParam> getRequestParamList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, UrlRequestParam.class);
  }

  @Override
  @NotNull
  public PsiElement getAngleLeft() {
    return findNotNullChildByType(U_ANGLE_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getAngleRight() {
    return findChildByType(U_ANGLE_RIGHT);
  }

  @Override
  @NotNull
  public PsiElement getSlash() {
    return findNotNullChildByType(U_SLASH);
  }

}