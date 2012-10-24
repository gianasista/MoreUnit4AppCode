package org.moreunit

import com.intellij.psi.PsiFile
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.roots.ProjectRootManager
;

/**
 * Created with IntelliJ IDEA.
 * User: vera
 * Date: 11.10.12
 * Time: 12:03
 * To change this template use File | Settings | File Templates.
 */
class JumpLogic (source: PsiFile) {

  val TestCaseSuffix = "Test"

  def target: VirtualFile =
  {
    val contentRoots = ProjectRootManager.getInstance(source.getProject).getContentRoots
    findVirtualFileInListByName(contentRoots, targetName)
  }

  def findVirtualFileInListByName(fileListe: Array[VirtualFile], name: String): VirtualFile =
  {
    for (virtualFile <- fileListe)
    {
      var result: VirtualFile = null

      if (virtualFile.isDirectory)
        result = findVirtualFileInListByName(virtualFile.getChildren, name)
      else if (new ClassMatcher(virtualFile.getName, name, fileExtensionFromSource).matches)
        result = virtualFile

      if (result != null)
        return result
    }

    return null
  }

  def targetName: String = if (isTestCase) nameOfCut else nameOfTest

  def filenameWithoutExtension: String =
  {
    val lastIndexOfDot = source.getName.lastIndexOf('.')
    source.getName.dropRight(source.getName.length - lastIndexOfDot)
  }

  def fileExtensionFromSource: String = {
    val lastIndexOfDot = source.getName.lastIndexOf('.')
    source.getName.drop(lastIndexOfDot)
  }

  def isTestCase: Boolean = filenameWithoutExtension.endsWith(TestCaseSuffix)

  def nameOfCut: String = filenameWithoutExtension.dropRight(TestCaseSuffix.length)

  def nameOfTest: String = filenameWithoutExtension+TestCaseSuffix

}
