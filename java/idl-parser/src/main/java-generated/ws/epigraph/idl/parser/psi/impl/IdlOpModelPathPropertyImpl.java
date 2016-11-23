// This is a generated file. Not intended for manual editing.
package ws.epigraph.idl.parser.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ws.epigraph.idl.lexer.IdlElementTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import ws.epigraph.idl.parser.psi.*;

public class IdlOpModelPathPropertyImpl extends ASTWrapperPsiElement implements IdlOpModelPathProperty {

  public IdlOpModelPathPropertyImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull IdlVisitor visitor) {
    visitor.visitOpModelPathProperty(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof IdlVisitor) accept((IdlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public IdlAnnotation getAnnotation() {
    return findChildByClass(IdlAnnotation.class);
  }

  @Override
  @Nullable
  public IdlOpParam getOpParam() {
    return findChildByClass(IdlOpParam.class);
  }

}