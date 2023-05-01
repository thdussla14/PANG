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


let dropYn1 = true;
let dropYn2 = true;
let dropYn3 = true;
let dropYn4 = true;
// 드롭다운 구현
function viewMenu( no ){
	console.log( 'viewMenu 클릭됨' );
	
	if( no == 1 ){
		if( dropYn1 ){
			dropYn1 = false;
			document.querySelector('.main_menu1').style.display = 'block';	
		}else{
			dropYn1 = true;
			document.querySelector('.main_menu1').style.display = 'none';
		}
	}else if( no == 2 ){
		if( dropYn2 ){
			dropYn2 = false;
			document.querySelector('.main_menu2').style.display = 'block';
		}else{
			dropYn2 = true;
			document.querySelector('.main_menu2').style.display = 'none';
		}
	}else if( no == 3 ){
		if( dropYn3 ){
			dropYn3 = false;
			document.querySelector('.main_menu3').style.display = 'block';	
		}else{
			dropYn3 = true;
			document.querySelector('.main_menu3').style.display = 'none';
		}
	}else if( no == 4 ){
		if( dropYn4 ){
			dropYn4 = false;
			document.querySelector('.main_menu4').style.display = 'block';	
		}else{
			dropYn4 = true;
			document.querySelector('.main_menu4').style.display = 'none';
		}
	}
}



let sidebarYn = true;
function sidebar_onoff(){
	console.log( 'sideber 클릭됨' )
	if( sidebarYn ){
		sidebarYn = false;
		document.querySelector('.side_bar').style.left = "-300px";
		document.querySelector('.user_profile').style.left = "-300px";
		document.querySelector('.sidebar_onoff').innerHTML = `<i class="fas fa-chevron-right"></i>`
		document.querySelector('.chating_box').style.display = "none";
	}else{
		sidebarYn = true;
		document.querySelector('.side_bar').style.left = "0px";
		document.querySelector('.user_profile').style.left = "0px";
		document.querySelector('.sidebar_onoff').innerHTML = `<i class="fas fa-chevron-left"></i>`
	}
	
}


let loginInfoYn = true;
function login_info_scroll(){
	
	if( loginInfoYn ){
		loginInfoYn = false;
		document.querySelector('.connectlistbox').style.display = "block";
	}else{
		loginInfoYn = true;
		document.querySelector('.connectlistbox').style.display = "none";
	}
	
}

//---------------------------------------------------------------------------------------------------------------
/* 로그인 목록이 나오는 상자 */
let contentbox = document.querySelector('.contentbox')

let 클라이언트소켓 = null

if( memberInfo.member_id == null ){ // memberInfo : 헤더js 존재하는 객체
	alert('로그인 필수'); location.href="/pangpang/main.jsp";
}else{
	let html = 	`<div><img src="/jspweb/member/pimg/default.webp" class="hpimg" > </div>
				<div> ${memberInfo.member_name}[${memberInfo.member_id}]</div>
				`
	document.querySelector('.loginbox').innerHTML = html;
	console.log(memberInfo.member_id)
	// 1. 클라이언트소켓 생성 과 서버소켓 연결[@OnOpen]
	클라이언트소켓 = new WebSocket('ws://localhost:8080/pangpang/chatting/'+memberInfo.member_id );	
	// 클라이언트소켓 = new WebSocket('ws://localhost:8080/jspweb/chatting/'+memberInfo.mid );	
	클라이언트소켓.onopen = function(e){ 서버소켓연결(e) } // 클라이언트소켓 객체에 정의한 함수 대입
	클라이언트소켓.onmessage = function(e){ 메시지받기(e); }
	클라이언트소켓.onclose = function(e){  }
	클라이언트소켓.onerror = function(e){ alert('문제발생:관리자에게문의'+e) }
}
// 2. 클라이언트소켓이 접속했을때 이벤트/함수 정의
function 서버소켓연결( e ){ 
	console.log('연결됨')
}	// 접속했을때 하고 싶은 함수 정의
 		
