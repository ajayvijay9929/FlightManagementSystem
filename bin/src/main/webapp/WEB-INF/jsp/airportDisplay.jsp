<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Airport Details</title>
<style>
body {
            font-family: Arial, sans-serif;
            background-image: url('https://www.shutterstock.com/image-vector/vector-3d-illustration-airplane-clouds-600nw-2143367099.jpg'); /* Path to your background image */
            background-size: cover; /* Cover the entire page */
            background-position: center; /* Center the image */
            background-repeat: no-repeat; /* Do not repeat the image */
            height: 100vh; /* Ensure body takes full height */
            margin: 0; /* Remove default margin */
        }
</style>

</head>
<body>
<div align ="center">
 <i><h1>Airport Details</h1></i>
    <table>
        <tr>
        <h1>
            <td>Airport Code:</td>
            <td>${airport.airportCode}</td>
        </tr>
        <tr>
            <td>Airport Location:</td>
            <td>${airport.airportLocation}</td>
        </tr>
        </h1>
    </table>
    <br/><br/>
   <a href="i">Back to Home</a>
</body>
</html>