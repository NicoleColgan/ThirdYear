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
<title>Create a new student</title>
</head>
<body>
<h1>Enter the following details</h1>
<form action ="Controller"><!-- not sure if this will work -->
Student first name:
<input type="text" name="firstName">
<br>
Student second name:
<input type="text" name="lastName">
<br>
<input type ="submit" name = "create" value = "Create">
</form>
<!--after any operation is performed, return o main crud page-->

</body>
</html>