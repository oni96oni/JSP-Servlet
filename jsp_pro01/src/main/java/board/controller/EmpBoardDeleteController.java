package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.EmpBoardDTO;
import board.service.EmpBoardService;
import emps.model.EmpsDTO;

@WebServlet("/board/delete")
public class EmpBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpBoardService service = new EmpBoardService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		
		EmpBoardDTO data = service.getData(Integer.parseInt(id));
		EmpsDTO empData = (EmpsDTO)session.getAttribute("loginData");
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		if(data != null) {
			if(data.getEmpId() == empData.getEmpId()) {
				service.remove(data);
				sb.append(String.format("\"%s\": \"%s\"", "code", "success"));
			} else {
				sb.append(String.format("\"%s\": \"%s\"", "code", "permissionError"));
			}
		} else {
			sb.append(String.format("\"%s\": \"%s\"", "code", "notExists"));
		}
		sb.append("}");
		
		response.getWriter().append(sb.toString());
		response.getWriter().flush();
	}

}
