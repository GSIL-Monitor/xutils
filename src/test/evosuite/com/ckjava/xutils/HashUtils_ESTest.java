/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 15 10:41:32 GMT 2018
 */

package com.ckjava.xutils;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.ckjava.xutils.HashUtils;
import java.io.File;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.evosuite.runtime.testdata.EvoSuiteFile;
import org.evosuite.runtime.testdata.FileSystemHandling;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class HashUtils_ESTest extends HashUtils_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      HashUtils.digestString("+8p 5h3)[Z2o]I", "12165c48");
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      MockFile mockFile0 = new MockFile("");
      HashUtils.digestFile(mockFile0, "12165c48");
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      byte[] byteArray0 = new byte[0];
      HashUtils.byte2HexString(byteArray0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      // Undeclared exception!
      try { 
        HashUtils.byte2HexString((byte[]) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.HashUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      byte[] byteArray0 = new byte[4];
      HashUtils.byte2HexString(byteArray0);
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      EvoSuiteFile evoSuiteFile0 = new EvoSuiteFile("SHA-384012165c48");
      FileSystemHandling.appendLineToFile(evoSuiteFile0, "");
      MockFile mockFile0 = new MockFile("");
      File file0 = MockFile.createTempFile("SHA-384", "12165c48", (File) mockFile0);
      String string0 = HashUtils.digestFile(file0, "SHA-384");
      assertNotNull(string0);
      assertEquals("ec664e889ed6c1b2763cacf7899d95b7f347373eb982e523419feea3aa362d891b3bf025f292267a5854049091789c3e", string0);
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      String string0 = HashUtils.digestString("\r\n", "SHA-256");
      assertNotNull(string0);
      assertEquals("7eb70257593da06f682a3ddda54a9d260d4fc514f645237f5ca74b08f8da61a6", string0);
  }

  @Test(timeout = 4000)
  public void test7()  throws Throwable  {
      HashUtils hashUtils0 = new HashUtils();
      HashUtils.HASHTYPE hashUtils_HASHTYPE0 = hashUtils0.new HASHTYPE();
  }
}
