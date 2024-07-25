package com.aeroBlasters.flightManagementSystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aeroBlasters.flightManagementSystem.bean.Airport;

/**
 * Repository interface for managing Airport entities.
 * <p>
 * This interface extends JpaRepository and provides custom
 * queries for Airport entities using JPQL.
 * </p>
 */
public interface AirportRepository extends JpaRepository<Airport, String> {

	/**
	 * Retrieves all airport codes from the database.
	 * 
	 * @return a list of all airport codes.
	 */
	@Query("select airportCode from Airport")
	public List<String> findAllAirportCodes();

	/**
	 * Retrieves all airport locations from the database.
	 * 
	 * @return a list of all airport locations.
	 */
	@Query("select airportLocation from Airport")
	public List<String> findAllAirportLocations();

	/**
	 * Finds the airport code by location.
	 * 
	 * @param airportLocation the location of the airport.
	 * @return the airport code for the specified location.
	 */
	@Query("select airportCode from Airport where airportLocation = ?1")
	public String findAirportCodeByLocation(String airportLocation);
}
