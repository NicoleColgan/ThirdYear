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
<title>Reading all items from the database</title>
</head>
<body>
<h2>All Students in database</h2>
<table>
	<thead>
		<tr><td>ID</td><td>First Name</td><td>Last Name</td><td>Email</td>
		</tr>
	</thead>
	<c:forEach var="Students" items="${Students}">
		<tr><td>${Students.id}</td><td>${Students.first_name}</td><td>${Students.last_name}</td><td>${Students.email}</td>
		</tr>
	</c:forEach>
</table>
<br>
<h3>Return to the <a href="${pageContext.request.contextPath}/index.jsp">Main Page</a></h3>
</body>
</html>