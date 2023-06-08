<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<!-- Quelle: generiert durch ChatGPT -->
<!-- Dieses Dokument wurde erstellt durch Moritz Reindl -->
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../css/Sidebar.css">
<title>Meine Studiengaenge</title>
</head>
<body>

<!--	<div class="sidebar">-->
<!--		<a href="#profil">Profil</a> <a class="active" href="#decks">Meine-->
<!--			Decks</a> <a href="#suchen">Suchen</a>-->
<!--	</div>-->

	<div class="decks">
		<h1 class="h1">Meine Studieng&#228;nge</h1>
		<input type="submit" value="Thema hinzuf&#252;gen" class="button">
	</div>
	<!-- Quelle: ab hier bis einschließlich Funktion saveStudiengang generiert durch ChatGPT -->
	<button onclick="showPopup()" class="buttonAdd">Studiengang
		hinzuf&#252;gen</button>
		<!-- Bis zur Markierung erstellt durch ChatGPT-->
		<button onclick="openPopup()" >Studiengang
				l&#246;schen</button>

		<div id="popup" class="popup">
			<h2>Titel auswählen</h2>
			<form action="../ViewTeacher_StudiengaengeLoeschenServlet" method="post">
			<ul id="studiengaengeListe">
				<c:forEach items="${studienfaecher}" var="studiengang">
				<!-- Nachfolgende Zeilen erstellt durch Moritz Reindl -->
				<input type="hidden" name="userid" value="${studiengang.userId}">
					<li><input type="checkbox" name="selectedStudiengaenge" value="${studiengang.studiengangname}"> <c:out value="${studiengang.studiengangname}" /></li>
				</c:forEach>
			</ul>
				<button type="submit">Löschen</button>
			<button onclick="closePopup()">Abbrechen</button>
			</form>
		</div>

		<script>
			// JavaScript-Code zum Anzeigen und Verwalten des Pop-up-Fensters
			function openPopup() {
				var popup = document.getElementById("popup");
				popup.style.display = "block";
			}

			function closePopup() {
				var popup = document.getElementById("popup");
				popup.style.display = "none";
			}
		</script>
<!-- Markierung! -->
	<form method="post" action="../ViewTeacher_SuchenHiddenServlet">
		<input type="hidden" name="userid" value="${LoginForm.userid}">
	<button type="submit" class="buttonSearch">Karteikarten durchsuchen</button>
	</form>
	<form method="post" action="../ViewTeacher_StudiengaengeServlet">
		<input type="hidden" name="userid" value="${LoginForm.userid}">
		<div id="popup" class="popup">
			<label for="studiengang">Studiengang:</label> <input type="text"
				name="studiengang" id="studiengang" required />
			<button onclick="saveStudiengang()">Speichern</button>
		</div>

		<script>
			function showPopup() {
				var popup = document.getElementById('popup');
				popup.style.display = 'block';
			}

			function saveStudiengang() {
				var studiengangInput = document.getElementById('studiengang');
				var studiengang = studiengangInput.value;
				alert('Studiengang gespeichert: ' + studiengang);
				var popup = document.getElementById('popup');
				popup.style.display = 'none';
			}
		</script>
	</form>
	<form method="get" action="../ViewTeacher_ModuleServlet">
	<input type="hidden" name="userid" value="${LoginForm.userid}">
		<div>
			<table class="list">
				<c:forEach var="studiengang" items="${studienfaecher}">
					<tr>
						<td>${studiengang.studiengangname}</td>
						<td><input type="submit" name="studienfachId" id="studienfachId" value="${studiengang.studiengangname}"/></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</form>
	<p>Studiengänge können nur einmal angelegt werden. Bei erneutem Anlegen eines bereits existierenden Studiengang, wird kein neuer
	Studiengang angelegt!</p>
</body>
</html>



<!-- <a href="ViewTeacher_Module.jsp?studienfachId=${studiengang.studiengangname}"> ChatGPT -->