package com.ckjava.xutils.test;

import org.junit.Assert;
import org.junit.Test;

import com.ckjava.xutils.ArrayUtils;
import com.ckjava.xutils.Constants;

public class TestArrayUtils extends ArrayUtils implements Constants {
	
	@Test
	public void TestGetValue() {
		String[] arr = {"1"};
		String str = getValue(arr, 1);
		Assert.assertNull(str);
		str = getValue(arr, 0);
		Assert.assertNotNull(str);
		str = getValue(null, 0);
		Assert.assertNull(str);
		str = getValue(arr, 0);
		Assert.assertEquals("1", str);
		
		Assert.assertTrue(getValue(null, 1, 1) == 1);
		Assert.assertTrue(getValue(arr, 0, "").equals("1"));
		Assert.assertTrue(getValue(arr, 3, "2").equals("2"));
		
		Assert.assertTrue(getValue(null, 1) == null);
		Assert.assertTrue(getValue(new Object[]{}, 1) == null);
		Assert.assertTrue(getValue(arr, 2) == null);
		Assert.assertTrue(getValue(arr, -1) == null);
		
		Assert.assertTrue(getValue(null, 1, null) == null);
		Assert.assertTrue(getValue(new Object[]{}, 1, null) == null);
		Assert.assertTrue(getValue(arr, 2, null) == null);
		Assert.assertTrue(getValue(arr, -1, null) == null);
	}
	
	@Test
	public void TestGetSize() {
		String[] arr = {"1"};
		Assert.assertEquals(1, getSize(arr));
	}
	
	@Test
	public void TestMerge() {
		Integer[] arr = {1, 2, 3, 4};
		Integer[] arr2 = {6, 9};
		
		Integer[] arr3 = {1, 2, 3, 4, 6, 9};
		Integer[] arr4 = {1};
		
		Integer[] data = merge(arr, arr2);
		Assert.assertEquals(data.length, arr.length + arr2.length);
		
		for (int i = 0; i < data.length; i++) {
			Assert.assertEquals(data[i], arr3[i]);
		}
		
		String[] data1 = {"hello ", "world"};
		String[] data2 = {" !"};
		String str = "hello world !";
		String[] afterMergeArr = merge(data1, data2);
		Assert.assertEquals(join(afterMergeArr, ""), str);
		
		Object[] temp = merge(new Object[]{});
		Assert.assertTrue(merge(null) == null);
		Assert.assertTrue(merge(temp) == temp);
		Assert.assertTrue(merge(arr4) == arr4);
	}
	
	@Test
	public void join() {
		Integer[] arr = {1, 2, 3, 4};
		String finalString = join(arr, SPLITER.COMMA);
		Assert.assertTrue(finalString.equals("1,2,3,4"));
		
		Integer[] arr2 = {1, 2, 3, 4};
		finalString = join(arr2, SPLITER.COLON);
		Assert.assertTrue(finalString.equals("1:2:3:4"));
		
		arr = null;
		finalString = join(arr, SPLITER.COMMA);
		Assert.assertTrue(finalString == null);
		
		Object[] objarr = { 1, 2, "3", "4" };
		finalString = join(objarr, SPLITER.COLON);
		Assert.assertTrue(finalString.equals("1:2:3:4"));
		
		Assert.assertTrue(join(null, SPLITER.COLON) == null);
		Assert.assertTrue(join(new Object[]{}, SPLITER.COLON) == null);
	}
	
	
}
