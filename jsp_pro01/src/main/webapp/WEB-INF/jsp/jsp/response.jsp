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
		브라우저에게 전송하는 데이터의 컨텐츠 종류를 알려주어 해당 컨텐츠로 인식하고 해석하게 만든다.
	</p>
	<%
		response.setContentType("text/html");
		// response.setContentType("text/javascript");
	%>
	<hr>
	<h2>setStatus()</h2>
	<%
		// response.setStatus(HttpServletResponse.SC_OK);
		// response.setStatus(HttpServletResponse.SC_CREATED);
		// response.setStatus(HttpServletResponse.SC_ACCEPTED);
		// response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	%>
	<p>
		개발자 모드에서 Network 탭의 Status 항목에서 변경된 상태 코드 확인 가능<br>
		HTTP 상태 코드는 크게 2xx 번대, 3xx 번대, 4xx 번대, 5xx 번대로 분류<br>
		2xx : 정상 응답<br>
		3xx : 리다이렉트(다른 페이지로 재요청하시오.)<br>
		4xx : 요청 오류<br>
		5xx : 서버 오류<br>
	</p>
	<hr>
	<h2>sendError()</h2>
	<%
		// response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		// response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 요청입니다.");
	%>
	<p>
		setStatus() 와 동일한 기능을 수행하나. 별도의 에러 페이지를 출력하기 위해서 사용하는 메서드로<br>
		사용하면 됨.
	</p>
	<hr>
	<h2>sendRedirect()</h2>
	<%
		// response.sendRedirect("/jsp01/main");
	%>
</body>
</html>