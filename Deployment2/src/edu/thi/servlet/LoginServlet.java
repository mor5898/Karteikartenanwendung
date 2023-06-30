//Erstellt von Samil Muhammed Turan
package edu.thi.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import edu.thi.bean.RegisterBean;
import edu.thi.bean.ViewTeacher_StudiengaengeBean;
import edu.thi.bean.ViewTeacher_ModuleBean;
import jakarta.annotation.Resource;
//import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "LoginServlet", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	//Inhalt dieser Methode wurde erstellt von Riza Dursun
	// Methode zum aktualisieren der von diesem Servlet aufgerufene Seite.
	// Sie aktualisiert die hinzugefügten Decks des Studenten auf der Home Seite.
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
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		//Der folgende Teil wurde erstellt von Moritz Reindl
        List<ViewTeacher_StudiengaengeBean> studienfaecher = new ArrayList<>();

        try (Connection con = ds.getConnection();) {
            String query = "SELECT studiengangname FROM studiengang WHERE userId = '" + userid + "'" ;
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String studiengangname = resultSet.getString("studiengangname");
                ViewTeacher_StudiengaengeBean studienfach = new ViewTeacher_StudiengaengeBean();
                studienfach.setStudiengangname(studiengangname);
                System.out.println(studienfach.getStudiengangname());
                studienfaecher.add(studienfach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }//Ende von Moritz Reindls Teil
		RegisterBean login = search(userid);
		
		
		HttpSession session = request.getSession();
		//Bean für Moritz Reindls Teil
		session.setAttribute("studienfaecher", studienfaecher);
		session.setAttribute("LoginForm", login);
		//Überprüfung auf nicht esxistierenden User
		if(login.getUserid()==null) {
			String usernotexistent = "Der angegebene User existiert nicht";
	        request.setAttribute("text", usernotexistent);
	        request.getRequestDispatcher("jsp/Login.jsp").forward(request, response);
	      //Überprüfung der Passwortübereinstimmung
		}else if(password.equals(login.getPassword())) {
			if(login.getRole().equalsIgnoreCase("Dozent")) {//Überprüfung der Rolle und Weiterleitung zur jeweiligen Seite
				response.sendRedirect("jsp/ViewTeacher_Studiengaenge.jsp");//Weiterleitung zur Dozentenstartseite
	         }else if(login.getRole().equalsIgnoreCase("Student")) {
	        	 List<ViewTeacher_ModuleBean> module = suchen(userid);
	        	 session.setAttribute("modules", module);
	        	 response.sendRedirect("jsp/ViewStudent_Home.jsp");//Weiterleitung zur Studentenstartseite 
	         } 
		}else if(!password.equals(login.getPassword())){//Vorgehen bei falschem Passwort
			String wrongpassword = "Das angegebene Passwort ist falsch";
			request.setAttribute("text", wrongpassword);
			request.getRequestDispatcher("jsp/Login.jsp").forward(request, response);
		}	
	}
	// DB-Zugriff
	private RegisterBean search(String userid) throws ServletException {
		userid = (userid == null || userid == "") ? "%" : "%" + userid + "%";
		
		RegisterBean login = new RegisterBean();
		
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement("SELECT * FROM user WHERE userid LIKE ?")) {
			
			pstmt.setString(1, userid);
			try (ResultSet rs = pstmt.executeQuery()) {
			
				while(rs.next()) {
					login.setUserid(rs.getString("userid"));
					login.setEmail(rs.getString("email"));
					login.setPassword(rs.getString("password"));
					login.setRole(rs.getString("role"));
				}
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		return login;
	}
	

}
