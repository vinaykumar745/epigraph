package com.sumologic.epigraph.ideaplugin.schema.features.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiRecursiveElementVisitor;
import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import com.sumologic.epigraph.schema.parser.Fqn;
import com.sumologic.epigraph.schema.parser.SchemaLanguage;
import com.sumologic.epigraph.schema.parser.psi.*;
import com.sumologic.epigraph.schema.parser.SchemaParserDefinition;
import com.sumologic.epigraph.ideaplugin.schema.psi.*;
import com.sumologic.epigraph.schema.parser.psi.impl.SchemaPsiImplUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.sumologic.epigraph.schema.parser.lexer.SchemaElementTypes.*;

/**
 * @author <a href="mailto:konstantin@sumologic.com">Konstantin Sobolev</a>
 */
public class SchemaCompletionContributor extends CompletionContributor {
  // TODO default` doesn't show up in auto-complete/suggestions after `vartype Foo `

  private static final String[] TOP_LEVEL_COMPLETIONS = new String[]{
      "polymorphic ", "abstract ",
      "record ", "map", "list", "vartype ", "enum ",
      "integer ", "long ", "double ", "boolean ", "string ",
      "supplement "
  };

  // items from TOP_LEVEL_COMPLETIONS that can't follow 'polymorphic' keyword
  private static final Set<String> TOP_LEVEL_CANT_FOLLOW_POLY = new HashSet<>(Arrays.asList(
      "polymorphic ", "abstract ", "vartype ", "enum ", "supplement "
  ));

  // items from TOP_LEVEL_COMPLETIONS that can't follow 'abstract' keyword
  private static final Set<String> TOP_LEVEL_CANT_FOLLOW_ABSTRACT = new HashSet<>(Arrays.asList(
      "abstract ", "vartype ", "enum ", "supplement "
  ));

  // which types can have 'extends' clause
  private static final Set<IElementType> DEFS_SUPPORTING_EXTENDS = new HashSet<>(Arrays.asList(
      S_VAR_TYPE_DEF, S_RECORD_TYPE_DEF, S_LIST_TYPE_DEF, S_MAP_TYPE_DEF, S_PRIMITIVE_TYPE_DEF
  ));

  // which types can have 'meta' clause
  private static final Set<IElementType> DEFS_SUPPORTING_META = new HashSet<>(Arrays.asList(
      S_VAR_TYPE_DEF, S_RECORD_TYPE_DEF, S_LIST_TYPE_DEF, S_MAP_TYPE_DEF, S_ENUM_TYPE_DEF, S_PRIMITIVE_TYPE_DEF
  ));

  // which types can have 'supplements' clause
  private static final Set<IElementType> DEFS_SUPPORTING_SUPPLEMENTS = new HashSet<>(Arrays.asList(
      S_VAR_TYPE_DEF, S_RECORD_TYPE_DEF
  ));

  public SchemaCompletionContributor() {
    extend(
        CompletionType.BASIC,
        PlatformPatterns.psiElement().withLanguage(SchemaLanguage.INSTANCE),
        new CompletionProvider<CompletionParameters>() {
          @Override
          protected void addCompletions(@NotNull CompletionParameters parameters, ProcessingContext context, @NotNull CompletionResultSet result) {
            PsiElement position = parameters.getPosition();
            completeTopLevelKeywords(position, result);
            completeExtendsKeyword(position, result);
            completeMetaKeyword(position, result);
            completeSupplementsKeyword(position, result);
            completeWith(position, result);
            completeNewTypeName(position, result);
          }
        }
    );
  }

