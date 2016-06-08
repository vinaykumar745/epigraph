// This is a generated file. Not intended for manual editing.
package com.sumologic.epigraph.schema.parser.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.sumologic.epigraph.schema.parser.lexer.SchemaElementTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.sumologic.epigraph.schema.parser.psi.*;
import com.intellij.psi.PsiReference;
import com.sumologic.epigraph.schema.parser.Fqn;

public class SchemaFqnSegmentImpl extends ASTWrapperPsiElement implements SchemaFqnSegment {

  public SchemaFqnSegmentImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SchemaVisitor visitor) {
    visitor.visitFqnSegment(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SchemaVisitor) accept((SchemaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public PsiElement getId() {
    return notNullChild(findChildByType(S_ID));
  }

  @Nullable
  public String getName() {
    return SchemaPsiImplUtil.getName(this);
  }

  public PsiElement setName(String name) {
    return SchemaPsiImplUtil.setName(this, name);
  }

  @NotNull
  public PsiElement getNameIdentifier() {
    return SchemaPsiImplUtil.getNameIdentifier(this);
  }

  @Nullable
  public SchemaFqn getSchemaFqn() {
    return SchemaPsiImplUtil.getSchemaFqn(this);
  }

  @Nullable
  public SchemaFqnTypeRef getSchemaFqnTypeRef() {
    return SchemaPsiImplUtil.getSchemaFqnTypeRef(this);
  }

  public boolean isLast() {
    return SchemaPsiImplUtil.isLast(this);
  }

  @Nullable
  public PsiReference getReference() {
    return SchemaPsiImplUtil.getReference(this);
  }

  @NotNull
  public Fqn getFqn() {
    return SchemaPsiImplUtil.getFqn(this);
  }

}
