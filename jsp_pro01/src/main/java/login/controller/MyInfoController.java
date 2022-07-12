package login.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import emps.model.EmpsDTO;
import emps.model.EmpsDetailDTO;
import emps.service.EmpsService;

@WebServlet("/myinfo")
public class MyInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String view = "/WEB-INF/jsp/login/myinfo.jsp";
	
	private EmpsService empsService = new EmpsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		RequestDispatcher rd = null;
		if(session.getAttribute("loginData") != null) {
			EmpsDTO empsData = (EmpsDTO)session.getAttribute("loginData");
			EmpsDetailDTO empsDetailData = empsService.getEmpDetail(empsData.getEmpId());
			
			request.setAttribute("empsDetailData", empsDetailData);
			
			File file = new File(
					request.getServletContext().getRealPath(request.getContextPath() + "/static/img/emp/" + empsData.getEmpId() + ".png"));
			
			request.setAttribute("imagePath", request.getContextPath() + "/static/img/emp/profile.png");
			if(file.exists()) {
				request.setAttribute("imagePath", request.getContextPath() + "/static/img/emp/" + empsData.getEmpId() + ".png");
			}
			
			rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/login");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginData") == null) {
			response.sendRedirect(request.getContextPath() + "/login");
		} else {
			EmpsDTO empsData = (EmpsDTO)session.getAttribute("loginData");
			empsData.setEmail(email);
			
			EmpsDetailDTO empsDetailData = new EmpsDetailDTO();
			empsDetailData.setEmpId(empsData.getEmpId());
			empsDetailData.setPhone(phone);
			
			boolean result = empsService.setEmp(empsData, empsDetailData);
			if(result) {
				// 수정 성공
				response.sendRedirect(request.getContextPath() + "/myinfo");
			} else {
				// 수정 실패
				request.setAttribute("error", "수정 작업 중 문제가 발생하였습니다.");
				RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.forward(request, response);
			}
		}
	}
	
}