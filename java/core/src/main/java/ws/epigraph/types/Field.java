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

/* Created by yegor on 2017-05-17. */

package ws.epigraph.types;

import org.jetbrains.annotations.NotNull;
import ws.epigraph.annotations.Annotations;

import java.util.Objects;

public class Field implements FieldApi {

  public final @NotNull String name;

  public final @NotNull DataType dataType;

  private final @NotNull Annotations annotations;

  public Field(
      @NotNull String name,
      @NotNull DataType dataType,
      @NotNull Annotations annotations) { // TODO capture overridden super-fields?
    this.name = name;
    this.dataType = dataType;
    this.annotations = annotations;
  }

  @Override
  public @NotNull String name() { return name; }

  @Override
  public @NotNull DataType dataType() { return dataType; }

  @Override
  public @NotNull Annotations annotations() { return annotations; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Field that = (Field) o;
    return Objects.equals(name, that.name) && Objects.equals(dataType, that.dataType) &&
           Objects.equals(annotations, that.annotations);
  }

  @Override
  public int hashCode() { return Objects.hash(name, dataType, annotations); }

}
