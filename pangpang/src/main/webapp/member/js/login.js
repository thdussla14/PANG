console.log('login페이지')
function login(){
	let member_id = document.querySelector('.member_id').value
	let member_pwd = document.querySelector('.member_pwd').value
	
	$.ajax({
		url : "/pangpang/member/login" ,
		method : "post" , 
		data : { "member_id" : member_id , "member_pwd" : member_pwd} ,
		success : (r)=>{ 
			console.log(r)
			if( r == 1 ){ location.href="/pangpang/product/product_index.jsp"; }
			else if(r>1){location.href="/pangpang/index.jsp"; }
			else{
				alert('아이디 또는 비밀번호가 틀립니다.')	
			}
		}
	})
}

function onpenFindidModal( ){
	let html = `<div class="modal_content">
						<table class="modal_table">
							<tr>
								<th class="modal_table_th">이름</th>
								<td class="modal_table_td">
									<input class="input1 member_name" type="text">
								</td>
							</tr>
							<tr>
								<th class="modal_table_th">이메일</th>
								<td class="modal_table_td">
									<input class="input1 member_email" type="text">
								</td>
							</tr>
						</table>
					</div>
					<div class="modal_btns">
						<button onclick="findid()" class="findid_btn modal_btns" type="button">확인</button>
					</div>`
	document.querySelector('.findid_box').innerHTML=html
	document.querySelector('.findid_box').style = ''
	document.querySelector('.findid_modal_wrap').style.display ='flex';
}
function closeFindidModal(){
	document.querySelector('.findid_modal_wrap').style.display ='none';
}

/* 모달 */
function onpenFindpwdModal( ){
	let html = `<div class="modal_content con2">
						<table class="modal_table">
							<tr>
								<th class="modal_table_th">아이디</th>
								<td class="modal_table_td">
									<input class="input1 member_idpwd" type="text">
								</td>
							</tr>
							<tr>
								<th class="modal_table_th">이름</th>
								<td class="modal_table_td">
									<input class="input1 member_namepwd" type="text">
								</td>
							</tr>
							<tr>
								<th class="modal_table_th">이메일</th>
								<td class="modal_table_td">
									<input class="input1 member_emailpwd" type="text">
								</td>
							</tr>
						</table>
					</div>
					<div class="modal_btns">
						<button onclick="findpwd()" class="findpwd_btn modal_btns" type="button">확인</button>
					</div>`
	document.querySelector('.findpwd_box').innerHTML=html
	document.querySelector('.findpwd_box').style = ''
	document.querySelector('.findpwd_modal_wrap').style.display ='flex';
}
function closeFindpwdModal(){
	document.querySelector('.findpwd_modal_wrap').style.display ='none';
}



// 2. 아이디 찾기 
function findid(){	console.log( "findid()함수");
	let member_name = document.querySelector('.member_name').value; 
	let member_email = document.querySelector('.member_email').value; 
	let style ='width: 100%; height: 310px !important; display: flex;align-items: center; justify-content: center;font-size: 20px;'
	
	$.ajax({
		url : "/pangpang/member/find" ,
		method : "get" , 
		data : { "type" : 1 , "member_name" : member_name , "member_email" : member_email} ,
		success : (r) => {
			document.querySelector('.findid_box').style = style
			if( r == 'null'){ 
				let html = `동일한 회원정보가 없습니다.`
				document.querySelector('.findid_box').innerHTML= html;
			}else{ 
				document.querySelector('.findid_box').innerHTML= '회원님 ID : '+r;
			}
		}
	})	
} 

// 3. 비밀번호찾기 
function findpwd(){	console.log( "findpwd()함수");
	let style ='width: 100%; height: 310px !important; display: flex;align-items: center; justify-content: center;font-size: 20px;'
	
	let info = {
		type : 2 ,
		"member_id" : document.querySelector('.member_idpwd').value, 
		"member_name"  : document.querySelector('.member_namepwd').value ,
		"member_email" : document.querySelector('.member_emailpwd').value
	}; console.log( info );
	$.ajax({
		url : "/pangpang/member/find",
		method : "get",
		data : info , 
		success : (r) => {	console.log('통신'); console.log(r);
			document.querySelector('.findpwd_box').style = style
			if( r == 'false'){ 
				document.querySelector('.findpwd_box').innerHTML= '동일한 회원정보가 없습니다.'; 
			}else{ 
				document.querySelector('.findpwd_box').innerHTML= '임시비밀번호를 이메일로 전송했습니다.\n로그인 후 비밀번호를 변경해 주세요.'; 
				console.log(r)
			}
		}
	})
}

