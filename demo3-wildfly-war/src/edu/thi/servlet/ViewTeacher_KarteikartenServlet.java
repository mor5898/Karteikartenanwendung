package edu.thi.servlet;

import java.io.IOException;

import edu.thi.bean.ViewTeacher_ModuleBean;
import edu.thi.bean.ViewTeacher_StudiengaengeBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Erstellt durch: Moritz Reindl
 */
@WebServlet("/ViewTeacher_KarteikartenServlet")
public class ViewTeacher_KarteikartenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ViewTeacher_KarteikartenServlet() {
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
		request.setCharacterEncoding("UTF-8");
		String studiengangname = request.getParameter("studienfachId");
		String userId = request.getParameter("userid");
		String modulname = request.getParameter("modulname");
		System.out.println("Studiengang: " + studiengangname + " userId: " + userId + " modulname: " + modulname);
		
		ViewTeacher_StudiengaengeBean studiengang = new ViewTeacher_StudiengaengeBean();
		studiengang.setStudiengangname(studiengangname);
		studiengang.setUserId(userId);
		
		ViewTeacher_ModuleBean modul = new ViewTeacher_ModuleBean();
		modul.setStudiengangname(studiengangname);
		modul.setUserId(userId);
		modul.setModulname(modulname);
		
		HttpSession session = request.getSession();
		session.setAttribute("modul", modul);
		session.setAttribute("studienfachId", studiengang);
		session.setAttribute("userid", userId);
		response.sendRedirect("jsp/ViewTeacher_Karteikarten.jsp");
	}

}
