<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Admin panel:</h1>
		<form action="StudentServlet" method="POST">
			RollNo: <input type="text" placeHolder="RollNo" name="rollNo">
			<br>
			First name:  <input type="text" placeHolder="First Name" name="firstName">
			<br>
			Last name:  <input type="text" placeHolder="Last Name" name="lastName">
			<br>
			City:  <input type="text" placeHolder="City" name="city">
			<br>
			Day of birth:  <input type="text" placeHolder="YYYY-MM-DD" name="birthDay">
			<br>
			Create: <input type="radio" name="action" value="create"> Read: <input type="radio" name="action" value="read">
			<br>
			Update: <input type="radio" name="action" value="update"> Delete: <input type="radio" name="action" value="delete">
			<br>
			Read all: <input type="radio" name="action" value="readAll">
			<br>
			<input type="submit" value="Submit">
		</form>
</body>
</html>