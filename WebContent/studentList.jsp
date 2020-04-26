<%@page import="com.nichat.webJDBC.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="css/style.css">
<meta charset="ISO-8859-1">
<title>Student Tracker App</title>
</head>
<body>
<div id = "header">
<h2>Foobar University</h2>
</div>

<div>
<br>
<input type="button" value="Add Student"
	onclick = "window.location.href='addStudent.jsp';return false;"
	class="add-student-button"/>
<br>
</div>

<div>

	<table border="1">
	<thead>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Action</th>
		</tr>
	</thead>
		<c:forEach var="tempStudent" items="${STUDENT_LIST}" >
			<tr>
				<td>${tempStudent.first_name}</td>
				<td>${tempStudent.last_name}</td>
				<td>${tempStudent.email}</td>
				<td>
					<c:url var="tempLink" value="StudentControllerServlet">
						<c:param name="command" value="LOAD"/>
						<c:param name="studentId" value="${tempStudent.id}"/>
					</c:url>
					<a href="${tempLink}">Update</a>
					|
					<c:url var="deleteLink" value="StudentControllerServlet">
						<c:param name="command" value="DELETE"/>
						<c:param name="studentId" value="${tempStudent.id}"/>
					</c:url>
					<a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete this student?'))) return false">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>

</div>
</body>
</html>