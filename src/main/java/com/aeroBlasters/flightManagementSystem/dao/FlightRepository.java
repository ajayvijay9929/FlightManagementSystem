package com.aeroBlasters.flightManagementSystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aeroBlasters.flightManagementSystem.bean.Flight;

/**
 * Repository interface for managing Flight entities.
 * <p>
 * This interface extends JpaRepository and provides custom
 * queries for Flight entities using JPQL.
 * </p>
 */
public interface FlightRepository extends JpaRepository<Flight, Long> {

    /**
     * Finds all flights associated with a given route ID.
     * 
     * @param routeId the route ID to search for flights.
     * @return a list of flights for the specified route ID.
     */
    @Query("SELECT a FROM Flight a WHERE routeId = ?1")
    public List<Flight> findFlightsByRouteId(Long routeId);

    /**
     * Finds a flight by its flight number.
     * 
     * @param flightNumber the flight number.
     * @return the Flight entity with the specified flight number.
     */
    @Query("SELECT a FROM Flight a WHERE flightNumber = ?1")
    public Flight findFlightByFlightNumber(Long flightNumber);
}
