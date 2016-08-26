// This is a generated file. Not intended for manual editing.
package io.epigraph.lang.parser.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiNameIdentifierOwner;

public class SchemaVisitor extends PsiElementVisitor {

  public void visitAnonList(@NotNull SchemaAnonList o) {
    visitTypeRef(o);
  }

  public void visitAnonMap(@NotNull SchemaAnonMap o) {
    visitTypeRef(o);
  }

  public void visitCustomParam(@NotNull SchemaCustomParam o) {
    visitPsiNamedElement(o);
  }

  public void visitDataEnum(@NotNull SchemaDataEnum o) {
    visitDataValue(o);
  }

  public void visitDataList(@NotNull SchemaDataList o) {
    visitDataValue(o);
  }

  public void visitDataMap(@NotNull SchemaDataMap o) {
    visitDataValue(o);
  }

  public void visitDataMapEntry(@NotNull SchemaDataMapEntry o) {
    visitPsiElement(o);
  }

  public void visitDataPrimitive(@NotNull SchemaDataPrimitive o) {
    visitPsiElement(o);
  }

  public void visitDataRecord(@NotNull SchemaDataRecord o) {
    visitDataValue(o);
  }

  public void visitDataRecordEntry(@NotNull SchemaDataRecordEntry o) {
    visitPsiElement(o);
  }

  public void visitDataValue(@NotNull SchemaDataValue o) {
    visitPsiElement(o);
  }

  public void visitDataVar(@NotNull SchemaDataVar o) {
    visitDataValue(o);
  }

  public void visitDataVarEntry(@NotNull SchemaDataVarEntry o) {
    visitPsiElement(o);
  }

  public void visitDefaultOverride(@NotNull SchemaDefaultOverride o) {
    visitPsiElement(o);
  }

  public void visitDefs(@NotNull SchemaDefs o) {
    visitPsiElement(o);
  }

  public void visitEnumMemberDecl(@NotNull SchemaEnumMemberDecl o) {
    visitCustomParamsHolder(o);
    // visitPsiNamedElement(o);
  }

  public void visitEnumTypeBody(@NotNull SchemaEnumTypeBody o) {
    visitCustomParamsHolder(o);
  }

  public void visitEnumTypeDef(@NotNull EpigraphEnumTypeDef o) {
    visitTypeDef(o);
  }

  public void visitExtendsDecl(@NotNull SchemaExtendsDecl o) {
    visitPsiElement(o);
  }

  public void visitFieldDecl(@NotNull SchemaFieldDecl o) {
    visitCustomParamsHolder(o);
    // visitPsiNamedElement(o);
  }

  public void visitFqn(@NotNull SchemaFqn o) {
    visitPsiElement(o);
  }

  public void visitFqnSegment(@NotNull SchemaFqnSegment o) {
    visitPsiNameIdentifierOwner(o);
  }

  public void visitFqnTypeRef(@NotNull SchemaFqnTypeRef o) {
    visitTypeRef(o);
  }

  public void visitImportStatement(@NotNull SchemaImportStatement o) {
    visitPsiElement(o);
  }

  public void visitImports(@NotNull SchemaImports o) {
    visitPsiElement(o);
  }

  public void visitListTypeBody(@NotNull SchemaListTypeBody o) {
    visitCustomParamsHolder(o);
  }

  public void visitListTypeDef(@NotNull EpigraphListTypeDef o) {
    visitTypeDef(o);
  }

  public void visitMapTypeBody(@NotNull SchemaMapTypeBody o) {
    visitCustomParamsHolder(o);
  }

  public void visitMapTypeDef(@NotNull EpigraphMapTypeDef o) {
    visitTypeDef(o);
  }

  public void visitMetaDecl(@NotNull SchemaMetaDecl o) {
    visitPsiElement(o);
  }

  public void visitNamespaceDecl(@NotNull SchemaNamespaceDecl o) {
    visitPsiElement(o);
  }

  public void visitOpInputModelProjection(@NotNull SchemaOpInputModelProjection o) {
    visitPsiElement(o);
  }

  public void visitOpOutputEnumModelProjection(@NotNull SchemaOpOutputEnumModelProjection o) {
    visitPsiElement(o);
  }

  public void visitOpOutputFieldProjection(@NotNull SchemaOpOutputFieldProjection o) {
    visitPsiElement(o);
  }

  public void visitOpOutputFieldProjectionBody(@NotNull SchemaOpOutputFieldProjectionBody o) {
    visitPsiElement(o);
  }

  public void visitOpOutputFieldProjectionBodyPart(@NotNull SchemaOpOutputFieldProjectionBodyPart o) {
    visitPsiElement(o);
  }

