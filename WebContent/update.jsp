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
	<c:choose>
		<c:when test='${requestScope.result eq 1 }'>
			<h1>Aktualizacja przebiegła pomyślnie</h1>
		</c:when>
		<c:otherwise>
			<h1>Coś poszło nie tak</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>