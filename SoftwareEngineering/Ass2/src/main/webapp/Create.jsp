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
<form action ="Servlet2"><!-- not sure if this will work -->
Emp Name:
<input type="text" name="name">
<br>
Emp ID:
<input type="number" name="id">
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
<!--after any operation is performed, return o main crud page-->

</body>
</html>