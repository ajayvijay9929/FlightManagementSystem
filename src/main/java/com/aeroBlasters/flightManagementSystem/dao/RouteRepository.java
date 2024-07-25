package com.aeroBlasters.flightManagementSystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aeroBlasters.flightManagementSystem.bean.Route;

/**
 * Repository interface for managing Route entities.
 * <p>
 * This interface extends JpaRepository and provides custom
 * queries for Route entities using JPQL.
 * </p>
 */
public interface RouteRepository extends JpaRepository<Route, Long> {

	/**
	 * Finds the highest route ID in the database.
	 * 
	 * @return the highest route ID.
	 */
	@Query("select max(routeId) from Route")
	public Long findLastRouteId();

	/**
	 * Finds a route by its source and destination airport codes.
	 * 
	 * @param sourceAirportCode      the source airport code.
	 * @param destinationAirportCode the destination airport code.
	 * @return the Route entity matching the source and destination.
	 */
	@Query("select a from Route a where sourceAirportCode = ?1 And destinationAirportCode = ?2")
	public Route findRouteBySourceAndDestination(String sourceAirportCode, String destinationAirportCode);

	/**
	 * Retrieves all route IDs from the database.
	 * 
	 * @return a list of all route IDs.
	 */
	@Query("SELECT routeId FROM Route")
	public List<Long> findAllRoutesId();
}
