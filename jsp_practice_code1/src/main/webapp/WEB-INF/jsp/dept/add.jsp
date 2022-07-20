<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			deptId: value
		},
		dataType: "json",
		success: function(data, status) {
			var form = document.forms[0];
			if(data.errCode === "duplicate") {
				var label = document.createElement("label");
				label.setAttribute("class", "input-label-error");
				label.innerText = data.errMessage;
				
				if(form.deptId.nextElementSibling === null) {
					form.deptId.after(label);
				}
			} else {
				if(form.deptId.nextElementSibling !== null) {
					form.deptId.nextElementSibling.remove();
				}
			}
		}
	});
}

function existsCheck(name, value) {
	$.ajax({
		type: "get",
		url: "/ajax/existsCheck",
		data: {
			name: name,
			value: value
		},
		dataType: "json",
		success: function(data, status) {
			var form = document.forms[0];
			if(data.errCode === "notExists") {
				if(form[name].nextElementSibling === null) {					
					var label = document.createElement("label");
					label.setAttribute("class", "input-label-error");
					label.innerText = data.errMessage;
					form[name].after(label);
				} else {
					form[name].nextElementSibling.setAttribute("class", "input-label-error");
					form[name].nextElementSibling.innerText = data.errMessage;
				}
			} else if(data.errCode == "exists") {
				if(form[name].nextElementSibling === null) {	
					var label = document.createElement("label");
					label.setAttribute("class", "input-label-ok");
					label.innerText = data.errMessage;
					form[name].after(label);
				} else {
					form[name].nextElementSibling.setAttribute("class", "input-label-ok");
					form[name].nextElementSibling.innerText = data.errMessage;
				}
			}
		}
	});
}
</script>
<body>
	<section class="container">
		<form class="small-form" action="./add" method="post">
			<div class="input-form wide">
				<label class="input-label">부서ID</label>
				<input class="input-text" type="text" name="deptId" onchange="dupCheck(this.value);" value="${data.deptId}">
			</div>
			<div class="input-form wide">
				<label class="input-label">부서명</label>
				<input class="input-text" type="text" name="deptName" value="${data.deptName}">
			</div>
			<div class="input-form wide">
				<label class="input-label">매니저ID</label>
				<input class="input-text" type="text" name="mngId" onchange="existsCheck(this.name, this.value);" value="${data.mngId}">
			</div>
			<div class="input-form wide">
				<label class="input-label">지역코드</label>
				<input class="input-text" type="text" name="locId" onchange="existsCheck(this.name, this.value);" value="${data.locId}">
			</div>
			<div class="input-form wide form-right">
				<button class="btn btn-outline btn-ok" type="submit">저장</button>
				<button class="btn btn-outline btn-cancel" type="button" onclick="location.href='../depts'">취소</button>
			</div>
		</form>
	</section>
</body>
</html>