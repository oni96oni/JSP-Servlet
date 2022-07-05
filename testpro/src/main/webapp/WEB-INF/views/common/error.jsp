<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러</title>
</head>
<body>
	<% 
		if(request.getAttribute("message") != null) {
	%>
		<h1>게시글 등록 실패!</h1>
	<%
		}
	%>
</body>
</html>