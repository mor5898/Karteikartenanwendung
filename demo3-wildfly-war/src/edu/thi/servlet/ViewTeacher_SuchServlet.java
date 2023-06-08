package edu.thi.servlet;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import java.util.ArrayList;

import edu.thi.bean.ViewTeacher_KarteikarteAnzeigeBean;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SuchServlet
 */
@WebServlet("/SuchServlet")
public class ViewTeacher_SuchServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	
	List<ViewTeacher_KarteikarteAnzeigeBean> suchen(String schlagwort, String userid) throws ServletException
	{
		schlagwort = (schlagwort == null || schlagwort == "") ? "%" : "%" + schlagwort + "%";
		
		List<ViewTeacher_KarteikarteAnzeigeBean> karteikarten = new ArrayList<ViewTeacher_KarteikarteAnzeigeBean>();
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM karteikarte WHERE titel LIKE ? AND userId = '" + userid + "'"))
		{
			pstmt.setString(1,  schlagwort);
			try(ResultSet rs = pstmt.executeQuery())
			{
				while(rs.next())
				{
					ViewTeacher_KarteikarteAnzeigeBean karteikarte = new ViewTeacher_KarteikarteAnzeigeBean();
					
					String studienfach = rs.getString("studiengangname");
					karteikarte.setStudiengang(studienfach);
					
					String modul = rs.getString("modulname");
					karteikarte.setModul(modul);
					
					int karteikarteID = rs.getInt("karteikartenId");
					karteikarte.setKarteikarteID(karteikarteID);
					
					String titel = rs.getString("titel");
					karteikarte.setTitel(titel);
					
					karteikarten.add(karteikarte);
				}
			}
		}
		catch (Exception ex)
		{
			throw new ServletException(ex.getMessage());
		}
		
		return karteikarten;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*request.setCharacterEncoding("UTF-8");
		
		String schlagwort = request.getParameter("schlagwort");
		
		List<ViewTeacher_KarteikarteAnzeigeBean> karteikarten = suchen(schlagwort);
		
		request.setAttribute("karteikarten", karteikarten);
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/ViewTeacher_suchergebnis.jsp");
		dispatcher.forward(request, response);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		
		String schlagwort = request.getParameter("schlagwort");
		String userid = request.getParameter("userid");
		
		List<ViewTeacher_KarteikarteAnzeigeBean> karteikarten = suchen(schlagwort, userid);
		
		request.setAttribute("karteikarten", karteikarten);
		
		final RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/ViewTeacher_suchergebnis.jsp");
		dispatcher.forward(request, response);
	}

}
