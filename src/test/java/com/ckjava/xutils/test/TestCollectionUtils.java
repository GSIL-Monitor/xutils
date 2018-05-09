package com.ckjava.xutils.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ckjava.xutils.CollectionUtils;

public class TestCollectionUtils extends CollectionUtils {
	
	List<String> srcList = new ArrayList<String>();
	Map<String, Boolean> dataMap = new HashMap<String, Boolean>();
	
	@Before
	public void initTest() {
		srcList.add("1");
		srcList.add(null);
		srcList.add("3");
		srcList.add("3");
		
		dataMap.put("flag-1", true);
	}
	
	@Test
	public void TestFormatListELement() {
		List<Integer> destList = new ArrayList<Integer>();
		formatListELement(srcList, destList);
		
		Assert.assertTrue(getSize(srcList) == getSize(destList));
		for (int i = 0; i < destList.size(); i++) {
			Integer dest = destList.get(i);
			String src = srcList.get(i);
			if (dest == null) {
				Assert.assertTrue(src == null);
			} else {
				Assert.assertTrue(dest.toString().equals(src));
			}
		}
		
		formatListELement(null, destList);
		formatListELement(srcList, null);
	}
	
	@Test 
	public void testGetSize() {
		Assert.assertTrue(0 == getSize(null));
		Assert.assertTrue(srcList.size() == getSize(srcList));
	}
	
	@Test
	public void testGetVal() {
		Assert.assertTrue(getVal(dataMap, "flag-1"));
		Assert.assertTrue(getVal(dataMap, "NULL") == null);
		Assert.assertTrue(getVal(dataMap, null) == null);
		Assert.assertTrue(getVal(null, "flag-1") == null);
	}
	
	@Test
	public void testAsHashMap() {
		String[] keys = {"key-1", "key-2", "key-3"};
		String[] values = {"val-1", "val-2", "val-3"};
		
		HashMap<String, String> data = asHashMap(keys, values);
		for (Iterator<Entry<String, String>> it = data.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			String key = entry.getKey();
			String value = entry.getValue();
			
			int index = getIndexInArr(keys, key);
			
			
			Assert.assertTrue(key.equals(keys[index]));
			Assert.assertTrue(value.equals(values[index]));
			
		}
		
		Assert.assertTrue(asHashMap(null, null) == null);
		Assert.assertTrue(asHashMap(keys, null) == null);
		Assert.assertTrue(asHashMap(null, values) == null);
	}

	private int getIndexInArr(String[] keys, String key) {
		for (int i = 0; i < keys.length; i++) {
			if (keys[i].equals(key)) {
				return i;
			}
		}
		return -1;
	}
	
	@Test
	public void testAsMap() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		String[] keys = {"key-1", "key-2", "key-3"};
		String[] values = {"val-1", "val-2", "val-3"};
		
		LinkedHashMap<String, String> data = asMap(keys, values, map);
		int index = 0;
		for (Iterator<Entry<String, String>> it = data.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			Assert.assertTrue(entry.getKey().equals(keys[index]));
			Assert.assertTrue(entry.getValue().equals(values[index]));
			
			index ++;
		}
		
		Assert.assertTrue(asMap(null, null, map) == null);
		Assert.assertTrue(asMap(keys, null, map) == null);
		Assert.assertTrue(asMap(null, values, map) == null);
		Assert.assertTrue(asMap(keys, values, null) == null);
	}
	
}
