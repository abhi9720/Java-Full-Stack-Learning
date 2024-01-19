package com.nagarro.miniassignment.validation;

public class EnglishAlphabetsValidator implements Validator {
	private static volatile EnglishAlphabetsValidator instance;

	private EnglishAlphabetsValidator() {
	}

	public static EnglishAlphabetsValidator getInstance() {
		if (instance == null) {
			synchronized (EnglishAlphabetsValidator.class) {
				if (instance == null) {
					instance = new EnglishAlphabetsValidator();
				}
			}
		}
		return instance;
	}

	@Override
	public boolean validate(Object value) {
		return value != null && value.toString().matches("^[a-zA-Z]+$");
	}
}