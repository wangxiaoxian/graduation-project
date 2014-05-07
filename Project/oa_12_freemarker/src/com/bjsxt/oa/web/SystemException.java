package com.bjsxt.oa.web;

public class SystemException extends RuntimeException {
	
	private String key;

	public SystemException() {
		super();
	}

	public SystemException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
		this(message, cause);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(String message, String key) {
		super(message);
		this.key = key;
	}
	
	public SystemException(Throwable cause) {
		super(cause);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
