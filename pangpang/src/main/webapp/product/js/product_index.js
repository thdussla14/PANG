console.log('product_index js')


// 1. 카테고리 리스트 출력
getCategory() 
function getCategory(){
	
	$.ajax({
		url 	: "/pangpang/category",
		method	: "get",
		async	: false,
		success	: (r)=>{
			console.log(r)
			let html = '';
			r.forEach((o)=>{
				html += `<div class="category"> 
							<div class="cimg"> <a href="/pangpang/product/product_list.jsp?cno=${o.category_no}">
							<img src="/pangpang/product/pimg/${o.category_img}" alt=""></a> </div>
							<div class="cname">${o.category_name}</div>			
						</div>`;
			})
			document.querySelector('.category_wrap').innerHTML = html ;
		} // success e
	}) // ajax e	
} // getCategory e