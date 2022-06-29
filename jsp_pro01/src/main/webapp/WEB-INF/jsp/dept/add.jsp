<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 추가</title>
</head>
<body>
	<h1>부서 추가 화면</h1>
	<form action="./add" method="post">
		<div>
			<input type="text" name="deptId" value="<%=request.getParameter("deptId") %>" placeholder="부서코드">
		</div>
		<div>
			<input type="text" name="deptName" value="<%=request.getParameter("deptName") %>" placeholder="부서명">
		</div>
		<div>
			<input type="text" name="mngId" value="<%=request.getParameter("mngId") %>" placeholder="매니저코드">
		</div>
		<div>
		<%
				if(request.getParameter("locId") != null) {
			%>
				<input type="text" name="locId" value="<%=request.getParameter("locId") %>" placeholder="지역 코드">
			<%
				} else {
			%>
				<input type="text" name="locId" value="" placeholder="지역 코드">
			<%
				}
			%>
		</div>
		<div>
			<button type="submit">저장</button>
		</div>
	</form>
	<%
		if(request.getAttribute("error") != null) {
	%>
		<script type="text/javascript">
			alert("데이터 저장 처리중 에러 발생!");
		</script>
	<% 		
		}
	%>
</body>
</html>