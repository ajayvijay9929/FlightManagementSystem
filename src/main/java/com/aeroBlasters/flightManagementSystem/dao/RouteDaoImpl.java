package com.aeroBlasters.flightManagementSystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.aeroBlasters.flightManagementSystem.bean.Route;

/**
 * Implementation of the RouteDao interface.
 * <p>
 * This class provides concrete implementations of the methods
 * defined in the RouteDao interface using the RouteRepository.
 * </p>
 */
@Repository
@Service
public class RouteDaoImpl implements RouteDao {

	@Autowired
	private RouteRepository repository;

	@Override
	public void save(Route route) {
		repository.save(route); // Save or update the route entity in the database
	}

	@Override
	public List<Route> findAllRoutes() {
		return repository.findAll(); // Retrieve all routes from the database
	}

	@Override
	public Route findRouteById(Long id) {
		return repository.findById(id).orElse(null); // Find a route by its ID, return null if not found
	}

	@Override
	public Route findRouteBySourceAndDestination(String source, String destination) {
		return repository.findRouteBySourceAndDestination(source, destination); // Find a route by source and
																				// destination
	}

	@Override
	public Long generateRouteId() {
		Long val = repository.findLastRouteId();
		if (val == null) {
			val = 101L; // Start with ID 101 if no previous IDs exist
		} else {
			val = val + 1; // Increment the last used ID
		}
		return val;
	}

	@Override
	public List<Long> findAllRoutesId() {
		return repository.findAllRoutesId(); // Retrieve all route IDs from the database
	}

	// @Override
	// public void updateRoute(Route route) {
	// repository.save(route); // Update the route entity in the database
	// }

	@Override
	public void deleteRoute(Route route) {
		repository.delete(route); // Delete the route entity from the database
	}
}
