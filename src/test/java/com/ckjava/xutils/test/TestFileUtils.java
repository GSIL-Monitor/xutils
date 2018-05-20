package com.ckjava.xutils.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import com.ckjava.xutils.FileUtils;
import com.ckjava.xutils.IOUtils;

public class TestFileUtils extends FileUtils {
	
	String path = null;
	
	@Before
	public void init() {
		path = TestFileUtils.class.getResource("/").getPath();
	}
	
	@Test
	public void joinPath() {
		String pathString = joinPath(path, "tmep", "/a", "b/", "c/d/f/", "/c/d/f", "c/d/f");
		pathString = pathString.replace("/", File.separator).replace("\\", File.separator);
		
		String targetPath = path+"tmep/a/b/c/d/f/c/d/f/c/d/f";
		targetPath = targetPath.replace("/", File.separator).replace("\\", File.separator);
				
		assertTrue(pathString.equals(targetPath));
		
		pathString = joinPath(path, "tmep", "/a", "b\\", "c\\d/f/", "\\c/d/f", "c\\d/f", "/a.txt");
		pathString = pathString.replace("/", File.separator).replace("\\", File.separator);
		
		targetPath = path+"tmep/a/b/c/d/f/c/d/f/c/d/f/a.txt";
		targetPath = targetPath.replace("/", File.separator).replace("\\", File.separator);
		assertTrue(pathString.equals(targetPath));
		
		pathString = joinPath("d:/a/b", "1.txt");
		pathString = pathString.replace("/", File.separator).replace("\\", File.separator);
		
		targetPath = "d:/a/b/1.txt";
		targetPath = targetPath.replace("/", File.separator).replace("\\", File.separator);
		assertTrue(pathString.equals(targetPath));
	}
	
	@Test
	public void testCopyFile() throws IOException {
		File tempFile = new File(joinPath(path,"/tempFile.txt"));
		tempFile.createNewFile();
		
		String targetFilePath = joinPath(path,"tempdir/",tempFile.getName());
		boolean flag = copyFile(tempFile.getAbsolutePath(), targetFilePath);
		assertTrue(flag);
		
		File targetFile = new File(targetFilePath);
		assertTrue(targetFile.exists());
		
		copyFileCover(tempFile.getAbsolutePath(), targetFilePath, true);
		
		assertTrue(targetFile.delete());
	}
	
	@Test
	public void copyDirectory() throws IOException {
		createFile(joinPath(path, "/dir1/tempFile1.txt"));
		createFile(joinPath(path, "/dir1/tempFile2.txt"));
		String srcDir = joinPath(path,"/dir1/");
		
		String targetDir = joinPath(path,"/dir2");
		assertTrue(deleteDirectory(targetDir));
		
		boolean flag = copyDirectory(srcDir, targetDir);
		assertTrue(flag);
		
		assertFalse(copyDirectoryCover(srcDir, targetDir, false));
		
		assertTrue(copyDirectoryCover(srcDir, targetDir, true));
		
		assertTrue(deleteDirectory(srcDir));
		assertTrue(deleteDirectory(targetDir));
	}
	
	@Test
	public void createDirectory() {
		String targetDir = joinPath(path,"/dir2","dir3");
		assertTrue(createDirectory(targetDir));
		assertFalse(createDirectory(targetDir));
		
		createFile(joinPath(targetDir,"1.txt"));
		createFile(joinPath(targetDir,"a/2.txt"));
		
		assertTrue(deleteDirectory(targetDir));
	}
	
	@Test
	public void zipFile() throws IOException {
		File tempFile = new File(joinPath(path,"/tempFile.txt"));
		tempFile.createNewFile();
		
		write(tempFile, "测试\r\ntest", CHARSET.UTF8);
		
		File zipFile = zipFile(tempFile);
		assertTrue(zipFile.exists());
		
		tempFile.delete();
		zipFile.delete();
	}
	
	@Test
	public void unzipFiles() throws IOException {
		// 创建测试目录
		File tempUnzipDir = new File(joinPath(path,"/tempUnzipDir"));
		tempUnzipDir.mkdirs();
		
		// 创建压缩文件
		File tempFile = new File(joinPath(path,"/tempFile.txt"));
		tempFile.createNewFile();
		
		write(tempFile, "测试\r\ntest", CHARSET.UTF8);
		
		File zipFile = zipFile(tempFile);
		assertTrue(zipFile.exists());
		
		// 解压文件
		assertTrue(unzipFile(zipFile, tempUnzipDir));
		
		// 判断解压后的文件是否一致
		File unzipFile = new File(joinPath(path,"/tempUnzipDir", "tempFile.txt"));
		assertTrue(unzipFile.exists());
		
		String data = IOUtils.getString(new FileInputStream(unzipFile));
		assertTrue(data.equals("测试\r\ntest"));
		
		// 删除文件
		unzipFile.delete();
		tempUnzipDir.delete();
		zipFile.delete();
		tempFile.delete();
	}
	
