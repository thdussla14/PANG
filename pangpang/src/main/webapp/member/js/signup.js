console.log('signup')
// 모달 설정 
function openmodal_address(){
	document.querySelector('.modal_wrap_address').style.display='flex';
}
function closemodal_address(){
	document.querySelector('.modal_wrap_address').style.display='none';
}

//------------------------------------------------------------------------------------- 주소검색
// 주소검색 API
function getAddress(){
	
	let keyword = document.querySelector('.keyword').value;
	console.log(keyword)
	
	// api 호출 전에 검색어 체크 	
	if (!checkSearchedWord(keyword)) {return ;}
	
	let info = {
		currentPage : 1,
		countPerPage: 10,
		keyword		: keyword,
		confmKey	: 'devU01TX0FVVEgyMDIzMDMyOTE1MjMzNjExMzY0MDI=',
		resultType	: 'json'
	}
	
	$.ajax({
		url 		: "https://business.juso.go.kr/addrlink/addrLinkApi.do",
		method 		: "post",		
		data 		: info,
		crossDomain	: true,
		success 	: (jsonStr)=>{
			console.log(jsonStr)
			if(jsonStr != null){makeListJson(jsonStr);}
		}// success e
	})	// ajax e
}

let jusoList = [];
 
// 검색된 주소 데이터 테이블 형식으로 변환 => 출력
function makeListJson(jsonStr){
	console.log(jsonStr.result)
	console.log(jsonStr.juso)

	let index = 0;
	let htmlStr = "";
	htmlStr += `<table>`;
	$(jsonStr.results.juso).each(function(){
	
		htmlStr +=`<tr>
					<td class="address_select${index}" onclick="address_select(${index})">${this.roadAddr}</td>
				   </tr>`
		index++;
		
	});
	htmlStr += `</table>`;
	document.querySelector('.resultbox').innerHTML = htmlStr;
	console.log(jusoList)
}

// 주소검색시 특수문자, 특정문자열 제거
function checkSearchedWord(keyword){
	if(keyword.length >0){
		
		//특수문자 제거
		var expText = /[%=><]/ ;
		if(expText.test(keyword) == true){
			alert("특수문자를 입력 할수 없습니다.") ;
			return false;
			
		}
		
		//특정문자열(sql예약어의 앞뒤공백포함) 제거
		var sqlArray = new Array(
			//sql 예약어
			"OR", "SELECT", "INSERT", "DELETE", "UPDATE", "CREATE", "DROP", "EXEC",
             		 "UNION",  "FETCH", "DECLARE", "TRUNCATE" 
		);		
		var regex;
		for(var i=0; i<sqlArray.length; i++){
			regex = new RegExp( sqlArray[i] ,"gi") ;
			
			if (regex.test(keyword) ) {
			    alert("\"" + sqlArray[i]+"\"와(과) 같은 특정문자로 검색할 수 없습니다.");
				keyword = keyword.replace(regex, "");
				return false;
			}
		}
	}
	return true ;
}

// 엔터 클릭시 검색 실행
function enterkey(){
	console.log( window.event.keycode )
	if( window.event.keyCode == 13){getAddress();}
}

// 선택한 주소 출력 => 상세주소 div 오픈
function address_select(i){
	
	let selected = 'address_select'+i;
	document.querySelector('.address_select_input').innerHTML = document.querySelector(`.${selected}`).innerHTML;
	document.querySelector('.address_select').style.display='flex';
	document.querySelector('.address_detail').style.display='flex';
}

// 주소 확정
function delivery_address(){
	
	let address_select = document.querySelector('.address_select_input').innerHTML;
	let address_detail = document.querySelector('.address_detail_input').value;
	
	document.querySelector('.member_address').innerHTML = address_select+","+address_detail;
	
	closemodal_address();

}



/* 생일 입력받기 */
function setbirth(){
	let birthYear = document.querySelector('.birthYear').value
	let birthMonth = document.querySelector('.birthMonth').value
	let birthDay = document.querySelector('.birthDay').value
	
	if(birthMonth<10){
		birthMonth = '0'+birthMonth
	}
	if(birthDay<10){
		birthDay = '0'+birthDay
	}
	let member_birth = birthYear+"-"+birthMonth+"-"+birthDay
	return member_birth
}

