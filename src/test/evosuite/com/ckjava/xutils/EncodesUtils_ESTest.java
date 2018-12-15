/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 15 10:41:34 GMT 2018
 */

package com.ckjava.xutils;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.ckjava.xutils.EncodesUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class EncodesUtils_ESTest extends EncodesUtils_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      String string0 = EncodesUtils.urlEncode("");
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      String string0 = EncodesUtils.urlDecode("");
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      byte[] byteArray0 = EncodesUtils.hexString2Byte("");
      String string0 = EncodesUtils.encodeHex(byteArray0);
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      byte[] byteArray0 = EncodesUtils.hexString2Byte("");
      String string0 = EncodesUtils.encodeBase64(byteArray0);
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      String string0 = EncodesUtils.encodeBase64("");
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      byte[] byteArray0 = EncodesUtils.decodeHex("ff3cfffffc");
      assertEquals(5, byteArray0.length);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      byte[] byteArray0 = EncodesUtils.decodeHex("");
      assertArrayEquals(new byte[] {}, byteArray0);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      String string0 = EncodesUtils.decodeBase64String("");
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      byte[] byteArray0 = EncodesUtils.decodeBase64("");
      assertArrayEquals(new byte[] {}, byteArray0);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      byte[] byteArray0 = EncodesUtils.hexString2Byte("");
      String string0 = EncodesUtils.byte2HexString(byteArray0);
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      // Undeclared exception!
      try { 
        EncodesUtils.urlEncode((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.net.URLEncoder", e);
      }
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      // Undeclared exception!
      try { 
        EncodesUtils.urlDecode((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.net.URLDecoder", e);
      }
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      // Undeclared exception!
      try { 
        EncodesUtils.urlDecode(" %>%\"ZL)\"2[MyHM^t");
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // URLDecoder: Illegal hex characters in escape (%) pattern - For input string: \">%\"
         //
         verifyException("java.net.URLDecoder", e);
      }
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      // Undeclared exception!
      try { 
        EncodesUtils.hexString2Byte((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.EncodesUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      // Undeclared exception!
      try { 
        EncodesUtils.encodeHex((byte[]) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.codec.binary.Hex", e);
      }
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      // Undeclared exception!
      try { 
        EncodesUtils.encodeBase64((byte[]) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      // Undeclared exception!
      try { 
        EncodesUtils.encodeBase64((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.EncodesUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      // Undeclared exception!
      try { 
        EncodesUtils.decodeHex((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.EncodesUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      // Undeclared exception!
      try { 
        EncodesUtils.decodeBase64String((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.EncodesUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      // Undeclared exception!
      try { 
        EncodesUtils.decodeBase64((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.EncodesUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      // Undeclared exception!
      try { 
        EncodesUtils.byte2HexString((byte[]) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.EncodesUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      byte[] byteArray0 = EncodesUtils.hexString2Byte(" at index ");
      String string0 = EncodesUtils.byte2HexString(byteArray0);
      assertEquals("faff37deff", string0);
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      byte[] byteArray0 = EncodesUtils.decodeBase64("+v833v8=");
      assertArrayEquals(new byte[] {(byte) (-6), (byte) (-1), (byte)55, (byte) (-34), (byte) (-1)}, byteArray0);
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      String string0 = EncodesUtils.encodeBase64("faff37deff");
      assertEquals("ZmFmZjM3ZGVmZg==", string0);
  }

  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      String string0 = EncodesUtils.urlEncode("faff37deff");
      assertEquals("faff37deff", string0);
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      byte[] byteArray0 = EncodesUtils.hexString2Byte(" at index ");
      String string0 = EncodesUtils.encodeHex(byteArray0);
      assertEquals("faff37deff", string0);
  }

  @Test(timeout = 4000)
  public void test26()  throws Throwable  {
      String string0 = EncodesUtils.urlDecode("+v833v8=");
      assertEquals(" v833v8=", string0);
  }

  @Test(timeout = 4000)
  public void test27()  throws Throwable  {
      EncodesUtils encodesUtils0 = new EncodesUtils();
  }

  @Test(timeout = 4000)
  public void test28()  throws Throwable  {
      byte[] byteArray0 = EncodesUtils.hexString2Byte(" at index ");
      String string0 = EncodesUtils.encodeBase64(byteArray0);
      assertEquals("+v833v8=", string0);
  }

  @Test(timeout = 4000)
  public void test29()  throws Throwable  {
      String string0 = EncodesUtils.decodeBase64String("PZ1^.)Zfuz=/V[|U");
      assertEquals("=\uFFFDY~\uFFFD", string0);
  }

  @Test(timeout = 4000)
  public void test30()  throws Throwable  {
      try { 
        EncodesUtils.decodeHex("PZ1^.)Zfuz=/V[|U");
        fail("Expecting exception: Exception");
      
      } catch(Exception e) {
         //
         // Illegal hexadecimal character P at index 0
         //
         verifyException("org.apache.commons.codec.binary.Hex", e);
      }
  }
}
