package com.sumologic.epigraph.ideaplugin.schema.highlighting;

import com.intellij.codeInsight.daemon.impl.HighlightInfoType;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPolyVariantReference;
import com.intellij.psi.PsiReference;
import com.intellij.psi.ResolveResult;
import com.intellij.util.containers.MultiMap;
import com.sumologic.epigraph.ideaplugin.schema.actions.ImportTypeIntentionFix;
import com.sumologic.epigraph.ideaplugin.schema.brains.ImportsManager;
import com.sumologic.epigraph.ideaplugin.schema.brains.hierarchy.HierarchyCache;
import com.sumologic.epigraph.ideaplugin.schema.brains.hierarchy.TypeMembers;
import com.sumologic.epigraph.ideaplugin.schema.features.imports.OptimizeImportsQuickFix;
import com.sumologic.epigraph.ideaplugin.schema.index.SchemaIndexUtil;
import com.sumologic.epigraph.schema.parser.Fqn;
import com.sumologic.epigraph.schema.parser.NamingConventions;
import com.sumologic.epigraph.schema.parser.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.sumologic.epigraph.schema.parser.lexer.SchemaElementTypes.S_FQN_TYPE_REF;

/**
 * @author <a href="mailto:konstantin@sumologic.com">Konstantin Sobolev</a>
 */
public class SchemaAnnotator implements Annotator {
  // TODO change most of annotations to inspections? See http://www.jetbrains.org/intellij/sdk/docs/reference_guide/custom_language_support/code_inspections_and_intentions.html
  // TODO unnecessary backticks (with quickfix?)

