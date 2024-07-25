package com.aeroBlasters.flightManagementSystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aeroBlasters.flightManagementSystem.bean.Ticket;

/**
 * Implementation of the TicketDao interface.
 * <p>
 * This class provides concrete implementations of the methods
 * defined in the TicketDao interface using the TicketRepository.
 * </p>
 */
@Repository
public class TicketDaoImpl implements TicketDao {

    @Autowired
    private TicketRepository repository;

    @Override
    public void save(Ticket ticket) {
        System.out.println("Ticket Number: from DAO IMPL" + ticket.getCarrierName()); // Debug statement
        repository.save(ticket); // Save or update the ticket entity in the database
    }

    @Override
    public Long findLastTicketNumber() {
        Long val = repository.findLastTicketNumber();
        if (val == null) {
            val = 1000001L; // Start with ticket number 1000001 if no previous numbers exist
        }
        return val;
    }

    @Override
    public Ticket findTicketByTicketNumber(Long ticketNumber) {
        return repository.findById(ticketNumber).orElse(null); // Find a ticket by its number, return null if not found
    }

    @Override
    public void deleteTicketByTicketNumber(Long ticketNumber) {
        repository.deleteTicketByTicketNumber(ticketNumber); // Delete a ticket by its number
    }

    @Override
    public void delete(Ticket ticket) {
        repository.delete(ticket); // Delete a ticket entity from the database
    }

    @Override
    public List<Ticket> findAllTickets() {
        return repository.findAll(); // Retrieve all tickets from the database
    }

    @Override
    public List<Ticket> findTicketsByUsername(String username) {
        return repository.findTicketsByUsername(username); // Find all tickets by the username of the passenger
    }
}
