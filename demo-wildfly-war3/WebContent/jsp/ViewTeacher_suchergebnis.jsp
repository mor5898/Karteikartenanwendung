<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%>
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
		<table>
			<tr>
				<th>Studiengang</th>
				<th>Modul</th>
				<th>Karteikarte-ID</th>
				<th>Titel</th>
			</tr>
			<c:forEach var="currentKarteikarte" items="${ karteikarten }">
				<tr>
					<td>${currentKarteikarte.studiengang}</td>
					<td>${currentKarteikarte.modul}</td>
					<td>${currentKarteikarte.karteikarteID}</td>
					<td>${currentKarteikarte.titel}</td>
					<td><a href="../HIER LINK EINFUEGEN?id=${currentKarteikarte.karteikarteID }">Anzeigen</a></td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>