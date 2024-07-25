<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<h1>
<u><i>All Airports</i></u>
</h1>
<table border="1">
            <tr>
                <th>Airport Code</th>
                <th>Airport Location</th>
            </tr>
            <c:forEach items="${airportList}" var="airport" >
			<tr>
                <td>${airport.airportCode}</td>
                <td>${airport.airportLocation}</td>
                
            </tr>
</c:forEach>
</table>
<br><br><br>
<a href="i">Back to Home</a>
</div>
</body>
</html>