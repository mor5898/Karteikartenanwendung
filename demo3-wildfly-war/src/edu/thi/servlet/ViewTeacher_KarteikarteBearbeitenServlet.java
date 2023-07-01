package edu.thi.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import edu.thi.bean.ViewTeacher_KarteikarteErstellenBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Erstellt durch Moritz Reindl
 */
@WebServlet("/ViewTeacher_KarteikarteBearbeitenServlet")
public class ViewTeacher_KarteikarteBearbeitenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;

	// Methode lädt die Daten der Karteikarte und übergibt sie an die Jsp, somit sind die Felder in der jsp vorbelegt 
	// Karteikarte wird bearbeitet, nicht neu erstellt!
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//String studiengangname = request.getParameter("studienfachId");
		String userId = request.getParameter("userid");
		String karteikartenId = request.getParameter("karteikartenId");
		String searchFlag = request.getParameter("searchFlag");

		ViewTeacher_KarteikarteErstellenBean karteikarteForList = new ViewTeacher_KarteikarteErstellenBean();	

		try (Connection con = ds.getConnection();) {

			String query = "SELECT * FROM karteikarte WHERE karteikartenId = " + karteikartenId;
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
		
			if (resultSet.next()) {
				
				String modulnameForList = resultSet.getString("modulname");
				String fragentextForList = resultSet.getString("fragentext");
				String titelForList = resultSet.getString("titel");
				int karteikartenIdForList = resultSet.getInt("karteikartenId");
				String antwortAForList = resultSet.getString("antwortA");
				String antwortBForList = resultSet.getString("antwortB");
				String antwortCForList = resultSet.getString("antwortC");
				String antwortDForList = resultSet.getString("antwortD");
				String korrekteAntwortForList = resultSet.getString("korrekteAntwort");
				String begrundungstextForList = resultSet.getString("begruendungstext");
				String studiengangname = resultSet.getString("studiengangname");
				
				karteikarteForList.setModulname(modulnameForList);
				karteikarteForList.setStudiengangname(studiengangname);
				karteikarteForList.setUserId(userId);
				karteikarteForList.setfragentext(fragentextForList);
				karteikarteForList.settitel(titelForList);
				karteikarteForList.setkarteikartenId(karteikartenIdForList);
				karteikarteForList.setantwortA(antwortAForList);
				karteikarteForList.setantwortB(antwortBForList);
				karteikarteForList.setantwortC(antwortCForList);
				karteikarteForList.setantwortD(antwortDForList);
				karteikarteForList.setkorrekteAntwort(korrekteAntwortForList);
				karteikarteForList.setbegruendung(begrundungstextForList);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("karteikarte", karteikarteForList);
		session.setAttribute("searchFlag", searchFlag);
		
		if (searchFlag != null && searchFlag.length() > 0) {
			response.sendRedirect("jsp/ViewTeacher_KarteikarteBearbeitenSearch.jsp");
		} 
		else {
		response.sendRedirect("jsp/ViewTeacher_KarteikarteBearbeiten.jsp");
		}
	}

}
