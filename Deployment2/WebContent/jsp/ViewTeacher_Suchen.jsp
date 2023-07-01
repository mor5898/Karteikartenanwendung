<!-- Dieses Dokument wurde erstellt durch Moritz Reindl-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" charset="UTF-8">
	<link rel="stylesheet" href="../css/ViewTeacher_Suchen.css">
	</head>
	<body>
		<div>
			<form action="../ViewTeacher_SuchServlet" method="post" >
				<input type="hidden" name="userid" value="${user.userid}">
				
				<label class="eingeloggt">Eingeloggt: ${user.userid}</label>
				<fieldset><legend>Decks suchen</legend>
					<div class="suche">
						<label>Titel der Karteikarte:</label>
					  	<input type="text" id="schlagwort" name="schlagwort" placeholder="Hier Schlagw&ouml;rter eingeben..." maxlength="50"> 
					  	<button name="submit" type="submit">Suche starten</button>
					</div>
					<div class="buttonContainer">
						
						<button name="reset" type="reset">Zur&#252;cksetzen</button>
					</div>
				</fieldset>
				<div class="zurueckButton">
					<button name="zurueck" type="submit" formaction="ViewTeacher_Studiengaenge.jsp" value="zur&#252;ck" >
						Zur&#252;ck
					</button>
				</div>
			</form>
		</div>
	</body>
</html>