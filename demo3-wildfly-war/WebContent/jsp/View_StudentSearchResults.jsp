<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Suchergebnis</title>
</head>
<body>
    <h1>Suchergebnis</h1>
    <table>
        <thead>
            <tr>
                <th>Studiengang</th>
                <th>Modul</th>
                <th>Dozent</th>
                <th>Aktion</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="modul" items="${module}">
                <tr>
                    <td>${modul.studiengangname}</td>
                    <td>${modul.modulname}</td>
                    <td>${modul.userId}</td>
                    <td>
                        <form action="../View_StudentDeckHinzufuegen" method="post">
                            <input type="hidden" name="id" value="${LoginForm.userid}">
                            <input type="hidden" name="studie" value="${modul.studiengangname}">
                            <input type="hidden" name="modul" value="${modul.modulname}">
                            <input type="hidden" name="doz" value="${modul.userId}">
                            <button type="submit">Hinzufügen</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
