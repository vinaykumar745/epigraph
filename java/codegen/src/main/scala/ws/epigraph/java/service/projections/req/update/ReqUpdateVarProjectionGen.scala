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

package ws.epigraph.java.service.projections.req.update

import ws.epigraph.java.GenContext
import ws.epigraph.java.JavaGenNames.{jn, ln}
import ws.epigraph.java.service.projections.req.update.ReqUpdateProjectionGen.{classNamePrefix, classNameSuffix}
import ws.epigraph.java.service.projections.req.{OperationInfo, ReqProjectionGen, ReqVarProjectionGen}
import ws.epigraph.lang.Qn
import ws.epigraph.projections.op.input._
import ws.epigraph.types.TypeKind

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
class ReqUpdateVarProjectionGen(
  protected val operationInfo: OperationInfo,
  protected val op: OpInputVarProjection,
  protected val namespaceSuffix: Qn,
  protected val ctx: GenContext) extends ReqUpdateProjectionGen with ReqVarProjectionGen {

  override type OpProjectionType = OpInputVarProjection
  override type OpTagProjectionEntryType = OpInputTagProjectionEntry

  override val shortClassName: String = s"$classNamePrefix${ln(cType)}$classNameSuffix"

  override protected def tailGenerator(op: OpInputVarProjection, normalized: Boolean) =
    new ReqUpdateVarProjectionGen(
      operationInfo,
      op,
      namespaceSuffix.append(
        ReqVarProjectionGen.typeNameToPackageName(cType, namespace.toString) + ReqVarProjectionGen.tailPackageSuffix(
          normalized)
      ),
      ctx
    ) {
      override protected lazy val normalizedTailGenerators: Map[OpInputVarProjection, ReqProjectionGen] = Map()
    }

  override protected def tagGenerator(tpe: OpInputTagProjectionEntry): ReqProjectionGen =
    ReqUpdateModelProjectionGen.dataProjectionGen(
      operationInfo,
      tpe.projection(),
      namespaceSuffix.append(jn(tpe.tag().name()).toLowerCase),
      ctx
    )

  override protected def generate: String = generate(
    Qn.fromDotSeparated("ws.epigraph.projections.req.update.ReqUpdateVarProjection"),
    Qn.fromDotSeparated("ws.epigraph.projections.req.update.ReqUpdateTagProjectionEntry")
  )
}

object ReqUpdateVarProjectionGen {
  def dataProjectionGen(
    operationInfo: OperationInfo,
    op: OpInputVarProjection,
    namespaceSuffix: Qn,
    ctx: GenContext): ReqUpdateProjectionGen = op.`type`().kind() match {

    case TypeKind.UNION =>
      new ReqUpdateVarProjectionGen(operationInfo, op, namespaceSuffix, ctx)
    case TypeKind.RECORD =>
      new ReqUpdateRecordModelProjectionGen(
        operationInfo,
        op.singleTagProjection().projection().asInstanceOf[OpInputRecordModelProjection],
        namespaceSuffix,
        ctx
      )
    case TypeKind.MAP =>
      new ReqUpdateMapModelProjectionGen(
        operationInfo,
        op.singleTagProjection().projection().asInstanceOf[OpInputMapModelProjection],
        namespaceSuffix,
        ctx
      )
    case TypeKind.LIST =>
      new ReqUpdateListModelProjectionGen(
        operationInfo,
        op.singleTagProjection().projection().asInstanceOf[OpInputListModelProjection],
        namespaceSuffix,
        ctx
      )
    case TypeKind.PRIMITIVE =>
      new ReqUpdatePrimitiveModelProjectionGen(
        operationInfo,
        op.singleTagProjection().projection().asInstanceOf[OpInputPrimitiveModelProjection],
        namespaceSuffix,
        ctx
      )
    case x => throw new RuntimeException(s"Unknown projection kind: $x")

  }
}