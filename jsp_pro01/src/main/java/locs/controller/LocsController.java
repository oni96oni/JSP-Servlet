package locs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dept.model.DeptDTO;
import locs.model.LocsDTO;
import locs.service.LocsService;

@WebServlet("/locs")
public class LocsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LocsService service = new LocsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search"); // 사용자가 입력한 데이터 저장
		String page = request.getParameter("page");
		int count = 5;
		String sort = "";
		String whatPage = "locs";
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("pgc") != null) {
//			count = (int)session.getAttribute("pgc");
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
		session.setAttribute("whatPage", whatPage);
		
		request.setAttribute("pgc", count);
		request.setAttribute("menuLocation", "locs");
		List<LocsDTO> datas = null;
		if(search == null) {
			int pageNum = 1;
			if(page != null) {
				if(!page.isEmpty() && page.matches("\\d+")) {
					pageNum = Integer.parseInt(page);
				}
			}
			datas = service.getPage(pageNum, count, sort);
			request.setAttribute("pageList", service.getPageNumberList(count));
			request.setAttribute("page", pageNum);
		} else {
			LocsDTO data = service.getLocsId(search);
			if(data != null) {
				datas = new ArrayList<LocsDTO>();
				datas.add(data);
			}
		}
		
		request.setAttribute("datas", datas); // (속성명, 속성의 값)
		
		String view = "/WEB-INF/jsp/locs/index.jsp"; // 이 view로 화면을 구성 하겠다.
		request.getRequestDispatcher(view).forward(request, response); // forward가 정보를 jsp에다가 전달한다.
	}
}