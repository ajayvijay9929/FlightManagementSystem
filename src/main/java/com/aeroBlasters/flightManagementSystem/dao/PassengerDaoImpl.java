package com.aeroBlasters.flightManagementSystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.aeroBlasters.flightManagementSystem.bean.Passenger;

/**
 * Implementation of the PassengerDao interface.
 * <p>
 * This class provides concrete implementations of the methods
 * defined in the PassengerDao interface using the PassengerRepository.
 * </p>
 */
@Service
@Repository
public class PassengerDaoImpl implements PassengerDao {

    @Autowired
    private PassengerRepository repository;

    @Override
    public void save(Passenger passenger) {
        repository.save(passenger); // Save or update the passenger entity in the database
    }

    @Override
    public List<Passenger> findByTicketId(Long ticketId) {
        // Find all passengers associated with a given ticket ID
        return repository.findByTicketId(ticketId);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id); // Delete a passenger by their ID
    }

    @Override
    public void update(Passenger passenger) {
        if (passenger.getId() == null) {
            throw new IllegalArgumentException("Passenger ID cannot be null for update operation");
        }
        repository.save(passenger); // Update the passenger entity in the database
    }

    @Override
    public void deletePassengerByTicketNumber(Long ticketNumber) {
        repository.deletePassengerByTicketNumber(ticketNumber); // Delete passengers by ticket number
    }

    @Override
    public List<Passenger> findAllPassengers() {
        return repository.findAll(); // Retrieve all passengers from the database
    }
}
