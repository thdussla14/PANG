console.log('stock_list js')

// * pageObject : 현재페이지, 검색, 전송타입 보관된 객체 
let pageObject = {
	page 	 : 1,
	key 	 : "key",
	keyword  : "keyword", 
	type 	 : 0,
	listsize : 10
}

// 전체 재고 내역 출력
getstockList(1)
function getstockList(page){	
	pageObject.page = page ; 
	$.ajax({
		url 	: "/pangpang/stock",
		method	: "get",
		data 	: pageObject,
		async	: false,
		success	: (r)=>{
			console.log(r)

			let html = `<tr>
							<th width="10%"> 관리번호 </th>
							<th width="10%"> 제품번호 </th> 
							<th width="5%">  
								<select class="stockmanagementtype" onchange="getstockList_type()">
									<option value="0"> 구분 </option>
									<option value="1"> 입고 </option>
									<option value="2"> 출고 </option>
									<option value="3"> 폐기 </option>
								</select>
							</th>
							<th width="10%"> 업체 </th>
							<th width="20%"> 일자 </th>
							<th width="5%"> 수량 </th>
							<th width="10%"> 단가 </th>
							<th width="20%"> 예정 폐기일 </th>
							<th width="10%"> 비고 </th>
						</tr>`;	
						
			if(r.stockList.length<1){
				html += `<tr><td colspan="9">검색된 내용이 없습니다.</td></tr>`
				
			}else{
				r.stockList.forEach((o)=>{
					html += `<tr>
								<td> ${ o.stockmanagementno} 		</td>
								<td> ${ o.product_no} 				</td>
								<td> ${ o.stockmanagementtype==1?'입고':
										o.stockmanagementtype==2?'출고':'폐기'
									} 	</td>
								<td> ${ o.stockmanagementcompany} 	</td>
								<td> ${ o.stockmanagementdate} 		</td>
								<td> ${ o.stockmanagementamount} 	</td>
								<td> ${ o.product_price} 			</td>
								<td> ${ o.stockmanagementenddate==null?'':o.stockmanagementenddate} </td>
								<td> <button onclick="deleteStock(${o.stockmanagementno})" class="btn" type="button"> 삭제 </button>	</td>
							</tr>`;	
				});			
			}			

			document.querySelector('.stocklist').innerHTML = html ;	
			
			html = '' ;
			html += page <=1 ? ``:
				`<li class="page-item"><a class="page-link" onclick="getstockList(${page-1})"> &laquo;  </a> </li>`;
			for(let i=r.startbtn ; i<=r.endbtn ; i++){
				html += `<li class="page-item" ><a class="page-link" onclick="getstockList(${i})"> ${i} </a> </li>`
			}
			html += page >= r.totalpage ? ``:
				`<li class="page-item"><a class="page-link" onclick="getstockList(${page+1})"> &raquo;  </a> </li>`
				
			document.querySelector('.pagination').innerHTML = html;
			
		}// success e
	}); // ajax e	
}
// 주문상태별 리스트 출력
function getstockList_type(){
	pageObject.type = document.querySelector('.stockmanagementtype').value;
	getstockList(1);	
}
// 검색된 리스트 출력
function search(){
	// 입력받은 데이터를 전역변수 필드에 대입 
	pageObject.key     = document.querySelector('.key').value;
	pageObject.keyword = document.querySelector('.keyword').value;
	console.log(pageObject)
	getstockList(1);
}
// 입고 입력
function setStock(){
	
	let stockmanagementtype = document.querySelector('.stocktype_input').value;
	let stockmanagementcompany= document.querySelector('.company_input').value;
	let stockmanagementamount= document.querySelector('.stock_amount_input').value;
	let stockmanagementdate= document.querySelector('.stock_date_input').value;
	let stockmanagementenddate= document.querySelector('.drop_date_input').value;
	let product_price= document.querySelector('.stock_price_input').value;
	let product_no= document.querySelector('.product_no_input').value;
	
	if(stockmanagementtype==null){alert('제품번호를 입력해주세요.'); return;}
	if(stockmanagementcompany==null){alert('업체를 입력해주세요.'); return;}
	if(stockmanagementamount==null){alert('수량을 입력해주세요.'); return;}
	if(stockmanagementdate==null){alert('실행일자를 입력해주세요.'); return;}
	if(stockmanagementenddate==null){alert('폐기예정일자를 입력해주세요.'); return;}
	if(product_price==null){alert('가격을 입력해주세요.'); return;}
	if(product_no==null){alert('제품번호를 입력해주세요.'); return;}
		
	let info = {
		stockmanagementtype : stockmanagementtype,
		stockmanagementcompany: stockmanagementcompany,
		stockmanagementamount: stockmanagementamount,
		stockmanagementdate: stockmanagementdate,
		stockmanagementenddate: stockmanagementenddate,
		product_price: product_price,
		product_no: product_no
	}
	console.log(info)
	$.ajax({
		url 	: "/pangpang/stock",
		method	: "post",
		data 	: info,
		async	: false,
		success	: (r)=>{
			console.log(r)	
			if(r=='true'){
				alert('등록이 완료되었습니다.');
				getstockList(1);}
			else{alert('등록에 실패하였습니다. 재입력해주세요.')}
		}
	});
}

// 재고 내역 삭제
function deleteStock(stockmanagementno){
	console.log(stockmanagementno)
	$.ajax({
		url 	: "/pangpang/stock",
		method	: "delete",
		data 	: {"stockmanagementno":stockmanagementno},
		async	: false,
		success	: (r)=>{
			console.log(r)	
			if(r=='true'){
				alert('삭제가 완료되었습니다.');
				getstockList(1);}
			else{alert('삭제에 실패하였습니다. 재시도해주세요.')}
		}
	});
}















