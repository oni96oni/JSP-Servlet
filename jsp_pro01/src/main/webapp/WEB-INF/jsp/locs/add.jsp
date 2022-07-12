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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/default.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/form.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/navigation.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/paging.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/required.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/table.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/required.js"></script>
</head>
<body>
	<h1>지역 추가 화면</h1>
<%-- 
	<%	
		int locsId = 0;
		String strAdd = "", posCode = "", city = "", staPro = "", conId="";
		if(request.getAttribute("error") != null) {
			LocsDTO data = (LocsDTO) request.getAttribute("data");
			locsId = data.getLocsId();
			strAdd = String.valueOf(data.getStrAdd());
			posCode = String.valueOf(data.getPosCode());
			city = String.valueOf(data.getCity());
			staPro = String.valueOf(data.getStaPro());
			conId = String.valueOf(data.getConId());
	%>
			<script type="text/javascript">
				alert("<%=request.getAttribute("errorMsg") %>");
			</script>
	<%
		}
	%>
--%>
	<section class="container">
		<form class="small-form" action="./add" method="post">
			<div class="input-form wide">
				<label class="input-label">지역ID</label>
				<input class="input-text" type="text" name="locsId" value="${data.locsId}">
				<c:if test="${errorCode eq 'locsId'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
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
				<input class="input-text" type="text" name="conId" value="${data.conId}">
				<c:if test="${errorCode eq 'conId'}">
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