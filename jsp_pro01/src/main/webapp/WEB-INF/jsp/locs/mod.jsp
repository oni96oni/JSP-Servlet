<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="locs.model.LocsDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>지역 수정</title>
	<link herf="<%=request.getContextPath() %>/static/css/default.css">
	<script src="<%=request.getContextPath() %>/static/js/default.js"></script>
</head>
<body>
	<h1>지역 수정 화면</h1>
	<%
		
		String locsId="",strAdd = "", posCode = "", city = "", staPro = "", conId="";
		
		LocsDTO data = (LocsDTO) request.getAttribute("data");
		locsId = String.valueOf(data.getLocsId());
		strAdd = data.getStrAdd();
		posCode = data.getPosCode();
		city = data.getCity();
		staPro = data.getStaPro();
		conId = data.getConId();
		
		if(request.getAttribute("error") != null) {
	%>
			<script type="text/javascript">
				alert("<%=request.getAttribute("errorMsg") %>");
			</script>
	<%
		}
	%>
	<form action="./mod" method="post">
		<input type="hidden" name="locsId" value="<%=locsId %>" readonly>
		<div>
			<input type="text" name="strAdd" value="<%=strAdd %>" placeholder="도로명">
		</div>
		<div>
			<input type="text" name="posCode" value="<%=posCode %>" placeholder="우편번호">
		</div>
		<div>
			<input type="text" name="city" value="<%=city %>" placeholder="도시">
		</div>
		<div>
			<input type="text" name="staPro" value="<%=staPro %>" placeholder="지역 코드">
		</div>
		<div>
			<input type="text" name="conId" value="<%=conId %>" placeholder="국가 ID">
		</div>
		<div>
			<button type="submit">저장</button>
		</div>
	</form>
</body>
</html>