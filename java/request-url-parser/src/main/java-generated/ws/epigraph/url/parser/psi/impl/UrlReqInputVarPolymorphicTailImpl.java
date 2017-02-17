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

public class UrlReqInputVarPolymorphicTailImpl extends ASTWrapperPsiElement implements UrlReqInputVarPolymorphicTail {

  public UrlReqInputVarPolymorphicTailImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull UrlVisitor visitor) {
    visitor.visitReqInputVarPolymorphicTail(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof UrlVisitor) accept((UrlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public UrlReqInputVarMultiTail getReqInputVarMultiTail() {
    return findChildByClass(UrlReqInputVarMultiTail.class);
  }

  @Override
  @Nullable
  public UrlReqInputVarSingleTail getReqInputVarSingleTail() {
    return findChildByClass(UrlReqInputVarSingleTail.class);
  }

  @Override
  @NotNull
  public PsiElement getTilda() {
    return findNotNullChildByType(U_TILDA);
  }

}
