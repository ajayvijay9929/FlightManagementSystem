<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
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
<h2>
<form action="/airport-select" method="post">
 Select Airport Code : <input list="allCodes" name ="airport-code" id = "airport-code"/>
<datalist id = "allCodes">
<c:forEach items="${codeList}" var="aircode" >
<option value = "${aircode}"/>
</c:forEach>
</datalist>
<br/><br/>
<button type = "submit">Submit</button>
</h2>
<a href="i">Back to Home</a>
</div>
</form>
</body>
</html>