  public void visitOpOutputKeyProjection(@NotNull SchemaOpOutputKeyProjection o) {
    visitPsiElement(o);
  }

  public void visitOpOutputKeyProjectionPart(@NotNull SchemaOpOutputKeyProjectionPart o) {
    visitPsiElement(o);
  }

  public void visitOpOutputListModelProjection(@NotNull SchemaOpOutputListModelProjection o) {
    visitPsiElement(o);
  }

  public void visitOpOutputListPolyBranch(@NotNull SchemaOpOutputListPolyBranch o) {
    visitPsiElement(o);
  }

  public void visitOpOutputMapModelProjection(@NotNull SchemaOpOutputMapModelProjection o) {
    visitPsiElement(o);
  }

  public void visitOpOutputMapPolyBranch(@NotNull SchemaOpOutputMapPolyBranch o) {
    visitPsiElement(o);
  }

  public void visitOpOutputModelProjection(@NotNull SchemaOpOutputModelProjection o) {
    visitPsiElement(o);
  }

  public void visitOpOutputModelProjectionBody(@NotNull SchemaOpOutputModelProjectionBody o) {
    visitPsiElement(o);
  }

  public void visitOpOutputModelProjectionBodyPart(@NotNull SchemaOpOutputModelProjectionBodyPart o) {
    visitPsiElement(o);
  }

  public void visitOpOutputPrimitiveModelProjection(@NotNull SchemaOpOutputPrimitiveModelProjection o) {
    visitPsiElement(o);
  }

  public void visitOpOutputRecordModelProjection(@NotNull SchemaOpOutputRecordModelProjection o) {
    visitPsiElement(o);
  }

  public void visitOpOutputRecordPolyBranch(@NotNull SchemaOpOutputRecordPolyBranch o) {
    visitPsiElement(o);
  }

  public void visitOpOutputTagProjection(@NotNull SchemaOpOutputTagProjection o) {
    visitPsiElement(o);
  }

  public void visitOpOutputVarProjection(@NotNull SchemaOpOutputVarProjection o) {
    visitPsiElement(o);
  }

  public void visitOpParamProjection(@NotNull SchemaOpParamProjection o) {
    visitPsiElement(o);
  }

  public void visitOpParameters(@NotNull SchemaOpParameters o) {
    visitPsiElement(o);
  }

  public void visitPrimitiveTypeBody(@NotNull SchemaPrimitiveTypeBody o) {
    visitCustomParamsHolder(o);
  }

  public void visitPrimitiveTypeDef(@NotNull EpigraphPrimitiveTypeDef o) {
    visitTypeDef(o);
  }

  public void visitQid(@NotNull SchemaQid o) {
    visitPsiElement(o);
  }

  public void visitRecordTypeBody(@NotNull SchemaRecordTypeBody o) {
    visitCustomParamsHolder(o);
  }

  public void visitRecordTypeDef(@NotNull EpigraphRecordTypeDef o) {
    visitTypeDef(o);
  }

  public void visitSupplementDef(@NotNull SchemaSupplementDef o) {
    visitPsiElement(o);
  }

  public void visitSupplementsDecl(@NotNull SchemaSupplementsDecl o) {
    visitPsiElement(o);
  }

  public void visitTypeDefWrapper(@NotNull SchemaTypeDefWrapper o) {
    visitPsiElement(o);
  }

  public void visitTypeRef(@NotNull SchemaTypeRef o) {
    visitPsiElement(o);
  }

  public void visitValueTypeRef(@NotNull SchemaValueTypeRef o) {
    visitPsiElement(o);
  }

  public void visitVarTagDecl(@NotNull SchemaVarTagDecl o) {
    visitCustomParamsHolder(o);
    // visitPsiNamedElement(o);
  }

  public void visitVarTagRef(@NotNull SchemaVarTagRef o) {
    visitPsiNameIdentifierOwner(o);
  }

  public void visitVarTypeBody(@NotNull SchemaVarTypeBody o) {
    visitCustomParamsHolder(o);
  }

  public void visitVarTypeDef(@NotNull EpigraphVarTypeDef o) {
    visitTypeDef(o);
  }

  public void visitCustomParamsHolder(@NotNull CustomParamsHolder o) {
    visitElement(o);
  }

  public void visitPsiNameIdentifierOwner(@NotNull PsiNameIdentifierOwner o) {
    visitElement(o);
  }

  public void visitPsiNamedElement(@NotNull PsiNamedElement o) {
    visitElement(o);
  }

  public void visitTypeDef(@NotNull EpigraphTypeDef o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