/* 도메인 선택하기 */
function setdomain(){
	let domain_select = document.querySelector('.domain_select').value
	if(domain_select!='직접입력'){
		document.querySelector('.domain').value = domain_select
	}else{
		document.querySelector('.domain').value = ''
	}
}

/* 이메일 입력받기*/
function setemail(){
	let email = document.querySelector('.email').value
	let domain = document.querySelector('.domain').value
	
	let member_email = email+'@'+domain
	return member_email
}

/* 전화번호 입력받기 */
function setphone(){
	let number_select = document.querySelector('.number_select').value
	let member_phone1 = document.querySelector('.member_phone1').value
	let member_phone2 = document.querySelector('.member_phone2').value
	
	let member_phone = number_select+"-"+member_phone1+-+member_phone2
	return member_phone
}

// * confirm span 모두 가져오기 --> 여러개의 span이 배열/리스트 객체에 대입
let checkconfirm = document.querySelectorAll('.confirm')

/* 이름 유효성검사 */
function namecheck(){
	let member_name =document.querySelector('.member_name').value
	if(member_name.length>=2){
		let namej = /^[가-힣]+[가-힣]$/
		if(namej.test(member_name)){
			checkconfirm[0].innerHTML = 'O'
			checkconfirm[0].style="color: #f0f7f5 !important" 
		}else{
			checkconfirm[0].innerHTML = '한글로 입력하세요'
			checkconfirm[0].style="color: red" 
		}
	}else{
		checkconfirm[0].innerHTML = '이름을 입력하세요'
		checkconfirm[0].style="color: red"
	}
}

/* 생일입력 유효성검사 */
function birthcheck(){
	let member_birth = setbirth();
	let birthj = /^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/
	if(birthj.test(member_birth)){
		checkconfirm[1].innerHTML = 'O'
		checkconfirm[1].style="color: #f0f7f5 !important"
	}else{
		checkconfirm[1].innerHTML = '모두 선택하세요'
		checkconfirm[1].style="color: red"
	}
}

/* 이메일 형식 확인 유효성검사 */
function emailcheck(){
	if(checkconfirm[2].innerHTML=='O'){ // 이메일 재선택 
		document.querySelector('.email').disabled = false;
		document.querySelector('.email').value = ''
		document.querySelector('.domain').disabled = false;
		document.querySelector('.domain').value = ''
		document.querySelector('.domain_select').disabled = false;
		document.querySelector('.domain_select').value = '직접입력'
		document.querySelector('.checkEmail').innerHTML = `이메일 중복확인`
		checkconfirm[2].innerHTML = ''
		checkconfirm[2].style="color: red"
	}else{
		let member_email = setemail();
		let memailj = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-]+$/
		if( memailj.test(member_email) ){ 
			$.ajax({
				url : "/pangpang/member/confirm" ,
				method : "get" , 
				data : {"member_email":member_email, "type":1} ,
				success : (r)=>{ 
					if(r=='false'){
						getauth()
					}else{
						alert('사용 중인 이메일입니다.')
					}
				}
			})
		}else{ 
			alert('이메일 형식에 맞게 작성하여 주십시오.')
		}
	}
} 

/* 이메일 인증 함수 */
function getauth(){
/*
	$.ajax({
		url : "/hotel/admin/email" , 
		method :"post",
		data : { "member_email" : setemail() } ,
		success : (r)=>{ 
			console.log('통신'); console.log(r);
			let html =  `	<div class="timebox"> 02 : 00</div>
							<input type="text" class="authinput input1" placeholder="인증코드">
							<button onclick="authconfirm()" type="button" class="btns btn2">확인</button>`
			// 2. html 대입 
			document.querySelector('.authbox').innerHTML = html;
			// 3. 타이머 함수 실행 
			auth = r;		//  *** 이메일 에게 보낸 난수 대입  ** 
			timer = 120;	// 인증 시간 대입 
			settimer();		// 타이머 함수 실행 
		}
	})
*/
	
	// ------------------ 2. 메일 전송 테스트 안되는 경우 임시 테스트 ---------------- //
	// 1. 인증 구역 html 구성 
	
	let html =  `<div class="timebox"> 02 : 00</div>
				<input type="text" class="authinput input1" placeholder="인증코드">
				<button onclick="authconfirm()" type="button" class="btns btn2">확인</button>`
	// 2. html 대입 
	document.querySelector('.authbox').innerHTML = html;
	document.querySelector('.authbox').style = 'margin-top: 6px;'
	// 3. 타이머 함수 실행 
	auth = 1234;	// **인증 임시 코드 대입	[ 이메일 에게 보낸 난수 대입 ]
	timer = 120;	// 인증 시간 대입 
	settimer();		// 타이머 함수 실행 

}
 
