package edu.thi.servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import edu.thi.bean.ViewTeacher_ModuleBean;
import edu.thi.bean.ViewTeacher_KarteikarteErstellenBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//Servlet vollständig generiert durch ChatGPT
@WebServlet("/UNUSED_View_StudentModulSuche")
public class UNUSED_View_StudentModulSuche extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	String searchTerm = request.getParameter("searchTerm");

        try {

            Connection con = ds.getConnection();
            // SQL-Abfrage vorbereiten
            String sql = "SELECT modul.modulname, modul.studiengangname, karteikarte.userId "
					+ "FROM modul  NATURAL JOIN karteikarte "
					+ "WHERE modulname LIKE ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "%" + searchTerm + "%");

            // SQL-Abfrage ausführen
            ResultSet resultSet = statement.executeQuery();

            // Suchergebnisse verarbeiten
            List<ViewTeacher_ModuleBean> searchResults = new ArrayList<>();
            while (resultSet.next()) {
            	ViewTeacher_ModuleBean dataObject = new ViewTeacher_ModuleBean();
                dataObject.setModulname(resultSet.getString("modulname"));
                dataObject.setStudiengangname(resultSet.getString("studiengangname"));
                dataObject.setUserId(resultSet.getString("userId"));
                // Weitere Eigenschaften des DataObjects setzen
                searchResults.add(dataObject);
            }
            resultSet.close();
            statement.close();
            con.close();
  
            // Suchergebnisse an JSP-Datei übergeben
            
            request.setAttribute("searchResults", searchResults);
            request.getRequestDispatcher("jsp/View_StudentModulErgebnis.jsp").forward(request, response);
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
