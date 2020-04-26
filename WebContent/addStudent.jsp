<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
<title>Add Student</title>
</head>
<body>
<div id = "header">
<h2>Foobar University</h2>
</div>
<h4><b>Add Student</b></h4>
<form action="StudentControllerServlet" method="GET">
<input type="hidden" name="command" value="ADD"/>
<table>
<tr>
<td>First Name:</td>
<td>
<input type="text" name="firstName"/>
</td>
</tr>
<tr>
<td>Last Name:</td>
<td>
<input type="text" name="lastName"/>
</td>
</tr>
<tr>
<td>Email:</td>
<td>
<input type="text" name="email"/>
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