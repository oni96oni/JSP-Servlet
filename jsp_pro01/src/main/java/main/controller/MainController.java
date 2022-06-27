package main.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/jsp/index.jsp"; //jsp파일의 경로
		request.getRequestDispatcher(view).forward(request, response);
	}
}

/*
	비즈니스 로직->servlet 화면 구현->jsp
	
	jsp파일을 보여주는것은
	JSP 파일을 제공하는 것이 아니라, JSP 파일을 실행해서 얻어진 결과 (HTML 코드)를 제공.
	
	jsp실행과정요약
	클라이언트요청 -> WEB Container에 JSP파일이 등록이 되어있나? 확인 되어 있으면 그것을 반환
			안되어 있으면 jsp파일을 jsp.java로 변환 이 파일을 class로 컴파일 한뒤 로드해서 Web Container에 등록
	
	client -> server -> WAS -> servlet -> jsp -> 실행결과 , 이 실행결과가 사용자에게 반환된다.
	
	servlet이 GET,POST요청인지 구분한다.
	
	
 */
