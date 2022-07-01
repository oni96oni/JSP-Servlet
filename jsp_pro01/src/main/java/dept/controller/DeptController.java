package dept.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/depts")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		String page = request.getParameter("page"); // url로 요청받음
		int count = 3;
		
		List<DeptDTO> datas = null;
		if(search == null) {
			int pageNum = 1;
			if(page != null) {
				if(!page.isEmpty() && page.matches("\\d+")) {
					pageNum = Integer.parseInt(page);
				}
			}
			datas = service.getPage(pageNum, count);
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
