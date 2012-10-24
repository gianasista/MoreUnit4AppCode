package org.moreunit

import com.intellij.openapi.project.Project
import javax.swing.JList
import com.intellij.openapi.ui.popup.PopupChooserBuilder

/**
 * Created with IntelliJ IDEA.
 * User: vera
 * Date: 23.10.12
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
class ChooseDialog (project: Project) extends Runnable
{
  val elements = new java.util.Vector[String]()
  elements.add("Test1")
  elements.add("Test2")
  val list = new JList(elements)

  def show: Unit =
  {
    val builder = new PopupChooserBuilder(list)
    builder.setTitle("Found tests")
    builder.setItemChoosenCallback(this)
    builder.createPopup().showCenteredInCurrentWindow(project)
  }

  def run: Unit =
  {
    val selection = list.getSelectedValue
    println(selection)
  }
}
