<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="locs.model.LocsDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>지역 추가</title>
</head>
<body>
	<h1>지역 추가 화면</h1>
	<%	
		int locsId = 0;
		String strAdd = "", posCode = "", city = "", staPro = "", conId="";
		if(request.getAttribute("error") != null) {
			LocsDTO data = (LocsDTO) request.getAttribute("data");
			locsId = data.getLocsId();
			strAdd = String.valueOf(data.getStrAdd());
			posCode = String.valueOf(data.getPosCode());
			city = String.valueOf(data.getCity());
			staPro = String.valueOf(data.getStaPro());
			conId = String.valueOf(data.getConId());
	%>
			<script type="text/javascript">
				alert("<%=request.getAttribute("errorMsg") %>");
			</script>
	<%
		}
	%>
	<form action="./add" method="post">
		<div>
			<input type="text" name="locsId" value="<%=locsId %>" placeholder="국가 ID">
		</div>
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