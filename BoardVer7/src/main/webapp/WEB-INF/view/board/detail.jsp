<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>${loginUser.unm}님환영합니다.</div>
<button type="button" id="like_btn" onclick="like(${param.iboard})">
	<img id="like" width="5%" src="/res/css/22.png">
</button>
<div>
	<a href="list">리스트로 돌아가기</a>
</div>
<div id="info" data-iboard="${param.iboard}" data-login="${loginUser.iuser}">
<div>
	NO:<span id="iboard"></span>
</div>
<div>
	TITLE:<span id="title"></span>
</div>
<div>
	CONTENT:<span id="ctnt"></span>
</div>
<div>
	WRITTER:<span id="iuser"></span>
</div>
<div>
	REGDT:<span id="regdt"></span>
</div>
	<div id="delmod"></div>
</div>

<script src="/res/js/detail.js"></script>
