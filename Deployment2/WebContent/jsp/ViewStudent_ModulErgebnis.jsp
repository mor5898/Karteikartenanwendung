<!-- Erstellt von Riza Dursun -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Datenbanksuche</title>
<link rel="stylesheet" type="text/css" href="../css/ViewStudent_Suche.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="bild">
		<img src="../images/bil.jpg">
	</div>
	<div class="mitte">
		<script type="text/javascript"
			src="../javascript/ViewStudent_ModulErgebnis.js"></script>
		<h1 class="h1">Datenbanksuche</h1>
		<form id="searchForm" method="post">
			<input type="hidden" id="userid" name="userid" value="${user.userid}">
			<label class="label" for="searchTerm">Modul eingeben:</label> <input
				class="feld" type="text" id="searchTerm" name="searchTerm"><br>
			<button class="suchen" type="button" id="button" name="button">Suchen</button>
		</form>

		<div id="hitlist">Keine Treffer</div>
		<a href="ViewStudent_Home.jsp" class="zur2"><button class="zur"
				type="submit" name="submit">zur&#252;ck</button></a>
	</div>
</body>
</html>
