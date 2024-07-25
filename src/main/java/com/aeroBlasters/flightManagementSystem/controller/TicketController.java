package com.aeroBlasters.flightManagementSystem.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aeroBlasters.flightManagementSystem.bean.Flight;
import com.aeroBlasters.flightManagementSystem.bean.Passenger;
import com.aeroBlasters.flightManagementSystem.bean.Route;
import com.aeroBlasters.flightManagementSystem.bean.Ticket;
import com.aeroBlasters.flightManagementSystem.dao.AirportDao;
import com.aeroBlasters.flightManagementSystem.dao.FlightDao;
import com.aeroBlasters.flightManagementSystem.dao.PassengerDao;
import com.aeroBlasters.flightManagementSystem.dao.RouteDao;
import com.aeroBlasters.flightManagementSystem.dao.TicketDao;
import com.aeroBlasters.flightManagementSystem.exception.RouteException;
import com.aeroBlasters.flightManagementSystem.exception.TicketException;
import com.aeroBlasters.flightManagementSystem.service.TicketService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@ControllerAdvice
@RestController
public class TicketController {
    @Autowired
    private TicketDao ticketDao;
    @Autowired
    private FlightDao flightDao;
    @Autowired
    private RouteDao routeDao;
    @Autowired
    private PassengerDao passengerDao;
    @Autowired
    private AirportDao airportDao;
    @Autowired
    private TicketService ticketService;

    @GetMapping("/ticket/{id}")
    public ModelAndView showTicketForm(@PathVariable Long id) {
        Flight flight = flightDao.findFlightById(id);
        Route route = routeDao.findRouteById(flight.getRouteId());
        Long newTicketId = ticketDao.findLastTicketNumber() + 1;
        Ticket ticket = new Ticket();
        ticket.setTicketNumber(newTicketId);
        ticket.setFlightNumber(flight.getFlightNumber());
        ticket.setCarrierName(flight.getCarrierName());
        ModelAndView mv = new ModelAndView("ticketBookingPage");
        mv.addObject("ticketRecord", ticket);
        mv.addObject("flight", flight);
        mv.addObject("route", route);
        return mv;
    }

    @PostMapping("/ticket")
    public ModelAndView openShowTicketPage(@ModelAttribute("ticketRecord") Ticket ticket, HttpServletRequest request) {
        Long ticketNumber = ticketDao.findLastTicketNumber() + 1;
        ticket.setTicketNumber(ticketNumber);
        System.out.println("Ticket Number: " + ticket.getTicketNumber());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Retrieves the username of the currently authenticated user
        System.out.println("Username: " + username);

        ticketDao.save(ticket);

        Double totalAmount = 0.0;
        System.out.println("Flight Number from openshowticketpage: " + ticket.getFlightNumber());

        Long totalSeats = ticketService.getTotalSeats(ticket.getFlightNumber());
        Long bookedSeats = ticketService.getBookedSeats(ticket.getFlightNumber());
        System.out.println("Total Seats: " + totalSeats);
        System.out.println("Booked Seats: " + bookedSeats);

        ModelAndView mv = new ModelAndView("showTicketPage");
        String fromCity = airportDao.findAirportById(request.getParameter("fromLocation")).getAirportLocation();
        String toCity = airportDao.findAirportById(request.getParameter("toLocation")).getAirportLocation();
        Double basePrice = Double.parseDouble(request.getParameter("totalAmount"));
        String arrivalTime = request.getParameter("arrivalTime");
        String departureTime = request.getParameter("departureTime");
        String pname = "";
        String dob = "";
        Long totalPassengers = 0L;
        for (int i = 1; i <= 6; i++) {
            pname = request.getParameter("name" + i);
            dob = request.getParameter("dob" + i);
            if (pname.equals("--"))
                continue;
            Passenger passenger = new Passenger();
            passenger.setPassengerName(pname);
            passenger.setDob(dob);
            passenger.setTicket(ticket);
            passenger.setFare(ticket.getTotalAmount());
            passenger.setPassengerAge(LocalDate.now().getYear() - LocalDate.parse(dob).getYear());
            passengerDao.save(passenger);
            totalAmount += ticketService.calculateFinalTicketPrice(LocalDate.parse(dob).getYear(), basePrice,
                    totalSeats, bookedSeats);
            totalPassengers++;
            System.out.println("name " + pname + "dob " + pname);
        }
        if (totalPassengers == 0) {
            ticketDao.delete(ticket);
            throw new TicketException("No passengers added to the ticket");
        }
        ticket.setTotalAmount(totalAmount);
        ticket.setArrivalTime(arrivalTime);
        ticket.setDepartureTime(departureTime);
        ticket.setSourceAirport(fromCity);
        ticket.setDestinationAirport(toCity);
        ticket.setUsername(username);
        ticketService.updateBookedSeats(ticket.getFlightNumber(), totalPassengers);
        ticketDao.save(ticket);
        List<Passenger> passengerList = passengerDao.findByTicketId(ticketNumber);
        mv.addObject("passengerList", passengerList);
        mv.addObject("ticketRecord", ticket);
        return mv;
    }

