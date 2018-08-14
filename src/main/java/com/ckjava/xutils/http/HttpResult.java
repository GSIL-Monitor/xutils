package com.ckjava.xutils.http;

import com.ckjava.xutils.ExceptionUtils;

/**
 * 自定义封装 http 请求结果
 */
public class HttpResult {

    private int statusCode;
    private String bodyString;
    private String exceptionString;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBodyString() {
        return bodyString;
    }

    public void setBodyString(String bodyString) {
        this.bodyString = bodyString;
    }

    public String getExceptionString() {
        return exceptionString;
    }

    public void setExceptionString(String exceptionString) {
        this.exceptionString = exceptionString;
    }

    public HttpResult(int statusCode, String bodyString, String exceptionString) {
        this.statusCode = statusCode;
        this.bodyString = bodyString;
        this.exceptionString = exceptionString;
    }

    public HttpResult() {
    }

    public static HttpResult getException(Throwable e) {
        return new HttpResult(500, null, ExceptionUtils.getExceptionMsg(e));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HttpResult{");
        sb.append("statusCode=").append(statusCode);
        sb.append(", bodyString='").append(bodyString).append('\'');
        sb.append(", exceptionString='").append(exceptionString).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
