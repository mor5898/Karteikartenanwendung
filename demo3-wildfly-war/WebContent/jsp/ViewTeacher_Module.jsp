<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<!-- Quelle: https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_sidebar_responsive -->
<!-- Dieses Dokument wurde erstellt durch Moritz Reindl -->
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../css/ViewTeacher_ModuleCSS.css">
</head>
<body>
<script src="../javascript/ViewTeacher_Studiengaenge.js"> </script>
<!--	<div class="sidebar">-->
<!--		<a href="#profil">Profil</a> <a class="active" href="#decks">Meine-->
<!--			Decks</a> <a href="#suchen">Suchen</a>-->
<!--	</div>-->

	<div class="decks">
		<form action="../ViewTeacher_ModuleServlet" method="post">
			<h1 class="h1">
				<a href="ViewTeacher_Studiengaenge.jsp">Meine Studiengaenge</a>:${studienfachId.studiengangname}
			</h1>
			<!--<form action="../ViewTeacher_ModuleServlet" method="post">-->
			<input type="hidden" name="userid" value="${LoginForm.userid}">
			<input type="hidden" name="studienfachId"
				value="${studienfachId.studiengangname}">
			<button onclick="showPopup()" class="buttonAdd">Modul
				hinzuf&#252;gen</button>
			<div id="popup" class="popup">
				<label for="modul">Modultitel:</label> <input type="text"
					name="modul" id="modul" required />
				<button onclick="saveModul()">Speichern</button>
				<button onclick="closePopupAdd()">Abbrechen</button>
			</div>
		</form>
<!-- Bis zur Markierung erstellt durch ChatGPT-->
		<button onclick="openPopup()" >Modul
				l&#246;schen</button>

		<div id="popupDelete" class="popup">
			<h2>Modul auswählen</h2>
			<form action="../ViewTeacher_ModuleLoeschenServlet" method="post">
			<ul id="moduleListe">
				<c:forEach items="${module}" var="modul">
				<!-- Nachfolgende Zeilen erstellt durch Moritz Reindl -->
				<input type="hidden" name="userid" value="${modul.userId}">
				<input type="hidden" name="studienfachId" value="${modul.studiengangname}">
					<li><input type="checkbox" name="selectedModule" value="${modul.modulname}"> <c:out value="${modul.modulname}" /></li>
				</c:forEach>
			</ul>
				<button type="submit">Löschen</button>
			</form>
			<button onclick="closePopup()">Abbrechen</button>
		</div>


<!-- Markierung! -->
		<button type="submit" value="zur&#252;ck" class="buttonBack"><a href="ViewTeacher_Studiengaenge.jsp">zur&#252;ck</a></button>
	</div>
	<form method="post" action="../ViewTeacher_KarteikartenServlet">
		<input type="hidden" name="userid" value="${LoginForm.userid}">
		<input type="hidden" name="studienfachId" value="${studienfachId.studiengangname}">
		<div>
			<table class="list">
				<c:forEach var="modul" items="${module}">
					<tr>
						<td><a href="ViewTeacher_Karteikarten.jsp?modulname=${modul.modulname}">${modul.modulname}</a></td>
						<td>${modul.modulname}</td>
						<td><input type="submit" name="modulname"id="modul.modulname" value="${modul.modulname}" /></td>
						</tr>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
	<p>Modulname können nur einmal angelegt werden. Bei erneutem Anlegen eines bereits existierenden Modulname, wird kein neues
	Modul angelegt!</p>
	<p>Eingeloggt: ${LoginForm.userid}</p>
</body>
</html>



