package emps.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dept.model.DeptDTO;
import dept.service.DeptService;
import emps.model.EmpsDTO;
import emps.model.EmpsDetailDTO;
import emps.service.EMPS_SERVICE_STATUS;
import emps.service.EmpsService;
import job.model.JobDTO;
import job.service.JobService;

@WebServlet("/emps/add")
@MultipartConfig
public class EmpsAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpsService service = new EmpsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/emps/add.jsp";
		
		DeptService deptService = new DeptService();
		JobService jobService = new JobService();
		
		List<DeptDTO> deptDatas = deptService.getAll();
		List<JobDTO> jobDatas = jobService.getAll();
		
		request.setAttribute("deptDatas", deptDatas);
		request.setAttribute("jobDatas", jobDatas);
		request.setAttribute("imagePath", request.getContextPath() + "/static/img/emp/profile.png");
		
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String empId = request.getParameter("empId");
		String empName = request.getParameter("empName");
		String jobId = request.getParameter("jobId");
		String deptId = request.getParameter("deptId");
		String email = request.getParameter("email");
		String hireDate = request.getParameter("hireDate");
		String phone = request.getParameter("phone");
		String salary = request.getParameter("salary");
		String commission = request.getParameter("commission");
		
		EmpsDTO empsData = new EmpsDTO();
		empsData.setEmpId(Integer.parseInt(empId));
		empsData.setEmpName(empName);
		empsData.setJobId(jobId);
		empsData.setDeptId(Integer.parseInt(deptId));
		empsData.setEmail(email);
		
		EmpsDetailDTO empsDetailData = new EmpsDetailDTO();
		empsDetailData.setEmpId(empId);
		empsDetailData.setHireDate(hireDate);
		empsDetailData.setPhone(phone);
		empsDetailData.setSalary(salary);
		empsDetailData.setCommission(commission);
		
		EmpsService empsService = new EmpsService();
		boolean result= empsService.add(empsData, empsDetailData);
		
		if(result) {
			response.sendRedirect(request.getContextPath() + "/emps");
			Part imgFile = request.getPart("uploadImg");
			String originName = imgFile.getSubmittedFileName();
			
			/* ????????? ????????? ????????? ???????????? ????????? ???
			if(!originName.endsWith(".png")) {
				request.setAttribute("imageError", "???????????? PNG??? ????????? ?????????");
				doGet(request, response);
				return;
			}
			*/
			
			String location = request.getServletContext().getRealPath("/static/img/emp") + "/" + empsData.getEmpId() + ".png";
			if(!imgFile.getSubmittedFileName().isEmpty()) {
				imgFile.write(location);
			}
//			response.sendRedirect(request.getContextPath() + "/emps/detail?id=" + empsData.getEmpId());
		} else {
			doGet(request, response);
		}
/*
		EMPS_SERVICE_STATUS status = service.addEmps(empsData);
		
		String view = "/WEB-INF/jsp/emps/add.jsp";
		switch(status) {
			case SUCCESS:
				response.sendRedirect("/emps?search=" + empsData.getEmpId());
				return;
			case EMP_ID_DUPLICATED:
				request.setAttribute("errorCode", "empId");
				request.setAttribute("errorMsg", "?????? ID ?????? ????????? ?????????????????????.");
				break;
			case EMP_NAME_NOT_EXISTS:
				request.setAttribute("errorCode", "empName");
				request.setAttribute("errorMsg", "?????? ????????? ???????????? ????????????.");
				break;
			case EMP_EMAIL_NOT_EXISTS:
				request.setAttribute("errorCode", "email");
				request.setAttribute("errorMsg", "???????????? ???????????? ????????????.");
				break;
			case JOB_ID_NOT_EXISTS:
				request.setAttribute("errorCode", "jobId");
				request.setAttribute("errorMsg", "?????? ID??? ???????????? ????????????.");
				break;
			case JOB_NAME_NOT_EXISTS:
				request.setAttribute("errorCode", "jobName");
				request.setAttribute("errorMsg", "??????????????? ???????????? ????????????.");
				break;
			case DEPT_NAME_NOT_EXISTS:
				request.setAttribute("errorCode", "deptName");
				request.setAttribute("errorMsg", "?????? ????????? ???????????? ????????????.");
				break;
			case DEPT_ID_NOT_EXISTS:
				request.setAttribute("errorCode", "deptId");
				request.setAttribute("errorMsg", "?????? ID??? ???????????? ????????????.");
				break;
			case FAILED:
				request.setAttribute("errorCode", "error");
				request.setAttribute("errorMsg", "??? ??? ?????? ????????? ?????????????????????.");
				break;
		}
*/
		request.setAttribute("empsData", empsData);
		request.setAttribute("empsDetailData", empsDetailData);
		request.setAttribute("error", true);
//		request.getRequestDispatcher(view).forward(request, response);
	}

}