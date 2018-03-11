package com.ckjava.xutils;

public class ExceptionUtils {

	/**
	 * 获取 Exception 信息
	 * 
	 * @param e Throwable 对象
	 * @return String 关于异常的简单描述
	 */
	public static String getExceptionMsg(Throwable e) {
		StringBuilder msg = new StringBuilder();
		msg.append("Exception:").append(e.getClass().getName()).append(",Message:").append(e.getMessage());
		return msg.toString();
	}
}
