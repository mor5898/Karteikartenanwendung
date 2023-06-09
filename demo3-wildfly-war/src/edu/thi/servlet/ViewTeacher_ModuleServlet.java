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

/**
 * Erstellt durch Moritz Reindl
 */
@WebServlet("/ViewTeacher_ModuleServlet")
public class ViewTeacher_ModuleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	
	//Methode holt sich alle Module eines Users
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String studiengangname = request.getParameter("studienfachId");
		String userId = request.getParameter("userid");
	
		ViewTeacher_StudiengaengeBean studiengang = new ViewTeacher_StudiengaengeBean();
		studiengang.setStudiengangname(studiengangname);
		studiengang.setUserId(userId);
	
		HttpSession session = request.getSession();
		
		List<ViewTeacher_ModuleBean> module = new ArrayList<>();

		try (Connection con = ds.getConnection();) {
			String query = "SELECT modulname FROM modul WHERE userId = '" + userId + "' AND studiengangname = '"
					+ studiengangname + "'";
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String modulnameForList = resultSet.getString("modulname");
				ViewTeacher_ModuleBean modulForList = new ViewTeacher_ModuleBean();
				modulForList.setModulname(modulnameForList);
				modulForList.setStudiengangname(studiengangname);
				modulForList.setUserId(userId);
				module.add(modulForList);
			}
			session.setAttribute("module", module);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		session.setAttribute("studienfachId", studiengang);
		session.setAttribute("userid", userId);
		System.out.println("Studiengang: " + studiengangname + " userid: " + userId);
		response.sendRedirect("jsp/ViewTeacher_Module.jsp");
	}

	/**
	 * Methode zum Anlegen eines neuen Moduls
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String studiengangname = request.getParameter("studienfachId");
		String userId = request.getParameter("userid");
		String modulname = request.getParameter("modul");

		if (studiengangname.isEmpty() || userId.isEmpty()) {
			System.out.println("Fehler bei Übergabe der Attribute!");
		}

		ViewTeacher_ModuleBean modul = new ViewTeacher_ModuleBean();
		modul.setModulname(modulname);
		modul.setStudiengangname(studiengangname);
		modul.setUserId(userId);

		ViewTeacher_StudiengaengeBean studiengang = new ViewTeacher_StudiengaengeBean();
		studiengang.setStudiengangname(studiengangname);
		studiengang.setUserId(userId);

		insertModul(modul);

		List<ViewTeacher_ModuleBean> module = new ArrayList<>();

		try (Connection con = ds.getConnection();) {
			String query = "SELECT modulname FROM modul WHERE userId = '" + userId + "' AND studiengangname = '"
					+ studiengangname + "'";
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String modulnameForList = resultSet.getString("modulname");
				ViewTeacher_ModuleBean modulForList = new ViewTeacher_ModuleBean();
				modulForList.setModulname(modulnameForList);
				modulForList.setStudiengangname(studiengangname);
				modulForList.setUserId(userId);
				module.add(modulForList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("module", module);
		request.setAttribute("studienfachId", studiengang);
		request.setAttribute("userid", userId);

		HttpSession session = request.getSession();
		session.setAttribute("module", module);
		session.setAttribute("studienfachId", studiengang);
		session.setAttribute("userid", userId);
		response.sendRedirect("jsp/ViewTeacher_Module.jsp");

	}
	
	// Methode zum Einfügen eines neuen Moduls in die Datenbank
	private void insertModul(ViewTeacher_ModuleBean modul) {

		boolean duplicateFound = checkDuplicateModul(modul);

		if (!duplicateFound) {
			try (Connection con = ds.getConnection();) {
				String query = "INSERT INTO modul (modulname, studiengangname, userId) VALUES (?, ?, ?)";
				PreparedStatement statement = con.prepareStatement(query);
				statement.setString(1, modul.getModulname());
				statement.setString(2, modul.getStudiengangname());
				statement.setString(3, modul.getUserId());
				statement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Methode zur Überprüfung, ob ein Modul bereits existiert
	private boolean checkDuplicateModul(ViewTeacher_ModuleBean modul) {

		boolean duplicateFound = false;

		try (Connection con = ds.getConnection();) {
			String query = "SELECT * FROM studiengang WHERE userId = '" + modul.getUserId()
					+ "' AND studiengangname = '" + modul.getStudiengangname() + "' AND modulname = '" + modul.getModulname()
					+ "'";
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
