package edu.thi.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import edu.thi.bean.ViewTeacher_ModuleBean;
import jakarta.annotation.Resource;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewStudent_DecksLoeschen
 */
@WebServlet("/ViewStudent_DecksLoeschen")
public class ViewStudent_DecksLoeschenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;

	/**
	 * Default constructor.
	 */
	public ViewStudent_DecksLoeschenServlet() {
		// TODO Auto-generated constructor stub
	}

	// Diese Methode löscht in der Tabelle modulprostudent die ausgewählten Module
	// und die jeweiligen Studiengangname, DozentId und die StudentId
	private void delete(String id, String doz, String studie, String modul) throws ServletException {

		try (Connection con = ds.getConnection(); // Hier wurde das Feld bilddatei ergänzt und ein ?
				PreparedStatement pstmt = con.prepareStatement(
						"DELETE FROM modulprostudent WHERE student_id = ? AND dozent_id=? AND studiengangname=? AND modulname = ?")) {

			pstmt.setString(1, id);
			pstmt.setString(2, doz);
			pstmt.setString(3, studie);
			pstmt.setString(4, modul);

			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}

	// Methode zum aktualisieren der von diesem Servlet aufgerufene Seite.
	// Sie aktualisiert die hinzugefügten Decks des Studenten auf der Home Seite.
	List<ViewTeacher_ModuleBean> suchen(String id) throws ServletException {

		List<ViewTeacher_ModuleBean> modul = new ArrayList<ViewTeacher_ModuleBean>();

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con
						.prepareStatement("SELECT * FROM modulprostudent WHERE student_id LIKE ?")) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					ViewTeacher_ModuleBean module = new ViewTeacher_ModuleBean();

					String userId = rs.getString("dozent_id");
					module.setUserId(userId);

					String studiengang = rs.getString("studiengangname");
					module.setStudiengangname(studiengang);
					;

					String modulname = rs.getString("modulname");
					module.setModulname(modulname);

					modul.add(module);
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}

		return modul;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		String studiengang = request.getParameter("studie");
		String modul = request.getParameter("modul");
		String dozent = request.getParameter("doz");
		String id = request.getParameter("id");

		// Methode wird mit den ausgewählten Parametern aufgerufen und anschließend in
		// der Tabelle gelöscht.
		delete(id, dozent, studiengang, modul);
		List<ViewTeacher_ModuleBean> module = suchen(id);
		session.setAttribute("modules", module);

		response.sendRedirect("jsp/ViewStudent_Home.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

}
