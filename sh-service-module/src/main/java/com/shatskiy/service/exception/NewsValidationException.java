package com.shatskiy.service.exception;

public class NewsValidationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NewsValidationException() {
		super();
	}

	public NewsValidationException(String message) {
		super(message);
	}

	public NewsValidationException(Exception e) {
		super(e);
	}

	public NewsValidationException(String message, Exception e) {
		super(message, e);
	}
}
