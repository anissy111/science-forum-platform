package com.scienceforum.exception;

/**
 * Exception thrown when a requested resource is not found
 * 
 * This is a custom exception that extends RuntimeException.
 * It should be thrown when attempting to access a resource that doesn't exist.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
