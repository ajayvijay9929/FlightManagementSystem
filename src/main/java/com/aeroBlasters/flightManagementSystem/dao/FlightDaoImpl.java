package com.aeroBlasters.flightManagementSystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.aeroBlasters.flightManagementSystem.bean.Flight;

/**
 * Implementation of the FlightDao interface.
 * <p>
 * This class provides concrete implementations of the methods
 * defined in the FlightDao interface using the FlightRepository.
 * </p>
 */
@Service
@Repository
public class FlightDaoImpl implements FlightDao {

    @Autowired
    private FlightRepository repository;

    @Override
    public void save(Flight flight) {
        repository.save(flight); // Save or update the flight entity in the database
    }

    @Override
    public List<Flight> findFlightsByRouteId(Long routeId) {
        return repository.findFlightsByRouteId(routeId); // Find all flights by route ID
    }

    @Override
    public List<Flight> findAllFlights() {
        return repository.findAll(); // Retrieve all flights from the database
    }

    @Override
    public void addFlight(Flight flight) {
        // This method is redundant as save() handles both save and update operations
        // If needed, additional functionality can be implemented here
    }

    @Override
    public Flight findFlightById(Long id) {
        return repository.findById(id).orElse(null); // Find a flight by its ID, return null if not found
    }

    @Override
    public Flight findFlightByFlightNumber(Long flightNumber) {
        System.out.println("FlightDaoImpl: findFlightByFlightNumber() called");
        return repository.findFlightByFlightNumber(flightNumber); // Find a flight by its flight number
    }

    @Override
    public void deleteFlight(Flight flight) {
        repository.delete(flight); // Delete a flight from the database
    }
}
