// This is a generated file. Not intended for manual editing.
package ws.epigraph.edl.parser.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface EdlOpDeleteMultiTagProjectionItem extends PsiElement {

  @Nullable
  EdlOpDeleteModelProjection getOpDeleteModelProjection();

  @NotNull
  List<EdlOpDeleteModelProperty> getOpDeleteModelPropertyList();

  @NotNull
  EdlTagName getTagName();

  @Nullable
  PsiElement getCurlyLeft();

  @Nullable
  PsiElement getCurlyRight();

}