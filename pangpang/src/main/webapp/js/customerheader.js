console.log( 'newheader.js 실행');

let memberInfo = null;

// 로그인한 회원정보 호출 
getLogin();
function getLogin(){
	$.ajax({
		url : "/pangpang/member/login" ,
		async : false ,
		method : "get" , 
		success : (r) => {
			memberInfo = r;
			console.log(r)
		}
	})
}

if( memberInfo.member_id != null ){
	
	let html = `
				<a href="/pangpang/member/logout.jsp"><span class="signup_btn_box"> 로그아웃 </span></a>
				<a href="#"><span class="cust_center"> 고객센터 </span></a>
				`
	document.querySelector('.cust_loginbox').innerHTML = html;
	
}





