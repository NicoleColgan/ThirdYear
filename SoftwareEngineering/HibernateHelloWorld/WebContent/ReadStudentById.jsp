<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style>
     <%@ include file="/css/style.css"%>
</style>
<link rel="stylesheet" type="text/css" href="css/style.css">
<meta charset="ISO-8859-1">
<title>Read Student by ID</title>
</head>
<body>
<h1>Enter the student you want to retrieve by ID</h1>
<form action ="Controller"><!-- not sure if this will work -->
Student ID:
<input type="number" name="id">
<br>
<input type ="submit" name = "readById" value = "ReadById">
</form>
<!--after any operation is performed, return o main crud page-->

</body>
</html>