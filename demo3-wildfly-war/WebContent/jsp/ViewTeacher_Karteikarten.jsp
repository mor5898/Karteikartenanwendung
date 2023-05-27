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
  <a href="#profil">Profil</a>
  <a class="active" href="#decks">Meine Decks</a>
  <a href="#suchen">Suchen</a>
</div>

<div class="decks">
	<h1 class="h1"><a href="ViewTeacher_Studiengaenge.jsp">Meine Studiengaenge</a>: <a href="ViewTeacher_Module.jsp">${studienfachId.studiengangname}</a>: ${modul.modulname}</h1>
	<input type="submit" value="Frage hinzuf&#252;gen" class="buttonAdd">
	<input type="submit" value="zur&#252;ck" class="buttonBack">
	<input type="submit" value="speichern" class="buttonSave">
	<input type="submit" value="Deck ver&#246;ffentlichen" class="buttonPublish">
</div>

</body>
</html>
