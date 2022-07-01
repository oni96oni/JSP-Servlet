package dept.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DEPT_SERVICE_STATUS;
import dept.service.DeptService;

@WebServlet("/depts/del")
public class DeptDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		DeptDTO data = service.getDeptId(id);
		request.setAttribute("data", data);
		
		String view = "/WEB-INF/jsp/dept/del.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId = request.getParameter("deptId");
		DEPT_SERVICE_STATUS status = service.deleteDept(deptId);
		
		String view = "/WEB-INF/jsp/dept/del.jsp";
		switch(status) {
			case SUCCESS:
				response.sendRedirect("../depts");
				return;
			case FAILED:
				request.setAttribute("errorMsg", "삭제 처리 중 문제 발생");
				break;
			case DEPT_ID_NOT_EXISTS:
				request.setAttribute("errorMsg", "삭제할 데이터가 존재하지 않습니다.");
				break;
		}
		request.getRequestDispatcher(view).forward(request, response);
	}

}
