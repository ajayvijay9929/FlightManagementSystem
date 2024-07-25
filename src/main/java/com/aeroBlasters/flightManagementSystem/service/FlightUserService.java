package com.aeroBlasters.flightManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aeroBlasters.flightManagementSystem.bean.FlightUser;
import com.aeroBlasters.flightManagementSystem.dao.FlightUserRepository;

@Service
public class FlightUserService implements UserDetailsService {
	@Autowired
	private FlightUserRepository repository;
	private String type;

	/**
	 * Saves the user details in the database.
	 * 
	 * @param user the FlightUser object containing user details.
	 */
	public void save(FlightUser user) {
		repository.save(user);
	}

	/**
	 * Retrieves the type of the user.
	 * 
	 * @return the type of the user.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Loads user details by username.
	 * 
	 * @param username the username of the user.
	 * @return the UserDetails of the user.
	 * @throws UsernameNotFoundException if the username is not found.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		FlightUser users = repository.findById(username).get();
		type = users.getType();
		return users;
	}
}
