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
@WebServlet("/View_StudentSearchServlet")
public class View_StudentSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Servlet zur Entgegennahme von Formularinhalten, Lesen der Daten in einer DB und Generierung
		// eines Beans zur Weitergabe der Formulardaten an eine JSP
		
		request.setCharacterEncoding("UTF-8");	// In diesem Format erwartet das Servlet jetzt die Formulardaten
		String searchTerm = request.getParameter("searchTerm");
		
		String userid = request.getParameter("userid");
		RegisterBean user = new RegisterBean();
		user.setUserid(userid);
		// DB-Zugriff
		List<ViewTeacher_ModuleBean> module = search(searchTerm);
				
		// Scope "Request"
		request.setAttribute("module", module);
		request.setAttribute("user", user);
		System.out.println("Sind die Module leer? : " + module.isEmpty());
		//System.out.println(module.get(0).getModulname());
		// Weiterleiten an JSP
		final RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/View_StudentSearchResults.jsp");
		dispatcher.forward(request, response);	
	}

	private List<ViewTeacher_ModuleBean> search(String lastname) throws ServletException {
		lastname = (lastname == null || lastname == "") ? "%" : "%" + lastname + "%";
		List<ViewTeacher_ModuleBean> module = new ArrayList<ViewTeacher_ModuleBean>();
		
		// DB-Zugriff
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT modul.modulname, modul.studiengangname, karteikarte.userId " 
						+ "FROM modul "
						+ "INNER JOIN karteikarte ON modul.modulname = karteikarte.modulname "
						+ "WHERE modul.modulname LIKE ?")) {

			pstmt.setString(1, lastname);
			try (ResultSet rs = pstmt.executeQuery()) {
			
				while (rs.next()) {
					ViewTeacher_ModuleBean modul = new ViewTeacher_ModuleBean();
					
					/*Long id = Long.valueOf(rs.getLong("id"));
					employee.setId(id);
					
					String first = rs.getString("first");
					employee.setFirstname(first);
					
					String last = rs.getString("last");
					employee.setLastname(last);
					
					Integer age = Integer.valueOf(rs.getInt("age"));
					employee.setAge(age);*/
					String modulname = rs.getString("modulname");
					modul.setModulname(modulname);
					
					String studiengangname = rs.getString("studiengangname");
					modul.setStudiengangname(studiengangname);
					
					String userId = rs.getString("userId");
					modul.setUserId(userId);
					
					module.add(modul);
				} // while rs.next()
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		return module;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");	// In diesem Format erwartet das Servlet jetzt die Formulardaten
		String userid = request.getParameter("userid");
		
		RegisterBean user = new RegisterBean();
		user.setUserid(userid);
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		response.sendRedirect("jsp/View_StudentModulErgebnis.jsp");
		
	}

}
