<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dept.model.DeptDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>삭제 확인</title>
</head>
<body>
	<%
		if(request.getAttribute("data") != null) {
			DeptDTO data = (DeptDTO) request.getAttribute("data");
	%>
			<ul>
				<li>부서 코드 : <%=data.getDeptId() %></li>
				<li>부서 이름 : <%=data.getDeptName() %></li>
			</ul>
			<div>
				<button type="submit" form="deleteForm">삭제</button>
				<button type="button" onclick="history.back();">취소</button>
			</div>
			<form id="deleteForm" action="./del" method="post">
				<input type="hidden" name="deptId" value="<%=data.getDeptId() %>">
			</form>
	<%
		} else {
			if(request.getAttribute("errorMsg") == null) {
	%>
				<p>요청한 자료는 이미 삭제 되었거나 존재하지 않습니다.</p>
	<%
			} else {
				String errorMsg = (String)request.getAttribute("errorMsg");
	%>
				<p><%=errorMsg %></p>
	<%
			}
	%>
			<div>
				<button type="button" onclick="location.href='../depts';">돌아가기</button>
			</div>
	<%
		}
	%>
</body>
</html>