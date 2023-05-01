console.log('cart js')


// 도착예정일 구하기   [오후 4시 이전 주문시 내일 도착 / 이후 주문수 모레 도착 ]

// 현재 시간 구하기 
let today 	= new Date(); 
let time 	= today.getHours();		// 시간 
console.log(time)
if(time<16){ 	
	today.setDate(today.getDate()+1);
}else{	
	today.setDate(today.getDate()+2); 		
}
	
let month 	= today.getMonth()+1;  	// 월
let date 	= today.getDate();  	// 날짜
let day 	= today.getDay();  		// 요일	일요일이 0, 월요일이1, 토요일이 6

console.log(today)
// 요일 문자로 변롼 
if(day==0){day='일';}
if(day==1){day='월';}
if(day==2){day='화';}
if(day==3){day='수';}
if(day==4){day='목';}
if(day==5){day='금';}
if(day==6){day='토';}

let totalprice = 0;
let cartList = [];
let checkboxes = document.querySelectorAll('input[name="cart"]');
// 회원제 기능
if(memberInfo == null){
	alert('회원제 기능입니다. 로그인 후 사용해주세요.');
	location.href="/pangpang/product/product_index.jsp";
}else{
	getCartList();
}
// 로그인한 회원 장바구니 제품 출력 
function getCartList(){
	$.ajax({
		url 	: "/pangpang/cart",
		method	: "get",
		async	: false,
		success	: (r)=>{
			console.log(r)
			if( r.length < 1){
				
				alert('장바구니가 비었습니다.'); location.href="/pangpang/product/product_index.jsp";
				
			}else{
				
				totalprice = 0;	
				let html = '';
				r.forEach((o)=>{
					if(o.stock==0){
						html += `<div class="soldout">
									<input type="checkbox"  disabled checked>
									<img class="cart_img"  src="/pangpang/product/pimg/${o.product_img}" alt="">
									
									<div class="product_info">
										<div class="pname"> ${o.product_name} <span class="stock"> 잔여수량 : ${o.stock+o.product_unit}</span></div>
										<div class="product_info_bottom"> 
											<div>
												${month+"/"+date}(${day})  도착 보장 (서울경기 기준)
											</div>
											<div>
												${o.product_price.toLocaleString()} 원 
												<select name="amount" class="수량${o.product_no}" onchange="setPrice(${o.product_no},${o.product_price})">
													<option value="1"> 1 ${o.product_unit}</option>
												</select>
											</div>
										</div>					
									</div>
				
									<div class="pprice pprice${o.product_no}">
										${(o.product_price*o.cart_amount).toLocaleString()} 원 <br>
										<img  class="mini_logo" alt="" src="/pangpang/product/pimg/PANG.png">
										<span class="mini_mark">팡팡배송</span>
									</div>								
								</div>`
						
					}else{
						cartList.push(o)	
						totalprice += (o.product_price*o.cart_amount);
						html += `<div class="cart_items">
									<input name="cart" value="${o.product_no}" type="checkbox" onclick="check()" onchange="setPrice(${o.product_no},${o.product_price})" >
									<img class="cart_img"  src="/pangpang/product/pimg/${o.product_img}" alt="">
									
									<div class="product_info">
										<div class="pname"> ${o.product_name} <span class="stock"> 잔여수량 : ${o.stock+o.product_unit}</span></div>
										<div class="product_info_bottom"> 
											<div class="date">
												${month+"/"+date}(${day})  도착 보장 (서울경기 기준)
											</div>
											<div>
												${o.product_price.toLocaleString()} 원 
												<select name="amount" class="수량${o.product_no}" onchange="setPrice(${o.product_no},${o.product_price})">
													<option value="1"> 1 ${o.product_unit}</option>
													<option value="2"> 2 ${o.product_unit}</option>
													<option value="3"> 3 ${o.product_unit}</option>
													<option value="4"> 4 ${o.product_unit}</option>
												</select>
											</div>
										</div>					
									</div>
				
									<div class="pprice pprice${o.product_no}">
										${(o.product_price*o.cart_amount).toLocaleString()} 원 <br>
										<img  class="mini_logo" alt="" src="/pangpang/product/pimg/PANG.png">
										<span class="mini_mark">팡팡배송</span>
									</div>								
								</div>`
						
					}

				})
				console.log(html)
				document.querySelector('.cartlist').innerHTML = html;
				
				// 총 가격 합산 출력 
				totalPrice()
				
				
				// 장바구니에 담긴 수량 출력
				r.forEach((o)=>{
					document.querySelector(`.수량${o.product_no}`).value = o.cart_amount;	
				})	
				
				// 전부 체크된 상태로 출력
				checkboxes = document.querySelectorAll('input[name="cart"]');
				checkboxes.forEach((o)=>{o.checked = true;})
				
			}
			
		}// success e
	}); // ajax e	
}// getCartList e


