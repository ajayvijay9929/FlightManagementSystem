package com.aeroBlasters.flightManagementSystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aeroBlasters.flightManagementSystem.bean.Airport;

/**
 * Implementation of the AirportDao interface.
 * <p>
 * This class provides concrete implementations of the methods
 * defined in the AirportDao interface using the AirportRepository.
 * </p>
 */
@Repository
public class AirportDaoImpl implements AirportDao {

	@Autowired
	private AirportRepository repository;

	@Override
	public void addAirport(Airport airport) {
		repository.save(airport); // Save the airport entity to the database
	}

	@Override
	public List<Airport> findAllAirports() {
		return repository.findAll(); // Retrieve all airports from the database
	}

	@Override
	public Airport findAirportById(String id) {
		return repository.findById(id).orElse(null); // Find an airport by its code, return null if not found
	}

	@Override
	public List<String> findAllAirportLocations() {
		return repository.findAllAirportLocations(); // Retrieve all airport locations
	}

	@Override
	public String findAirportCodeByLocation(String airportLocation) {
		return repository.findAirportCodeByLocation(airportLocation); // Find airport code by location
	}

	@Override
	public void updateAirport(Airport airport) {
		repository.save(airport); // Update the airport entity in the databased
	}

	@Override
	public void deleteAirport(Airport airport) {
		repository.delete(airport); // Delete the airport entity from the database
	}
}
