<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<!-- Dieses Dokument wurde erstellt durch Fatih Doruk, Muhammed Samil Turan und Riza Dursun -->
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/ViewTeacher_KarteikarteErstellen.css">
</head>  

<body class="body">

		<h1 class="h1">
			<a href="ViewTeacher_Studiengaenge.jsp">Meine Studiengaenge</a>: <a
				href="ViewTeacher_Module.jsp">${karteikarte.studiengangname}</a>:
			${karteikarte.modulname}
		</h1>
	<div class="div">
	<form method="post" action="../ViewTeacher_KarteikarteErstellenServlet" enctype="multipart/form-data">
		<label for="titel">Titel:</label>
		<input type="text" id="titel" name="titel" class="input">
		<label for="fragentext">Frage:</label>
		<input type="text" id="fragentext" name="fragentext" class="input">
		<label for="titel">Bilddatei (optional):</label><br>
		<input type="file" name="image" id="image" accept="image/*" multiple><br>
		<div class="div2">
			<label style="font-size:110%;"><b>Antwortm&#246;glichkeiten:</b></label><br>
			<label for="antwortA">Antwort A:</label>
			<input type="text" id="antwortA" name="antwortA" class="input">
			<label for="antwortB">Antwort B:</label>
			<input type="text" id="antwortB" name="antwortB" class="input">
			<label for="antwortC">Antwort C:</label>
			<input type="text" id="antwortC" name="antwortC" class="input">
			<label for="antwortD">Antwort D:</label>
			<input type="text" id="antwortD" name="antwortD" class="input">
		</div>
		
		<label for="korrekteAntwort">Korrekte Antwort:</label>
			<select name="korrekteAntwort" id="korrekteAntwort" class="input">
				<option value="a">Antwort A</option>
				<option value="b">Anwort B</option>
				<option value="c">Anwort C</option>
				<option value="d">Anwort D</option>
			</select>
		<label for="korrekteAntwort">Begruendung der Antwort:</label>
		<input type="text" id="begruendungstext" name="begruendungstext" class="input">
		<button type="submit" name="submit" class="buttonSaveQuestion">Speichern</button>
	</form>
	<form action ="Profil.html">
			<input type="submit" value="Abbrechen" class="buttonCancel">
	</form>
	</div>
	<p>Eingeloggt: ${karteikarte.userId}</p>
</body>
</html>
