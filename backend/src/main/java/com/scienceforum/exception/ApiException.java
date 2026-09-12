package com.scienceforum.exception;

/**
 * General API Exception for application errors
 * 
 * This is a custom exception for handling application-specific errors.
 */
public class ApiException extends RuntimeException {

    private final String errorCode;

    public ApiException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ApiException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
