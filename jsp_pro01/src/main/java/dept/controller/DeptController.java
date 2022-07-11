package dept.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/depts")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		String page = request.getParameter("page"); // url로 요청받음
		int count = 5; // 한 페이지에 보여줄 게시글의 수
		String sort = "";
		
		HttpSession session = request.getSession();
		if(session.getAttribute("pgc") != null) {
//			count = (int)session.getAttribute("pgc");
			count = Integer.parseInt(session.getAttribute("pgc").toString());
		}
		
		if(request.getParameter("pgc") != null) {
			count = Integer.parseInt(request.getParameter("pgc"));
		}
		
		session.setAttribute("pgc", count);
		
		System.out.println(sort);
		if(session.getAttribute("sort") != null) {
			sort = (String) session.getAttribute("sort");
		}
		System.out.println(sort);
		if(request.getParameter("sort") != null) {
			sort = request.getParameter("sort");
		}
		System.out.println(sort);
		session.setAttribute("sort", sort);
/*	
//쿠키이용하는 코드 
//!! 적용하고나서는 main으로 간뒤에 depts에 가야 초기 쿠키를 전달받을 수 있다. 안그러면 에러 발생
	
		Cookie[] cookies = request.getCookies();
		for(Cookie c: cookies) {
			if(c.getName().equals("pgc")) {
				count = Integer.parseInt(c.getValue());
			}
		}
		
		Cookie cookie = null;
		if(request.getParameter("pgc") != null) {
			count = Integer.parseInt(request.getParameter("pgc"));
			cookie = new Cookie("pgc", request.getParameter("pgc"));
		} else { 
			cookie = new Cookie("pgc", String.valueOf(count));
		}
		cookie.setMaxAge(30);
		cookie.setPath("/depts");
		response.addCookie(cookie);
		
*/		
		request.setAttribute("sort", sort);
		request.setAttribute("pgc", count);
		request.setAttribute("menuLocation", "depts");
		List<DeptDTO> datas = null;
		if(search == null) {
			int pageNum = 1; //초기값 1
			if(page != null) {
				if(!page.isEmpty() && page.matches("\\d+")) { //페이지가 비어있지는 않은지, 숫자로구성되어 있는지
					pageNum = Integer.parseInt(page);
				}
			}
			if(sort != null) {
				System.out.println("sort가 null이 아닐때 실행을하는데? 지금 빈문자열이 들어온다." + sort);
				datas = service.getSortPage(pageNum, count, sort);
				System.out.println("service.getSortPage(pageNum, count, sort); 실행 체크");
			} else {
				datas = service.getPage(pageNum, count);
			}
			
			request.setAttribute("pageList", service.getPageNumberList(count));
			request.setAttribute("page", pageNum);
		} else {
			DeptDTO data = service.getDeptId(search);
			if(data != null) {
				datas = new ArrayList<DeptDTO>();
				datas.add(data);
			}
		}
		
		request.setAttribute("datas", datas);
		
		String view = "/WEB-INF/jsp/dept/index.jsp";
		request.getRequestDispatcher(view).forward(request, response);
	}

}
