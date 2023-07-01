<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<!-- Dieses Dokument wurde erstellt durch Moritz Reindl -->
<!-- Seite zum Verwalten der Module eines Studiengangs -->
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../css/ViewTeacher_ModuleCSS.css">
<script src="../javascript/ViewTeacher_Studiengaenge.js">
	
</script>
</head>
<body>
	<div class="container">
		<p>
			<a id="abmelden" href="../index.html">Abmelden</a>
		</p>
		<div class="decks">
			
			<form action="../ViewTeacher_ModuleServlet" method="post">
				<h1 class="h1">
					<a href="ViewTeacher_Studiengaenge.jsp">Meine Studiengaenge</a>:${studienfachId.studiengangname}
				</h1>
				<input type="hidden" name="userid" value="${LoginForm.userid}">
				<input type="hidden" name="studienfachId"
					value="${studienfachId.studiengangname}">
				<button id="buttonAddStudie" class="buttonAdd">Modul
					hinzuf&#252;gen</button>
				<div id="popup" class="popup">
					<label for="modul">Modultitel:</label> <input type="text"
						name="modul" id="modul" required maxlength="50" />
					<button id="buttonSaveModul">Speichern</button>
					<button id="buttonClosePopupAdd">Abbrechen</button>
				</div>
			</form>
			
			<!-- Bis zur Markierung erstellt durch ChatGPT-->
			<button id="buttonDelete">Modul l&#246;schen</button>

			<div id="popupDelete" class="popup">
				<h2>Modul auswählen</h2>
				<form action="../ViewTeacher_ModuleLoeschenServlet" method="post"
					id="modulDeleteForm">
					<ul id="moduleListe">
						<c:forEach items="${module}" var="modul">
							<!-- Nachfolgende Zeilen erstellt durch Moritz Reindl -->
							<input type="hidden" name="userid" value="${modul.userId}">
							<input type="hidden" name="studienfachId"
								value="${modul.studiengangname}">
							<li><input type="checkbox" name="selectedModule"
								value="${modul.modulname}"> <c:out
									value="${modul.modulname}" /></li>
						</c:forEach>
					</ul>
					<div class="button-container">
						<button type="submit">Löschen</button>
					</div>
				</form>
				<button id="buttonPopupClose">Abbrechen</button>
			</div>
		<!-- Markierung! -->
			
			<button type="submit" value="zur&#252;ck" class="buttonBack">
				<a href="ViewTeacher_Studiengaenge.jsp">zur&#252;ck</a></button>
		</div>
		
		<form method="post" action="../ViewTeacher_KarteikartenServlet">
			<input type="hidden" name="userid" value="${LoginForm.userid}">
			<input type="hidden" name="studienfachId"
				value="${studienfachId.studiengangname}">
			<div>
				<table class="list">
					<c:forEach var="modul" items="${module}">
						<tr>
							<td>${modul.modulname}</td>
							<td><button type="submit" name="modulname"
									id="modul.modulname" value="${modul.modulname}" />Zum Modul
								</button></td>
						</tr>
						</tr>
					</c:forEach>
				</table>
			</div>
		</form>
		
		<p>Module können nur einmal angelegt werden. Bei erneutem Anlegen
			eines bereits existierenden Modul, wird kein neues Modul angelegt!</p>
		
		<p>Eingeloggt: ${LoginForm.userid}</p>
	</div>
	
</body>
</html>



