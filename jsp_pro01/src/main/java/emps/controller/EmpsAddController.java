package emps.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
import emps.service.EmpsService;
import job.model.JobDTO;
import job.service.JobService;

@WebServlet("/emps/add")
@MultipartConfig
public class EmpsAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/emps/add.jsp";
		
		DeptService deptService = new DeptService();
		JobService jobService = new JobService();
		
		List<DeptDTO> deptDatas = deptService.getAll();
		List<JobDTO> jobDatas = jobService.getAll();
		
		request.setAttribute("deptDatas", deptDatas);
		request.setAttribute("jobDatas", jobDatas);
		request.setAttribute("imagePath", request.getContextPath() + "/static/img/emp/profile.png");
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
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
		empsData.setEmpId(empId);
		empsData.setEmpName(empName);
		empsData.setJobId(jobId);
		empsData.setDeptId(deptId);
		empsData.setEmail(email);
		
		EmpsDetailDTO empsDetailData = new EmpsDetailDTO();
		empsDetailData.setEmpId(empId);
		empsDetailData.setHireDate(hireDate);
		empsDetailData.setPhone(phone);
		empsDetailData.setSalary(salary);
		empsDetailData.setCommission(commission);
		
		EmpsService empsService = new EmpsService();
		boolean result = empsService.add(empsData, empsDetailData);
		
		if(result) {
			// 저장 성공
			Part imgFile = request.getPart("uploadImg");
			String originName = imgFile.getSubmittedFileName();
			
			/* 나중에 디테일 화면이 구현되면 완성 할 것.
			if(!originName.endsWith(".png")) {
				request.setAttribute("imageError", "이미지는 PNG 만 업로드 하세요.");
				doGet(request, response);
				return;
			}
			*/
			
			String location = request.getServletContext().getRealPath("/static/img/emp") + "/" + empsData.getEmpId() + ".png";
			
			if(!originName.isEmpty()) {
				imgFile.write(location);
			}
			
			response.sendRedirect(request.getContextPath() + "/emps");
			// response.sendRedirect(request.getContextPath() + "/emps/detail?id=" + empsData.getEmpId());
		} else {
			// 저장 실패
			doGet(request, response);
		}
		
	}

}
