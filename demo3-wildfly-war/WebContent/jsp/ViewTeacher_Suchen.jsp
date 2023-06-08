<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<!-- Quelle: https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_sidebar_responsive -->
	<!-- Dieses Dokument wurde erstellt durch Moritz Reindl und Fatih Doruk-->
	<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
	<link rel="stylesheet" href="../css/Sidebar.css">
	</head>
	<body>
		<div class="sidebar">
		  <a href="#profil">Profil</a>
		  <a href="#decks">Meine Decks</a>
		  <a class="active" href="#suchen">Suchen</a>
		</div>
		<h1 class="h1">Suchen</h1>
		<div class="search">
			<form action="../ViewTeacher_SuchServlet" method="post" >
				<input type="hidden" name="userid" value="${user.userid}">
				<fieldset><legend>Decks suchen</legend>
					<div class="grid-item">Schlagwort:</div>
					  	<input type="text" id="schlagwort" name="schlagwort" placeholder="Hier SchlagwÃ¶rter eingeben"> 
					<div class="grid-item">
						<button name="submit" type="submit">Suche starten</button>
						<button name="reset" type="reset">ZurÃ¼cksetzen</button>
					</div>
				</fieldset>
			</form>
		</div>
	</body>
</html>