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

package ws.epigraph.projections.gen;

import ws.epigraph.types.ListType;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public interface GenListModelProjection<
    VP extends GenVarProjection<VP, TP, MP>,
    TP extends GenTagProjectionEntry<TP, MP>,
    MP extends GenModelProjection</*MP*/?, ?>,
    LMP extends GenListModelProjection<VP, TP, MP, LMP, M>,
    M extends ListType
    > extends GenModelProjection<LMP, M> {

  @NotNull VP itemsProjection();
}