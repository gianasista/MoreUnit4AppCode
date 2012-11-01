package org.moreunit

import com.intellij.openapi.project.Project
import javax.swing.JList
import com.intellij.openapi.ui.popup.PopupChooserBuilder
import java.util
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.fileEditor.FileEditorManager

/**
 * Created with IntelliJ IDEA.
 * User: vera
 * Date: 23.10.12
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
class ChooseDialog (project: Project, fileList: util.ArrayList[VirtualFile]) extends Runnable
{
  val list = new JList(fileList.toArray())

  def show: Unit =
  {
    val size = fileList.size()
    size match
    {
      case 0 => return
      case 1 => FileEditorManager.getInstance(project).openFile(fileList.get(0), true)
      case _ =>
        val builder = new PopupChooserBuilder(list)
        builder.setTitle("Found tests")
        builder.setItemChoosenCallback(this)
        builder.createPopup().showCenteredInCurrentWindow(project)
    }
  }

  def run: Unit =
  {
    val selection = list.getSelectedValue.asInstanceOf[VirtualFile]
    FileEditorManager.getInstance(project).openFile(selection, true)
  }
}
