<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, locs.model.LocsDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>지역 조회 결과</title>
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
	<h1>지역 조회 결과</h1>
	<div>
		<button type="button" onclick="location.href='./locs/add'">추가</button>
	</div>
	<div>
		<form action="./locs" method="get">
			<div>
				<input type="text" name="search">
				<button type="submit">조회</button>
			</div>
		</form>
	</div>
	<table>
		<tr>
			<th>LocsId</th>
			<th>StrAdd</th>
			<th>PosCode</th>
			<th>CITY</th>
			<th>StaPro</th>
			<th>ConId</th>
		</tr>
		<% 
			if(request.getAttribute("datas") != null) {
				List<LocsDTO> datas = (List<LocsDTO>)request.getAttribute("datas");
				for(LocsDTO data: datas) {
		%>
					<tr>
						<td><%=data.getLocsId() %></td>
						<td><%=data.getStrAdd() %></td>
						<td><%=data.getPosCode() %></td>
						<td><%=data.getCity() %></td>
						<td><%=data.getStaPro() %></td>
						<td><%=data.getConId() %></td>
						<td>
							<button type="button" onclick="location.href='./locs/mod?id=<%=data.getLocsId() %>'">수정</button>
							<button type="button" onclick="location.href='./locs/del?id=<%=data.getLocsId() %>'">삭제</button>
						</td>
					</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="7">검색 결과가 없습니다.</td>
		</tr>	
		<%
			}
		%>
	</table>
	<%
		if(request.getAttribute("pageList") != null) {
			List<Integer> pageList = (List<Integer>) request.getAttribute("pageList");
			int currentPage = (int)(request.getAttribute("page"));
	%>
			<ul>
	<%
			if(currentPage - 1 > 0) {
	%>
				<li><a href="./locs?page=<%=currentPage - 1 %>">Prev</a></li>
	<%
			}
			int i = currentPage - 1;
			int maxPage = i + 5 > pageList.size() ? pageList.size() : i + 5;
			for(; i < maxPage; i++) {
	%>
				<li><a href="./locs?page=<%=pageList.get(i) %>"><%=pageList.get(i) %></a></li>
	<%
			}
			if(currentPage + 1 <= pageList.size()) {
	%>
				<li><a href="./locs?page=<%=currentPage + 1 %>">Next</a></li>
	<%
			}
	%>
			</ul>
	<%
		}
	%>
</body>
</html>