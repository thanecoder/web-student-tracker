<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
<title>Update Student</title>
</head>
<body>
<div id = "header">
<h2>Foobar University</h2>
</div>
<h4><b>Update Student</b></h4>

<form action="StudentControllerServlet" method="GET">
<input type="hidden" name="command" value="UPDATE"/>
<input type="hidden" name="studentId" value="${THE_STUDENT.id }"/>
<%
	

%>
<table>
<tr>
<td>First Name:</td>
<td>
<input type="text" name="firstName" value="${THE_STUDENT.first_name }"/>
</td>
</tr>
<tr>
<td>Last Name:</td>
<td>
<input type="text" name="lastName" value="${THE_STUDENT.last_name }"/>
</td>
</tr>
<tr>
<td>Email:</td>
<td>
<input type="text" name="email" value="${THE_STUDENT.email }"/>
</td>
</tr>
<tr>
<td></td>
<td><input type="submit" value="Submit" class="save"/></td>
</tr>
</table>

</form>
</body>
</html>