let auth = 0;	// 인증 코드 변수 
let timer = 0;	// 인증 시간 변수
let timerInter;	// Interval 함수를 저장할 변수
 
/* 타이머 함수 */
function settimer(){
	// setInterval : 특정 시간마다 함수 실행 		// setInterval( ()=>{} , 시간/밀리초 )
		// clearInterval : Interval 종료
	timerInter = setInterval( ()=>{
		let minutes = parseInt( timer / 60 ) ;	// 분 계산 
		let seconds = parseInt( timer % 60 ) ;	// 분 계산후 나머지가 초
		// 한자리수 이면 0 추가 
		minutes = minutes < 10 ? "0"+minutes : minutes;
		seconds = seconds < 10 ? "0"+seconds : seconds;
		// 시간 구성 
		let timeHTML = minutes +" : " + seconds ;	// 시 : 분 형식으로 html 구성 
		// html 대입 
		document.querySelector('.timebox').innerHTML = timeHTML;
		// 1초 차감 
		timer--;
		// 만약에 인증시간이 0보다 작으면  
		if( timer < 0 ){
			clearInterval( timerInter );	// setInterval 정지
			checkconfirm[2].innerHTML = '인증실패';
			checkconfirm[2].style="color: red"
			document.querySelector('.authbox').innerHTML = ""; // auth 내 html 지우기
			document.querySelector('.authbox').style = 'margin-top: 0px;'
		}
	} , 1000 );	// 1초마다 { } 코드 
}
/* 인증코드 확인 */
function authconfirm(){
	// 1. 입력받은 인증코드 호출 
	let authinput = document.querySelector('.authinput').value;
	// 2. 발급된 인증코드 와 입력한 인증코드 비교 
	if( auth == authinput ){ // 인증코드 일치 
		clearInterval( timerInter );
		alert('사용 가능한 이메일입니다.')
		document.querySelector('.authbox').innerHTML = "";
		document.querySelector('.authbox').style = 'margin-top: 0px;'
		document.querySelector('.email').disabled = true;
		document.querySelector('.domain').disabled = true;
		document.querySelector('.domain_select').disabled = true;
		document.querySelector('.checkEmail').innerHTML = `이메일 재선택`
		checkconfirm[2].innerHTML = 'O'
		checkconfirm[2].style="color: #f0f7f5 !important"
		
	}else{ // 인증코드 불일치 
		checkconfirm[2].innerHTML = '인증코드가 일치하지 않습니다.';
		checkconfirm[2].style="color: red"
	}
}

/* 전화번호 유효성검사 */
function phonecheck(){
	let member_phone = setphone()
	let phonej =/^\d{3}-\d{3,4}-\d{4}$/;
	if(phonej.test(member_phone)){
		checkconfirm[3].innerHTML = 'O'
		checkconfirm[3].style="color: #f0f7f5 !important"
	}else{
		checkconfirm[3].innerHTML = '형식에 맞게 입력해주세요'
		checkconfirm[3].style="color: red"
	}
}

