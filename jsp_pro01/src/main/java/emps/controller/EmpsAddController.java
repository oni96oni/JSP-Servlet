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

@WebServlet("/emps/add")
public class EmpsAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpsService service = new EmpsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/emps/add.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String mngId = request.getParameter("mngId");
		String locId = request.getParameter("locId");
		
		EmpsDTO data = new EmpsDTO();
		data.setDeptId(Integer.parseInt(deptId));
		data.setDeptName(deptName);
		
		EMPS_SERVICE_STATUS status = service.addEmps(data);
		
		String view = "/WEB-INF/jsp/emps/add.jsp";
		switch(status) {
			case SUCCESS:
				response.sendRedirect("/emps?search=" + data.getEmpId());
				return;
			case DEPT_ID_DUPLICATED:
				request.setAttribute("errorCode", "deptId");
				request.setAttribute("errorMsg", "부서 ID 중복 오류가 발생하였습니다.");
				break;
			case MNG_ID_NOT_EXISTS:
				request.setAttribute("errorCode", "mngId");
				request.setAttribute("errorMsg", "관리자 ID가 존재하지 않습니다.");
				break;
			case LOC_ID_NOT_EXISTS:
				request.setAttribute("errorCode", "locId");
				request.setAttribute("errorMsg", "지역 ID가 존재하지 않습니다.");
				break;
			case FAILED:
				request.setAttribute("errorCode", "error");
				request.setAttribute("errorMsg", "알 수 없는 오류가 발생하였습니다.");
				break;
		}
		request.setAttribute("data", data);
		request.setAttribute("error", true);
		request.getRequestDispatcher(view).forward(request, response);
	}

}