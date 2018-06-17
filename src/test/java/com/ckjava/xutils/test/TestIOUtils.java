package com.ckjava.xutils.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import com.ckjava.xutils.Constants;
import com.ckjava.xutils.FileUtils;
import com.ckjava.xutils.IOUtils;

public class TestIOUtils extends IOUtils implements Constants {

	@Test
	public void getString() throws FileNotFoundException {
		long time = System.currentTimeMillis();
		String fileName = time+".txt";
		String content = "sdfslz中文";
		File file = new File(fileName);
		FileUtils.writeStringToFile(file, content, true, CHARSET.UTF8);
		Assert.assertTrue(content.equals(getString(new FileInputStream(file))));
		
		FileUtils.writeStringToFile(file, content, false, CHARSET.GB2312);
		Assert.assertTrue(content.equals(getString(new FileInputStream(file), CHARSET.GB2312)));
		
		Assert.assertTrue(file.delete() == true);
	}

}
