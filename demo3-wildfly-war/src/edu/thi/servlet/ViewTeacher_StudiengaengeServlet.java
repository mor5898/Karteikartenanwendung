package edu.thi.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import edu.thi.bean.ViewTeacher_StudiengaengeBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * erstellt durch Moritz Reindl
 */
@WebServlet("/ViewTeacher_StudiengaengeServlet")
public class ViewTeacher_StudiengaengeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Datenbankconnection erstellt durch Moritz Reindl
	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * doPost generiert durch ChatGPT
	 * Diese Methode nimmt die Parameter entgegen, fügt den eingegebene Studiengang hinzu und holt anschließend(!) alle Studiengänge des eingeloggten users
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String studiengangname = request.getParameter("studiengang");
		String userId = request.getParameter("userid");

		ViewTeacher_StudiengaengeBean studienfach = new ViewTeacher_StudiengaengeBean();
		studienfach.setStudiengangname(studiengangname);
		studienfach.setUserId(userId);

		insertStudiengang(studienfach);

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

	/**
	 * Methode zum Einfügen eines Studiengangs in die Datenbank
	 */
	private void insertStudiengang(ViewTeacher_StudiengaengeBean studienfach) {

		boolean duplicateFound = checkDuplicateStudiengang(studienfach);

		if (!duplicateFound) {
			
			try (Connection con = ds.getConnection();
					PreparedStatement statement = con
							.prepareStatement("INSERT INTO studiengang (studiengangname, userId) VALUES (?,?)")) // Zeile erstellt durch Moritz Reindl
			{

				// Die folgenden drei Zeilen wurden erstellt durch Moritz Reindl
				statement.setString(1, studienfach.getStudiengangname());
				statement.setString(2, studienfach.getUserId());
				statement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Diese Methode wird verwendet, um zu Überprüfen, ob bereits ein Studiengang
	// mit identischen Primärschlüssel existiert.
	// Sollte dies der Fall sein, wird der neue Studiengang nicht in die Datenbank
	// geschrieben und somit ein Absturz der Anwendung vermieden.
	private boolean checkDuplicateStudiengang(ViewTeacher_StudiengaengeBean studienfach) {

		boolean duplicateFound = false;

		try (Connection con = ds.getConnection();) {
			String query = "SELECT * FROM studiengang WHERE userId = '" + studienfach.getUserId()
					+ "' AND studiengangname = '" + studienfach.getStudiengangname() + "'";
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				duplicateFound = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return duplicateFound;
	}
}
