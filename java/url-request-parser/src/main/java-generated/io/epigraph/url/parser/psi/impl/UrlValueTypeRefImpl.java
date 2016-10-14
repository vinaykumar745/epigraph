// This is a generated file. Not intended for manual editing.
package io.epigraph.url.parser.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.epigraph.url.lexer.UrlElementTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import io.epigraph.url.parser.psi.*;

public class UrlValueTypeRefImpl extends ASTWrapperPsiElement implements UrlValueTypeRef {

  public UrlValueTypeRefImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull UrlVisitor visitor) {
    visitor.visitValueTypeRef(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof UrlVisitor) accept((UrlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public UrlDefaultOverride getDefaultOverride() {
    return findChildByClass(UrlDefaultOverride.class);
  }

  @Override
  @NotNull
  public UrlTypeRef getTypeRef() {
    return findNotNullChildByClass(UrlTypeRef.class);
  }

}