  private void completeTopLevelKeywords(@NotNull PsiElement position, @NotNull CompletionResultSet result) {
    // Only complete if this token is first one on the current line?
    // otherwise we encourage something like
    // long Foo string Bar
    // (on the same line)

    PsiElement parent = position.getParent();
    if (parent != null) {
      if (parent instanceof SchemaTypeDef || parent instanceof SchemaSupplementDef) return;

      PsiElement grandParent = parent.getParent();

      boolean completeTypeDef = false;
      boolean completeImport = false;
      boolean completeNamespace = false;
      boolean afterPolymorphic = SchemaPsiUtil.hasPrevSibling(parent, S_POLYMORPHIC);
      boolean afterAbstract = SchemaPsiUtil.hasPrevSibling(parent, S_ABSTRACT);

      if (grandParent != null) {
        IElementType grandParentElementType = grandParent.getNode().getElementType();

        if (grandParentElementType == S_DEFS) {
          PsiElement nextParentSibling = SchemaPsiUtil.nextNonWhitespaceSibling(parent);
          // don't initiate new type completion if we're followed by anything but a new def, e.g. when we're followed by a {..} dummy block
          completeTypeDef = nextParentSibling == null
              || nextParentSibling instanceof SchemaTypeDefWrapper
              || nextParentSibling instanceof SchemaSupplementDef;

        } else if (grandParentElementType == S_IMPORT_STATEMENT) {
          if (!SchemaPsiUtil.hasNextSibling(grandParent, S_IMPORT_STATEMENT)) {
            completeTypeDef = true;
          }
          completeImport = true;
        } else if (grandParentElementType == S_NAMESPACE_DECL) {
          // check if we're between 'namespace' and 'import'
          SchemaImports schemaImports = PsiTreeUtil.getNextSiblingOfType(grandParent, SchemaImports.class);
          if (schemaImports == null || schemaImports.getFirstChild() == null) {
            // we're after 'namespace' not followed by 'import'
            completeTypeDef = true;
          }
          completeImport = true;
        } else if (grandParent instanceof SchemaFile /* && !SchemaPsiUtil.hasNextSibling(grandParent, S_NAMESPACE_DECL) */) {
          completeNamespace = true;
        }
      }

      if (completeTypeDef) {
        for (String topLevelKeyword : TOP_LEVEL_COMPLETIONS) {
          if (afterPolymorphic && TOP_LEVEL_CANT_FOLLOW_POLY.contains(topLevelKeyword)) continue;
          if (afterAbstract && TOP_LEVEL_CANT_FOLLOW_ABSTRACT.contains(topLevelKeyword)) continue;
          result.addElement(LookupElementBuilder.create(topLevelKeyword));
        }
      }

      if (completeImport) {
        result.addElement(LookupElementBuilder.create("import "));
      }

      if (completeNamespace) {
        result.addElement(LookupElementBuilder.create("namespace "));
      }
    }
  }

  private void completeExtendsKeyword(@NotNull PsiElement position, @NotNull CompletionResultSet result) {
    SchemaPsiUtil.ElementQualifier extendsPresent = element -> {
      if (element instanceof SchemaTypeDef) {
        SchemaTypeDef schemaTypeDef = (SchemaTypeDef) element;
        return schemaTypeDef.getExtendsDecl() != null;
      }
      return false;
    };

    completeInnerTypedefKeyword(
        position,
        new SchemaPsiUtil.ElementTypeQualifier(DEFS_SUPPORTING_EXTENDS),
        extendsPresent,
        "extends ",
        result);
  }

  private void completeMetaKeyword(@NotNull PsiElement position, @NotNull CompletionResultSet result) {
    SchemaPsiUtil.ElementQualifier metaOrExtendsPresent = element -> {
      if (element instanceof SchemaTypeDef) {
        SchemaTypeDef schemaTypeDef = (SchemaTypeDef) element;
        return schemaTypeDef.getMetaDecl() != null || schemaTypeDef.getExtendsDecl() != null;
      }
      return false;
    };

    completeInnerTypedefKeyword(
        position,
        new SchemaPsiUtil.ElementTypeQualifier(DEFS_SUPPORTING_META),
        metaOrExtendsPresent,
        "meta ",
        result);
  }

  private void completeSupplementsKeyword(@NotNull PsiElement position, @NotNull CompletionResultSet result) {
    SchemaPsiUtil.ElementQualifier metaOrExtendsOrSupplementsPresent = element -> {
      if (element instanceof SchemaTypeDef) {
        SchemaTypeDef schemaTypeDef = (SchemaTypeDef) element;
        if (schemaTypeDef.getMetaDecl() != null || schemaTypeDef.getExtendsDecl() != null) return true;
        if (schemaTypeDef instanceof SchemaRecordTypeDef) {
          return schemaTypeDef.getSupplementsDecl() != null;
        }
        if (schemaTypeDef instanceof SchemaVarTypeDef) {
          return schemaTypeDef.getSupplementsDecl() != null;
        }
      }
      return false;
    };

    completeInnerTypedefKeyword(
        position,
        new SchemaPsiUtil.ElementTypeQualifier(DEFS_SUPPORTING_SUPPLEMENTS),
        metaOrExtendsOrSupplementsPresent,
        "supplements ",
        result);
  }

