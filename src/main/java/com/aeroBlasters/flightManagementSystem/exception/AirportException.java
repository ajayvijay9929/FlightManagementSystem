package com.aeroBlasters.flightManagementSystem.exception;

/**
 * Custom exception class for handling errors related to Airport operations.
 */
public class AirportException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new AirportException with the specified detail message.
     * 
     * @param message the detail message.
     */
    public AirportException(String message) {
        super(message);
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
