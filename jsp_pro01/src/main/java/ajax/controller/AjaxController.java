package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emps.service.EmpsService;

@WebServlet("/ajax/check")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		
		String checkName = request.getParameter("checkName");
		
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		PrintWriter out = response.getWriter();
		switch(checkName) {
			case "jobRange":
				String id = request.getParameter("id");
				Map<String, Integer> range = new EmpsService().getSalaryRange(id);
				sb.append(String.format("\"%s\": %d,", "min", range.get("minSalary")));
				sb.append(String.format("\"%s\": %d", "max", range.get("maxSalary")));
				break;
		}
		sb.append("}");
		out.print(sb.toString());
		out.flush();
	}
}
