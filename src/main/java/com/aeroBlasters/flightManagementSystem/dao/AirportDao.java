package com.aeroBlasters.flightManagementSystem.dao;

import com.aeroBlasters.flightManagementSystem.bean.Airport;

import java.util.List;

/**
 * Data Access Object interface for managing Airport entities.
 * <p>
 * This interface defines the methods for CRUD operations and
 * custom queries related to Airport entities.
 * </p>
 */
public interface AirportDao {

   /**
    * Adds a new airport to the database.
    * 
    * @param airport the Airport entity to be added.
    */
   public void addAirport(Airport airport);

   /**
    * Retrieves all airports from the database.
    * 
    * @return a list of all Airport entities.
    */
   public List<Airport> findAllAirports();

   /**
    * Finds an airport by its code.
    * 
    * @param id the airport code.
    * @return the Airport entity with the specified code.
    */
   public Airport findAirportById(String id);

   /**
    * Retrieves all airport locations from the database.
    * 
    * @return a list of airport locations.
    */
   public List<String> findAllAirportLocations();

   /**
    * Finds the airport code by its location.
    * 
    * @param airportLocation the location of the airport.
    * @return the airport code for the specified location.
    */
   public String findAirportCodeByLocation(String airportLocation);

   public void updateAirport(Airport airport);

   public void deleteAirport(Airport airport);
}
