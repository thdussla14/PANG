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


getMember()
function getMember(){
	$.ajax({
		url : "/pangpang/member/info" ,
		method : "get" , 
		data : { "type":2 ,"member_no":memberInfo.member_no} ,
		success : (r)=>{ 
			console.log(r)
			
			
			document.querySelector('.member_address').innerHTML = r.member_address;
			document.querySelector('.member_email').value = r.member_email;
			document.querySelector('.member_id').value = r.member_id;
			document.querySelector('.member_name').value = r.member_name;
			document.querySelector('.member_no').value = r.member_no;
			document.querySelector('.member_phone').value = r.member_phone;
			//document.querySelector('.member_pwd').value = r.member_pwd;
			//document.querySelector('.member_rank').value = r.member_rank;
			document.querySelector('.member_birth').value = r.member_birth;
			getAccount()
			getOrderList()
		}
	})
}

function update(){
	console.log('마이페이지 수정')
	let info = {
		type:1,
		member_no : document.querySelector('.member_no').value,
		member_name : document.querySelector('.member_name').value,
		member_birth : document.querySelector('.member_birth').value,
		member_email : document.querySelector('.member_email').value,
		member_phone : document.querySelector('.member_phone').value,
		member_id : document.querySelector('.member_id').value,
		member_address : document.querySelector('.member_address').innerHTML,
		member_rank: document.querySelector('.member_rank').value
	}
	console.log(info)
	
	$.ajax({
		url : "/pangpang/member/info" ,
		method : "put" , 
		data : info ,
		success : (r)=>{ 
			console.log(r)
			if(r=="true"){
				alert('수정 성공')
				location.reload();
			}else{
				alert('수정실패')
				location.reload();
			}
			
		}
	}) // ajax end	*/ 
}

function dropmember(){
	let member_pwd = prompt('비밀번호')
	$.ajax({
		url : "/pangpang/member/info" ,
		method : "delete" , 
		data : {"type":1,"member_pwd":member_pwd} ,
		success : (r)=>{ 
			console.log(r)
			if(r=="true"){
				alert('탈퇴 성공')
				location.href='/pangpang/member/logout.jsp'
			}else{
				alert('탈퇴 실패[비밀번호가 다릅니다]')
			}
			
		}
	}) // ajax end	*/ 
}

/* 비밀번호 변경 모달 */
function onpenUpdatepwdModal( ){
	let html = `<div class="modal_content2 con2">
						<table style="width: 350px" class="modal_table">
							<tr>
								<th style="width: 120px" class="modal_table_th">현재 비밀번호</th>
								<td class="modal_table_td">
									<input class="input1 member_pwd" type="password">
								</td>
							</tr>
							<tr>
								<th class="modal_table_th">새 비밀번호</th>
								<td class="modal_table_td">
									<input class="input1 member_npwd" type="password">
								</td>
							</tr>
							<tr>
								<th class="modal_table_th">새 비밀번호 확인</th>
								<td class="modal_table_td">
									<input class="input1 member_npwdconfirm" type="password">
								</td>
							</tr>
						</table>
					</div>
					<div class="modal_btns">
						<button onclick="updatepwd()" class="findpwd_btn" type="button">확인</button>
					</div>`
	document.querySelector('.updatepwd_box').innerHTML=html
	document.querySelector('.updatepwd_box').style = ''
	document.querySelector('.updatepwd_modal_wrap').style.display ='flex';
}
function closeUpdatepwdModal(){
	document.querySelector('.updatepwd_modal_wrap').style.display ='none';
}

// * confirm span 모두 가져오기 --> 여러개의 span이 배열/리스트 객체에 대입
let checkconfirm = 0

