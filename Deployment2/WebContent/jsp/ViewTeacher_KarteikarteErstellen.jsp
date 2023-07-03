<!-- Dieses Dokument wurde erstellt durch Riza Dursun -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/ViewTeacher_KarteikarteErstellen.css">
</head>  

<body class="body">

		<h1 class="h1">
			<a href="ViewTeacher_Studiengaenge.jsp">Meine Studieng&auml;nge</a>: <a
				href="ViewTeacher_Module.jsp">${karteikarte.studiengangname}</a>:
			${karteikarte.modulname}
		</h1>
	<div class="div">
	<form method="post" action="../ViewTeacher_KarteikarteErstellenServlet" enctype="multipart/form-data">
		<input type="hidden" name="userid" value="${karteikarte.userId}">
	    <input type="hidden" name="studienfachId" value="${karteikarte.studiengangname}">
	    <input type="hidden" name="modulname" value="${karteikarte.modulname}">
		<label for="titel">Titel:</label>
		<input type="text" id="titel" name="titel" class="input">
		<label for="fragentext">Frage:</label>
		<input type="text" id="fragentext" name="fragentext" class="input" required maxlength="250">
		<label for="titel">Bilddatei (optional):</label><br>
		<input type="file" name="image" id="image" accept="image/*" multiple>
		<label>Es kann nur eine Bilddatei hochgeladen werden!</label>
		<div class="div2">
			<label id="answer"><b>Antwortm&#246;glichkeiten:</b></label><br>
			<label for="antwortA">Antwort A:</label>
			<input type="text" id="antwortA" name="antwortA" class="input" required maxlength="250">
			<label for="antwortB">Antwort B:</label>
			<input type="text" id="antwortB" name="antwortB" class="input" required maxlength="250">
			<label for="antwortC">Antwort C:</label>
			<input type="text" id="antwortC" name="antwortC" class="input" required maxlength="250">
			<label for="antwortD">Antwort D:</label>
			<input type="text" id="antwortD" name="antwortD" class="input" required maxlength="250">
		</div>
		
		<label for="korrekteAntwort">Korrekte Antwort:</label>
			<select name="korrekteAntwort" id="korrekteAntwort" class="input" required>
				<option value="A">Antwort A</option>
				<option value="B">Anwort B</option>
				<option value="C">Anwort C</option>
				<option value="D">Anwort D</option>
			</select>
		<label for="korrekteAntwort">Begr&uuml;ndung der Antwort:</label>
		<input type="text" id="begruendungstext" name="begruendungstext" class="input" required maxlength="500">
		<button type="submit" name="submit" class="buttonSaveQuestion">Speichern</button>
	</form>
	<form action ="ViewTeacher_Karteikarten.jsp">
			<input type="submit" value="Abbrechen" class="buttonCancel">
	</form>
	</div>
	<p>Eingeloggt: ${karteikarte.userId}</p>
</body>
</html>
