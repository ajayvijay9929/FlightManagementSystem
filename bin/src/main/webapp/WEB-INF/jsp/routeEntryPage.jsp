<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
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
<div align = "center">
<h3>
<form:form action= "/route" method = "post" modelAttribute="routeRecord">
<form:hidden path = "routeId"/> 
<table>
<tr>
<td>
Enter Source City: </td>
<td>  <form:input path = "sourceAirportCode"/>  </td>
</tr>   <tr>
<td>
Enter Destination City:  </td>
<td> <form:input path = "destinationAirportCode"/> </td>
</tr>
</table>
<button type = "submit">Submit</button>
<button type = "reset">Reset</button>
</form:form>
</h3>
<a href="i">Back to Home</a>
</div> 
</body>
</html>