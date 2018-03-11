package com.ckjava.xutils;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtils extends org.apache.commons.io.IOUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(IOUtils.class);
	
	/**
	 * 从 {@code java.io.InputStream } 中以特定字符集读取出字符串
	 * 
	 * @param is {@code java.io.InputStream } 对象
	 * @param charset 特定字符集
	 * @return 读取的字符串
	 */
	public static String getString(InputStream is, String charset) {
		StringBuilder str = new StringBuilder();
		int temp = 0;
		byte[] b = new byte[102400];
		try {
			while ((temp = is.read(b, 0, b.length)) != -1) {
				str.append(new String(b, 0, temp, charset));
			}
			return str.toString();
		} catch (Exception e) {
			logger.error("IOUtils getString(InputStream is, String charset) has error", e);
			return null;
		} finally {
			closeQuietly(is);
		}
	}
	
	/**
	 * 从 {@code java.io.InputStream } 中以系统默认的字符集读取出字符串
	 * 
	 * @param is {@code java.io.InputStream } 对象
	 * @return 读取的字符串
	 */
	public static String getString(InputStream is) {
		StringBuilder str = new StringBuilder();
		int temp = 0;
		byte[] b = new byte[102400];
		try {
			while ((temp = is.read(b, 0, b.length)) != -1) {
				str.append(new String(b, 0, temp));
			}
			return str.toString();
		} catch (Exception e) {
			logger.error("IOUtils getString(InputStream is) has error", e);
			return null;
		} finally {
			closeQuietly(is);
		}
	}
}
