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
	<form action="./locs" method="get">
		<div>
			<input type="text" name="search">
			<button type="submit">조회</button>
		</div>
	</form>
	<table>
		<tr>
			<th>LOCATION_ID</th>
			<th>STREET_ADDRESS</th>
			<th>POSTAL_CODE</th>
			<th>CITY</th>
			<th>STATE_PROVINCE</th>
			<th>COUNTRY_ID</th>
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
		</tr>
		<% 		}
			} else {
		%>
		<tr>
			<td colspan="6">검색 결과가 없습니다.</td>
		</tr>	
		<%
			}
		%>
	</table>
</body>
</html>