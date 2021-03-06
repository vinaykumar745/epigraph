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

package ws.epigraph.ideaplugin.schema.features.rename;

import com.intellij.codeInsight.navigation.NavigationUtil;
import com.intellij.ide.util.PsiElementListCellRenderer;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.Query;
import com.intellij.util.containers.ConcurrentList;
import com.intellij.util.containers.ContainerUtil;
import ws.epigraph.ideaplugin.schema.SchemaBundle;
import ws.epigraph.ideaplugin.schema.brains.hierarchy.SchemaDirectTypeParentsSearch;
import ws.epigraph.ideaplugin.schema.presentation.SchemaPresentationUtil;
import ws.epigraph.schema.parser.psi.SchemaFieldDecl;
import ws.epigraph.schema.parser.psi.SchemaRecordTypeBody;
import ws.epigraph.schema.parser.psi.SchemaRecordTypeDef;
import ws.epigraph.schema.parser.psi.SchemaTypeDef;
import ws.epigraph.schema.parser.psi.impl.SchemaElementFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:konstantin.sobolev@gmail.com">Konstantin Sobolev</a>
 */
public class SchemaRenameUtil {
  private static final Set<PsiNamedElement> superMembersToRename = new HashSet<>();

  public static PsiNamedElement chooseSuper(@NotNull PsiNamedElement element) {
    FindFirstProcessor processor = new FindFirstProcessor();
    chooseAndProcessSuper(element, processor, null);
    return processor.getFound();
  }

  public static void chooseAndProcessSuper(@NotNull PsiNamedElement element,
                                           @NotNull PsiElementProcessor<PsiNamedElement> processor,
                                           @Nullable Editor editor) {

    List<PsiNamedElement> maxSuperMembers = findMaxSuperMembers(element);
    afterChoosingSuperMember(element, maxSuperMembers, editor, processor);
  }

  private static void afterChoosingSuperMember(@NotNull PsiNamedElement member,
                                               @NotNull List<PsiNamedElement> maxSuperMembers,
                                               @Nullable Editor editor,
                                               @NotNull PsiElementProcessor<PsiNamedElement> action) {
    if (maxSuperMembers.isEmpty()) {
      action.execute(member);
      return;
    }

    final List<PsiNamedElement> memberAndMaxSuperMembers = new ArrayList<>(maxSuperMembers);
    memberAndMaxSuperMembers.add(member);

    final List<SchemaTypeDef> typeAndMaxSuperTypes = memberAndMaxSuperMembers.stream()
        .map(e -> PsiTreeUtil.getParentOfType(e, SchemaTypeDef.class, false))
        .collect(Collectors.toList());

    // no zippers in java 8..
    final Map<SchemaTypeDef, PsiNamedElement> typesToMembers = memberAndMaxSuperMembers.stream()
        .collect(Collectors.toMap(
            t -> PsiTreeUtil.getParentOfType(t, SchemaTypeDef.class, false),
            Function.identity())
        );
    final boolean onlyOneSuperType = typeAndMaxSuperTypes.size() == 1;

    final SchemaTypeDef renameAllMarkerObject = SchemaElementFactory.createRecordTypeDef(member.getProject(), "renameAll");
    typesToMembers.put(renameAllMarkerObject, null);

    // should map maintain order?
    // selection = renameAllMarkerObject ?

    final PsiElementProcessor<SchemaTypeDef> processor = typeDef -> {
      if (typeDef == renameAllMarkerObject) {
        PsiNamedElement element = typesToMembers.get(typeDef);
        // TODO element is actually null
        action.execute(element);
      } else {
        PsiNamedElement mainOne = typesToMembers.get(typeAndMaxSuperTypes.iterator().next());
        superMembersToRename.clear();
        Iterator<SchemaTypeDef> it = typeAndMaxSuperTypes.iterator();
        it.next(); // drop first one
        while (it.hasNext()) {
          SchemaTypeDef td = it.next();
          if (it.hasNext()) superMembersToRename.add(typesToMembers.get(td)); // drop last one
        }
        action.execute(mainOne);
      }
      return false;
    };

    final String renameAllText = SchemaBundle.message("rename.all.base.members");
    final String renameBase = SchemaBundle.message("rename.base.member");
    final String renameOnlyCurrent = SchemaBundle.message("rename.only.current.member");
    final String name = member.getName();
    final String title = onlyOneSuperType ?
        SchemaBundle.message("rename.overrides.member", name,
            SchemaPresentationUtil.getName(typeAndMaxSuperTypes.iterator().next(), true)) :
        SchemaBundle.message("rename.has.multiple.base.members", name);


    final SchemaTypeDef[] typesPlusMarker = typeAndMaxSuperTypes.toArray(new SchemaTypeDef[typeAndMaxSuperTypes.size() + 1]);
    typesPlusMarker[typesPlusMarker.length - 1] = renameAllMarkerObject;
    reverse(typesPlusMarker);

    final JBPopup popup = NavigationUtil.getPsiElementPopup(typesPlusMarker, new PsiElementListCellRenderer<SchemaTypeDef>() {
      @Override
      protected Icon getIcon(PsiElement element) {
        return SchemaPresentationUtil.getIcon(element);
      }

      @Override
      public String getElementText(SchemaTypeDef t) {
        if (t == renameAllMarkerObject) return renameAllText;

        if (t == typeAndMaxSuperTypes.get(typeAndMaxSuperTypes.size() - 1)) return renameOnlyCurrent;
        else if (onlyOneSuperType) return renameBase;
        else return SchemaBundle.message("rename.only.in", t.getKind().name, SchemaPresentationUtil.getName(t, false));
      }

      @Nullable
      @Override
      protected String getContainerText(SchemaTypeDef t, String name) {
        if (t == renameAllMarkerObject || t == typeAndMaxSuperTypes.get(typeAndMaxSuperTypes.size() - 1) || onlyOneSuperType)
          return null;
        return SchemaPresentationUtil.getNamespaceString(t, true);
      }

      @Override
      protected int getIconFlags() {
        return 0;
      }
    }, title, processor, renameAllMarkerObject);

    if (ApplicationManager.getApplication().isUnitTestMode()) {
      processor.execute(onlyOneSuperType ? typeAndMaxSuperTypes.iterator().next() : renameAllMarkerObject);
      return;
    }
    if (editor != null) popup.showInBestPositionFor(editor);
    else popup.showInFocusCenter();
  }

