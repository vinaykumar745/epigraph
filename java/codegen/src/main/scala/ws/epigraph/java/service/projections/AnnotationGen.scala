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

package ws.epigraph.java.service.projections

import ws.epigraph.annotations.Annotation
import ws.epigraph.java.NewlineStringInterpolator.{NewlineHelper, i}
import ws.epigraph.java.service.ServiceObjectGen.gen
import ws.epigraph.java.service.{ServiceGenContext, ServiceGenUtils, ServiceObjectGen}

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
class AnnotationGen(ann: Annotation) extends ServiceObjectGen[Annotation](ann) {

  override protected def generateObject(ctx: ServiceGenContext): String =
  /*@formatter:off*/sn"""\
new Annotation(
  ${ServiceGenUtils.genTypeExpr(ann.`type`(), ctx.gctx)},
  ${i(gen(ann.gDatum(), ctx))},
  ${gen(ann.location(), ctx)},
  ws.epigraph.refs.IndexBasedTypesResolver.INSTANCE
)"""/*@formatter:on*/
}
