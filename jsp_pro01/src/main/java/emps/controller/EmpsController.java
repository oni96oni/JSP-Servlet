package emps.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
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
public class EmpsController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private EmpsService service = new EmpsService();
	String view = "/WEB-INF/jsp/emps/index.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		String page = request.getParameter("page"); // url로 요청받음
		int count = 5; // 한 페이지에 보여줄 게시글의 수
		String sort = "";
		
		Enumeration params = request.getParameterNames();
		while(params.hasMoreElements()) {
		  String name = (String) params.nextElement();
		  System.out.print(name + " : " + request.getParameter(name) + "     "); 
		}
		System.out.println();
		
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
		if(session.getAttribute("sort") != null) {
			sort = (String) session.getAttribute("sort");
		}
		if(request.getParameter("sort") != null) {
			sort = request.getParameter("sort");
		}
		
		session.setAttribute("pgc", count);
		session.setAttribute("sort", sort);
		request.setAttribute("pgc", count);
		request.setAttribute("menuLocation", "emps");
		List<EmpsDTO> datas = null;
		if(search == null) {
			int pageNum = 1; //초기값 1
			if(page != null) {
				if(!page.isEmpty() && page.matches("\\d+")) { //페이지가 비어있지는 않은지, 숫자로구성되어 있는지
					pageNum = Integer.parseInt(page);
				}
			}
			datas = service.getPage(pageNum, count, sort);
			request.setAttribute("pageList", service.getPageNumberList(count));
			request.setAttribute("page", pageNum);
		} else {
			EmpsDTO data = service.getEmpsId(search);
			if(data != null) {
				datas = new ArrayList<EmpsDTO>();
				datas.add(data);
			}
		}
		
		request.setAttribute("datas", datas);
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}