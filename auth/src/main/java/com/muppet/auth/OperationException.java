package com.muppet.auth;

public class OperationException extends RuntimeException {

	public OperationException(String message) {
		super(message);
	}

	public OperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public OperationException(Throwable cause) {
		super(cause);
	}

	protected OperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
