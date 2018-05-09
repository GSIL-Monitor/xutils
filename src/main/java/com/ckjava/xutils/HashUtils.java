package com.ckjava.xutils;

import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashUtils extends IOUtils {

	private static final Logger logger = LoggerFactory.getLogger(IOUtils.class);

	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 获取字符串的 hash字符串
	 * 
	 * @param originalString 原始字符串
	 * @param hashType 包括 MD2,MD5,SHA-1,SHA-224,SHA-256,SHA-384,SHA-512 
	 * @return hash字符串(16进制摘要字符串)
	 */
	public static String digestString(String originalString, String hashType) {
		try {
			byte[] strTemp = originalString.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance(hashType);
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			
			return byte2HexString(md);
		} catch (Exception e) {
			logger.error("HashUtils MD5Digest has error", e);
			return null;
		}
	}
	
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
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(str);
	}

	/**
	 * 获取文件的 hash字符串
	 * 
	 * @param originalFile 原始文件对象 File
	 * @param hashType 包括 MD2,MD5,SHA-1,SHA-224,SHA-256,SHA-384,SHA-512 
	 * 
	 * @return hash字符串(16进制字符串)
	 */
	public static String digestFile(File originalFile, String hashType) {
		DigestInputStream digestInputStream = null;
		try {
			// 拿到一个MD5转换器（同样，这里可以换成SHA1）
			MessageDigest messageDigest = MessageDigest.getInstance(hashType);
			// 使用DigestInputStream
			digestInputStream = new DigestInputStream(new FileInputStream(originalFile), messageDigest);
			// read的过程中进行MD5处理，直到读完文件
			int bufferSize = 256 * 1024;
			byte[] buffer = new byte[bufferSize];
			while (digestInputStream.read(buffer) > 0) {
			}
			// 获取最终的MessageDigest
			messageDigest = digestInputStream.getMessageDigest();

			// 拿到结果，也是字节数组，包含16个元素
			byte[] resultByteArray = messageDigest.digest();

			// 同样，把字节数组转换成字符串
			return byte2HexString(resultByteArray);
		} catch (Exception e) {
			logger.error("HashUtils digestFile has error", e);
			return null;
		} finally {
			closeQuietly(digestInputStream);
		}
	}
		
	public class HASHTYPE {
		public static final String MD2 = "MD2";
		public static final String MD5 = "MD5";
		public static final String SHA1 = "SHA-1";
		public static final String SHA224 = "SHA-224";
		public static final String SHA256 = "SHA-256";
		public static final String SHA384 = "SHA-384";
		public static final String SHA512 = "SHA-512";
	}
	
	/*public static String byte2HexString(byte[] b) { // 一个字节的数，
	    StringBuffer sb = new StringBuffer(b.length * 2);
	    String tmp = "";
	    for (int n = 0; n < b.length; n++) {
	        // 整数转成十六进制表示
	        tmp = (Integer.toHexString(b[n] & 0XFF));
	        if (tmp.length() == 1) {
	            sb.append("0");
	        }
	        sb.append(tmp);
	    }
	    return sb.toString(); // 转成大写
	}*/

}
