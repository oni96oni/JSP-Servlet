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
window.onload = function() {
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
	<div>
		<form action="./depts" method="get">
			<div>
				<input type="text" name="search">
				<button type="submit">조회</button>
			</div>
		</form>
	</div>
	<table>
		<tr>
			<th>DeptId</th>
			<th>DeptName</th>
			<th>MngId</th>
			<th>LocId</th>
			<th></th>
		</tr>
		<%
			if(request.getAttribute("datas") != null) {
				List<DeptDTO> datas = (List<DeptDTO>)request.getAttribute("datas");
				for(DeptDTO data: datas) {
		%>
					<tr>
						<td><%=data.getDeptId() %></td>
						<td><%=data.getDeptName() %></td>
						<td><%=data.getMngId() %></td>
						<td><a href="./locs?search=<%=data.getLocId() %>"><%=data.getLocId() %></a></td>
						<td>
							<button type="button" onclick="location.href='./depts/mod?id=<%=data.getDeptId() %>'">수정</button>
							<button type="button" onclick="location.href='./depts/del?id=<%=data.getDeptId() %>'">삭제</button>
						</td>
					</tr>
		<%
				}
			} else {
		%>
			<tr>
				<td colspan="4">검색 결과가 없습니다.</td>
			</tr>
		<%
			}
		%>
	</table>
	<%
		if(request.getAttribute("pageList") != null) {
			List<Integer> pageList = (List<Integer>) request.getAttribute("pageList");
			int currentPage = Integer.parseInt(request.getParameter("page"));
	%>
			<ul>
	<%
			if(currentPage - 1 > 0) {
	%>
				<li><a href="./depts?page=<%=currentPage - 1 %>">Prev</a></li>
	<%
			}
			int i = currentPage - 1;
			int maxPage = i + 5 > pageList.size() ? pageList.size() : i + 5;
			for(; i < maxPage; i++) {
	%>
				<li><a href="./depts?page=<%=pageList.get(i) %>"><%=pageList.get(i) %></a></li>
	<%
			}
			if(currentPage + 1 <= pageList.size()) {
	%>
				<li><a href="./depts?page=<%=currentPage + 1 %>">Next</a></li>
	<%
			}
	%>
			</ul>
	<%
		}
	%>
</body>
</html>