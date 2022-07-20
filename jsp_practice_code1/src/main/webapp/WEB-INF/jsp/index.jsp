<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Welcome JSP/Servlet</title>
	<%@ include file="./module/head.jsp" %>
</head>
<script type="text/javascript">
	function sendAjax() {
		$.ajax({
			type: "get",
			url: "/ajax/test",
			data: {
				x: "Hello",
				y: 1234
			},
			dataType: "json",
			success: function(data, status) {
				//서버로 부터의 응답코드가 200번일때 동작할 함수작성
				//data: 서버로 부터 받은 데이터 정보를 가지고 있는 객체
				//status: 응답 코드
				console.log("success: " + data);
				console.log("success: " + data.attributeName);
				console.log("success: " + status);
			},
			error: function(data, status) {
				//서버로 부터의 응답코드가 200이 아닐때 동작할 함수작성
				//data: 서버로 부터 받은 데이터 정보를 가지고 있는 객체
				//status: 응답 코드
				console.log("error: " + data);
				console.log("error: " + status);
			},
			complete: function(data, status) {
				//통신 성공 여부와 관계 없이 완료후 동작할 함수 작성
				console.log("complete: " + data);
				console.log("complete: " + status);
			}
		})
	}
	
	<%-- 
	<script>
        function showError(error) {
            document.getElementById("error").innerHTML = error;
            document.getElementById("error").style.display = "none";
        }
    </script>
    <div id="error" style="display: none;"></div>
    <input type="text" id="name" name="name" onblur="showError(this.value)">
    <input type="submit" value="전송">
    <script>
        document.getElementById("name").onblur = function() {
            if (this.value == "") {
                showError("이름을 입력하세요");
            }
        }
    </script>
	--%>
	
</script>
<body>
	<%@ include file="/WEB-INF/jsp/module/navigation.jsp" %>
	<section class="container">
		<div>
			<button type="button" onclick="sendAjax();">비동기 통신</button>
		</div>
		<c:url var="loginUrl" value="/login" />
		<form class="small-form" action="${loginUrl}" method="post">
			<div class="input-form wide">
				<label class="input-label">직원ID</label>
				<input class="input-text" type="text" name="empId" value="">
				<c:if test="${errorCode eq 'deptId'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">부서명</label>
				<select class="select-form" name="deptId">
					<c:forEach items="${deptDatas}" var="deptDto">
						<option value="${deptDto.deptId}">${deptDto.deptName}</option>
					</c:forEach>
				</select>
				<c:if test="${errorCode eq 'deptId'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
			</div>
			<div class="input-form wide">
				<label class="input-label">이름</label>
				<input class="input-text" type="text" name="empName" value="">
				<c:if test="${errorCode eq 'deptName'}">
					<label class="input-label-error">${errorMsg}</label>
				</c:if>
			</div>
			<div class="input-form wide form-right">
				<button class="btn btn-outline btn-ok" type="submit">로그인</button>
			</div>
		</form>
	</section>
</body>
</html>