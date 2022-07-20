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
	<%@ include file="../module/head.jsp" %>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/module/navigation.jsp" %>
	<section class="container">
		<div>
			<form action="./locs" method="get">
				<div class="input-form form-left">
					<button class="btn btn-outline" type="button" onclick="location.href='./locs/add'">추가</button>
				</div>
				<div class="input-form form-right">
					<input class="input-text" type="text" name="search">
					<button class="btn btn-outline" type="submit">조회</button>
					<select class="select-form" onchange="location.href='./locs?pgc=' + this.value">
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
				<col class="col-auto">
				<col class="col-120">
				<col class="col-120">
				<col class="col-180">
				<col class="col-120">
				<col class="col-120">
			</colgroup>
			<thead>
				<tr>
					<th class="${sort == 'locsId' ? 'sort-desc' : ''}"
					onclick="location.href='./locs?page=${page}&sort=locsId'">Location ID</th>
					<th class="${sort == 'strAdd' ? 'sort-desc' : ''}"
					onclick="location.href='./locs?page=${page}&sort=strAdd'">Street Address</th>
					<th class="${sort == 'posCode' ? 'sort-desc' : ''}"
					onclick="location.href='./locs?page=${page}&sort=posCode'">Postal Code</th>
					<th class="${sort == 'city' ? 'sort-desc' : ''}"
					onclick="location.href='./locs?page=${page}&sort=city'">City</th>
					<th class="${sort == 'staPro' ? 'sort-desc' : ''}"
					onclick="location.href='./locs?page=${page}&sort=staPro'">State Province</th>
					<th class="${sort == 'conId' ? 'sort-desc' : ''}"
					onclick="location.href='./locs?page=${page}&sort=conId'">Country Id</th>
					<th class="border-hidden-right"></th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty datas}">
					<c:forEach items="${datas}" var="data">
						<tr>
							<td>${data.locsId}</td>
							<td>${data.strAdd}</td>
							<td>${data.posCode}</td>
							<td>${data.city}</td>
							<td>${data.staPro}</td>
							<td>${data.conId}</td>
							<td class="border-hidden-right">
								<button class="btn btn-icon" type="button" onclick="location.href='./locs/mod?id=${data.locsId}'">
									<span class="material-symbols-outlined">edit</span>
								</button>
								<button class="btn btn-icon" type="button" onclick="location.href='./locs/del?id=${data.locsId}'">
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