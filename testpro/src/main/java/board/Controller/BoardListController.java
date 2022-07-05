package board.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardService;

@WebServlet("/update.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private BoardService service;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		Board board = service.getbId(id);
		
		request.setAttribute("board", board);
		
		String view = "/WEB-INF/views/common/boardList.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bId = request.getParameter("bId");
		String bTitle = request.getParameter("bTitle");
		String bWriter= request.getParameter("bWriter");
		String bContent = request.getParameter("bContent");
		
		board.model.Board board = new board.model.Board();
		board.setbId(bId);
		board.setbTitle(bTitle);
		board.setbWriter(bWriter);
		board.setbContent(bContent);
		
		String view = "";
		
		if(!service.updateBoard(board)) {
			view = "views/common/errorPage.jsp";
			 request.setAttribute("msg", "게시글 수정 실패");
		} else {
			view = "views/common/bdetail.jsp";
		}
		
		request.setAttribute("board", board);
		request.setAttribute("error", true);
		request.getRequestDispatcher(view).forward(request, response);
	}
}