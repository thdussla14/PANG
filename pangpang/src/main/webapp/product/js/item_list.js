console.log('item_list js')

// * pageObject : 현재페이지, 검색, 전송타입 보관된 객체 
let pageObject = {
	page 	 : 1,
	key 	 : "key",
	keyword  : "keyword", 
	type 	 : 1,
	listsize : 10,
	cno 	 : 0
}


// 1. 품목 전체 출력
getItemList(1)
function getItemList(page){
	pageObject.page = page;
	$.ajax({
		url 	: "/pangpang/product",
		method	: "get",
		data 	: pageObject,
		async	: false,
		success	: (r)=>{
			console.log(r)			
			let html = `<tr>
							<th class="categorylist"> 카테고리명 </th><th> 제품번호 </th><th> 제품이미지 </th><th> 제품명 </th><th> 제품규격 </th>
							<th> 포장단위 </th><th> 잔여재고 </th><th> 비고 </th>
						</tr>`;						
			r.itemList.forEach((o)=>{
				html += `<tr>
							<td> ${o.category_name}  </td>	<td> ${o.product_no}   </td> 
							<td> <img src="/pangpang/product/pimg/${o.product_img}" width="50px" height="50px"> </td> <td> ${o.product_name}  </td> 	
							<td> ${o.product_option} </td> 	<td> ${o.product_unit} </td> <td> ${o.product_count} </td>	
							<td>
								<button class="updatebtn btn" onclick="openmodal_U(${o.product_no})" type="button"> 수정 </button>
								<button class="deletebtn btn" onclick="openmodal_D(${o.product_no})" type="button"> 삭제 </button>
							</td>		
						</tr>`;
			})			
			document.querySelector('.itemlist').innerHTML = html ;
			
			$.ajax({
				url 	: "/pangpang/category",
				method	: "get",
				async	: false,
				success	: (c)=>{
					console.log(c)
					let html = `<select class="category" onchange="setCno()">
									<option> 카테고리명 </option>`;
					c.forEach((o)=>{
						html += `<option value="${o.category_no}"> ${o.category_name} </option>`;
					})
					html += `</select>`
					document.querySelector('.categorylist').innerHTML = html ;	
					
					html = '' ;
					html += page <=1 ? ``:
						`<li class="page-item"><a class="page-link" onclick="getItemList(${page-1})"> &laquo;  </a> </li>`;
					for(let i=r.startbtn ; i<=r.endbtn ; i++){
						html += `<li class="page-item" ><a class="page-link" onclick="getItemList(${i})"> ${i} </a> </li>`
					}
					html += page >= r.totalpage ? ``:
						`<li class="page-item"><a class="page-link" onclick="getItemList(${page+1})"> &raquo;  </a> </li>`
						
					document.querySelector('.pagination').innerHTML = html;
												
				} // success e
			}) // ajax e
			
			
		} // success e
	}) // ajax e		
}
// 2. 검색된 품목 리스트 출력
function search(){	
	pageObject.key		= document.querySelector('.key').value;     
	pageObject.keyword 	= document.querySelector('.keyword').value;
	document.querySelector('.keyword').value = '';
	console.log(pageObject)
	getItemList(1)
	pageObject.key		= "key";
	pageObject.keyword 	= "keyword";
}
// 3. 카테고리별 품목 리스트 출력
function setCno(){
	pageObject.type		= 2;
	pageObject.cno		= document.querySelector('.category').value;
	getItemList(1)
	pageObject.cno		= 0;
	pageObject.type		= 1;
}

