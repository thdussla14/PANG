console.log( 'newheader.js2 실행');

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

let dropYn = true;
// 드롭다운 구현
function viewMenu(){
	console.log( 'viewMenu 클릭됨' );
	
	if( dropYn ){
		dropYn = false;
		document.querySelector('.main_menu').style.display = 'block';	
	}else{
		dropYn = true;
		document.querySelector('.main_menu').style.display = 'none';
	}
}


let sidebarYn = true;
function sidebar_onoff(){
	console.log( 'sideber 클릭됨' )
	if( sidebarYn ){
		sidebarYn = false;
		document.querySelector('.side_bar').style.left = "-300px";
		document.querySelector('.sidebar_onoff').innerHTML = `<i class="fas fa-chevron-right"></i>`
	}else{
		sidebarYn = true;
		document.querySelector('.side_bar').style.left = "0px";
		document.querySelector('.sidebar_onoff').innerHTML = `<i class="fas fa-chevron-left"></i>`
	}
	
}

//---------------------------------------------------------------------------------------------------------------
/* 채팅창 내용들이 출력되는 상자 */
let contentbox = document.querySelector('.contentbox')

let 클라이언트소켓 = null

if( memberInfo.member_id == null ){ // memberInfo : 헤더js 존재하는 객체
	alert('로그인하고 들어오세요~'); location.href="/pangpang/main.jsp";
}else{
	let html = 	`<div><img src="/jspweb/member/pimg/default.webp" class="hpimg" > </div>
				<div> ${memberInfo.member_name}[${memberInfo.member_id}]</div>`
	document.querySelector('.loginbox').innerHTML = html;
	console.log(memberInfo.member_id)
	// 1. 클라이언트소켓 생성 과 서버소켓 연결[@OnOpen]
	클라이언트소켓 = new WebSocket('ws://192.168.17.9:8080/pangpang/chatting/'+memberInfo.member_id );	
	// 클라이언트소켓 = new WebSocket('ws://localhost:8080/jspweb/chatting/'+memberInfo.mid );	
	클라이언트소켓.onopen = function(e){ 서버소켓연결(e) } // 클라이언트소켓 객체에 정의한 함수 대입
	클라이언트소켓.onmessage = function(e){ 메시지받기(e); }
	클라이언트소켓.onclose = function(e){ 연결해제(e) }
	클라이언트소켓.onerror = function(e){ alert('문제발생:관리자에게문의'+e) }
}
// 2. 클라이언트소켓이 접속했을때 이벤트/함수 정의
function 서버소켓연결( e ){ 
	console.log('연결됨')
}	// 접속했을때 하고 싶은 함수 정의
 		

// 4. 서버로부터 메시지가 왔을때 메시지 받기
function 메시지받기( e ){	// <------  e <----- getBasicRemote().sendText(msg)
	//console.log( e) ; console.log( e.data ); // e.data : 문자열타입  vs JSON.parse( e.data ) : 객체타입
	//console.log( JSON.parse( e.data ) ); // 문자열json -> 객체json 형변환 
	
	let data = JSON.parse( e.data );	// 전달받은 메시지 dto 
		console.log( data );
	
	// 명단[여러개=list/Array] vs 메시지정보[1개=dto/object]
		// Array 타입 확인 : Array.isArray( 객체 ) : 해당 객체가 배열/리스트이면 true
	if( Array.isArray( data ) ){
		let html ='';
		data.forEach( (o)=>{
			console.log(o)
			if(o.member_id!=memberInfo.member_id){
				html += `
					<button type="button" class="onePerson" onclick="chatbox()">
						<div class="connectbox">
							<div><img src="/jspweb/member/pimg/default.webp" class="hpimg" > </div>
							<div class="name">  ${o.member_name}[${o.member_id}]		</div>
						</div>
					</button>
					`
			}
		} );
		document.querySelector('.connectlistbox').innerHTML = html;
	}
}










// 6. 엔터키를 눌렀을때
function enterkey(){
	// 만약에 입력한 키 코드가 13[엔터] 이면 메시지전송
	if( window.event.keyCode == 13 ){
		보내기();
	}
}

 
