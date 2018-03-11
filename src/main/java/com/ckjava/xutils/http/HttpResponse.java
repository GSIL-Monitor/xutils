package com.ckjava.xutils.http;

import com.ckjava.xutils.ExceptionUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("响应实体的封装")
public class HttpResponse<T> {

	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	public static final String EXCEPTION = "exception";
	
	@ApiModelProperty("api 返回的数据")
	private T data;
	@ApiModelProperty("api 返回的数据状态,包含success,fail,exception")
	private String sign;
	@ApiModelProperty("api 返回的详细描述")
	private String message;
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public HttpResponse(T data, String sign, String message) {
		super();
		this.data = data;
		this.sign = sign;
		this.message = message;
	}
	
	public HttpResponse(Throwable e) {
		super();
		this.data = null;
		this.sign = EXCEPTION;
		this.message = getErrorMsg(e);
	}
	
	public static String getErrorMsg(Throwable e) {
		return ExceptionUtils.getExceptionMsg(e);
	}
	
	public static <T> HttpResponse<T> getReturn(T data, String sign, String message) {
		return new HttpResponse<T>(data, sign, message);
	}
	
	public static <T> HttpResponse<T> getReturn(Throwable e) {
		return new HttpResponse<T>(e);
	}
	
}