/* 비밀번호 유효성검사 */
function pwdcheck(){
	let member_npwd = document.querySelector('.member_npwd').value
	let pwdj = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d$@$!%*#?&]{8,20}$/
	let member_id = memberInfo.member_id
	let member_pwd = document.querySelector('.member_pwd').value
	
	if(!pwdj.test(member_npwd)){
		alert( '형식에 맞게 <br>입력해주세요' )
	}else if(/(\w)\1\1\1/.test(member_npwd)){
		alert( '연속된 동일문자 사용불가')
		
	}else if(member_npwd.search(member_id)>-1){
		alert('아이디를 포함한 비밀번호 불가능')
		
	}else if(member_npwd==member_pwd){
		alert('기존 비밀번호 불가능')
		
	
	}else{
		checkconfirm++;
		
	}
}

/* 비밀번호 확인 검사 */
function pwdconfirmcheck(){
	let member_npwd = document.querySelector('.member_npwd').value
	let member_npwdconfirm = document.querySelector('.member_npwdconfirm').value
	if(member_npwd==member_npwdconfirm){
		checkconfirm++;
	}else{
		alert('일치하지 않습니다.')
		
	}
}

function updatepwd(){
	checkconfirm = 0
	pwdcheck()
	pwdconfirmcheck()
	
	if( checkconfirm != 2 ){return;}
	
	let info = {
		type : 2,
		member_npwd : document.querySelector('.member_npwd').value,
		member_pwd : document.querySelector('.member_pwd').value,
	}
	console.log(info)
	
	$.ajax({
		url : "/pangpang/member/info" ,	// 서블릿 클래스의 @WebServlet("/member")
		method :"put" ,			// 메소드 선택
		data : info ,
		success : (r)=>{ 
			console.log(r)
			if( r == 'true'){
				alert('비밀번호 변경 성공');
				location.href="/pangpang/member/logout.jsp"; // 해당 페이지 이동 */
			}else{ alert('비밀번호 변경 실패[비밀번호가 틀렸습니다]') }
		} // success end 
	}) // ajax end 
}

/* 계좌등록 모달 */
function openAddAccountModal( ){
	let html = `<div class="modal_content2 con2">
						<table class="modal_table">
							<tr>
								<th class="modal_table_th">은행</th>
								<td class="modal_table_td">
									<select class="account_bank">
										<option>카카오뱅크</option>
										<option>농협</option>
										<option>국민</option>
										<option>신한</option>
										<option>우리</option>
										<option>기업</option>
										<option>하나</option>
										<option>새마을금고</option>
										<option>우체국</option>
										<option>SC제일</option>
										<option>대구</option>
										<option>부산</option>
										<option>경남</option>
										<option>광주</option>
										<option>신협</option>
										<option>수협</option>
										<option>산업</option>
										<option>전북</option>
										<option>제주</option>
										<option>씨티</option>
										<option>케이뱅크</option>
										<option>토스뱅크</option>
									</select>
								</td>
							</tr>
							<tr>
								<th class="modal_table_th">계좌번호</th>
								<td class="modal_table_td">
									<input class="input1 account_number" type="text">
								</td>
							</tr>
						</table>
					</div>
					<div class="modal_btns">
						<button onclick="addAccount()" class="findpwd_btn" type="button">확인</button>
					</div>`
	document.querySelector('.addAccount_box').innerHTML=html
	document.querySelector('.addAccount_box').style = ''
	document.querySelector('.addaccount_modal_wrap').style.display ='flex';
}
function closeAddAccountModal(){
	document.querySelector('.addaccount_modal_wrap').style.display ='none';
}

function addAccount(){
	let account_number = document.querySelector('.account_number').value;
	let account_bank = document.querySelector('.account_bank').value;
	let check = /^[0-9]+$/; 
	if (!check.test(account_number)) {
	    alert("숫자만 입력 가능합니다.");
	    return;
	}
	
	let info = {
		"account_bank":account_bank,
		"account_number":account_number
	}
	$.ajax({
		url : "/pangpang/member/account" ,	// 서블릿 클래스의 @WebServlet("/member")
		method :"post" ,			// 메소드 선택
		data : info ,
		success : (r)=>{ 
			console.log(r)
			if( r == 'true'){
				alert('계좌 등록 성공');
				location.reload();
			}else{ alert('계좌 등록 실패') }
		} // success end 
	}) // ajax end 
}

