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
import edu.thi.bean.ViewTeacher_ModuleBean;
import edu.thi.bean.ViewTeacher_StudiengaengeBean;
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
@WebServlet("/ViewTeacher_KarteikartenLoeschenServlet")
public class ViewTeacher_KarteikartenLoeschenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	
	// Methode zum Löschen beliebig vieler Karteikarten eines Moduls
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String modulname = request.getParameter("modulname");
		String studiengangname = request.getParameter("studienfachId");
		String userId = request.getParameter("userid");
		
		ViewTeacher_StudiengaengeBean studiengang = new ViewTeacher_StudiengaengeBean();
		studiengang.setStudiengangname(studiengangname);
		studiengang.setUserId(userId);
		
		ViewTeacher_ModuleBean modul = new ViewTeacher_ModuleBean();
		modul.setStudiengangname(studiengangname);
		modul.setUserId(userId);
		modul.setModulname(modulname);
		
		// Code bis zur Markierung generiert durch ChatGPT
		String[] selectedKarteikartenIds = request.getParameterValues("selectedKarteikarten");
		
        // Datenbankverbindung aufbauen und Einträge löschen
        try (Connection con = ds.getConnection();) {
            String sql = "DELETE FROM karteikarte WHERE karteikartenId = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            
            // Für jede ausgewählte Id das Prepared Statement ausführen
            for (String karteikartenId : selectedKarteikartenIds) {
                statement.setString(1, karteikartenId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Hier können Sie geeignete Fehlerbehandlung durchführen
        }
        // Markierung!
        
		List<ViewTeacher_KarteikarteErstellenBean> karteikarten = new ArrayList<>();

		try (Connection con = ds.getConnection();) {
			String query = "SELECT karteikartenId, titel, fragentext, modulname FROM karteikarte WHERE userId = '" + userId + "' AND studiengangname = '" + studiengangname + "'"
					+ " AND modulname = '" + modulname + "'";
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String modulnameForList = resultSet.getString("modulname");
				String fragentextForList = resultSet.getString("fragentext");
				String titelForList = resultSet.getString("titel");
				int karteikartenIdForList = resultSet.getInt("karteikartenId");
				ViewTeacher_KarteikarteErstellenBean karteikarteForList = new ViewTeacher_KarteikarteErstellenBean();
				karteikarteForList.setModulname(modulnameForList);
				karteikarteForList.setStudiengangname(studiengangname);
				karteikarteForList.setUserId(userId);
				karteikarteForList.setfragentext(fragentextForList);
				karteikarteForList.settitel(titelForList);
				karteikarteForList.setkarteikartenId(karteikartenIdForList);
				karteikarten.add(karteikarteForList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("karteikarten", karteikarten);
		session.setAttribute("modul", modul);
		session.setAttribute("studienfachId", studiengang);
		session.setAttribute("userid", userId);
		response.sendRedirect("jsp/ViewTeacher_Karteikarten.jsp");
	}

}
