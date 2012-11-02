package org.moreunit

import com.intellij.psi.PsiFile
import com.intellij.openapi.vfs.VirtualFile

/**
 * Created with IntelliJ IDEA.
 * User: vera
 * Date: 15.10.12
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
class ClassMatcher (source: PsiFile)
{
  val TestCaseSuffix = "Test"
  val fileExtension = fileExtensionFromSource
  val classname = filenameWithoutExtension

  def matches(target: VirtualFile): Boolean =
  {
    target.getName == targetName+fileExtension
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

  def isTestCase: Boolean = classname.endsWith(TestCaseSuffix)

  def nameOfCut: String = classname.dropRight(TestCaseSuffix.length)

  def nameOfTest: String = classname+TestCaseSuffix
}
