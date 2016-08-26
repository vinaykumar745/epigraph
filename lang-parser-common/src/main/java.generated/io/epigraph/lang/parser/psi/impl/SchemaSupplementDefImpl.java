// This is a generated file. Not intended for manual editing.
package io.epigraph.lang.parser.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.epigraph.lang.lexer.EpigraphElementTypes.*;
import com.intellij.extapi.psi.StubBasedPsiElementBase;
import io.epigraph.lang.parser.psi.stubs.SchemaSupplementDefStub;
import io.epigraph.lang.parser.psi.*;
import com.intellij.navigation.ItemPresentation;

public class SchemaSupplementDefImpl extends StubBasedPsiElementBase<SchemaSupplementDefStub> implements SchemaSupplementDef {

  public SchemaSupplementDefImpl(SchemaSupplementDefStub stub, com.intellij.psi.stubs.IStubElementType nodeType) {
    super(stub, nodeType);
  }

  public SchemaSupplementDefImpl(ASTNode node) {
    super(node);
  }

  public SchemaSupplementDefImpl(SchemaSupplementDefStub stub, com.intellij.psi.tree.IElementType nodeType, ASTNode node) {
    super(stub, nodeType, node);
  }

  public void accept(@NotNull SchemaVisitor visitor) {
    visitor.visitSupplementDef(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SchemaVisitor) accept((SchemaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<SchemaFqnTypeRef> getFqnTypeRefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SchemaFqnTypeRef.class);
  }

  @Override
  @NotNull
  public PsiElement getSupplement() {
    return notNullChild(findChildByType(E_SUPPLEMENT));
  }

  @Override
  @Nullable
  public PsiElement getWith() {
    return findChildByType(E_WITH);
  }

  @Nullable
  public SchemaFqnTypeRef sourceRef() {
    return EpigraphPsiImplUtil.sourceRef(this);
  }

  @NotNull
  public List<SchemaFqnTypeRef> supplementedRefs() {
    return EpigraphPsiImplUtil.supplementedRefs(this);
  }

  @Nullable
  public SchemaTypeDef source() {
    return EpigraphPsiImplUtil.source(this);
  }

  @NotNull
  public List<SchemaTypeDef> supplemented() {
    return EpigraphPsiImplUtil.supplemented(this);
  }

  @NotNull
  public ItemPresentation getPresentation() {
    return EpigraphPsiImplUtil.getPresentation(this);
  }

  @NotNull
  public String toString() {
    return EpigraphPsiImplUtil.toString(this);
  }

}