// 3. 모달 영역
// 제품 등록용 모달
function openmodal_R(){	
	// 4. 등록 카테고리 목록 출력
	$.ajax({
		url 	: "/pangpang/category",
		method	: "get",
		async	: false,
		success	: (r)=>{
			console.log(r)
			let html = ``;
			r.forEach((o)=>{
				html += `<option value="${o.category_no}"> ${o.category_name} </option>`;
			})
			document.querySelector('.categorylist1').innerHTML = html ;
		} // success e
	}) // ajax e
	document.querySelector('.modalregister').style.display='flex';
}
function closemodal_R(){
	document.querySelector('.modalregister').style.display='none';
}
// 제품 수정용 모달
function openmodal_U(product_no){
	// 카테고리 목록 출력
	$.ajax({
		url 	: "/pangpang/category",
		method	: "get",
		async	: false,
		success	: (r)=>{
			console.log(r)
			let html = ``;
			r.forEach((o)=>{
				html += `<option value="${o.category_no}"> ${o.category_name} </option>`;
			})
			document.querySelector('.categorylist2').innerHTML = html ;
		} // success e
	}) // ajax e
	// 기존 제품 정보 호출
	$.ajax({
		url 	: "/pangpang/product",
		method	: "get",
		data 	: {"type":3, "pno":product_no},
		async	: false,
		success	: (r)=>{
			console.log(r)
			document.querySelector('.product_name_U').value = r.product_name;
			document.querySelector('.product_option_U').value = r.product_option;
			document.querySelector('.product_unit_U').value = r.product_unit;
			document.querySelector('.product_content_U').value = r.product_content;
			document.querySelector('.product_price_U').value = r.product_price;
			document.querySelector('.product_discount_U').value = r.product_discount;
		} // success e
	}) // ajax e

	// 
	let html = `<button onclick="item_update(${product_no})" class="modal_cancel btn" type="button"> 수정 </button>	
			   	<button onclick="closemodal_U()"  class="modal_cancel btn" type="button"> 닫기 </button>`;
	document.querySelector('.modalbtnbox_U').innerHTML = html;
	document.querySelector('.modalupdate').style.display='flex';

}
function closemodal_U(){
	document.querySelector('.modalupdate').style.display='none';
}
// 제품 삭제용 모달
function openmodal_D(product_no){
	let html = `<input type="hidden" class="pno" value="">
					
				<h3  class="modal_title">   해당 품목의 삭제를 진행하시겠습니까? </h3>		
				<div class="modal_content">
				<div class="modalbtnbox">
					<button onclick="item_delete(${product_no})" class="modal_cancel btn" type="button"> 삭제 </button>	
					<button onclick="closemodal_D()"   class="modal_cancel btn" type="button"> 닫기 </button>
				</div>					
				</div>`
	document.querySelector('.modal_box_delete').innerHTML = html;			
	document.querySelector('.modaldelete').style.display='flex';

}
function closemodal_D(){
	document.querySelector('.modaldelete').style.display='none';
}

// 품목 등록
function item_register(){
	
	let registerForm = document.querySelectorAll('.registerForm')[0];// 첫번째 form 가져오기	
	let registerFormData = new FormData(registerForm);
	
	$.ajax({
		url 	: "/pangpang/product",
		method	: "post",
		data 	: registerFormData,
		contentType : false,
        processData : false,
		success	: (r)=>{
			console.log(r)
			if(r == 'true'){alert('품목 등록 성공');closemodal_R();}
			else{alert('품목 등록 실패');closemodal_R();}
		} // success e
	}) // ajax e		
}

// 품목 수정
function item_update(product_no){

	let updateForm = document.querySelectorAll('.updateForm')[0];// 첫번째 form 가져오기	
	let updateFormData = new FormData(updateForm);
	updateFormData.append("product_no",product_no);
	console.log(updateFormData)
	for (var key of updateFormData.keys()) {

	  console.log(key);
	
	}
	for (var value of updateFormData.values()) {

	  console.log(value);
	
	}
	$.ajax({
		url 	: "/pangpang/product",
		method	: "put",
		data 	: updateFormData,
		contentType : false,
        processData : false,
		success	: (r)=>{
			console.log(r)
			if(r == 'true'){alert('품목 수정 성공');closemodal_U();getItemList(1);}
			else{alert('품목 수정 실패');closemodal_U();}
		} // success e
	}) // ajax e		
}

// 제품 삭제
function item_delete(product_no){
	$.ajax({
		url 	: "/pangpang/product",
		method	: "delete",
		data 	: {"pno":product_no},
		success	: (r)=>{
			console.log(r)
			if(r == 'true'){alert('품목 삭제 성공');closemodal_D();getItemList(1);}
			else{alert('품목 삭제 실패')}
			
		} // success e
	}) // ajax e		
}






 