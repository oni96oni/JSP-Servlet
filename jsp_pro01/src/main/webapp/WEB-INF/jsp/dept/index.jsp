<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dept.model.DeptDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 조회 결과</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/default.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/form.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/navigation.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/paging.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/required.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/table.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/required.js"></script>
</head>

<body>
	<%@ include file="/WEB-INF/jsp/module/navigation.jsp" %>
	<section class="container">
		<div>
			<form action="./depts" method="get">
				<div class="input-form form-left">
					<button class="btn btn-outline" type="button" onclick="location.href='./depts/add'">추가</button>
				</div>
				<div class="input-form form-right">
					<input class="input-text" type="text" name="search">
					<button class="btn btn-outline" type="submit">조회</button>
				</div>
			</form>
		</div>
		<table class="table wide vertical-hidden hover">
			<colgroup>
				<col class="col-60">
				<col class="col-auto">
				<col class="col-60">
				<col class="col-60">
				<col class="col-120 ">
			</colgroup>
			<thead>
				<tr>
					<th>DeptId</th>
					<th>DeptName</th>
					<th>MngId</th>
					<th>LocId</th>
					<th class="border-hidden-right"></th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${not empty datas}">
				<c:forEach items="${datas}" var="data">
					<tr>
						<td>${data.deptId}</td>
						<td>${data.deptName}</td>
						<td>${data.mngId}</td>
						<td><a href="./locs?search=${data.locId}">${data.locId}</a></td>
						<td>
							<button class="btn btn-icon" type="button" onclick="location.href='./depts/mod?id=${data.deptId}'">
								<span class="material-symbols-outlined">edit</span>
							</button>
							<button class="btn btn-icon" type="button" onclick="location.href='./depts/del?id=${data.deptId}'">
								<span class="material-symbols-outlined">delete</span>
							</button>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
		<c:set var="currentPage" value="${page}" />
		<c:set var="pageList" value="${pageList}" />
		<c:if test="not empty pageList">
		<!-- 배열은 어떻게?? -->
		</c:if>
		<%
			if(request.getAttribute("pageList") != null) {
				List<Integer> pageList = (List<Integer>) request.getAttribute("pageList");
				int currentPage = (int)(request.getAttribute("page"));
		%>
			<div class="paging">
				<ul class="page center">
		<%
				if(currentPage - 1 > 0) {
		%>
		<c:if test="${currentPage -1 > 0}">
			<li class="page-item">
				<a class="page-link" href="./depts?page=<%=currentPage - 1 %>">Prev</a>
			</li>
		</c:if>
		<%
				}
				int i = currentPage - 1;
				int maxPage = i + 5 > pageList.size() ? pageList.size() : i + 5;
				for(; i < maxPage; i++) {
		%>
					<li class="page-item">
						<a class="page-link" href="./depts?page=<%=pageList.get(i) %>"><%=pageList.get(i) %></a>
					</li>
		<%
				}
				if(currentPage + 1 <= pageList.size()) {
		%>
					<li class="page-item">
						<a class="page-link" href="./depts?page=<%=currentPage + 1 %>">Next</a>
					</li>
		<%
				}
		%>
				</ul>
			</div>
		<%
			}
		%>
	</section>
</body>
</html>