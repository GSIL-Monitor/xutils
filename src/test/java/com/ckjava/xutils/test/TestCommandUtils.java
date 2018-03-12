package com.ckjava.xutils.test;

import org.junit.Test;

import com.ckjava.xutils.CommandUtils;
import com.ckjava.xutils.Constants;
import com.ckjava.xutils.OSUtils;

public class TestCommandUtils {

	@Test
	public void execTask() {
		final StringBuffer output = new StringBuffer();
		// 在一个线程中执行
		Thread task = new Thread(new Runnable() {
			@Override
			public void run() {
				String os = OSUtils.getCurrentOSType();
				String cmd = "cmd /c mvn -version";
				String charset = Constants.CHARSET.GBK;
				if (os.equals(Constants.OSTYPE.LINUX)) {
					cmd = "/bin/bash mvn -version";
					charset = Constants.CHARSET.UTF8;
				}
				
				CommandUtils.execTask(cmd, null, null, charset, output);
			}
		});
		task.start();
		// 等线程执行完毕后再输出最终的结果
		while (true) {
			if (!task.isAlive()) {
				System.out.println(output.toString());
				break;
			} else {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("running");
			}
		}
		
	}
	
}