    @GetMapping("/showTicket/{ticketNumber}")
    public ModelAndView showTicketPage(@PathVariable("ticketNumber") Long ticketNumber) {
        Ticket ticket = ticketDao.findTicketByTicketNumber(ticketNumber);
        List<Passenger> passengerList = passengerDao.findByTicketId(ticketNumber);

        ModelAndView mv = new ModelAndView("showTicketPage");

        mv.addObject("passengerList", passengerList);
        mv.addObject("ticketRecord", ticket);

        return mv;
    }

    @PostMapping("/cancelTicket/{ticketNumber}")
    public ModelAndView cancelTicket(@PathVariable("ticketNumber") Long ticketNumber) {
        System.out.println("Cancelling Ticket Number: " + ticketNumber);
        boolean isCancelled = ticketService.cancelTicket(ticketNumber);
        System.out.println("Ticket Cancelled: " + isCancelled);

        ModelAndView mv = new ModelAndView("cancelTicketPage");
        if (isCancelled) {
            mv.setViewName("redirect:/index");
            mv.addObject("message", "Ticket Cancelled Successfully");
        } else {
            mv.setViewName("errorPage");
            mv.addObject("message", "Ticket Cancellation Failed");
        }

        return mv;
    }

    @GetMapping("/tickets")
    public ModelAndView showAllTickets() {
        List<Ticket> ticketList = ticketDao.findAllTickets();
        System.out.println("Ticket List: " + ticketList.size());
        ModelAndView mv = new ModelAndView("ticketReportPage");
        mv.addObject("ticketList", ticketList);
        return mv;
    }

    @GetMapping("/passengers")
    public ModelAndView showAllPassengers() {
        List<Passenger> passengerList = passengerDao.findAllPassengers();
        System.out.println("Passenger List: " + passengerList.size());
        ModelAndView mv = new ModelAndView("passengerReportPage");
        mv.addObject("passengerList", passengerList);
        return mv;
    }

    // Displays a page to select a route for flight search
    @GetMapping("/mytickets")
    public ModelAndView showMyTickets() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Retrieves the username of the currently authenticated user
        System.out.println("Username: " + username);

        List<Ticket> ticketList = ticketDao.findTicketsByUsername(username);
        Map<Ticket, List<Passenger>> ticketPassengerMap = new HashMap<>();

        // For each ticket, find the passengers associated with it and add to the map
        for (Ticket ticket : ticketList) {
            List<Passenger> passengerList = passengerDao.findByTicketId(ticket.getTicketNumber()); // Find passengers by
                                                                                                   // ticket Number
            ticketPassengerMap.put(ticket, passengerList);
        }

