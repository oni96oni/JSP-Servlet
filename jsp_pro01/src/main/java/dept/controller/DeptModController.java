package dept.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dept.model.DeptDTO;
import dept.service.DEPT_SERVICE_STATUS;
import dept.service.DeptService;
import login.model.PermDTO;

@WebServlet("/depts/mod")
public class DeptModController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DeptService service = new DeptService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		boolean isPerm = false;
		List<PermDTO> perms = (List<PermDTO>) session.getAttribute("permData");
		for(PermDTO perm : perms) {
			if(perm.getTableName().equals("departments")) {
				isPerm = perm.ispUpdate();
			}
		}
		
		if(!isPerm) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		
		String id = request.getParameter("id");
		
		DeptDTO data = service.getDeptId(id);
		
		request.setAttribute("data", data);
		
		String view = "/WEB-INF/jsp/dept/mod.jsp";
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
		
		// add 작업을 했던 것과 유사하게 수정 처리가
		// 완료된 데이터에 대해 상태 정보를 받아서
		// 정상/실패 를 구분하고 JSP 에서 에러메시지가
		// 나올 수 있게 처리한다.
		DEPT_SERVICE_STATUS status = service.modifyDept(data);
		
		String view = "/WEB-INF/jsp/dept/mod.jsp";
		switch(status) {
			case SUCCESS:
				response.sendRedirect("/jsp01/depts?search=" + data.getDeptId());
				return;
			case MNG_ID_NOT_EXISTS:
				request.setAttribute("errorMsg", "관리자 ID가 존재하지 않습니다.");
				break;
			case LOC_ID_NOT_EXISTS:
				request.setAttribute("errorMsg", "지역 ID가 존재하지 않습니다.");
				break;
			case FAILED:
				request.setAttribute("errorMsg", "알 수 없는 오류가 발생하였습니다.");
				break;
		}
		request.setAttribute("data", data);
		request.setAttribute("error", true);
		request.getRequestDispatcher(view).forward(request, response);
	}

}
