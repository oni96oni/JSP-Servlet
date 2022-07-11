<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="emps.model.EmpsDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원 수정</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/default.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/form.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/navigation.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/paging.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/required.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/table.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/required.js"></script>
</head>
<body>
	<h1>직원 수정 화면</h1>
	<%
		String empId = "", empName = "", email = "", jobName = "", jobId = "", deptName = "", deptId = "";
		EmpsDTO data = (EmpsDTO)request.getAttribute("data");// 이때 type이 Object 이기 때문에 반드시 형변환을 해줘야 한다.
		empId = String.valueOf(data.getEmpId());
		empName = data.getEmpName();
		email = data.getEmail();
		jobName = data.getJobName();
		jobId = data.getJobId();
		deptName = data.getDeptName();
		deptId = String.valueOf(data.getDeptId());
		
		if(request.getAttribute("error") != null) {
	%>
			<script type="text/javascript">
				alert("<%=request.getAttribute("errorMsg") %>");
			</script>
	<%
		}
	%>
	<form action="./mod" method="post">
		<input type="hidden" name="empId" value="<%=empId %>" readonly>
		<div>
			<input type="text" name="empName" value="<%=empName %>" placeholder="직원 이름">
		</div>
		<div>
			<input type="text" name="email" value="<%=email %>" placeholder="이메일">
		</div>
		<div>
			<input type="text" name="jobName" value="<%=jobName %>" placeholder="직업명">
		</div>
		<div>
			<input type="text" name="jobId" value="<%=jobId %>" placeholder="직업 ID">
		</div>
		<div>
			<input type="text" name="deptName" value="<%=deptName %>" placeholder="부서명">
		</div>
		<div>
			<input type="text" name="deptId" value="<%=deptId %>" placeholder="부서 ID">
		</div>
		<div>
			<button type="submit">저장</button>
		</div>
	</form>
</body>
</html>