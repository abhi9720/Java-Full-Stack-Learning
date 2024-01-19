package com.nagarro.miniassignment.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nagarro.miniassignment.exception.ApiException;
import com.nagarro.miniassignment.exception.ValidationException;

import lombok.Getter;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");

	@ExceptionHandler(ApiException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {
		ErrorResponse errorResponse = new ErrorResponse(
				ex.getMessage(),
				ex.getCode(),
				LocalDateTime.now().format(formatter)
				);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex) {

		ErrorResponse errorResponse = new ErrorResponse(
				ex.getMessage(),
				HttpStatus.BAD_REQUEST.value(),
				LocalDateTime.now().format(formatter)
				);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}



	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
		String paramName = ex.getParameterName();
		String errorMessage = "Required request parameter '" + paramName + "' is missing.";
		String timestamp = LocalDateTime.now().format(formatter);

		return new ErrorResponse(errorMessage, HttpStatus.BAD_REQUEST.value(), timestamp);
	}


	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> handleOtherExceptions(Exception ex) {

		ErrorResponse errorResponse = new ErrorResponse(
				ex.getLocalizedMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				LocalDateTime.now().format(formatter)
				);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}



	@Getter
	private static class ErrorResponse {
		private final String message;
		private final int code;
		private final String timestamp;

		public ErrorResponse(String message, int code, String timestamp) {
			this.message = message;
			this.code = code;
			this.timestamp = timestamp;
		}	
	}
}
