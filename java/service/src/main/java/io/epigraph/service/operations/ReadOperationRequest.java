package io.epigraph.service.operations;

import io.epigraph.projections.req.output.ReqOutputFieldProjection;
import io.epigraph.projections.req.path.ReqVarPath;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class ReadOperationRequest extends OperationRequest {
  public ReadOperationRequest(
      final @Nullable ReqVarPath path,
      final @NotNull ReqOutputFieldProjection outputProjection) {
    super(path, outputProjection);
  }
}
