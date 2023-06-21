<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Mein Profil</title>
<script src="../javascript/View_StudentHome.js"></script>
</head>
<body>
<div class ="Suchen">
<h1>Decks hinzufuegen</h1>
<form action="../View_StudentSearchServlet" method="post" >
 <input type="hidden" name="userid" value="${LoginForm.userid}">
<button name="submit" type="submit">Weiter zur Suche</button> 
</form>
</div>
<h1>Meine Decks</h1>
<div class="Decks">
 <!--  <form action="../ModulSuchServlet?id=${LoginForm.userid}" method="post" >
 <button name="submit" type="submit">Decks Laden</button>-->
 <table>
				<tr>
					<th>Studiengang</th>
					<th>Modul</th>
					<th>Dozent</th>
				</tr>
				
				 <c:forEach var="module" items="${modules}">
            <form action="ViewStudent_KarteikarteAbfrageServlet?studie=${module.studiengangname}&modul=${module.modulname}&doz=${module.userId}"  method="post"  >
               		
               <tr>
                    <td><c:out value="${module.studiengangname}" /></td>
                    <td><c:out value="${module.modulname}" /></td>
                    <td><c:out value="${module.userId}" /></td>
                    
                    
                    <!-- Weitere Tabellenzellen für zusätzliche Eigenschaften des DataObjects -->
                </tr>
                <tr>
                 <td colspan="4">
                    <button type="submit" name="submit">Abfragen</button>
                </td>
            </tr>
            </form>
            </c:forEach>
            
	<!--  			<c:forEach var="module" items="${modules}">
					
					<tr>
					
					<td><c:out value="${module.studiengangname}" /></td>
                    <td><c:out value="${module.modulname}" /></td>
                    <td><c:out value="${module.userId}" /></td>
						
					</tr>
				</c:forEach>-->
			</table>
<!-- </form> -->
</div>


</body>
</html>