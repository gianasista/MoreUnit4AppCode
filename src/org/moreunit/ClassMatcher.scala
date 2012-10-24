package org.moreunit

/**
 * Created with IntelliJ IDEA.
 * User: vera
 * Date: 15.10.12
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
class ClassMatcher (filename: String, name: String, fileextension: String)
{
  def matches: Boolean =
  {
       filename == name+fileextension
  }
}
