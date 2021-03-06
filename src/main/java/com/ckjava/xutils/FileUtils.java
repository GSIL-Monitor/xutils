package com.ckjava.xutils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件操作工具类
 * 实现文件的创建、删除、复制、压缩、解压以及目录的创建、删除、复制、压缩解压等功能
 * @author ThinkGem
 * @version 2015-3-16
 */
public class FileUtils extends org.apache.commons.io.FileUtils implements Constants {
	
	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	/**
	 * 复制单个文件，如果目标文件存在，则不覆盖
	 * 
	 * @param srcFileName 待复制的文件名
	 * @param descFileName 目标文件名
	 * @return 如果复制成功，则返回true，否则返回false
	 */
	public static boolean copyFile(String srcFileName, String descFileName) {
		return copyFileCover(srcFileName, descFileName, false);
	}

	/**
	 * 复制单个文件
	 * @param srcFileName 待复制的文件名
	 * @param descFileName 目标文件名
	 * @param coverlay 如果目标文件已存在，是否覆盖
	 * @return 如果复制成功，则返回true，否则返回false
	 */
	public static boolean copyFileCover(String srcFileName, String descFileName, boolean coverlay) {
		File srcFile = new File(srcFileName);
		// 判断源文件是否存在
		if (!srcFile.exists()) {
			logger.debug("复制文件失败，源文件 " + srcFileName + " 不存在!");
			return false;
		}
		// 判断源文件是否是合法的文件
		else if (!srcFile.isFile()) {
			logger.debug("复制文件失败，" + srcFileName + " 不是一个文件!");
			return false;
		}
		File descFile = new File(descFileName);
		// 判断目标文件是否存在
		if (descFile.exists()) {
			// 如果目标文件存在，并且允许覆盖
			if (coverlay) {
				logger.debug("目标文件已存在，准备删除!");
				if (!FileUtils.delFile(descFileName)) {
					logger.debug("删除目标文件 " + descFileName + " 失败!");
					return false;
				}
			} else {
				logger.debug("复制文件失败，目标文件 " + descFileName + " 已存在!");
				return false;
			}
		} else {
			if (!descFile.getParentFile().exists()) {
				// 如果目标文件所在的目录不存在，则创建目录
				logger.debug("目标文件所在的目录不存在，创建目录!");
				// 创建目标文件所在的目录
				if (!descFile.getParentFile().mkdirs()) {
					logger.debug("创建目标文件所在的目录失败!");
					return false;
				}
			}
		}

		// 准备复制文件
		// 读取的位数
		int readByte = 0;
		InputStream ins = null;
		OutputStream outs = null;
		try {
			// 打开源文件
			ins = new FileInputStream(srcFile);
			// 打开目标文件的输出流
			outs = new FileOutputStream(descFile);
			byte[] buf = new byte[1024];
			// 一次读取1024个字节，当readByte为-1时表示文件已经读取完毕
			while ((readByte = ins.read(buf)) != -1) {
				// 将读取的字节流写入到输出流
				outs.write(buf, 0, readByte);
			}
			logger.debug("复制单个文件{}到{}成功!", srcFileName, descFileName);
			return true;
		} catch (Exception e) {
			logger.debug("复制文件失败：" + e.getMessage());
			return false;
		} finally {
			IOUtils.closeQuietly(outs);
			IOUtils.closeQuietly(ins);
		}
	}

	/**
	 * 复制整个目录的内容，如果目标目录存在，则不覆盖
	 * @param srcDirName 源目录名
	 * @param descDirName 目标目录名
	 * @return 如果复制成功返回true，否则返回false
	 */
	public static boolean copyDirectory(String srcDirName, String descDirName) {
		return copyDirectoryCover(srcDirName, descDirName, false);
	}

