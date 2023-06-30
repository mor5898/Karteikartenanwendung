<!-- Erstellt von Fatih Doruk -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="${pageContext.request.requestURI}" />
		<meta charset="UTF-8">
		<link rel="stylesheet" href="../css/ViewStudent_karteikarteAbfrage.css">
		<script src="../javascript/ViewStudent_KarteikarteAbfrage.js"></script>
		<title>Karteikarte Abfrage</title>
	</head>
	<body>
	
		<form class="form" id=abfrageForm method=post action="../ViewStudent_KarteikarteNaechsteFrageServlet">
		<div>
			<c:forEach var="currentKarteikarte" items="${ karteikarten }" varStatus="status">
			<c:if test="${currentKarteikarte.currentFlag == true}"> 
			<input type="hidden" name="count" id="count" value="${currentKarteikarte.count}" />
			<input type="hidden" name="length" id="length" value="${karteikarten.size()}" />
			<input type="hidden" name="studiengang" value="${currentKarteikarte.studiengangname}" />
			<input type="hidden" name="modul" value="${currentKarteikarte.modulname}" />
			<input type="hidden" name="dozent" value="${currentKarteikarte.user}" />
			<input type="hidden" id="korrekteAntwort" value="${currentKarteikarte.korrekteAntwort}" />
			<fieldset><legend>&Uuml;berpr&uuml;fung Karteikarte</legend>
			<div class="overhead">Studiengang: ${currentKarteikarte.studiengangname}</div>
			<div class="overhead">Modul: ${currentKarteikarte.modulname}</div>
			<div class="overhead">Bereitgestellt von: ${currentKarteikarte.user}</div>
			<div class="overhead">Titel: ${currentKarteikarte.titel}</div>
			</fieldset>
			<fieldset>
						<div>	
							<div>
								<div class="anzahlZeiger">
									<label for="anzahlFalsch" class="anzahlFalsch">&#9746;:</label>
									<input id ="anzahlFalsch" name="anzahlFalsch" class="anzahlFalsch" type="text" readonly="true" value="${currentKarteikarte.anzahlFalsch}"/>
									<label for="anzahlRichtig" class="anzahlRichtig">&#9745;:</label>
									<input id="anzahlRichtig" name="anzahlRichtig" class="anzahlRichtig" type="text" readonly="true" value="${currentKarteikarte.anzahlRichtig}"/>
								</div>
								<div class="frage">${currentKarteikarte.frage}</div>
									<c:if test="${currentKarteikarte.bildFlag == true}">
										<div class="bild">
											<img src="../ViewTeacher_KarteikarteErstellenBildServlet?id=${currentKarteikarte.karteikartenID}"/>
										</div>
									</c:if>
							</div>
							<div>
								<div class="antwort" id="divA">
									<input type="radio" name="antwort" id="antwortA" value="a" /> 
									<label for="antwortA">${currentKarteikarte.antwortA}</label>
								</div>	
								<div class="antwort" id="divB">
									<input type="radio" name="antwort" id="antwortB" value="b" />
									<label for="antwortB">${currentKarteikarte.antwortB}</label>
								</div>
								<div class="antwort" id="divC">
									<input type="radio" name="antwort" id="antwortC" value="c" />
									<label for="antwortC">${currentKarteikarte.antwortC}</label>
								</div>
								<div class="antwort" id="divD">
									<input type="radio" name="antwort" id="antwortD" value="d" />
									<label for="antwortD">${currentKarteikarte.antwortD}</label>	
								</div>	
							</div>
						</div>
						<div>
							<button type="button" id="begruendungButton" class="disabledButton" disabled>Begr&uuml;ndung anzeigen</button>
						</div>
						<div>
							<div id="begruendung" hidden="hidden">${currentKarteikarte.begruendung}</div>
						</div>
						<div class="buttonContainer">
						<button name="beenden" type="submit" id="beenden" formaction="/Deployment2/jsp/ViewStudent_Home.jsp">Beenden</button>
						<button name="naechste Frage" type="submit" id="naechsteFrage">N&auml;chste Frage</button>
						</div>
			</fieldset>
			</c:if>
			</c:forEach>
		</div>
		</form>
	</body>
</html>