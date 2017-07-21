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

package ws.epigraph.services.resources.epigraph.types;

import ws.epigraph.services._resources.epigraph.projections.output.type._nt.vartype.VarTypeAsm;
import ws.epigraph.services._resources.epigraph.projections.output.type._nt.vartype.supertypes.VarType_ListAsm;
import ws.epigraph.services._resources.epigraph.projections.output.type._nt.vartype.tags.Tag_ListAsm;
import ws.epigraph.types.EntityTypeApi;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public final class EntityTypeAsm extends VarTypeAsm<EntityTypeApi> {
  public static final EntityTypeAsm INSTANCE = new EntityTypeAsm();

  private EntityTypeAsm() {
    super(
        TypeAsmImpl.ABSTRACT_ASM.on(t -> (EntityTypeApi) t), // abstract
        AnnotationsAsmImpl.INSTANCE.on(EntityTypeApi::annotations), // annotations
        QualifiedTypeNameAsmImpl.INSTANCE.on(EntityTypeApi::name), // name
        new VarType_ListAsm<>(EntityTypeApi::supertypes, INSTANCE),  // supertypes
        new Tag_ListAsm<>(EntityTypeApi::tags, TagAsmImpl.INSTANCE) // tags
    );
  }
}