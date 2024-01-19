package com.nagarro.miniassignment.util;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.nagarro.miniassignment.exception.ValidationException;
import com.nagarro.miniassignment.validation.Validator;
import com.nagarro.miniassignment.validation.ValidatorFactory;

public class UserValidationHelper {


	public static int extractSizeFromPayload(Map<String, Object> payload) {
	    int defaultSize = 1; 
	    System.out.println(payload);

	    if (payload.containsKey("size")) {
	        Object sizeValue = payload.get("size");

	        if (sizeValue instanceof Integer) {
	            return (int) sizeValue;
	        } else if (sizeValue instanceof String) {
	            try {
	                return Integer.parseInt((String) sizeValue);
	            } catch (NumberFormatException e) {
	                throw new ValidationException("Invalid 'size' parameter. It must be a valid integer.");
	            }
	        }
	    }
	    else {
            throw new ValidationException("Payload does not contains size");	
	    }
	    return defaultSize;
	}

	public static  Object validateSizeParameter(int size) {
		System.out.println("Validate size" + size );
	    if (size < 0 || size > 5) {
	        throw new ValidationException("Invalid 'size' parameter. It must be in the range 1 to 5");
	    }
	    return null;
	}

	public static void validateInputParameters(String sortType, String sortOrder, String limit, String offset) {

		Validator alphabetsValidator = ValidatorFactory.createValidator(String.class);
		Validator numericValidator = ValidatorFactory.createValidator(Integer.class);

		List<String> errors = new ArrayList<>();

		// Validate sortType
		if (!alphabetsValidator.validate(sortType)) {
			errors.add("Invalid value for sortType");
		} else if (!"name".equalsIgnoreCase(sortType) && !"age".equalsIgnoreCase(sortType)) {
			errors.add("Invalid value for sortType. It must be either 'name' or 'age'.");
		}

		// Validate sortOrder
		if (!alphabetsValidator.validate(sortOrder)) {
			errors.add("Invalid value for sortOrder");
		} else if (!"odd".equalsIgnoreCase(sortOrder) && !"even".equalsIgnoreCase(sortOrder)) {
			errors.add("Invalid value for sortOrder. It must be either 'odd' or 'even'.");
		}

		// Validate limit
		if (!numericValidator.validate(limit)) {
			errors.add("Invalid value for limit");
		} else {
			int limitValue = Integer.parseInt(limit);
			if (limitValue < 1 || limitValue > 5) {
				errors.add("Limit must be between 1 and 5");
			}
		}

		// Validate offset
		if (!numericValidator.validate(offset)) {
			errors.add("Invalid value for offset");
		} else {
			int offsetValue = Integer.parseInt(offset);
			if (offsetValue < 0) {
				errors.add("Offset must be greater than or equal to 0");
			}
		}

		if (!errors.isEmpty()) {
			throw new ValidationException(String.join(", ", errors));
		}
	}

}