/* 아이디 형식 + 중복 검사 */
function idcheck(){
	if(checkconfirm[5].innerHTML=='O'){ // 아이디 재선택 
		document.querySelector('.member_id').disabled = false;
		document.querySelector('.member_id').value = ''
		document.querySelector('.checkmId').innerHTML = `아이디 중복확인`
		checkconfirm[5].innerHTML = ''
		checkconfirm[5].style="color: red"
	}else{
		let member_id = document.querySelector('.member_id').value
		let idj = /^[a-z0-9]{5,12}$/
		if(idj.test(member_id)){
			$.ajax({
				url : "/pangpang/member/confirm" ,
				method : "get" , 
				data : {"member_id":member_id, "type":2} ,
				success : (r)=>{ 
					if(r=='false'){
						alert('사용 가능한 아이디입니다.')
						document.querySelector('.member_id').disabled = true;
						document.querySelector('.checkmId').innerHTML = `아이디 재입력`
						checkconfirm[5].innerHTML = 'O'
						checkconfirm[5].style="color: #f0f7f5 !important"
					}else{
						alert('사용 중인 아이디입니다.')
						return
					}
				}
			})
		}else{ 
			alert('아이디 형식에 맞게 작성하여 주십시오.')
		}
	}
	
}

/* 비밀번호 유효성검사 */
function pwdcheck(){
	let member_pwd = document.querySelector('.member_pwd').value
	let pwdj = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d$@$!%*#?&]{8,20}$/
	let member_id = document.querySelector('.member_id').value
	
	if(!pwdj.test(member_pwd)){
		checkconfirm[6].innerHTML = '형식에 맞게 입력해주세요'
		checkconfirm[6].style="color: red"
	}else if(/(\w)\1\1\1/.test(member_pwd)){
		checkconfirm[6].innerHTML = '연속된 동일문자 사용불가'
		checkconfirm[6].style="color: red"
	}else if(member_pwd.search(member_id)>-1){
		checkconfirm[6].innerHTML = '아이디를 포함한 비밀번호 불가능'
		checkconfirm[6].style="color: red"
	}else{
		checkconfirm[6].innerHTML = 'O'
		checkconfirm[6].style="color: #f0f7f5 !important"
	}
}

/* 비밀번호 확인 검사 */
function pwdconfirmcheck(){
	let member_pwd = document.querySelector('.member_pwd').value
	let member_pwdconfirm = document.querySelector('.member_pwdconfirm').value
	if(member_pwd==member_pwdconfirm){
		checkconfirm[7].innerHTML = 'O'
		checkconfirm[7].style="color: #f0f7f5 !important"
	}else{
		checkconfirm[7].innerHTML = '일치하지 않습니다.'
		checkconfirm[7].style="color: red"
	}
}

/* 1. 회원가입 - 첨부파일 없음 */ 
function signup(){
	namecheck()
	birthcheck()
	phonecheck()
	pwdcheck()
	pwdconfirmcheck()
	
	// 유효성검사에 대한 체크 
	let count = 0 ;
	for( let i = 0 ; i<checkconfirm.length ; i++ ){
		if( checkconfirm[i].innerHTML == 'O' ){ count++ }
	}
	if( count != 7 ){return;}

	
	let info = {
		member_name : document.querySelector('.member_name').value,
		member_birth : setbirth(),
		member_email : setemail(),
		member_phone : setphone(),
		member_id : document.querySelector('.member_id').value,
		member_pwd : document.querySelector('.member_pwd').value,
		member_address : document.querySelector('.member_address').innerHTML
	}
	console.log(info)
	
	$.ajax({
		url : "/pangpang/member/info" ,
		method : "post" , 
		data : info ,
		success : (r)=>{ 
			console.log(r)
			if(r="true"){
				alert('회원가입 성공')
				location.href="/pangpang/main.jsp"
			}
			
		}
	}) // ajax end	 
	
	/*
	let signupForm = document.querySelectorAll('.signupForm')[0];
	let signupFormData = new FormData( signupForm );
	signupFormData.set("member_birth",setbirth())
	signupFormData.set("member_email",setemail())
	signupFormData.set("member_phone",setphone())
	signupFormData.set("member_marketingSms",marketingSms)
	signupFormData.set("member_marketingEmail",marketingEmail)
	let member_id = document.querySelector('.member_id').value
	signupFormData.set("member_id",member_id)
	console.log(signupFormData)
	
	$.ajax({
		url : "/hotel/member/info",				
		method : "post",					
		data : signupFormData , 			
		contentType : false ,			
		processData : false ,			
		success : (r)=>{
			console.log( 'ajax 응답');	
			console.log( r );
		}
	})
	*/
} // end 
























