package com.aeroBlasters.flightManagementSystem.bean;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ticket {
    @Id
    private Long ticketNumber;
    private Long routeId;
    private Long flightNumber;
    private String carrierName;
    private Double totalAmount;
    private String username;
    private String date;
    private String arrivalTime;
    private String departureTime;
    private String SourceAirport;
    private String DestinationAirport;

    // Default constructor
    public Ticket() {
        super();
    }

    // Parameterized constructor
    public Ticket(Long ticketNumber, Long routeId, Long flightNumber, String carrierName, Double totalAmount) {
        super();
        this.ticketNumber = ticketNumber;
        this.routeId = routeId;
        this.flightNumber = flightNumber;
        this.carrierName = carrierName;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
    public Long getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Long ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Long flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getSourceAirport() {
        return SourceAirport;
    }

    public void setSourceAirport(String sourceAirport) {
        SourceAirport = sourceAirport;
    }

    public String getDestinationAirport() {
        return DestinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        DestinationAirport = destinationAirport;
    }
}
