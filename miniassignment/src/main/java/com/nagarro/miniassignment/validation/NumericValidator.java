package com.nagarro.miniassignment.validation;

public class NumericValidator implements Validator {

	private static volatile NumericValidator instance;

	private NumericValidator() {
	}

	public static NumericValidator getInstance() {
		if (instance == null) {
			synchronized (NumericValidator.class) {
				if (instance == null) {
					instance = new NumericValidator();
				}
			}
		}
		return instance;
	}

	@Override
	public boolean validate(Object value) {
		 try {
	            Integer.parseInt(value.toString());
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	}

}
