<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>${loginUser.uid}님환영합니다.</div>
<button type="button" id="like_btn" onclick="like(${param.iboard})">
	<img id="like" width="100%" src="/css/22.png">
</button>
<div>
	<a href="list">리스트로 돌아가기</a>
</div>
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
<c:if test="${loginUser.iuser eq data.iuser}">
	<div>
		<button>
			<a href="del?iboard=${param.iboard}">삭제</a>
		</button>
		<button>
			<a href="mod?iboard=${param.iboard}">수정</a>
		</button>
	</div>
</c:if>
<script>
	function ajax(iboard){
		console.log('iboard:'+iboard);
		const param={iboard}
		
		const init={
				method:'POST',
				body: new URLSearchParams(param)				
		}
		fetch('/board/detail',init)
		.then(function(res){return res.json();})
		.then(function(myJson){
			console.log(myJson);
			setData(myJson);
		});
		function setData(data) {
			var iboardElem=document.querySelector('#iboard');
			var titleElem=document.querySelector('#title');
			var regdtElem=document.querySelector('#regdt');
			var writterElem=document.querySelector('#iuser');
			var ctntElem=document.querySelector('#ctnt');
			iboardElem.innerText=data.iboard;
			titleElem.innerText=data.title;
			regdtElem.innerText=data.regdt;
			writterElem.innerText=data.iuser;
			ctntElem.innerText=data.ctnt;
		}
	}
	ajax(${param.iboard});
</script>
