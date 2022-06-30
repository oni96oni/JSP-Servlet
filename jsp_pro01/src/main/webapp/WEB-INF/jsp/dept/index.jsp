<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dept.model.DeptDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>부서 조회 결과</title>
</head>
<script type="text/javascript">
window.onload = function() { //페이지가 로드완료되면 실행하겠다.
	var form = document.forms[0];
	form.addEventListener("submit", formCheck);
}

function formCheck(e) {
	var f = e.target;
	e.preventDefault();
	
	if(f.search.value.trim() === "") {
		f.search.value = f.search.value.trim();
		return;
	}
	f.submit();
}
</script>
<body>
	<h1>부서 조회 결과</h1>
	<div>
		<button type="button" onclick="location.href='./depts/add'">추가</button>
	</div>
	<form action="./depts" method="get">
		<div>
			<input type="text" name="search">
			<button type="submit">조회</button>
		</div>
	</form>
	<table>
		<tr>
			<th>DeptId</th>
			<th>DeptName</th>
			<th>MngId</th>
			<th>LocId</th>
			<th></th>
		</tr>
		<% 
			if(request.getAttribute("datas") != null) { // 여기서는 List<DeptDTO> DB자료들을 받거나 search에 해당하는 DTO받아서 출력
				List<DeptDTO> datas = (List<DeptDTO>)request.getAttribute("datas"); // Object type -> List<DeptDTO> 형변환
				for(DeptDTO data: datas) {
		%>
		<tr>
			<td><%=data.getDeptId() %></td>
			<td><%=data.getDeptName() %></td>
			<td><%=data.getMngId() %></td>
			<td><a href="./locs?search=<%=data.getLocId() %>"><%=data.getLocId() %></a></td>
			<td>
				<button type="button" onclick="location.href='./depts/mod?id=<%=data.getDeptId() %>'">수정</button>
			</td>
		</tr>
		<% 		}
			} else {
		%>
		<tr>
			<td colspan="5">검색 결과가 없습니다.</td>
		</tr>	
		<%
			}
		%>
	</table>
</body>
</html>