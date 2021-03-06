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

import ws.epigraph.services._resources.epigraph.projections.output.datumtype._nt.listtype.ListTypeAsm;
import ws.epigraph.services._resources.epigraph.projections.output.datumtype._nt.listtype.supertypes.ListType_ListAsm;
import ws.epigraph.types.ListTypeApi;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public final class ListTypeAsmImpl extends ListTypeAsm<ListTypeApi> {
  public static final ListTypeAsmImpl INSTANCE = new ListTypeAsmImpl();

  private ListTypeAsmImpl() {
    super(
        TypeAsmImpl.ABSTRACT_ASM.from(t -> (ListTypeApi) t), // abstract
        AnnotationsAsmImpl.INSTANCE.from(ListTypeApi::annotations), // annotations
        DatumTypeAsmImpl.INSTANCE.from(ListTypeApi::metaType), // meta
        TypeNameAsmImpl.INSTANCE.from(ListTypeApi::name), // name
        new ListType_ListAsm<>(ListTypeApi::supertypes, INSTANCE), // supertypes
        DataTypeAsmImpl.INSTANCE.from(ListTypeApi::elementType) // element type
    );
  }
}
