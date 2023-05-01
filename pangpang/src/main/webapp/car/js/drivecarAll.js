reportAll();
//전체 운행일지모음
function reportAll(){
	console.log("reportAll.js 진입");
	$.ajax({
		 url:"/pangpang/drivecar",
		 method:"get",
		 data:{"type":2},
		 success:(r)=>{
			 console.log("통신");
			 console.log(r);
				let html=`<tr>
                           <th width="10%"> 번호 </th>
                           <th width="10%"> 사원명 </th>
                           <th width="10%"> 차량이름 </th>
                           <th width="10%"> 일련번호</th>
                           <th width="10%"> 행선지</th>	
                           <th width="10%"> 운행시작일자</th>
                           <th width="10%"> 운행종료일자</th>
                           <th width="10%"> 운행사유</th>
                           <th width="10%"> 운행일지내용</th>			
						</tr>`
			r.forEach((o,i)=>{
               html +=`                  
                  <tr>
                     <td> ${i+1}</td>
                     <td> ${o.member_name} </td>
                     <td> ${o.carmanage_name} </td>
                     <td> ${o.carmanage_number} </td>
					 <td> ${o.drivecar_distance} </td>
					 <td> ${o.drivecar_str_date} </td>
					 <td> ${o.drivecar_end_date} </td>
					 <td> ${o.drivecar_parking} </td>
					 <td> ${o.report_content} </td>
                  </tr>`  
			})
			document.querySelector('.driveAlltable').innerHTML=html;			 
		 }
	})
}