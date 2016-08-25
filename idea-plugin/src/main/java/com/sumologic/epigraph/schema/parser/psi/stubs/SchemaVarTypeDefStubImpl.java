package com.sumologic.epigraph.schema.parser.psi.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubElement;
import io.epigraph.lang.lexer.EpigraphElementTypes;
import com.sumologic.epigraph.schema.parser.psi.SchemaVarTypeDef;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author <a href="mailto:konstantin@sumologic.com">Konstantin Sobolev</a>
 */
public class SchemaVarTypeDefStubImpl extends SchemaTypeDefStubBaseImpl<SchemaVarTypeDef> implements SchemaVarTypeDefStub {
  private final List<SerializedFqnTypeRef> supplementedTypeRefs;

  SchemaVarTypeDefStubImpl(StubElement parent,
                           String name,
                           String namespace,
                           @Nullable final List<SerializedFqnTypeRef> extendsTypeRefs,
                           @Nullable final List<SerializedFqnTypeRef> supplementedTypeRefs) {
    super(parent, (IStubElementType) EpigraphElementTypes.E_VAR_TYPE_DEF, name, namespace, extendsTypeRefs);
    this.supplementedTypeRefs = supplementedTypeRefs;
  }

  @Nullable
  @Override
  public List<SerializedFqnTypeRef> getSupplementedTypeRefs() {
    return supplementedTypeRefs;
  }
}