function getAccount(){
	$.ajax({
		url : "/pangpang/member/account" ,
		method : "get" , 
		success : (r)=>{ 
			console.log(r)
			let html = `<tr>
						<th  width="10%">	은행	 </th> 
						<th class="member_rank1"> 
							계좌번호
						</th>
						<th>
							비고
						</th>
					</tr>`
			r.forEach((o)=>{
				html += `<tr>
							<td  width="10%">	${o.account_bank}	 </th> 
							<td class="member_rank1"> 
								${o.account_number}
							</td>
							<td>
								<button onclick="deleteAccount(${o.account_no})" class="btn" type="button">삭제</button>
							</td>
						</tr>`
			})
				
			document.querySelector('.accountTable').innerHTML=html	
		}
	})
}

function deleteAccount(account_no){
	$.ajax({
		url : "/pangpang/member/account" ,	// 서블릿 클래스의 @WebServlet("/member")
		method :"delete" ,			// 메소드 선택
		data : {"account_no":account_no} ,
		success : (r)=>{ 
			console.log(r)
			if( r == 'true'){
				alert('계좌 삭제 성공');
				location.reload();
			}else{ alert('계좌 삭제 실패') }
		} // success end 
	}) // ajax end 
}

// * pageObject : 현재페이지, 검색, 전송타입 보관된 객체 
let pageObject = {
	page 	 : 1,
	key 	 : "key",
	keyword  : "keyword", 
	type 	 : -1,
	listsize : 10,
	mno		 : 0,
}
getOrderList()
// 주문 내역 
function getOrderList(){
	pageObject.mno = memberInfo.member_no;	
	$.ajax({
		url 	: "/pangpang/order",
		method	: "get",
		data 	: pageObject,
		success	: (r)=>{
			console.log('결과1')
			console.log(r)
			let html = ``;
			if( r.orderList.length == 0){
				html += `<tr> <th> 주문한 내역이 없습니다. </th> </tr>`
			}else{
				r.orderList.forEach((o)=>{
				 html += `<table class="item table table1">
				 		<tr>
							<th width="10%"> 주문번호	  </th> 
							<td >  ${o.ordermanagement_no} </td>
						</tr>
						<tr>
							<th width="10%"> 주문일자	  </th> 
							<td >  ${o.ordermanagement_date} </td>
						</tr>				
						<tr>
							<th width="10%"> 주문상태	  </th> 
							<td > ${ o.ordermanagement_state==1?'결제확인중':
									o.ordermanagement_state==2?'결제확인':
									o.ordermanagement_state==3?'배송지연':
									o.ordermanagement_state==4?'배송중':
									o.ordermanagement_state==5?'배송완료':'거래완료'
							} </td>
						</tr>`;
								
				$.ajax({
					url 	: "/pangpang/order",
					method	: "get",
					data 	: {"type":-2,"ordermanagement_no":o.ordermanagement_no},
					async	: false,
					success	: (r)=>{
						console.log('결과2')
						console.log(r)
						
						r.list.forEach((o)=>{
							html += `<tr>
										<td rowspan="2"> <img src="/pangpang/product/pimg/${o.product_img}" width="50px" height="50px"> </td> 
										<td > ${o.product_name}</td>
									</tr>
									<tr>
										<td > ${o.product_price.toLocaleString()} 원 <span> ${o.cart_amount+o.product_unit} </span> </td>
									</tr>`
						})
						
						html += 	`<tr>
										<th> 결제정보 </th> 
										<td > ${r.payment_how+" / "+r.payment_date+" / "+r.payment_price.toLocaleString()} 원 </td>
									</tr>
									</table>`
																			
						}// success e
					}); // ajax e	
				})
			}
			document.querySelector('.receiver_info').innerHTML = html;
		}// success e
	});	// ajax e
}		
			
			
			
			
			
			