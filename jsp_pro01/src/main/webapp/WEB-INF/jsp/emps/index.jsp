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
	<%@ include file="../module/head.jsp" %>
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
						<td><a href="./empinfo?empId=${data.empId}">${data.empId}</a></td>
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
	</section>
	<%@ include file="/WEB-INF/jsp/module/paging.jsp" %>
</body>
</html>