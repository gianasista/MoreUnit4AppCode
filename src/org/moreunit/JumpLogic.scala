package org.moreunit

import com.intellij.psi.PsiFile
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.roots.ProjectRootManager
import collection.mutable.ArrayBuffer
import java.util
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

  def target: util.ArrayList[VirtualFile] =
  {
    val contentRoots = ProjectRootManager.getInstance(source.getProject).getContentRoots
    findVirtualFilesInListByName(contentRoots, targetName)
  }

  def findVirtualFilesInListByName(fileListe: Array[VirtualFile], name: String): util.ArrayList[VirtualFile] =
  {
    var resultList = new util.ArrayList[VirtualFile]()
    for (virtualFile <- fileListe)
    {
      if (virtualFile.isDirectory)
        resultList.addAll(findVirtualFilesInListByName(virtualFile.getChildren, name))
      else if (new ClassMatcher(virtualFile.getName, name, fileExtensionFromSource).matches)
        resultList.add(virtualFile)
    }
    return resultList
  }

  def targetName: String = if (isTestCase) nameOfCut else nameOfTest

  def filenameWithoutExtension: String =
  {
    val lastIndexOfDot = source.getName.lastIndexOf('.')
    source.getName.dropRight(source.getName.length - lastIndexOfDot)
  }

  def fileExtensionFromSource: String =
  {
    val lastIndexOfDot = source.getName.lastIndexOf('.')
    source.getName.drop(lastIndexOfDot)
  }

  def isTestCase: Boolean = filenameWithoutExtension.endsWith(TestCaseSuffix)

  def nameOfCut: String = filenameWithoutExtension.dropRight(TestCaseSuffix.length)

  def nameOfTest: String = filenameWithoutExtension+TestCaseSuffix

}
