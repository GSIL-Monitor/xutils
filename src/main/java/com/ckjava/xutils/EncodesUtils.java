package com.ckjava.xutils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * 封装各种格式的编码解码工具类.
 */
public class EncodesUtils implements Constants {

	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	/**
	 * 将 {@code byte[]} 转成 Hex 编码
	 * 
	 * @param input
	 *            {@code byte[]}
	 * @return Hex 编码
	 */
	public static String encodeHex(byte[] input) {
		return new String(Hex.encodeHex(input));
	}

	/**
	 * Hex解码.
	 * 
	 * @param input
	 *            String 类型
	 * @return byte[] 类型
	 * @throws DecoderException
	 *             解码异常
	 */
	public static byte[] decodeHex(String input) throws DecoderException {
		return Hex.decodeHex(input.toCharArray());
	}

	/**
	 * Base64编码.
	 * 
	 * @param input
	 *            byte[] 类型
	 * @return String 类型
	 */
	public static String encodeBase64(byte[] input) {
		return new String(Base64.encodeBase64(input));
	}

	/**
	 * Base64编码
	 * 
	 * @param input
	 *            String 类型, 以 UTF-8 编码
	 * @return String 类型
	 * @throws UnsupportedEncodingException
	 *             未知的字符编码异常
	 */
	public static String encodeBase64(String input) throws UnsupportedEncodingException {
		return new String(Base64.encodeBase64(input.getBytes(CHARSET.UTF8)));
	}

	/**
	 * Base64解码.
	 * 
	 * @param base64String
	 *            base64 字符串
	 * @return byte[] 类型
	 * @throws UnsupportedEncodingException
	 *             未知编码异常
	 */
	public static byte[] decodeBase64(String base64String) throws UnsupportedEncodingException {
		return Base64.decodeBase64(base64String.getBytes(CHARSET.UTF8));
	}

	/**
	 * Base64解码.
	 * 
	 * @param base64String
	 *            base64 字符串
	 * @return String 类型
	 * @throws UnsupportedEncodingException
	 *             未知编码异常
	 */
	public static String decodeBase64String(String base64String) throws UnsupportedEncodingException {
		return new String(Base64.decodeBase64(base64String.getBytes(CHARSET.UTF8)), CHARSET.UTF8);
	}

	/**
	 * URL 编码, Encode默认为UTF-8.
	 * 
	 * @param part
	 *            String 类型
	 * @return String 类型
	 * @throws UnsupportedEncodingException
	 *             未知编码异常
	 */
	public static String urlEncode(String part) throws UnsupportedEncodingException {
		return URLEncoder.encode(part, CHARSET.UTF8);
	}

	/**
	 * URL 解码, Encode默认为UTF-8.
	 * 
	 * @param part
	 *            String 类型
	 * @return String 类型
	 * @throws UnsupportedEncodingException
	 *             未知编码异常
	 */
	public static String urlDecode(String part) throws UnsupportedEncodingException {
		return URLDecoder.decode(part, CHARSET.UTF8);
	}

	/**
	 * 16进制字符串转换成字节数组
	 *
	 * @param hexString
	 *            16进制字符串
	 * @return 字节数组
	 */
	public static byte[] hexString2Byte(String hexString) {
		byte[] b = new byte[hexString.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexString.charAt(j++);
			char c1 = hexString.charAt(j++);
			b[i] = (byte) ((Character.getNumericValue(c0) << 4) | Character.getNumericValue(c1));
		}
		return b;
	}

	/**
	 * 将字节数组转成16进制字符串
	 *
	 * @param bytes
	 *            字节数组
	 * @return 16进制字符串
	 */
	public static String byte2HexString(byte[] bytes) {
		int j = bytes.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = bytes[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(str);
	}
}
