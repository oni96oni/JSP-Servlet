<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%!
	// 멤버 변수
	private String name = "Hello";
	
	// 메서드
	public String hello() {
		return "Hello";
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSP - Script Tag</title>
</head>
<body>
	<%-- 주석 --%>
	<h1>JSP - Script Tag</h1>
	<ul>
	<%
		// Java 주석
		for(int i = 0; i < 5; i++) {
			Random rd = new Random();
	%>
		<li><%=rd.nextInt(100) %></li>
	<%
		}
	%>
	</ul>
	<br>
	<input type="text" value="<%=name %>"><br>
	<input type="text" value="<%=hello() %>">
</body>
</html>