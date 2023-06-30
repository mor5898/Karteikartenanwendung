<!-- Dieses Dokument wurde erstellt durch Moritz Reindl -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Seite zum Verwalten der Studiengänge eines Users -->
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../css/ViewTeacher_StudiengaengeCSS.css">
<title>Meine Studiengaenge</title>
<script src="../javascript/ViewTeacher_Studiengaenge.js"></script>
</head>
<body>
	<div class="container">
		<div class="popup-overlay"></div>
		<p>
			<a id="abmelden" href="../index.html">Abmelden</a>
		</p>
		<div class="decks">
			<h1 class="h1">Meine Studieng&#228;nge</h1>
		</div>
	
		<!-- Bis zur Markierung erstellt durch ChatGPT-->
		<button class="buttonAdd" id="buttonAddStudie">Studiengang
			hinzuf&#252;gen</button>
		<button class="buttonAdd" id="buttonDelete">Studiengang
			l&#246;schen</button>

		<div id="popupDelete" class="popup">
			<h2>Studiengang auswählen</h2>
			<form action="../ViewTeacher_StudiengaengeLoeschenServlet"
				method="post" id="studiengangDeleteForm">
				<input type="hidden" name="userid" value="${LoginForm.userid}">
				<ul id="studiengaengeListe">
					<c:forEach items="${studienfaecher}" var="studiengang">
						<input type="hidden" name="userid" value="${studiengang.userId}">
						<li><input type="checkbox" name="selectedStudiengaenge"
							value="${studiengang.studiengangname}"> <c:out
								value="${studiengang.studiengangname}" /></li>
					</c:forEach>
				</ul>
				<div class="button-container">
					<button type="submit">Löschen</button>
				</div>
			</form>
			<div class="button-container">
				<button id="buttonPopupClose">Abbrechen</button>
			</div>
		</div>
		<!-- Markierung! -->

		<form method="post" action="../ViewTeacher_SuchenHiddenServlet">
			<input type="hidden" name="userid" value="${LoginForm.userid}">
			<button type="submit" class="buttonSearch">Meine gesamten
				Karteikarten durchsuchen</button>
		</form>
	
		<form method="post" action="../ViewTeacher_StudiengaengeServlet">
			<input type="hidden" name="userid" value="${LoginForm.userid}">
			<div id="popup" class="popup">
				<label for="studiengang">Studiengang:</label> <input type="text"
					name="studiengang" id="studiengang" required maxlength="40" />
				<button>Speichern</button>
				<button id="buttonClosePopupAdd">Abbrechen</button>
			</div>
		</form>
	
		<form method="get" action="../ViewTeacher_ModuleServlet">
			<input type="hidden" name="userid" value="${LoginForm.userid}">
			<div>
				<table class="list">
					<c:forEach var="studiengang" items="${studienfaecher}">
						<tr>
							<td>${studiengang.studiengangname}</td>
							<td><button type="submit" name="studienfachId"
									id="studienfachId" value="${studiengang.studiengangname}" />Zum
								Studiengang
								</button></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</form>
		
		<p>Studiengänge können nur einmal angelegt werden. Bei erneutem
			Anlegen eines bereits existierenden Studiengang, wird kein neuer
			Studiengang angelegt!</p>
		
		<p>Eingeloggt: ${LoginForm.userid}</p>
	</div>
	
</body>
</html>
