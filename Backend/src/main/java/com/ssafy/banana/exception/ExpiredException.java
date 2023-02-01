package com.ssafy.banana.exception;

public class ExpiredException extends RuntimeException {
	public ExpiredException() {
		super();
	}

	public ExpiredException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExpiredException(String message) {
		super(message);
	}

	public ExpiredException(Throwable cause) {
		super(cause);
	}
}