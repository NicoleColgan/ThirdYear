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
<title>update Student details</title>
</head>
<body>
<h1>Select a student to update by their id value</h1>
<form action ="Controller"><!-- not sure if this will work -->
Student ID:
<input type="number" name="id">
<br>
<h2>Enter new details for student</h2><br>
First name:
<input type="text" name="firstName">
<br>
Last name:
<input type="text" name="lastName">
<br>
<input type ="submit" name = "update" value = "Update">
</form>
</body>
</html>