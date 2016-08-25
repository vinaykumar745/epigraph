package com.sumologic.epigraph.schema.parser.psi.stubs;

import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.stubs.StubBase;
import com.intellij.psi.stubs.StubElement;
import io.epigraph.lang.lexer.EpigraphElementTypes;
import com.sumologic.epigraph.schema.parser.psi.SchemaTypeDefWrapper;

/**
 * @author <a href="mailto:konstantin@sumologic.com">Konstantin Sobolev</a>
 */
class SchemaTypeDefWrapperStubImpl extends StubBase<SchemaTypeDefWrapper> implements SchemaTypeDefWrapperStub {
  SchemaTypeDefWrapperStubImpl(StubElement parent) {
    super(parent, (IStubElementType) EpigraphElementTypes.E_TYPE_DEF_WRAPPER);
  }
}
