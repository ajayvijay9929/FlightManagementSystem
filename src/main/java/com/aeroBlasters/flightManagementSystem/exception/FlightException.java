package com.aeroBlasters.flightManagementSystem.exception;

/**
 * Custom exception class for handling errors related to Flight operations.
 */
public class FlightException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new FlightException with the specified detail message.
     * 
     * @param message the detail message.
     */
    public FlightException(String message) {
        super(message);
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
