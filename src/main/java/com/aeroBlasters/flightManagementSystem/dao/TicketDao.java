package com.aeroBlasters.flightManagementSystem.dao;

import java.util.List;

import com.aeroBlasters.flightManagementSystem.bean.Ticket;

/**
 * Data Access Object interface for managing Ticket entities.
 * <p>
 * This interface defines the methods for CRUD operations and
 * custom queries related to Ticket entities.
 * </p>
 */
public interface TicketDao {

    /**
     * Saves or updates a ticket entity in the database.
     * 
     * @param ticket the Ticket entity to be saved or updated.
     */
    public void save(Ticket ticket);

    /**
     * Finds the highest ticket number in the database.
     * 
     * @return the highest ticket number.
     */
    public Long findLastTicketNumber();

    /**
     * Finds a ticket by its ticket number.
     * 
     * @param ticketNumber the ticket number to search for.
     * @return the Ticket entity with the specified ticket number.
     */
    public Ticket findTicketByTicketNumber(Long ticketNumber);

    /**
     * Deletes a ticket by its ticket number.
     * 
     * @param ticketNumber the ticket number of the ticket to be deleted.
     */
    public void deleteTicketByTicketNumber(Long ticketNumber);

    /**
     * Deletes a ticket entity from the database.
     * 
     * @param ticket the Ticket entity to be deleted.
     */
    public void delete(Ticket ticket);

    /**
     * Retrieves all tickets from the database.
     * 
     * @return a list of all Ticket entities.
     */
    public List<Ticket> findAllTickets();

    public List<Ticket> findTicketsByUsername(String username);
}