  private static List<PsiNamedElement> findMaxSuperMembers(@NotNull PsiNamedElement element) {
    SchemaTypeDef currentType;
    Function<SchemaTypeDef, PsiNamedElement> typeToMemberFunc;

    if (element instanceof SchemaFieldDecl) {
      SchemaFieldDecl fieldDecl = (SchemaFieldDecl) element;
      currentType = fieldDecl.getRecordTypeDef();
      String name = element.getName();
      assert name != null;
      typeToMemberFunc = new HasFieldFilter(name);
    } // else member tag
    else throw new IllegalArgumentException("Unsupported element: " + element);

    final ConcurrentList<PsiNamedElement> res = ContainerUtil.createConcurrentList();
    addMaxSuperMembers(currentType, typeToMemberFunc, res);
    res.remove(currentType);

    return res;
  }

  private static void addMaxSuperMembers(@NotNull SchemaTypeDef currentType,
                                         @NotNull Function<SchemaTypeDef, PsiNamedElement> typeFunc,
                                         @NotNull List<PsiNamedElement> acc) {

    final int accSizeBefore = acc.size();

    Query<SchemaTypeDef> parentsQuery = SchemaDirectTypeParentsSearch.search(currentType);
    parentsQuery.forEach(typeDef -> {
      PsiNamedElement member = typeFunc.apply(typeDef);
      if (member != null) addMaxSuperMembers(typeDef, typeFunc, acc);

      return true;
    });

    if (acc.size() == accSizeBefore /*&& accSizeBefore > 0*/) acc.add(currentType);
  }


  private static class HasFieldFilter implements Function<SchemaTypeDef, PsiNamedElement> {
    @NotNull
    private final String fieldName;

    private HasFieldFilter(@NotNull String fieldName) {
      this.fieldName = fieldName;
    }

    @Override
    public PsiNamedElement apply(SchemaTypeDef typeDef) {
      if (typeDef instanceof SchemaRecordTypeDef) {
        SchemaRecordTypeDef recordTypeDef = (SchemaRecordTypeDef) typeDef;
        SchemaRecordTypeBody recordTypeBody = recordTypeDef.getRecordTypeBody();
        if (recordTypeBody == null) return null;

        for (SchemaFieldDecl fieldDecl : recordTypeBody.getFieldDeclList()) {
          if (fieldName.equals(fieldDecl.getName()))
            return fieldDecl;
        }
      }

      return null;
    }
  }

  private static class FindFirstProcessor implements PsiElementProcessor<PsiNamedElement> {
    @Nullable
    private PsiNamedElement found;

    @Nullable
    PsiNamedElement getFound() {
      return found;
    }

    @Override
    public boolean execute(@NotNull PsiNamedElement element) {
      found = element;
      return false;
    }
  }

  private static <T> void reverse(@NotNull final T[] a) {
    for (int i = 0; i < a.length / 2; i++) {
      T t = a[i];
      a[i] = a[a.length - i - 1];
      a[a.length - i - 1] = t;
    }
  }
}
