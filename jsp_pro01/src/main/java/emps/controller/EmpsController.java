package emps.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import emps.model.EmpsDTO;
import emps.service.EmpsService;

@WebServlet("/emps")
public class EmpsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String view = "/WEB-INF/jsp/emps/index.jsp";
	private EmpsService service = new EmpsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		int count = 5;
		
		if(page == null) {
			page = "1";
		}
		
		HttpSession session = request.getSession();
		if(session.getAttribute("pgc") != null) {
			count = Integer.parseInt(session.getAttribute("pgc").toString());
		}
		
		if(request.getParameter("pgc") != null) {
			count = Integer.parseInt(request.getParameter("pgc"));
		}
		
		request.setAttribute("menuLocation", "emps");
		session.setAttribute("pgc", count);
		
		// List<EmpsDTO> datas = service.getAll();
		List<EmpsDTO> datas = service.getPage(Integer.parseInt(page), count);
		List<Integer> pageList = service.getPageNumberList(count);
		
		request.setAttribute("datas", datas);
		request.setAttribute("page", page);
		request.setAttribute("pageList", pageList);
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

}
