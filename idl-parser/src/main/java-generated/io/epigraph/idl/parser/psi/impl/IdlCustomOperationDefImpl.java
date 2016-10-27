// This is a generated file. Not intended for manual editing.
package io.epigraph.idl.parser.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.epigraph.idl.lexer.IdlElementTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import io.epigraph.idl.parser.psi.*;

public class IdlCustomOperationDefImpl extends ASTWrapperPsiElement implements IdlCustomOperationDef {

  public IdlCustomOperationDefImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull IdlVisitor visitor) {
    visitor.visitCustomOperationDef(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof IdlVisitor) accept((IdlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<IdlCustomOperationBodyPart> getCustomOperationBodyPartList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, IdlCustomOperationBodyPart.class);
  }

  @Override
  @NotNull
  public IdlOperationName getOperationName() {
    return findNotNullChildByClass(IdlOperationName.class);
  }

  @Override
  @Nullable
  public PsiElement getCurlyLeft() {
    return findChildByType(I_CURLY_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getCurlyRight() {
    return findChildByType(I_CURLY_RIGHT);
  }

  @Override
  @NotNull
  public PsiElement getCustomOp() {
    return findNotNullChildByType(I_CUSTOM_OP);
  }

}
