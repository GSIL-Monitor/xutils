package com.ckjava.xutils.test;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.ckjava.xutils.FileUtils;
import com.ckjava.xutils.HashUtils;

public class TestHashUtils extends HashUtils {
	
	@Test
	public void testDigestString() {
		String str1 = "hello";
		String str2 = "您好";
		
		System.out.println("str1 digestString="+digestString(str1, HASHTYPE.MD5));
		System.out.println("str1 digestString2="+digestString(str1, HASHTYPE.MD5));
		System.out.println("str2 md5="+digestString(str2, HASHTYPE.MD5));
		
		assertTrue(digestString(str1, HASHTYPE.MD5).length() == 32);
		assertTrue(digestString(str1, HASHTYPE.MD5).equals(digestString(str1, HASHTYPE.MD5)));
		assertTrue(!digestString(str1, HASHTYPE.MD5).equals(digestString(str2, HASHTYPE.MD5)));
	}
	
	@Test
	public void testDigestFile() {
		String path = TestDbUtils.class.getResource("/").getPath();
		String md5 = digestFile(new File(FileUtils.joinPath(path, "test.db")), HASHTYPE.MD5);
		System.out.println(md5);
		assertTrue(md5.length() == 32);
		assertTrue(!md5.isEmpty());
	}
	
	public static void main(String[] args) {
		String hashString = digestFile(new File("D:\\Users\\chen_k\\Downloads\\apache-tomcat-8.5.31.zip"), HASHTYPE.SHA512);
		System.out.println(hashString.equals("51d8877782bc975b8c566263df7e55f383e617aa3c81ea2c219fed18e1f5d9e8233a92100de95b9a8df69ce5c0ad89a195d5b7e5647fcf9df26231870073a9cb"));
	}
	
	

}
