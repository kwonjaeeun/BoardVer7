<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mod-${param.iboard}</title>
<link rel="stylesheet" href="../css/form.css">
</head>
<body>
<div class="center_board">
		<div class="b_title">
			<strong><a href="home">Board ver.04</strong>
		</div>
		<div class="navi_container">
			<div>
				<a href="/user/join"> Join </a>
			</div>
			<div>
				<a href="/user/login" id="log_tag"> Logout </a>
			</div>
		</div>
	</div>
	<div>${loginUser.uid}님환영합니다.</div>
	<form action="mod?iboard=${param.iboard}" method="post">
		<div>
			<input type="text" name="title" placeholder="Title" value="${data.title}">
		</div>
		<div>
			<textarea name="ctnt" placeholder="Content">${data.ctnt}</textarea>
		</div>
		<div>
			<input type="submit" value="modify">
			<input type="reset" value="Reset">
		</div>
	</form>
	
</body>
</html>