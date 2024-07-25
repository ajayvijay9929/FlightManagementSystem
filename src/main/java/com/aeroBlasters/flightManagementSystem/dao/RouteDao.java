package com.aeroBlasters.flightManagementSystem.dao;

import java.util.List;

import com.aeroBlasters.flightManagementSystem.bean.Route;

/**
 * Data Access Object interface for managing Route entities.
 * <p>
 * This interface defines the methods for CRUD operations and
 * custom queries related to Route entities.
 * </p>
 */
public interface RouteDao {

   /**
    * Saves or updates a route entity in the database.
    * 
    * @param route the Route entity to be saved or updated.
    */
   public void save(Route route);

   /**
    * Retrieves all routes from the database.
    * 
    * @return a list of all Route entities.
    */
   public List<Route> findAllRoutes();

   /**
    * Finds a route by its ID.
    * 
    * @param id the ID of the route.
    * @return the Route entity with the specified ID.
    */
   public Route findRouteById(Long id);

   /**
    * Finds a route by its source and destination airport codes.
    * 
    * @param source      the source airport code.
    * @param destination the destination airport code.
    * @return the Route entity matching the source and destination.
    */
   public Route findRouteBySourceAndDestination(String source, String destination);

   /**
    * Generates a new route ID by finding the last used ID and incrementing it.
    * 
    * @return the new route ID.
    */
   public Long generateRouteId();

   /**
    * Retrieves all route IDs from the database.
    * 
    * @return a list of all route IDs.
    */
   public List<Long> findAllRoutesId();

   public void deleteRoute(Route route);
}
