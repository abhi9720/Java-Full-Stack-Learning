package com.frontend.util;

public class BookAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BookAlreadyExistsException() {
		// TODO Auto-generated constructor stub
	}

	public BookAlreadyExistsException(String message) {
		super(message);
	}

}
