<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div align="center" >
<h3>
 <form:form id="airportForm" action="/airport" method="post" modelAttribute="airport_data">
 <table>
 <tr>
 <td>
  Enter Airport City:  </td>
  <td> <form:input path="airportLocation"/> </td>
  </tr>
   </br></br>
 <tr>
 <td>
  Enter Airport Code:  </td>
  <td>  <form:input path="airportCode"/>  </td>
</tr>
</table>        
  <button type ="submit">Submit</button>
    <button type ="reset">Reset</button> 
</form:form>
</h3>
<a href="i">Back to Home</a>
</div>
</body>
</html>