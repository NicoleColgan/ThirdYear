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
<%@ taglib prefix ="c" 
uri="http://java.sun.com/jsp/jstl/core" %>
<title>Reading items from the database</title>
</head>
<body>
<h2>All employees in database</h2>
<table>
	<thead>
		<tr><td>ID</td><td>Name</td><td>Email</td><td>Desig</td><td>Salary</td><td>Number</td>
		</tr>
	</thead>
	<c:forEach var="employees" items="${employees}">
		<tr><td>${employees.id}</td><td>${employees.name}</td><td>${employees.email}</td><td>${employees.desig}</td><td>${employees.salary}</td><td>${employees.number}</td>
		</tr>
	</c:forEach>
</table>
<br>
<h3>Return to the <a href="${pageContext.request.contextPath}/index.jsp">Main Page</a></h3>
</body>
</html>