<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSP/Servlet - MVC Model</title>
</head>
<body>
	<h1>JSP/Servlet - MVC Model</h1>
	<h2>Model 1</h2>
	<p>
		mvc 이전 고전적 웹 서비스 모델 방식으로 하나의 JSP에 화면 구성, 비지니스 로직, 데이터베이스 연결 처리 등을 구성<br>
		운용하는 형태의 모델 방식이다.
	</p>
	<p>
		개인이 서비스를 개발하는 경우에는 편하게 작업을 진행가능<br>
		단, 시간이 흘러 유지보수가 자주 이루어진 경우 이전에 작업한 코드의 분석이 어려워진다.<br>
		하나의 페이지에 HTML, CSS, JS, JSP, JAVA, SQL 등의 코드가 섞여 있기 때문에 로직을 파악하는 것이 어렵다.<br>
		원할한 협업이 어렵다.
	</p>
	
	<hr>
	<h2>MVC Model1(Model 2)</h2>
	<p>
		Model 1 에서 분리가 안된 코드들을 기능별/ 역할별로 코드를 분리하여 관리하는 방식<br>
		새로운 기능/역할 추가 용이.
	</p>
	<ul>
		<li>Model : 사용자에게 필요한 정보를 처리하기 위한 비지니스 로직의 역할</li>
		<li>View : 사용자에게 정보를 보여주기 위한 화면을 구성하는 역할</li>
		<li>Controller : 모델과 뷰 사이에 어떤 동작이 있을 때 중간에서 조정을 하는 역할</li>
	</ul>
	<hr>
	<h2>MVC Model을 사용하여 DEPARTMENTS 테이블 정보 조회하기</h2>
	<ol>
		<li>/jsp01/depts 주소로 요청을 하는 경우 dept.controller.DeptController가 동작이 되도록 한다.</li>
		<li>DeptController에서는 dept.service.DeptService를 사용하여 전체 부서를 조회하도록 한다.</li>
		<li>DeptService에서는 dept.model.DeptDAO 를 사용하여 데이터베이스에 조회 요청을 하게 한다.</li>
		<li>DeptDAO는 마이바티스를 이용해서 데이터베이스 연결 세션을 생성 후 사용하게 된다.</li>
		<li>모든 처리 결과는 요처의 역순으로 결과값을 리턴하게 하여 DeptController까지 전달한다.</li>
		<li>DeptController에서는 전달 받은 데이터에 적합한 View를 선택하여 View에서 화면을 구성할 수 있게 데이터를 전달한다.</li>
		<li>View는 DeptController에서 전달한 데이터를 사용하여 화면을 구성한다.</li>
		<li>모든 작업이 완료되어 완성된 HTML 코드를 클라이언트에게 응답 메시지로 전송한다</li>
	</ol>
	<button type="button" onclick="location.href='/jsp01/depts'">부서조회</button>
</body>
</html>