package edu.thi.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import edu.thi.bean.RegisterBean;
import edu.thi.bean.ViewTeacher_ModuleBean;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Demo10Servlet
 */
@WebServlet("/ViewStudent_SearchServlet")
public class ViewStudent_SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	// Servlet zur Entgegennahme von Formularinhalten, Lesen der Daten in einer DB
	// und Generierung
	// eines Beans zur Weitergabe der Formulardaten an eine JSP
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8"); // In diesem Format erwartet das Servlet jetzt die Formulardaten
		String searchTerm = request.getParameter("searchTerm");
		String userid = request.getParameter("userId");
		RegisterBean user = new RegisterBean();
		user.setUserid(userid);
		// In diesem Codeabschnitt wird eine Liste von ViewTeacher_ModuleBean-Objekten mit
		// dem Namen "module" durchsucht, basierend auf einem Suchbegriff "searchTerm".
		// Dann wird eine leere Liste "filteredModule" erstellt. In einer Schleife wird jedes
		// Modul in der "module"-Liste überprüft. Für jedes Modul wird die Methode "modulzustudent" aufgerufen,
		// um zu überprüfen, ob das Modul bereits einem bestimmten Studenten, Dozenten und Studiengang zugewiesen wurde.
		// Wenn die Methode "modulzustudent" false zurückgibt, bedeutet dies, dass das Modul noch nicht zugewiesen wurde, und
		// es wird der "filteredModule"-Liste hinzugefügt.
		// Am Ende enthält die "filteredModule"-Liste nur diejenigen Module, die noch nicht einem
		// bestimmten Studenten, Dozenten und Studiengang zugewiesen wurden.
		List<ViewTeacher_ModuleBean> module = search(searchTerm);
		List<ViewTeacher_ModuleBean> filteredModule = new ArrayList<>();
		for (ViewTeacher_ModuleBean modul : module) {
			if (!modulzustudent(modul.getModulname(), userid, modul.getUserId(), modul.getStudiengangname())) {
				filteredModule.add(modul);
			}
		}
		// Scope "Request"
		session.setAttribute("module", filteredModule);
		session.setAttribute("userid", user);

		// Weiterleiten an JSP
		final RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/ViewStudent_SearchResults.jsp");
		dispatcher.forward(request, response);
	}

	// Diese Methode sucht mit dem eingegebenen Suchwort in den Tabellen modul und
	// karteikarte nach
	// den Modulen die dem Suchwort ähneln und gibt diese mit dem jeweiligen
	// Studiengang und Dozent zurück
	private List<ViewTeacher_ModuleBean> search(String lastname) throws ServletException {
		lastname = (lastname == null || lastname == "") ? "%" : "%" + lastname + "%";
		List<ViewTeacher_ModuleBean> module = new ArrayList<ViewTeacher_ModuleBean>();

		// DB-Zugriff
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con
						.prepareStatement("SELECT DISTINCT modul.modulname, modul.studiengangname, karteikarte.userId "
								+ "FROM modul NATURAL JOIN karteikarte " + "WHERE modul.modulname LIKE ?")) {

			pstmt.setString(1, lastname);
			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					ViewTeacher_ModuleBean modul = new ViewTeacher_ModuleBean();

					String modulname = rs.getString("modulname");
					modul.setModulname(modulname);

					String studiengangname = rs.getString("studiengangname");
					modul.setStudiengangname(studiengangname);

					String userId = rs.getString("userId");
					modul.setUserId(userId);

					module.add(modul);
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}

		return module;

	}

	// Diese Methode überprüft, ob ein bestimmtes Modul bereits einem bestimmten
	// Studenten, Dozenten und Studiengang zugewiesen wurde.
	// Sie stellt eine Verbindung zur Datenbank her und führt eine Abfrage durch, um
	// die Anzahl der entsprechenden Datensätze zu ermitteln.
	// Wenn die Anzahl größer als Null ist, wird true zurückgegeben, andernfalls
	// false.
	private boolean modulzustudent(String modulname, String id, String doz, String studie) throws ServletException {
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(
						"SELECT COUNT(*) AS count FROM modulprostudent WHERE modulname = ? AND student_id = ? AND dozent_id = ? AND studiengangname = ?")) {

			pstmt.setString(1, modulname);
			pstmt.setString(2, id);
			pstmt.setString(3, doz);
			pstmt.setString(4, studie);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					int count = rs.getInt("count");
					return count > 0;

				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}

		return false;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8"); // In diesem Format erwartet das Servlet jetzt die Formulardaten
		String userid = request.getParameter("userid");
		RegisterBean user = new RegisterBean();
		user.setUserid(userid);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		// Weiterleitung an JSP
		response.sendRedirect("jsp/ViewStudent_ModulErgebnis.jsp");

	}

}
