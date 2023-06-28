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
 * erstellt durch Moritz Reindl
 */
@WebServlet("/ViewTeacher_SuchenHiddenServlet")
public class ViewTeacher_SuchenHiddenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup="java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * Servlet und doPost Methode dienen lediglich der Übermittlung der user-Bean
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userid");    
		RegisterBean user = new RegisterBean();
		user.setUserid(userId);
		
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
        response.sendRedirect("jsp/ViewTeacher_Suchen.jsp");
	}
}
