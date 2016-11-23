/*
 * Copyright 2016 Sumo Logic
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ws.epigraph.projections.op.path;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Contract;
import ws.epigraph.idl.parser.psi.*;
import ws.epigraph.lang.TextLocation;
import ws.epigraph.projections.Annotation;
import ws.epigraph.projections.Annotations;
import ws.epigraph.projections.op.OpParam;
import ws.epigraph.projections.op.OpParams;
import ws.epigraph.psi.EpigraphPsiUtil;
import ws.epigraph.psi.PsiProcessingError;
import ws.epigraph.psi.PsiProcessingException;
import ws.epigraph.refs.TypesResolver;
import ws.epigraph.types.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ws.epigraph.projections.IdlProjectionPsiParserUtil.*;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class OpPathPsiParser {

  public static OpVarPath parseVarPath(
      @NotNull DataType dataType,
      @NotNull IdlOpVarPath psi,
      @NotNull TypesResolver typesResolver,
      @NotNull List<PsiProcessingError> errors)
      throws PsiProcessingException {

    final Type type = dataType.type;

    @Nullable IdlOpModelPath modelProjection = psi.getOpModelPath();

    if (isModelPathEmpty(modelProjection)) {
      if (psi.getTagName() != null)
        throw new PsiProcessingException("Path can't end with a tag", psi.getTagName(), errors);

      return new OpVarPath(
          type,
          null, // no tags = end of path
          EpigraphPsiUtil.getLocation(psi)
      );
    }

    final Type.Tag tag = getTag(
        type,
        psi.getTagName(),
        dataType.defaultTag,
        psi,
        errors
    );

    @NotNull List<IdlOpModelPathProperty> modelPropertiesPsi =
        psi.getOpModelPathPropertyList();

    final OpModelPath<?, ?> parsedModelProjection = parseModelPath(
        tag.type,
        parseModelParams(modelPropertiesPsi, typesResolver, errors),
        parseModelAnnotations(modelPropertiesPsi, errors),
        modelProjection,
        typesResolver,
        errors
    );

    try {
      return new OpVarPath(
          type,
          new OpTagPath(
              tag, parsedModelProjection, EpigraphPsiUtil.getLocation(modelProjection)
          ),
          EpigraphPsiUtil.getLocation(psi)
      );
    } catch (Exception e) {
      throw new PsiProcessingException(e, psi, errors);
    }
  }


  @NotNull
  private static OpParams parseModelParams(
      @NotNull List<IdlOpModelPathProperty> modelProperties,
      @NotNull TypesResolver resolver,
      @NotNull List<PsiProcessingError> errors) throws PsiProcessingException {


    return parseParams(
        modelProperties.stream().map(IdlOpModelPathProperty::getOpParam),
        resolver,
        errors
    );
  }

  @NotNull
  private static Annotations parseModelAnnotations(
      @NotNull List<IdlOpModelPathProperty> modelProperties,
      @NotNull List<PsiProcessingError> errors) throws PsiProcessingException {

    return parseAnnotations(
        modelProperties.stream().map(IdlOpModelPathProperty::getAnnotation),
        errors
    );
  }

  @NotNull
  private static OpVarPath createDefaultVarPath(
      @NotNull Type type,
      @NotNull Type.Tag tag,
      @NotNull PsiElement locationPsi,
      @NotNull List<PsiProcessingError> errors) throws PsiProcessingException {

    return new OpVarPath(
        type,
        new OpTagPath(
            tag,
            createDefaultModelPath(
                tag.type,
                OpParams.EMPTY,
                Annotations.EMPTY,
                locationPsi,
                errors
            ),
            EpigraphPsiUtil.getLocation(locationPsi)
        ),
        EpigraphPsiUtil.getLocation(locationPsi)
    );
  }

  @Contract("null -> true")
  private static boolean isModelPathEmpty(@Nullable IdlOpModelPath pathPsi) {
    return pathPsi == null || (
        pathPsi.getOpRecordModelPath() == null &&
        pathPsi.getOpMapModelPath() == null
    );
  }

  @NotNull
  public static OpModelPath<?, ?> parseModelPath(
      @NotNull DatumType type,
      @NotNull OpParams params,
      @NotNull Annotations annotations,
      @NotNull IdlOpModelPath psi,
      @NotNull TypesResolver typesResolver,
      @NotNull List<PsiProcessingError> errors) throws PsiProcessingException {

    switch (type.kind()) {
      case RECORD:
        @Nullable IdlOpRecordModelPath recordModelProjectionPsi = psi.getOpRecordModelPath();
        if (recordModelProjectionPsi == null)
          return createDefaultModelPath(type, params, annotations, psi, errors);
        ensureModelKind(psi, TypeKind.RECORD, errors);
        return parseRecordModelPath(
            (RecordType) type,
            params,
            annotations,
            recordModelProjectionPsi,
            typesResolver,
            errors
        );
      case MAP:
        @Nullable IdlOpMapModelPath mapModelProjectionPsi = psi.getOpMapModelPath();
        if (mapModelProjectionPsi == null)
          return createDefaultModelPath(type, params, annotations, psi, errors);
        ensureModelKind(psi, TypeKind.MAP, errors);

        return parseMapModelPath(
            (MapType) type,
            params,
            annotations,
            mapModelProjectionPsi,
            typesResolver,
            errors
        );
      case LIST:
        throw new PsiProcessingException("Unsupported type kind: " + type.kind(), psi, errors);
      case ENUM:
        throw new PsiProcessingException("Unsupported type kind: " + type.kind(), psi, errors);
      case PRIMITIVE:
        return parsePrimitiveModelPath(
            (PrimitiveType) type,
            params,
            annotations,
            psi
        );
      case UNION:
        throw new PsiProcessingException("Unsupported type kind: " + type.kind(), psi, errors);
      default:
        throw new PsiProcessingException("Unknown type kind: " + type.kind(), psi, errors);
    }
  }

  private static void ensureModelKind(
      @NotNull IdlOpModelPath psi,
      @NotNull TypeKind expectedKind,
      @NotNull List<PsiProcessingError> errors) throws PsiProcessingException {

    @Nullable TypeKind actualKind = findProjectionKind(psi);
    if (!expectedKind.equals(actualKind))
      throw new PsiProcessingException(MessageFormat.format(
          "Unexpected projection kind ''{0}'', expected ''{1}''",
          actualKind,
          expectedKind
      ), psi, errors);
  }

  @Nullable
  private static TypeKind findProjectionKind(@NotNull IdlOpModelPath psi) {
    if (psi.getOpRecordModelPath() != null) return TypeKind.RECORD;
    if (psi.getOpMapModelPath() != null) return TypeKind.MAP;
    return null;
  }

  @NotNull
  private static OpModelPath<?, ?> createDefaultModelPath(
      @NotNull DatumType type,
      @NotNull OpParams params,
      @NotNull Annotations annotations,
      @NotNull PsiElement locationPsi, @NotNull List<PsiProcessingError> errors) throws PsiProcessingException {

    switch (type.kind()) {
      case RECORD:
        return new OpRecordModelPath(
            (RecordType) type,
            params,
            annotations,
            null,
            EpigraphPsiUtil.getLocation(locationPsi)
        );
      case MAP:
        MapType mapType = (MapType) type;

        final OpPathKeyProjection keyProjection =
            new OpPathKeyProjection(
                OpParams.EMPTY,
                Annotations.EMPTY,
                EpigraphPsiUtil.getLocation(locationPsi)
            );

        @NotNull DataType valueType = mapType.valueType();
        Type.@Nullable Tag defaultValuesTag = valueType.defaultTag;

        if (defaultValuesTag == null)
          throw new PsiProcessingException(String.format(
              "Can't create default projection for map type '%s, as it's value type '%s' doesn't have a default tag",
              type.name(),
              valueType.name
          ), locationPsi, errors);

        final OpVarPath valueVarProjection = createDefaultVarPath(
            valueType.type,
            defaultValuesTag,
            locationPsi,
            errors
        );

        return new OpMapModelPath(
            mapType,
            params,
            annotations,
            keyProjection,
            valueVarProjection,
            EpigraphPsiUtil.getLocation(locationPsi)
        );
      case LIST:
        throw new PsiProcessingException("Unsupported type kind: " + type.kind(), locationPsi, errors);
      case UNION:
        throw new PsiProcessingException(
            "Was expecting to get datum model kind, got: " + type.kind(),
            locationPsi,
            errors
        );
      case ENUM:
        throw new PsiProcessingException("Unsupported type kind: " + type.kind(), locationPsi, errors);
      case PRIMITIVE:
        return new OpPrimitiveModelPath(
            (PrimitiveType) type,
            params,
            annotations,
            EpigraphPsiUtil.getLocation(locationPsi)
        );
      default:
        throw new PsiProcessingException("Unknown type kind: " + type.kind(), locationPsi, errors);
    }
  }

  @NotNull
  public static OpRecordModelPath parseRecordModelPath(
      @NotNull RecordType type,
      @NotNull OpParams params,
      @NotNull Annotations annotations,
      @NotNull IdlOpRecordModelPath psi,
      @NotNull TypesResolver typesResolver,
      @NotNull List<PsiProcessingError> errors) throws PsiProcessingException {

    final @NotNull IdlOpFieldPathEntry fieldPathEntryPsi = psi.getOpFieldPathEntry();

    final String fieldName = fieldPathEntryPsi.getQid().getCanonicalName();
    RecordType.Field field = type.fieldsMap().get(fieldName);
    if (field == null)
      throw new PsiProcessingException(
          String.format("Can't field projection for '%s', field '%s' not found", type.name(), fieldName),
          fieldPathEntryPsi,
          errors
      );

    @NotNull final IdlOpFieldPath fieldPathPsi = fieldPathEntryPsi.getOpFieldPath();

    @NotNull final TextLocation fieldLocation = EpigraphPsiUtil.getLocation(fieldPathEntryPsi);

    final OpFieldPathEntry fieldProjection = new OpFieldPathEntry(
        field,
        parseFieldPath(
            field.dataType(),
            fieldPathPsi,
            typesResolver,
            errors
        ),
        fieldLocation
    );

    return new OpRecordModelPath(
        type,
        params,
        annotations,
        fieldProjection,
        EpigraphPsiUtil.getLocation(psi)
    );
  }

  @NotNull
  public static OpFieldPath parseFieldPath(
      @NotNull DataType fieldType,
      @NotNull IdlOpFieldPath psi,
      @NotNull TypesResolver resolver,
      @NotNull List<PsiProcessingError> errors) throws PsiProcessingException {

    @NotNull OpParams fieldParams;
    @NotNull Annotations fieldAnnotations;

    List<OpParam> fieldParamsList = null;
    @Nullable Map<String, Annotation> fieldAnnotationsMap = null;
    for (IdlOpFieldPathBodyPart fieldBodyPart : psi.getOpFieldPathBodyPartList()) {
      @Nullable IdlOpParam fieldParamPsi = fieldBodyPart.getOpParam();
      if (fieldParamPsi != null) {
        if (fieldParamsList == null) fieldParamsList = new ArrayList<>(3);
        fieldParamsList.add(parseParameter(fieldParamPsi, resolver, errors));
      }

      fieldAnnotationsMap = parseAnnotation(fieldAnnotationsMap, fieldBodyPart.getAnnotation(), errors);
    }

    fieldParams = fieldParamsList == null ? OpParams.EMPTY : new OpParams(fieldParamsList);
    fieldAnnotations = fieldAnnotationsMap == null ? Annotations.EMPTY : new Annotations(fieldAnnotationsMap);

    final OpVarPath varProjection;

    @Nullable IdlOpVarPath varPathPsi = psi.getOpVarPath();

    if (varPathPsi == null) {
      @Nullable Type.Tag defaultFieldTag = fieldType.defaultTag;
      if (defaultFieldTag == null)
        throw new PsiProcessingException(String.format(
            "Can't construct default projection for type '%s' because it has no default tag",
            fieldType.name
        ), psi, errors);

      varProjection =
          createDefaultVarPath(fieldType.type, defaultFieldTag, psi, errors);
    } else {
      varProjection = parseVarPath(fieldType, varPathPsi, resolver, errors);
    }

    return new OpFieldPath(
        fieldParams,
        fieldAnnotations,
        varProjection,
        EpigraphPsiUtil.getLocation(psi)
    );
  }

  @NotNull
  public static OpMapModelPath parseMapModelPath(
      @NotNull MapType type,
      @NotNull OpParams params,
      @NotNull Annotations annotations,
      @NotNull IdlOpMapModelPath psi,
      @NotNull TypesResolver resolver,
      @NotNull List<PsiProcessingError> errors) throws PsiProcessingException {

    @NotNull OpPathKeyProjection keyProjection = parseKeyProjection(psi.getOpPathKeyProjection(), resolver, errors);

    @Nullable IdlOpVarPath valueProjectionPsi = psi.getOpVarPath();

    if (valueProjectionPsi == null)
      throw new PsiProcessingException("Map value projection not specified", psi, errors);

    @NotNull OpVarPath valueProjection = parseVarPath(type.valueType(), valueProjectionPsi, resolver, errors);

    return new OpMapModelPath(
        type,
        params,
        annotations,
        keyProjection,
        valueProjection,
        EpigraphPsiUtil.getLocation(psi)
    );
  }

  @NotNull
  private static OpPathKeyProjection parseKeyProjection(
      @NotNull IdlOpPathKeyProjection keyProjectionPsi,
      @NotNull TypesResolver resolver,
      @NotNull List<PsiProcessingError> errors) throws PsiProcessingException {

    List<OpParam> params = null;
    @Nullable Map<String, Annotation> annotationsMap = null;

    @Nullable final IdlOpPathKeyProjectionBody body = keyProjectionPsi.getOpPathKeyProjectionBody();
    if (body != null) {
      for (IdlOpPathKeyProjectionPart keyPart : body.getOpPathKeyProjectionPartList()) {
        @Nullable IdlOpParam paramPsi = keyPart.getOpParam();
        if (paramPsi != null) {
          if (params == null) params = new ArrayList<>(3);
          params.add(parseParameter(paramPsi, resolver, errors));
        }

        annotationsMap = parseAnnotation(annotationsMap, keyPart.getAnnotation(), errors);
      }
    }

    return new OpPathKeyProjection(
        OpParams.fromCollection(params),
        Annotations.fromMap(annotationsMap),
        EpigraphPsiUtil.getLocation(keyProjectionPsi)
    );
  }

  @NotNull
  public static OpPrimitiveModelPath parsePrimitiveModelPath(
      @NotNull PrimitiveType type,
      @NotNull OpParams params,
      @NotNull Annotations annotations,
      @NotNull PsiElement locationPsi) {

    return new OpPrimitiveModelPath(
        type,
        params,
        annotations,
        EpigraphPsiUtil.getLocation(locationPsi)
    );
  }

}