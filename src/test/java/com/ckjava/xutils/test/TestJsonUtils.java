package com.ckjava.xutils.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.ckjava.xutils.JsonUtils;

public class TestJsonUtils extends JsonUtils {

	@Test
	public void resolveJsonString() {
		try {
			String jsonString = "";
			List<String> dataList = resolveJsonString(jsonString);
			assertTrue(dataList.size() == 0);
			
			jsonString = "[{\"error\":\"不为空\"}]";
			dataList = resolveJsonString(jsonString);
			assertTrue(dataList.contains("error=\"error\":\"不为空\""));
		
			dataList.clear();
			jsonString = "{\"error\":\"不为空\", \"msg\":{\"detail\":\"用户名称为空\"}}";
			dataList = resolveJsonString(jsonString);
			assertTrue(dataList.contains("error=\"error\":\"不为空\""));
			assertTrue(dataList.contains("detail=\"detail\":\"用户名称为空\""));
			
			jsonString = "[{\"error\": \"不为空\", \"detail\":[{\"username\":\"username为空\", \"hobby\":[{\"name\":\"football\"},{\"name\":\"baseball\"}]}]}]";
			dataList = resolveJsonString(jsonString);
			assertTrue(dataList.contains("error=\"error\":\"不为空\""));
			assertTrue(dataList.contains("username=\"username\":\"username为空\""));
			assertTrue(dataList.contains("name=\"name\":\"football\""));
			assertTrue(dataList.contains("name=\"name\":\"baseball\""));
			
			jsonString = "{\"min_request_fields\": \"{\\\"card_holder\\\":true,\\\"idcard_no\\\":true,\\\"idcard_type\\\":true,\\\"pass_word\\\":false,\\\"phone_no\\\":true,\\\"phone_verify_no\\\":true,\\\"validity\\\":true,\\\"verify_no\\\":true}\"}";
			dataList = resolveJsonString(jsonString);
			assertTrue(dataList.contains("min_request_fields=\"min_request_fields\":\"{\\\"card_holder\\\":true,\\\"idcard_no\\\":true,\\\"idcard_type\\\":true,\\\"pass_word\\\":false,\\\"phone_no\\\":true,\\\"phone_verify_no\\\":true,\\\"validity\\\":true,\\\"verify_no\\\":true}\""));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