        ModelAndView mv = new ModelAndView("myTicketsPage");
        mv.addObject("ticketPassengerMap", ticketPassengerMap);
        // mv.addObject("ticketList", ticketList);
        return mv;
    }

    @GetMapping("/updateticket/{ticketNumber}")
    public ModelAndView showUpdateTicketForm(@PathVariable Long ticketNumber) {
        Ticket ticket = ticketDao.findTicketByTicketNumber(ticketNumber);
        Flight flight = flightDao.findFlightById(ticket.getFlightNumber());
        Route route = routeDao.findRouteById(flight.getRouteId());
        List<Passenger> passengerList = passengerDao.findByTicketId(ticketNumber);

        ModelAndView mv = new ModelAndView("ticketUpdatePage");
        mv.addObject("ticketRecord", ticket);
        mv.addObject("flight", flight);
        mv.addObject("route", route);
        mv.addObject("passengerList", passengerList);
        return mv;
    }

    @PostMapping("/updateticket")
    public ModelAndView updateTicket(@ModelAttribute("ticketRecord") Ticket ticket, HttpServletRequest request) {
        // Retrieve username of the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("Username: " + username);

        // Retrieve additional parameters from the request
        String ticketNumberStr = request.getParameter("ticketNumber");
        String fromLocation = request.getParameter("fromLocation");
        String toLocation = request.getParameter("toLocation");
        String totalAmountStr = request.getParameter("totalAmount");
        String arrivalTime = request.getParameter("arrivalTime");
        String departureTime = request.getParameter("departureTime");

        // Check for null or empty parameters before parsing
        if (ticketNumberStr == null || ticketNumberStr.isEmpty() ||
                totalAmountStr == null || totalAmountStr.isEmpty() ||
                fromLocation == null || fromLocation.isEmpty() ||
                toLocation == null || toLocation.isEmpty() ||
                arrivalTime == null || arrivalTime.isEmpty() ||
                departureTime == null || departureTime.isEmpty()) {
            throw new IllegalArgumentException("Required parameters are missing");
        }

        Long ticketNumber = Long.parseLong(ticketNumberStr);
        Double totalAmount = Double.parseDouble(totalAmountStr);

        // Retrieve and save passengers
        for (int i = 1; i <= 6; i++) {
            String passengerIdStr = request.getParameter("id" + i);
            String passengerName = request.getParameter("name" + i);
            String passengerAgeStr = request.getParameter("age" + i);

            if (passengerName == null || passengerName.isEmpty() ||
                    passengerIdStr == null || passengerIdStr.isEmpty() ||
                    passengerAgeStr == null || passengerAgeStr.isEmpty()) {
                continue;
            }

            Long passengerId = Long.parseLong(passengerIdStr);
            Integer passengerAge = Integer.parseInt(passengerAgeStr);

            Passenger passenger = new Passenger();
            passenger.setId(passengerId);
            passenger.setPassengerName(passengerName);
            passenger.setPassengerAge(passengerAge);
            passenger.setTicket(ticket);
            passenger.setFare(ticket.getTotalAmount());
            passengerDao.update(passenger);
            System.out.println("Passenger Name: " + passengerName + ", Age: " + passengerAge);
        }

        // Update ticket details
        ticket.setTicketNumber(ticketNumber);
        ticket.setTotalAmount(totalAmount);
        ticket.setArrivalTime(arrivalTime);
        ticket.setDepartureTime(departureTime);
        ticket.setSourceAirport(airportDao.findAirportById(fromLocation).getAirportLocation());
        ticket.setDestinationAirport(airportDao.findAirportById(toLocation).getAirportLocation());
        ticket.setUsername(username);

        // Retrieve passenger list and prepare ModelAndView
        List<Passenger> passengerList = passengerDao.findByTicketId(ticket.getTicketNumber());
        ModelAndView mv = new ModelAndView("showTicketPage");
        mv.addObject("passengerList", passengerList);
        mv.addObject("ticketRecord", ticket);
        return mv;
    }

    @GetMapping("/deleteticket/{ticketNumber}")
    public ModelAndView showDeleteTicketForm(@PathVariable Long ticketNumber) {
        Ticket ticket = ticketDao.findTicketByTicketNumber(ticketNumber);
        Flight flight = flightDao.findFlightById(ticket.getFlightNumber());
        Route route = routeDao.findRouteById(flight.getRouteId());
        List<Passenger> passengerList = passengerDao.findByTicketId(ticketNumber);

        ModelAndView mv = new ModelAndView("ticketDeletePage");
        mv.addObject("ticketRecord", ticket);
        mv.addObject("flight", flight);
        mv.addObject("route", route);
        mv.addObject("passengerList", passengerList);
        return mv;
    }

    @PostMapping("/deleteticket")
    public ModelAndView deleteTicket(@ModelAttribute("ticketRecord") Ticket ticket) {
        // Retrieve username of the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println("Username: " + username);

        // Retrieve ticket number
        Long ticketNumber = ticket.getTicketNumber();
        System.out.println("Ticket Number: " + ticketNumber);

        // Retrieve passengers associated with the ticket
        List<Passenger> passengerList = passengerDao.findByTicketId(ticketNumber);

        // Delete passengers associated with the ticket
        for (Passenger passenger : passengerList) {
            passengerDao.deleteById(passenger.getId());
        }

        // Delete the ticket
        ticketDao.delete(ticket);

        List<Ticket> ticketList = ticketDao.findTicketsByUsername(username);
        Map<Ticket, List<Passenger>> ticketPassengerMap = new HashMap<>();

        // For each ticket, find the passengers associated with it and add to the map
        for (Ticket t : ticketList) {
            List<Passenger> ticketPassengers = passengerDao.findByTicketId(t.getTicketNumber()); // Find passengers by
                                                                                                 // ticket Number
            ticketPassengerMap.put(t, ticketPassengers);
        }

        ModelAndView mv = new ModelAndView("myTicketsPage");
        mv.addObject("ticketPassengerMap", ticketPassengerMap);
        mv.addObject("message", "Ticket Deleted Successfully");
        return mv;
    }

    @ExceptionHandler(value = TicketException.class)
    public ModelAndView handlingTicketException(TicketException exception) {
        String message = "Ticket Exception: " + exception.getMessage();
        ModelAndView mv = new ModelAndView("myTicketsPage");
        mv.addObject("errorMessage", message);
        return mv;
    }
}
