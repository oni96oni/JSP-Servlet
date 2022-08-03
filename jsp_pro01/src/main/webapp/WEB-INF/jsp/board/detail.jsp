<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${data.title}</title>
	<link rel="stylesheet" type="text/css" href="/static/bs5/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
	<script type="text/javascript" src="/static/bs5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/static/js/jquery-3.6.0.min.js"></script>
</head>
<script type="text/javascript">
	function ajaxLike(element, id) {
		$.ajax({
			type: "post",
			url: "/ajax/board/like",
			data: {
				id: id
			},
			success: function(data) {
				element.innerText = data.like;
			}
		});
	}
</script>
<body>
	<header></header>
	<section class="container">
		<div class="mt-3">
			<div class="mb-1 border-bottom border-2 border-secondary">
				<h1>${data.title}</h1>
			</div>
			<div class="mb-3">
				<label class="pe-3 text-secondary text-opacity-75">${empData.empName}</label>
				<fmt:formatDate value="${data.createDate}" var="createDate" dateStyle="long" />
				<label class="pe-3 text-secondary text-opacity-75">${createDate}</label>
				<label class="pe-3 text-secondary text-opacity-75">조회수: ${data.viewCnt}</label>
			</div>
			<div class="mb-1 border-bottom border-2 border-secondary">
				<p>${data.content}</p>
			</div>
			<div class="mb-1">
				<div onclick="ajaxLike(id_like, ${data.id});">
					<i class="bi bi-hand-thumbs-up text-secondary text-opacity-50"></i>
					<label id="id_like" class="text-secondary text-opacity-75">${data.like}</label>
				</div>
			</div>
			<div class="mb-1 text-end">
				<c:url var="boardUrl" value="/board" />
				<button class="btn btn-primary" type="button" onclick="location.href='${boardUrl}'">목록</button>
				<c:if test="${data.empId eq sessionScope.loginData.empId}">
					<button class="btn btn-success" type="button">수정</button>
					<button class="btn btn-danger" type="button" data-bs-toggle="modal" data-bs-target="#removeModal">삭제</button>
				</c:if>
			</div>
		</div>
		<div class="modal fade" id="removeModal" tabindex="-1" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h6 class="modal-title">삭제 확인</h6>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						해당 데이터를 삭제하겠습니까?
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-sm btn-danger" data-bs-dismiss="modal" onclick="deleteBoard(${data.id})">확인</button>
					</div>
				</div>
			</div>
		</div>
	</section>
	<footer></footer>
	<script type="text/javascript">
		function deleteBoard(boardId) {
			$.ajax({
				url: "/board/delete",
				type: "post",
				data: {
					id: boardId
				},
				dataType: "json",
				success: function(data) {
					if(data.code === "success") {
						alert("삭제 완료");
						location.href = "/board";
					} else if(data.code === "permissionError") {
						alert("권한이 오류");
					} else if(data.code === "notExists") {
						alert("이미 삭제되었습니다.")
					}
				}
			});
		}
	</script>
</body>
</html>