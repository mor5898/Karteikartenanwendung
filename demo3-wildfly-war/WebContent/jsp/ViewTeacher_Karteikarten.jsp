<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<!-- Dieses Dokument wurde erstellt durch Moritz Reindl -->
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../css/Sidebar.css">
</head>
<body>

	<div class="sidebar">
		<a href="#profil">Profil</a> <a class="active" href="#decks">Meine
			Decks</a> <a href="#suchen">Suchen</a>
	</div>

	<div class="decks">
		<h1 class="h1">
			<a href="ViewTeacher_Studiengaenge.jsp">Meine Studiengaenge</a>: <a
				href="ViewTeacher_Module.jsp">${studienfachId.studiengangname}</a>:
			${modul.modulname}
		</h1>
		<!-- Nachfolgende Zeile wurde erstellt durch ChatGPT-->
		<form method="post"
			action="../ViewTeacher_KarteikarteErstellenHiddenServlet">
			<input type="hidden" name="userid" value="${LoginForm.userid}">
			<input type="hidden" name="studienfachId"
				value="${studienfachId.studiengangname}"> <input
				type="hidden" name="modulname" value="${modul.modulname}">
			<button type="submit" class="buttonAdd">Frage
				hinzuf&#252;gen</button>
		</form>
		<input type="submit" value="zur&#252;ck" class="buttonBack"> <input
			type="submit" value="speichern" class="buttonSave"> <input
			type="submit" value="Deck ver&#246;ffentlichen" class="buttonPublish">
	</div>
	<form method="post"
		action="../ViewTeacher_KarteikarteBearbeitenServlet">
		<input type="hidden" name="userid" value="${LoginForm.userid}">
		<input type="hidden" name="studienfachId"
			value="${studienfachId.studiengangname}"> <input
			type="hidden" name="modulname" value="${modul.modulname}">
		<div>
			<table class="list">
				<c:forEach var="karteikarte" items="${karteikarten}">
					<tr>
						<input type="hidden" name="karteikartenId"
							value="${karteikarte.karteikartenId}">
						<input type="hidden" name="fragentext"
							value="${karteikarte.fragentext}">
						<td>${karteikarte.titel}</td>
						<td>${karteikarte.fragentext}</td>
						<td><button type="submit" name="titel" id="karteikarte.titel"
								value="${karteikarte.titel}" />${karteikarte.titel}</button></td>
					</tr>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
</body>
</html>
