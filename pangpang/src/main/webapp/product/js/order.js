console.log('order js')

let totalprice=0;

// 모달 설정 
function openmodal_address(){
	document.querySelector('.modal_wrap_address').style.display='flex';
}
function closemodal_address(){
	document.querySelector('.modal_wrap_address').style.display='none';
}
function openmodal_account(){
	getPaylist()
	document.querySelector('.modal_wrap_account').style.display='flex';
}
function closemodal_account(){
	document.querySelector('.modal_wrap_account').style.display='none';
}


let orderlist = JSON.parse(localStorage.getItem("orderlist"));
//------------------------------------------------------------------------------------- 주문제품 불러오기
printorder()
function printorder(){	
	console.log(orderlist)
	totalprice=0;
	
	let html = `<tr>
					<th width="10%"> 제품이름 </th> 
					<th width="80%"> 제품정보 </th> 
					<th width="10%"> 제품수량 </th> 
				</tr>`
		
	orderlist.forEach((o)=>{
		
		totalprice += (o.product_price * o.cart_amount);

		html += `	<tr>
						<td> 
							<div><img class="order_img"  src="/pangpang/product/pimg/${o.product_img}" alt=""></div>
						</td>
						<td>
							 ${o.product_name}, ${o.product_option}		
						</td>
						<td>
							 ${o.cart_amount+o.product_unit}
						</td>
					</tr>`
	})
		
		if(totalprice<19000){totalprice=totalprice+3000}else{totalprice}
		
		html += `<tr>
					<th colspan="3" class="totalpricebox"> 총 결제 금액 <span class="totalprice"> ${totalprice.toLocaleString()}</span> 원 </th>
				</tr>`
		
	
		document.querySelector('.buyproduct_info').innerHTML = html ;
}


//------------------------------------------------------------------------------------- 회원정보 불러오기
getMemberInfo()
function getMemberInfo(){

	document.querySelector('.member_name').innerHTML  		= memberInfo.member_name;
	document.querySelector('.member_email').innerHTML 		= memberInfo.member_email;
	document.querySelector('.member_phone').innerHTML 		= memberInfo.member_phone;
	
	document.querySelector('.receiver_name').innerHTML 		= memberInfo.member_name;
	document.querySelector('.receiver_phone').innerHTML   	= memberInfo.member_phone;
	document.querySelector('.receiver_address').innerHTML 	= memberInfo.member_address;

}

