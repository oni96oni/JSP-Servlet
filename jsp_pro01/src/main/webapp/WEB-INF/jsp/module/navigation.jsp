<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String menuLocation = "";
	if(request.getAttribute("munuLocation") != null) {
		menuLocation = (String)request.getAttribute("menuLocation"); // String으로 넘어오는것이 아니라 Object로 넘어온다!
	}
%>
<header>
	<nav class="top-nav center">
		<ul class="nav">
			<li class="nav-item dropdown">
				<a class="nav-link" href="#"> JSP</a>
				<ul class="nav dropdown-nav">
					<li class="nav-item">
						<a class="nav-link" href="./jsp/script_tag">JSP - Script Tag</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./jsp/request">JSP - request</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./jsp/response">JSP - response</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./jsp/mvc">JSP - MVC Model</a>
					</li>
				</ul>
			</li>
			<li class="nav-item <%=menuLocation.equals("depts") ? "active" : "" %>">
				<a class="nav-link" href="./depts">부서</a>
			</li>
			<li class="nav-item <%=menuLocation.equals("locs") ? "active" : "" %>">
				<a class="nav-link" href="./locs">지역</a>
			</li>
		</ul>
	</nav>
</header>