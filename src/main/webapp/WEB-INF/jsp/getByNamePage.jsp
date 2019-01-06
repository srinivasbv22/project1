<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Get By Id</title>
</head>
<body>
	
	<form action="getByName.html" method="post">
		Enter Gun Name:
		<br>
		<input type="text" name="name" >
		<br>
		<input type="submit" value="Submit" /> 
	</form>
	<br>
		${message}
	<br>
	<br>
	<a href="index.html">Index</a>
</body>
</html>