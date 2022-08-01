<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/default.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/form.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/navigation.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/paging.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/required.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/table.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/required.js"></script>
</head>
<body>
	<section class="container">
		<div>
			<c:url var="boardUrl" value="/board" />
			<form action="${boardUrl}" method="get">
				<div class="input-form form-left">
					<button class="btn btn-outline" type="button" onclick="location.href='${boardUrl}/add'">추가</button>
				</div>
				<div class="input-form form-right">
					<input class="input-text" type="text" name="search">
					<button class="btn btn-outline" type="submit">조회</button>
					<select class="select-form" onchange="location.href='${boardUrl}?pgc=' + this.value">
						<option value="5" ${pgc == 5 ? 'selected' : ''}>5 개</option>
						<option value="10" ${pgc == 10 ? 'selected' : ''}>10 개</option>
						<option value="15" ${pgc == 15 ? 'selected' : ''}>15 개</option>
						<option value="20" ${pgc == 20 ? 'selected' : ''}>20 개</option>
					</select>
				</div>
			</form>
		</div>
		<table class="table wide vertical-hidden hover">
			<colgroup>
				<col class="col-120">
				<col class="col-240">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
				<col class="col-120">
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>추천수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty datas}">
					<c:forEach items="${datas.pageData}" var="data">
						<c:url var="boardDetailUrl" value="/board/detail">
							<c:param name="id">${data.id}</c:param>
						</c:url>
						<tr onclick="location.href='${boardDetailUrl}'">
							<td>${data.id}</td>
							<td>${data.title}</td>
							<td>${data.empName}</td>
							<td>${data.viewCnt}</td>
							<td>${data.like}</td>
							<td>${data.createDate}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</section>
</body>
</html>