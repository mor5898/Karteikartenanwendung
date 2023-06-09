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
 * Servlet implementation class ViewTeacher_ModuleLoeschenServlet
 */
@WebServlet("/ViewTeacher_ModuleLoeschenServlet")
public class ViewTeacher_ModuleLoeschenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
    /**
     * Default constructor. 
     */
    public ViewTeacher_ModuleLoeschenServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		String[] selectedModulname = request.getParameterValues("selectedModule");
		
        // Datenbankverbindung aufbauen und Einträge löschen
        try (Connection con = ds.getConnection();) {
            String sql = "DELETE FROM modul WHERE modulname = ? AND userId = '" + userId + 
            		"' AND studiengangname = '" + studiengangname + "'";
            PreparedStatement statement = con.prepareStatement(sql);
            
            // Für jede ausgewählte Id das Prepared Statement ausführen
            for (String mod : selectedModulname) {
                statement.setString(1, mod);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Hier können Sie geeignete Fehlerbehandlung durchführen
        }
        // Markierung!
        
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

}
