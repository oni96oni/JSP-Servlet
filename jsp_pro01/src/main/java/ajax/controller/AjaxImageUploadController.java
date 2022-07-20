package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import emps.model.EmpsDTO;

@WebServlet("/ajax/imageUpload")
@MultipartConfig
public class AjaxImageUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		EmpsDTO empsData = (EmpsDTO)session.getAttribute("loginData");
		
		Part part = request.getPart("uploadImage");
		
		if(!part.getSubmittedFileName().isEmpty()) {
			String realPath = request.getServletContext().getRealPath("/static/img/emp/");
			part.write(realPath + empsData.getEmpId() + ".png");
			
			out.println("{");
			out.println("    \"src\": \"/static/img/emp/" + empsData.getEmpId() + ".png\"");
			out.println("}");
			out.flush();
		}
	}

}
