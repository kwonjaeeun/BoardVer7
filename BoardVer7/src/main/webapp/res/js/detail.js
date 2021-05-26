	var infoElem=document.querySelector('#info');
	var iboard=infoElem.dataset.iboard;
	var login=infoElem.dataset.login;
	var delmodElem=document.querySelector('#delmod');
	function ajax(iboard){
		const param={iboard}
		const init={
				method:'POST',
				body: new URLSearchParams(param)				
		}
		fetch('/board/detail',init)
		.then(function(res){return res.json();})
		.then(function(myJson){
			setData(myJson);
		});
		function setData(data) {
			var iboardElem=document.querySelector('#iboard');
			var titleElem=document.querySelector('#title');
			var regdtElem=document.querySelector('#regdt');
			var writterElem=document.querySelector('#iuser');
			var ctntElem=document.querySelector('#ctnt');
			var delmodElem=document.querySelector('#delmod');
			iboardElem.innerText=data.iboard;
			titleElem.innerText=data.title;
			regdtElem.innerText=data.regdt;
			writterElem.innerText=data.writerNm;
			ctntElem.innerText=data.ctnt;
				if(login==data.iuser){
					var delbtn=document.createElement('button');
					var modbtn=document.createElement('button');
					delbtn.innerText='삭제';
					delbtn.addEventListener("click",aaa);
					modbtn.innerText='수정';
					delmodElem.append(delbtn);
					delmodElem.append(modbtn);
					}
		}
	}
	function aaa(){
		console.log("aaa");
	}
	ajax(iboard);