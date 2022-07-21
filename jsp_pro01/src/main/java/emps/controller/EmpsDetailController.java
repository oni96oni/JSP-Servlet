package emps.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emps.model.EmpsDTO;
import emps.model.EmpsDetailDTO;
import emps.service.EmpsService;

@WebServlet("/emps/detail")
public class EmpsDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String view = "/WEB-INF/jsp/emps/detail.jsp";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id"); //요청할때 id값 추출, id값으로 구분하니까
		
		EmpsService empsService = new EmpsService();
		EmpsDTO empsData = empsService.getId(id);
		EmpsDetailDTO empsDetailData = empsService.getEmpDetail(empsData.getEmpId());
		
		String imagePath = empsService.getProfileImagePath(request, "/static/img/emp/", empsData);
		
		request.setAttribute("empsData", empsData);
		request.setAttribute("empsDetailData", empsDetailData);
		request.setAttribute("imagePath", imagePath);
		
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}