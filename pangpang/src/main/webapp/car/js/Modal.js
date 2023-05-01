/*등록모달*/
//모달실행함수
//console.log(detailview)

function onpenModal( type , i){
	let html='';
	if(type==1){
		html=`
				<div class="modal_box">
				<h3 class="modal_title">
					등록하실내용을입력해주세요
				</h3>
				<div class="modal_content">
					   <form class="car_formdata">
					      차번호:       	<input name="carmanage_number" type="text">			  		<br>
					      차량이름:      	<input name="carmanage_name" type="text">			     	<br>
					      차이미지:        
					      	<div class="carmanage_img">
								<input type="file" class="cimg"	 name="carmanage_img">	<br>
							</div>
					      사용가능여부:    	<input name="carmanage_use_yn" type="text">			 		<br>
					      차량등록일자:    	<input name="carmanage_start" type="text">			     	<br>
					</form> 	
				</div>
				<div class="modal_btns">
					<button onclick="regi()" class="modal_check" type="button">확인</button>
					<button onclick="closeModal()" class="modal_cencel" type="button">닫기</button>
				</div>
			</div>
		`
		document.querySelector('.modal_wrap').innerHTML =html;
	}
	else if(type==2){
		html=`<div class="modal_box">
					<h3 class="modal_title">
						상세정보입니다
					</h3>
					<div class="modal_content">
						 <form class="updateForm">     
							 차량등록일자: ${ detailview[i].carmanage_start } <br>	
				      		 차량폐기일자: ${ detailview[i].carmanage_finish } <br>
						</form> 	
					</div>
					<div class="modal_btns">
						<button onclick="carupdate()" class="modal_check" type="button">확인</button>
						<button onclick="closeModal()" class="modal_cencel" type="button">닫기</button>
					</div>
				</div>

				`
		document.querySelector('.modal_wrap').innerHTML =html;
	}
	else if(type==3){
		html=`<div class="modal_box">
					<h3 class="modal_title">
						입력하세요
					</h3>
					<div class="modal_content">
						 <form class="updateForm">     
							 차량등록일자: ${ detailview[i].carmanage_start } <br>	
						</form> 	
					</div>
					<div class="modal_btns">
						<button onclick="carupdate()" class="modal_check" type="button">확인</button>
						<button onclick="closeModal()" class="modal_cencel" type="button">닫기</button>
					</div>
				</div>

				`
		document.querySelector('.modal_wrap').innerHTML =html;
		
	}else if(type==4){
		html=`
				<div class="modal_box">
				<h3 class="modal_title">
					배차하실 시간과 장소를 입력해주세요
				</h3>
				<div class="modal_content">
 						  목적지:       	<input class="bookcar_destination" type="text">			  	<br>
					      출발시간:      	<input class="bookcar_str_date" type="text">			     	<br>
					      복귀시간:       <input class="bookcar_end_date" type="text">			     	<br>
				</div>
				<div class="modal_btns">
					<button onclick="confirm(${ view[i].carmanage_no })" class="modal_check" type="button">확인</button>
					<button onclick="closeModal()" class="modal_cencel" type="button">닫기</button>
				</div>
			</div>
		`
		document.querySelector('.modal_wrap').innerHTML =html;
	}	
	
	 document.querySelector('.modal_wrap').style.display ='flex';
	

}
//모달단기함수
function closeModal(){
	document.querySelector('.modal_wrap').style.display ='none';
}

function onpenModal2(){
	
	document.querySelector('.modal_wrap2').style.display ='flex';
}
//모달단기함수
function closeModal2(){
	document.querySelector('.modal_wrap2').style.display ='none';
}


