package com.aeroBlasters.flightManagementSystem.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.aeroBlasters.flightManagementSystem.bean.Ticket;

/**
 * Repository interface for managing Ticket entities.
 * <p>
 * This interface extends JpaRepository and provides custom
 * queries for Ticket entities using JPQL.
 * </p>
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    /**
     * Finds the highest ticket number in the database.
     * 
     * @return the highest ticket number.
     */
    @Query("select max(ticketNumber) from Ticket")
    public Long findLastTicketNumber();

    /**
     * Deletes a ticket by its ticket number.
     * 
     * @param ticketNumber the ticket number of the ticket to be deleted.
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Ticket t WHERE t.ticketNumber = :ticketNumber")
    void deleteTicketByTicketNumber(Long ticketNumber);

    /**
     * Finds all tickets by the username of the passenger.
     * 
     * @param username the username of the passenger.
     * @return a list of Ticket entities with the specified username.
     */
    @Query("SELECT t FROM Ticket t WHERE t.username = :username")
    public List<Ticket> findTicketsByUsername(String username);
}
