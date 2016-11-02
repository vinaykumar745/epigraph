package io.epigraph.service.operations;

import io.epigraph.data.Data;
import io.epigraph.projections.req.output.ReqOutputFieldProjection;
import io.epigraph.projections.req.path.ReqFieldPath;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class CustomOperationRequest extends OperationRequest {
  @NotNull
  private final Data data;

  public CustomOperationRequest(
      final @Nullable ReqFieldPath path,
      final @NotNull Data data,
      final @NotNull ReqOutputFieldProjection outputProjection) {
    super(path, outputProjection);
    this.data = data;
  }

  @NotNull
  public Data data() { return data; }
}
