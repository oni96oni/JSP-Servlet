<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String menuLocation = "";
	if(request.getAttribute("menuLocation") != null) {
		menuLocation = (String)request.getAttribute("menuLocation");
	}
%>
<header>
	<nav class="top-nav center">
		<ul class="nav">
			<li class="nav-item dropdown">
				<a class="nav-link" href="#">JSP/Servlet</a>
				<ul class="nav dropdown-nav">
					<li class="nav-item">
						<a class="nav-link" href="./jsp/script_tag">Script Tag</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./jsp/request">request</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./jsp/response">response</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="./jsp/mvc">MVC Model</a>
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