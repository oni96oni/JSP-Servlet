package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.EmpBoardDTO;
import board.service.EmpBoardService;
import emps.model.EmpsDTO;

@WebServlet("/board/add")
public class EmpBoardAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpBoardService service = new EmpBoardService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/board/add.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		EmpBoardDTO data = new EmpBoardDTO();
		data.setTitle(title);
		data.setContent(content);
		data.setEmpId(((EmpsDTO)session.getAttribute("loginData")).getEmpId());
		// 위의 3개만 not null이고 나머지는 null이어도 괜찮거나 디폴트값이 있다.
		
		int boardId = service.add(data);
		if(boardId > 0) {
			// 정상적으로 게시글이 등록됨
			response.sendRedirect(request.getContextPath() + "/board/detail?id=" + boardId);
		} else {
			// 게시글 번호가 0보다 작다? 문제가 있다는 뜻 - 롤백되었을때 0보다 작은값이 들어온다.
			doGet(request, response);
		}
	}
}