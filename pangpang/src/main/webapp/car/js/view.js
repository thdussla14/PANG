//memberno로 배차기록 조회화기
console.log(memberInfo.member_id)

let login=memberInfo.member_id;

let com='';
record();
function record(){
	console.log("record열림");
	
   $.ajax({
      url:"/pangpang/view",
      data:{"login":login},
      method:"get",
      async:false,
      success:(r)=>{
         console.log('통신');
         console.log(r);
         	com=r;
         	let html=`<tr>
                           <th width="10%"> 번호 </th>
                           <th width="10%"> 운행시작일자 </th>
                           <th width="10%"> 운행종료일자 </th>
                           <th width="10%"> 운행허가상태</th>
                           <th class="reason" width="10%"> 비고 </th>						
						</tr>`
			r.forEach((o,i)=>{
               html +=`                  
                  <tr>
                     <td> ${i+1}</td>
                     <td> ${o.bookcar_str_date} </td>
                     <td> ${o.bookcar_end_date} </td>
                     <td><button onclick="driver(${o.bookcar_no})" class="btn">${o.bookcar_yn}</button></td>
					 <td> ${o.reason} </td>
                  </tr>`

			})
			document.querySelector('.myrecord').innerHTML=html;
      }//success e
      
   })//ajax e	
}//함수 e

//Y일경우 운행일지페이지 작성 n일경우 사유확인 
function driver(bookcar_no){
	/*console.log("driver함수 열림")*/
	com.forEach((o)=>{
		if(o.bookcar_no==bookcar_no){
			console.log(o.bookcar_yn)
			if(o.bookcar_yn=='Y'){
				alert("운행일지를 작성하세요")
				//여기에 운행일지 경로 넣기
				location.href="/pangpang/car/drivecar/drivecar.jsp?bookcar_no=" +bookcar_no
			}
		}
		
	})

}








