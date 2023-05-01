console.log('order_list js')

// * pageObject : 현재페이지, 검색, 전송타입 보관된 객체 
let pageObject = {
	page 	 : 1,
	key 	 : "key",
	keyword  : "keyword", 
	type 	 : 0,
	listsize : 10,
	mno		 : 0,
}

let ordermanagement_no = -1;

// 전체 주문 리스트 출력
getOrderList(1)
function getOrderList(page){
	// 해당 함수로부터 페이징 번호 받기 
	console.log(page)
	pageObject.page = page ; 
		
	$.ajax({
		url 	: "/pangpang/order",
		method	: "get",
		data 	: pageObject,
		async	: false,
		success	: (r)=>{
			console.log(r)
			let html = `<tr>
							<th> 주문번호 </th><th> 주문일자 </th>
							<th>  
								<select class="ordermanagement_state" onchange="getOrderList_type()">
									<option value="-1"> 주문상태 	 </option>
									<option value="1">  결제확인중  </option>
									<option value="2">  결제확인 	 </option>
									<option value="3">  배송지연 	 </option>
									<option value="4">  배송중 	 </option>
									<option value="5">  배송완료 	 </option>
									<option value="6">  거래완료 	 </option>
								</select>
							</th>
							
							<th> 배송주소 </th><th> 주문회원 </th><th> 상세보기 </th><th> 상태변경 </th>
						</tr>`;	
			r.orderList.forEach((o)=>{
				html += `<tr>
							<td> ${ o.ordermanagement_no} 		</td>
							<td> ${ o.ordermanagement_date} 	</td>
							<td> ${ o.ordermanagement_state==1?'결제확인중':
									o.ordermanagement_state==2?'결제확인':
									o.ordermanagement_state==3?'배송지연':
									o.ordermanagement_state==4?'배송중':
									o.ordermanagement_state==5?'배송완료':'거래완료'
							} </td>
							<td> ${o.ordermanagement_address} 	</td>
							<td> ${o.member_id} 			  	</td>
							<td> <button onclick="openmodal(${ o.ordermanagement_no})" class="btn" type="button"> 상세보기 </button></td>
							<td> 
								<select class="ordermanagement_state_change${o.ordermanagement_no}">
									<option value="1">  결제확인중  </option>
									<option value="2">  결제확인 	 </option>
									<option value="3">  배송지연 	 </option>
									<option value="4">  배송중 	 </option>
									<option value="5">  배송완료 	 </option>
									<option value="6">  거래완료 	 </option>
								</select>
								<button onclick="updateState(${ o.ordermanagement_no})" type="button"> 변경 </button>					
							</td>
						</tr>`;
				});
			document.querySelector('.orderlist').innerHTML = html ;
			
			html = '' ;
			html += page <=1 ? ``:
				`<li class="page-item"><a class="page-link" onclick="getOrderList(${page-1})"> &laquo;  </a> </li>`;
			for(let i=r.startbtn ; i<=r.endbtn ; i++){
				html += `<li class="page-item" ><a class="page-link" onclick="getOrderList(${i})"> ${i} </a> </li>`
			}
			html += page >= r.totalpage ? ``:
				`<li class="page-item"><a class="page-link" onclick="getOrderList(${page+1})"> &raquo;  </a> </li>`
	
			document.querySelector('.pagination').innerHTML = html;
		}// success e
	}); // ajax e	
}
// 주문상태별 리스트 출력
function getOrderList_type(){
	pageObject.type = document.querySelector('.ordermanagement_state').value;
	getOrderList(1);	
}
// 검색된 리스트 출력
function search(){
	// 입력받은 데이터를 전역변수 필드에 대입 
	pageObject.key     = document.querySelector('.key').value;
	pageObject.keyword = document.querySelector('.keyword').value;
	console.log(pageObject)
	getOrderList(1);
	pageObject.key     = "key";
	pageObject.keyword = "keyword";
}



function openmodal(ordermanagement_no){
	ordermanagement_no = ordermanagement_no;
	
	detail(ordermanagement_no)
		
	document.querySelector('.modal_wrap_detail').style.display='flex';
}
function closemodal(){
	ordermanagement_no = -1;
	document.querySelector('.modal_wrap_detail').style.display='none';
}

// 주문상세 출력
function detail(ordermanagement_no){
	console.log(ordermanagement_no)
	$.ajax({
		url 	: "/pangpang/order",
		method	: "get",
		data 	: {"type":-2,"ordermanagement_no":ordermanagement_no},
		async	: false,
		success	: (r)=>{
			console.log(r)
			
			let html =`<tr><td colspan="3"> 제품정보 </td></tr>`;
			
			r.list.forEach((o)=>{
				html += `<tr>
							<td rowspan="2"> <img src="/pangpang/product/pimg/${o.product_img}" width="50px" height="50px"> </td> 
							<td colspan="2"> ${o.product_name}</td>
						</tr>
						<tr>
							<td colspan="2"> ${o.product_price.toLocaleString()} 원 <span> ${o.cart_amount+o.product_unit} </span> </td>
						</tr>`
			})
			html += 	`<tr>
							<td> 결제정보 </td> 
							<td colspan="2"> ${r.payment_how+" / "+r.payment_date+" / "+r.payment_price.toLocaleString()} 원 </td>
						</tr>`			

			document.querySelector('.orderdetail').innerHTML = html;
			
		}// success e
	}); // ajax e			

}

// 주문상태변경
function updateState(ordermanagement_no){

	let state = document.querySelector(`.ordermanagement_state_change${ordermanagement_no}`).value;

	$.ajax({
		url 	: "/pangpang/order",
		method	: "put",
		data 	: {"ordermanagement_no":ordermanagement_no, "state":state},
		async	: false,
		success	: (r)=>{
			console.log(r)
			if(r=='true'){alert('변경 성공하였습니다.');getOrderList(1);}
			else{alert('변경 실패하였습니다.')}
		}
	});
	
}


