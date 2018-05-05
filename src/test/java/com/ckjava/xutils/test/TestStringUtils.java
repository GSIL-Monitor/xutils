package com.ckjava.xutils.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.ckjava.xutils.CollectionUtils;
import com.ckjava.xutils.StringUtils;

public class TestStringUtils extends StringUtils {
	public static void main(String[] args) {
		int s = -9;
		System.out.println(String.valueOf(s));
		
		System.out.println(Integer.class.getSimpleName());
		System.out.println(String.class.getSimpleName());
		System.out.println(Boolean.class.getSimpleName());
		System.out.println(Long.class.getSimpleName());
		System.out.println(Double.class.getSimpleName());
		System.out.println(Float.class.getSimpleName());
		System.out.println(java.sql.Date.class.getSimpleName());
		System.out.println(java.sql.Time.class.getSimpleName());
		System.out.println(java.sql.Timestamp.class.getSimpleName());
		System.out.println(byte[].class.getSimpleName());
		System.out.println(java.math.BigDecimal.class.getSimpleName());
		
	}
	
	@Test
	public void getStringByFilter() {
		String targetString = "{sdfsf10252sdf10252sfslf23y4nsfhispw3}sdfsfw23";
		assertTrue(getStringByFilter(targetString, "10252", "f", 1).equals("sd"));
		assertTrue(getStringByFilter(targetString, "10252", "f", 2).equals("s"));
		assertTrue(getStringByFilter(targetString, "10252", "f", 3).equals("s"));
		assertTrue(getStringByFilter(targetString, "10252", "f", 4).equals("s"));
		assertTrue(getStringByFilter(targetString, "sdfsfsdsfd34", "fsdf", 1).equals(EMPTY));
	}
	
	@Test
	public void replaceVariable() {
		String sourceString = "SELECT * FROM data_user WHERE group_id = ${group_id} AND del_flag = ${del_flag}";
		String targetString = "SELECT * FROM data_user WHERE group_id = '1' AND del_flag = '0'";
		Map<String, String> data = CollectionUtils.asHashMap(new String[] {"group_id", "del_flag"}, new String[] {"'1'", "'0'"});
		assertTrue(replaceVariable(sourceString, data).equals(targetString));
	}
	
	@Test
	public void containsStr() {
		String str = "a";
		String[] strs = {"a", "b"};
		assertTrue(containsStr(str, strs));
		assertTrue(containsStr(null, null));
		assertFalse(containsStr(null, strs));
		assertFalse(containsStr(str, null));
		assertFalse(containsStr("c", strs));
	}
	
	@Test
	public void containsStrIgnoreCase() {
		String str = "a";
		String[] strs = {"A", "b"};
		assertTrue(containsStrIgnoreCase(str, strs));
		assertTrue(containsStrIgnoreCase(null, null));
		assertFalse(containsStrIgnoreCase(null, strs));
		assertFalse(containsStrIgnoreCase(str, null));
		assertFalse(containsStrIgnoreCase("c", strs));
	}
	
	@Test
	public void objectHasStr() {
		String str = "abc";
		assertTrue(objectHasStr(str, "a"));
		assertFalse(objectHasStr(str, "d"));
		
		List<String> dataList = new ArrayList<>();
		dataList.add("a");
		dataList.add("b");
		
		assertTrue(objectHasStr(str, "a"));
		assertFalse(objectHasStr(str, "d"));
	}
	
	@Test
	public void getStr() {
		String str = "abc";
		assertTrue(getStr(str).equals(str));
		assertTrue(getStr(null).equals(EMPTY));
		assertTrue(getStr(EMPTY, str).equals(str));
		assertTrue(getStr(null, str).equals(str));
	}
	
	@Test
	public void extractVariable() {
		String str = "SELECT * FROM data_user WHERE group_id = ${group_id} AND del_flag = ${del_flag}";
		List<String> strList = extractVariable(str);
		assertTrue(strList != null);
		assertTrue(strList.size() == 2);
		assertTrue(strList.contains("group_id"));
		assertTrue(strList.contains("del_flag"));
	}
	
}
