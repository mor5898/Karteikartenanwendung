<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${module == null || empty module}">
		Keine Treffer!
	</c:when>
	<c:otherwise>
		<form action="../View_StudentDeckHinzufuegen" method="post">
			<table>
				<tr>
					<th>Studiengang</th>
					<th>Modulname</th>
					<th>Dozent</th>
				</tr>
				<c:forEach var="modul" items="${module}">
					<tr>
						<td>${modul.studiengangname}</td>
						<td>${modul.modulname}</td>
						<td>${modul.userId}</td>
						<input type="hidden" name="studie"
							value="${modul.studiengangname}">
						<input type="hidden" name="id" value="${user.userid}">
						<input type="hidden" name="modul" value="${modul.modulname}">
						<input type="hidden" name="doz" value="${modul.userId}">
						<td><button type="submit" name="modul" id="mpdul"
								value="${modul.modulname}">Studiengang hinzufügen</button></td>
					</tr>
				</c:forEach>
			</table>
		</form>
	</c:otherwise>
</c:choose>
