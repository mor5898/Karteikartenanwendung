<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Datenbanksuche</title>
</head>
<body>
    <script type="text/javascript" src="../javascript/View_StudentModulErgebnis.js"></script>
    <h1>Datenbanksuche</h1>
    <form id="searchForm" method="post">
     <input type="hidden" id="userid" name="userid" value="${user.userid}">
        <label for="searchTerm">Modul eingeben:</label> <input type="text" id="searchTerm" name="searchTerm" ><br>
        <button type="button" id="button" name="button" >Suchen</button>
    </form>
     <div id="hitlist">Keine Treffer</div>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    <div>
     
        <table>
            <tr>
                <th>Studiegangname</th>
                <th>Modulname</th>
                <th>Dozent</th>
                <!-- Weitere Spaltenüberschriften für zusätzliche Eigenschaften des DataObjects -->
            </tr>
           
            <c:forEach var="dataObject" items="${searchResults}">
            <form action="View_StudentDeckHinzufuegen?id=${LoginForm.userid}&doz=${dataObject.userId}&studie=${dataObject.studiengangname}&modul=${dataObject.modulname}"  method="post"  >
               		
               <tr>
                    <td><c:out value="${dataObject.studiengangname}" /></td>
                    <td><c:out value="${dataObject.modulname}" /></td>
                    <td><c:out value="${dataObject.userId}" /></td>
                    
                    
                    <!-- Weitere Tabellenzellen für zusätzliche Eigenschaften des DataObjects -->
                </tr>
                <tr>
                 <td colspan="4">
                    <button type="submit" name="submit">Hinzufuegen</button>
                </td>
            </tr>
            </form>
            </c:forEach>
        </table>
        
			<!-- <form action="ModulSuchServlet?id=${LoginForm.userid}" method="post"> -->
			<button type="submit" name="submit"><a href="View_StudentHome.jsp">zur&#252;ck</a></button>
			</form>
    </div>
    
    
    
    
    
    
    
    
    
    
</body>
</html>
