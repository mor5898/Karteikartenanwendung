package edu.thi.servlet;

import java.io.IOException;

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
 * Servlet implementation class ViewTeacher_SuchenHiddenServlet
 */
@WebServlet("/ViewTeacher_SuchenHiddenServlet")
public class ViewTeacher_SuchenHiddenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;
    /**
     * Default constructor. 
     */
    public ViewTeacher_SuchenHiddenServlet() {
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
		
		String userId = request.getParameter("userid");    
		RegisterBean user = new RegisterBean();
		user.setUserid(userId);
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
        response.sendRedirect("jsp/ViewTeacher_Suchen.jsp");
	}
}
