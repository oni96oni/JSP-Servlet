package board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.EmpBoardDTO;
import board.service.EmpBoardService;
import comment.model.CommentDTO;
import comment.service.CommentService;
import common.util.Paging;
import emps.model.EmpsDTO;
import emps.service.EmpsService;

@WebServlet("/board/detail")
public class EmpBoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpBoardService service = new EmpBoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/board/detail.jsp";
		String id = request.getParameter("id");
		
		EmpsService empsService = new EmpsService();
		
		EmpBoardDTO data = service.getData(Integer.parseInt(id));
		
		if(data != null) {
			HttpSession session = request.getSession();
			
			service.incViewCnt(session, data);
			
			EmpsDTO empData = empsService.getId("" + data.getEmpId());
			CommentService commentService = new CommentService();
			List commentDatas = commentService.getDatas(data.getId());
			
			String page = request.getParameter("page");
			page = page == null ? "1" : page;
			
			Paging commentPage = new Paging(commentDatas, Integer.parseInt(page), 5);
			
			request.setAttribute("data", data);
			request.setAttribute("empData", empData);
			request.setAttribute("commentPage", commentPage);
			
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
		} else {
			// 데이터가 조회되지 않은 경우
			// 별도의 에러 페이지로 전환
		}
	}

}
