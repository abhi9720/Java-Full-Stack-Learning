package com.nagarro.miniassignment.validation;

public class ValidatorFactory {
    public static Validator createValidator(Class<?> type) {
        switch (type.getSimpleName()) {
            case "Integer":
                return NumericValidator.getInstance();
            case "String":
                return EnglishAlphabetsValidator.getInstance();
            default:
                throw new IllegalArgumentException("Unsupported parameter type");
        }
    }
}
