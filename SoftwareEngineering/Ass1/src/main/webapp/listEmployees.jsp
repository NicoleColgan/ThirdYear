<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib prefix ="c" 
uri="http://java.sun.com/jsp/jstl/core" %>
<title>Insert title here</title>
</head>
<body>
	<h1>Employees</h1>
	<table border="1"> <%--add border --%>
			<thead>
				<tr><td>ID</td><td>Name</td></tr>
			</thead>
			<%--iterate over each employee object and display attributes --%>
			<c:forEach var = "employees" items = "${employees}">
			<tr><td>${employees.id}</td><td>${employees.name}</td>
			<td>${employees.dept.name}</td></tr>
			</c:forEach>
			
		</table>
</body>
</html>