	/**
	 * 复制整个目录的内容 
	 * @param srcDirName 源目录名
	 * @param descDirName 目标目录名
	 * @param coverlay 如果目标目录存在，是否覆盖
	 * @return 如果复制成功返回true，否则返回false
	 */
	public static boolean copyDirectoryCover(String srcDirName, String descDirName, boolean coverlay) {
		File srcDir = new File(srcDirName);
		// 判断源目录是否存在
		if (!srcDir.exists()) {
			logger.debug("复制目录失败，源目录 " + srcDirName + " 不存在!");
			return false;
		}
		// 判断源目录是否是目录
		else if (!srcDir.isDirectory()) {
			logger.debug("复制目录失败，" + srcDirName + " 不是一个目录!");
			return false;
		}
		// 如果目标文件夹名不以文件分隔符结尾，自动添加文件分隔符
		String descDirNames = descDirName;
		if (!descDirNames.endsWith(File.separator)) {
			descDirNames = descDirNames + File.separator;
		}
		File descDir = new File(descDirNames);
		// 如果目标文件夹存在
		if (descDir.exists()) {
			if (coverlay) {
				// 允许覆盖目标目录
				logger.debug("目标目录已存在，准备删除!");
				if (!FileUtils.delFile(descDirNames)) {
					logger.debug("删除目录 {} 失败!", descDirNames);
					return false;
				}
			} else {
				logger.debug("目标目录复制失败，目标目录 {} 已存在!", descDirNames);
				return false;
			}
		} else {
			// 创建目标目录
			logger.debug("目标目录不存在，准备创建!");
			if (!descDir.mkdirs()) {
				logger.debug("创建目标目录失败!");
				return false;
			}
		}

		boolean flag = true;
		// 列出源目录下的所有文件名和子目录名
		File[] files = srcDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 如果是一个单个文件，则直接复制
			if (files[i].isFile()) {
				flag = copyFile(files[i].getAbsolutePath(), descDirNames + files[i].getName());
				// 如果拷贝文件失败，则退出循环
				if (!flag) {
					break;
				}
			}
			// 如果是子目录，则继续复制目录
			if (files[i].isDirectory()) {
				flag = copyDirectory(files[i].getAbsolutePath(), descDirNames + files[i].getName());
				// 如果拷贝目录失败，则退出循环
				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {
			logger.debug("复制目录 {} 到 {} 失败!", srcDirName, descDirNames);
			return false;
		}
		logger.debug("复制目录 {} 到 {} 成功!", srcDirName, descDirNames);
		return true;
	}

	/**
	 * 
	 * 删除文件，可以删除单个文件或文件夹
	 * 
	 * @param fileName 被删除的文件名
	 * @return 如果删除成功，则返回true，否是返回false
	 */
	public static boolean delFile(String fileName) {
 		File file = new File(fileName);
		if (!file.exists()) {
			logger.debug(fileName + " 文件不存在!");
			return true;
		} else {
			if (file.isFile()) {
				return FileUtils.deleteFile(fileName);
			} else {
				return FileUtils.deleteDirectory(fileName);
			}
		}
	}

	/**
	 * 
	 * 删除单个文件
	 * 
	 * @param fileName 被删除的文件名
	 * @return 如果删除成功，则返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				logger.debug("删除文件 " + fileName + " 成功!");
				return true;
			} else {
				logger.debug("删除文件 " + fileName + " 失败!");
				return false;
			}
		} else {
			logger.debug(fileName + " 文件不存在!");
			return true;
		}
	}

	/**
	 * 
	 * 删除目录及目录下的文件
	 * 
	 * @param dirName 被删除的目录所在的文件路径
	 * @return 如果目录删除成功，则返回true，否则返回false
	 */
	public static boolean deleteDirectory(String dirName) {
		String dirNames = dirName;
		if (!dirNames.endsWith(File.separator)) {
			dirNames = dirNames + File.separator;
		}
		File dirFile = new File(dirNames);
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			logger.debug(dirNames + " 目录不存在!");
			return true;
		}
		boolean flag = true;
		// 列出全部文件及子目录
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = FileUtils.deleteFile(files[i].getAbsolutePath());
				// 如果删除文件失败，则退出循环
				if (!flag) {
					break;
				}
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				flag = FileUtils.deleteDirectory(files[i].getAbsolutePath());
				// 如果删除子目录失败，则退出循环
				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {
			logger.debug("删除目录失败!");
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			logger.debug("删除目录 {} 成功!", dirNames);
			return true;
		} else {
			logger.debug("删除目录 {} 失败!", dirNames);
			return false;
		}

	}
	
	/**
	 * 将文件目录字符串拼接起来
	 * 
	 * @param paths 文件目录字符串数组
	 * @return 拼接后的字符串 
	 */
	public static String joinPath(String...paths) {
		StringBuilder pathStr = new StringBuilder();
		for (int i = 0, c = ArrayUtils.getSize(paths); i < c; i++) {
			String path = ArrayUtils.getValue(paths, i).replace("/", File.separator).replace("\\", File.separator);
			if (i == 0) {
				// 在尾部追加 File.separator
				if (path.lastIndexOf(File.separator) == path.length()-1) {
					pathStr.append(path);
				} else {
					pathStr.append(path).append(File.separator);
				}
			} else if (i == c-1) {
				// 去掉首部的 File.separator
				if (path.indexOf(File.separator) == 0) {
					path = path.substring(1);
				}
				pathStr.append(path);
			} else {
				// 去掉首部的 File.separator
				if (path.indexOf(File.separator) == 0) {
					path = path.substring(1);
				}
				// 在尾部追加 File.separator
				if (path.lastIndexOf(File.separator) == path.length()-1) {
					pathStr.append(path);
				} else {
					pathStr.append(path).append(File.separator);	
				}
			}
		}
		return pathStr.toString();
	}

