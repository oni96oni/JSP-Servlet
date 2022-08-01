package ajax.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.EmpBoardDTO;
import board.service.EmpBoardService;

@WebServlet("/ajax/board/like")
public class AjaxBoardLikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpBoardService service = new EmpBoardService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		String id = request.getParameter("id");
		
		EmpBoardDTO data = service.getData(Integer.parseInt(id));
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		if(data != null) {
			service.incLike(request.getSession(), data);
			sb.append(String.format("\"%s\": %d", "like", data.getLike()));
		}
		sb.append("}");
		response.getWriter().append(sb.toString());
		response.getWriter().flush();
	}

}
