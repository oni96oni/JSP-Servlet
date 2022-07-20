<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>내 정보</title>
	<%@ include file="../module/head.jsp" %>
</head>
<script type="text/javascript">
window.onload = function() {
	previewImg.addEventListener("click", function(e) {
		imgSelect.click();
	});
	
	imgSelect.addEventListener("change", ajaxImageUpload);
}

function showImagePreview(e) {
	var file = e.target.files[0];
	var imgUrl = URL.createObjectURL(file);
	previewImg.src = imgUrl;
}

function ajaxImageUpload(e) {
	var file = e.target.files[0];
	var fData = new FormData();
	fData.append("uploadImage", file, file.name);
	
	$.ajax({
		type: "post",
		enctype: "multipart/form-data",
		url: "/ajax/imageUpload",
		data: fData,
		processData: false,
		contentType: false,
		success: function(data, status) {
			previewImg.src = data.src;
		}
	});
}
</script>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<c:url var="myInfoUpdateUrl" value="/myinfo" />
		<form class="large-form" action="${myInfoUpdateUrl}" method="post" enctype="multipart/form-data">
			<div class="image-form left">
				<img id="previewImg" class="image-360" alt="여기에는 증명 사진이 배치됩니다." src="${imagePath}">
				<br>
				<input id="imgSelect" type="file" name="uploadImg" value="이미지 선택" accept="image/png">
				<c:if test="${not empty imageError}">
					<label class="input-label-error">${imageError}</label>
				</c:if>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">ID</label>
					<input class="input-text w-auto" type="text" name="empId" value="${sessionScope.loginData.empId}" disabled>
				</div>
				<div class="input-form">
					<label class="input-label w-100">이름</label>
					<input class="input-text w-auto" type="text" name="empName" value="${sessionScope.loginData.empName}" disabled>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">직급</label>
					<select class="select-form w-auto" name="jobId" disabled>
						<option>${sessionScope.loginData.jobName}</option>
					</select>
				</div>
				<div class="input-form">
					<label class="input-label w-100">부서</label>
					<select class="select-form w-auto" name="deptId" disabled>
						<option>${sessionScope.loginData.deptName}</option>
					</select>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">이메일</label>
					<input class="input-text w-auto" type="text" name="email" value="${sessionScope.loginData.email}">
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<fmt:formatDate var="fHireDate" value="${empsDetailData.hireDate}" dateStyle="long" />
					<label class="input-label w-100">입사일</label>
					<input class="input-text w-auto" type="text" name="hireDate" value="${fHireDate}" disabled>
				</div>
				<div class="input-form">
					<label class="input-label w-100">전화번호</label>
					<input class="input-text w-auto" type="text" name="phone" value="${empsDetailData.phone}">
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<fmt:formatNumber var="fSalary" value="${empsDetailData.salary}" />
					<label class="input-label w-100">급여액</label>
					<input class="input-text w-auto" type="text" name="salery" value="${fSalary}" disabled>
				</div>
				<div class="input-form">
					<fmt:formatNumber var="fCommission" value="${empsDetailData.commission}" type="percent" />
					<label class="input-label w-100">커미션</label>
					<input class="input-text w-auto" type="text" name="commission" value="${fCommission}" disabled>
				</div>
			</div>
			<div class="input-form form-right">
				<button class="btn btn-outline btn-ok" type="submit">저장</button>
			</div>
		</form>
	</section>
</body>
</html>