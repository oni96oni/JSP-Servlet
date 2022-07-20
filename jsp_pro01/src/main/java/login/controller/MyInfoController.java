package login.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import emps.model.EmpsDTO;
import emps.model.EmpsDetailDTO;
import emps.service.EmpsService;

@WebServlet("/myinfo")
@MultipartConfig(
		maxFileSize = 1024 * 1024 * 10
)
public class MyInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String view = "/WEB-INF/jsp/login/myinfo.jsp";
	
	private EmpsService empsService = new EmpsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		RequestDispatcher rd = null;
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
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		EmpsDTO empsData = (EmpsDTO)session.getAttribute("loginData");
		
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		Part imgFile = request.getPart("uploadImg");
		String originName = imgFile.getSubmittedFileName();
		
		if(!originName.endsWith(".png")) {
			request.setAttribute("imageError", "이미지는 PNG 만 업로드 하세요.");
			doGet(request, response);
			return;
		}
		
		String location = request.getServletContext().getRealPath("/static/img/emp") + "/" + empsData.getEmpId() + ".png";
		
		empsData.setEmail(email);
		
		EmpsDetailDTO empsDetailData = new EmpsDetailDTO();
		empsDetailData.setEmpId(empsData.getEmpId());
		empsDetailData.setPhone(phone);
		
		boolean result = empsService.setEmp(empsData, empsDetailData);
		
		if(result) {
			// 수정 성공
			response.sendRedirect(request.getContextPath() + "/myinfo");
			if(!originName.isEmpty()) {
				imgFile.write(location);
			}
		} else {
			// 수정 실패
			request.setAttribute("error", "수정 작업 중 문제가 발생하였습니다.");
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		}
	}

}
