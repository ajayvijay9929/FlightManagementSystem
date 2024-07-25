package com.aeroBlasters.flightManagementSystem.exception;

/**
 * Custom exception class for handling errors related to Ticket operations.
 */
public class TicketException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new TicketException with the specified detail message.
     * 
     * @param message the detail message.
     */
    public TicketException(String message) {
        super(message);
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
