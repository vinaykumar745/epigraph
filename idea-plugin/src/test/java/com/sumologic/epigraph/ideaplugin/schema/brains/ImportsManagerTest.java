package com.sumologic.epigraph.ideaplugin.schema.brains;

import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import com.sumologic.epigraph.schema.parser.Fqn;
import com.sumologic.epigraph.schema.parser.psi.SchemaFile;
import com.sumologic.epigraph.schema.parser.psi.SchemaImportStatement;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:konstantin@sumologic.com">Konstantin Sobolev</a>
 */
public class ImportsManagerTest extends LightCodeInsightFixtureTestCase {
  @Override
  protected String getTestDataPath() {
    return "src/test/resources/testData/brains";
  }

  public void testFindUnusedImports() {
    myFixture.configureByFiles("UnusedImports.epi_schema", "foo.epi_schema", "foobar.epi_schema");
    Set<SchemaImportStatement> unusedImports = ImportsManager.findUnusedImports((SchemaFile) myFixture.getFile());

    Set<String> strings = unusedImports.stream().map(SchemaImportStatement::getText).collect(Collectors.toSet());
    assertEquals(new HashSet<>(Arrays.asList("import foo", "import foo.Y", "import foo.Z")), strings);
  }

  public void testOptimizeImports() {
    myFixture.configureByFiles("UnusedImports.epi_schema", "foo.epi_schema", "foobar.epi_schema");
    List<Fqn> optimizedImports = ImportsManager.getOptimizedImports((SchemaFile) myFixture.getFile());

    assertEquals(Arrays.asList(
        new Fqn[]{
            Fqn.fromDotSeparated("foo.X"),
            Fqn.fromDotSeparated("foo.bar")
        }
    ), optimizedImports);

  }
}