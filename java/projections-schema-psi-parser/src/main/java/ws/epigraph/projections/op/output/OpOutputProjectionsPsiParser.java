/*
 * Copyright 2017 Sumo Logic
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

package ws.epigraph.projections.op.output;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ws.epigraph.projections.Annotations;
import ws.epigraph.projections.ProjectionUtils;
import ws.epigraph.projections.op.OpKeyPresence;
import ws.epigraph.projections.op.OpParams;
import ws.epigraph.psi.EpigraphPsiUtil;
import ws.epigraph.psi.PsiProcessingException;
import ws.epigraph.refs.TypeRef;
import ws.epigraph.refs.TypesResolver;
import ws.epigraph.schema.TypeRefs;
import ws.epigraph.schema.parser.psi.*;
import ws.epigraph.types.*;
import ws.epigraph.types.TypeKind;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import static ws.epigraph.projections.ProjectionsParsingUtil.getDatumType;
import static ws.epigraph.projections.ProjectionsParsingUtil.getUnionType;
import static ws.epigraph.projections.SchemaProjectionPsiParserUtil.*;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public final class OpOutputProjectionsPsiParser {

  private OpOutputProjectionsPsiParser() {}

  public static OpOutputVarProjection parseVarProjection(
      @NotNull DataTypeApi dataType,
      @NotNull SchemaOpOutputVarProjection psi,
      @NotNull TypesResolver typesResolver,
      @NotNull OpOutputPsiProcessingContext context) throws PsiProcessingException {

    final SchemaOpOutputNamedVarProjection namedVarProjection = psi.getOpOutputNamedVarProjection();
    if (namedVarProjection == null) {
      final SchemaOpOutputUnnamedOrRefVarProjection unnamedOrRefVarProjection =
          psi.getOpOutputUnnamedOrRefVarProjection();

      if (unnamedOrRefVarProjection == null)
        throw new PsiProcessingException(
            "Incomplete var projection definition",
            psi,
            context.errors()
        );

      return parseUnnamedOrRefVarProjection(
          dataType,
          unnamedOrRefVarProjection,
          typesResolver,
          context
      );
    } else {
      // named var projection
      final String projectionName = namedVarProjection.getQid().getCanonicalName();

      final @Nullable SchemaOpOutputUnnamedOrRefVarProjection unnamedOrRefVarProjection =
          namedVarProjection.getOpOutputUnnamedOrRefVarProjection();

      if (unnamedOrRefVarProjection == null)
        throw new PsiProcessingException(
            String.format("Incomplete var projection '%s' definition", projectionName),
            psi,
            context.errors()
        );

      final OpOutputVarProjection reference = context.varReferenceContext()
          .reference(dataType.type(), projectionName, false, EpigraphPsiUtil.getLocation(psi));

      final OpOutputVarProjection value = parseUnnamedOrRefVarProjection(
          dataType,
          unnamedOrRefVarProjection,
          typesResolver,
          context
      );

      context.varReferenceContext()
          .resolve(projectionName, value, EpigraphPsiUtil.getLocation(unnamedOrRefVarProjection), context);

      return reference;
    }
  }

  public static OpOutputVarProjection parseUnnamedOrRefVarProjection(
      @NotNull DataTypeApi dataType,
      @NotNull SchemaOpOutputUnnamedOrRefVarProjection psi,
      @NotNull TypesResolver typesResolver,
      @NotNull OpOutputPsiProcessingContext context)
      throws PsiProcessingException {

    final SchemaOpOutputVarProjectionRef varProjectionRef = psi.getOpOutputVarProjectionRef();
    if (varProjectionRef == null) {
      // usual var projection
      final SchemaOpOutputUnnamedVarProjection unnamedVarProjection = psi.getOpOutputUnnamedVarProjection();
      if (unnamedVarProjection == null)
        throw new PsiProcessingException("Incomplete var projection definition", psi, context.errors());
      else return parseUnnamedVarProjection(
          dataType,
          unnamedVarProjection,
          typesResolver,
          context
      );
    } else {
      // var projection reference
      final SchemaQid varProjectionRefPsi = varProjectionRef.getQid();
      if (varProjectionRefPsi == null)
        throw new PsiProcessingException(
            "Incomplete var projection definition: name not specified",
            psi,
            context.errors()
        );

      final String projectionName = varProjectionRefPsi.getCanonicalName();
      return context.varReferenceContext()
          .reference(dataType.type(), projectionName, true, EpigraphPsiUtil.getLocation(psi));

    }
  }

  public static OpOutputVarProjection parseUnnamedVarProjection(
      @NotNull DataTypeApi dataType,
      @NotNull SchemaOpOutputUnnamedVarProjection psi,
      @NotNull TypesResolver typesResolver,
      @NotNull OpOutputPsiProcessingContext context)
      throws PsiProcessingException {

    final TypeApi type = dataType.type();
    final LinkedHashMap<String, OpOutputTagProjectionEntry> tagProjections = new LinkedHashMap<>();

    @Nullable SchemaOpOutputSingleTagProjection singleTagProjectionPsi = psi.getOpOutputSingleTagProjection();

    if (singleTagProjectionPsi == null) {
      @Nullable SchemaOpOutputMultiTagProjection multiTagProjection = psi.getOpOutputMultiTagProjection();
      assert multiTagProjection != null;
      // parse list of tags
      @NotNull List<SchemaOpOutputMultiTagProjectionItem> tagProjectionPsiList =
          multiTagProjection.getOpOutputMultiTagProjectionItemList();

      for (SchemaOpOutputMultiTagProjectionItem tagProjectionPsi : tagProjectionPsiList) {
        final TagApi tag =
            getTag(type, tagProjectionPsi.getTagName(), dataType.defaultTag(), tagProjectionPsi, context);

        final OpOutputModelProjection<?, ?, ?> parsedModelProjection;

        @NotNull DatumTypeApi tagType = tag.type();
        @Nullable SchemaOpOutputModelProjection modelProjection = tagProjectionPsi.getOpOutputModelProjection();
        assert modelProjection != null; // todo when it can be null?

        @NotNull List<SchemaOpOutputModelProperty> modelPropertiesPsi = tagProjectionPsi.getOpOutputModelPropertyList();

        parsedModelProjection = parseModelProjection(
            tagType,
            parseModelParams(modelPropertiesPsi, typesResolver, context),
            parseModelAnnotations(modelPropertiesPsi, context),
            parseModelMetaProjection(tagType, modelPropertiesPsi, typesResolver, context),
            modelProjection,
            typesResolver,
            context
        );

        tagProjections.put(
            tag.name(),
            new OpOutputTagProjectionEntry(
                tag,
                parsedModelProjection,
                EpigraphPsiUtil.getLocation(tagProjectionPsi)
            )
        );
      }
    } else {
      TagApi tag = findTag(
          type,
          singleTagProjectionPsi.getTagName(),
          dataType.defaultTag(),
          singleTagProjectionPsi,
          context
      );
      if (tag != null || !singleTagProjectionPsi.getText().isEmpty()) {
        final OpOutputModelProjection<?, ?, ?> parsedModelProjection;
        if (tag == null) // will throw proper error
          tag = getTag(
              type,
              singleTagProjectionPsi.getTagName(),
              dataType.defaultTag(),
              singleTagProjectionPsi,
              context
          );

        @Nullable SchemaOpOutputModelProjection modelProjection = singleTagProjectionPsi.getOpOutputModelProjection();
        assert modelProjection != null; // todo when it can be null?

        @NotNull List<SchemaOpOutputModelProperty> modelPropertiesPsi =
            singleTagProjectionPsi.getOpOutputModelPropertyList();

        parsedModelProjection = parseModelProjection(
            tag.type(),
            parseModelParams(modelPropertiesPsi, typesResolver, context),
            parseModelAnnotations(modelPropertiesPsi, context),
            parseModelMetaProjection(tag.type(), modelPropertiesPsi, typesResolver, context),
            modelProjection,
            typesResolver,
            context
        );

        tagProjections.put(
            tag.name(),
            new OpOutputTagProjectionEntry(
                tag,
                parsedModelProjection,
                EpigraphPsiUtil.getLocation(singleTagProjectionPsi)
            )
        );
      }
    }

    // parse tails
    final List<OpOutputVarProjection> tails;
    @Nullable SchemaOpOutputVarPolymorphicTail psiTail = psi.getOpOutputVarPolymorphicTail();
    if (psiTail == null) tails = null;
    else {
      tails = new ArrayList<>();

      @Nullable SchemaOpOutputVarSingleTail singleTail = psiTail.getOpOutputVarSingleTail();
      if (singleTail == null) {
        @Nullable SchemaOpOutputVarMultiTail multiTail = psiTail.getOpOutputVarMultiTail();
        assert multiTail != null;
        for (SchemaOpOutputVarMultiTailItem tailItem : multiTail.getOpOutputVarMultiTailItemList()) {
          @NotNull SchemaTypeRef tailTypeRef = tailItem.getTypeRef();
          @NotNull SchemaOpOutputVarProjection psiTailProjection = tailItem.getOpOutputVarProjection();
          @NotNull OpOutputVarProjection tailProjection =
              buildTailProjection(dataType, tailTypeRef, psiTailProjection, typesResolver, context);
          tails.add(tailProjection);
        }
      } else {
        @NotNull SchemaTypeRef tailTypeRef = singleTail.getTypeRef();
        @NotNull SchemaOpOutputVarProjection psiTailProjection = singleTail.getOpOutputVarProjection();
        @NotNull OpOutputVarProjection tailProjection =
            buildTailProjection(dataType, tailTypeRef, psiTailProjection, typesResolver, context);
        tails.add(tailProjection);
      }

    }

    try {
      return new OpOutputVarProjection(
          type,
          tagProjections,
          singleTagProjectionPsi == null || tagProjections.size() != 1,
          tails,
          EpigraphPsiUtil.getLocation(psi)
      );
    } catch (Exception e) {
      throw new PsiProcessingException(e, psi, context);
    }
  }


  private static @NotNull OpParams parseModelParams(
      @NotNull List<SchemaOpOutputModelProperty> modelProperties,
      @NotNull TypesResolver resolver,
      @NotNull OpOutputPsiProcessingContext context) throws PsiProcessingException {


    return parseParams(
        modelProperties.stream().map(SchemaOpOutputModelProperty::getOpParam),
        resolver,
        context.inputPsiProcessingContext()
    );
  }

  private static @NotNull Annotations parseModelAnnotations(
      @NotNull List<SchemaOpOutputModelProperty> modelProperties,
      @NotNull OpOutputPsiProcessingContext context) throws PsiProcessingException {

    return parseAnnotations(
        modelProperties.stream().map(SchemaOpOutputModelProperty::getAnnotation),
        context
    );
  }

  private static @Nullable OpOutputModelProjection<?, ?, ?> parseModelMetaProjection(
      @NotNull DatumTypeApi type,
      @NotNull List<SchemaOpOutputModelProperty> modelProperties,
      @NotNull TypesResolver resolver,
      @NotNull OpOutputPsiProcessingContext context
  ) throws PsiProcessingException {

    @Nullable SchemaOpOutputModelMeta modelMetaPsi = null;

    for (SchemaOpOutputModelProperty modelProperty : modelProperties) {
      if (modelMetaPsi != null)
        context.addError("Metadata projection should be specified only once", modelProperty);

      modelMetaPsi = modelProperty.getOpOutputModelMeta();
    }

    if (modelMetaPsi == null) return null;
    else {
      @Nullable DatumTypeApi metaType = type.metaType();
      if (metaType == null) {
        context.addError(
            String.format("Type '%s' doesn't have a metadata, metadata projection can't be specified", type.name()),
            modelMetaPsi
        );
        return null;
      } else {

        @NotNull SchemaOpOutputModelProjection metaProjectionPsi = modelMetaPsi.getOpOutputModelProjection();
        return parseModelProjection(
            metaType,
            OpParams.EMPTY,
            Annotations.EMPTY,
            null, // TODO what if meta-type has it's own meta-type? meta-meta-type projection should go here
            metaProjectionPsi,
            resolver,
            context
        );
      }
    }
  }

  private static @NotNull OpOutputVarProjection buildTailProjection(
      @NotNull DataTypeApi dataType,
      @NotNull SchemaTypeRef tailTypeRefPsi,
      @NotNull SchemaOpOutputVarProjection psiTailProjection,
      @NotNull TypesResolver typesResolver,
      @NotNull OpOutputPsiProcessingContext context) throws PsiProcessingException {

    @NotNull TypeRef tailTypeRef = TypeRefs.fromPsi(tailTypeRefPsi, context);
    @NotNull UnionTypeApi tailType = getUnionType(tailTypeRef, typesResolver, tailTypeRefPsi, context);
    return parseVarProjection(
        tailType.dataType(dataType.defaultTag()),
        psiTailProjection,
        typesResolver,
        context
    );
  }


  private static @NotNull OpOutputVarProjection createDefaultVarProjection(
      @NotNull TypeApi type,
      @NotNull TagApi tag,
      @NotNull PsiElement locationPsi,
      @NotNull OpOutputPsiProcessingContext context) throws PsiProcessingException {
    return new OpOutputVarProjection(
        type,
        ProjectionUtils.singletonLinkedHashMap(
            tag.name(),
            new OpOutputTagProjectionEntry(
                tag,
                createDefaultModelProjection(
                    tag.type(),
                    OpParams.EMPTY,
                    Annotations.EMPTY,
                    locationPsi,
                    context
                ),
                EpigraphPsiUtil.getLocation(locationPsi)
            )
        ),
        false,
        null,
        EpigraphPsiUtil.getLocation(locationPsi)
    );
  }

  private static @NotNull OpOutputVarProjection createDefaultVarProjection(
      @NotNull DatumTypeApi type,
      @NotNull PsiElement locationPsi,
      @NotNull OpOutputPsiProcessingContext context) throws PsiProcessingException {
    return createDefaultVarProjection(type, type.self(), locationPsi, context);
  }

  public static @NotNull OpOutputVarProjection createDefaultVarProjection(
      @NotNull DataTypeApi type,
      @NotNull PsiElement locationPsi,
      @NotNull OpOutputPsiProcessingContext context)
      throws PsiProcessingException {

    @Nullable TagApi defaultTag = type.defaultTag();
    if (defaultTag == null) {

      if (type.type() instanceof DatumType) {
        DatumTypeApi datumType = (DatumTypeApi) type.type();
        defaultTag = datumType.self();
      } else {
        throw new PsiProcessingException(
            String.format("Can't build default projection for '%s', default tag not specified", type.name()),
            locationPsi,
            context
        );
      }

    }

    return createDefaultVarProjection(type.type(), defaultTag, locationPsi, context);
  }

  public static @NotNull OpOutputModelProjection<?, ?, ?> parseModelProjection(
      @NotNull DatumTypeApi type,
      @NotNull OpParams params,
      @NotNull Annotations annotations,
      @Nullable OpOutputModelProjection<?, ?, ?> metaProjection,
      @NotNull SchemaOpOutputModelProjection psi,
      @NotNull TypesResolver typesResolver,
      @NotNull OpOutputPsiProcessingContext context)
      throws PsiProcessingException {

    return parseModelProjection(
        OpOutputModelProjection.class,
        type,
        params,
        annotations,
        metaProjection,
        psi,
        typesResolver,
        context
    );

  }

  @SuppressWarnings("unchecked")
  private static @NotNull <MP extends OpOutputModelProjection<?, ?, ?>>
  /*@NotNull*/ MP parseModelProjection(
      @NotNull Class<MP> modelClass,
      @NotNull DatumTypeApi type,
      @NotNull OpParams params,
      @NotNull Annotations annotations,
      @Nullable OpOutputModelProjection<?, ?, ?> metaProjection,
      @NotNull SchemaOpOutputModelProjection psi,
      @NotNull TypesResolver typesResolver,
      @NotNull OpOutputPsiProcessingContext context)
      throws PsiProcessingException {

    switch (type.kind()) {
      case RECORD:
        assert modelClass.isAssignableFrom(OpOutputRecordModelProjection.class);

        @Nullable SchemaOpOutputRecordModelProjection recordModelProjectionPsi = psi.getOpOutputRecordModelProjection();
        if (recordModelProjectionPsi == null)
          return (MP) createDefaultModelProjection(type, params, annotations, psi, context);

        ensureModelKind(psi, TypeKind.RECORD, context);
        return (MP) parseRecordModelProjection(
            (RecordTypeApi) type,
            params,
            annotations,
            metaProjection,
            parseModelTails(
                OpOutputRecordModelProjection.class,
                psi.getOpOutputModelPolymorphicTail(),
                typesResolver,
                context
            ),
            recordModelProjectionPsi,
            typesResolver,
            context
        );

      case MAP:
        assert modelClass.isAssignableFrom(OpOutputMapModelProjection.class);

        @Nullable SchemaOpOutputMapModelProjection mapModelProjectionPsi = psi.getOpOutputMapModelProjection();
        if (mapModelProjectionPsi == null)
          return (MP) createDefaultModelProjection(type, params, annotations, psi, context);

        ensureModelKind(psi, TypeKind.MAP, context);

        return (MP) parseMapModelProjection(
            (MapTypeApi) type,
            params,
            annotations,
            metaProjection,
            parseModelTails(
                OpOutputMapModelProjection.class,
                psi.getOpOutputModelPolymorphicTail(),
                typesResolver,
                context
            ),
            mapModelProjectionPsi,
            typesResolver,
            context
        );

      case LIST:
        assert modelClass.isAssignableFrom(OpOutputListModelProjection.class);

        @Nullable SchemaOpOutputListModelProjection listModelProjectionPsi = psi.getOpOutputListModelProjection();
        if (listModelProjectionPsi == null)
          return (MP) createDefaultModelProjection(type, params, annotations, psi, context);

        ensureModelKind(psi, TypeKind.LIST, context);

        return (MP) parseListModelProjection(
            (ListTypeApi) type,
            params,
            annotations,
            metaProjection,
            parseModelTails(
                OpOutputListModelProjection.class,
                psi.getOpOutputModelPolymorphicTail(),
                typesResolver,
                context
            ),
            listModelProjectionPsi,
            typesResolver,
            context
        );

      case ENUM:
        throw new PsiProcessingException("Unsupported type kind: " + type.kind(), psi, context);

      case PRIMITIVE:
        assert modelClass.isAssignableFrom(OpOutputPrimitiveModelProjection.class);

        return (MP) parsePrimitiveModelProjection(
            (PrimitiveTypeApi) type,
            params,
            annotations,
            metaProjection,
            parseModelTails(
                OpOutputPrimitiveModelProjection.class,
                psi.getOpOutputModelPolymorphicTail(),
                typesResolver,
                context
            ),
            psi
        );

      case UNION:
        throw new PsiProcessingException("Unsupported type kind: " + type.kind(), psi, context);

      default:
        throw new PsiProcessingException("Unknown type kind: " + type.kind(), psi, context);
    }
  }

  @Contract("_, null, _, _ -> null")
  private static @Nullable <MP extends OpOutputModelProjection<?, ?, ?>>
  /*@Nullable*/ List<MP> parseModelTails(
      @NotNull Class<MP> modelClass,
      @Nullable SchemaOpOutputModelPolymorphicTail tailPsi,
      @NotNull TypesResolver typesResolver,
      @NotNull OpOutputPsiProcessingContext context) throws PsiProcessingException {

    if (tailPsi == null) return null;
    else {
      List<MP> tails = new ArrayList<>();

      final SchemaOpOutputModelSingleTail singleTailPsi = tailPsi.getOpOutputModelSingleTail();
      if (singleTailPsi == null) {
        final SchemaOpOutputModelMultiTail multiTailPsi = tailPsi.getOpOutputModelMultiTail();
        assert multiTailPsi != null;
        for (SchemaOpOutputModelMultiTailItem tailItemPsi : multiTailPsi.getOpOutputModelMultiTailItemList()) {
          final SchemaOpOutputModelProjection tailProjectionPsi = tailItemPsi.getOpOutputModelProjection();
          if (tailProjectionPsi == null)
            context.addError("Incomplete tail projection", tailItemPsi);
          else tails.add(
              buildModelTailProjection(
                  modelClass,
                  tailItemPsi.getTypeRef(),
                  tailProjectionPsi,
                  tailItemPsi.getOpOutputModelPropertyList(),
                  typesResolver,
                  context
              )
          );
        }
      } else {
        final SchemaOpOutputModelProjection tailProjectionPsi = singleTailPsi.getOpOutputModelProjection();
        if (tailProjectionPsi == null)
          context.addError("Incomplete tail projection", singleTailPsi);
        else
          tails.add(
              buildModelTailProjection(
                  modelClass,
                  singleTailPsi.getTypeRef(),
                  tailProjectionPsi,
                  singleTailPsi.getOpOutputModelPropertyList(),
                  typesResolver,
                  context
              )
          );
      }
      return tails;
    }
  }

  private static @NotNull <MP extends OpOutputModelProjection<?, ?, ?>>
  /*@NotNull*/ MP buildModelTailProjection(
      @NotNull Class<MP> modelClass,
      @NotNull SchemaTypeRef tailTypeRefPsi,
      @NotNull SchemaOpOutputModelProjection modelProjectionPsi,
      @NotNull List<SchemaOpOutputModelProperty> modelPropertiesPsi,
      @NotNull TypesResolver typesResolver,
      @NotNull OpOutputPsiProcessingContext context) throws PsiProcessingException {

    @NotNull TypeRef tailTypeRef = TypeRefs.fromPsi(tailTypeRefPsi, context);
    @NotNull DatumTypeApi tailType = getDatumType(tailTypeRef, typesResolver, tailTypeRefPsi, context);

    return parseModelProjection(
        modelClass,
        tailType,
        parseModelParams(modelPropertiesPsi, typesResolver, context),
        parseModelAnnotations(modelPropertiesPsi, context),
        parseModelMetaProjection(tailType, modelPropertiesPsi, typesResolver, context),
        modelProjectionPsi,
        typesResolver,
        context
    );
  }

  private static void ensureModelKind(
      @NotNull SchemaOpOutputModelProjection psi,
      @NotNull TypeKind expectedKind,
      @NotNull OpOutputPsiProcessingContext context) throws PsiProcessingException {

    @Nullable TypeKind actualKind = findProjectionKind(psi);
    if (expectedKind != actualKind)
      throw new PsiProcessingException(MessageFormat.format(
          "Unexpected projection kind ''{0}'', expected ''{1}''",
          actualKind,
          expectedKind
      ), psi, context);
  }

  private static @Nullable TypeKind findProjectionKind(@NotNull SchemaOpOutputModelProjection psi) {
    if (psi.getOpOutputRecordModelProjection() != null) return TypeKind.RECORD;
    if (psi.getOpOutputMapModelProjection() != null) return TypeKind.MAP;
    if (psi.getOpOutputListModelProjection() != null) return TypeKind.LIST;
    return null;
  }

  private static @NotNull OpOutputModelProjection<?, ?, ?> createDefaultModelProjection(
      @NotNull DatumTypeApi type,
      @NotNull OpParams params,
      @NotNull Annotations annotations,
      @NotNull PsiElement locationPsi,
      @NotNull OpOutputPsiProcessingContext context)
      throws PsiProcessingException {

    switch (type.kind()) {
      case RECORD:
        return new OpOutputRecordModelProjection(
            (RecordTypeApi) type,
            params,
            annotations,
            null,
            Collections.emptyMap(),
            null,
            EpigraphPsiUtil.getLocation(locationPsi)
        );
      case MAP:
        MapTypeApi mapType = (MapTypeApi) type;

        final OpOutputKeyProjection keyProjection =
            new OpOutputKeyProjection(
                OpKeyPresence.OPTIONAL,
                OpParams.EMPTY,
                Annotations.EMPTY,
                EpigraphPsiUtil.getLocation(locationPsi)
            );

        @NotNull DataTypeApi valueType = mapType.valueType();
        @Nullable TagApi defaultValuesTag = valueType.defaultTag();

        if (defaultValuesTag == null)
          throw new PsiProcessingException(String.format(
              "Can't create default projection for map type '%s, as it's value type '%s' doesn't have a default tag",
              type.name(),
              valueType.name()
          ), locationPsi, context);

        final OpOutputVarProjection valueVarProjection = createDefaultVarProjection(
            valueType.type(),
            defaultValuesTag,
            locationPsi,
            context
        );

        return new OpOutputMapModelProjection(
            mapType,
            params,
            annotations,
            null,
            keyProjection,
            valueVarProjection,
            null,
            EpigraphPsiUtil.getLocation(locationPsi)
        );
      case LIST:
        ListTypeApi listType = (ListTypeApi) type;
        @NotNull DataTypeApi elementType = listType.elementType();
        @Nullable TagApi defaultElementsTag = elementType.defaultTag();

        if (defaultElementsTag == null)
          throw new PsiProcessingException(String.format(
              "Can't create default projection for list type '%s, as it's element type '%s' doesn't have a default tag",
              type.name(),
              elementType.name()
          ), locationPsi, context);

        final OpOutputVarProjection itemVarProjection = createDefaultVarProjection(
            elementType.type(),
            defaultElementsTag,
            locationPsi,
            context
        );

        return new OpOutputListModelProjection(
            listType,
            params,
            annotations,
            null,
            itemVarProjection,
            null,
            EpigraphPsiUtil.getLocation(locationPsi)
        );
      case UNION:
        throw new PsiProcessingException(
            "Was expecting to get datum model kind, got: " + type.kind(),
            locationPsi,
            context
        );
      case ENUM:
        throw new PsiProcessingException("Unsupported type kind: " + type.kind(), locationPsi, context);
      case PRIMITIVE:
        return new OpOutputPrimitiveModelProjection(
            (PrimitiveTypeApi) type,
            params,
            annotations,
            null,
            null,
            EpigraphPsiUtil.getLocation(locationPsi)
        );
      default:
        throw new PsiProcessingException("Unknown type kind: " + type.kind(), locationPsi, context);
    }
  }

  public static @NotNull OpOutputRecordModelProjection parseRecordModelProjection(
      @NotNull RecordTypeApi type,
      @NotNull OpParams params,
      @NotNull Annotations annotations,
      @Nullable OpOutputModelProjection<?, ?, ?> metaProjection,
      @Nullable List<OpOutputRecordModelProjection> tails,
      @NotNull SchemaOpOutputRecordModelProjection psi,
      @NotNull TypesResolver typesResolver,
      @NotNull OpOutputPsiProcessingContext context) throws PsiProcessingException {

    LinkedHashMap<String, OpOutputFieldProjectionEntry> fieldProjections = new LinkedHashMap<>();
    @NotNull List<SchemaOpOutputFieldProjectionEntry> fieldProjectionEntriesPsi =
        psi.getOpOutputFieldProjectionEntryList();

    for (SchemaOpOutputFieldProjectionEntry fieldProjectionEntryPsi : fieldProjectionEntriesPsi) {
      final String fieldName = fieldProjectionEntryPsi.getQid().getCanonicalName();
      FieldApi field = type.fieldsMap().get(fieldName);

      if (field == null) {
        context.addError(
            String.format(
                "Unknown field '%s' in type '%s'; known fields: {%s}",
                fieldName,
                type.name(),
                String.join(", ", type.fieldsMap().keySet())
            ),
            fieldProjectionEntryPsi
        );
        continue;
      }

      final @NotNull SchemaOpOutputFieldProjection fieldProjectionPsi =
          fieldProjectionEntryPsi.getOpOutputFieldProjection();

      final OpOutputFieldProjection opOutputFieldProjection = parseFieldProjection(
          field.dataType(),
          fieldProjectionPsi,
          typesResolver,
          context
      );

      fieldProjections.put(
          fieldName,
          new OpOutputFieldProjectionEntry(
              field,
              opOutputFieldProjection,
              EpigraphPsiUtil.getLocation(fieldProjectionPsi)
          )
      );
    }

    return new OpOutputRecordModelProjection(
        type,
        params,
        annotations,
        metaProjection,
        fieldProjections,
        tails,
        EpigraphPsiUtil.getLocation(psi)
    );
  }

  public static @NotNull OpOutputFieldProjection parseFieldProjection(
      @NotNull DataTypeApi fieldType,
      @NotNull SchemaOpOutputFieldProjection psi,
      @NotNull TypesResolver resolver,
      @NotNull OpOutputPsiProcessingContext context) throws PsiProcessingException {

//    List<OpParam> fieldParamsList = null;
//    @Nullable Map<String, Annotation> fieldAnnotationsMap = null;
//    for (SchemaOpOutputFieldProjectionBodyPart fieldBodyPart : psi.getOpOutputFieldProjectionBodyPartList()) {
//      @Nullable SchemaOpParam fieldParamPsi = fieldBodyPart.getOpParam();
//      if (fieldParamPsi != null) {
//        if (fieldParamsList == null) fieldParamsList = new ArrayList<>(3);
//        fieldParamsList.add(parseParameter(fieldParamPsi, resolver, context));
//      }
//
//      fieldAnnotationsMap = parseAnnotation(fieldAnnotationsMap, fieldBodyPart.getAnnotation(), context);
//    }

    return new OpOutputFieldProjection(
//        OpParams.fromCollection(fieldParamsList),
//        Annotations.fromMap(fieldAnnotationsMap),
        parseVarProjection(fieldType, psi.getOpOutputVarProjection(), resolver, context),
        EpigraphPsiUtil.getLocation(psi)
    );
  }

  public static @NotNull OpOutputMapModelProjection parseMapModelProjection(
      @NotNull MapTypeApi type,
      @NotNull OpParams params,
      @NotNull Annotations annotations,
      @Nullable OpOutputModelProjection<?, ?, ?> metaProjection,
      @Nullable List<OpOutputMapModelProjection> tails,
      @NotNull SchemaOpOutputMapModelProjection psi,
      @NotNull TypesResolver resolver,
      @NotNull OpOutputPsiProcessingContext context)
      throws PsiProcessingException {

    @NotNull OpOutputKeyProjection keyProjection =
        parseKeyProjection(psi.getOpOutputKeyProjection(), resolver, context);

    @Nullable SchemaOpOutputVarProjection valueProjectionPsi = psi.getOpOutputVarProjection();
    @NotNull OpOutputVarProjection valueProjection =
        valueProjectionPsi == null
        ? createDefaultVarProjection(
            type.valueType(),
            psi,
            context
        )
        : parseVarProjection(type.valueType(), valueProjectionPsi, resolver, context);

    return new OpOutputMapModelProjection(
        type,
        params,
        annotations,
        metaProjection,
        keyProjection,
        valueProjection,
        tails,
        EpigraphPsiUtil.getLocation(psi)
    );
  }

  private static @NotNull OpOutputKeyProjection parseKeyProjection(
      @NotNull SchemaOpOutputKeyProjection keyProjectionPsi,
      @NotNull TypesResolver resolver,
      @NotNull OpOutputPsiProcessingContext context) throws PsiProcessingException {

    final OpKeyPresence presence;

    if (keyProjectionPsi.getForbidden() != null)
      presence = OpKeyPresence.FORBIDDEN;
    else if (keyProjectionPsi.getRequired() != null)
      presence = OpKeyPresence.REQUIRED;
    else
      presence = OpKeyPresence.OPTIONAL;

    final @NotNull List<SchemaOpOutputKeyProjectionPart> keyPartsPsi =
        keyProjectionPsi.getOpOutputKeyProjectionPartList();

    final @NotNull OpParams keyParams =
        parseParams(
            keyPartsPsi.stream().map(SchemaOpOutputKeyProjectionPart::getOpParam),
            resolver,
            context.inputPsiProcessingContext()
        );
    final @NotNull Annotations keyAnnotations =
        parseAnnotations(keyPartsPsi.stream().map(SchemaOpOutputKeyProjectionPart::getAnnotation), context);

    return new OpOutputKeyProjection(
        presence,
        keyParams,
        keyAnnotations,
        EpigraphPsiUtil.getLocation(keyProjectionPsi)
    );
  }

  public static @NotNull OpOutputListModelProjection parseListModelProjection(
      @NotNull ListTypeApi type,
      @NotNull OpParams params,
      @NotNull Annotations annotations,
      @Nullable OpOutputModelProjection<?, ?, ?> metaProjection,
      @Nullable List<OpOutputListModelProjection> tails,
      @NotNull SchemaOpOutputListModelProjection psi,
      @NotNull TypesResolver resolver,
      @NotNull OpOutputPsiProcessingContext context)
      throws PsiProcessingException {

    OpOutputVarProjection itemsProjection;
    @Nullable SchemaOpOutputVarProjection opOutputVarProjectionPsi = psi.getOpOutputVarProjection();
    if (opOutputVarProjectionPsi == null)
      itemsProjection = createDefaultVarProjection(type, psi, context);
    else
      itemsProjection = parseVarProjection(type.elementType(), opOutputVarProjectionPsi, resolver, context);


    return new OpOutputListModelProjection(
        type,
        params,
        annotations,
        metaProjection,
        itemsProjection,
        tails,
        EpigraphPsiUtil.getLocation(psi)
    );
  }

  public static @NotNull OpOutputPrimitiveModelProjection parsePrimitiveModelProjection(
      @NotNull PrimitiveTypeApi type,
      @NotNull OpParams params,
      @NotNull Annotations annotations,
      @Nullable OpOutputModelProjection<?, ?, ?> metaProjection,
      @Nullable List<OpOutputPrimitiveModelProjection> tails,
      @NotNull PsiElement locationPsi) {

    return new OpOutputPrimitiveModelProjection(
        type,
        params,
        annotations,
        metaProjection,
        tails,
        EpigraphPsiUtil.getLocation(locationPsi)
    );
  }

}
