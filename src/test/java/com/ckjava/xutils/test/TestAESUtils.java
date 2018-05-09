package com.ckjava.xutils.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ckjava.xutils.AESUtils;

public class TestAESUtils extends AESUtils {
	
	private String hexString = "e682a8e5a5bd2c2068656c6c6f";
	private String aesHexString = "ebaeb927af1f2e847d583370c3407ce9";
	private String dataString = "您好, hello";
	private String pw = "pwdd00000000";
	
	@Test
	public void testByte2HexString() throws Exception {
		byte[] data = "您好, hello".getBytes(CHARSET.UTF8);
		String hexString = byte2HexString(data);
		System.out.println("hexString="+hexString);
		assertTrue(hexString.equals(hexString));
	}
	
	@Test
	public void testHexString2Byte() throws Exception {
		byte[] data = hexString2Byte(hexString);
		String string = new String(data, CHARSET.UTF8);
		System.out.println("string="+string);
		assertTrue(string.equals(dataString));
	}
	
	@Test
	public void testEncryptString() {
		setKeyLength(16);
		String encryptDataString = encrypt(dataString, pw);
		System.out.println(encryptDataString);
		assertTrue(encryptDataString.equals(aesHexString));
	}
	
	@Test
	public void testDecryptString() {
		setKeyLength(16);
		String originalDataString = decrypt(aesHexString, pw);
		System.out.println("originalDataString="+originalDataString);
		assertTrue(originalDataString.equals(dataString));
	}
}
