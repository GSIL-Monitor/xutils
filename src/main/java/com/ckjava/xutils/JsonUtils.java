package com.ckjava.xutils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	/**
	 * 将json字符串中所有的对象以 key=value 的字符串形式存入 List 对象中
	 * 
	 * @param jsonString 原始的 json 字符串
	 * @return {@code List<String> } 集合中是以 key=value 的字符串
	 * @throws Exception 异常对象
	 */
	public static List<String> resolveJsonString(String jsonString) throws Exception {
		List<String> dataList = new ArrayList<>();
		
		if (jsonString.startsWith("{")) {
			JSONObject object = JSONObject.parseObject(jsonString);
			resolveJSONObject(object, dataList);
		} else if (jsonString.startsWith("[")) {
			JSONArray array = JSONArray.parseArray(jsonString);
			resolveJSONArray(array, dataList);
		} else {
			logger.warn("the jsonString[{}] must be json string", jsonString);
		}
		
		return dataList;
	}
	
	private static void resolveJSONObject(JSONObject object, List<String> dataList) {
		for (Iterator<String> it = object.keySet().iterator(); it.hasNext();) {
			String key = it.next();
			Object value = object.get(key);
			
			if (value instanceof JSONObject) {
				resolveJSONObject((JSONObject) value, dataList);
			} else if (value instanceof JSONArray) {
				resolveJSONArray((JSONArray) value, dataList);
			} else {
				String finalValue = "";
				if (value instanceof String) {
					String valueString = StringUtils.getStr(value);
					if (valueString.startsWith("{") || valueString.startsWith("[")) {
						valueString = valueString.replace("\"", "\\\"");
					}
					finalValue = "\"" + key + "\":" + "\"" + valueString + "\"";
				} else {
					finalValue = "\"" + key + "\":" + StringUtils.getStr(value);
				}
				dataList.add(key + "=" + finalValue);
			}
		}
	}
	
	private static void resolveJSONArray(JSONArray array, List<String> dataList) {
		for (Iterator<Object> it = array.listIterator(); it.hasNext();) {
			Object value = it.next();
			if (value instanceof JSONObject) {
				resolveJSONObject((JSONObject) value, dataList);
			} else if (value instanceof JSONArray) {
				resolveJSONArray((JSONArray) value, dataList);
			}
		}
	}
}
