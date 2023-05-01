console.log('product_list js')

// * pageObject : 현재페이지, 검색, 전송타입 보관된 객체 
let pageObject = {
	page 	 : 1,
	key 	 : "key",
	keyword  : "keyword", 
	type 	 : 1,
	listsize : 12,
	cno 	 : 0
}


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


// 0. 선택한 카테고리 번호 가져오기
let cno = document.querySelector('.cno').value;
console.log(cno);

// 1. 사이드바 카테고리 리스트 출력
getCategoryList(cno) 
function getCategoryList(cno){
	
	$.ajax({
		url 	: "/pangpang/category",
		method	: "get",
		async	: false,
		success	: (r)=>{
			console.log(r)
			let html = ``;
			r.forEach((o)=>{
				if(o.category_no==cno){
					html += `<a href="/pangpang/product/product_list.jsp?cno=${o.category_no}" class="list-group-item list-group-item-action active" aria-current="true"> ${o.category_name} </a>`;	
				}else{
					html += `<a href="/pangpang/product/product_list.jsp?cno=${o.category_no}" class="list-group-item list-group-item-action"> ${o.category_name} </a>`;
				}				
			})
			document.querySelector('.categorylist').innerHTML = html ;
			
		} // success e
	}) // ajax e	
} // getCategory e

// 2. 카테고리별 제품 리스트 출력
getProductList(cno) 
function getProductList(cno){
	pageObject.type = 2;
	pageObject.cno = cno;	
	$.ajax({
		url 	: "/pangpang/product",
		method	: "get",
		data 	: pageObject,
		async	: false,
		success	: (r)=>{
			console.log(r)
			let html = `<h3 class="category_name"> ${r.itemList[0].category_name}</h3>`;						
			r.itemList.forEach((o)=>{
				
				if(o.product_count == 0){
					html += `<div class="soldout"> 
									<div class="pimg"> 
									<img class="product_img" alt="" src="/pangpang/product/pimg/${o.product_img} ">  </div>
									<div class="pname"> 	${o.product_name}  </div>
									<div class="price"> 	${o.product_price.toLocaleString()}원 <img class="mini_logo"alt="" src="/pangpang/product/pimg/PANG.png"><span  class="mini_mark">팡팡배송</span></div>
									<div class="soldoutmark"> 품절상품 </div>
							</div>`;
					
				}else{
					html += `<div class="item"> 
									<div class="pimg"> <a href="/pangpang/product/product_view.jsp?cno=${cno}&pno=${o.product_no}">
									<img class="product_img" alt="" src="/pangpang/product/pimg/${o.product_img} "> </a> </div>
									<div class="pname"> 	${o.product_name}  </div>
									<div class="price"> 	${o.product_price.toLocaleString()}원 <img class="mini_logo"alt="" src="/pangpang/product/pimg/PANG.png"><span  class="mini_mark">팡팡배송</span></div>
									<div class="date"> 		${month+"/"+date}(${day})  도착 보장	</div>
							</div>`;
				}
				

			
			})				
			document.querySelector('.product_wrap').innerHTML = html ;
			
		} // success e
	}) // ajax e	
} // getProductList e