	public static void main(String[] args) {
		String[] excludePath = {".svn", ".git", ".metadata",".recommenders", "target", "bin", ".settings", "classes", "logs"};
		String[] excludeFile = {".class"};
		//String path = "D:/git-workspace/db_backup";
		//String path = "D:/php-workspace";
		//String path = "D:/svn-workspace";
		String path = "D:\\git-workspace\\xutils";
		String desDir = "D:/BaiduYunDownload/encode-files";
		//backupDir(path, desDir, excludePath, excludeFile);
		
		String originalFileName = "blurAdmin_zip_encode_1525865019863.zip";
		String encodeFileName = "blurAdmin_zip_encode_1525865019863";
		String zipFileName = "blurAdmin.zip";
		String projectFileName = "blurAdmin";
		
		String zipFile = "D:\\workspace\\angularJS\\"+originalFileName;
		String unzipPath = "D:\\workspace\\angularJS\\";
		
		unpackFile(new File(zipFile), new File(unzipPath), encodeFileName, zipFileName, projectFileName);
	}
	
	public static void unpackFile(File zipfile, File dirPath, String encodefileName, String zipFileName, String projectFileName) {
		boolean flag = unzipFile(zipfile, dirPath);
		if (flag) {
			System.out.println("解压原始文件成功");
			
			File destZipFile = new File(dirPath.getAbsolutePath()+File.separator+zipFileName);
			deCodeFile(new File(dirPath.getAbsolutePath()+File.separator+encodefileName), destZipFile);
			System.out.println("deCodeFile done");
			
			flag = unzipFile(destZipFile, new File(dirPath.getAbsolutePath()+File.separator+projectFileName));
			if (flag) {
				System.out.println("解压项目文件成功");
			} else {
				System.out.println("解压项目文件成功");
			}
		} else {
			System.err.println("解压原始文件失败");
		}
		
	}
	
	public static void backupDir(String path, String desDir, String[] excludePath, String[] excludeFile) {
		File zipFile = new File(path + ".zip");
		File encodeFile = new File(path + "_zip_encode_" + new Date().getTime());
		
		FileUtils.zipFiles(path, "*", excludePath, excludeFile, zipFile);
		
		enCodeFile(zipFile, encodeFile);
		
		File finalZipFile = FileUtils.zipFile(encodeFile);
		
		try {
			File desFile = new File(desDir + File.separator + finalZipFile.getName());
			if (desFile.exists()) {
				desFile.delete();
			}
			FileUtils.moveFileToDirectory(finalZipFile, new File(desDir), true);
			
			if (zipFile.delete()) {
				System.out.println("delete " + zipFile);
			}
			
			if (encodeFile.delete()) {
				System.out.println("delete " + encodeFile);
			}
			
			if (finalZipFile.delete()) {
				System.out.println("delete " + finalZipFile.getAbsolutePath());
			}
			
			System.out.println("finish");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void deCodeFile() {
		String data = "D:\\BaiduYunDownload\\dagger.txt";
		
		String temp = "";
		try {
			BufferedReader is = new BufferedReader(new FileReader(new File(data)));
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(new File("D:\\BaiduYunDownload\\dagger.zs.jar")));
			while ((temp = is.readLine()) != null) {
				byte[] str = Base64.decodeBase64(temp.getBytes());
				os.write(str);
			}
			is.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("done");
	}
	
	public static void deCodeFile(File sourceFile, File destFile) {
		String temp = "";
		try {
			BufferedReader is = new BufferedReader(new FileReader(sourceFile));
			BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(destFile));
			while ((temp = is.readLine()) != null) {
				byte[] str = Base64.decodeBase64(temp.getBytes());
				os.write(str);
			}
			is.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将 zip文件通过 base64 处理 
	 * 
	 * @param zipFile
	 * @param encodeFile
	 */
	public static void enCodeFile(File zipFile, File encodeFile) {
		int temp = 0;
		byte[] b = new byte[102400];
		try {
			InputStream is = new FileInputStream(zipFile);
			BufferedWriter os = new BufferedWriter(new FileWriter(encodeFile));
			while ((temp = is.read(b, 0, b.length)) != -1) {
				byte[] realdata = new byte[temp];
				System.arraycopy(b, 0, realdata, 0, temp);
				byte[] str = Base64.encodeBase64(realdata);
				os.write(new String(str));
				os.newLine();
			}
			is.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
