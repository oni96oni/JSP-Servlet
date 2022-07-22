package dept.controller;

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
import dept.service.DeptService;
import login.model.PermDTO;

@WebServlet("/depts")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		boolean isPerm = false;
		
		List<PermDTO> perms = (List<PermDTO>) session.getAttribute("permData");
		for(PermDTO perm : perms) {
			if(perm.getTableName().equals("departments")) {
				isPerm = perm.ispRead();
			}
		}
		
		if(!isPerm) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
		String search = request.getParameter("search");
		String page = request.getParameter("page");
		String sort = "deptId";
		int count = 5;
		
		if(session.getAttribute("pgc") != null) {
			count = Integer.parseInt(session.getAttribute("pgc").toString());
		}
		if(session.getAttribute("sort") != null) {
			sort = (String)session.getAttribute("sort");
		}
		
		if(request.getParameter("pgc") != null) {
			count = Integer.parseInt(request.getParameter("pgc"));
		}
		if(request.getParameter("sort") != null) {
			sort = request.getParameter("sort");
		}
		
		session.setAttribute("pgc", count);
		session.setAttribute("sort", sort);
		request.setAttribute("pgc", count);
		request.setAttribute("menuLocation", "depts");
		
		List<DeptDTO> datas = null;
		if(search == null) {
			int pageNum = 1;
			if(page != null) {
				if(!page.isEmpty() && page.matches("\\d+")) {
					pageNum = Integer.parseInt(page);
				}
			}
			datas = service.getPage(pageNum, count, sort);
			request.setAttribute("page", pageNum);
			request.setAttribute("pageList", service.getPageNumberList(count));
		} else {
			DeptDTO data = service.getDeptId(search);
			if(data != null) {
				datas = new ArrayList<DeptDTO>();
				datas.add(data);
			}
		}
		
		request.setAttribute("datas", datas);
		
		String view = "/WEB-INF/jsp/dept/index.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
