<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>Roll No</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>City</th>
			<th>Day of birth</th>
		<tr>
		<c:forEach var="student" items='${requestScope.students}' >
			<tr>
				<td><c:out value='${student.rollNo}'/></td>
				<td><c:out value='${student.firstName}'/></td>
				<td><c:out value='${student.lastName}'/></td>
				<td><c:out value='${student.city}'/></td>
				<td><c:out value='${student.birthDay}'/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>