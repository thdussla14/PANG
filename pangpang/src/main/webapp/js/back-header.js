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

