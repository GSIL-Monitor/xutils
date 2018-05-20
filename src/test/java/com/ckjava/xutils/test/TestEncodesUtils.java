package com.ckjava.xutils.test;

import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;

import org.apache.commons.codec.DecoderException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ckjava.xutils.Constants;
import com.ckjava.xutils.EncodesUtils;

public class TestEncodesUtils extends EncodesUtils implements Constants {

	private String str = null;
	private String base64Str = null;
	private String hexStr = null;
	private String urlEncodeString = null;
	
	@Before
	public void initTest() {
		str = "hello 你好";
		base64Str = "aGVsbG8g5L2g5aW9";
		hexStr = "68656c6c6f20e4bda0e5a5bd";
		urlEncodeString = "hello+%E4%BD%A0%E5%A5%BD";
	}
	
	@Test
	public void encodeHex() throws UnsupportedEncodingException {
		String tempHexStr = encodeHex(str.getBytes(CHARSET.UTF8));
		System.out.println(MessageFormat.format("hexStr = {0}, tempHexStr = {1}", hexStr, tempHexStr));
		Assert.assertTrue(hexStr.equals(tempHexStr));
	}
	
	@Test
	public void decodeHex() throws DecoderException, UnsupportedEncodingException {
		byte[] tempByte = decodeHex(hexStr);
		Assert.assertTrue(new String(tempByte, CHARSET.UTF8).equals(str));
	}
	
	@Test
	public void encodeBase64() throws UnsupportedEncodingException {
		String tempBase64Str = encodeBase64(str.getBytes(CHARSET.UTF8));
		Assert.assertTrue(tempBase64Str.equals(base64Str));
	}
	
	@Test
	public void encodeBase64UTF() throws UnsupportedEncodingException {
		String tempBase64Str = encodeBase64(str);
		Assert.assertTrue(tempBase64Str.equals(base64Str));
	}
	
	@Test
	public void decodeBase64() {
		try {
			byte[] tempByte = decodeBase64(base64Str);
			assertTrue(new String(tempByte).equals(str));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void decodeBase64String() {
		try {
			String tempString = decodeBase64String(base64Str);
			assertTrue(new String(tempString).equals(str));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void urlEncode() {
		try {
			String encodeString = urlEncode(str);
			assertTrue(encodeString.equals(urlEncodeString));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void urlDecode() {
		try {
			String tempString = urlDecode(urlEncodeString);
			assertTrue(tempString.equals(str));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void bytesToHexString() throws UnsupportedEncodingException {
		String hexString = byte2HexString(str.getBytes(CHARSET.UTF8));
		assertTrue(hexString.equals(hexStr));
	}
	
	@Test
	public void hexStringToBytes() throws UnsupportedEncodingException {
		byte[] bytes = hexString2Byte(hexStr);
		assertTrue(str.equals(new String(bytes, CHARSET.UTF8)));
	}
	
}
