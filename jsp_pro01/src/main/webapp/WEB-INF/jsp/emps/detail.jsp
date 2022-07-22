<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>직원 상세</title>
	<%@ include file="../module/head.jsp" %>
</head>
<script type="text/javascript">
window.onload = function() {
	previewImg.addEventListener("click", function(e) {
		imgSelect.click();
	});
	
	imgSelect.addEventListener("change", showImagePreview);
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
function ajaxEmpDelete(id) {
	if(confirm("해당 데이터를 삭제하겠습니까?")) {
		$.ajax({
			type: "post",
			url: "/ajax/delete",
			data: {
				id: id
			},
			dataType: "json",
			success: function(data) {
				if(data.type === "success") {
					alert(data.message);
					location.href="/emps";
				} else if(data.type === "fail") {
					alert(data.message);
				}
			}
		});
	}
}
</script>
<body>
	<%@ include file="../module/navigation.jsp" %>
	<section class="container">
		<div class="large-form">
			<div class="image-form left">
				<img id="previewImg" class="image-360" alt="여기에는 증명 사진이 배치됩니다." src="${imagePath}">
				<br>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">ID</label>
					<input class="input-text w-auto" type="text" name="empId" value="${empsData.empId}" disabled>
				</div>
				<div class="input-form">
					<label class="input-label w-100">이름</label>
					<input class="input-text w-auto" type="text" name="empName" value="${empsData.empName}" disabled>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">직급</label>
					<select class="select-form w-auto" name="jobId" disabled>
						<option value="${empsData.jobId}">${empsData.jobName}</option>
					</select>
				</div>
				<div class="input-form">
					<label class="input-label w-100">부서</label>
					<select class="select-form w-auto" name="deptId" disabled>
						<option value="${empsData.deptId}">${empsData.deptName}</option>
					</select>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">이메일</label>
					<input class="input-text w-auto" type="text" name="email" value="${empsData.email}" disabled>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">입사일</label>
					<input class="input-text w-auto" type="text" name="hireDate" value="${empsDetailData.hireDate}" disabled>
				</div>
				<div class="input-form">
					<label class="input-label w-100">전화번호</label>
					<input class="input-text w-auto" type="text" name="phone" value="${empsDetailData.phone}" disabled>
				</div>
			</div>
			<div class="input-form inline">
				<div class="input-form">
					<label class="input-label w-100">급여액</label>
					<input class="input-text w-auto" type="text" name="salary" value="${empsDetailData.salary}" disabled>
				</div>
				<div class="input-form">
					<label class="input-label w-100">커미션</label>
					<input class="input-text w-auto" type="text" name="commission" value="${empsDetailData.commission}" disabled>
				</div>
			</div>
			<div class="input-form form-right">
				<c:url var="empModUrl" value="/emps/modify">
					<c:param name="id" value="${empsData.empId}" />
				</c:url>
				<button class="btn btn-outline btn-ok" type="button" onclick="location.href='${empModUrl}'">수정</button>
				<c:url var="empDelUrl" value="/emps/delete">
					<c:param name="id" value="${empsData.empId}" />
				</c:url>
				<button class="btn btn-outline btn-cancel" type="button" onclick="ajaxEmpDelete(${empsData.empId});">삭제</button>
			</div>
		</div>
	</section>
</body>
</html>