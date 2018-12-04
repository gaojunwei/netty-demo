package com.jd.jr.innovation.epl.demo.socket.server.common.exception;

import java.text.MessageFormat;

/**
 * 自定义异常类
 */
public class AppException extends RuntimeException {

	private String key;
	private String info;

	public AppException(String key) {
		super(key);
		this.key = key;
		this.info = key;
	}

	public AppException(String key, String info) {
		super(MessageFormat.format("{0}[{1}]", key, info));
		this.key = key;
		this.info = info;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
