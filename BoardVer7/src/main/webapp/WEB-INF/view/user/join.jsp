<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h1>join</h1>
<form id="frm" action="join" method="post" onsubmit="return frmChk();">
	<div>
		<input type="text" name="uid" placehorder="아이디"> 
		<input type="button" id="btnChkId" value="중복ID체크"> 
		<span style="color:red;" id=chkUidResult></span>
	</div>
	<div>
		<div>
			<input type="password" name="upw" placehorder="비밀번호">
		</div>
		<div>
			<input type="password" id="chkUpw" placehorder="비밀번호 확인">
		</div>
		<div>
			<input type="text" name="unm" placehorder="이름">
		</div>
		<div>
			성별: <label>여성<input type="radio" name="gender" value="0"
				checked="checked"></label> <label>남성<input type="radio"
				name="gender" value="1"></label>
		</div>
		<div>
			<input type="submit" value="회원가입"> <input type="reset"
				value="초기화">

		</div>

	</div>
</form>
<script src="/res/js/userjoin.js"></script>