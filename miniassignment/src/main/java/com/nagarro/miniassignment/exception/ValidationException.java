package com.nagarro.miniassignment.exception;

public class ValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String message;

	public ValidationException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
