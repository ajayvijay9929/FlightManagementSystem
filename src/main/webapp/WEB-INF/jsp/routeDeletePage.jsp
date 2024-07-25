<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <title>Delete Route</title>
                <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
                <style>
                    body {
                        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                        background-image: url('/images/routes.jpg');
                        background-size: cover;
                        background-position: center;
                        background-repeat: no-repeat;
                        color: #0f0101;
                        text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.5);
                        margin: 0;
                        padding: 0;
                    }

                    .navbar {
                        background-color: rgba(17, 0, 253, 0.9);
                        padding: 10px;
                        position: fixed;
                        width: 100%;
                        z-index: 1000;
                    }

                    .navbar a {
                        color: white;
                        padding: 14px 20px;
                        text-decoration: none;
                    }

                    .form-container {
                        background-color: rgba(255, 255, 255, 0.9);
                        padding: 30px;
                        border-radius: 10px;
                        box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
                        margin-top: 80px;
                    }

                    .form-container h1 {
                        color: #333;
                        text-shadow: none;
                        margin-bottom: 30px;
                    }

                    .btn-danger {
                        background-color: #dc3545;
                        border-color: #dc3545;
                    }

                    .btn-danger:hover {
                        background-color: #c82333;
                        border-color: #bd2130;
                    }

                    .back-link {
                        margin-top: 20px;
                        display: inline-block;
                        color: #ffffff;
                    }

                    .back-link:hover {
                        color: #ffffff;
                        text-decoration: none;
                    }
                </style>
            </head>

            <body>
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-md-8 form-container">
                            <h1 class="text-center"><u><i>Delete Route</i></u></h1>
                            <form:form modelAttribute="route" action="/deleteroute" method="post">
                                <input type="hidden" name="routeId" value="${routeRecord.routeId}" />
                                <div class="form-group">
                                    <label for="sourceAirportCode">Source Airport:</label>
                                    <input type="text" class="form-control" id="sourceAirportCode"
                                        name="sourceAirportCode" value="${sourceAirport.airportLocation}" required>
                                </div>
                                <div class="form-group">
                                    <label for="destinationAirportCode">Destination Airport:</label>
                                    <input type="text" class="form-control" id="destinationAirportCode"
                                        name="destinationAirportCode" value="${destinationAirport.airportLocation}"
                                        required>
                                </div>
                                <div class="form-group">
                                    <label for="fare">Route Fare:</label>
                                    <input type="number" class="form-control" id="fare" name="fare"
                                        value="${routeRecord.fare}" required>
                                </div>
                                <button type="submit" class="btn btn-danger">Delete Route</button>
                            </form:form>
                            <a href="/index" class="back-link btn btn-secondary">Back to Home</a>
                        </div>
                    </div>
                </div>

                <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
                <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
            </body>

            </html>