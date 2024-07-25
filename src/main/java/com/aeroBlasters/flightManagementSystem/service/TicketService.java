package com.aeroBlasters.flightManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aeroBlasters.flightManagementSystem.bean.Flight;
import com.aeroBlasters.flightManagementSystem.dao.FlightDao;
import com.aeroBlasters.flightManagementSystem.dao.PassengerDao;
import com.aeroBlasters.flightManagementSystem.dao.TicketDao;

@Service
public class TicketService {
    @Autowired
    private PassengerDao passengerDao;

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private FlightDao flightDao;

    /**
     * Calculates the final ticket price after applying age-based discounts.
     * 
     * @param birthYear   the birth year of the passenger.
     * @param basePrice   the base price of the ticket.
     * @param totalSeats  the total number of seats on the flight.
     * @param bookedSeats the number of seats already booked.
     * @return the final ticket price after discount.
     */
    public Double calculateFinalTicketPrice(Integer birthYear, Double basePrice, Long totalSeats, Long bookedSeats) {
        Integer age = ageCalculation(birthYear);
        Long availableSeats = capacityCalculation(totalSeats, bookedSeats);
        if (availableSeats <= 0) {
            throw new IllegalStateException("No tickets available");
        }
        Double discountedPrice = discount(basePrice, age);
        return discountedPrice;
    }

    /**
     * Calculates the discount based on age.
     * 
     * @param totalAmount the base price of the ticket.
     * @param age         the age of the passenger.
     * @return the discounted price.
     */
    public Double discount(Double totalAmount, int age) {
        final double CHILD_DISCOUNT = 0.3; // 30% discount
        final double YOUTH_DISCOUNT = 0.2; // 20% discount
        final double ELDERLY_DISCOUNT = 0.25; // 25% discount
        final double NO_DISCOUNT = 0.1; // 10% discount for others

        if (age < 12) {
            return totalAmount - (totalAmount * CHILD_DISCOUNT);
        } else if (age >= 12 && age <= 24) {
            return totalAmount - (totalAmount * YOUTH_DISCOUNT);
        } else if (age >= 65) {
            return totalAmount - (totalAmount * ELDERLY_DISCOUNT);
        } else {
            return totalAmount - (totalAmount * NO_DISCOUNT);
        }
    }

    /**
     * Calculates the age of a passenger based on the birth year.
     * 
     * @param year the birth year of the passenger.
     * @return the age of the passenger.
     */
    public Integer ageCalculation(Integer year) {
        return 2024 - year; // Consider updating the current year dynamically
    }

    /**
     * Calculates the number of available seats.
     * 
     * @param totalSeats  the total number of seats.
     * @param bookedSeats the number of booked seats.
     * @return the number of available seats.
     */
    public Long capacityCalculation(Long totalSeats, Long bookedSeats) {
        return totalSeats - bookedSeats;
    }

    /**
     * Cancels a ticket based on the ticket number.
     * 
     * @param ticketNumber the ticket number to cancel.
     * @return true if the cancellation is successful, false otherwise.
     */
    public boolean cancelTicket(Long ticketNumber) {
        try {
            passengerDao.deletePassengerByTicketNumber(ticketNumber);
            ticketDao.deleteTicketByTicketNumber(ticketNumber);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Retrieves the total number of seats for a flight.
     * 
     * @param flightNumber the flight number.
     * @return the total number of seats on the flight.
     */
    public Long getTotalSeats(Long flightNumber) {
        System.out.println("flight number from get total seats: " + flightNumber);
        Flight flight = flightDao.findFlightByFlightNumber(flightNumber);
        System.out.println("flight details from get total seats: " + flight);
        return flight.getSeatCapacity();
    }

    /**
     * Retrieves the number of booked seats for a flight.
     * 
     * @param flightNumber the flight number.
     * @return the number of booked seats on the flight.
     */
    public Long getBookedSeats(Long flightNumber) {
        return flightDao.findFlightByFlightNumber(flightNumber).getSeatBooked();
    }

    public TicketService() {
        super();
    }

    public TicketService(PassengerDao passengerDao, TicketDao ticketDao, FlightDao flightDao) {
        this.passengerDao = passengerDao;
        this.ticketDao = ticketDao;
        this.flightDao = flightDao;
    }

    /**
     * Updates the number of booked seats for a flight.
     * 
     * @param flightNumber   the flight number.
     * @param totalPassenger the number of passengers to add to the booked seats.
     */
    public void updateBookedSeats(Long flightNumber, Long totalPassenger) {
        System.out.println("Flight Number from updateBookedSeats: " + flightNumber);
        Flight flight = flightDao.findFlightByFlightNumber(flightNumber);
        flight.setSeatBooked(flight.getSeatBooked() + totalPassenger);
        flightDao.save(flight);
    }
}
