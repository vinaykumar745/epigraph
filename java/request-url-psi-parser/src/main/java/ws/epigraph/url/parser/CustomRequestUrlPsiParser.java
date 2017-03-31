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

package ws.epigraph.url.parser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ws.epigraph.gdata.GDatum;
import ws.epigraph.projections.StepsAndProjection;
import ws.epigraph.projections.gen.ProjectionReferenceName;
import ws.epigraph.projections.op.input.OpInputFieldProjection;
import ws.epigraph.projections.op.path.OpFieldPath;
import ws.epigraph.projections.req.input.ReqInputFieldProjection;
import ws.epigraph.projections.req.output.ReqOutputFieldProjection;
import ws.epigraph.projections.req.path.ReqFieldPath;
import ws.epigraph.psi.PsiProcessingContext;
import ws.epigraph.psi.PsiProcessingException;
import ws.epigraph.refs.TypesResolver;
import ws.epigraph.schema.operations.CustomOperationDeclaration;
import ws.epigraph.types.DataTypeApi;
import ws.epigraph.types.TypeApi;
import ws.epigraph.url.CustomRequestUrl;
import ws.epigraph.url.parser.psi.UrlCustomUrl;
import ws.epigraph.url.parser.psi.UrlReqInputFieldProjection;
import ws.epigraph.url.parser.psi.UrlReqOutputTrunkFieldProjection;
import ws.epigraph.url.projections.req.input.ReqInputProjectionsPsiParser;
import ws.epigraph.url.projections.req.input.ReqInputPsiProcessingContext;
import ws.epigraph.url.projections.req.input.ReqInputReferenceContext;
import ws.epigraph.url.projections.req.path.ReqPathPsiParser;
import ws.epigraph.url.projections.req.path.ReqPathPsiProcessingContext;

import java.util.Map;

import static ws.epigraph.url.projections.UrlProjectionsPsiParserUtil.addTypeNamespace;
import static ws.epigraph.url.projections.UrlProjectionsPsiParserUtil.parseRequestParams;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public final class CustomRequestUrlPsiParser {

  private CustomRequestUrlPsiParser() {}

  public static @NotNull CustomRequestUrl parseCustomRequestUrl(
      @NotNull DataTypeApi resourceType,
      @NotNull CustomOperationDeclaration op,
      @NotNull UrlCustomUrl psi,
      @NotNull TypesResolver typesResolver,
      @NotNull PsiProcessingContext context) throws PsiProcessingException {

    final @Nullable OpFieldPath opPath = op.path();

    final Map<String, GDatum> requestParams = parseRequestParams(psi.getRequestParamList(), context);

    if (opPath == null)
      return parseCustomRequestUrlWithoutPath(resourceType, requestParams, op, psi, typesResolver, context);
    else
      return parseCustomRequestUrlWithPath(resourceType, requestParams, op, opPath, psi, typesResolver, context);
  }

  private static @NotNull CustomRequestUrl parseCustomRequestUrlWithPath(
      final @NotNull DataTypeApi resourceType,
      final @NotNull Map<String, GDatum> requestParams,
      final @NotNull CustomOperationDeclaration op,
      final @NotNull OpFieldPath opPath,
      final @NotNull UrlCustomUrl psi,
      final @NotNull TypesResolver typesResolver,
      final @NotNull PsiProcessingContext context) throws PsiProcessingException {

    final @NotNull ReqFieldPath reqPath =
        ReqPathPsiParser.parseFieldPath(
            resourceType,
            opPath,
            psi.getReqFieldPath(),
            typesResolver,
            new ReqPathPsiProcessingContext(context)
        );

    final @NotNull TypeApi opOutputType =
        op.outputType(); // already calculated based on outputType/path declared in idl

    TypesResolver newResolver = addTypeNamespace(opOutputType, typesResolver);

    final @NotNull StepsAndProjection<ReqOutputFieldProjection> outputStepsAndProjection =
        RequestUrlPsiParserUtil.parseOutputProjection(
            opOutputType.dataType(),
            op.outputProjection(),
            psi.getReqOutputTrunkFieldProjection(),
            newResolver,
            context
        );

    return new CustomRequestUrl(
        psi.getQid().getCanonicalName(),
        reqPath,
        getInputProjection(op, psi, typesResolver, context),
        outputStepsAndProjection,
        requestParams
    );

  }

  private static @NotNull CustomRequestUrl parseCustomRequestUrlWithoutPath(
      final @NotNull DataTypeApi resourceType,
      final Map<String, GDatum> requestParams,
      final @NotNull CustomOperationDeclaration op,
      final @NotNull UrlCustomUrl psi,
      final @NotNull TypesResolver typesResolver,
      final @NotNull PsiProcessingContext context)
      throws PsiProcessingException {

    final @Nullable UrlReqOutputTrunkFieldProjection fieldProjectionPsi = psi.getReqOutputTrunkFieldProjection();
    TypesResolver newResolver = addTypeNamespace(resourceType.type(), typesResolver);

    final StepsAndProjection<ReqOutputFieldProjection> outputStepsAndProjection =
        RequestUrlPsiParserUtil.parseOutputProjection(
            op.outputType().dataType(),
            op.outputProjection(),
            fieldProjectionPsi,
            newResolver,
            context
        );

    return new CustomRequestUrl(
        psi.getQid().getCanonicalName(),
        null,
        getInputProjection(op, psi, typesResolver, context),
        outputStepsAndProjection,
        requestParams
    );
  }

  private static @Nullable ReqInputFieldProjection getInputProjection(
      final @NotNull CustomOperationDeclaration op,
      final @NotNull UrlCustomUrl psi,
      final @NotNull TypesResolver typesResolver,
      final @NotNull PsiProcessingContext context) throws PsiProcessingException {
    final ReqInputFieldProjection inputProjection;

    // todo unify with same code from `CreateRequestUrlParser`
    final @Nullable UrlReqInputFieldProjection inputProjectionPsi = psi.getReqInputFieldProjection();
    final @Nullable OpInputFieldProjection opInputProjection = op.inputProjection();
    if (inputProjectionPsi == null) inputProjection = null;
    else {
      final @Nullable TypeApi opInputType = op.inputType();
      assert opInputType != null;

      if (opInputProjection == null)
        throw new PsiProcessingException(
            "Input projection is not supported by the operation",
            inputProjectionPsi,
            context
        );

      ReqInputReferenceContext reqInputReferenceContext =
          new ReqInputReferenceContext(ProjectionReferenceName.EMPTY, null, context);
      ReqInputPsiProcessingContext reqInputPsiProcessingContext =
          new ReqInputPsiProcessingContext(context, reqInputReferenceContext);

      @NotNull DataTypeApi inputDataType = opInputType.dataType();
      inputProjection = ReqInputProjectionsPsiParser.parseFieldProjection(
          inputDataType,
          opInputProjection,
          inputProjectionPsi,
          typesResolver,
          reqInputPsiProcessingContext
      );

      reqInputReferenceContext.ensureAllReferencesResolved();
    }
    return inputProjection;
  }

}
