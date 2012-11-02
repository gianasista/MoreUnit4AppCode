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
  val matcher = new ClassMatcher(source)

  def target: util.ArrayList[VirtualFile] =
  {
    val contentRoots = ProjectRootManager.getInstance(source.getProject).getContentRoots
    findVirtualFilesInListByName(contentRoots)
  }

  def findVirtualFilesInListByName(fileListe: Array[VirtualFile]): util.ArrayList[VirtualFile] =
  {
    var resultList = new util.ArrayList[VirtualFile]()
    for (virtualFile <- fileListe)
    {
      if (virtualFile.isDirectory)
        resultList.addAll(findVirtualFilesInListByName(virtualFile.getChildren))
      else if (matcher.matches(virtualFile))
        resultList.add(virtualFile)
    }
    return resultList
  }
}
