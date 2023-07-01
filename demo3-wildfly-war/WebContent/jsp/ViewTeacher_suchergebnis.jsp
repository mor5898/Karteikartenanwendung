<!-- Erstellt von Fatih Doruk -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.requestURI}" />
	<meta charset="UTF-8">
	<link rel="stylesheet" href="../css/ViewTeacher_Suchergebnis.css">
	<title> Suchergebnis Karteikarten</title>
</head>
<body>
	<h1>Treffer Karteikarten für Schlagwort: ${param.schlagwort}</h1>
	<form method="post" action="../ViewTeacher_KarteikarteBearbeitenServlet">
		<button type="submit" value="zur&#252;ck" formaction="ViewTeacher_Suchen.jsp">Zur&#252;ck</button>
		<input type="hidden" name="searchFlag" value="true">
		<div class ="suchtabelle">
			<table>
				<tr>
					<th>Studiengang</th>
					<th>Modul</th>
					<th>Karteikarte-ID</th>
					<th>Titel</th>
				</tr>
				<c:forEach var="currentKarteikarte" items="${ karteikarten }">
					<input type="hidden" name="userid"
						value="${currentKarteikarte.userId}">
					<input type="hidden" name="studienfachId"
						value="${currentKarteikarte.studiengang}">
					<input type="hidden" name="modulname"
						value="${currentKarteikarte.modul}">
					<input type="hidden" name="fragentext"
						value="${currentKarteikarte.fragentext}">
					<tr>
						<td>${currentKarteikarte.studiengang}</td>
						<td>${currentKarteikarte.modul}</td>
						<td>${currentKarteikarte.karteikarteID}</td>
						<td>${currentKarteikarte.titel}</td>
						<td>
							<button type="submit" name="karteikartenId" id="karteikarte.titel" value="${currentKarteikarte.karteikarteID}">Anzeigen</button>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
</body>
</html>