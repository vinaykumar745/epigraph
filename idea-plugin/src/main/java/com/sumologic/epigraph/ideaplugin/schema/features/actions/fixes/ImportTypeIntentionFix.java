package com.sumologic.epigraph.ideaplugin.schema.features.actions.fixes;

import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.daemon.impl.ShowAutoImportPass;
import com.intellij.codeInsight.hint.HintManager;
import com.intellij.codeInspection.HintAction;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.PopupChooserBuilder;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.ui.components.JBList;
import com.intellij.util.IncorrectOperationException;
import com.sumologic.epigraph.ideaplugin.schema.features.actions.ImportTypeAction;
import com.sumologic.epigraph.ideaplugin.schema.features.actions.SchemaNamespaceRenderer;
import com.sumologic.epigraph.ideaplugin.schema.index.SchemaIndexUtil;
import com.sumologic.epigraph.ideaplugin.schema.options.SchemaSettings;
import com.sumologic.epigraph.schema.parser.Fqn;
import com.sumologic.epigraph.schema.parser.psi.SchemaFile;
import com.sumologic.epigraph.schema.parser.psi.SchemaFqnTypeRef;
import com.sumologic.epigraph.schema.parser.psi.SchemaTypeDef;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:konstantin@sumologic.com">Konstantin Sobolev</a>
 */
public class ImportTypeIntentionFix implements HintAction {
  // TODO(low) implement LocalQuickFix, see CreateNSDeclarationIntentionFix

  private final SchemaFqnTypeRef typeRef;

  public ImportTypeIntentionFix(SchemaFqnTypeRef typeRef) {
    this.typeRef = typeRef;
  }

  @Override
  public boolean showHint(@NotNull Editor editor) {
    if (!SchemaSettings.getInstance().SHOW_EPIGRAPH_SCHEMA_ADD_IMPORT_HINTS) return false;
    if (typeRef.resolve() != null) return false;

    List<String> importOptions = calculateImportOptions();
    if (importOptions.isEmpty()) return false;

    final String message = ShowAutoImportPass.getMessage(importOptions.size() > 1, importOptions.get(0));
    final ImportTypeAction action = new ImportTypeAction((SchemaFile) typeRef.getContainingFile(), importOptions, editor);
    HintManager.getInstance().showQuestionHint(editor, message,
        typeRef.getTextOffset(),
        typeRef.getTextRange().getEndOffset(), action);

    return false;
  }

  @Nls
  @NotNull
  @Override
  public String getText() {
    return "Import epigraph namespace";
  }

  @Nls
  @NotNull
  @Override
  public String getFamilyName() {
    return getText();
  }

  @Override
  public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
    return typeRef != null && typeRef.isValid() && typeRef.resolve() == null;
  }

  @Override
  public void invoke(@NotNull Project project, Editor editor, PsiFile file) throws IncorrectOperationException {
    if (!FileModificationService.getInstance().prepareFileForWrite(file)) return;
    if (typeRef.resolve() != null) return;

    final List<String> importOptions = calculateImportOptions();
    if (importOptions.isEmpty()) return;

    if (importOptions.size() > 1) {
      final JList list = new JBList(importOptions);
      //noinspection unchecked
      list.setCellRenderer(SchemaNamespaceRenderer.INSTANCE);

      Runnable runnable = () -> {
        final int index = list.getSelectedIndex();
        if (index < 0) return;
        PsiDocumentManager.getInstance(project).commitAllDocuments();

        CommandProcessor.getInstance().executeCommand(project, () ->
                ApplicationManager.getApplication().runWriteAction(() ->
                    {
                      ImportTypeAction.addImport((SchemaFile) file, importOptions.get(index));
                    }
                ),
            getText(), getFamilyName()
        );
      };

      new PopupChooserBuilder(list).
          setTitle("Select namespace to import").
          setItemChoosenCallback(runnable).
          createPopup().
          showInBestPositionFor(editor);
    } else {
      ImportTypeAction.addImport((SchemaFile) file, importOptions.get(0));
    }
  }

  @Override
  public boolean startInWriteAction() {
    return true;
  }

  ////

  private List<String> calculateImportOptions() {
    final Fqn typeRefFqn = typeRef.getFqn().getFqn();
    final int tailSegmentsToRemove = typeRefFqn.size() == 1 ? 0 : typeRefFqn.size() - 1;

    return SchemaIndexUtil.findTypeDefs(typeRef.getProject(), null, typeRefFqn, null).stream()
        .map(SchemaTypeDef::getFqn)
        .filter(fqn -> fqn != null)
        .map(fqn -> fqn.removeTailSegments(tailSegmentsToRemove).toString())
        .sorted()
        .distinct()
        .collect(Collectors.toList());
  }
}