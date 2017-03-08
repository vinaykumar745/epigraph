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

package ws.epigraph.projections.abs;

import de.uka.ilkd.pp.Layouter;
import org.jetbrains.annotations.Nullable;
import ws.epigraph.gdata.GDataPrettyPrinter;
import ws.epigraph.lang.Keywords;
import ws.epigraph.lang.Qn;
import ws.epigraph.projections.Annotation;
import ws.epigraph.projections.Annotations;
import ws.epigraph.projections.ProjectionsPrettyPrinterContext;
import ws.epigraph.projections.gen.GenModelProjection;
import ws.epigraph.projections.gen.GenTagProjectionEntry;
import ws.epigraph.projections.gen.GenVarProjection;
import ws.epigraph.types.TypeKind;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public abstract class AbstractProjectionsPrettyPrinter<
    VP extends GenVarProjection<VP, TP, MP>,
    TP extends GenTagProjectionEntry<TP, MP>,
    MP extends GenModelProjection</*MP*/?, ?, ?, ?>,
    E extends Exception> {

  protected final @NotNull Layouter<E> l;
  protected final @NotNull GDataPrettyPrinter<E> gdataPrettyPrinter;
  protected final @NotNull ProjectionsPrettyPrinterContext<VP> context;

  private final Collection<String> visitedRefs = new HashSet<>();

  protected AbstractProjectionsPrettyPrinter(
      final @NotNull Layouter<E> layouter,
      final @NotNull ProjectionsPrettyPrinterContext<VP> context) {
    l = layouter;
    this.context = context;
    gdataPrettyPrinter = new GDataPrettyPrinter<>(l);
  }

  public void addVisitedRefs(@NotNull Collection<String> names) {
    visitedRefs.addAll(names);
  }

  public final void print(@NotNull VP p, int pathSteps) throws E {
    final Qn name = p.name();

    boolean shouldPrint = true;

    if (name != null) {
      String shortName = name.last();

      if (!context.inNamespace(name)) {
        context.addOtherNamespaceProjection(p);
        l.print("$").print(shortName);
        shouldPrint = false;
      } else if (visitedRefs.contains(shortName)) {
        l.print("$").print(shortName);
        shouldPrint = false;
      } else {
        visitedRefs.add(shortName);
        l.print("$").print(shortName).print(" = ");
      }
    }

    if (shouldPrint)
      printVarNoRefCheck(p, pathSteps);
  }

  public final void printVarNoRefCheck(@NotNull VP p, int pathSteps) throws E {
    printVarOnly(p, pathSteps);
    printTailsOnly(p);
  }

  protected void printVarOnly(@NotNull VP p, int pathSteps) throws E {
    printVarDecoration(p);
    Map<String, TP> tagProjections = p.tagProjections();
    if (p.type().kind() != TypeKind.UNION) {
      // samovar
      TP tp = tagProjections.values().iterator().next();
      print(null, tp, decSteps(pathSteps));
    } else if (!p.parenthesized()) {
      Map.Entry<String, TP> entry = tagProjections.entrySet().iterator().next();
      l.print(":");
      print(entry.getKey(), entry.getValue(), decSteps(pathSteps));
    } else if (tagProjections.isEmpty()) {
      l.print(":()");
    } else {
      if (pathSteps > 0) throw new IllegalArgumentException(
          String.format(
              "found %d var tags and parenthesized = %b while path still contains %d steps",
              tagProjections.size(),
              p.parenthesized(),
              pathSteps
          )
      );
      l.beginCInd();
      l.print(":(");
      boolean first = true;
      for (Map.Entry<String, TP> entry : tagProjections.entrySet()) {
        if (first) first = false;
        else l.print(",");
        l.brk();
        print(entry.getKey(), entry.getValue(), 0);
      }
      l.brk(1, -l.getDefaultIndentation()).end().print(")");
    }
  }

  protected void printVarDecoration(@NotNull VP p) throws E { }

  private void printTailsOnly(@NotNull VP p) throws E {
    Collection<VP> polymorphicTails = p.polymorphicTails();

    if (polymorphicTails != null && !polymorphicTails.isEmpty()) {
      l.beginIInd();
      l.brk();
      if (polymorphicTails.size() == 1) {
        l.print("~~");
        VP tail = polymorphicTails.iterator().next();
        l.print(tail.type().name().toString());
        l.brk();
        print(tail, 0);
      } else {
        l.beginCInd();
        l.print("~~(");
        boolean first = true;
        for (VP tail : polymorphicTails) {
          if (first) first = false;
          else l.print(",");
          l.brk();
          l.beginIInd(0);
          l.print(tail.type().name().toString()).brk();
          print(tail, 0);
          l.end();
        }
        l.brk(1, -l.getDefaultIndentation()).end().print(")");
      }
      l.end();
    }
  }

  public abstract void print(@Nullable String tagName, @NotNull TP tp, int pathSteps) throws E;

  public void print(@NotNull MP mp, int pathSteps) throws E {
    // check recursion?
    printModelOnly(mp, pathSteps);
    printModelTailsOnly(mp);
  }

  public abstract void printModelOnly(@NotNull MP mp, int pathSteps) throws E;

  @SuppressWarnings("unchecked") // todo introduce TMP extends MP
  private void printModelTailsOnly(@NotNull MP p) throws E {
    List<MP> polymorphicTails = (List<MP>) p.polymorphicTails();

    if (polymorphicTails != null && !polymorphicTails.isEmpty()) {
      l.beginIInd();
      l.brk();
      if (polymorphicTails.size() == 1) {
        l.print("~");
        MP tail = polymorphicTails.iterator().next();
        l.print(modelTailTypeNamePrefix(tail));
        l.print(tail.model().name().toString());
        l.brk();
        print(tail, 0);
      } else {
        l.beginCInd();
        l.print("~(");
        boolean first = true;
        for (MP tail : polymorphicTails) {
          if (first) first = false;
          else l.print(",");
          l.brk();
          l.beginIInd(0);
          l.print(modelTailTypeNamePrefix(tail));
          l.print(tail.model().name().toString()).brk();
          print(tail, 0);
          l.end();
        }
        l.brk(1, -l.getDefaultIndentation()).end().print(")");
      }
      l.end();
    }
  }

  protected String modelTailTypeNamePrefix(@NotNull MP mp) {
    return "";
  }

  public void print(@NotNull Annotations cp) throws E {
    print(cp, false, true);
  }

  public boolean print(@NotNull Annotations cp, boolean needCommas, boolean first) throws E {
    for (Map.Entry<String, Annotation> entry : cp.asMap().entrySet()) {
      if (needCommas) {
        if (first) first = false;
        else l.print(",");
      }
      l.brk();
      l.beginCInd(0);
      l.print(entry.getKey()).brk().print("=").brk();
      gdataPrettyPrinter.print(entry.getValue().value());
      l.end();
    }

    return first;
  }

  protected boolean isPrintoutEmpty(@NotNull VP vp) {

    Collection<VP> tails = vp.polymorphicTails();
    if (tails != null && !tails.isEmpty()) return false;
    if (vp.type().kind() == TypeKind.UNION) return false; // non-samovar always prints something

    for (TP tagProjection : vp.tagProjections().values()) {
      final MP modelProjection = tagProjection.projection();
      if (!isPrintoutEmpty(modelProjection)) return false;
      if (!modelProjection.annotations().isEmpty()) return false;
    }

    return true;
  }

  protected @NotNull String escape(@NotNull String s) { return Keywords.schema.escape(s); }

  public abstract boolean isPrintoutEmpty(@NotNull MP mp);

  protected int decSteps(int pathSteps) {
    return pathSteps == 0 ? 0 : pathSteps - 1;
  }
}
