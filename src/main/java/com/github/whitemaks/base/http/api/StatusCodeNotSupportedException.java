package com.github.whitemaks.base.http.api;

public class StatusCodeNotSupportedException extends RuntimeException {

	public StatusCodeNotSupportedException(int statusCode) {
		super("Status code [" + statusCode + "] is not supported");
	}

}
