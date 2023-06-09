<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<!-- Quelle: https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_sidebar_responsive -->
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
		<form action="../ViewTeacher_ModuleServlet" method="post">
			<h1 class="h1">
				<a href="../jsp/MeineThemen.jsp">Meine Studiengaenge</a>:${studienfachId.studiengangname}
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
			</div>

			<script>
				function showPopup() {
					var popup = document.getElementById('popup');
					popup.style.display = 'block';
				}

				function saveModul() {
					var modulInput = document.getElementById('modul');
					var modul = modulInput.value;
					alert('Modul gespeichert: ' + modul);
					var popup = document.getElementById('popup');
					popup.style.display = 'none';
				}
			</script>
		</form>
		<input type="submit" value="zur&#252;ck" class="buttonBack">
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
</body>
</html>



