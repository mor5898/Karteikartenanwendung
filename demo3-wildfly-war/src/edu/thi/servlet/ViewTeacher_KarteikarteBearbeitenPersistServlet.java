package edu.thi.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

/**
 * Erstellt durch Riza Dursun
 */
@WebServlet("/ViewTeacher_KarteikarteBearbeitenPersistServlet")
@MultipartConfig
public class ViewTeacher_KarteikarteBearbeitenPersistServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;

	// Methode speichert die geänderten Daten der Karteikarte persistent in der Datenbank 
	// anschließend werden alle Karteikarten des aktuellen Moduls aus der Datenbank geladen und übergeben
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// Parameter aus dem Request abrufen
		String karteikartenId = request.getParameter("karteikartenId");
		String modulname = request.getParameter("modulname");
		String studiengangname = request.getParameter("studienfachId");
		String userId = request.getParameter("userid");
		String fragentext = request.getParameter("fragentext");
		String antwortA = request.getParameter("antwortA");
		String antwortB = request.getParameter("antwortB");
		String antwortC = request.getParameter("antwortC");
		String antwortD = request.getParameter("antwortD");
		String korrekteAntwort = request.getParameter("korrekteAntwort");
		String begruendungstext = request.getParameter("begruendungstext");
		String titel = request.getParameter("titel");
		String searchFlag = request.getParameter("searchFlag");
		System.out.println(searchFlag);

		ViewTeacher_StudiengaengeBean studiengang = new ViewTeacher_StudiengaengeBean();
		studiengang.setStudiengangname(studiengangname);
		studiengang.setUserId(userId);

		ViewTeacher_ModuleBean modul = new ViewTeacher_ModuleBean();
		modul.setStudiengangname(studiengangname);
		modul.setUserId(userId);
		modul.setModulname(modulname);

		// ab hier bis zur Markierung generiert durch ChatGPT
		String sql = "UPDATE Karteikarte SET modulname=?, studiengangname=?, userId=?, fragentext=?, antwortA=?, antwortB=?, antwortC=?, antwortD=?, korrekteAntwort=?, begruendungstext=?, titel=?, bilddatei=? WHERE karteikartenId=?";

		// JDBC-Verbindung aufbauen
		try (Connection con = ds.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

			// Parameter in die SQL-Abfrage setzen
			stmt.setString(1, modulname);
			stmt.setString(2, studiengangname);
			stmt.setString(3, userId);
			stmt.setString(4, fragentext);
			stmt.setString(5, antwortA);
			stmt.setString(6, antwortB);
			stmt.setString(7, antwortC);
			stmt.setString(8, antwortD);
			stmt.setString(9, korrekteAntwort);
			stmt.setString(10, begruendungstext);
			stmt.setString(11, titel);
			
			Part filepart = request.getPart("image");
			stmt.setBinaryStream(12, filepart.getInputStream());
		
			stmt.setString(13, karteikartenId);

			int affectedRows = stmt.executeUpdate();

			if (affectedRows > 0) {
				response.setStatus(HttpServletResponse.SC_OK);
				response.getWriter().println("Die Tabelle 'karteikarte' wurde erfolgreich aktualisiert.");
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().println("Fehler beim Aktualisieren der Tabelle 'karteikarte'.");
			}

		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println("Ein Datenbankfehler ist aufgetreten: " + e.getMessage());
		}
		// Markierung!!
		
		List<ViewTeacher_KarteikarteErstellenBean> karteikarten = new ArrayList<>();

		try (Connection con = ds.getConnection();) {
			String query = "SELECT karteikartenId, titel, fragentext, modulname FROM karteikarte WHERE userId = '"
					+ userId + "' AND studiengangname = '" + studiengangname + "'" + " AND modulname = '" + modulname
					+ "'";
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
		
		if (searchFlag != null && searchFlag.length() > 0) {
			response.sendRedirect("jsp/ViewTeacher_Suchen.jsp");
		} 
		else {
		response.sendRedirect("jsp/ViewTeacher_Karteikarten.jsp");
		}

	}

}
