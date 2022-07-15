<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="emps.model.EmpsDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 추가</title>
	<%@ include file="../module/head.jsp" %>
</head>
<script type="text/javascript">
function dupCheck(value) {
	$.ajax({
		type: "get",
		url: "/ajax/dupCheck",
		data: {
			empId: value
		},
		dataType: "json",
		success: function(data, status) {
			var form = document.forms[0];
			if(data.errCode === "duplicate") {
				var label = document.createElement("label");
				label.setAttribute("class", "input-label-error");
				label.innerText = data.errMessage;
				
				if(form.empId.nextElementSibling === null) {
					form.empId.after(label);
				}
			} else {
				if(form.empId.nextElementSibling !== null) {
					form.empId.nextElementSibling.remove();
				}
			}
		}
	});
}
</script>
<body>
	<h1>직원 추가 화면</h1>
	<section class="container">
		<form class="small-form" action="./add" method="post">
			<div class="input-form wide">
				<label class="input-label">직원ID</label>
				<input class="input-text" type="text" name="empId" onchange="dupCheck(this.value);" value="${data.empId}">
			</div>
			<div class="input-form wide">
				<label class="input-label">직원 이름</label>
				<input class="input-text" type="text" name="empName" value="${data.empName}">
				<c:if test="${errorCode eq 'empName'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">이메일</label>
				<input class="input-text" type="text" name="email" value="${data.email}">
				<c:if test="${errorCode eq 'email'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">직업명</label>
				<input class="input-text" type="text" name="jobName" value="${data.jobName}">
				<c:if test="${errorCode eq 'jobName'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">직업ID</label>
				<input class="input-text" type="text" name="jobId" value="${data.jobId}">
				<c:if test="${errorCode eq 'jobId'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">부서이름</label>
				<input class="input-text" type="text" name="deptName" value="${data.deptName}">
				<c:if test="${errorCode eq 'deptName'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">부서ID</label>
				<input class="input-text" type="text" name="deptId" value="${data.deptId}">
				<c:if test="${errorCode eq 'deptId'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
			</div>
			<div class="input-form wide form-right">
				<button class="btn btn-ouitline btn-ok" type="submit">저장</button>
				<button class="btn btn-ouitline btn-cancel" type="button" onclick="location.href='../emps'">취소</button>
			</div>
		</form>
	</section>
</body>
</html>