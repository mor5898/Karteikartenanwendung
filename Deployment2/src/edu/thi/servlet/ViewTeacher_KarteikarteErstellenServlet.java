package edu.thi.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import edu.thi.bean.ViewTeacher_KarteikarteErstellenBean;
import edu.thi.bean.ViewTeacher_ModuleBean;
import edu.thi.bean.ViewTeacher_StudiengaengeBean;
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
 * Erstellt durch Riza Dursun und Fatih Doruk
 */
@WebServlet("/ViewTeacher_KarteikarteErstellenServlet")
//Hier diese Annotation übergeben
@MultipartConfig

public class ViewTeacher_KarteikarteErstellenServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
    private DataSource ds;
	
	//Methode zum Speichern einer Karteikarte in der Datenbank, hier wird auch das Bild gespeichert
	private void persist(ViewTeacher_KarteikarteErstellenBean form, Part filepart) throws ServletException {
		
		String[] generatedKeys = new String[] {"id"};
		
		try(Connection con = ds.getConnection();																																														//Hier wurde das Feld bilddatei ergänzt und ein ?
				PreparedStatement pstmt = con.prepareStatement("INSERT INTO karteikarte (karteikartenId, modulname, studiengangname, userId,fragentext, antwortA, antwortB, antwortC, antwortD, korrekteAntwort, begruendungstext, titel, bilddatei) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)", generatedKeys)) {
            
			// Code ab hier bis zum Ende der Markierung generiert durch ChatGPT
            String query = "SELECT karteikartenId FROM karteikarte";
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            // Array zum Speichern der vorhandenen Primärschlüssel
            boolean[] taken = new boolean[10000]; // Annahme: Primärschlüssel reichen von 0 bis 9999
            
            // Markiere die vorhandenen Primärschlüssel als belegt
            while (rs.next()) {
                int id = rs.getInt("karteikartenId");
                taken[id] = true;
            }
            
            // Suche nach einem unbenutzten Primärschlüssel
            int availableId = -1;
            for (int i = 0; i < taken.length; i++) {
                if (!taken[i]) {
                    availableId = i;
                    break;
                }
            }
            // Ende der Markierung
            
			pstmt.setInt(1, availableId);
			pstmt.setString(2, form.getModulname());
			pstmt.setString(3, form.getStudiengangname());
			pstmt.setString(4, form.getUserId());
			pstmt.setString(5, form.getfragentext());
			pstmt.setString(6, form.getantwortA());
			pstmt.setString(7, form.getantwortB());
			pstmt.setString(8, form.getantwortC());
			pstmt.setString(9, form.getantwortD());
			pstmt.setString(10, form.getkorrekteAntwort());
			pstmt.setString(11, form.getbegruendung());
			pstmt.setString(12, form.gettitel());
			//Hier muss diese Methode für pstmt ergänzt werden
			pstmt.setBinaryStream(13, filepart.getInputStream());
			pstmt.executeUpdate();
			
			
			} catch (Exception ex) {
				throw new ServletException(ex.getMessage());
			}
	
		
	}

	/**
	 * Methode nimmt Parameter entgegen, ruft die Methode zum Speichern der Karteikarte auf und holt sich anschließend alle Karteikarten des aktuellen Moduls
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		ViewTeacher_KarteikarteErstellenBean form = new ViewTeacher_KarteikarteErstellenBean();
		form.setfragentext(request.getParameter("fragentext"));
		form.setantwortA(request.getParameter("antwortA"));
		form.setantwortB(request.getParameter("antwortB"));
		form.setantwortC(request.getParameter("antwortC"));
		form.setantwortD(request.getParameter("antwortD"));
		form.setkorrekteAntwort(request.getParameter("korrekteAntwort"));
		form.setbegruendung(request.getParameter("begruendungstext"));
		form.settitel(request.getParameter("titel"));
		form.setModulname(request.getParameter("modulname"));
		form.setStudiengangname(request.getParameter("studienfachId"));
		form.setUserId(request.getParameter("userid"));
		
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
		
		ViewTeacher_StudiengaengeBean studiengang = new ViewTeacher_StudiengaengeBean();
		studiengang.setStudiengangname(form.getStudiengangname());
		studiengang.setUserId(form.getUserId());
		
		ViewTeacher_ModuleBean modul = new ViewTeacher_ModuleBean();
		modul.setStudiengangname(form.getStudiengangname());
		modul.setUserId(form.getUserId());
		modul.setModulname(form.getModulname());
		
		List<ViewTeacher_KarteikarteErstellenBean> karteikarten = new ArrayList<>();

		try (Connection con = ds.getConnection();) {
			String query = "SELECT karteikartenId, titel, fragentext, modulname FROM karteikarte WHERE userId = '" + form.getUserId() + "' AND studiengangname = '" + form.getStudiengangname() + "'"
					+ " AND modulname = '" + form.getModulname() + "'";
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String modulnameForList = resultSet.getString("modulname");
				String fragentextForList = resultSet.getString("fragentext");
				String titelForList = resultSet.getString("titel");
				int karteikartenIdForList = resultSet.getInt("karteikartenId");
				ViewTeacher_KarteikarteErstellenBean karteikarteForList = new ViewTeacher_KarteikarteErstellenBean();
				karteikarteForList.setModulname(modulnameForList);
				karteikarteForList.setStudiengangname(form.getStudiengangname());
				karteikarteForList.setUserId(form.getUserId());
				karteikarteForList.setfragentext(fragentextForList);
				karteikarteForList.settitel(titelForList);
				karteikarteForList.setkarteikartenId(karteikartenIdForList);
				karteikarten.add(karteikarteForList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		HttpSession session = request.getSession();
		session.setAttribute("karteikarten", karteikarten);
		session.setAttribute("modul", modul);
		session.setAttribute("studienfachId", studiengang);
		session.setAttribute("userid", form.getUserId());
		
		response.sendRedirect("jsp/ViewTeacher_Karteikarten.jsp");
		
	}

}
