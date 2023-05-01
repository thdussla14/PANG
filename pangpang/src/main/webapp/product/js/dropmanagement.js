// 등급제한
if( memberInfo.member_rank != 3 ){
	alert('접근 불가능한 등급입니다.');	location.href="/pangpang/index.jsp";
}

console.log('drop js')


let droplist = [];
 getDropList()
function getDropList(){
	console.log('droplist 실행')
	$.ajax({
		url 	: "/pangpang/drop",
		method	: "get",
		async	: false,
		success	: (r)=>{
			droplist = r;
			let html = `<tr> 
							<th> 제품번호 		</th> 
							<th> 제품이름		</th> 
							<th> 수량 		</th>
						</tr>`;
			if(droplist==null){
				html += `<tr><td colspan="3"> 금일 폐기 대상이 없습니다. </td></tr>`
			}else{
				droplist.forEach((o)=>{
					html += `<tr>
								<td> ${o.product_no}</td> 
								<td> ${o.product_name}</td> 
								<td> ${o.stockmanagementamount}</td>				
							</tr>`
				})
				document.querySelector('.droplist').innerHTML = html ;				
			}
		}
	});
	
}

function drop(){
	
	$.ajax({
		url 	: "/pangpang/drop",
		method	: "post",
		async	: false,
		data    : droplist,
		success	: (r)=>{
			console.log(r);
			 getDropList();
		}		
	})	
}