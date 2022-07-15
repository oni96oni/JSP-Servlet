<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="locs.model.LocsDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>지역 추가</title>
	<%@ include file="../module/head.jsp" %>
</head>
<script type="text/javascript">
function dupCheck(value) {
	$.ajax({
		type: "get",
		url: "/ajax/dupCheck",
		data: {
			locsId: value
		},
		dataType: "json",
		success: function(data, status) {
			var form = document.forms[0];
			if(data.errCode === "duplicate") {
				var label = document.createElement("label");
				label.setAttribute("class", "input-label-error");
				label.innerText = data.errMessage;
				
				if(form.locsId.nextElementSibling === null) {
					form.locsId.after(label);
				}
			} else {
				if(form.locsId.nextElementSibling !== null) {
					form.locsId.nextElementSibling.remove();
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
				<label class="input-label">지역ID</label>
				<input class="input-text" type="text" name="locsId" onchange="dupCheck(this.value);" value="${data.locsId}">
			</div>
			<div class="input-form wide">
				<label class="input-label">지역 주소</label>
				<input class="input-text" type="text" name="strAdd" value="${data.strAdd}">
				<c:if test="${errorCode eq 'strAdd'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">우편 번호</label>
				<input class="input-text" type="text" name="posCode" value="${data.posCode}">
				<c:if test="${errorCode eq 'posCode'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">도시</label>
				<input class="input-text" type="text" name="city" value="${data.city}">
				<c:if test="${errorCode eq 'city'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">지역명</label>
				<input class="input-text" type="text" name="staPro" value="${data.staPro}">
				<c:if test="${errorCode eq 'staPro'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">국가ID</label>
				<input class="input-text" type="text" name="conId" onchange="existsCheck(this.name, this.value);" value="${data.conId}">
			</div>
			<div class="input-form wide form-right">
				<button class="btn btn-ouitline btn-ok" type="submit">저장</button>
				<button class="btn btn-ouitline btn-cancel" type="button" onclick="location.href='../emps'">취소</button>
			</div>
		</form>
	</section>
</body>
</html>