package org.moreunit

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

/**
 * Created with IntelliJ IDEA.
 * User: vera
 * Date: 24.10.12
 * Time: 21:05
 * To change this template use File | Settings | File Templates.
 */
class ClassMatcherTest extends FunSpec with ShouldMatchers {

  describe("ClassMatcher for Test")
  {
    it("should accept suffix")
    {
      val matcher = new ClassMatcher("NameTest.java", "NameTest", ".java")
      matcher should be ('matches)
    }
  }

}
