package edu.thi.servlet;

import java.io.IOException;

import edu.thi.bean.ViewTeacher_KarteikarteErstellenBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewTeacher_KarteikarteErstellenHiddenServlet
 */
@WebServlet("/ViewTeacher_KarteikarteErstellenHiddenServlet")
public class ViewTeacher_KarteikarteErstellenHiddenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ViewTeacher_KarteikarteErstellenHiddenServlet() {
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
		String studiengangname = request.getParameter("studienfachId");
		String userId = request.getParameter("userid");
		String modulname = request.getParameter("modulname");
		
		ViewTeacher_KarteikarteErstellenBean karteikarteForList = new ViewTeacher_KarteikarteErstellenBean();
		
		karteikarteForList.setModulname(modulname);
		karteikarteForList.setStudiengangname(studiengangname);
		karteikarteForList.setUserId(userId);
		
		HttpSession session = request.getSession();
		session.setAttribute("karteikarte", karteikarteForList);
		System.out.println(karteikarteForList.getUserId());
		response.sendRedirect("jsp/ViewTeacher_KarteikarteErstellen.jsp");
	}

}
