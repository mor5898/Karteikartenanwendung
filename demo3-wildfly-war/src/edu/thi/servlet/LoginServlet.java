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
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");	// In diesem Format erwartet das Servlet jetzt die Formulardaten
		String userid = request.getParameter("userid");
		/*List<ViewTeacher_StudiengaengeBean> studienfaecher = getAllStudienfaecher();*/
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
        }
       // request.setAttribute("studienfaecher", studienfaecher);
		// DB-Zugriff
		RegisterBean login = search(userid);
		System.out.println(login.getUserid());		
		// Scope "Request"
		//request.setAttribute("LoginForm", login);
		
		// Weiterleiten an JSP
		//final RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ViewTeacher_Studiengaenge.jsp");
		//dispatcher.forward(request, response);	
		
		
		HttpSession session = request.getSession();
		session.setAttribute("studienfaecher", studienfaecher);
		session.setAttribute("LoginForm", login);
        response.sendRedirect("jsp/ViewTeacher_Studiengaenge.jsp");
	}
	
	private RegisterBean search(String userid) throws ServletException {
		userid = (userid == null || userid == "") ? "%" : "%" + userid + "%";
		
		RegisterBean login = new RegisterBean();
		// DB-Zugriff
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement("SELECT * FROM user WHERE userid LIKE ?")) {
			
			pstmt.setString(1, userid);
			try (ResultSet rs = pstmt.executeQuery()) {
			
				while (rs.next()) {
					login.setUserid(rs.getString("userid"));
					login.setEmail(rs.getString("email"));
					login.setPassword(rs.getString("password"));
					login.setRole(rs.getString("role"));
				} // while rs.next()
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		
		return login;
	}
	

}
