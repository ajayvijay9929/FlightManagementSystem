package com.aeroBlasters.flightManagementSystem.exception;

/**
 * Custom exception class for handling errors related to Route operations.
 */
public class RouteException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new RouteException with the specified detail message.
     * 
     * @param message the detail message.
     */
    public RouteException(String message) {
        super(message);
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
