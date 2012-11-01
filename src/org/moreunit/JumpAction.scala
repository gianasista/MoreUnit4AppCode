package org.moreunit

import com.intellij.openapi.actionSystem.{AnActionEvent, AnAction}
import com.intellij.openapi.project.Project
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.psi.{PsiDocumentManager, PsiFile}
import com.intellij.openapi.ui.popup.PopupChooserBuilder
import javax.swing.JList

/**
 * Created with IntelliJ IDEA.
 * User: vera
 * Date: 15.10.12
 * Time: 12:41
 * To change this template use File | Settings | File Templates.
 */
class JumpAction extends AnAction {

  def actionPerformed(event: AnActionEvent): Unit =
  {
    val project = event.getProject
    val editor = FileEditorManager.getInstance(project).getSelectedTextEditor
    val file = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument)

    val jumper = new JumpLogic(file)
    val target = jumper.target

    //if (target.isEmpty)
    //  FileEditorManager.getInstance(project).openFile(target.get(0), true)

    val dialog = new ChooseDialog(project, target)
    dialog.show
  }
}
