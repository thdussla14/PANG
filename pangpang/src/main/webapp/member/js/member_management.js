
// * pageObject : 현재 페이지 , 검색 , 전송타입 보관된 객체
let pageObject = {
	page : 1 , // page : 표시할 페이징번호
	list : "" , 
	order : 1,
	type : 1 ,
	listsize : 10,
	rank : 0,
}
// 1: 설정 X, 2: 오름차순(ascending order), 3: 내림차순(descending order) 
// 한번 누르면 오름차순 한번 더 누르면 내림차순 
let orderlist =[
	{list : 'member_no', order : 1},
	{list : 'member_name', order : 1},
	{list : 'member_id', order : 1},
	{list : 'member_email', order : 1},
	{list : 'member_phone', order : 1},
	{list : 'member_address', order : 1},
	{list : 'member_birth', order : 1},
	{list : 'member_rank', order : 1},
]

getmemberlist(1)
function getmemberlist(page){
	pageObject.page = page;
	
	$.ajax({
		url : "/pangpang/member/info" ,
		method : "get" , 
		data : pageObject ,
		success : (r)=>{ 
			console.log(r)
			html =`<tr>
				<th class="line1" onclick="getsearch(${0})">번호</th>
				<th class="line2" onclick="getsearch(${1})">이름</th>
				<th class="line3" onclick="getsearch(${2})">아이디</th>
				<th class="line4" onclick="getsearch(${3})">이메일</th>
				<th class="line5" onclick="getsearch(${4})">전화번호</th>
				<th class="line6" onclick="getsearch(${5})">주소</th>
				<th class="line7" onclick="getsearch(${6})">생일</th>
				<th class="line8" onclick="getsearch(${7})">등급</th>
				<th class="line9" >비고</th>
			</tr>`
			r.memberList.forEach((o)=>{
				html += `<tr>
							<td>${o.member_no}</td>
							<td>${o.member_name}</td>
							<td>${o.member_id}</td>
							<td>${o.member_email}</td>
							<td>${o.member_phone}</td>
							<td>${o.member_address}</td>
							<td>${o.member_birth}</td>
							<td>${o.member_rank}</td>
							<td>
								<button type="button" class="btn" style="width: 50px;" onclick="onpenModal(${o.member_no})">수정</button>
								<button type="button" class="btn" style="width: 50px;" onclick="drop(${o.member_no})">삭제</button>
							</td>
						</tr>`
			})
			
			document.querySelector('.table').innerHTML = html;
			// -------------------- 페이징 버튼 출력 --------------------- //
			html = ''; // 기존에 들어있던 내용 제거 
			// 이전 [ 만약에 현재 페이지가 1 이하 이면 이전페이지 없음 ]
			html += page <= 1 ?
					`<button onclick="getmemberlist(${ page })" type="button" class="pagebtn"> < </button>`
					:
					` <button onclick="getmemberlist(${ page-1 })" type="button" class="pagebtn"> < </button> `
			// 페이징 번호 버튼 들 
			for( let i = r.startbtn ; i<=r.endbtn ; i++ ){ // 시작버튼번호 부터 마지막버튼번호 까지 버튼 생성 
				html += `
					<button onclick="getmemberlist(${i})" type="button" class="pagebtn"> ${i} </button>
					`
			}
			// 다음 [ 만약에 현재 페이지가 총페이지수 이상이면 다음페이지 없음 ]
			html += page >= r.totalpage ?
					`<button onclick="getmemberlist(${ page })" type="button" class="pagebtn"> > </button>`
					:
					`<button onclick="getmemberlist(${ page+1 })" type="button" class="pagebtn" > > </button>`
					
		console.log(html)
			document.querySelector('.pagebox').innerHTML = html;
			
		}
	})
}

// 2. 정렬
function getsearch(index){
	console.log('onsearch()함수'+index);
	console.log(orderlist)
	orderlist.forEach((o,i)=>{
		if(i==index){// 누른 버튼번호와 같으면 
			console.log(o.order==2)
			if(o.order==2){ // 이미 전에 눌러서 2이면
				o.order=3
				pageObject.list=o.list
				pageObject.order=o.order
			}else if(o.order==3){
				o.order=2
				pageObject.list=o.list
				pageObject.order=o.order
			}else{ // 처음 누른거면
				o.order=2
				pageObject.list=o.list
				pageObject.order=o.order
			}
		}else{//누른 번호가 아니면 
			o.order = 1;
		}
	})
	getmemberlist(1);
}

function onpenModal(member_no ){
	$.ajax({
		url : "/pangpang/member/info" ,
		method : "get" , 
		data : { "type":2 , "member_no":member_no } ,
		success : (r)=>{ 
			console.log(r)
			
			document.querySelector('.member_no').value =r.member_no
			document.querySelector('.member_name').value =r.member_name
			document.querySelector('.member_id').value =r.member_id
			document.querySelector('.member_email').value =r.member_email
			document.querySelector('.member_phone').value =r.member_phone
			document.querySelector('.member_address').value =r.member_address
			document.querySelector('.member_birth').value =r.member_birth
			document.querySelector('.member_rank').value =r.member_rank
		}
	})
	document.querySelector('.modal_wrap').style.display ='flex';
}
function closeModal(){
	document.querySelector('.modal_wrap').style.display ='none';
}

function update(){
	let info = {
		type:1,
		member_no : document.querySelector('.member_no').value,
		member_name : document.querySelector('.member_name').value,
		member_birth : document.querySelector('.member_birth').value,
		member_email : document.querySelector('.member_email').value,
		member_phone : document.querySelector('.member_phone').value,
		member_id : document.querySelector('.member_id').value,
		member_address : document.querySelector('.member_address').value,
		member_rank: document.querySelector('.member_rank').value
	}
	console.log(info)
	
	$.ajax({
		url : "/pangpang/member/info" ,
		method : "put" , 
		data : info ,
		success : (r)=>{ 
			console.log(r)
			if(r=="true"){
				alert('수정 성공')
				location.reload();
			}else{
				alert('수정실패')
				location.reload();
			}
			
		}
	}) // ajax end	*/ 
}

function drop(member_no){
	$.ajax({
		url : "/pangpang/member/info" ,
		method : "delete" , 
		data : { "type":2 , "member_no":member_no } ,
		success : (r)=>{ 
			console.log(r)
			if(r=="true"){
				alert('회원탈퇴 성공')
				getmemberlist(1)
			}
		}
	})
}

function start(){
	$.ajax({
		url : "/pangpang/member/thread" ,
		method : "post" , 
		success : (r)=>{ 
			console.log(r)
		}
	})
}
