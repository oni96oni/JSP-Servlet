package main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeptService deptService = new DeptService();
	private String view = "/WEB-INF/jsp/index.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		RequestDispatcher rd = null;
		if(session.getAttribute("loginData") == null) {
			List<DeptDTO> deptDatas = deptService.getAll();
			request.setAttribute("deptDatas", deptDatas);
			rd = request.getRequestDispatcher(view);
		} else {
			rd = request.getRequestDispatcher("/WEB-INF/jsp/index2.jsp");
		}
		rd.forward(request, response);
	}

}
