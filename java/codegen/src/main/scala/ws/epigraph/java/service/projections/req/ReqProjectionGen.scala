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

package ws.epigraph.java.service.projections.req

import java.nio.file.Path

import ws.epigraph.compiler._
import ws.epigraph.java.NewlineStringInterpolator.NewlineHelper
import ws.epigraph.java.{JavaGen, JavaGenNames, JavaGenUtils}
import ws.epigraph.lang.Qn
import ws.epigraph.projections.op.OpParams
import ws.epigraph.types.{DatumTypeApi, TypeApi}

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
trait ReqProjectionGen extends JavaGen {
  protected val operationInfo: OperationInfo

  def namespace: Qn =
    operationInfo.resourceNamespace
      .append("resources")
      .append(operationInfo.resourceName.toLowerCase)
      .append("operations")
      .append(s"${operationInfo.operation.kind()}${Option(operationInfo.operation.name()).getOrElse("")}".toLowerCase)

  def shortClassName: String

  def fullClassName: String = namespace.append(shortClassName).toString

  override protected def relativeFilePath: Path = JavaGenUtils.fqnToPath(namespace).resolve(shortClassName + ".java")

  def children: Iterable[ReqProjectionGen] = Iterable()

  protected val packageStatement: String = s"package $namespace;"
}

object ReqProjectionGen {
  val classNamePrefix: String = "Req"
  val classNameSuffix: String = "Projection"

  def generateParams(op: OpParams, namespace: String, reqParamsExpr: String): CodeChunk = {
    import scala.collection.JavaConversions._

    op.asMap().values().map{ p =>

      val datumType: CDatumType = toCType(p.projection().model().asInstanceOf[DatumTypeApi])
      // Scala doesn't get it
      val valueType = JavaGenNames.lqn2(datumType, namespace)

      def genPrimitiveParam(nativeType: String): String = /*@formatter:off*/sn"""\
  /**
   * @return {@code ${p.name()}} parameter value
   */
  public @Nullable $nativeType get${JavaGenUtils.up(p.name())}Parameter() {
    ReqParam param = $reqParamsExpr.get("${p.name()}");
    if (param == null) return null;
    $valueType nativeValue = ($valueType) param.value();
    return nativeValue == null ? null : nativeValue.getVal();
  }
"""/*@formatter:on*/

      def genNonPrimitiveParam: String = /*@formatter:off*/sn"""\
  /**
   * @return {@code ${p.name()}} parameter value
   */
  public @Nullable $valueType get${JavaGenUtils.up(p.name())}Parameter() {
    ReqParam param = $reqParamsExpr.get("${p.name()}");
    return param == null ? null : ($valueType) param.value();
  }
"""/*@formatter:on*/

      // unwrap primitive param accessors to return native values
      val paramCode = datumType.kind match {
        case CTypeKind.STRING => genPrimitiveParam("String")
        case CTypeKind.INTEGER => genPrimitiveParam("Integer")
        case CTypeKind.LONG => genPrimitiveParam("Long")
        case CTypeKind.DOUBLE => genPrimitiveParam("Double")
        case CTypeKind.BOOLEAN => genPrimitiveParam("Boolean")
        case _ => genNonPrimitiveParam
      }

      CodeChunk(paramCode, Set(
        "org.jetbrains.annotations.Nullable",
        "ws.epigraph.projections.req.ReqParam"
      ))
    }.foldLeft(CodeChunk.empty)(_ + _)
  }

  def generateImports(imports: Set[String]): String = imports.toList.sorted.mkString("import ", ";\nimport ", ";")

  def toCType(t: TypeApi): CType = t.asInstanceOf[CTypeApiWrapper].cType

  def toCType(t: DatumTypeApi): CDatumType = t.asInstanceOf[CDatumTypeApiWrapper].cType
}
