<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Mein Profil</title>
<link rel="stylesheet" type="text/css" href="../css/ViewStudent_Home.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<div class="bild">
		<img src="../images/bil.jpg">
	</div>

	<div class="mitte">
		<Label class="user"> Eingeloggt: ${LoginForm.userid}</Label> <a
			href="../index.html" class="abm2"><button class="abm"
				type="submit" name="submit">Abmelden</button></a>
		<div class="Suchen">
			<h2 class="h2">Decks hinzuf&#252;gen</h2>
			<form action="../ViewStudent_SearchServlet" method="post">
				<input type="hidden" name="userid" value="${LoginForm.userid}">
				<button class="suchebutt" name="submit" type="submit">Weiter
					zur Suche</button>
			</form>
		</div>
		<h1 class="h1">Meine Decks</h1>
		<div class="Decks">

			<table>
				<tr>
					<th>Studiengang</th>
					<th>Modul</th>
					<th>Dozent</th>

				</tr>

				<c:forEach var="module" items="${modules}">
					<form
						action="../ViewStudent_KarteikarteAbfrageServlet?studie=${module.studiengangname}&modul=${module.modulname}&doz=${module.userId}"
						method="post">

						<tr>
							<td><c:out value="${module.studiengangname}" /></td>
							<td><c:out value="${module.modulname}" /></td>
							<td><c:out value="${module.userId}" /></td>
							<td class="button" colspan="3">
								<button type="submit" name="submit">Abfragen</button>
							</td>
					</form>
					<td class="delete">
						<form action="../ViewStudent_DecksLoeschen" method="get">
							<input type="hidden" name="id" value="${LoginForm.userid}" /> <input
								type="hidden" name="studie" value="${module.studiengangname}" />
							<input type="hidden" name="modul" value="${module.modulname}" />
							<input type="hidden" name="doz" value="${module.userId}" />
							<button type="submit">Löschen</button>
						</form>
					</td>
					</tr>


				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>