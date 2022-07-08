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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/default.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/form.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/navigation.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/paging.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/required.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/table.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/required.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/module/navigation.jsp" %>
	<h1>Welcome JSP/Servlet</h1>
	<div>
		<h2>EL(expression language; 표현 언어), JSTL 테스트</h2>
		<hr>
		<%--  ${1 + 1}<br>
		${2 * 3}<br>
		${2 == 3} / ${2 eq 3}<br>
		${2 != 3} / ${2 ne 3}<br>
		${2 >= 3} / ${2 ge 3}<br>
		${2 <= 3} / ${2 le 3}<br>
		${2 < 3} / ${2 gt 3}<br>
		${2 > 3} / ${2 lt 3}<br>
		${not true} / ${not false}<br>
		${null - 1} / ${null + 1}<br>
		${empty x }<br>
		
		<%=request.getContextPath() %> 가 아래줄처럼 사용가능
		${pageContext.request.contextPath} 
		
		<%=request.getParameter("x")%> 가 아래줄처럼 사용가능
		${param.x}
		
		<%=((DeptDTO)request.getAttribute("data")).getDeptId() %>
		
		${requestScope.data.deptId}<br>	EL식으로 쓰면 다운캐스팅이 필요없다! 멤버변수명으로 오기때문에
		${requestScope.data.deptName} 심지어 임포트를 안해줘도 된다!
		${data.deptId}<br> requestScope도 생략가능하다.	
		${data.deptName}
		
		null 체크(값이 null이면 출력자체를 안함), casting(알아서 해줘), import 등을 안해주어도 되서 편리하다.
		
		JSTL
		<c: ~ : 제어문
		<fmt: ~ : 포멧(날짜,숫자)
		<fn: ~ : 함수(문자열관련
		
		mybatis 동적쿼리와 문법이 비슷
		--%>
		<c:set var="income" scope="page" value="${4000*4}" />
		<c:if test="${income > 8000}">
			<p>
				My income is:
				<c:out value="${income}" />
				${data}
			<p>

		</c:if>
		<c:if test="false">
			<h1>if 제어문 : test에 작성한 조건식이 참이면 실행된다.</h1>
		</c:if>
		<hr>
		<c:choose>
			<c:when test="${income <= 1000}">  
				Income is not good.  
			</c:when>
			<c:when test="${income > 10000}">  
				Income is very good.  
			</c:when>
			<c:otherwise>  
				Income is undetermined...  
			</c:otherwise>
		</c:choose>
	<hr>
	<c:forEach begin="5" end="10" step="2" var="j">  
   		<h1>Item <c:out value="${j}"/></h1>
   	</c:forEach> 
	<%
		List<String> list = new ArrayList<String>();
		list.add("a"); list.add("b"); list.add("c"); list.add("d");
		request.setAttribute("list", list);
	%>
	<c:forEach items="${list}" var="v">
		${v}<br>
	</c:forEach>
	<%
		Map<String, String> map = new HashMap<String, String>();
		map.put("a","가"); map.put("b","나"); map.put("c","다"); map.put("d","라");
		request.setAttribute("map", map);
	%>
	<c:forEach items="${map}" var="v">
		${v.key} - ${v.value}<br>
	</c:forEach>
	<hr>
	<%--
		변수의 사용범위를 나타내는 scope
		page		하나의 jsp(servlet)안에서만 유효
		request		요청~응답의 흐름안에서만 유효
		session		사용자의 모든요청~응답 흐름안에서만 유효
		application	모든요청자가 사용가능
		
		앞에 Scope범위를 쓰지않으면 pageScope사용
		pageScope없으면 requestScope사용
		requestScope없으면 sessionScope사용
		sessionScope없으면 applicationScope사용!
		
		즉 page를 우선순위로 없으면 다음 우선순위를 찾아가면서 불러온다.
	 --%>
	<c:set var="d" value="Hello1" scope="page"/>
	<c:set var="d" value="Hello2" scope="request"/>
	<c:set var="d" value="Hello3" scope="session"/>
	<c:set var="d" value="Hello4" scope="application"/>
	${pageScope.d}<br>
	${requestScope.d}<br>
	${sessionScope.d}<br>
	${applicationScope.d}<br>
	<hr>
	<c:remove var="d" scope="page"/>
	<c:remove var="d" scope="request"/>
	<c:remove var="d" scope="session"/>
	<c:remove var="d" scope="application"/>
	<hr>
	<c:url var="url1" value="/path">
		<c:param name="x" value="10"/>
	</c:url>
	${url1}
	<hr>
	<%--
	
	 --%>
	<fmt:formatNumber value="1000"/><br>
	<fmt:formatNumber value="0.1" type="percent"/><br>
	<fmt:formatNumber value="1000" type="currency"/><br>
	<fmt:formatNumber value="1000" type="currency" currencySymbol="!%%@#"/>
	<hr>
	<c:set var="date" value="<%=new Date() %>" />
	<fmt:formatDate value="${date}" type="date" /><br>
	<fmt:formatDate value="${date}" type="date" dateStyle="full" /><br>
	<fmt:formatDate value="${date}" type="date" dateStyle="long" /><br>
	<fmt:formatDate value="${date}" type="date" dateStyle="medium" /><br>
	<fmt:formatDate value="${date}" type="date" dateStyle="short" /><br>
	<fmt:formatDate value="${date}" type="date" pattern="YYYY-MM-dd E EEEE" />
	<hr>
	<fmt:formatDate value="${date}" type="time" /><br>
	<fmt:formatDate value="${date}" type="time" timeStyle="full" /><br>
	<fmt:formatDate value="${date}" type="time" timeStyle="long" /><br>
	<fmt:formatDate value="${date}" type="time" timeStyle="medium" /><br>
	<fmt:formatDate value="${date}" type="time" timeStyle="short" /><br>
	<fmt:formatDate value="${date}" type="time" pattern="a hh:mm:ss / HH:mm:ss z" />
	<hr>
	<fmt:formatDate value="${date}" type="both" /><br>
	<fmt:formatDate value="${date}" type="both" timeStyle="long" dateStyle="long"/><br>
	<hr>
	${fn:contains('Hello', 'e') }<br>
	${fn:containsIgnoreCase('Hello', 'E') }<br>
	${fn:startsWith('Hello', 'E') }<br>
	${fn:endsWith('Hello', 'e')}<br>
	${fn:indexOf('Hello', 'e')}<br>
	${fn:length('Hello')}<br>
	${fn:replace('Hello', 'e', 'a')}<br>
	${fn:substring('Hello', 1, 3)}<br>
	${fn:split('Hello, Hi', ', ')}<br>
	<% String s[] = {"a","b"}; request.setAttribute("txt", s); %>
	${fn:join(txt, '-')}<br>
	${fn:trim('  Hello  ')}<br>
	</div>
</body>
</html>