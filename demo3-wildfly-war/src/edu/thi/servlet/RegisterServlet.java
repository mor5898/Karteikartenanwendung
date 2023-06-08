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
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name="RegisterServlet", urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
    /**
     * Default constructor. 
     */
    public RegisterServlet() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		RegisterBean form = new RegisterBean();
		form.setEmail(request.getParameter("email"));
		form.setPassword(request.getParameter("password"));
		form.setPassword2(request.getParameter("password2"));
		form.setUserid(request.getParameter("userid"));
		form.setRole(request.getParameter("role"));
		
		// Regexerstellung zur Überprüfung von Passwortbedingungen
		String password = form.getPassword();
	    String uppercaseRegex = "^.*[A-Z]+.*$";
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
	    

	    // Überprüfung der Passwortbedingungen
	    boolean isValid = true;
	     if(!form.getPassword().equals(form.getPassword2())) {
	    	 isValid = false;
	    	 response.getWriter().println("Die Passwörter müssen übereinstimmen.");
	     }
	     if(form.getPassword().length()<6) {
	    	 isValid = false;
	    	 response.getWriter().println("Das Passwort muss aus mindestens 6 Stellen bestehen.");
	     }
	     if (!Uppercasematcher.matches()) {
	    	 isValid = false;
	         response.getWriter().println("Das Passwort muss mindestens einen Großbuchstaben enthalten.");
	     }
	     if (!Lowercasematcher.matches()) {
	         isValid = false;
	         response.getWriter().println("Das Passwort muss mindestens einen Kleinbuchstaben enthalten.");
	     }
	     if (!Digitmatcher.matches()) {
	         isValid = false;
	         response.getWriter().println("Das Passwort muss mindestens eine Ziffer enthalten.");
	     }
	     if (!SpecialCharmatcher.matches()) {
	         isValid = false;
	         response.getWriter().println("Das Passwort muss mindestens ein Sonderzeichen enthalten.");
	     }
	     if (isValid) {
	    	 persist(form);
	         final HttpSession session = request.getSession();
	         session.setAttribute("RegisterForm", form);
	         response.sendRedirect("jsp/Register.jsp");
	     }
	}
	
	private void persist(RegisterBean form) throws ServletException {
		String[] generatedKeys = new String[] {"id"};	
		
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"INSERT INTO user (userid,passwort,email,rolle) VALUES (?,?,?,?)", 
					generatedKeys)){

			pstmt.setString(1, form.getUserid());
			pstmt.setString(2, form.getPassword());
			pstmt.setString(3, form.getEmail());
			pstmt.setString(4, form.getRole());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				form.setId(rs.getLong(1));
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
	}
}
