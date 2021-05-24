var frmElem = document.querySelector('#frm');
var uidElem = frmElem.uid;
var upwElem = frmElem.upw;
var chkUpwElem = frmElem.chkUpw;
var unmElem = frmElem.unm;
var chkUidResultElem = frmElem.querySelector('#chkUidResult');
var btnChkIdElem = frmElem.btnChkId;
btnChkIdElem.addEventListener('click', function() {
var param={uid:uidElem.value,unm:unmElem.value,upw:upwElem.value};
	idChkAjax(param);
});
function idChkAjax(param) {
	console.log(param);
	const init = {
		method: 'POST',
		body: new URLSearchParams(param)
	}
	fetch('/user/idChk', init)
	.then(function(a) { return a.json(); })
	.then(function(myJson) {
		if(myJson.result==0){		
			console.log(0);	
			chkUidResultElem.innerHTML = '사용 불가능한 아이디';
			chkUidResultElem.style.color = '#ff0000';
			return;
		}
			console.log(1);	
			chkUidResultElem.innerHTML = '사용 가능한 아이디';
			chkUidResultElem.style.color = '#0000ff';
			
		
	 })
}

function frmChk() {
	var uidVal = uidElem.value;
	var chkUpwVal = chkUpwElem.value;
	var upwVal = upwElem.value;
	var unmVal = unmElem.value;
	var result = 0;

	if (uidVal.length == 0) {
		alert('아이디를 작성해주세요');
		result++;
	} else if (uidVal.length < 3) {
		alert('아이디는 3자 이상 작성해야 합니다');
		result++;
	} else if (upwVal.length == 0) {
		alert('비밀번호를 작성해주세요');
		result++;
	} else if (upwVal.length < 4) {
		alert('비밀번호는 4자 이상 작성해야 합니다');
		result++;
	} else if (upwVal !== chkUpwVal) {
		alert('비밀번호가 맞는지 확인하세요');
		result++;
	} else if (unmVal.length == 0) {
		alert('이름을 작성해주세요');
		result++;
	} else if (upwVal < 2) {
		alert('이름은 2자이상이어야합니다');
		result++;
	}
	if (result) {
		return false;
	}
	console.log(uidVal);
	console.log(upwVal);
	console.log(chkUpwVal);
	return true;
}

