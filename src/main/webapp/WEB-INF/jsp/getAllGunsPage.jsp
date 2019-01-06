<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>Name</th>
			<th>Model</th>
			<th>Price</th>
		</tr>
		<c:forEach items="${message}" var="gun">
		 <tr>
		 	<td>${gun.name}</td>
		 	<td>${gun.model}</td>
		 	<td>${gun.price}</td>
		</tr>
		</c:forEach>
	
	</table>
	<br>
	<a href="../../index.html">Index</a>
</body>
</html>