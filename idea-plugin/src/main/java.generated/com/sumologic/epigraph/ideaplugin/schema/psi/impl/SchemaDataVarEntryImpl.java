// This is a generated file. Not intended for manual editing.
package com.sumologic.epigraph.ideaplugin.schema.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.sumologic.epigraph.ideaplugin.schema.lexer.SchemaElementTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.sumologic.epigraph.ideaplugin.schema.psi.*;

public class SchemaDataVarEntryImpl extends ASTWrapperPsiElement implements SchemaDataVarEntry {

  public SchemaDataVarEntryImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SchemaVisitor visitor) {
    visitor.visitDataVarEntry(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SchemaVisitor) accept((SchemaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SchemaDataValue getDataValue() {
    return findChildByClass(SchemaDataValue.class);
  }

  @Override
  @NotNull
  public List<SchemaFqnTypeRef> getFqnTypeRefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SchemaFqnTypeRef.class);
  }

  @Override
  @NotNull
  public PsiElement getColon() {
    return findNotNullChildByType(S_COLON);
  }

  @Override
  @Nullable
  public PsiElement getComma() {
    return findChildByType(S_COMMA);
  }

  @Override
  @Nullable
  public PsiElement getNull() {
    return findChildByType(S_NULL);
  }

  @Override
  @NotNull
  public PsiElement getId() {
    return findNotNullChildByType(S_ID);
  }

}
