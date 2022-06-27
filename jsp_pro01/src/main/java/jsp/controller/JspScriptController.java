package jsp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsp/script_tag")
public class JspScriptController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/jsp/script_tag.jsp"; //jsp파일의 경로
		request.getRequestDispatcher(view).forward(request, response);
		System.out.println(request.getMethod());
		System.out.println(request.getSession());
		System.out.println(request.getParameterNames());
	}

}
