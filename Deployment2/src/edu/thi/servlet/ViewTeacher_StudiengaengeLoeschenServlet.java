//Erstellt von Muhammed Samil Turan
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
import edu.thi.bean.ViewTeacher_StudiengaengeBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ViewTeacher_StudiengaengeLoeschenServlet")
public class ViewTeacher_StudiengaengeLoeschenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * Methode zum Löschen beliebig vieler Studiengänge eines Users
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String userId = request.getParameter("userid");

		// Code bis zur Markierung generiert durch ChatGPT
		String[] selectedStudiengaenge = request.getParameterValues("selectedStudiengaenge");

		System.out.println(selectedStudiengaenge.length);
		System.out.println("userId: " + userId);
		// Datenbankverbindung aufbauen und Einträge löschen
		try (Connection con = ds.getConnection();) {
			String sql = "DELETE FROM studiengang WHERE studiengangname = ? AND userId = '" + userId + "'";
			PreparedStatement statement = con.prepareStatement(sql);

			// Für jede ausgewählte Id das Prepared Statement ausführen
			for (String studiengangname : selectedStudiengaenge) {
				statement.setString(1, studiengangname);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Markierung!

		List<ViewTeacher_StudiengaengeBean> studienfaecher = new ArrayList<>();

		try (Connection con = ds.getConnection();) {
			String query = "SELECT studiengangname FROM studiengang WHERE userId = '" + userId + "'";
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String studienfachId = resultSet.getString("studiengangname");
				ViewTeacher_StudiengaengeBean studiengang = new ViewTeacher_StudiengaengeBean();
				studiengang.setStudiengangname(studienfachId);
				studienfaecher.add(studiengang);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("studienfaecher", studienfaecher);

		HttpSession session = request.getSession();
		session.setAttribute("studienfaecher", studienfaecher);
		response.sendRedirect("jsp/ViewTeacher_Studiengaenge.jsp");
	}

}
