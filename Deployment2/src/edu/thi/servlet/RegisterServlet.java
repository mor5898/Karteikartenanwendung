//Erstellt von Muhammed Samil Turan
package edu.thi.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import edu.thi.bean.RegisterBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name="RegisterServlet", urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//Bean zum Eintrag der Registrierung in die DB
		RegisterBean form = new RegisterBean();
		form.setEmail(request.getParameter("email"));
		form.setPassword(request.getParameter("password"));
		form.setPassword2(request.getParameter("password2"));
		form.setUserid(request.getParameter("userid"));
		form.setRole(request.getParameter("role"));
		//Bean zur Überprüfung auf bereits vorhandenen User über DB Suche
		RegisterBean register = search(form.getUserid());
		//Bean zur Überprüfung auf bereits benutzte Email über DB Suche
		RegisterBean email = searchemail(form.getEmail());
		
		// Regexerstellung zur Überprüfung von Passwortbedingungen
		String password = form.getPassword();
	    String uppercaseRegex = "^.*[A-Z]+.*$";//Regex für Großbuchstaben
	    Pattern Uppercasepattern = Pattern.compile(uppercaseRegex);
	    Matcher Uppercasematcher = Uppercasepattern.matcher(password);
	    String lowercaseRegex = "^.*[a-z]+.*$";
	    Pattern Lowercasepattern = Pattern.compile(lowercaseRegex);
	    Matcher Lowercasematcher = Lowercasepattern.matcher(password);
	    String digitRegex = "^.*[0-9]+.*$";
	    Pattern Digitpattern = Pattern.compile(digitRegex);
	    Matcher Digitmatcher = Digitpattern.matcher(password);
	    String specialCharRegex = "^.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]+.*$";
	    Pattern SpecialCharpattern = Pattern.compile(specialCharRegex);
	    Matcher SpecialCharmatcher = SpecialCharpattern.matcher(password);
	    
	    //Grundgerüst isValid und if-Anweisungen erstellt von ChatGPT
	    boolean isValid = true;
	    
	    //Überprüfung auf bereits existierenden User
	     if (register.getUserid()!=null) {
	    	 isValid = false;
	    	 String existinguser = "Der angegebene User existiert bereits";
	    	 request.setAttribute("text", existinguser);
	    	 request.getRequestDispatcher("jsp/Register.jsp").forward(request, response);
	     }
	     // Überprüfung auf  bereits benutzte Email
	     if (email.getEmail()!=null) {
	    	 isValid = false;
	    	 String existingemail = "Die angegebene Email wird bereits benutzt";
	    	 request.setAttribute("text", existingemail);
	    	 request.getRequestDispatcher("jsp/Register.jsp").forward(request, response);
	     }
	     // Überprüfung auf Übereinstimmung der eingegebenen Passwörter
	     if(!form.getPassword().equals(form.getPassword2())) {
	    	 isValid = false;
	    	 String passwordmatching = "Die Passwörter müssen übereinstimmen.";
	    	 request.setAttribute("text", passwordmatching);
	    	 request.getRequestDispatcher("jsp/Register.jsp").forward(request, response);
	     }
	     // Überprüfung der Passwortbedingungen
	     if(form.getPassword().length()<6 ||!Uppercasematcher.matches()||!Lowercasematcher.matches()||!Digitmatcher.matches()||!SpecialCharmatcher.matches()) {
	    	 isValid = false;
	    	 String wrongpassword = "Das Passwort muss aus mindestens 6 Stellen bestehen und muss mindestens 1 Ziffer, 1 Großbuchstaben, 1 Kleinbuchstaben und 1 Sonderzeichen enthalten.";
	    	 request.setAttribute("text", wrongpassword);
	    	 request.getRequestDispatcher("jsp/Register.jsp").forward(request, response);
	     }
	     // Vorgehen bei erfolgreicher Überprüfung 
	     if (isValid) {
	    	 String successfulreg = "Hallo " + form.getUserid() + ", deine Registrierung war erfolgreich" ;
	    	 request.setAttribute("text", successfulreg);
	    	 request.getRequestDispatcher("jsp/Register.jsp").forward(request, response);
	    	 persist(form);
	     }
	}
	// DB Zugriff zur Einspeicherung von Registrierungen
	private void persist(RegisterBean form) throws ServletException {
		String[] generatedKeys = new String[] {"id"};	
		
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO user (userid,password,email,role) VALUES (?,?,?,?)", 
					generatedKeys)){
			
			pstmt.setString(1, form.getUserid());
			pstmt.setString(2, form.getPassword());
			pstmt.setString(3, form.getEmail());
			pstmt.setString(4, form.getRole());
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
	// DB Zugriff zur Überprüfung auf bereits registrierte Benutzer
	private RegisterBean search(String userid) throws ServletException {
		userid = (userid == null || userid == "") ? "%" : "%" + userid + "%";
		
		RegisterBean login = new RegisterBean();
		// DB-Zugriff
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement("SELECT * FROM user WHERE userid LIKE ?")) {
			
			pstmt.setString(1, userid);
			try (ResultSet rs = pstmt.executeQuery()) {
			
				while(rs.next()) {
					//rs.next();
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
	// DB Zugriff zur Überprüfung von bereits benutzten Emails
	private RegisterBean searchemail(String email) throws ServletException {
		email = (email == null || email == "") ? "%" : "%" + email + "%";
		
		RegisterBean login = new RegisterBean();
		// DB-Zugriff
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement("SELECT * FROM user WHERE email LIKE ?")) {
			
			pstmt.setString(1, email);
			try (ResultSet rs = pstmt.executeQuery()) {
			
				while(rs.next()) {
					//rs.next();
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