// 4. 서버로부터 메시지가 왔을때 메시지 받기
function 메시지받기( e ){	// <------  e <----- getBasicRemote().sendText(msg)
		console.log(e)
	let data = JSON.parse( e.data );// 문자열json -> 객체json 형변환 
		console.log( data );
	// 명단[여러개=list/Array] vs 메시지정보[1개=dto/object]
	// Array 타입 확인 : Array.isArray( 객체 ) : 해당 객체가 배열/리스트이면 true
	if( Array.isArray( data ) ){
		let html ='';
		data.forEach( (o)=>{
			console.log(o)
			if(o.member_id!=memberInfo.member_id){
				html += `
					<button type="button" class="onePerson" onclick="chatbox(${o.member_no})">
						<div class="connectbox">
							<div><img src="/pangpang/member/img/default.webp" class="hpimg" > </div>
							<div class="name">  ${o.member_name}[${o.member_id}]		</div>
							<div class="message_alert${o.member_no} message_alert">  </div>
						</div>
					</button>
					`
			}
		} );
		document.querySelector('.connectlistbox').innerHTML = html;
	}else{
		if(chatboxYn==true){
			let msgbox = document.querySelector('.msgbox')
			msgbox.style.bottom = "0px";
			setTimeout( ()=>{  
				 msgbox.style.bottom = "-100px"; 
			} , 4000)
			
			document.querySelector('.message_alert'+data.chat_fmno).innerHTML = `<i class="fas fa-bell"></i>`
		}
		getcontent( data.chat_fmno ); // 채팅 받았을때 채팅방내 채팅내용 렌더링
	}
}

//---------------------------------------------------------------------------------------------------------------
// 채팅방 열기
let chatboxYn = true;
function chatbox(member_no){
	console.log(chatboxYn)
	if( chatboxYn ){
		chatboxYn = false;
		document.querySelector('.message_alert'+member_no).innerHTML =''
		$.ajax({
			url : "/pangpang/member/info" ,
			method : "get" , 
			data : { "type":2 ,"member_no":member_no} ,
			success : (r)=>{ 
				console.log(r)
				let html = `
					<div class="chat_info">
						<div class="mimgbox">
							<img src="/pangpang/member/img/default.webp" class="hpimg">
							<span class="pname"> ${r.member_name}[${r.member_id}] </span>
						</div>
						<div>
							<button onclick="closechatbox()" class="closebtn btn" type="button">X</button>
						</div>
					</div>
					
					<div class="chat_content">
						
					</div>
					
					<div class="chat_btn">
						<textarea class="chatinput" rows="" cols=""></textarea>
						<button onclick="sendchat(${member_no})" type="button">전송</button>
					</div>
						`;
				document.querySelector('.chating_box').innerHTML = html;
				getcontent(member_no);
				document.querySelector('.chating_box').style.display = "block";
			}
		})
	}else{
		chatboxYn = true;
		document.querySelector('.chating_box').innerHTML = '';
		document.querySelector('.chating_box').style.display = "none";
	}
}

// 채팅방 닫기
function closechatbox(){
	chatboxYn = true;
	document.querySelector('.chating_box').innerHTML = '';
	document.querySelector('.chating_box').style.display = "none";
}

// 4. 채팅 보내기[ db 처리 ]
function sendchat(member_no){
	let chat_msg = document.querySelector('.chatinput').value;
	let info ={
		"chat_tmno" : member_no , 
		"chat_msg" : chat_msg
	}
	console.log(info)
	console.log(chat_msg)
	$.ajax({
		url : "/pangpang/member/chat" ,
		method : "post" , 
		data : info ,
		success : (r)=>{ 
			console.log( r == 'true' )
			if( r == 'true'){
				document.querySelector('.chatinput').value = '';
				getcontent( member_no ); // 채팅창 목록 새로고침
			}
		}
	})
}

// 채팅내역 가져오기
function getcontent( member_no ){
	let chathtml = '';
	$.ajax({
		url : "/pangpang/member/chat" ,
		method : "get",
		data : { "youmno" : member_no },
		async : false , /* 동기식 */
		success : (r)=>{
			r.forEach( ( o )=> {
				if( o.chat_fmno == memberInfo.member_no ){ // 현재 로그인된 회원과 보낸 사람과 일치하면 
					chathtml += `<div class="sendbox"> ${ o.chat_msg }</div>`
				}else{ 
					chathtml += `<div class="receivebox"> ${ o.chat_msg }</div>`
				}
			})	
		}
	}) // end 
	document.querySelector('.chat_content').innerHTML = chathtml;
}


 
