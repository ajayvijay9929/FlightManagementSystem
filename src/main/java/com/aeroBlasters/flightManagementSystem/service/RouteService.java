package com.aeroBlasters.flightManagementSystem.service;

import org.springframework.stereotype.Service;

import com.aeroBlasters.flightManagementSystem.bean.Route;

@Service
public class RouteService {
	/**
	 * Creates a return route based on the provided route details.
	 * 
	 * @param route the route to base the return route on.
	 * @return a new Route object representing the return route.
	 */
	public Route createReturnRoute(Route route) {
		Long newId = route.getRouteId() + 1;
		String sourceCode = route.getDestinationAirportCode();
		String destinationCode = route.getSourceAirportCode();
		return new Route(newId, sourceCode, destinationCode, route.getFare());
	}
}
