<!-- Erstellt von Moritz Reindl -->
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
			<a href="MeineThemen.html">Meine Themen</a>: <a
				href="MeineThemen.html">Wirtschaftsinformatik</a>: Datenbanksysteme
		</h1>
	<div class="div">
	<form method="post" action="../ViewTeacher_KarteikarteBearbeitenPersistServlet"
		enctype="multipart/form-data">
		<input type="hidden" name="userid" value="${karteikarte.userId}">
		<input type="hidden" name="karteikartenId" value="${karteikarte.karteikartenId}">
		<input type="hidden" name="studienfachId" value="${karteikarte.studiengangname}"> 
		<input type="hidden" name="modulname" value="${karteikarte.modulname}">
		<div class="answer">
			<div class="grid-item">Titel:</div>
			<input type="text" id="titel" name="titel"
				value="${karteikarte.titel}" required maxlength="50">
			<div class="grid-item">Frage:</div>
			<input type="text" id="fragentext" name="fragentext"
				value="${karteikarte.fragentext}" required maxlength="250">
			<div class="grid-item">Bilddatei (optional):</div>

			<button type="submit" name="submit">speichern</button>
			<input type="file" name="image" id="image" accept="image/*" multiple>
	<div class="div2">
			<label id="answer"><b>Antwortm&#246;glichkeiten:</b></label><br>
			<div class="answerOptions">
				<div class="grid-item">Antwort A:</div>
				<input type="text" id="antwortA" name="antwortA"
					value="${karteikarte.antwortA}" required maxlength="250">
				<div class="grid-item">Antwort B:</div>
				<input type="text" id="antwortB" name="antwortB"
					value="${karteikarte.antwortB}" required maxlength="250">
				<div class="grid-item">Antwort C:</div>
				<input type="text" id="antwortC" name="antwortC"
					value="${karteikarte.antwortC}" required maxlength="250">
				<div class="grid-item">Antwort D:</div>
				<input type="text" id="antwortD" name="antwortD"
					value="${karteikarte.antwortD}" required maxlength="250">
			</div>
			<div class="grid-item">Korrekte Antwort:</div>
			<select name="korrekteAntwort" id="korrekteAntwort" required>
				<option value="a">Antwort A</option>
				<option value="b">Anwort B</option>
				<option value="c">Anwort C</option>
				<option value="d">Anwort D</option>
			</select>
			<div class="grid-item">Begruendung der Antwort:</div>
			<input type="text" id="begruendungstext" name="begruendungstext"
				value="${karteikarte.begruendung}" required maxlength="500">
			<div class="button"></div>
		</div>
	</form>
	<form action="Profil.html">
		<button type="submit" value="zur&#252;ck" class="buttonBack"><a href="ViewTeacher_Karteikarten.jsp">zur&#252;ck</a></button>
	</form>
	</div>
	<p>Eingeloggt: ${karteikarte.userId}</p>
</body>
</html>
