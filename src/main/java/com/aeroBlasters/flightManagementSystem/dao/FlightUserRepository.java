package com.aeroBlasters.flightManagementSystem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aeroBlasters.flightManagementSystem.bean.FlightUser;

/**
 * Repository interface for managing FlightUser entities.
 * <p>
 * This interface extends JpaRepository and provides methods for
 * querying FlightUser entities.
 * </p>
 */
public interface FlightUserRepository extends JpaRepository<FlightUser, String> {

	/**
	 * Finds a FlightUser by their username.
	 * 
	 * @param username the username of the FlightUser.
	 * @return an Optional containing the FlightUser if found, or empty if not
	 *         found.
	 */
	Optional<FlightUser> findByUsername(String username);
}