  private void completeInnerTypedefKeyword(@NotNull PsiElement position,
                                           @NotNull SchemaPsiUtil.ElementQualifier canHaveQualifier,
                                           @NotNull SchemaPsiUtil.ElementQualifier negativeBeforeQualifier,
                                           @NotNull String completion,
                                           @NotNull CompletionResultSet result) {

    PsiElement parent = position.getParent();
    if (parent == null) return;

    // this should be the actual type def wrapper
    PsiElement prevParentSibling = SchemaPsiUtil.prevNonWhitespaceSibling(parent);

    if (prevParentSibling instanceof SchemaTypeDefWrapper) {
      SchemaTypeDefWrapper typeDefWrapper = (SchemaTypeDefWrapper) prevParentSibling;
      SchemaTypeDef typeDef = typeDefWrapper.getElement();

      if (!canHaveQualifier.qualifies(typeDef)) return;
      if (negativeBeforeQualifier.qualifies(typeDef)) return;

      result.addElement(LookupElementBuilder.create(completion));
    }
  }

  private void completeWith(@NotNull PsiElement position, @NotNull final CompletionResultSet result) {
    PsiElement qid = position.getParent();
    if (!(qid instanceof SchemaQid)) return;

    if (PsiTreeUtil.getParentOfType(qid, SchemaSupplementDef.class) != null) {
      SchemaFqnTypeRef fqnTypeRef = PsiTreeUtil.getParentOfType(qid, SchemaFqnTypeRef.class);
      if (fqnTypeRef != null) {
        PsiElement prevSibling = SchemaPsiUtil.prevNonWhitespaceSibling(fqnTypeRef);
        if (prevSibling != null && prevSibling.getNode().getElementType() != S_COMMA &&
            !SchemaPsiUtil.hasPrevSibling(fqnTypeRef, S_WITH)) {
          result.addElement(LookupElementBuilder.create("with "));
        }
      }
    }

  }

  private void completeNewTypeName(@NotNull PsiElement position, @NotNull final CompletionResultSet result) {
    // complete things like "record <caret>"
    // use all unresolved type references as candidates. In the future: be more intelligent and filter them by kind,
    // see commented out CompletionTest::testUndefinedTypeNameCompletion2

    // first check we're in the correct position
    PsiElement qid = position.getParent();
    if (!(qid instanceof SchemaQid)) return;
    PsiElement parent = qid.getParent();
    if (!(parent instanceof SchemaTypeDef)) return;

    PsiElement prevSibling = SchemaPsiUtil.prevNonWhitespaceSibling(qid);
    if (prevSibling == null) return;

    IElementType prevSiblingElementType = prevSibling.getNode().getElementType();
    if (!SchemaParserDefinition.TYPE_KINDS.contains(prevSiblingElementType)) return;

    PsiElement defs = PsiTreeUtil.getParentOfType(parent, SchemaDefs.class);
    assert defs != null;

    // now collect all candidates

    defs.accept(new PsiRecursiveElementVisitor() {
      @Override
      public void visitElement(PsiElement element) {
        super.visitElement(element);

        if (element instanceof SchemaFqnTypeRef) {
          SchemaFqnTypeRef typeRef = (SchemaFqnTypeRef) element;

          Fqn fqn = typeRef.getFqn().getFqn();
          // only bother if it's single-segment
          if (fqn.size() != 1) return;

          PsiReference reference = SchemaPsiImplUtil.getReference(typeRef);
          if (reference == null) return;

          if (reference.resolve() != null) return;

          // now here is the place to look around and be more intelligent

          result.addElement(LookupElementBuilder.create(fqn.toString()));
        }

      }
    });

  }
}
