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
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ViewStudent_DeckHinzufuegen")
public class ViewStudent_DeckHinzufuegen extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
    private DataSource ds;
	
	List<ViewTeacher_ModuleBean> suchen(String id) throws ServletException
	{
		
		List<ViewTeacher_ModuleBean> modul = new ArrayList<ViewTeacher_ModuleBean>();
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM modulprostudent WHERE student_id LIKE ?"))
		{
			pstmt.setString(1, id);
			try(ResultSet rs = pstmt.executeQuery())
			{
				
				while(rs.next())
				{
					ViewTeacher_ModuleBean module = new ViewTeacher_ModuleBean();
					
					String userId = rs.getString("dozent_id");
					module.setUserId(userId);

					String studiengang = rs.getString("studiengangname");
					module.setStudiengangname(studiengang);;
					
					String modulname = rs.getString("modulname");
					module.setModulname(modulname);
										
					modul.add(module);
				}
			}
		}
		catch (Exception ex)
		{
			throw new ServletException(ex.getMessage());
		}
		
		return modul;
	}
	
	private void persist(String id, String doz, String studie, String modul) throws ServletException {
		//String[] generatedKeys = new String[] {"id"};
		try(Connection con = ds.getConnection();																																														
				PreparedStatement pstmt = con.prepareStatement("INSERT INTO modulprostudent (student_id, dozent_id, studiengangname, modulname) VALUES (?,?,?,?)")) {
            
			pstmt.setString(1, id);
			pstmt.setString(2, doz);
			pstmt.setString(3, studie);
			pstmt.setString(4, modul);
			pstmt.executeUpdate();
	
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage());
			}
	
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		//String userId = request.getParameter("id");
		List<ViewTeacher_ModuleBean> module = suchen(id);
		
		//request.setAttribute("id", userId);
		session.setAttribute("modules", module);
		final RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/ViewStudent_Home.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		String studentid = request.getParameter("id");
		String dozentid = request.getParameter("doz");
		String studiengang = request.getParameter("studie");
		String modul = request.getParameter("modul");
		System.out.println(studentid + dozentid + studiengang + modul);
		
		persist(studentid, dozentid, studiengang, modul);
		
		System.out.println(request.getParameterValues("studie"));
		System.out.println(request.getParameterValues("doz"));
		System.out.println(request.getParameterValues("modul"));
		
		String[] studiengangs = request.getParameterValues("studie");
		String[] dozentIds = request.getParameterValues("doz");
		String[] modulNames = request.getParameterValues("modul");

		   
		   // Iteriere über die Arrays und verarbeite die Werte entsprechend
		   for (int i = 0; i < studiengangs.length; i++) {
		      String studiengang = studiengangs[i];
		      String dozentId = dozentIds[i];
		      String modulName = modulNames[i];
		      
		      // Verarbeite die Werte hier
		      persist(studentid, dozentId, studiengang, modulName);
		   }
		   
		List<ViewTeacher_ModuleBean> module = suchen(studentid);
		
		//request.setAttribute("id", userId);
		//response.sendRedirect("jsp/ViewStudent_Home.jsp");
		//doGet(request, response);
		HttpSession session = request.getSession();
		session.setAttribute("modules", module);
		session.setAttribute("id", studentid);

		response.sendRedirect("jsp/ViewStudent_Home.jsp");*/
	    // doPost-Methode des Servlets
	    
	    // Auslesen der übermittelten Formulardaten
	    String studiengang = request.getParameter("studie");
	    String modul = request.getParameter("modul");
	    String dozent = request.getParameter("doz");
	    String id = request.getParameter("id");
	    
	    persist(id, dozent, studiengang, modul);
	    // Erzeugen eines Beans zur Weitergabe der Formulardaten an eine JSP
	  //  ViewTeacher_ModuleBean selectedModule = new ViewTeacher_ModuleBean();
	    //selectedModule.setStudiengangname(studiengang);
	    //selectedModule.setModulname(modul);
	    //selectedModule.setUserId(dozent);
	    List<ViewTeacher_ModuleBean> module = suchen(id);
	    // Scope "Session" für das ausgewählte Modul
	    HttpSession session = request.getSession();
	    session.setAttribute("modules", module);
	    response.sendRedirect("jsp/ViewStudent_Home.jsp");
	    // Weiterleiten an die JSP mit der Tabelle für das Suchergebnis
	    //final RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/ViewStudent_Home.jsp");
	    //dispatcher.forward(request, response);
	}

}
