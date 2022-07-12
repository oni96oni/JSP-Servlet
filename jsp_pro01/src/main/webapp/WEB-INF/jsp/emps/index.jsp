<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, emps.model.EmpsDTO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<meta charset="UTF-8">
	<title>직원 조회 결과</title>
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
			<form action="./emps" method="get">
				<div class="input-form form-left">
					<button class="btn btn-outline" type="button" onclick="location.href='./emps/add'">추가</button>
				</div>
				<div class="input-form form-right">
					<input class="input-text" type="text" name="search">
					<button class="btn btn-outline" type="submit">조회</button>
					<select class="select-form" onchange="location.href='./emps?pgc=' + this.value">
						<option value="5" ${pgc == 5 ? 'selected' : '' }>5 개</option>
						<option value="10" ${pgc == 10 ? 'selected' : '' }>10 개</option>
						<option value="15" ${pgc == 15 ? 'selected' : '' }>15 개</option>
						<option value="20" ${pgc == 20 ? 'selected' : '' }>20 개</option>
					</select>
				</div>
			</form>
		</div>
		<table class="table wide vertical-hidden hover">
			<colgroup>
				<col class="col-120">
				<col class="col-240">
				<col class="col-240">
				<col class="col-240">
				<col class="col-240 ">
				<col class="col-120 ">
			</colgroup>
			<thead>
				<tr>
					<th class="${sort == 'empId' ? 'sort-desc' : ''}"
					onclick="location.href='./emps?page=${page}&sort=empId'">직원ID
					</th>
					<th class="${sort == 'empName' ? 'sort-desc' : ''}"
					onclick="location.href='./emps?page=${page}&sort=empName'">이름
					</th>
					<th class="${sort == 'email' ? 'sort-desc' : ''}"
					onclick="location.href='./emps?page=${page}&sort=email'">이메일
					</th>
					<th class="${sort == 'jobName' ? 'sort-desc' : ''}"
					onclick="location.href='./emps?page=${page}&sort=jobName'">직급
					</th>
					<th class="${sort == 'deptName' ? 'sort-desc' : ''}"
					onclick="location.href='./emps?page=${page}&sort=deptName'">부서
					</th>
					<th class="border-hidden-right"></th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${not empty datas}">
				<c:forEach items="${datas}" var="data">
					<tr>
						<td>${data.empId}</td>
						<td>${data.empName}</td>
						<td>${data.email}</td>
						<td>${data.jobName}</td>
						<td>${data.deptName}</td>
						<td class="border-hidden-right">
							<button class="btn btn-icon" type="button" onclick="location.href='./emps/mod?id=${data.empId}'">
								<span class="material-symbols-outlined">edit</span>
							</button>
							<button class="btn btn-icon" type="button" onclick="location.href='./emps/del?id=${data.empId}'">
								<span class="material-symbols-outlined">delete</span>
							</button>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			</tbody>
		</table>
		<c:if test="${not empty pageList}">
			<c:set var="pageList" value="${pageList}" />
			<c:set var="currentPage" value="${page}" />
				<div class="paging">
					<ul class="page center">
						<c:if test="${currentPage - 1 > 0 }">
							<li class="page-item">
							<a class="page-link" href="./emps?page=${currentPage - 1}">Prev</a>
							</li>
						</c:if>
						<c:set var="i" value="${currentPage - 1}"/>
						<c:set var="maxPage" value="${i+5 > pageList.size() ? pageList.size() : i + 5}" />
						<c:forEach begin="${i}" end="${maxPage - 1}" var="num">
							<li class="page-item">
								<a class="page-link" href="./emps?page=${pageList.get(num)}">${pageList.get(num)}></a>
							</li>
						</c:forEach>
						<c:if test="${currentPage + 1 <= pageList.size()}">
							<li class="page-item">
								<a class="page-link" href="./emps?page=${currentPage + 1}">Next</a>
							</li>
						</c:if>
					</ul>
				</div>
		</c:if>
	</section>
</body>
</html>