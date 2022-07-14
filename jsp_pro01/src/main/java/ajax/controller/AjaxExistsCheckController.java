package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/ajax/existsCheck")
public class AjaxExistsCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService deptService = new DeptService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String value = request.getParameter("value");
		
		response.setContentType("application/json; charset=utf-8");
		boolean data = false;
		String errMessage = "";
		if(name != null) {
			switch(name) {
				case "mngId":
					data = deptService.existsManager(value);
					errMessage = "    \"errMessage\": \"해당 관리자 ID는 존재하지 않습니다.\"";
					break;
				case "locId":
					data = deptService.existsLocation(value);
					errMessage = "    \"errMessage\": \"해당 지역 ID는 존재하지 않습니다.\"";
					break;
			}
			
			PrintWriter out = response.getWriter();
			if(!data) {
				out.println("{");
				out.println("    \"errCode\": \"notExists\",");
				out.println(errMessage);
				out.println("}");
				out.flush();
			} else {
				out.println("{");
				out.println("    \"errCode\": \"exists\",");
				out.println("    \"errMessage\": \"정상.\"");
				out.println("}");
				out.flush();
			}
		}
	}

}
