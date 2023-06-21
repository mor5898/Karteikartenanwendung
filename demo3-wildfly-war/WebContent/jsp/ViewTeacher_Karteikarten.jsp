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
<script src="../javascript/ViewTeacher_Karteikarten.js"> </script>
<!--	<div class="sidebar">-->
<!--		<a href="#profil">Profil</a> <a class="active" href="#decks">Meine-->
<!--			Decks</a> <a href="#suchen">Suchen</a>-->
<!--	</div>-->

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
		<form method="get"
			action="../ViewTeacher_ModuleServlet">
				<input type="hidden" name="userid" value="${LoginForm.userid}">
			<input type="hidden" name="studienfachId"
				value="${studienfachId.studiengangname}"> 
		<button type="submit" value="zur&#252;ck" class="buttonBack">
			zur&#252;ck
		</button>
		</form>
<!-- Bis zur Markierung erstellt durch ChatGPT-->
		<button onclick="openPopup()" >Karteikarte
				l&#246;schen</button>

		<div id="popup" class="popup">
			<h2>Titel auswählen</h2>
			<form action="../ViewTeacher_KarteikartenLoeschenServlet" method="post">
			<ul id="karteikartenListe">
				<c:forEach items="${karteikarten}" var="karteikarte">
				<!-- Nachfolgende Zeilen erstellt durch Moritz Reindl -->
				<input type="hidden" name="modulname" value="${karteikarte.modulname}">
				<input type="hidden" name="userid" value="${karteikarte.userId}">
				<input type="hidden" name="studienfachId" value="${karteikarte.studiengangname}">
					<li><input type="checkbox" name="selectedKarteikarten" value="${karteikarte.karteikartenId}"> <c:out value="${karteikarte.titel}" /></li>
				</c:forEach>
			</ul>
				<button type="submit">Löschen</button>
			</form>
			<button onclick="closePopup()">Abbrechen</button>
		</div>
<!-- Markierung! -->

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
	<p>Eingeloggt: ${LoginForm.userid}</p>
</body>
</html>
