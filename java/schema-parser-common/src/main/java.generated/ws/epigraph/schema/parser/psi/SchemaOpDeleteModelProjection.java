// This is a generated file. Not intended for manual editing.
package ws.epigraph.schema.parser.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SchemaOpDeleteModelProjection extends PsiElement {

  @Nullable
  SchemaOpDeleteListModelProjection getOpDeleteListModelProjection();

  @Nullable
  SchemaOpDeleteMapModelProjection getOpDeleteMapModelProjection();

  @Nullable
  SchemaOpDeleteModelPolymorphicTail getOpDeleteModelPolymorphicTail();

  @Nullable
  SchemaOpDeleteRecordModelProjection getOpDeleteRecordModelProjection();

}
