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

@WebServlet("/depts/add")
public class DeptAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/dept/add.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String mngId = request.getParameter("mngId");
		String locId = request.getParameter("locId");
		
		DeptDTO data = new DeptDTO();
		data.setDeptId(Integer.parseInt(deptId));
		data.setDeptName(deptName);
		data.setMngId(Integer.parseInt(mngId));
		data.setLocId(Integer.parseInt(locId));
		
		DEPT_SERVICE_STATUS status = service.addDept(data);
		
		String view = "/WEB-INF/jsp/dept/add.jsp";
		switch(status) {
			case SUCCESS:
				response.sendRedirect(request.getContextPath() + "/depts?search=" + data.getDeptId());
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
