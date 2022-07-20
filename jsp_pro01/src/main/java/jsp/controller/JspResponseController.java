package jsp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsp/response")
public class JspResponseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/jsp/response.jsp";
		
		if(request.getParameter("redirect") != null) {
			response.sendRedirect("/jsp01/main");
		} else {
			request.getRequestDispatcher(view).forward(request, response);
		}
	}

}