  @Override
  public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
    element.accept(new SchemaVisitor() {
      @Override
      public void visitImports(@NotNull SchemaImports schemaImports) {
        List<SchemaImportStatement> importStatements = schemaImports.getImportStatementList();
        validateImports((SchemaFile) schemaImports.getContainingFile(), importStatements, holder);
      }

      @Override
      public void visitFieldDecl(@NotNull SchemaFieldDecl fieldDecl) {
        PsiElement id = fieldDecl.getQid();
        setHighlighting(id, holder, SchemaSyntaxHighlighter.FIELD);

        String namingError = NamingConventions.validateFieldName(id.getText());
        if (namingError != null) {
          holder.createErrorAnnotation(id, namingError);
        }

        PsiElement override = fieldDecl.getOverride();
        if (override != null && TypeMembers.getOverridenFields(fieldDecl).isEmpty()) {
          holder.createErrorAnnotation(override, "Field overrides nothing");
        }
      }

      @Override
      public void visitVarTagDecl(@NotNull SchemaVarTagDecl memberDecl) {
        PsiElement id = memberDecl.getQid();
        setHighlighting(id, holder, SchemaSyntaxHighlighter.VAR_MEMBER);

        String namingError = NamingConventions.validateVarTypeMemberName(id.getText());
        if (namingError != null)
          holder.createErrorAnnotation(id, namingError);

        PsiElement override = memberDecl.getOverride();
        if (override != null && TypeMembers.getOverridenTags(memberDecl).isEmpty()) {
          holder.createErrorAnnotation(override, "Tag overrides nothing");
        }
      }

      @Override
      public void visitDefaultOverride(@NotNull SchemaDefaultOverride defaultOverride) {
        SchemaVarTagRef varTagRef = defaultOverride.getVarTagRef();
        if (varTagRef != null) {
          PsiElement id = varTagRef.getQid();
          setHighlighting(id, holder, SchemaSyntaxHighlighter.VAR_MEMBER);
        }
      }

      @Override
      public void visitTypeDef(@NotNull SchemaTypeDef typeDef) {
        PsiElement id = typeDef.getNameIdentifier();
        if (id != null) {
          setHighlighting(id, holder, SchemaSyntaxHighlighter.DECL_TYPE_NAME);

          String typeName = typeDef.getName();
          if (typeName != null) {
            Fqn shortTypeNameFqn = new Fqn(typeName);
            Fqn fullTypeNameFqn = typeDef.getFqn();

            String namingError = NamingConventions.validateTypeName(typeName);
            if (namingError != null) {
              holder.createErrorAnnotation(id, namingError);
            }

            // check if it hides an import
            List<Fqn> importsBySuffix = ImportsManager.findImportsBySuffix((SchemaFile) typeDef.getContainingFile(), shortTypeNameFqn);
            if (!importsBySuffix.isEmpty()) {
              Fqn importFqn = importsBySuffix.iterator().next();
              boolean isImplicit = ImportsManager.DEFAULT_IMPORTS_LIST.contains(importFqn);
              holder.createWarningAnnotation(id, "Type \"" + typeName + "\" is shadowed by " + (isImplicit ? "implicit " : "") + "import " + importFqn);
            }

            // check if's already defined
            List<SchemaTypeDef> typeDefs = SchemaIndexUtil.findTypeDefs(element.getProject(), new Fqn[]{fullTypeNameFqn});
            if (typeDefs.size() > 1) {
              holder.createErrorAnnotation(id, "Type \"" + fullTypeNameFqn + "\" is already defined");
            }

            // check for circular inheritance
            HierarchyCache hierarchyCache = HierarchyCache.getHierarchyCache(element.getProject());
            List<SchemaTypeDef> typeParents = hierarchyCache.getTypeParents(typeDef);
            if (typeParents.contains(typeDef)) {
              holder.createErrorAnnotation(id, "Circular inheritance");
            }
          }
        }
      }

//      @Override
//      public void visitVarTypeDef(@NotNull SchemaVarTypeDef varTypeDef) {
//        visitTypeDef(varTypeDef);
//      }
//
//      @Override
//      public void visitRecordTypeDef(@NotNull SchemaRecordTypeDef recordTypeDef) {
//        visitTypeDef(recordTypeDef);
//        // TODO check that non-abstract record type def has no (+inherited) abstract fields
//      }

      @Override
      public void visitExtendsDecl(@NotNull SchemaExtendsDecl schemaExtendsDecl) {
        SchemaTypeDef typeDef = (SchemaTypeDef) schemaExtendsDecl.getParent();
        if (typeDef == null) return;

        List<SchemaFqnTypeRef> typeRefList = schemaExtendsDecl.getFqnTypeRefList();
        for (SchemaFqnTypeRef fqnTypeRef : typeRefList) {
          boolean wrongKind = false;

          SchemaTypeDef parent = fqnTypeRef.resolve();
          if (parent != null) {
            if (typeDef.getKind() != parent.getKind()) wrongKind = true;
          }

          if (wrongKind) holder.createErrorAnnotation(fqnTypeRef, "Wrong parent type kind");
        }
      }

      @Override
      public void visitSupplementsDecl(@NotNull SchemaSupplementsDecl o) {
        // TODO similar to the above
      }

      @Override
      public void visitSupplementDef(@NotNull SchemaSupplementDef supplementDef) {
        // TODO check types compatibility (and circular inheritance?)
      }

      @Override
      public void visitCustomParam(@NotNull SchemaCustomParam customParam) {
        setHighlighting(customParam.getQid(), holder, SchemaSyntaxHighlighter.PARAM_NAME);
      }

      @Override
      public void visitFqnTypeRef(@NotNull SchemaFqnTypeRef typeRef) {
        SchemaFqn fqn = typeRef.getFqn();
        highlightFqn(fqn, holder, new ImportTypeIntentionFix(typeRef));
      }

      @Override
      public void visitFqn(@NotNull SchemaFqn fqn) {
        PsiElement parent = fqn.getParent();
        // TODO don't check ref in the namespace decl?
        if (parent.getNode().getElementType() != S_FQN_TYPE_REF) {
          highlightFqn(fqn, holder, null);
        }
      }

      @Override
      public void visitVarTagRef(@NotNull SchemaVarTagRef tagRef) {
        PsiReference reference = tagRef.getReference();
        if (reference == null || reference.resolve() == null) {
          holder.createErrorAnnotation(tagRef.getNode(), "Unresolved reference");
        }
      }
    });
  }

  private void validateExtendsList(@NotNull SchemaTypeDef typeDef, @NotNull SchemaAnonList anonList) {
    // TODO check types compatibility, lists are covariant?
  }

  private void validateExtendsMap(@NotNull SchemaTypeDef typeDef, @NotNull SchemaAnonMap anonMap) {
    // TODO check types compatibility, maps are covariant?
  }

