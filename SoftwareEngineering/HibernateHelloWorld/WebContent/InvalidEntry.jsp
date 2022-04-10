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
<title>client entered invalid entry for employee</title>
</head>
<body>
<h2>You entered an invalid entry for an employee</h2>
<h3>Return to the <a href="${pageContext.request.contextPath}/index.jsp">Main Page</a> and try again</h3>
</body>
</html>