//------------------------------------------------------------------------------------- 받는사람 수정
// 수정 선택시 수정 입력란 오픈
function update_recieverinfo(){	
	
	document.querySelector('.receiver_info').innerHTML = 
	
		`<tr>
			<th width="10%"> 이름	  </th> 
			<td> <input class="receiver_name"  type="text" ></td>
		 </tr>
		 <tr>
			<th  width="10%">휴대폰 번호 </th> 
			<td> <input class="receiver_phone" type="text" ></td>
		 </tr>						
		 <tr>
			<th width="10%"> 배송주소 </th> 
			<td><span class="delivery_address"></span> <button class="btn" type="button" onclick="openmodal_address()"> 주소 찾기 </button> </td>
		 </tr>
		 <tr>
			<th width="10%"> 배송요청사항  </th> 
			<td> 
				<select class="delivery_option">  
					<option value="1"> 문앞 		  </option>
					<option value="2"> 경비실 	  </option>
					<option value="3"> 무인택배보관함 </option>
				</select>
			</td>
		 </tr>`
	
	// 버튼 변경 [수정] => [완료]/[취소]	 
	document.querySelector('.Rinfo').innerHTML 
	= `	<button class="updatebtn btn" onclick="update_recieverinfo_complete()" type="button"> 완료 </button>
		<button class="updatebtn btn" onclick="update_recieverinfo_cancle()"   type="button"> 취소 </button>` 
		
	document.querySelector('.receiver_name').value 			= memberInfo.member_name;
	document.querySelector('.receiver_phone').value   		= memberInfo.member_phone;
	document.querySelector('.delivery_address').innerHTML 	= memberInfo.member_address;
	
}
// 수정 완료시 입력한 회원 정보 출력
function update_recieverinfo_complete(){
	
	let receiver_name 		= document.querySelector('.receiver_name').value;
	let receiver_phone 		= document.querySelector('.receiver_phone').value;
	let delivery_address 	= document.querySelector('.delivery_address').innerHTML;
	let delivery_option		= document.querySelector('.delivery_option').value;
	
	document.querySelector('.receiver_info').innerHTML = 		
			`<tr>
				<th width="10%"> 이름	  </th> 
				<td class="receiver_name">${receiver_name} <span>기본배송지</span></td>
			</tr>
			<tr>
				<th  width="10%">휴대폰 번호 </th> 
				<td  class="receiver_phone">${receiver_phone}  </td>
			</tr>						
			<tr>
				<th width="10%"> 배송주소 </th> 
				<td class="receiver_address">${delivery_address}   </td>
			</tr>
			<tr>
				<th width="10%"> 배송요청사항  </th> 
				<td class="delivery_option"> ${delivery_option==1?'기본: 문앞':delivery_option==2?'경비실':'무인택배보관함'} </td>
			</tr>`
	document.querySelector('.Rinfo').innerHTML 
		= `<button class="updatebtn btn" onclick="update_recieverinfo()" type="button"> 수정 </button>` 			
}
// 수정 취소시 기존 로그인 회원 정보 재호출
function update_recieverinfo_cancle(){
	
	document.querySelector('.receiver_info').innerHTML = 		
			`<tr>
				<th width="10%"> 이름	  </th> 
				<td class="receiver_name"> <span>기본배송지</span></td>
			</tr>
			<tr>
				<th  width="10%">휴대폰 번호 </th> 
				<td  class="receiver_phone"> </td>
			</tr>						
			<tr>
				<th width="10%"> 배송주소 </th> 
				<td class="receiver_address">  </td>
			</tr>
			<tr>
				<th width="10%"> 배송요청사항  </th> 
				<td> 일반 : 문앞 </td>
			</tr>`
			
	document.querySelector('.Rinfo').innerHTML 
		= `<button class="updatebtn btn" onclick="update_recieverinfo()" type="button"> 수정 </button>` 
		
	getMemberInfo()
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
	
	document.querySelector('.delivery_address').innerHTML = address_select+","+address_detail;
	
	closemodal_address();

}
 //------------------------------------------------------------------------------------- 결제

 // 팡팡페이 등록 계좌 목록 모달
 function getPaylist(){
      $.ajax({
		url 	: "/pangpang/member/account",
		method	: "get",
		async	: false,
		success	: (r)=>{
			console.log(r)
			let html = ``;
			
			r.forEach((o)=>{
				html +=`<tr>
							<td>${o.account_bank}	</td>
							<td>${o.account_number}	</td>
							<td><button onclick="requestPay(4)" class="modal_btn btn"> 선택 </button></td>
						</tr>`;	
			})	
			document.querySelector('.accountlist').innerHTML = html;		
		}// success e
	}) // ajax e
 }

  // 회원 식별 번호   
  const IMP = window.IMP;  // 생략 가능
  IMP.init("imp47415848"); // 예: imp00000000a
   
  function requestPay(type) {
	console.log(orderlist)
	console.log(totalprice)
	
	let info = {
	      pay_method	: "card",
	      merchant_uid	: "ORD20180131-0000011",  	// 주문번호
	      name			: "PANGPANG",				// 상호명
	      amount		: totalprice,               // 결제금액 
	      buyer_email	: memberInfo.member_email,
	      buyer_name	: document.querySelector('.receiver_name').innerHTML,
	      buyer_tel		: document.querySelector('.receiver_phone').innerHTML,
	      buyer_addr	: document.querySelector('.receiver_address').innerHTML,
	      buyer_postcode: "01181",
	      member_no		: memberInfo.member_no,
	      orderlist		: JSON.stringify(orderlist)
	}
	console.log(info);
	if(type==1){
		info.pg="html5_inicis";
		
	}else if(type==2){
		info.pg='kakaopay';
		
	}else if(type==3){
		info.pg='tosspay';
		
	}else if(type==4){
		info.pg='pangpangpay';
		
	}

    IMP.request_pay(info, function (rsp) { 		// callback
    	if (rsp.success) {						// 결제 성공 시 로직
       
      	} else {								// 결제 실패 시 로직 = 테스트용이므로 결제 취소시 진행으로
      		alert('결제가 완료되었습니다.');
      		// 주문 DB 등록
      			$.ajax({
					url 	: "/pangpang/order",
					method	: "post",
					async	: false,
					data	: info,
					success	: (r)=>{
						console.log(r)
						if(r=='true'){
							cartOutAll();
							alert('주문이 완료되었습니다.')
							location.href="/pangpang/product/product_index.jsp";
						}else{
							alert('주문이 취소되었습니다.[관리자에게 문의]')
						}
					}
			});
       	}
    });
  }
  
  
  // 주문 완료 후 전체 장바구니 삭제 
	function cartOutAll(){
		
		$.ajax({
			url 	: "/pangpang/cart",
			method	: "delete",
			async	: false,
			data	: {"type":1 },
			success	: (r)=>{
				console.log('cartoutall'+r)							
			}// success e
		}); // ajax e	
	}// cartOutAll e
	
	
	
	
	
	