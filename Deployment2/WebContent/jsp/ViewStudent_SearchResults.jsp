<!-- Erstellt von Riza Dursun -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Suchergebnis</title>
<link rel="stylesheet" type="text/css" href="../css/ViewStudent_Suche.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="mitte2">
		<h1 class="h1">Suchergebnis</h1>
		<div class="suchtabelle">
			<table>
				<thead>
					<tr>
						<th>Studiengang</th>
						<th>Modul</th>
						<th>Dozent</th>

					</tr>
				</thead>
				<tbody>
				<c:if test="${empty module}">
						<label class="aus">Keine Treffer!</label>
						</c:if>
					<c:forEach var="modul" items="${module}">
						<tr>
							<td>${modul.studiengangname}</td>
							<td>${modul.modulname}</td>
							<td>${modul.userId}</td>
							<td>
								<form action="../ViewStudent_DeckHinzufuegen" method="post">
									<input type="hidden" name="id" value="${LoginForm.userid}">
									<input type="hidden" name="studie"
										value="${modul.studiengangname}"> <input type="hidden"
										name="modul" value="${modul.modulname}"> <input
										type="hidden" name="doz" value="${modul.userId}">
									<button type="submit">Hinzufügen</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
