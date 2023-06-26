<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${pageContext.request.requestURI}" />
		<meta charset="UTF-8">
		<link rel="stylesheet" href="../css/erstellen.css">
		<title>Karteikarte erstellt</title>
	</head>
	<body>
	<div class="answer">
		<div class="grid-item">Titel: ${form.titel}</div>
		<div class="grid-item">Frage: ${form.fragentext}</div>
		<div class="grid-item">Bild: <img src="../../BildServlet?id=${form.karteikartenId}"></div>
		<div class="grid-item">Antwortm&#246;glichkeiten:</div>
		<div class="answerOptions">
			<div class="grid-item">Antwort A: ${form.antwortA}</div>
			<div class="grid-item">Antwort B: ${form.antwortB}</div>
			<div class="grid-item">Antwort C: ${form.antwortC}</div>
			<div class="grid-item">Antwort D: ${form.antwortD}</div>
		</div>
		<div class="grid-item">Korrekte Antwort: ${form.korrekteAntwort}</div>
		<div class="grid-item">Begruendung der Antwort: ${form.begruendung}</div>
		<div class="button">
		<form action ="Profil.html">
		<input type="submit" value="Bearbeiten" class="buttonCancel">
		</form>
	</div>
	</body>
</html>