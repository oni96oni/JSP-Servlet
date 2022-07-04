package locs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DEPT_SERVICE_STATUS;
import locs.model.LocsDTO;
import locs.service.LOCS_SERVICE_STATUS;
import locs.service.LocsService;

@WebServlet("/locs/del")
public class LocsDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LocsService service = new LocsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		LocsDTO data = service.getLocsId(id);
		request.setAttribute("data", data);
		
		String view = "/WEB-INF/jsp/locs/del.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String locsId = request.getParameter("locsId");
		LOCS_SERVICE_STATUS status = service.deleteLocs(locsId);
		
		String view = "/WEB-INF/jsp/locs/del.jsp";
		switch(status) {
			case SUCCESS:
				response.sendRedirect("../locs");
				return;
			case FAILED:
				request.setAttribute("errorMsg", "삭제 처리 중 문제 발생");
				break;
			case LOCS_ID_NOT_EXISTS:
				request.setAttribute("errorMsg", "삭제할 데이터가 존재하지 않습니다.");
				break;
		}
		request.getRequestDispatcher(view).forward(request, response);
	}

}
