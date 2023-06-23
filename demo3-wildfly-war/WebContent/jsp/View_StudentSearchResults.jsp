<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${module == null || empty module}">
		Keine Treffer!
	</c:when>
	<c:otherwise>
		<form action="../View_StudentDeckHinzufuegen?id=${LoginForm.userid}"
			method="post">
			<table>
				<tr>
					<th>Studiengang</th>
					<th>Modulname</th>
					<th>Dozent</th>
					<th>Aktion</th>
				</tr>
				<c:forEach items="${module}" var="modul" varStatus="status">
					<tr>
						<td>${modul.studiengangname}</td>
						<td>${modul.modulname}</td>
						<td>${modul.userId}</td>
						<td><input type="hidden" name="studie_${status.index}"
							value="${modul.studiengangname}" /> <input type="hidden"
							name="doz_${status.index}" value="${modul.userId}" /> <input
							type="hidden" name="modul_${status.index}"
							value="${modul.modulname}" />

							<button type="submit" name="modul" id="modul"
								value="${modul.modulname}">Studiengang hinzufügen</button></td>
					</tr>
				</c:forEach>
			</table>
		</form>
		<p>Modules Attribute: ${sessionScope.modules}</p>
		<p>ID Attribute: ${sessionScope.id}</p>
	</c:otherwise>
</c:choose>
