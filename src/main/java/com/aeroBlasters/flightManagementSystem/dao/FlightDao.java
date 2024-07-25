package com.aeroBlasters.flightManagementSystem.dao;

import java.util.List;

import com.aeroBlasters.flightManagementSystem.bean.Flight;

/**
 * Data Access Object interface for managing Flight entities.
 * <p>
 * This interface defines the methods for CRUD operations and
 * custom queries related to Flight entities.
 * </p>
 */
public interface FlightDao {

    /**
     * Finds all flights associated with a given route ID.
     * 
     * @param routeId the route ID to search for flights.
     * @return a list of flights for the specified route ID.
     */
    public List<Flight> findFlightsByRouteId(Long routeId);

    /**
     * Retrieves all flights from the database.
     * 
     * @return a list of all Flight entities.
     */
    public List<Flight> findAllFlights();

    /**
     * Saves or updates a flight entity in the database.
     * 
     * @param flight the Flight entity to be saved or updated.
     */
    public void save(Flight flight);

    /**
     * Adds a new flight to the database.
     * 
     * @param flight the Flight entity to be added.
     */
    public void addFlight(Flight flight);

    /**
     * Finds a flight by its ID.
     * 
     * @param id the ID of the flight.
     * @return the Flight entity with the specified ID.
     */
    public Flight findFlightById(Long id);

    /**
     * Finds a flight by its flight number.
     * 
     * @param flightNumber the flight number.
     * @return the Flight entity with the specified flight number.
     */
    public Flight findFlightByFlightNumber(Long flightNumber);

    public void deleteFlight(Flight flight);
}
