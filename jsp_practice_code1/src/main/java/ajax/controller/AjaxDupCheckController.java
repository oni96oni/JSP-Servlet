package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;
import emps.model.EmpsDTO;
import emps.service.EmpsService;
import locs.model.LocsDTO;
import locs.service.LocsService;

@WebServlet("/ajax/dupCheck")
public class AjaxDupCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService deptService = new DeptService();
	private LocsService locsService = new LocsService();
	private EmpsService empsService = new EmpsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptId = request.getParameter("deptId");
		String locsId = request.getParameter("locsId");
		String empId = request.getParameter("empId");
		
		response.setContentType("application/json; charset=utf-8");
		
		if(deptId != null) {
			DeptDTO data = deptService.getDeptId(deptId);
			if(data != null) {
				PrintWriter out = response.getWriter();
				out.println("{");
				out.println("    \"errCode\": \"duplicate\",");
				out.println("    \"errMessage\": \"해당 부서 ID는 이미 존재합니다.\"");
				out.println("}");
				out.flush();
			}
		} else if(locsId != null){
			LocsDTO data = locsService.getLocsId(locsId);
			if(data != null) {
				PrintWriter out = response.getWriter();
				out.println("{");
				out.println("    \"errCode\": \"duplicate\",");
				out.println("    \"errMessage\": \"해당 지역 ID는 이미 존재합니다.\"");
				out.println("}");
				out.flush();
			}
		} else if(empId != null) {
			EmpsDTO data = empsService.getEmpsId(empId);
			if(data != null) {
				PrintWriter out = response.getWriter();
				out.println("{");
				out.println("    \"errCode\": \"duplicate\",");
				out.println("    \"errMessage\": \"해당 직원 ID는 이미 존재합니다.\"");
				out.println("}");
				out.flush();
			}
		} else {
			// 다음 중복 조회 추가할 공간.
		}
	}

}
