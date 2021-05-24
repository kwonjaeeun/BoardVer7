<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${requestScope.title}</title>

<link rel="stylesheet" href="/res/css/template.css" >
</head>
<body>
<header>
		<nav>
			<ul>
			<c:if test="${sessionScope.loginUser eq null}">
				<li><a href="/user/login">LOGIN</a></li>
			</c:if>			
			<c:if test="${sessionScope.loginUser ne null}">						
				<li><a href="/user/login">LOGOUT</a></li>
				<li><a href="/board/write">WRITE</a></li>
				<li><a href="/board/like">LIKE</a></li>
			</c:if>			
				<li><a href="/board/list">LIST</a></li>
			</ul>
		</nav>
	</header>
<section>
	<jsp:include page="/WEB-INF/view/${requestScope.page}.jsp"></jsp:include>
</section>
</body>
</html>