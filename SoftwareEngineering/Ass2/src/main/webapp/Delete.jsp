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
<title>Deleting database elements</title>
</head>
<body>
<h1>Enter employee id that you would like to delete</h1>
<form action ="Servlet4"><!-- not sure if this will work -->
Emp id:
<input type="text" name="id">
<input type="submit">
</form>
</body>
</html>