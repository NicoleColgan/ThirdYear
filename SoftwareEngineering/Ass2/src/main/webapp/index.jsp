<%@ page language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Hello World!</title>
	<style>
     <%@ include file="/css/style.css"%>
</style>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<h1><b>CRUD operations</b></h1>
	
    <form action="Servlet"> <!-- When submit button is hit, go to servlet.java -->
    
    <input type ="checkbox" name="operation" value="CREATE">
    CREATE<BR>
    <input type ="checkbox" name="operation" value="READ">
    READ<br>
    <input type ="checkbox" name="operation" value="UPDATE">
    UPDATE<br>
    <input type ="checkbox" name="operation" value="DELETE">
    DELETE<br>
    
    <input type ="submit" value = "Submit">
    
    </form>

</body>
</html>