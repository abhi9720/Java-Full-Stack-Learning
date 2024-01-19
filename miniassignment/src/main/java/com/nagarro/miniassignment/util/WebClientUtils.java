package com.nagarro.miniassignment.util;


import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.nagarro.miniassignment.exception.ApiException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebClientUtils {

    public static <T> T handleWebClientError(WebClientResponseException error, String logMessage, String customErrorMessage) {
        HttpStatusCode statusCode = error.getStatusCode();
        if (statusCode.is5xxServerError()) {
            // Log 5xx Server Error
            log.error("5xx Server Error from WebClient: {}", logMessage, error);
        } else {
            // Log unexpected WebClient Error
            log.error("Unexpected WebClient Error: {}", logMessage, error);
        }
        // Throw ApiException with custom error message and status code
        throw new ApiException(error.getMessage()+customErrorMessage, statusCode.value());
    }
}
