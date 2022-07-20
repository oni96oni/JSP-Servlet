<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %><!-- 지시자(page, include: 다른 파일 불러 올때, taglib:EL/JSTL이라는 라이브러리 등록하면 편하게 사용 할 수있음), jsp설정정보-->
<%! 
	private String name = "Hello";

	public String hello() {
		return "Hello";
	}
%><!-- 선언문, 주로 멤버변수가 들어간다.-->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSP - Script Tag</title>
</head>
<body>
	<h1>JSP - Script Tag</h1>
	<ul>
	<%
		for(int i=0; i<5; i++) {
			Random rd = new Random();
	%>
		<li><%=rd.nextInt(100) %></li>
	<%
		}
	%> <!-- script 릿태그? 주로 지역변수가 들어간다. WAS에서 실행되고 실행된 결과만을 HTML로 만들어서 RETURN하기 때문에 페이지 소스보기해도 소스를 볼 수 없다-->
	</ul>
	<input type="text" value="<%=name%>"><br> <!-- 표현식 -->
	<input type="text" value="<%=hello()%>"><br>
</body>
</html>

<%--주석은 두가지 방식 , JSP에는 지시자, 선언문, 릿, 표현식 태그들이 있으며 이안에 서는 //로 주석 사용 가능 --%>