<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSP - response</title>
</head>
<body>
	<h1>JSP - response</h1>
	<h2>setContentType()</h2>
	<p>
		브라우저에게 전송하는 데이터의 컨텐츠 종류를 알려주어 해당 컨텐츠로 인식하고 해석하게 만든다
	</p>
	<%
		response.setContentType("text/html");
		//response.setContentType("text/javascript");
	%>
	<hr>
	<h2>setStatus()</h2>
	<%
		//response.setStatus(HttpServletResponse.SC_OK);
		//response.setStatus(HttpServletResponse.SC_CREATED);
		//response.setStatus(HttpServletResponse.SC_ACCEPTED);
		//response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	%>
	<hr>
	<p>
		개발자 모드에서 Network 탭에 상태코드에서 원하는 상태코드로 보여줄 수 있게 설정 가능하다.<br>
		HTTP 상태 코드는 크게 2xx 번대(성공), 3xx 번대(리다이렉트), 4xx 번대(실패:클라이언트문제), 5xx 번대(실패:서버문제)
	</p>
	<h2>sendError()</h2>
	<%
		//response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		//response.sendError(HttpServletResponse.SC_BAD_REQUEST,"잘못된 요청입니다.");
	%>
	<p>
		setStatus() 와 동일한 기능을 수행하나. 별도의 에러페이지를 출력하기 위해서 사용하는 메서드로<br>
		사용하면 됨.
	</p>
	<hr>
	<h2>sendRedirect()</h2>
	<%
		//response.sendRedirect("/jsp01/main");
	%>
</body>
</html>