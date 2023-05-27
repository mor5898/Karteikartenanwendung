<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
 		<base href="${pageContext.request.requestURI}" />
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<h2>Hallo ${RegisterForm.getUserid()}, Deine Registrierung war erfolgreich</h2>
		
		<p><a href="../index.html">Zurück zur Startseite</a></p>
	</body>
</html>