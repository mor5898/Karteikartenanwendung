package edu.thi.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import edu.thi.bean.ViewTeacher_KarteikarteErstellenBean;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewTeacher_KarteikarteBearbeitenServlet
 */
@WebServlet("/ViewTeacher_KarteikarteBearbeitenServlet")
public class ViewTeacher_KarteikarteBearbeitenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(lookup = "java:jboss/datasources/MySqlThidbDS")
	private DataSource ds;

    /**
     * Default constructor. 
     */
    public ViewTeacher_KarteikarteBearbeitenServlet() {
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
		String karteikartenId = request.getParameter("karteikartenId");
		String fragentext = request.getParameter("fragentext");
		String titel = request.getParameter("titel");
		System.out.println("Daten: " + studiengangname + userId + modulname + karteikartenId + fragentext + titel);
		ViewTeacher_KarteikarteErstellenBean karteikarteForList = new ViewTeacher_KarteikarteErstellenBean();
		
		//List<ViewTeacher_KarteikarteErstellenBean> karteikarten = new ArrayList<>();

		try (Connection con = ds.getConnection();) {
			/*String query = "SELECT * FROM karteikarte WHERE userId = '" + userId + "' AND studiengangname = '" + studiengangname + "'"
					+ " AND modulname = '" + modulname + "'" + " AND karteikartenId = " + karteikartenId + " AND fragentext = '" + fragentext + "'"
					+ " AND titel = '" + titel + "'";*/
			String query = "SELECT * FROM karteikarte WHERE karteikartenId = " + karteikartenId;
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			//System.out.println(resultSet.next());
			if (resultSet.next()) {
				
				String modulnameForList = resultSet.getString("modulname");
				String fragentextForList = resultSet.getString("fragentext");
				String titelForList = resultSet.getString("titel");
				int karteikartenIdForList = resultSet.getInt("karteikartenId");
				String antwortAForList = resultSet.getString("antwortA");
				String antwortBForList = resultSet.getString("antwortB");
				String antwortCForList = resultSet.getString("antwortC");
				String antwortDForList = resultSet.getString("antwortD");
				String korrekteAntwortForList = resultSet.getString("korrekteAntwort");
				String begrundungstextForList = resultSet.getString("begruendungstext");
				
				karteikarteForList.setModulname(modulnameForList);
				karteikarteForList.setStudiengangname(studiengangname);
				karteikarteForList.setUserId(userId);
				karteikarteForList.setfragentext(fragentextForList);
				karteikarteForList.settitel(titelForList);
				karteikarteForList.setkarteikartenId(karteikartenIdForList);
				karteikarteForList.setantwortA(antwortAForList);
				karteikarteForList.setantwortB(antwortBForList);
				karteikarteForList.setantwortC(antwortCForList);
				karteikarteForList.setantwortD(antwortDForList);
				karteikarteForList.setkorrekteAntwort(korrekteAntwortForList);
				karteikarteForList.setbegruendung(begrundungstextForList);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		session.setAttribute("karteikarte", karteikarteForList);
		System.out.println(karteikarteForList.getkarteikartenId());
		System.out.println(karteikarteForList.getantwortA());
		System.out.println(karteikarteForList.getantwortB());
		System.out.println(karteikarteForList.getModulname());
		System.out.println(karteikarteForList.getbegruendung());
		System.out.println("Ende!");
		response.sendRedirect("jsp/ViewTeacher_KarteikarteBearbeiten.jsp");
	}

}
