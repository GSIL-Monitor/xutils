package com.ckjava.xutils.test;

import org.junit.Assert;
import org.junit.Test;

import com.ckjava.xutils.ExceptionUtils;

public class TestExceptionUtils extends ExceptionUtils {

	@Test
	public void getExceptionMsg() {
		Exception e = new RuntimeException("测试异常");
		String msg = getExceptionMsg(e);
		Assert.assertTrue(msg.contains("java.lang.RuntimeException"));
		Assert.assertTrue(msg.contains("测试异常"));
	}

}
