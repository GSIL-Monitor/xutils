/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 15 10:33:32 GMT 2018
 */

package com.ckjava.xutils;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.ckjava.xutils.HttpClientUtils;
import com.ckjava.xutils.http.HttpResult;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class HttpClientUtils_ESTest extends HttpClientUtils_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      HashMap<String, String> hashMap1 = new HashMap<String, String>();
      // Undeclared exception!
      try { 
        HttpClientUtils.delete(",ku4m5YDm", hashMap0, hashMap1);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.HttpClientUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      HashMap<String, String> hashMap1 = new HashMap<String, String>(1585);
      // Undeclared exception!
      try { 
        HttpClientUtils.get("", hashMap0, hashMap1);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.HttpClientUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      HashMap<String, String> hashMap1 = new HashMap<String, String>();
      // Undeclared exception!
      try { 
        HttpClientUtils.put("", hashMap0, hashMap1, (String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.HttpClientUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      hashMap0.put("", (String) null);
      HttpResult httpResult0 = HttpClientUtils.post("m^WrCjb\"AI/", hashMap0, hashMap0, "");
      assertNull(httpResult0.getBodyString());
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      hashMap0.put("", (String) null);
      HttpResult httpResult0 = HttpClientUtils.get("m^WrCjb\"AI/", hashMap0, hashMap0);
      assertNull(httpResult0.getBodyString());
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      hashMap0.put("", (String) null);
      HttpResult httpResult0 = HttpClientUtils.delete("m^WrCjb\"AI/", hashMap0, hashMap0);
      assertNull(httpResult0.getBodyString());
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      String string0 = HttpClientUtils.addParamValue((String) null, "", "uVr12~}m");
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      String string0 = HttpClientUtils.addParamValue("(x%_SUaj~d*D#PJA?)j", "q7)(S_r*a#ABhL=kfe:", "q7)(S_r*a#ABhL=kfe:");
      assertEquals("(x%_SUaj~d*D#PJA?)j", string0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      hashMap0.put((String) null, "cJ");
      // Undeclared exception!
      try { 
        HttpClientUtils.put("sze", hashMap0, hashMap0, "cJ");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      // Undeclared exception!
      try { 
        HttpClientUtils.post((String) null, hashMap0, hashMap0, (String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      // Undeclared exception!
      try { 
        HttpClientUtils.post("%v(;o&$[U", hashMap0, hashMap0, "%v(;o&$[U");
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Malformed escape pair at index 0: %v(;o&$[U
         //
         verifyException("java.net.URI", e);
      }
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      // Undeclared exception!
      try { 
        HttpClientUtils.isValidUrl((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      // Undeclared exception!
      try { 
        HttpClientUtils.get((String) null, hashMap0, hashMap0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      hashMap0.put((String) null, "5(5%E");
      // Undeclared exception!
      try { 
        HttpClientUtils.delete("", hashMap0, hashMap0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
      }
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      boolean boolean0 = HttpClientUtils.isValidUrl("6");
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      String string0 = HttpClientUtils.addParamValue("UTF-8", "com.ckjava.xutils.HttpClientUtils$1", (String) null);
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      String string0 = HttpClientUtils.addParamValue((String) null, (String) null, "6");
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      hashMap0.put((String) null, (String) null);
      // Undeclared exception!
      try { 
        HttpClientUtils.put("g=_CSeoDHA?P|", hashMap0, hashMap0, "g=_CSeoDHA?P|");
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Illegal character in query at index 12: g=_CSeoDHA?P|
         //
         verifyException("java.net.URI", e);
      }
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      hashMap0.put("jKsl", "~|");
      BiFunction<Object, Object, String> biFunction0 = (BiFunction<Object, Object, String>) mock(BiFunction.class, new ViolatedAssumptionAnswer());
      hashMap0.merge("*TQemt-;<jHI", "jKsl", biFunction0);
      // Undeclared exception!
      try { 
        HttpClientUtils.get("6", hashMap0, hashMap0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.HttpClientUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      hashMap0.put("content-type", (String) null);
      HashMap<String, String> hashMap1 = new HashMap<String, String>();
      // Undeclared exception!
      try { 
        HttpClientUtils.post("'FaQ", hashMap0, hashMap1, "'FaQ");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.HttpClientUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      hashMap0.put("'FaQ", "uqh8q%ss");
      // Undeclared exception!
      try { 
        HttpClientUtils.post("'FaQ", hashMap0, hashMap0, "'FaQ");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.HttpClientUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      // Undeclared exception!
      try { 
        HttpClientUtils.post("zh", (Map<String, String>) null, (Map<String, String>) null, "\" h2wQnn?3>");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.HttpClientUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      HttpClientUtils httpClientUtils0 = new HttpClientUtils();
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      // Undeclared exception!
      try { 
        HttpClientUtils.delete("m^WrCjb\"AI/", hashMap0, hashMap0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Illegal character in path at index 1: m^WrCjb\"AI/
         //
         verifyException("java.net.URI", e);
      }
  }

  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      // Undeclared exception!
      try { 
        HttpClientUtils.get("m^WrCjb\"AI/", hashMap0, hashMap0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Illegal character in path at index 1: m^WrCjb\"AI/
         //
         verifyException("java.net.URI", e);
      }
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      HashMap<String, String> hashMap0 = new HashMap<String, String>();
      // Undeclared exception!
      try { 
        HttpClientUtils.put("", hashMap0, hashMap0, "");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.HttpClientUtils", e);
      }
  }
}
