// This is a generated file. Not intended for manual editing.
package ws.epigraph.url.parser.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ws.epigraph.url.lexer.UrlElementTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import ws.epigraph.url.parser.psi.*;

public class UrlReqUpdateKeysProjectionImpl extends ASTWrapperPsiElement implements UrlReqUpdateKeysProjection {

  public UrlReqUpdateKeysProjectionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull UrlVisitor visitor) {
    visitor.visitReqUpdateKeysProjection(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof UrlVisitor) accept((UrlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<UrlReqUpdateKeyProjection> getReqUpdateKeyProjectionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, UrlReqUpdateKeyProjection.class);
  }

  @Override
  @NotNull
  public PsiElement getBracketLeft() {
    return findNotNullChildByType(U_BRACKET_LEFT);
  }

  @Override
  @NotNull
  public PsiElement getBracketRight() {
    return findNotNullChildByType(U_BRACKET_RIGHT);
  }

  @Override
  @Nullable
  public PsiElement getPlus() {
    return findChildByType(U_PLUS);
  }

}