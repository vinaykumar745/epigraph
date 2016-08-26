package io.epigraph.lang.parser.psi.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import io.epigraph.lang.lexer.EpigraphElementTypes;
import io.epigraph.lang.parser.psi.EpigraphRecordTypeDef;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author <a href="mailto:konstantin@sumologic.com">Konstantin Sobolev</a>
 */
public class EpigraphRecordTypeDefStubImpl extends EpigraphTypeDefStubBaseImpl<EpigraphRecordTypeDef> implements EpigraphRecordTypeDefStub {
  private final List<SerializedFqnTypeRef> supplementedTypeRefs;

  EpigraphRecordTypeDefStubImpl(StubElement parent,
                                String name,
                                String namespace,
                                @Nullable final List<SerializedFqnTypeRef> extendsTypeRefs,
                                @Nullable final List<SerializedFqnTypeRef> supplementedTypeRefs) {
    super(parent, (IStubElementType) EpigraphElementTypes.E_RECORD_TYPE_DEF, name, namespace, extendsTypeRefs);
    this.supplementedTypeRefs = supplementedTypeRefs;
  }

  @Nullable
  @Override
  public List<SerializedFqnTypeRef> getSupplementedTypeRefs() {
    return supplementedTypeRefs;
  }
}
