<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Update Ticket</title>
                <style>
                    body {
                        font-family: 'Arial', sans-serif;
                        background-image: url('/images/flights.jpg');
                        background-size: cover;
                        background-position: center;
                        background-repeat: no-repeat;
                        height: 100vh;
                        margin: 0;
                        background-color: #f8f9fa;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        padding: 20px;
                    }

                    .details-container {
                        padding: 30px;
                        background-color: rgba(255, 255, 255, 0.9);
                        border-radius: 12px;
                        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
                        width: 90%;
                        max-width: 700px;
                        text-align: left;
                    }

                    h3 {
                        color: #007bff;
                        margin-bottom: 15px;
                        font-size: 1.4em;
                        text-align: center;
                    }

                    table {
                        width: 100%;
                        margin-bottom: 15px;
                        border-collapse: collapse;
                    }

                    td {
                        padding: 8px;
                        vertical-align: middle;
                    }

                    label {
                        font-weight: bold;
                        display: block;
                        margin-bottom: 6px;
                        font-size: 0.9em;
                    }

                    input[type="text"],
                    input[type="date"] {
                        width: calc(100% - 20px);
                        padding: 8px;
                        border-radius: 6px;
                        border: 1px solid #ccc;
                        box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.1);
                        font-size: 0.9em;
                    }

                    input[disabled] {
                        background-color: #e9ecef;
                        cursor: not-allowed;
                    }

                    button {
                        background-color: #007bff;
                        color: white;
                        border: none;
                        border-radius: 6px;
                        padding: 10px;
                        cursor: pointer;
                        width: 100%;
                        font-size: 1em;
                        transition: background-color 0.3s ease;
                        margin-top: 15px;
                    }

                    button:hover {
                        background-color: #0056b3;
                    }

                    .required {
                        color: #dc3545;
                    }
                </style>
            </head>

            <body>

                <div class="details-container" align="center">
                    <form:form action="/updateticket" method="post" modelAttribute="ticketRecord">
                        <form:hidden path="ticketNumber" />
                        <form:hidden path="flightNumber" />
                        <form:hidden path="carrierName" />
                        <input type="hidden" name="fromLocation" value="${route.sourceAirportCode}" />
                        <input type="hidden" name="toLocation" value="${route.destinationAirportCode}" />
                        <input type="hidden" name="routeId" value="${route.routeId}" />
                        <input type="hidden" name="totalAmount" value="${route.fare}" />
                        <input type="hidden" name="arrivalTime" value="${flight.arrival}" />
                        <input type="hidden" name="departureTime" value="${flight.departure}" />
                        <input type="hidden" name="ticketNumber" value="${ticketRecord.ticketNumber}" />
                        <h3>Flight Booking Details</h3>
                        <table>
                            <tr>
                                <td><label class="required">Ticket Number : </label>
                                    <form:input path="ticketNumber" disabled="true" />
                                </td>
                                <td><label class="required">Flight Number : </label>
                                    <form:input path="flightNumber" disabled="true" />
                                </td>
                                <td><label class="required">Airlines Name : </label>
                                    <form:input path="carrierName" disabled="true" />
                                </td>
                            </tr>
                            <tr>
                                <td><label class="required">From : </label><input type="text"
                                        value="${route.sourceAirportCode}" disabled="true" /></td>
                                <td><label class="required">To : </label><input type="text"
                                        value="${route.destinationAirportCode}" disabled="true" /></td>
                                <td><label class="required">Fare : </label><input type="text" value="${route.fare}"
                                        disabled="true" /></td>
                            </tr>
                            <tr>
                                <td><label class="required">Arrival Time : </label><input type="text"
                                        value="${flight.arrival}" disabled="true" /></td>
                                <td><label class="required">Departure Time : </label><input type="text"
                                        value="${flight.departure}" disabled="true" /></td>
                                <td><label class="required">Additional Information : </label><input type="text"
                                        disabled="true" value="All OK" /></td>
                            </tr>
                        </table>
                        <h3>Passenger Details</h3>
                        <table>
                            <tbody>
                                <c:if test="${passengerList.size() >= 1}">
                                    <tr>
                                        <input type="hidden" name="id1" value="${passengerList[0].id}" />
                                        <td>
                                            <label for="name1">Passenger Name:</label>
                                            <input type="text" class="form-control" id="name1" name="name1"
                                                value="${passengerList[0].passengerName}" required>
                                        </td>
                                        <td>
                                            <label for="age1">Passenger Age:</label>
                                            <input type="text" class="form-control" id="age1" name="age1"
                                                value="${passengerList[0].passengerAge}" required>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${passengerList.size() >= 2}">
                                    <tr>
                                        <input type="hidden" name="id2" value="${passengerList[1].id}" />
                                        <td>
                                            <label for="name2">Passenger Name:</label>
                                            <input type="text" class="form-control" id="name2" name="name2"
                                                value="${passengerList[1].passengerName}">
                                        </td>
                                        <td>
                                            <label for="age2">Passenger Age:</label>
                                            <input type="text" class="form-control" id="age2" name="age2"
                                                value="${passengerList[1].passengerAge}">
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${passengerList.size() >= 3}">
                                    <tr>
                                        <input type="hidden" name="id3" value="${passengerList[2].id}" />
                                        <td>
                                            <label for="name3">Passenger Name:</label>
                                            <input type="text" class="form-control" id="name3" name="name3"
                                                value="${passengerList[2].passengerName}">
                                        </td>
                                        <td>
                                            <label for="age3">Passenger Age:</label>
                                            <input type="text" class="form-control" id="age3" name="age3"
                                                value="${passengerList[2].passengerAge}">
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${passengerList.size() >= 4}">
                                    <tr>
                                        <input type="hidden" name="id4" value="${passengerList[3].id}" />
                                        <td>
                                            <label for="name4">Passenger Name:</label>
                                            <input type="text" class="form-control" id="name4" name="name4"
                                                value="${passengerList[3].passengerName}">
                                        </td>
                                        <td>
                                            <label for="age4">Passenger Age:</label>
                                            <input type="text" class="form-control" id="age4" name="age4"
                                                value="${passengerList[3].passengerAge}">
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${passengerList.size() >= 5}">
                                    <tr>
                                        <input type="hidden" name="id5" value="${passengerList[4].id}" />
                                        <td>
                                            <label for="name5">Passenger Name:</label>
                                            <input type="text" class="form-control" id="name5" name="name5"
                                                value="${passengerList[4].passengerName}">
                                        </td>
                                        <td>
                                            <label for="age5">Passenger Age:</label>
                                            <input type="text" class="form-control" id="age5" name="age5"
                                                value="${passengerList[4].passengerAge}">
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${passengerList.size() >= 6}">
                                    <tr>
                                        <input type="hidden" name="id6" value="${passengerList[5].id}" />
                                        <td>
                                            <label for="name6">Passenger Name:</label>
                                            <input type="text" class="form-control" id="name6" name="name6"
                                                value="${passengerList[5].passengerName}">
                                        </td>
                                        <td>
                                            <label for="age6">Passenger Age:</label>
                                            <input type="text" class="form-control" id="age6" name="age6"
                                                value="${passengerList[5].passengerAge}">
                                        </td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>

                        <button type="submit">Update</button>

                    </form:form>
                </div>
            </body>

            </html>