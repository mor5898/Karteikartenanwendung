<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<!-- Dieses Dokument wurde erstellt durch Fatih Doruk und Riza Dursun -->
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/erstellen.cssss">
</head>

<body class="body">

	<div class="sidebar">
		<a href="#profil">Profil</a> <a class="active" href="#decks">Meine
			Decks</a> <a href="#suchen">Suchen</a>
	</div>
	<div class="decks">
		<h1 class="h1">
			<a href="MeineThemen.html">Meine Themen</a>: <a
				href="MeineThemen.html">Wirtschaftsinformatik</a>: Datenbanksysteme
		</h1>
	</div>
	<form method="post" action="../ViewTeacher_KarteikarteBearbeitenPersistServlet"
		enctype="multipart/form-data">
		<input type="hidden" name="userid" value="${karteikarte.userId}">
		<input type="hidden" name="karteikartenId" value="${karteikarte.karteikartenId}">
		<input type="hidden" name="studienfachId"
			value="${karteikarte.studiengangname}"> 
		<input
			type="hidden" name="modulname" value="${karteikarte.modulname}">
		<div class="answer">
			<div class="grid-item">Titel:</div>
			<input type="text" id="titel" name="titel"
				value="${karteikarte.titel}" required>
			<div class="grid-item">Frage:</div>
			<input type="text" id="fragentext" name="fragentext"
				value="${karteikarte.fragentext}" required>
			<div class="grid-item">Bilddatei (optional):</div>

			<button type="submit" name="submit">speichern</button>
			<input type="file" name="image" id="image" accept="image/*" multiple>

			<div class="grid-item">Antwortm&#246;glichkeiten:</div>
			<div class="answerOptions">
				<div class="grid-item">Antwort A:</div>
				<input type="text" id="antwortA" name="antwortA"
					value="${karteikarte.antwortA}" required>
				<div class="grid-item">Antwort B:</div>
				<input type="text" id="antwortB" name="antwortB"
					value="${karteikarte.antwortB}" required>
				<div class="grid-item">Antwort C:</div>
				<input type="text" id="antwortC" name="antwortC"
					value="${karteikarte.antwortC}" required>
				<div class="grid-item">Antwort D:</div>
				<input type="text" id="antwortD" name="antwortD"
					value="${karteikarte.antwortD}" required>
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
				value="${karteikarte.begruendung}" required>
			<div class="button"></div>
		</div>
	</form>
	<form action="Profil.html">
		<input type="submit" value="Abbrechen" class="buttonCancel">
	</form>
</body>
</html>
