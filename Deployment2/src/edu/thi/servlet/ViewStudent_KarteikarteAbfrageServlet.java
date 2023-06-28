package edu.thi.servlet;

import edu.thi.bean.ViewStudent_KarteikarteAbfrageBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import javax.sql.DataSource;

import java.util.ArrayList;

/**
 * Servlet implementation class ViewStudent_KarteikarteAbfrageServlet
 */
@WebServlet("/ViewStudent_KarteikarteAbfrageServlet")
public class ViewStudent_KarteikarteAbfrageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	
	List<ViewStudent_KarteikarteAbfrageBean> suche (String studiengang, String modul, String dozent) throws ServletException
	{
		int count = 1;
		List<ViewStudent_KarteikarteAbfrageBean> karteikarten = new ArrayList<ViewStudent_KarteikarteAbfrageBean>();
		
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM karteikarte WHERE studiengangname LIKE ? "
						+ 																		"and modulname LIKE ?"
						+ 																		"and userId LIKE ?")) 
			{
				pstmt.setString(1, studiengang);
				pstmt.setString(2, modul);
				pstmt.setString(3, dozent);
				
				try (ResultSet rs = pstmt.executeQuery()) 
				{
					while (rs.next()) 
					{
						ViewStudent_KarteikarteAbfrageBean karteikarteBean = new ViewStudent_KarteikarteAbfrageBean();
						
						karteikarteBean.setKarteikartenID(rs.getInt("karteikartenId"));
						karteikarteBean.setModulname(rs.getString("modulname"));
						karteikarteBean.setStudiengangname(rs.getString("studiengangname"));
						karteikarteBean.setUser(rs.getString("userId"));
						karteikarteBean.setTitel(rs.getString("titel"));
						karteikarteBean.setFrage(rs.getString("fragentext"));
						karteikarteBean.setAntwortA(rs.getString("antwortA"));
						karteikarteBean.setAntwortB(rs.getString("antwortB"));
						karteikarteBean.setAntwortC(rs.getString("antwortC"));
						karteikarteBean.setAntwortD(rs.getString("antwortD"));
						karteikarteBean.setBegruendung(rs.getString("begruendungstext"));
						karteikarteBean.setKorrekteAntwort(rs.getString("korrekteAntwort").charAt(0));
						karteikarteBean.setcurrentFlag(false);
						karteikarteBean.setCount(count);
						karteikarteBean.setAnzahlRichtig(0);
						karteikarteBean.setAnzahlFalsch(0);
						
						karteikarteBean.setBildFlag(false);
						if(rs.getBlob("bilddatei") != null)
						{
							karteikarteBean.setBildFlag(true);
						}
						if(count == 1)
						{
							karteikarteBean.setcurrentFlag(true);
						}
						count++;
						karteikarten.add(karteikarteBean);
					}
				}
			} 
			catch (Exception ex) 
			{
				throw new ServletException(ex.getMessage());
			}
		
		return karteikarten;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String studiengang = request.getParameter("studie");
		String modul = request.getParameter("modul");
		String dozent = request.getParameter("doz");
		
		List<ViewStudent_KarteikarteAbfrageBean> karteikarten = suche(studiengang, modul, dozent);
		
		final HttpSession session = request.getSession();
		session.setAttribute("karteikarten", karteikarten);
		response.sendRedirect("/Deployment2/jsp/ViewStudent_KarteikarteAbfrage.jsp");
	}

}
