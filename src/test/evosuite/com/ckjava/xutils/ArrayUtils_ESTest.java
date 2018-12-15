/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 15 10:35:16 GMT 2018
 */

package com.ckjava.xutils;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.ckjava.xutils.ArrayUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class ArrayUtils_ESTest extends ArrayUtils_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      String[] stringArray0 = new String[5];
      ArrayUtils.getValue(stringArray0, 0, "");
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      Object[] objectArray0 = new Object[2];
      ArrayUtils.getValue(objectArray0, 1, (Object) "");
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      ArrayUtils arrayUtils0 = new ArrayUtils();
      String[][] stringArray0 = new String[3][6];
      stringArray0[0] = arrayUtils0.EMPTY_STRING_ARRAY;
      stringArray0[1] = arrayUtils0.EMPTY_STRING_ARRAY;
      stringArray0[2] = arrayUtils0.EMPTY_STRING_ARRAY;
      ArrayUtils.merge(stringArray0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      String[] stringArray0 = new String[5];
      stringArray0[0] = "";
      stringArray0[1] = "";
      stringArray0[2] = "";
      stringArray0[3] = "";
      stringArray0[4] = "";
      ArrayUtils.join(stringArray0, "");
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      Object[] objectArray0 = new Object[3];
      Object object0 = new Object();
      objectArray0[0] = object0;
      ArrayUtils.getValue(objectArray0, 0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      Integer[] integerArray0 = new Integer[4];
      ArrayUtils.getSize((Object[]) integerArray0);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      Object[][] objectArray0 = new Object[8][6];
      objectArray0[0] = null;
      // Undeclared exception!
      try { 
        ArrayUtils.merge(objectArray0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("com.ckjava.xutils.ArrayUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      ArrayUtils arrayUtils0 = new ArrayUtils();
      Object[][] objectArray0 = new Object[4][8];
      objectArray0[0] = (Object[]) arrayUtils0.EMPTY_BOOLEAN_OBJECT_ARRAY;
      Object[] objectArray1 = new Object[7];
      objectArray1[0] = (Object) arrayUtils0.INDEX_NOT_FOUND;
      objectArray0[1] = objectArray1;
      // Undeclared exception!
      try { 
        ArrayUtils.merge(objectArray0);
        fail("Expecting exception: ArrayStoreException");
      
      } catch(ArrayStoreException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.util.ArrayList", e);
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      Integer[] integerArray0 = new Integer[7];
      // Undeclared exception!
      try { 
        ArrayUtils.join(integerArray0, (String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      ArrayUtils arrayUtils0 = new ArrayUtils();
      int int0 = ArrayUtils.getSize((Object[]) arrayUtils0.EMPTY_STRING_ARRAY);
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      Object[] objectArray0 = new Object[4];
      Object object0 = ArrayUtils.getValue(objectArray0, 1);
      assertNull(object0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      String[][] stringArray0 = new String[1][8];
      String[] stringArray1 = ArrayUtils.merge(stringArray0);
      assertNotNull(stringArray1);
      assertEquals(8, stringArray1.length);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      Object[][] objectArray0 = new Object[0][5];
      Object[] objectArray1 = ArrayUtils.merge(objectArray0);
      assertNull(objectArray1);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      String[] stringArray0 = new String[0];
      String string0 = ArrayUtils.join(stringArray0, "E[9fBUc`EGz");
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      Integer[] integerArray0 = new Integer[1];
      String string0 = ArrayUtils.join((Object[]) integerArray0, "|3");
      assertNotNull(string0);
      assertEquals("null", string0);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      String string0 = ArrayUtils.join((Object[]) null, "pTF+~)fe");
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      int int0 = ArrayUtils.getSize((Object[]) null);
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      String[] stringArray0 = new String[4];
      stringArray0[2] = "|3";
      String string0 = ArrayUtils.getValue(stringArray0, 2, "|3");
      assertEquals("|3", string0);
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      String[] stringArray0 = new String[4];
      String string0 = ArrayUtils.getValue(stringArray0, (-1822), "|3");
      assertEquals("|3", string0);
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      ArrayUtils arrayUtils0 = new ArrayUtils();
      Object object0 = ArrayUtils.getValue((Object[]) arrayUtils0.EMPTY_BYTE_OBJECT_ARRAY, 0, (Object) arrayUtils0.INDEX_NOT_FOUND);
      assertEquals((-1), object0);
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      Object[] objectArray0 = new Object[5];
      Integer[] integerArray0 = new Integer[1];
      Integer integer0 = new Integer(2944);
      integerArray0[0] = integer0;
      Object object0 = ArrayUtils.getValue(objectArray0, (int) integerArray0[0], (Object) integerArray0[0]);
      assertTrue(object0.equals((Object)integer0));
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      Object object0 = ArrayUtils.getValue((Object[]) null, (-1822), (Object) null);
      assertNull(object0);
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      Integer[] integerArray0 = new Integer[1];
      Integer integer0 = ArrayUtils.getValue(integerArray0, (-1822));
      assertNull(integer0);
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      Object[] objectArray0 = new Object[5];
      Integer[] integerArray0 = new Integer[1];
      Integer integer0 = new Integer(2944);
      integerArray0[0] = integer0;
      Object object0 = ArrayUtils.getValue(objectArray0, (int) integerArray0[0]);
      assertNull(object0);
  }

  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      Object object0 = ArrayUtils.getValue((Object[]) null, 0);
      assertNull(object0);
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      ArrayUtils arrayUtils0 = new ArrayUtils();
      Object object0 = ArrayUtils.getValue((Object[]) arrayUtils0.EMPTY_CLASS_ARRAY, (-1));
      assertNull(object0);
  }
}