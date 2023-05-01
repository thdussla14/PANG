console.log("drivecar열림");
let bookcar_no=document.querySelector('.bookcar_no').value;
console.log(bookcar_no);
/*console.log("아이디확인")
console.log(memberInfo)*/


//보고서등록버튼
function report(){
	console.log("report열림")

	let info={
		 reportday :document.querySelector('.reportday').value,	//작성일자
		 drivecar_distance :document.querySelector('.drivecar_distance').value, //행선지
		 purpose :document.querySelector('.purpose').value, //운행목적
		 report_content :document.querySelector('.report_content').value, //보고서내용
		 bookcar_no //배차넘버
	}
	
	$.ajax({
		url:"/pangpang/drivecar",
		method:"post",
		data:info,
/*		//첨부파일
		contentType: false,
		processData: false,*/
		success:(r)=>{
			console.log('통신');
			console.log(r);
			if(r=='true'){
					alert('글쓰기성공');
					//location.href="/jspweb/board/list.jsp?cno="+document.querySelector('.cno').value;
					location.href="/pangpang/member/mypage2.jsp"
			}else{
				 alert('글쓰기실패');
			}
		}
	})

}


/*//전체 운행일지모음
function reportAll(){
	console.log("reportAll.js 진입");
	$.ajax({
		 url:"/pangpang/drivecar",
		 method:"get",
		 success:(r)=>{
			 console.log("통신");
			 console.log(r);
				let html=`<tr>
                           <th width="10%"> 번호 </th>
                           <th width="10%"> 차량이미지</th>
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
                     <td> ${o.carmanage_img} </td>
                     <td> ${o.carmanage_name} </td>
                     <td> ${o.carmanage_number} </td>
					 <td> ${o.drivecar_distance} </td>
					 <td> ${o.drivecar_str_date} </td>
					 <td> ${o.bookcar_end_date} </td>
					 <td> ${o.drivecar_parking} </td>
					 <td> ${o.report_content} </td>
                  </tr>`  
			})
			document.querySelector('.driveAlltable').innerHTML=html;			 
		 }
	})
}*/