function totalPrice(){
	// 총 가격 합산 출력 
	if(totalprice < 19000 ){ // 총 가격이 19000원 미만 이면 배송비 3000원 추가
					
		document.querySelector('.now_pricebox').innerHTML 
		=`<span class="ad">다른 팡팡배송 상품을 추가하면 함께 무료배송 가능</span>`
					
		document.querySelector('.order_price').innerHTML 
		= `총 상품가격 ${totalprice.toLocaleString()} 원 + 총 배송비 3,000 원 = 총 주문금액 = ${(totalprice+3000).toLocaleString()} 원`;
					
	}else{// 총 가격이 19000원 이상이면 배송비 무료
		document.querySelector('.now_pricebox').innerHTML 
		=`<span class="ad"> 팡팡배송 무료배송 </span>`;
					
		document.querySelector('.order_price').innerHTML 
		= `총 상품가격 ${totalprice.toLocaleString()} 원 <i class="fas fa-plus-circle"></i> 총 배송비 0 원 <i class="fas fa-equals"> 총 주문금액 
			<i class="fas fa-equals"> ${totalprice.toLocaleString()} 원`;
	}
}


// 장바구니 수량 변경시 가격 변경 
function setPrice(pno,pprice){
	 	 
	checkboxes = document.querySelectorAll('input[name="cart"]'); 	 
	 	 
	let amount = document.querySelector(`.수량${pno}`).value;
	console.log(amount)
	
	document.querySelector(`.pprice${pno}`).innerHTML = `${(pprice * amount).toLocaleString()} 원 <br>
													<img  class="mini_logo" alt="" src="/pangpang/product/pimg/PANG.png">
													<span class="mini_mark">팡팡배송</span>`;	
													
	cartList.forEach((o)=>{
		if(o.product_no == pno){o.cart_amount = amount}
	})
		
	totalprice = 0;	
		
	cartList.forEach((o,i)=>{
		if(checkboxes[i].checked){
			totalprice +=  (o.product_price*o.cart_amount)
		}					
	})	
	
	totalPrice()																	
}

// 전체 선택 / 전체 선택 해제
function SelectAll() {

	  checkboxes = document.querySelectorAll('input[name="cart"]');
  	  
  	  if(checkboxes[checkboxes.length-1].checked){
		  checkboxes.forEach((o)=>{o.checked = true;})
	  }else{
		  checkboxes.forEach((o)=>{o.checked = false;})
	  }
	  //checkboxes.forEach((o)=>{o.checked = true;count++; productlist.push(o.value);})
	  //select.forEach((o)=>{ amountlist.push(o.value);})	  
}

// 일부 제품 선택 해제시 전체선택 체크 해제
function check(){

	let checkboxes = document.querySelectorAll('input[name="cart"]');
	
	checkboxes.forEach((o)=>{if(o.checked == false){checkboxes[checkboxes.length-1].checked = false;};return; })		
}

// 전체 장바구니 삭제 
function cartOutAll(){
	
	$.ajax({
		url 	: "/pangpang/cart",
		method	: "delete",
		async	: false,
		data	: {"type":1 },
		success	: (r)=>{
			console.log(r)
			if(r=='true'){alert('장바구니 비우기 성공'); location.href="/pangpang/index.jsp"}
			else{alert('장바구니 비우기 실패[관리자에게 문의해주세요]')}	
					
		}// success e
	}); // ajax e	
}// cartOutAll e

// 선택한 제품 장바구니 삭제
function cartOut(){		
	 
	  console.log(checkboxes)
	  
	  for(let i =0 ; i<(checkboxes.length-1) ; i++ ){	
		  console.log(checkboxes[i].checked)	 
		  console.log(checkboxes[i].value)
		  if(checkboxes[i].checked){
			$.ajax({
				url 	: "/pangpang/cart",
				method	: "delete",
				async	: false,
				data	: {"type":2, "pno":checkboxes[i].value},
				success	: (r)=>{
					console.log(r)	; if(r=='true'){console.log('장바구니 삭제 성공')}			
				}// success e
			}); // ajax e  
		  } // if e
	  }// for e
	  
	  // 장바구니 리스트 랜더링
	  location.href="/pangpang/product/cart.jsp";
	  
}// cartOut e

// 선택한 제품 주문하기
function order(){
	let orderlist = [];
	checkboxes = document.querySelectorAll('input[name="cart"]');

	cartList.forEach((o,i)=>{
		if(checkboxes[i].checked){
			orderlist.push(o)
		}			
	});	
	console.log(orderlist)
	
	// Json Object를 저장하기
	localStorage.setItem("orderlist", JSON.stringify(orderlist));
	
	location.href="/pangpang/product/order.jsp";
}

