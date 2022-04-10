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
<title>update employee details</title>
</head>
<body>
<h1>Select an employee to update by their id value</h1>
<form action ="servlet5"><!-- not sure if this will work -->
Emp ID:
<input type="number" name="id">
<br>
<h2>Enter new details for employee</h2><br>
Emp Name:
<input type="text" name="name">
<br>
Emp phone:
<input type="number" name="number">
<br>
Emp email:
<input type="email" name="email">
<br>
Emp Salary:
<input type="number" name="salary">
<br>
Emp Desig:
<input type="text" name="desig">
<br>
<input type ="submit" value = "Submit">
</form>
</body>
</html>