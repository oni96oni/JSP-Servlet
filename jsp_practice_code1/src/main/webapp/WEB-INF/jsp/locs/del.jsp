<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="locs.model.LocsDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>삭제 확인</title>
</head>
<body>
	<%
		if(request.getAttribute("data") != null) {
			LocsDTO data = (LocsDTO) request.getAttribute("data");
	%>
			<ul>
				<li>지역 ID : <%=data.getLocsId() %></li>
				<li>거리 주소 : <%=data.getStrAdd() %></li>
				<li>우편 번호 : <%=data.getPosCode() %></li>
				<li>도시 이름 : <%=data.getCity() %></li>
				<li>지역 이름 : <%=data.getStaPro() %></li>
				<li>국가 ID : <%=data.getConId() %></li>
			</ul>
			<div>
				<button type="submit" form="deleteForm">삭제</button>
				<button type="button" onclick="history.back();">취소</button>
			</div>
			<form id="deleteForm" action="./del" method="post">
				<input type="hidden" name="locsId" value="<%=data.getLocsId() %>">
			</form>
	<%
		} else {
			if(request.getAttribute("errorMsg") == null) {
	%>
				<p>요청한 자료는 이미 삭제 되었거나 존재하지 않습니다.</p>
	<%
			} else {
				String errorMsg = (String)request.getAttribute("errorMsg");
	%>
				<p><%=errorMsg %></p>
	<%
			}
	%>
			<div>
				<button type="button" onclick="location.href='../locs';">돌아가기</button>
			</div>
	<%
		}
	%>
</body>
</html>