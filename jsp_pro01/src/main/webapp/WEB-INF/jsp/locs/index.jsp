<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*, locs.model.LocsDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>지역 조회 결과</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/default.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/form.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/navigation.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/paging.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/required.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/css/table.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/required.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/module/navigation.jsp" %>
	<section class="container">
		<div>
			<form action="./locs" method="get">
				<div class="input-form form-right">
					<input class="input-text" type="text" name="search">
					<button class="btn btn-outline" type="submit">조회</button>
				</div>
			</form>
		</div>
		<table class="table wide vertical-hidden hover">
			<colgroup>
				<col class="col-120">
				<col class="col-auto">
				<col class="col-120">
				<col class="col-120">
				<col class="col-180">
				<col class="col-120">
			</colgroup>
			<thead>
				<tr>
					<th>Location ID</th>
					<th>Street Address</th>
					<th>Postal Code</th>
					<th>City</th>
					<th>State Province</th>
					<th>Country Id</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty datas}">
					<c:forEach items="${datas}" var="data">
						<tr>
							<td>${data.locId}</td>
							<td>${data.stAddr}</td>
							<td>${data.postal}</td>
							<td>${data.city}</td>
							<td>${data.state}</td>
							<td>${data.ctyId}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</section>
</body>
</html>