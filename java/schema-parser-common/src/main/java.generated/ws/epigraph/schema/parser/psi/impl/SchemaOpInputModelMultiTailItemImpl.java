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

public class SchemaOpInputModelMultiTailItemImpl extends ASTWrapperPsiElement implements SchemaOpInputModelMultiTailItem {

  public SchemaOpInputModelMultiTailItemImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SchemaVisitor visitor) {
    visitor.visitOpInputModelMultiTailItem(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SchemaVisitor) accept((SchemaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public SchemaOpInputModelProjection getOpInputModelProjection() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, SchemaOpInputModelProjection.class));
  }

  @Override
  @NotNull
  public SchemaTypeRef getTypeRef() {
    return notNullChild(PsiTreeUtil.getChildOfType(this, SchemaTypeRef.class));
  }

}
