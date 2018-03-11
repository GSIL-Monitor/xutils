package com.ckjava.xutils;

import java.util.Properties;

/**
 * 操作系统相关工具方法
 * 
 * @author ck
 *
 */
public class OSUtils implements Constants {
	
	private static Properties props = System.getProperties();

	/**
	 * 获取当前的操作系统类型
	 *
	 * @return windows or mac or linux
	 */
	public static String getCurrentOSType() {
		String osName = props.getProperty("os.name").toLowerCase();
		if (osName.contains(OSTYPE.WINDOWS)) {
			return OSTYPE.WINDOWS;
		} else if (osName.contains(OSTYPE.MAC)) {
			return OSTYPE.MAC;
		} else if (osName.contains(OSTYPE.LINUX)) {
			return OSTYPE.LINUX;
		} else {
			return StringUtils.EMPTY;
		}
	}
}
