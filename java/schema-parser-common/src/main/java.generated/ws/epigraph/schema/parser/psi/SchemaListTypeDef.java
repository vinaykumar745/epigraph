// This is a generated file. Not intended for manual editing.
package ws.epigraph.schema.parser.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.StubBasedPsiElement;
import ws.epigraph.schema.parser.psi.stubs.SchemaListTypeDefStub;

public interface SchemaListTypeDef extends SchemaTypeDef, StubBasedPsiElement<SchemaListTypeDefStub> {

  @NotNull
  SchemaAnonList getAnonList();

  @Nullable
  SchemaExtendsDecl getExtendsDecl();

  @Nullable
  SchemaListTypeBody getListTypeBody();

  @Nullable
  SchemaMetaDecl getMetaDecl();

  @Nullable
  SchemaQid getQid();

  @Nullable
  SchemaSupplementsDecl getSupplementsDecl();

  @Nullable
  PsiElement getAbstract();

}
