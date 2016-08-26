package io.epigraph.lang.parser.psi.stubs;

import com.intellij.psi.stubs.StubElement;
import io.epigraph.lang.parser.psi.EpigraphListTypeDef;
import io.epigraph.lang.parser.psi.impl.EpigraphListTypeDefImpl;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="mailto:konstantin@sumologic.com">Konstantin Sobolev</a>
 */
public class SchemaListTypeDefStubElementType extends SchemaTypeDefStubElementTypeBase<SchemaListTypeDefStub, EpigraphListTypeDef> {
  public SchemaListTypeDefStubElementType(@NotNull @NonNls String debugName) {
    super(debugName, "listtypedef");
  }

  @Override
  public EpigraphListTypeDef createPsi(@NotNull SchemaListTypeDefStub stub) {
    return new EpigraphListTypeDefImpl(stub, this);
  }

  @Override
  public SchemaListTypeDefStub createStub(@NotNull EpigraphListTypeDef typeDef, StubElement parentStub) {
    return new SchemaListTypeDefStubImpl(parentStub);
  }

}
