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
			document.querySelector('.member_rank').value = r.member_rank;
			document.querySelector('.member_birth').value = r.member_birth;
			reportAll();
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


//전체 운행일지모음
function reportAll(){
	console.log("reportAll.js 진입");
	$.ajax({
		 url:"/pangpang/drivecar",
		 method:"get",
		 data:{"type":1},
		 success:(r)=>{
			 console.log("통신");
			 console.log(r);
				let html=`<tr>
                           <th width="10%"> 번호 </th>
                           <th width="10%"> 차량이름 </th>
                           <th width="10%"> 일련번호</th>
                           <th width="10%"> 행선지</th>	
                           <th width="10%"> 운행시작일자</th>
                           <th width="10%"> 운행종료일자</th>
                           <th width="10%"> 운행사유</th>
                           <th width="10%"> 운행일지내용</th>			
						</tr>`
			r.forEach((o,i)=>{
               html +=`                  
                  <tr>
                     <td> ${i+1}</td>
                     <td> ${o.carmanage_name} </td>
                     <td> ${o.carmanage_number} </td>
					 <td> ${o.drivecar_distance} </td>
					 <td> ${o.drivecar_str_date} </td>
					 <td> ${o.drivecar_end_date} </td>
					 <td> ${o.drivecar_parking} </td>
					 <td> ${o.report_content} </td>
                  </tr>`  
			})
			document.querySelector('.driveAlltable').innerHTML=html;			 
		 }
	})
}
			
			
			
			