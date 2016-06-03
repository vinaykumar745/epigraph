// This is a generated file. Not intended for manual editing.
package com.sumologic.epigraph.ideaplugin.schema.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.sumologic.epigraph.ideaplugin.schema.lexer.SchemaElementTypes.*;
import com.sumologic.epigraph.ideaplugin.schema.psi.*;

public class SchemaDataVarImpl extends SchemaDataValueImpl implements SchemaDataVar {

  public SchemaDataVarImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SchemaVisitor visitor) {
    visitor.visitDataVar(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SchemaVisitor) accept((SchemaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<SchemaDataVarEntry> getDataVarEntryList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SchemaDataVarEntry.class);
  }

  @Override
  @NotNull
  public PsiElement getAngleLeft() {
    return findNotNullChildByType(S_ANGLE_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getAngleRight() {
    return findChildByType(S_ANGLE_RIGHT);
  }

}
