package io.epigraph.projections.req.output;

import io.epigraph.lang.TextLocation;
import io.epigraph.projections.generic.GenericTagProjectionEntry;
import io.epigraph.types.Type;
import org.jetbrains.annotations.NotNull;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class ReqOutputTagProjectionEntry extends GenericTagProjectionEntry<ReqOutputModelProjection<?>> {
  public ReqOutputTagProjectionEntry(
      @NotNull Type.Tag tag,
      @NotNull ReqOutputModelProjection<?> projection,
      @NotNull TextLocation location) {
    super(tag, projection, location);
  }
}
