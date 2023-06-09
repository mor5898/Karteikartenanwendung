<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.requestURI}" />
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/Sidebar.css">
<title>Suchergebnis</title>
<style>
table, th, td {
	border: 1px solid;
	border-collapse: collapse;
	padding: 5px;
	text-align: center;
}
</style>
</head>
<body>
	<h2>Suche war erfolgreich</h2>
	<h3>Gelesene Daten</h3>
	<button type="submit" value="zur&#252;ck">
		<a href="ViewTeacher_Suchen.jsp">zur&#252;ck</a>
	</button>
	<form method="post"
		action="../ViewTeacher_KarteikarteBearbeitenServlet">
		<div>
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
					<input type="hidden" name="karteikartenId"
						value="${currentKarteikarte.karteikarteID}">
					<input type="hidden" name="fragentext"
						value="${currentKarteikarte.fragentext}">
					<tr>
						<td>${currentKarteikarte.studiengang}</td>
						<td>${currentKarteikarte.modul}</td>
						<td>${currentKarteikarte.karteikarteID}</td>
						<td>${currentKarteikarte.titel}</td>
						<td><button type="submit" name="titel" id="karteikarte.titel"
								value="${currentKarteikarte.titel}" />Anzeigen
							</button></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
</body>
</html>