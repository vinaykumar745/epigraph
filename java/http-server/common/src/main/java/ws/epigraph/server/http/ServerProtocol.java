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

package ws.epigraph.server.http;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ws.epigraph.data.Data;
import ws.epigraph.data.Datum;
import ws.epigraph.errors.ErrorValue;
import ws.epigraph.invocation.OperationInvocationContext;
import ws.epigraph.invocation.OperationInvocationError;
import ws.epigraph.projections.op.input.OpInputVarProjection;
import ws.epigraph.projections.req.input.ReqInputVarProjection;
import ws.epigraph.projections.req.output.ReqOutputModelProjection;
import ws.epigraph.projections.req.output.ReqOutputVarProjection;
import ws.epigraph.projections.req.update.ReqUpdateVarProjection;
import ws.epigraph.schema.operations.OperationKind;
import ws.epigraph.wire.FormatException;

import java.io.IOException;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public interface ServerProtocol<C extends HttpInvocationContext> {

  @Nullable Data readInput(
      @NotNull OpInputVarProjection opInputProjection,
      @Nullable ReqInputVarProjection reqInputProjection,
      @NotNull C httpInvocationContext,
      @NotNull OperationInvocationContext operationInvocationContext) throws FormatException, IOException;

  @Nullable Data readUpdateInput(
      @NotNull OpInputVarProjection opInputProjection,
      @Nullable ReqUpdateVarProjection reqUpdateProjection,
      @NotNull C httpInvocationContext,
      @NotNull OperationInvocationContext operationInvocationContext) throws FormatException, IOException;

  void writeDataResponse(
      @NotNull OperationKind operationKind,
      @NotNull ReqOutputVarProjection projection,
      @Nullable Data data,
      @NotNull C httpInvocationContext,
      @NotNull OperationInvocationContext operationInvocationContext);

  void writeDatumResponse(
      @NotNull OperationKind operationKind,
      @NotNull ReqOutputModelProjection<?, ?, ?> projection,
      @Nullable Datum datum,
      @NotNull C httpInvocationContext,
      @NotNull OperationInvocationContext operationInvocationContext);

  void writeErrorResponse(
      @NotNull OperationKind operationKind,
      @NotNull ErrorValue error,
      @NotNull C httpInvocationContext,
      @NotNull OperationInvocationContext operationInvocationContext);

  void writeEmptyResponse(
      @NotNull OperationKind operationKind,
      @NotNull C httpInvocationContext,
      @NotNull OperationInvocationContext operationInvocationContext);

  void writeInvocationErrorResponse(
      @NotNull OperationInvocationError error,
      @NotNull C httpInvocationContext,
      @NotNull OperationInvocationContext operationInvocationContext);

  // we don't know about formats here
//  interface Factory<C extends HttpInvocationContext, P extends ServerProtocol<C>> {
//    @NotNull P newServerProtocol(
//        @NotNull Function<C, HttpExchange> httpExchangeFactory,
//        @NotNull FormatReader.Factory<OpInputFormatReader> opInputFormatReaderFactory,
//        @NotNull FormatReader.Factory<ReqInputFormatReader> reqInputFormatReaderFactory,
//        @NotNull FormatReader.Factory<ReqUpdateFormatReader> reqUpdateFormatReaderFactory,
//        @NotNull FormatWriter.Factory formatWriterFactory);
//  }
}