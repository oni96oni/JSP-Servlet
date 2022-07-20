<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dept.model.DeptDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 수정</title>
	<link herf="<%=request.getContextPath() %>/static/css/default.css">
	<script src="<%=request.getContextPath() %>/static/js/default.js"></script>
</head>
<body>
	<h1>부서 수정 화면</h1>
	<%
		String deptId = "", deptName = "", mngId = "", locId = "";
		DeptDTO data = (DeptDTO)request.getAttribute("data");// 이때 type이 Object 이기 때문에 반드시 형변환을 해줘야 한다.
		deptId = String.valueOf(data.getDeptId());
		deptName = data.getDeptName();
		mngId = String.valueOf(data.getMngId());
		locId = String.valueOf(data.getLocId());
		
		if(request.getAttribute("error") != null) {
	%>
			<script type="text/javascript">
				alert("<%=request.getAttribute("errorMsg") %>");
			</script>
	<%
		}
	%>
	<form action="./mod" method="post">
		<input type="hidden" name="deptId" value="<%=deptId %>" readonly>
		<div>
			<input type="text" name="deptName" value="<%=deptName %>" placeholder="부서명">
		</div>
		<div>
			<input type="text" name="mngId" value="<%=mngId %>" placeholder="매니저 ID">
		</div>
		<div>
			<input type="text" name="locId" value="<%=locId %>" placeholder="지역 코드">
		</div>
		<div>
			<button type="submit">저장</button>
		</div>
	</form>
</body>
</html>