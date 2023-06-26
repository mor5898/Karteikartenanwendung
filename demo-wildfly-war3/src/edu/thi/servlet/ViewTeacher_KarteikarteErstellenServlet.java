package edu.thi.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import edu.thi.bean.ViewTeacher_KarteikarteErstellenBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

/** 
 * Servlet implementation class ViewTeacher_KarteikarteErstellenServlet
 */
@WebServlet("/ViewTeacher_KarteikarteErstellenServlet")
//Hier diese Annotation übergeben
@MultipartConfig
public class ViewTeacher_KarteikarteErstellenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
    private DataSource ds;
	
	//Hier muss für die Methode Part filepart ergänzt werden
	private void persist(ViewTeacher_KarteikarteErstellenBean form, Part filepart) throws ServletException {
		String[] generatedKeys = new String[] {"id"};
		
		try(Connection con = ds.getConnection();																																														//Hier wurde das Feld bilddatei ergänzt und ein ?
				PreparedStatement pstmt = con.prepareStatement("INSERT INTO karteikarte (modulname, studiengangname, userId,fragentext, antwortA, antwortB, antwortC, antwortD, korrekteAntwort, begruendungstext, titel, bilddatei) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)", generatedKeys)) {
			pstmt.setString(1, "Datenbanksysteme");
			pstmt.setString(2, "Informatik");
			pstmt.setString(3, "BigD");
			pstmt.setString(4, form.getfragentext());
			pstmt.setString(5, form.getantwortA());
			pstmt.setString(6, form.getantwortB());
			pstmt.setString(7, form.getantwortC());
			pstmt.setString(8, form.getantwortD());
			pstmt.setString(9, form.getkorrekteAntwort());
			pstmt.setString(10, form.getbegruendung());
			pstmt.setString(11, form.gettitel());
			//Hier muss diese Methode für pstmt ergänzt werden
			pstmt.setBinaryStream(12, filepart.getInputStream());
			pstmt.executeUpdate();
			
			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				while(rs.next()) {
					form.setkarteikartenId(rs.getInt(1));
				}
			}
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage());
			}
	
		
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ViewTeacher_KarteikarteErstellenBean form = new ViewTeacher_KarteikarteErstellenBean();
		form.setfragentext(request.getParameter("fragentext"));
		form.setantwortA(request.getParameter("antwortA"));
		form.setantwortB(request.getParameter("antwortB"));
		form.setantwortC(request.getParameter("antwortC"));
		form.setantwortD(request.getParameter("antwortD"));
		form.setkorrekteAntwort(request.getParameter("korrekteAntwort"));
		form.setbegruendung(request.getParameter("begruendungstext"));
		form.settitel(request.getParameter("titel"));
//		form.setmodulname(request.getParameter("modulname"));
//		form.setstudiengangname(request.getParameter("studiengangname"));
		
		Part filepart = request.getPart("image");
		
	
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				 InputStream in = filepart.getInputStream()	)
		{
				int i=0;
				while ((i = in.read()) != -1) {
					baos.write(i);
				}
				form.setBild(baos.toByteArray());
				baos.flush();
		} 
		catch (IOException ex) 
		{
				throw new ServletException(ex.getMessage());
		}
						
		persist(form, filepart);
		
		final HttpSession session = request.getSession();
		session.setAttribute("form", form);
		
		response.sendRedirect("/demo-wildfly-war3/ViewTeacher/jsp/KarteikarteErstellen.jsp");
		
	}

}
