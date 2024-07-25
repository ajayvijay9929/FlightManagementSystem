package com.aeroBlasters.flightManagementSystem.exception;

/**
 * Custom exception class for handling errors related to Login operations.
 */
public class LoginException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new LoginException with the specified detail message.
     * 
     * @param message the detail message.
     */
    public LoginException(String message) {
        super(message);
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
