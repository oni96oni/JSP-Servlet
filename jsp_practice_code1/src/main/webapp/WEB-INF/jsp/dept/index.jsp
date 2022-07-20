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
	<%@ include file="../module/head.jsp" %>
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
					<select class="select-form" onchange="location.href='./depts?pgc=' + this.value">
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
				<col class="col-60">
				<col class="col-auto">
				<col class="col-60">
				<col class="col-60">
				<col class="col-120 ">
			</colgroup>
			<thead>
				<tr>
					<th class="${sort == 'deptId' ? 'sort-desc' : ''}"
					onclick="location.href='./depts?page=${page}&sort=deptId'">DeptId
					</th>
					<th class="${sort == 'deptName' ? 'sort-desc' : ''}"
					onclick="location.href='./depts?page=${page}&sort=deptName'">DeptName
					</th>
					<th class="${sort == 'mngId' ? 'sort-desc' : ''}"
					onclick="location.href='./depts?page=${page}&sort=mngId'">MngId
					</th>
					<th class="${sort == 'locId' ? 'sort-desc' : ''}"
					onclick="location.href='./depts?page=${page}&sort=locId'">LocId
					</th>
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
						<td class="border-hidden-right">
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
	</section>
	<%@ include file="/WEB-INF/jsp/module/paging.jsp" %>
</body>
</html>