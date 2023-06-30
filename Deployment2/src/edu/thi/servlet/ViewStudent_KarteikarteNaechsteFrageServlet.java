//Erstellt von Fatih Doruk
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
import java.util.ArrayList;
import javax.sql.DataSource;


/**
 * Servlet implementation class ViewStudent_KarteikarteNaechsteFrageServlet
 */
@WebServlet("/ViewStudent_KarteikarteNaechsteFrageServlet")
public class ViewStudent_KarteikarteNaechsteFrageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	//Diese Methode bekommt als Eingabe den Studiengang, das Modul und den dazugehörigen Dozenten und sucht sich alle Karteikarten 
	//dieses Moduls aus.
	List<ViewStudent_KarteikarteAbfrageBean> suche (String studiengang, String modul, String dozent, int aktCount, int anzahlRichtig, int anzahlFalsch) throws ServletException
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
						karteikarteBean.setAnzahlRichtig(anzahlRichtig);
						karteikarteBean.setAnzahlFalsch(anzahlFalsch);
						karteikarteBean.setBildFlag(true);
						
						if(rs.getBlob("bilddatei") == null || rs.getBlob("bilddatei").length() == 0)
						{
							karteikarteBean.setBildFlag(false);
						}
						
						if(aktCount+1 == count)
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int aktCount = Integer.valueOf(request.getParameter("count"));
		String studiengang = request.getParameter("studiengang");
		String modul = request.getParameter("modul");
		String dozent = request.getParameter("dozent");
		int anzahlRichtig = Integer.valueOf(request.getParameter("anzahlRichtig"));
		int anzahlFalsch = Integer.valueOf(request.getParameter("anzahlFalsch"));
		List<ViewStudent_KarteikarteAbfrageBean> karteikarten = suche(studiengang, modul, dozent, aktCount, anzahlRichtig, anzahlFalsch);
		
		
		final HttpSession session = request.getSession();
		session.setAttribute("karteikarten", karteikarten);
		response.sendRedirect("/Deployment2/jsp/ViewStudent_KarteikarteAbfrage.jsp");
	}

}
