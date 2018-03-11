package com.ckjava.xutils.test;

import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

import com.ckjava.xutils.ObjectUtils;

public class TestObjectUtils extends ObjectUtils {
	
	@Test
	public void fillMapWithString() {
		String data = "Server=nginx/1.12.0;Date=Fri, 09 Feb 2018 08:56:50 GMT;Content-Type=text/xml; charset=utf-8;Content-Length=711;Connection=keep-alive;Cache-Control=private, max-age=0;X-AspNet-Version=4.0.30319;X-Powered-By=ASP.NET;slb-http-protocol-version=HTTP/1.1;Access-Control-Expose-Headers=slb-http-protocol-version;";
		Map<String, String> dataMap = fillMapWithString(data, ";", 2);
		Assert.assertTrue(!dataMap.isEmpty());
		Assert.assertTrue(dataMap.size() == 11);
		Assert.assertTrue(dataMap.get("Content-Type").equals("text/xml"));
		Assert.assertTrue(dataMap.get("charset").equals("utf-8"));
		Assert.assertTrue(dataMap.get("Content-Length").equals("711"));
		Assert.assertTrue(dataMap.get("Connection").equals("keep-alive"));
		
		dataMap.clear();
		dataMap = fillMapWithString(data, ";", 3);
		Assert.assertTrue(dataMap.get("Cache-Control").equals("private, max-age"));
		
		data = "Server=nginx/1.12.0&Date=Fri, 09 Feb 2018 08:56:50 GMT&Content-Type=text/xml& charset=utf-8&Content-Length=711&Connection=keep-alive&Cache-Control=private, max-age=0&X-AspNet-Version=4.0.30319&X-Powered-By=ASP.NET&slb-http-protocol-version=HTTP/1.1&Access-Control-Expose-Headers=slb-http-protocol-version";
		dataMap.clear();
		dataMap = fillMapWithString(data);
		Assert.assertTrue(!dataMap.isEmpty());
		Assert.assertTrue(dataMap.size() == 11);
		Assert.assertTrue(dataMap.get("Content-Type").equals("text/xml"));
		Assert.assertTrue(dataMap.get("charset").equals("utf-8"));
		Assert.assertTrue(dataMap.get("Content-Length").equals("711"));
		Assert.assertTrue(dataMap.get("Connection").equals("keep-alive"));
		Assert.assertTrue(dataMap.get("Cache-Control").equals("private, max-age"));

		data = "abc@1,ef@2";
		dataMap = fillMapWithString(data, SPLITER.COMMA, SPLITER.AT);
		Assert.assertTrue(dataMap.size() == 2);
		Assert.assertTrue(dataMap.get("abc").equals("1"));
		Assert.assertTrue(dataMap.get("ef").equals("2"));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void objectToBytes() {
		String name = "ck";
		String age = "30";
		HashMap<String, String> data = new HashMap<>();
		data.put("name", name);
		data.put("age", age);
		byte[] bytes = objectToBytes(data);
		
		HashMap<String, String> objMap = (HashMap<String, String>) bytesToObject(bytes);
		Assert.assertEquals(objMap.get("name"), name);
		Assert.assertEquals(objMap.get("age"), age);
	}

	@Test
	public void testGetObjectString() {
		assertTrue(null == getObjectString(null));
		
		User user = new User("ck",30);
		String str = getObjectString(user);
		
		assertTrue(str.contains("name=ck&age=30"));
		
		user = new User();
		str = getObjectString(user);
		assertTrue(!str.contains("name=ck&age=30"));
	}
	
	@Test
	public void mPull() {
		List<SimpleDict> dataList = new ArrayList<>();
		dataList.add(new SimpleDict("a", "a1"));
		dataList.add(new SimpleDict("b", "b1"));
		
		Map<String, String> dataMap = mPull(dataList, "getKey", "getValue");
		Assert.assertTrue(dataMap.size() == 2);
		
		for (Iterator<Map.Entry<String, String>> it = dataMap.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			String key = entry.getKey();
			String value = entry.getValue();
			if (key == "a") {
				assertTrue(value.equals("a1"));	
			}
			if (key == "b") {
				assertTrue(value.equals("b1"));	
			}
		}
		
	}
	
	public class SimpleDict {
		private String key;
		private String value;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public SimpleDict(String key, String value) {
			super();
			this.key = key;
			this.value = value;
		}
		public SimpleDict() {
			super();
		}
		
	}
	
	public class User implements Serializable {
		private static final long serialVersionUID = 1L;
		private String name;
		private int age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public User(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}
		public User() {
			super();
		}
		
	}
}
