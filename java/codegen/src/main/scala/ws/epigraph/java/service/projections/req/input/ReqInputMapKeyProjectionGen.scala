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

package ws.epigraph.java.service.projections.req.input

import ws.epigraph.compiler.CMapType
import ws.epigraph.java.GenContext
import ws.epigraph.java.service.projections.req.input.ReqInputProjectionGen.{classNamePrefix, classNameSuffix}
import ws.epigraph.java.service.projections.req.{OperationInfo, ReqMapKeyProjectionGen}
import ws.epigraph.lang.Qn
import ws.epigraph.projections.op.input.OpInputKeyProjection

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
class ReqInputMapKeyProjectionGen(
  protected val operationInfo: OperationInfo,
  protected val cMapType: CMapType,
  protected val op: OpInputKeyProjection,
  protected val namespaceSuffix: Qn,
  protected val ctx: GenContext) extends ReqInputProjectionGen with ReqMapKeyProjectionGen {

  override type OpKeyProjectionType = OpInputKeyProjection

  override def shortClassName: String = s"$classNamePrefix${mapTypeShortName}Key$classNameSuffix"

  override protected def generate: String = generate(
    Qn.fromDotSeparated("ws.epigraph.projections.req.input.ReqInputKeyProjection")
  )
}
