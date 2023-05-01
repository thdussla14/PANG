//배차페이지 차량선택출력
view="";
carchoice();
function carchoice(){

   console.log("carmanagement.js carList 진입");
   $.ajax({
      url:"/pangpang/carmanage",
      data:{type:3},
      method:"get",
      async:false,
      success:(r)=>{
         console.log('통신');
         console.log(r);
         view=r
            let html = `<tr>            
                           <th width="10%"> 번호 </th>
                           <th width="10%"> 차량번호 </th>
                           <th width="10%"> 차량이름 </th>
                           <th width="10%"> 차량이미지</th>
                           <th width="10%"> 비고 </th>                   
                      </tr>`
                     
            r.forEach((o,i)=>{
               html +=`                  
                  <tr>
                     <td> ${i+1} </td>
                     <td style="display: none;"> ${o.carmanage_no} </td>
                     <td> ${o.carmanage_number} </td>
                     <td> ${o.carmanage_name} </td>
                     <td><img src="/pangpang/car/img/${o.carmanage_img == null ? 'default.png' : o.carmanage_img}" width="100%"> </td>
                     <td> <button onclick="choice(${o.carmanage_no})" type="button"  class="btn">선택하기</button></td>     
                  </tr>`               
            })         
            
            document.querySelector('.carchoice').innerHTML = html;   
      }
      
   })//ajax e
}//함수 e




//선택하기함수
function choice(carmanage_no){
	console.log(carmanage_no);
		view.forEach((o,i)=>{
			view[i].carmanage_no
		if(o.carmanage_no==carmanage_no){
				onpenModal(4,i);
		}
	})
	
	
};


console.log(memberInfo);
console.log(memberInfo.member_no);

let login=memberInfo;

//배차예약정보 전송버튼

function confirm(carmanage_no){
	console.log("-------차량번호확인-----------")
	console.log("bookcar.js confirm carmanage_no ::: " + carmanage_no);
	
	let book={
		bookcar_destination : document.querySelector('.bookcar_destination').value,
		bookcar_str_date : document.querySelector('.bookcar_str_date').value,
		bookcar_end_date : document.querySelector('.bookcar_end_date').value,	
		carmanage_no : carmanage_no
	}

	console.log(book);
	
	$.ajax({
		url:"/pangpang/bookcar",
		method:"get",
		data:book,
		success:(r)=>{
			console.log("통신성공");
			console.log(r);
			if(r=="true"){
				alert("배차신청완료");
			}else{
				alert("배차신청실패");
			}
		}
	})
	 	 	
}