	/**
	 * 创建单个文件
	 * @param descFileName 文件名，包含路径
	 * @return 如果创建成功，则返回true，否则返回false
	 */
	public static boolean createFile(String descFileName) {
		File file = new File(descFileName);
		if (file.exists()) {
			logger.debug("文件 " + descFileName + " 已存在!");
			return false;
		}
		if (descFileName.endsWith(File.separator)) {
			logger.debug(descFileName + " 为目录，不能创建目录!");
			return false;
		}
		if (!file.getParentFile().exists()) {
			// 如果文件所在的目录不存在，则创建目录
			if (!file.getParentFile().mkdirs()) {
				logger.debug("创建文件所在的目录失败!");
				return false;
			}
		}

		// 创建文件
		try {
			if (file.createNewFile()) {
				logger.debug(descFileName + " 文件创建成功!");
				return true;
			} else {
				logger.debug(descFileName + " 文件创建失败!");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(descFileName + " 文件创建失败!");
			return false;
		}

	}

	/**
	 * 创建目录
	 * @param descDirName 目录名,包含路径
	 * @return 如果创建成功，则返回true，否则返回false
	 */
	public static boolean createDirectory(String descDirName) {
		String descDirNames = descDirName;
		if (!descDirNames.endsWith(File.separator)) {
			descDirNames = descDirNames + File.separator;
		}
		File descDir = new File(descDirNames);
		if (descDir.exists()) {
			logger.debug("目录 {} 已存在!", descDirNames);
			return false;
		}
		// 创建目录
		if (descDir.mkdirs()) {
			logger.debug("目录 {} 创建成功!", descDirNames);
			return true;
		} else {
			logger.debug("目录 {} 创建失败!", descDirNames);
			return false;
		}

	}
	
	/**
	 * 压缩文件或目录
	 * @param srcDirName 压缩的根目录
	 * @param fileName 根目录下的待压缩的文件名或文件夹名，其中*或""表示跟目录下的全部文件
	 * @param excludePath String[] 排除的文件路径
	 * @param excludeFile String[] 排除的文件名称
	 * @param descFile 目标zip文件
	 */
	public static void zipFiles(String srcDirName, String fileName, String[] excludePath, String[] excludeFile, File descFile) {
		// 判断目录是否存在
		if (srcDirName == null) {
			logger.debug("文件压缩失败，目录 {} 不存在!", srcDirName);
			return;
		}
		File fileDir = new File(srcDirName);
		if (!fileDir.exists() || !fileDir.isDirectory()) {
			logger.debug("文件压缩失败，目录 {} 不存在!", srcDirName);
			return;
		}
		String dirPath = fileDir.getAbsolutePath();
		try {
			ZipOutputStream zouts = new ZipOutputStream(new FileOutputStream(descFile));
			if ("*".equals(fileName) || "".equals(fileName)) {
				zipDirectoryToZipFile(dirPath, fileDir, excludePath, excludeFile, zouts);
			} else {
				File file = new File(fileDir, fileName);
				if (file.isFile()) {
					zipFilesToZipFile(dirPath, file, zouts);
				} else {
					zipDirectoryToZipFile(dirPath, file, excludePath, excludeFile, zouts);
				}
			}
			zouts.close();
			logger.debug(descFile.getName() + " 文件压缩成功!");
		} catch (Exception e) {
			logger.debug("文件压缩失败："+ExceptionUtils.getExceptionMsg(e));
		}
	}
	
	/**
	 * 将文件压缩成zip, 并返回zip文件
	 * 
	 * @param file File 原始文件
	 * @return  File 原始文件带上 .zip 后缀后返回
	 */
	public static File zipFile(File file) {
		if (file.exists()) {
			String filePath = file.getParent();
			try {
				File zipFile = new File(filePath+File.separator+file.getName()+".zip");
				ZipOutputStream zouts = new ZipOutputStream(new FileOutputStream(zipFile));
				zipFilesToZipFile(filePath, file, zouts);
				zouts.close();
				return zipFile;
			} catch (Exception e) {
				logger.error("zip file fail" + e);
				return null;
			}
		} else {
			throw new RuntimeException("file not exits");
		}
	}
	
	/**
	 * 解压zip压缩文件到指定目录
	 * 
	 * @param zipFile zip原始文件
	 * @param dirPath 解压到哪个目录
	 * @return boolean true:解压成功， false:失败
	 */
	public static boolean unzipFile(File zipFile, File dirPath) {
		if (!dirPath.exists()) {
			createDirectory(dirPath.getAbsolutePath());
		}
		if (zipFile.exists()) {
			try {
				ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));

	            BufferedOutputStream bos = null;
	            ZipEntry entry = null;
	            int b = 0;
	            byte[] buffer = new byte[1024];
	            while ((entry=zis.getNextEntry()) != null) {
	            	String entryName = entry.getName();
	            	File unzipFile = new File(dirPath.getAbsolutePath() + File.separator + entryName);
	            	createFile(dirPath.getAbsolutePath() + File.separator + entryName);
	            	
	                bos = new BufferedOutputStream(new FileOutputStream(unzipFile));
	                while ((b = zis.read(buffer)) != -1) {
	                	bos.write(buffer, 0, b);
	                }
	                bos.flush();
	                bos.close();
	            }
	            zis.close();
	            return true;
			} catch (IOException e) {
				logger.error("FileUtils upZipFile 出现异常", e);
				return false;
			}
		} else {
			throw new RuntimeException(zipFile.getAbsolutePath() + "zip 文件不存在");
		}
	}

	/**
	 * 将目录压缩到ZIP输出流
	 * @param dirPath 目录路径
	 * @param fileDir 文件信息
	 * @param excludePath String[] 排除的文件路径
	 * @param excludeFile String[] 排除的文件名称
	 * @param zouts 输出流
	 */
	public static void zipDirectoryToZipFile(String dirPath, File fileDir, String[] excludePath, String[] excludeFile, ZipOutputStream zouts) {
		if (fileDir.isDirectory()) {
			File[] files = fileDir.listFiles();
			// 空的文件夹
			if (files.length == 0) {
				// 目录信息
				ZipEntry entry = new ZipEntry(getEntryName(dirPath, fileDir));
				try {
					zouts.putNextEntry(entry);
					zouts.closeEntry();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			}

			for (int i = 0; i < files.length; i++) {
				File tempFile = files[i]; 
				if (tempFile.isFile()) {
					if (ArrayUtils.contains(excludeFile, tempFile.getName())) {
						continue;
					}
					// 如果是文件，则调用文件压缩方法
					zipFilesToZipFile(dirPath, files[i], zouts);
				} else {
					if (ArrayUtils.contains(excludePath, tempFile.getName())) {
						continue;
					}
					// 如果是目录，则递归调用
					zipDirectoryToZipFile(dirPath, files[i], excludePath, excludeFile, zouts);
				}
			}
		}
	}

	/**
	 * 将文件压缩到ZIP输出流
	 * @param dirPath 目录路径
	 * @param file 文件
	 * @param zouts 输出流
	 */
	public static void zipFilesToZipFile(String dirPath, File file, ZipOutputStream zouts) {
		FileInputStream fin = null;
		ZipEntry entry = null;
		// 创建复制缓冲区
		byte[] buf = new byte[4096];
		int readByte = 0;
		if (file.isFile()) {
			try {
				// 创建一个文件输入流
				fin = new FileInputStream(file);
				// 创建一个ZipEntry
				entry = new ZipEntry(getEntryName(dirPath, file));
				// 存储信息到压缩文件
				zouts.putNextEntry(entry);
				// 复制字节到压缩文件
				while ((readByte = fin.read(buf)) != -1) {
					zouts.write(buf, 0, readByte);
				}
				zouts.closeEntry();
				fin.close();
				System.out.println("添加文件 " + file.getAbsolutePath() + " 到zip文件中!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取待压缩文件在ZIP文件中entry的名字，即相对于跟目录的相对路径名
	 * @param dirPat 目录名
	 * @param file entry文件名
	 * @return
	 */
	private static String getEntryName(String dirPath, File file) {
		String dirPaths = dirPath;
		if (!dirPaths.endsWith(File.separator)) {
			dirPaths = dirPaths + File.separator;
		}
		String filePath = file.getAbsolutePath();
		// 对于目录，必须在entry名字后面加上"/"，表示它将以目录项存储
		if (file.isDirectory()) {
			filePath += "/";
		}
		int index = filePath.indexOf(dirPaths);

		return filePath.substring(index + dirPaths.length());
	}

	/**
	 * 根据“文件名的后缀”获取文件内容类型（而非根据File.getContentType()读取的文件类型）
	 * @param returnFileName 带验证的文件名
	 * @return 返回文件类型
	 */
	public static String getContentType(String returnFileName) {
		String contentType = "application/octet-stream";
		if (returnFileName.lastIndexOf(".") < 0)
			return contentType;
		returnFileName = returnFileName.toLowerCase();
		returnFileName = returnFileName.substring(returnFileName.lastIndexOf(".") + 1);
		if (returnFileName.equals("html") || returnFileName.equals("htm") || returnFileName.equals("shtml")) {
			contentType = "text/html";
		} else if (returnFileName.equals("apk")) {
			contentType = "application/vnd.android.package-archive";
		} else if (returnFileName.equals("sis")) {
			contentType = "application/vnd.symbian.install";
		} else if (returnFileName.equals("sisx")) {
			contentType = "application/vnd.symbian.install";
		} else if (returnFileName.equals("exe")) {
			contentType = "application/x-msdownload";
		} else if (returnFileName.equals("msi")) {
			contentType = "application/x-msdownload";
		} else if (returnFileName.equals("css")) {
			contentType = "text/css";
		} else if (returnFileName.equals("xml")) {
			contentType = "text/xml";
		} else if (returnFileName.equals("gif")) {
			contentType = "image/gif";
		} else if (returnFileName.equals("jpeg") || returnFileName.equals("jpg")) {
			contentType = "image/jpeg";
		} else if (returnFileName.equals("js")) {
			contentType = "application/x-javascript";
		} else if (returnFileName.equals("atom")) {
			contentType = "application/atom+xml";
		} else if (returnFileName.equals("rss")) {
			contentType = "application/rss+xml";
		} else if (returnFileName.equals("mml")) {
			contentType = "text/mathml";
		} else if (returnFileName.equals("txt")) {
			contentType = "text/plain";
		} else if (returnFileName.equals("jad")) {
			contentType = "text/vnd.sun.j2me.app-descriptor";
		} else if (returnFileName.equals("wml")) {
			contentType = "text/vnd.wap.wml";
		} else if (returnFileName.equals("htc")) {
			contentType = "text/x-component";
		} else if (returnFileName.equals("png")) {
			contentType = "image/png";
		} else if (returnFileName.equals("tif") || returnFileName.equals("tiff")) {
			contentType = "image/tiff";
		} else if (returnFileName.equals("wbmp")) {
			contentType = "image/vnd.wap.wbmp";
		} else if (returnFileName.equals("ico")) {
			contentType = "image/x-icon";
		} else if (returnFileName.equals("jng")) {
			contentType = "image/x-jng";
		} else if (returnFileName.equals("bmp")) {
			contentType = "image/x-ms-bmp";
		} else if (returnFileName.equals("svg")) {
			contentType = "image/svg+xml";
		} else if (returnFileName.equals("jar") || returnFileName.equals("var") 
				|| returnFileName.equals("ear")) {
			contentType = "application/java-archive";
		} else if (returnFileName.equals("doc")) {
			contentType = "application/msword";
		} else if (returnFileName.equals("pdf")) {
			contentType = "application/pdf";
		} else if (returnFileName.equals("rtf")) {
			contentType = "application/rtf";
		} else if (returnFileName.equals("xls")) {
			contentType = "application/vnd.ms-excel";
		} else if (returnFileName.equals("ppt")) {
			contentType = "application/vnd.ms-powerpoint";
		} else if (returnFileName.equals("7z")) {
			contentType = "application/x-7z-compressed";
		} else if (returnFileName.equals("rar")) {
			contentType = "application/x-rar-compressed";
		} else if (returnFileName.equals("swf")) {
			contentType = "application/x-shockwave-flash";
		} else if (returnFileName.equals("rpm")) {
			contentType = "application/x-redhat-package-manager";
		} else if (returnFileName.equals("der") || returnFileName.equals("pem")
				|| returnFileName.equals("crt")) {
			contentType = "application/x-x509-ca-cert";
		} else if (returnFileName.equals("xhtml")) {
			contentType = "application/xhtml+xml";
		} else if (returnFileName.equals("zip")) {
			contentType = "application/zip";
		} else if (returnFileName.equals("mid") || returnFileName.equals("midi") 
				|| returnFileName.equals("kar")) {
			contentType = "audio/midi";
		} else if (returnFileName.equals("mp3")) {
			contentType = "audio/mpeg";
		} else if (returnFileName.equals("ogg")) {
			contentType = "audio/ogg";
		} else if (returnFileName.equals("m4a")) {
			contentType = "audio/x-m4a";
		} else if (returnFileName.equals("ra")) {
			contentType = "audio/x-realaudio";
		} else if (returnFileName.equals("3gpp")
				|| returnFileName.equals("3gp")) {
			contentType = "video/3gpp";
		} else if (returnFileName.equals("mp4")) {
			contentType = "video/mp4";
		} else if (returnFileName.equals("mpeg")
				|| returnFileName.equals("mpg")) {
			contentType = "video/mpeg";
		} else if (returnFileName.equals("mov")) {
			contentType = "video/quicktime";
		} else if (returnFileName.equals("flv")) {
			contentType = "video/x-flv";
		} else if (returnFileName.equals("m4v")) {
			contentType = "video/x-m4v";
		} else if (returnFileName.equals("mng")) {
			contentType = "video/x-mng";
		} else if (returnFileName.equals("asx") || returnFileName.equals("asf")) {
			contentType = "video/x-ms-asf";
		} else if (returnFileName.equals("wmv")) {
			contentType = "video/x-ms-wmv";
		} else if (returnFileName.equals("avi")) {
			contentType = "video/x-msvideo";
		}
		return contentType;
	}
	
	/**
	 * 向浏览器发送文件下载，支持断点续传
	 * @param file 要下载的文件
	 * @param request 请求对象
	 * @param response 响应对象
	 * @return 返回错误信息，无错误信息返回null
	 */
	public static String downFile(File file, HttpServletRequest request, HttpServletResponse response){
		 return downFile(file, request, response, null);
	}
	
	/**
	 * 向浏览器发送文件下载，支持断点续传
	 * @param file 要下载的文件
	 * @param request 请求对象
	 * @param response 响应对象
	 * @param fileName 指定下载的文件名
	 * @return 返回错误信息，无错误信息返回null
	 */
	public static String downFile(File file, HttpServletRequest request, HttpServletResponse response, String fileName){
		String error  = null;
		if (file != null && file.exists()) {
			if (file.isFile()) {
				if (file.length() <= 0) {
					error = "该文件是一个空文件。";
				}
				if (!file.canRead()) {
					error = "该文件没有读取权限。";
				}
			} else {
				error = "该文件是一个文件夹。";
			}
		} else {
			error = "文件已丢失或不存在！";
		}
		if (error != null){
			logger.debug("---------------" + file + " " + error);
			return error;
		}

		long fileLength = file.length(); // 记录文件大小
		long pastLength = 0; 	// 记录已下载文件大小
		int rangeSwitch = 0; 	// 0：从头开始的全文下载；1：从某字节开始的下载（bytes=27000-）；2：从某字节开始到某字节结束的下载（bytes=27000-39000）
		long toLength = 0; 		// 记录客户端需要下载的字节段的最后一个字节偏移量（比如bytes=27000-39000，则这个值是为39000）
		long contentLength = 0; // 客户端请求的字节总量
		String rangeBytes = ""; // 记录客户端传来的形如“bytes=27000-”或者“bytes=27000-39000”的内容
		RandomAccessFile raf = null; // 负责读取数据
		OutputStream os = null; 	// 写出数据
		OutputStream out = null; 	// 缓冲
		byte b[] = new byte[1024]; 	// 暂存容器

		if (request.getHeader("Range") != null) { // 客户端请求的下载的文件块的开始字节
			response.setStatus(javax.servlet.http.HttpServletResponse.SC_PARTIAL_CONTENT);
			logger.debug("request.getHeader(\"Range\") = " + request.getHeader("Range"));
			rangeBytes = request.getHeader("Range").replaceAll("bytes=", "");
			if (rangeBytes.indexOf('-') == rangeBytes.length() - 1) {// bytes=969998336-
				rangeSwitch = 1;
				rangeBytes = rangeBytes.substring(0, rangeBytes.indexOf('-'));
				pastLength = Long.parseLong(rangeBytes.trim());
				contentLength = fileLength - pastLength; // 客户端请求的是 969998336  之后的字节
			} else { // bytes=1275856879-1275877358
				rangeSwitch = 2;
				String temp0 = rangeBytes.substring(0, rangeBytes.indexOf('-'));
				String temp2 = rangeBytes.substring(rangeBytes.indexOf('-') + 1, rangeBytes.length());
				pastLength = Long.parseLong(temp0.trim()); // bytes=1275856879-1275877358，从第 1275856879 个字节开始下载
				toLength = Long.parseLong(temp2); // bytes=1275856879-1275877358，到第 1275877358 个字节结束
				contentLength = toLength - pastLength; // 客户端请求的是 1275856879-1275877358 之间的字节
			}
		} else { // 从开始进行下载
			contentLength = fileLength; // 客户端要求全文下载
		}

		// 如果设设置了Content-Length，则客户端会自动进行多线程下载。如果不希望支持多线程，则不要设置这个参数。 响应的格式是:
		// Content-Length: [文件的总大小] - [客户端请求的下载的文件块的开始字节]
		// ServletActionContext.getResponse().setHeader("Content- Length", new Long(file.length() - p).toString());
		response.reset(); // 告诉客户端允许断点续传多线程连接下载,响应的格式是:Accept-Ranges: bytes
		if (pastLength != 0) {
			response.setHeader("Accept-Ranges", "bytes");// 如果是第一次下,还没有断点续传,状态是默认的 200,无需显式设置;响应的格式是:HTTP/1.1 200 OK
			// 不是从最开始下载, 响应的格式是: Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小]
			logger.debug("---------------不是从开始进行下载！服务器即将开始断点续传...");
			switch (rangeSwitch) {
				case 1: { // 针对 bytes=27000- 的请求
					String contentRange = new StringBuffer("bytes ").append(new Long(pastLength).toString()).append("-")
							.append(new Long(fileLength - 1).toString()).append("/").append(new Long(fileLength).toString()).toString();
					response.setHeader("Content-Range", contentRange);
					break;
				}
				case 2: { // 针对 bytes=27000-39000 的请求
					String contentRange = rangeBytes + "/" + new Long(fileLength).toString();
					response.setHeader("Content-Range", contentRange);
					break;
				}
				default: {
					break;
				}
			}
		} else {
			// 是从开始下载
			logger.debug("---------------是从开始进行下载！");
		}

		try {
			response.addHeader("Content-Disposition", "attachment; filename=\"" + 
					EncodesUtils.urlEncode(StringUtils.isBlank(fileName) ? file.getName() : fileName) + "\"");
			response.setContentType(getContentType(file.getName())); // set the MIME type.
			response.addHeader("Content-Length", String.valueOf(contentLength));
			os = response.getOutputStream();
			out = new BufferedOutputStream(os);
			raf = new RandomAccessFile(file, "r");
			try {
				switch (rangeSwitch) {
					case 0: { // 普通下载，或者从头开始的下载 同1
					}
					case 1: { // 针对 bytes=27000- 的请求
						raf.seek(pastLength); // 形如 bytes=969998336- 的客户端请求，跳过 969998336 个字节
						int n = 0;
						while ((n = raf.read(b, 0, 1024)) != -1) {
							out.write(b, 0, n);
						}
						break;
					}
					case 2: { // 针对 bytes=27000-39000 的请求
						raf.seek(pastLength); // 形如 bytes=1275856879-1275877358 的客户端请求，找到第 1275856879 个字节
						int n = 0;
						long readLength = 0; // 记录已读字节数
						while (readLength <= contentLength - 1024) {// 大部分字节在这里读取
							n = raf.read(b, 0, 1024);
							readLength += 1024;
							out.write(b, 0, n);
						}
						if (readLength <= contentLength) { // 余下的不足 1024 个字节在这里读取
							n = raf.read(b, 0, (int) (contentLength - readLength));
							out.write(b, 0, n);
						}
						break;
					}
					default: {
						break;
					}
				}
				out.flush();
				logger.debug("---------------下载完成！");
			} catch (IOException ie) {
				/**
				 * 在写数据的时候， 对于 ClientAbortException 之类的异常，
				 * 是因为客户端取消了下载，而服务器端继续向浏览器写入数据时， 抛出这个异常，这个是正常的。
				 * 尤其是对于迅雷这种吸血的客户端软件， 明明已经有一个线程在读取 bytes=1275856879-1275877358，
				 * 如果短时间内没有读取完毕，迅雷会再启第二个、第三个。。。线程来读取相同的字节段， 直到有一个线程读取完毕，迅雷会 KILL
				 * 掉其他正在下载同一字节段的线程， 强行中止字节读出，造成服务器抛 ClientAbortException。
				 * 所以，我们忽略这种异常
				 */
				logger.debug("提醒：向客户端传输时出现IO异常，但此异常是允许的，有可能客户端取消了下载，导致此异常，不用关心！");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return null;
	}

	/**
	 * 修正路径，将 \\ 或 / 等替换为 File.separator
	 * @param path 待修正的路径
	 * @return 修正后的路径
	 */
	public static String path(String path){
		String p = StringUtils.replace(path, "\\", "/");
		p = StringUtils.join(StringUtils.split(p, "/"), "/");
		if (!StringUtils.startsWithAny(p, "/") && StringUtils.startsWithAny(path, "\\", "/")){
			p += "/";
		}
		if (!StringUtils.endsWithAny(p, "/") && StringUtils.endsWithAny(path, "\\", "/")){
			p = p + "/";
		}
		if (path != null && path.startsWith("/")){
			p = "/" + p; // linux下路径
		}
		return p;
	}
	
	/**
	 * 获取文件扩展名(返回小写)
	 * @param fileName 文件名
	 * @return 例如：test.jpg  返回：  jpg
	 */
	public static String getFileExtension(String fileName) {
		if ((fileName == null) || (fileName.lastIndexOf(".") == -1) || (fileName.lastIndexOf(".") == fileName.length() - 1)) {
			return null;
		}
		return StringUtils.lowerCase(fileName.substring(fileName.lastIndexOf(".") + 1));
	}

	/**
	 * 获取文件名，不包含扩展名
	 * @param fileName 文件名
	 * @return 例如：d:\files\test.jpg  返回：d:\files\test
	 */
	public static String getFileNameWithoutExtension(String fileName) {
		if ((fileName == null) || (fileName.lastIndexOf(".") == -1)) {
			return null;
		}
		return fileName.substring(0, fileName.lastIndexOf("."));
	}
	
	/**
	 * 获取制定目录下的文件列表
	 * @param filepath 目录
	 * @param filelist 文件列表
	 * @param filetype 制定文件类型
	 */
	public static void getFileToList(String filepath, List<File> filelist, String filetype) {
		File[] files = FileUtils.listFiles(filepath);
		if (files == null) {
			return;
		}
		for (File f : files) {
			File file = f.getAbsoluteFile();
			if (file.isFile()) {
				String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);
				if (filetype != null && !filetype.equalsIgnoreCase(suffix)) {
					continue;
				}
				filelist.add(file);
			} else {
				getFileToList(file.getAbsolutePath(), filelist, filetype);
			}
		}
	}
	
	/**
	 * 从指定目录获取指定名称的文件
	 * @param filepath 目录
	 * @param fileName 名称
	 * @return File 目标文件
	 */
	public static File getFileFromPath(String filepath, String fileName) {
		File[] files = FileUtils.listFiles(filepath);
		if (files == null) {
			return null;
		}
		for (File file : files) {
			if (file.getName().equals(fileName)) {
				return file;
			}
		}
		return null;
	}
	
	public static void fileCopy(File sourcefile, File destfile) throws Exception {
		if (sourcefile == null) {
			throw new IllegalArgumentException("source file is null");
		}
		if (destfile == null) {
			throw new IllegalArgumentException("destination file is null");
		}
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(sourcefile);
			fos = new FileOutputStream(destfile);
			byte[] b = new byte[1024 * 10 * 10];
			int temp = 0;
			while ((temp = fis.read(b)) != -1) {
				fos.write(b, 0, temp);
			}
			fos.flush();
		} catch (Exception e) {
			throw new Exception("copy file to destination occurrence error", e);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e2) {
				throw new Exception("copy file to destination when close stream occurrence error", e2);
			}
		}
	}
	
	public static File[] listFiles(String filepath) {
		if (filepath == null) {
			throw new IllegalArgumentException("the file path is null");
		}
		File file = new File(filepath);
		if (file.isDirectory()) {
			return file.listFiles();
		}
		return null;
	}
	
	public static String readFileContent(File file, String charset) throws Exception {
		if (file == null) {
			throw new IllegalArgumentException("the file object is null");
		} else {
			try {
				return IOUtils.getString(new FileInputStream(file), charset);
			} catch (FileNotFoundException e) {
				throw new FileNotFoundException("the " + file.getName() + " not found");
			} catch (Exception e) {
				throw new Exception("read " + file.getName() + " content occurence error");
			}
		}
	}
	
	/**
	 * @param file 目标文件
	 * @param str 写入文件的内容
	 * @param append 是否追加
	 * @param encoding 内容编码
	 */
    public static void writeStringToFile(File file, String str, boolean append, String encoding) {
    	OutputStreamWriter writer = null;
    	try {
    		if (file.getParentFile() != null && !file.getParentFile().exists()) {
    			file.getParentFile().mkdirs();
    		}
    		file.createNewFile();
    		writer = new OutputStreamWriter(new FileOutputStream(file, append), encoding);
            writer.write(str);
            writer.flush();
		} catch (Exception e) {
			logger.error("FileUtils writeStringToFile(File file, String str, boolean append, String encoding) has error", e);
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
			}
		}
    }
    
}
