<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <title>My Tickets</title>
            <!-- Include Bootstrap CSS -->
            <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
            <!-- Custom CSS -->
            <style>
                body {
                    background-image: url('/images/flights.jpg');
                    background-size: cover;
                    background-position: center;
                    background-repeat: no-repeat;
                    min-height: 100vh;
                    margin: 0;
                    display: flex;
                    align-items: center;
                    justify-content: center;
                }

                .tickets-table {
                    background-color: rgba(255, 255, 255, 0.8);
                    padding: 20px;
                    border-radius: 10px;
                }
            </style>
        </head>

        <body>
            <div class="container tickets-table">
                <h2>My Tickets</h2>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Ticket Number</th>
                            <th>Flight Number</th>
                            <th>Carrier Name</th>
                            <th>From</th>
                            <th>To</th>
                            <th>Arrival</th>
                            <th>Departure</th>
                            <th>Passenger Name</th>
                            <th>Age</th>
                            <th>Fare</th>
                            <th>Update Details</th>
                            <th>Cancel Ticket</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="entry" items="${ticketPassengerMap}">
                            <c:forEach var="passenger" items="${entry.value}">
                                <tr>
                                    <td>${entry.key.ticketNumber}</td>
                                    <td>${entry.key.flightNumber}</td>
                                    <td>${entry.key.carrierName}</td>
                                    <td>${entry.key.sourceAirport}</td>
                                    <td>${entry.key.destinationAirport}</td>
                                    <td>${entry.key.arrivalTime}</td>
                                    <td>${entry.key.departureTime}</td>
                                    <td>${passenger.passengerName}</td>
                                    <td>${passenger.passengerAge}</td>
                                    <td>${passenger.fare}</td>
                                    <td><a href="/updateticket/${entry.key.ticketNumber}"
                                            class="btn btn-secondary">Update</a>
                                    </td>
                                    <td><a href="/deleteticket/${entry.key.ticketNumber}"
                                            class="btn btn-danger">Remove</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </tbody>
                </table>
                <a href="/index" class="btn btn-primary">Back to Home</a>
            </div>
        </body>

        </html>