package com.ckjava.xutils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AESUtils extends StringUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(AESUtils.class);
	private static int DEFAULT_KEY_LEN = 16;
	
	/** 
	 * 算法/模式/填充
	 * 
	 **/
	private static final String CipherMode = "AES/ECB/PKCS5Padding";
	
	public static void setKeyLength(int keyLength) {
		DEFAULT_KEY_LEN = keyLength;
	}

	/**
	 * 创建 AES密钥
	 * 
	 * @param password 密码明文
	 * @return SecretKeySpec 对象
	 */
	private static SecretKeySpec createKey(String password, int keyLength) {
		if (password == null) {
			password = EMPTY;
		}
		// AES 支持 16, 24 或者 32 字节长度的密钥, 如果不够这里默认用"0"填充
		StringBuilder str = new StringBuilder(keyLength);
		str.append(password);
		while (str.length() < keyLength) {
			str.append("0");
		}
		if (str.length() > keyLength) {
			str.setLength(keyLength);
		}
		try {
			byte[] data = str.toString().getBytes(CHARSET.UTF8);
			return new SecretKeySpec(data, "AES");
		} catch (Exception e) {
			logger.error("AESUtils createKey has error", e);
			return null;
		}
	}

	/**
	 * 使用明文密码将字节数组加密
	 * 
	 * @param content 待加密的内容:字节数组
	 * @param password 密码明文
	 * @return 加密后的内容:字节数组
	 */
	public static byte[] encrypt(byte[] content, String password) {
		try {
			SecretKeySpec key = createKey(password, DEFAULT_KEY_LEN);
			Cipher cipher = Cipher.getInstance(CipherMode);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher.doFinal(content);
		} catch (Exception e) {
			logger.error("AESUtils encrypt has error", e);
			return null;
		}
	}

	/**
	 * 使用明文密码将字符串加密,结果为16进制字符串
	 * 
	 * @param content 待加密的字符串
	 * @param password 密码明文
	 * @return 加密后的16进制字符串
	 */
	public static String encrypt(String content, String password) {
		try {
			byte[] data = content.getBytes(CHARSET.UTF8);
			byte[] encryptData = encrypt(data, password);
			return byte2HexString(encryptData);
		} catch (Exception e) {
			logger.error("AESUtils encrypt has error", e);
			return null;
		}
	}

	/**
	 * 使用明文密码将字节数组解密成字节数组
	 * 
	 * @param content 加密的字节数组
	 * @param password 明文密码
	 * @return 解密后的字节数组
	 */
	public static byte[] decrypt(byte[] content, String password) {
		try {
			SecretKeySpec key = createKey(password, DEFAULT_KEY_LEN);
			Cipher cipher = Cipher.getInstance(CipherMode);
			cipher.init(Cipher.DECRYPT_MODE, key);
			
			return cipher.doFinal(content);
		} catch (Exception e) {
			logger.error("AESUtils createKey has error", e);
			return null;
		}
	}

	/**
	 * 使用明文密码解密16进制字符串(16进制字符串是由加密的字节数组转换而成的)
	 * 
	 * @param hexString 16进制字符串
	 * @param password 明文密码
	 * @return
	 */
	public static String decrypt(String hexString, String password) {
		try {
			byte[] data = hexString2Byte(hexString);
			byte[] decryptData = decrypt(data, password);
			return new String(decryptData, CHARSET.UTF8);
		} catch (Exception e) {
			logger.error("AESUtils decrypt has error", e);
			return null;
		}
	}
	
	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	/**
	 * 将字节数组转成16进制字符串
	 * 
	 * @param bytes 字节数组
	 * @return 16进制字符串
	 */
	public static String byte2HexString(byte[] bytes) {
		int j = bytes.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = bytes[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xF];
			str[k++] = hexDigits[byte0 & 0xF];
		}
		return new String(str);
	}
	
	/** 
	 * 16进制字符串转换成字节数组 
	 *  
	 * @param hexString 16进制字符串
	 * @return 字节数组
	 */  
	public static byte[] hexString2Byte(String hexString) {
	    byte[] b = new byte[hexString.length() / 2];
	    int j = 0;
	    for (int i = 0; i < b.length; i++) {
	        char c0 = hexString.charAt(j++);
	        char c1 = hexString.charAt(j++);
	        b[i] = (byte) (parse(c0) << 4 | parse(c1));
	    }
	    return b;
	}
	
	/**
	 * 将 0-f 的字符数字转换为十进制的数字 利用ascll值差距(http://tool.oschina.net/commons?type=4)
	 * 
	 * @param c char类型的字符( 0-f 或者 0-F)
	 * @return int 类型的数字
	 */
	public static int parse(char c) {
	    if (c >= 'a') {
	    	return c - 'a' + 10;
	    }
	    if (c >= 'A') {
	    	return c - 'A' + 10;
	    }
	    return c - '0';
	}
	
	/*public static void main(String[] args) {
		try {
			byte[] data = "您好, hello".getBytes(CHARSET.UTF8);	
			String hexString = byte2HexString(data);
			
			System.out.println("data hexString=" + hexString);
			byte[] dataBytes = hexString2Byte(hexString);
			System.out.println("final String=" + new String(dataBytes, CHARSET.UTF8));
			
			// 大写F的值为 70
			System.out.println('F'+10);
			System.out.println('a' - 'A');
			System.out.println('f' - 'a');
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
