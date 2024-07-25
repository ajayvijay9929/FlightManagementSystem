package com.aeroBlasters.flightManagementSystem.dao;

import java.util.List;

import com.aeroBlasters.flightManagementSystem.bean.Passenger;

/**
 * Data Access Object interface for managing Passenger entities.
 * <p>
 * This interface defines the methods for CRUD operations and
 * custom queries related to Passenger entities.
 * </p>
 */
public interface PassengerDao {

    /**
     * Saves or updates a passenger entity in the database.
     * 
     * @param passenger the Passenger entity to be saved or updated.
     */
    public void save(Passenger passenger);

    /**
     * Finds all passengers associated with a given ticket ID.
     * 
     * @param ticketId the ticket ID to search for passengers.
     * @return a list of passengers for the specified ticket ID.
     */
    public List<Passenger> findByTicketId(Long ticketId);

    /**
     * Deletes a passenger by their ID.
     * 
     * @param id the ID of the passenger to be deleted.
     */
    public void deleteById(Long id);

    /**
     * Updates a passenger entity in the database.
     * 
     * @param passenger the Passenger entity to be updated.
     */
    public void update(Passenger passenger);

    /**
     * Deletes passengers associated with a given ticket number.
     * 
     * @param ticketNumber the ticket number associated with passengers to be
     *                     deleted.
     */
    public void deletePassengerByTicketNumber(Long ticketNumber);

    /**
     * Retrieves all passengers from the database.
     * 
     * @return a list of all Passenger entities.
     */
    public List<Passenger> findAllPassengers();
}
