package comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import comment.model.CommentDTO;
import comment.service.CommentService;
import emps.model.EmpsDTO;

@WebServlet("/comment/add")
public class CommentAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CommentService service = new CommentService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); 
		
		String bid = request.getParameter("bid");
		String content= request.getParameter("content");
		
		EmpsDTO empData = (EmpsDTO) session.getAttribute("loginData"); // 작성자 id를 알아온다.
		
		CommentDTO commentData = new CommentDTO();
		commentData.setbId(Integer.parseInt(bid));
		commentData.setContent(content);
		commentData.setEmpId(empData.getEmpId());
		
		boolean result = service.add(commentData);
		if(result) {
			response.sendRedirect(request.getContextPath() + "/board/detail?id=" + commentData.getbId());
		} else {
			response.sendRedirect(request.getContextPath() + "/board/detail?id=" + commentData.getbId());

		}
		
	}
}