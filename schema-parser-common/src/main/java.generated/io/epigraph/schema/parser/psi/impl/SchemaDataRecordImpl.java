// This is a generated file. Not intended for manual editing.
package io.epigraph.schema.parser.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.epigraph.schema.lexer.SchemaElementTypes.*;
import io.epigraph.schema.parser.psi.*;

public class SchemaDataRecordImpl extends SchemaDataValueImpl implements SchemaDataRecord {

  public SchemaDataRecordImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SchemaVisitor visitor) {
    visitor.visitDataRecord(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SchemaVisitor) accept((SchemaVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<SchemaDataRecordEntry> getDataRecordEntryList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SchemaDataRecordEntry.class);
  }

  @Override
  @Nullable
  public SchemaFqnTypeRef getFqnTypeRef() {
    return PsiTreeUtil.getChildOfType(this, SchemaFqnTypeRef.class);
  }

  @Override
  @NotNull
  public PsiElement getCurlyLeft() {
    return notNullChild(findChildByType(S_CURLY_LEFT));
  }

  @Override
  @Nullable
  public PsiElement getCurlyRight() {
    return findChildByType(S_CURLY_RIGHT);
  }

}
