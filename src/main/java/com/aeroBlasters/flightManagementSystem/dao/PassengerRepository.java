package com.aeroBlasters.flightManagementSystem.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.aeroBlasters.flightManagementSystem.bean.Passenger;

/**
 * Repository interface for managing Passenger entities.
 * <p>
 * This interface extends JpaRepository and provides custom
 * queries for Passenger entities using JPQL.
 * </p>
 */
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    /**
     * Finds all passengers associated with a given ticket ID.
     * 
     * @param ticketId the ticket ID to search for passengers.
     * @return a list of passengers for the specified ticket ID.
     */
    @Query("select a from Passenger a where ticket_id = ?1")
    public List<Passenger> findByTicketId(Long ticketId);

    /**
     * Deletes passengers associated with a given ticket number.
     * 
     * @param ticketId the ticket number associated with passengers to be deleted.
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Passenger p WHERE ticket_id = ?1")
    void deletePassengerByTicketNumber(Long ticketId);
}