//  private void validateExtends(@NotNull SchemaTypeDefElement typeDef, @NotNull SchemaTypeDef parent) {
//    // TODO
//  }

  private void highlightFqn(@Nullable SchemaFqn schemaFqn, @NotNull AnnotationHolder holder,
                            @Nullable IntentionAction unresolvedTypeRefFix) {
    if (schemaFqn != null) {
//      setHighlighting(schemaFqn.getLastChild(), holder, SchemaSyntaxHighlighter.TYPE_REF);

      PsiPolyVariantReference reference = (PsiPolyVariantReference) schemaFqn.getLastChild().getReference();
      assert reference != null;

      if (reference.resolve() == null) {
        ResolveResult[] resolveResults = reference.multiResolve(false);
        int numTypeRefs = 0;
        for (ResolveResult resolveResult : resolveResults) {
          if (resolveResult.getElement() instanceof SchemaTypeDef)
            numTypeRefs++;
        }

        if (resolveResults.length == 0) {
          Annotation annotation = holder.createErrorAnnotation(schemaFqn.getNode(), "Unresolved reference");
          if (unresolvedTypeRefFix != null)
            annotation.registerFix(unresolvedTypeRefFix);
        } else if (numTypeRefs > 1) {
          holder.createErrorAnnotation(schemaFqn.getNode(), "Ambiguous type reference");
        } // else we have import prefix matching varTypeple namespaces, OK
      }
    }
  }

  // TODO use inspections for this. See http://www.jetbrains.org/intellij/sdk/docs/reference_guide/custom_language_support/code_inspections_and_intentions.html
  private static void validateImports(@NotNull SchemaFile file,
                                      @NotNull List<SchemaImportStatement> imports,
                                      @NotNull AnnotationHolder holder) {

    // duplicating imports
    MultiMap<Fqn, SchemaImportStatement> importsByFqn = ImportsManager.getImportsByFqn(imports);

    for (Map.Entry<Fqn, Collection<SchemaImportStatement>> entry : importsByFqn.entrySet()) {
      Collection<SchemaImportStatement> importStatements = entry.getValue();
      for (SchemaImportStatement importStatement : importStatements) {
        if (importStatements.size() > 1) {
          Annotation annotation = holder.createWarningAnnotation(importStatement, "Duplicate import");
          registerOptimizeImportsFix(annotation);
        }

        // unnecessary import of epigraph.*
        Fqn fqn = entry.getKey();
        if (ImportsManager.DEFAULT_IMPORTS_LIST.contains(fqn)) {
          Annotation annotation = holder.createWarningAnnotation(importStatement, "Unnecessary import");
          setHighlighting(importStatement, holder, HighlightInfoType.UNUSED_SYMBOL.getAttributesKey());
          registerOptimizeImportsFix(annotation);
        }
      }
    }

    // unused imports
    Set<SchemaImportStatement> unusedImports = ImportsManager.findUnusedImports(file);
    for (SchemaImportStatement unusedImport : unusedImports) {
      Annotation annotation = setHighlighting(unusedImport, holder, HighlightInfoType.UNUSED_SYMBOL.getAttributesKey());
      registerOptimizeImportsFix(annotation);
    }

    // conflicting imports
    MultiMap<String, SchemaImportStatement> importsByLastSegment = new MultiMap<>(importsByFqn.size(), 0.75f);
    for (Map.Entry<Fqn, Collection<SchemaImportStatement>> entry : importsByFqn.entrySet()) {
      String lastSegment = entry.getKey().last();
      assert lastSegment != null;
      importsByLastSegment.putValue(lastSegment, entry.getValue().iterator().next()); // take only first one so we don't report duplicate imports as conflicts
    }

    for (Map.Entry<String, Collection<SchemaImportStatement>> entry : importsByLastSegment.entrySet()) {
      Collection<SchemaImportStatement> conflictingImports = entry.getValue();
      if (conflictingImports.size() > 1) {
        for (SchemaImportStatement conflictingImport : conflictingImports) {
          Collection<String> conflictingImportsStrings = conflictingImports.stream()
              .filter(i -> i != conflictingImport)
              .map(i -> "\"" + i.getText() + "\"")
              .collect(Collectors.toList());

          holder.createErrorAnnotation(conflictingImport,
              String.format("\"%s\" conflicts with %s",
                  conflictingImport.getText(),
                  String.join(", ", conflictingImportsStrings)));
        }
      }
    }
  }

  private static void registerOptimizeImportsFix(@NotNull Annotation annotation) {
    annotation.registerFix(new OptimizeImportsQuickFix());
  }

  private static Annotation setHighlighting(@NotNull PsiElement element, @NotNull AnnotationHolder holder,
                                            @NotNull TextAttributesKey key) {
    Annotation annotation = holder.createInfoAnnotation(element, null);
    annotation.setEnforcedTextAttributes(EditorColorsManager.getInstance().getGlobalScheme().getAttributes(key));
    return annotation;
  }
}
