package dept.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/depts")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search"); // 사용자가 입력한 데이터 저장
		System.out.println(search);
		
		List<DeptDTO> datas = null;
		
		if(search == null || search == "") {
			datas = service.getAll();
		} else {
			DeptDTO data = service.getDeptId(search);
			if(data != null) {
				datas = new ArrayList<DeptDTO>();
				datas.add(data);
			}
		}
		
		request.setAttribute("datas", datas); // (속성명, 속성의 값)
		
		String view = "/WEB-INF/jsp/dept/index.jsp"; // 이 view로 화면을 구성 하겠다.
		request.getRequestDispatcher(view).forward(request, response); // forward가 정보를 jsp에다가 전달한다.
	}
}
/*  
    Servlet에서 JSP로 자료를 전달할때 request객체가 사용된다.
	setAttribute를하면 Object로 upcasting된다. 그래서 받아서 사용할때(getAttribute) downcasting을 해주어야 한다.
	여기서는 dept/index.jsp확인
 */