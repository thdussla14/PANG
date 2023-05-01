//숨겨진 no 너 왜 계속 null이니
/*let cno= document.querySelector('.carmanage_no').value ;*/


let detailview='';
console.log("------위임------")
console.log(detailview)

//전체출력
carList();
function carList(){

   console.log("carmanagement.js carList 진입");
   $.ajax({
      url:"/pangpang/carmanage",
      data:{type:1},
      method:"get",
      async:false,
      success:(r)=>{
         console.log('통신');
         console.log(r); 
         detailview=r;
            let html = `<tr>            
                           <th width="10%"> 번호 </th>
                           <th width="10%"> 차량번호 </th>
                           <th width="10%"> 차량이름 </th>
                           <th width="10%"> 차량이미지</th>
                           <th width="10%"> 차량사용여부 </th>
                           <th width="10%" colspan="3"> 비고 </th>
                     </tr>`
            r.forEach((o,i)=>{
               html +=`                  
                  <tr>
                     <td> ${i+1} </td>
                     <td style="display: none;"> ${o.carmanage_no} </td>
                     <td> ${o.carmanage_number} </td>
                     <td> ${o.carmanage_name} </td>
                     <td><img src="/pangpang/car/img/${ o.carmanage_img == null ? 'default.png' : o.carmanage_img }" width="100%"> </td>
                     <td> ${o.carmanage_use_yn} </td>
                     <td> <button onclick="car_update_modal_open(${o.carmanage_no})" type="button" class="btn">수정</button></td>
                     <td> <button onclick="cardelete(${o.carmanage_no})" type="button" class="btn">삭제</button></td>
                     <td> <button onclick="view(${o.carmanage_no})" type="button" class="btn">상세보기</button></td>
                             	
                  </tr>`               
            })         
            document.querySelector('.carmanage').innerHTML = html;   
      }
      
   })//ajax e
}//함수 e


console.log("------아래임------")
console.log(detailview)


//등록함수
function regi(){
   console.log('regi함수열림');
   
   let car_formdata = document.querySelectorAll('.car_formdata')[0];
   
   let car_formdataData = new FormData(car_formdata);
      console.log("-----------car_formdataData---------");
      console.log(car_formdataData);

   
   $.ajax({
      url:"/pangpang/carmanage",
      method:"post",
      data:car_formdataData,
      contentType : false ,         
     processData : false ,   
      success:(r)=>{
         console.log(r);
      if(r=='true'){
         console.log('통신성공')
         location.href="/pangpang/car/carmanagement/carmanagement.jsp"
      }else{
         console.log('통신실패')
      }
      }
   })//ajax e   

}


//삭제버튼구현
function cardelete(carmanage_no){
	console.log(carmanage_no)
   $.ajax({
      url:"/pangpang/carmanage",
      method:"delete",
     data:{"carmanage_no":carmanage_no },
      success:(r)=>{
         console.log('통신');
         console.log(r);
         if(r=='true'){
            alert('삭제성공')
            carList();
         }else{
            alert('삭제실패')
         }
         
      }
   })      
}

let temp_carmanage_no = 0;
//수정버튼모달
function car_update_modal_open (carmanage_no) {
	console.log("car_update_modal_open !!!");
	
	onpenModal2();
	console.log( carmanage_no );
	
	temp_carmanage_no = carmanage_no;
	
	console.log("임시 carmanage_no ::: " + temp_carmanage_no);
	
	$.ajax({
		url:"/pangpang/carmanage",
		method:"get",
		data:{"carmanage_no": carmanage_no , type : 1 },
		success:(r)=>{
			console.log("car_update_modal_open ::: ");
			console.log(r);
			console.log( r[0].carmanage_img + "이미지입니다.")
			
			if( r[0].carmanage_use_yn == 'N' ){
				document.querySelector('.carmanage_finish').style.display = "inline-block";
			}else{
				document.querySelector('.carmanage_finish').style.display = "none";
			}
			
			document.getElementsByName('carmanage_number')[0].value = r[0].carmanage_number;
			document.getElementsByName('carmanage_name')[0].value = r[0].carmanage_name
			document.getElementsByName('carmanage_use_yn')[0].value = r[0].carmanage_use_yn;
			document.getElementsByName('carmanage_finish')[0].value = r[0].carmanage_finish;
			document.querySelector('.carmanage_img').innerHTML = r[0].carmanage_img;
		}
	})
}

function selectChange(){
	
	let value = document.querySelector('.carmanage_use_yn').value;
		console.log( value );
	
	if( value == 'N' ){
		document.querySelector('.carmanage_finish').style.display = "inline-block";
	}else{
		document.querySelector('.carmanage_finish').style.display = "none";
	}
	
}



//수정버튼구현
function carupdate(){
	console.log('수정버튼클릭');
	
	/*
	let updateForm = document.querySelector('.updateForm');
	
	console.log("carupdate updateForm ::: ")
	console.log(updateForm)
	
	let updateFormData = new FormData(updateForm);
	
	console.log("carupdate updateFormData ::: ")
	console.log(updateFormData)
	
	updateFormData.set('carmanage_no',temp_carmanage_no)
	*/
	
	let updateForm = document.querySelectorAll('.updateForm')[0];
	let updateFormData = new FormData(updateForm);
	
	let carmanage_img = document.querySelector('.carmanage_img').innerHTML
		console.log( carmanage_img );
	
	updateFormData.set('carmanage_no', temp_carmanage_no);
	updateFormData.set('carmanage_img', carmanage_img )
	
	// updateFormData.set('carmanage_img', document.getElementsByName('update_img')[0].value);
	// updateFormData.set('carmanage_use_yn', document.getElementsByName('update_use_yn')[0].value);
	// updateFormData.set('carmanage_finish', document.getElementsByName('update_finish')[0].value);
	
	console.log("console.log(updateFormData) 확인")
	console.log( updateFormData )
	
/*	console.log(" updateFormData ::: ");
	console.log(updateFormData);
	
	console.log("updateFormData ::: ");
	console.log(updateFormData.get('carmanage_no'));
	
	console.log(" updateFormData ::: ");
	console.log(updateFormData.get('carmanage_img'));
	
	console.log(" updateFormData ::: ");
	console.log(updateFormData.get('carmanage_use_yn'));
	
	console.log(" updateFormData ::: ");
	console.log(updateFormData.get('carmanage_finish'));*/
	
   $.ajax({
      url:"/pangpang/carmanage",
      method:"put",
      data: updateFormData,
      contentType: false,
	  processData: false,
      success:(r)=>{
         console.log('통신');
         console.log(r);
         if(r=='true'){
            alert('수정성공');
            location.href="/pangpang/car/carmanagement/carmanagement.jsp"
         }else{
            alert('수정실패')
         }
         
      }
   })      
}

let checkno='';

//상세보기버튼구현
function view(carmanage_no){
	console.log('상세보기버튼')

	detailview.forEach((o,i)=>{
		if(o.carmanage_no == carmanage_no){
			console.log(o.carmanage_use_yn)
			if(o.carmanage_use_yn=='N'){
				onpenModal(2 ,i);
				
			}else if(o.carmanage_use_yn=='Y'){
				onpenModal(3 ,i);
			}
			
/*		console.log("-----------------");
		console.log(o.carmanage_start);
		console.log(o.carmanage_finish);
		document.querySelector('.detailview').innerHTML = o.carmanage_start;*/
		//document.querySelector('.detailview').innerHTML = o.carmanage_start +"<br>"+ o.carmanage_finish;
			
		
		}
	})
}



