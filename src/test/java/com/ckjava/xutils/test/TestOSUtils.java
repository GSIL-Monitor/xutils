package com.ckjava.xutils.test;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import com.ckjava.xutils.OSUtils;
import com.ckjava.xutils.StringUtils;

public class TestOSUtils extends OSUtils {

	@Test
	public void testOSType() {
		Properties pro = System.getProperties();
		pro.setProperty("os.name", OSTYPE.WINDOWS);
		Assert.assertTrue(getCurrentOSType().equals(OSTYPE.WINDOWS));
		pro.setProperty("os.name", OSTYPE.MAC);
		Assert.assertTrue(getCurrentOSType().equals(OSTYPE.MAC));
		pro.setProperty("os.name", OSTYPE.LINUX);
		Assert.assertTrue(getCurrentOSType().equals(OSTYPE.LINUX));
		pro.setProperty("os.name", StringUtils.EMPTY);
		Assert.assertTrue(getCurrentOSType().equals(StringUtils.EMPTY));
	}
}
