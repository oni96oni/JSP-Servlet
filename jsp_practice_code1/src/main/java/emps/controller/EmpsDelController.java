package emps.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emps.model.EmpsDTO;
import emps.service.EMPS_SERVICE_STATUS;
import emps.service.EmpsService;

@WebServlet("/emps/del")
public class EmpsDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpsService service = new EmpsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		EmpsDTO data = service.getEmpsId(id);
		request.setAttribute("data", data);
		
		String view = "/WEB-INF/jsp/emps/del.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId = request.getParameter("empId");
		EMPS_SERVICE_STATUS status = service.deleteEmps(empId);
		
		String view = "/WEB-INF/jsp/emps/del.jsp";
		switch(status) {
			case SUCCESS:
				response.sendRedirect("../emps");
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
