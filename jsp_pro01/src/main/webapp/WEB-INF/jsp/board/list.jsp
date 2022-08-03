<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판</title>
	<link rel="stylesheet" type="text/css" href="/static/bs5/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
	<script type="text/javascript" src="/static/bs5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/static/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<header class="mb-3"></header>
	<section class="container">
		<div class="mb-1">
			<c:url var="boardUrl" value="/board" />
			<form action="${boardUrl}" method="get">
				<div class="row g-1">
					<div class="col-8">
						<button class="btn btn-secondary" type="button" onclick="location.href='${boardUrl}/add'">추가</button>
					</div>
					<div class="col-3">
						<div class="input-group">
							<input class="form-control" type="text" name="search">
							<button class="btn btn-secondary" type="submit">조회</button>
						</div>
					</div>
					<div class="col-1">
						<select class="form-select" onchange="location.href='${boardUrl}?pgc=' + this.value">
							<option value="5" ${pgc == 5 ? 'selected' : ''}>5 개</option>
							<option value="10" ${pgc == 10 ? 'selected' : ''}>10 개</option>
							<option value="15" ${pgc == 15 ? 'selected' : ''}>15 개</option>
							<option value="20" ${pgc == 20 ? 'selected' : ''}>20 개</option>
						</select>
					</div>
				</div>
			</form>
		</div>
		<table class="table table-hover">
			<colgroup>
				<col class="col-1">
				<col class="col-auto">
				<col class="col-2">
				<col class="col-1">
				<col class="col-1">
				<col class="col-2">
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
		<nav>
			<div>
				<ul class="pagination justify-content-center">
					<c:if test="${datas.hasPrevPage()}">
						<li class="page-item">
							<a class="page-link" href="${boardUrl}?page=${datas.prevPageNumber}">Prev</a>
						</li>
					</c:if>
					<c:forEach items="${datas.getPageNumberList(datas.currentPageNumber - 2, datas.currentPageNumber + 2)}" var="num">
						<li class="page-item ${datas.currentPageNumber eq num ? 'active' : ''}">
							<a class="page-link" href="${boardUrl}?page=${num}">${num}</a>
						</li>
					</c:forEach>
					<c:if test="${datas.hasNextPage()}">
						<li class="page-item">
							<a class="page-link" href="${boardUrl}?page=${datas.nextPageNumber}">Next</a>
						</li>
					</c:if>
				</ul>
			</div>
		</nav>
	</section>
</body>
</html>