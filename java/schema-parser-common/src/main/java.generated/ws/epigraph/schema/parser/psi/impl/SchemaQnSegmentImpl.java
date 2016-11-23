// This is a generated file. Not intended for manual editing.
package ws.epigraph.schema.parser.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ws.epigraph.schema.lexer.SchemaElementTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import ws.epigraph.schema.parser.psi.*;
import com.intellij.psi.PsiReference;
import ws.epigraph.lang.Qn;

public class SchemaQnSegmentImpl extends ASTWrapperPsiElement implements SchemaQnSegment {

  public SchemaQnSegmentImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SchemaVisitor visitor) {
    visitor.visitQnSegment(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SchemaVisitor) accept((SchemaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public SchemaQid getQid() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, SchemaQid.class));
  }

  @Nullable
  public String getName() {
    return SchemaPsiImplUtil.getName(this);
  }

  @NotNull
  public PsiElement setName(String name) {
    return SchemaPsiImplUtil.setName(this, name);
  }

  @NotNull
  public PsiElement getNameIdentifier() {
    return SchemaPsiImplUtil.getNameIdentifier(this);
  }

  @Nullable
  public SchemaQn getSchemaFqn() {
    return SchemaPsiImplUtil.getSchemaFqn(this);
  }

  @Nullable
  public SchemaQnTypeRef getSchemaFqnTypeRef() {
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
  public Qn getQn() {
    return SchemaPsiImplUtil.getQn(this);
  }

}