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

@WebServlet("/emps/mod")
public class EmpsModController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpsService service = new EmpsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		EmpsDTO data = service.getEmpsId(id);
		
		request.setAttribute("data", data);
		
		String view = "/WEB-INF/jsp/emps/mod.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String empId = request.getParameter("empId");
		String empName = request.getParameter("empName");
		String email = request.getParameter("email");
		String jobName = request.getParameter("jobName");
		String jobId = request.getParameter("jobId");
		String deptName = request.getParameter("deptName");
		String deptId = request.getParameter("deptId");
		
		EmpsDTO data = new EmpsDTO();
		data.setEmpId(Integer.parseInt(empId));
		data.setEmpName(empName);
		data.setEmail(email);
		data.setJobName(jobName);
		data.setJobId(jobId);
		data.setDeptName(deptName);
		data.setDeptId(Integer.parseInt(deptId));
		
		// add 작업을 했던 것과 유사하게 수정 처리가
		// 완료된 데이터에 대해 상태 정보를 받아서
		// 정상/실패 를 구분하고 JSP 에서 에러메시지가
		// 나올 수 있게 처리한다.
		EMPS_SERVICE_STATUS status = service.modifyEmps(data);
		
		String view = "/WEB-INF/jsp/emps/mod.jsp";
		switch(status) {
			case SUCCESS:
				response.sendRedirect("/emps?search=" + data.getEmpId());
				return;
			case EMP_ID_DUPLICATED:
				request.setAttribute("errorCode", "empId");
				request.setAttribute("errorMsg", "직원 ID 중복 오류가 발생하였습니다.");
				break;
			case EMP_NAME_NOT_EXISTS:
				request.setAttribute("errorCode", "empName");
				request.setAttribute("errorMsg", "직원 이름이 존재하지 않습니다.");
				break;
			case EMP_EMAIL_NOT_EXISTS:
				request.setAttribute("errorCode", "email");
				request.setAttribute("errorMsg", "이메일이 존재하지 않습니다.");
				break;
			case JOB_ID_NOT_EXISTS:
				request.setAttribute("errorCode", "jobId");
				request.setAttribute("errorMsg", "직업 ID가 존재하지 않습니다.");
				break;
			case JOB_NAME_NOT_EXISTS:
				request.setAttribute("errorCode", "jobName");
				request.setAttribute("errorMsg", "직업이름이 존재하지 않습니다.");
				break;
			case DEPT_NAME_NOT_EXISTS:
				request.setAttribute("errorCode", "deptName");
				request.setAttribute("errorMsg", "부서 이름이 존재하지 않습니다.");
				break;
			case DEPT_ID_NOT_EXISTS:
				request.setAttribute("errorCode", "deptId");
				request.setAttribute("errorMsg", "부서 ID가 존재하지 않습니다.");
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
