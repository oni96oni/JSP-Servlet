package ajax.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emps.model.EmpsDTO;
import emps.service.EmpsService;

@WebServlet("/ajax/delete")
public class AjaxDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		
		String id = request.getParameter("id");
		
		EmpsService empsService = new EmpsService();
		EmpsDTO data = empsService.getId(id);
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		
		if(data == null) {
			sb.append(String.format("\"%s\": \"%s\",", "type", "fail"));
			sb.append(String.format("\"%s\": \"%s\" ", "message", "삭제하려는 데이터가 존재하지 않습니다."));
		} else {
			boolean result = empsService.removeId(data.getEmpId());
			if(result) {
				sb.append(String.format("\"%s\": \"%s\",", "type", "success"));
				sb.append(String.format("\"%s\": \"%s\" ", "message", "삭제되었습니다."));
			} else {
				sb.append(String.format("\"%s\": \"%s\",", "type", "fail"));
				sb.append(String.format("\"%s\": \"%s\" ", "message", "삭제 작업중 문제가 발생하였습니다."));
			}
		}
		
		sb.append("}");
		response.getWriter().print(sb.toString());
		response.getWriter().flush();
	}

}
