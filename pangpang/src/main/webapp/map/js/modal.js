console.log('modal.js 열림')
let selectCenterInfo = [];
// 1. 모달 열기 함수
function openModal( dist_center ){
	// 1. 모달 배경 구역 css 변경해서 모달 보이기 
	document.querySelector('.modal_wrap').style.display='flex'
	
	selectCenterInfo = dist_center.map( (o) =>{
		
		if( o == 1 ){
			return {value : 1 , name : '서울 팡팡물류센터' }
		}else if( o == 2 ){
			return {value : 2 , name : '안산 팡팡물류센터' }
		}else if( o == 3 ){
			return {value : 3 , name : '부천 팡팡물류센터' }
		}else if( o == 4 ){
			return {value : 4 , name : '시흥 팡팡물류센터' }
		}
		
	})
	
	
	let html = `<div>
					<p> 출발지 </p>
					<select class="start_point">
						<option> ${ selectCenterInfo[0].name } </option>
						<option> ${ selectCenterInfo[1].name } </option>
					</select>
				</div>
				<div>
					<p> 도착지 </p>
					<select class="end_point">
						<option> ${ selectCenterInfo[0].name } </option>
						<option> ${ selectCenterInfo[1].name } </option>
					</select>
				</div>`
	
	document.querySelector('.modal_content').innerHTML = html ;
	
}

// 2. 모달 닫기 함수
function closeModal(){
	document.querySelector('.modal_wrap').style.display='none'
}