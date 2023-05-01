// * pageObject : 현재 페이지 , 검색 , 전송타입 보관된 객체
let pageObject = {
	page : 1 , // page : 표시할 페이징번호
	list : "" , 
	order : 1,
	type : 1 ,
	listsize : 10,
	rank : 1,
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
	console.log(pageObject)
	$.ajax({
		url : "/pangpang/member/info" ,
		method : "get" , 
		data : pageObject ,
		success : (r)=>{ 
			console.log(r)
			html =`	<tr>
						<th class="line1" onclick="getsearch(${0})">번호</th>
						<th class="line2" onclick="getsearch(${1})">이름</th>
						<th class="line3" onclick="getsearch(${2})">아이디</th>
						<th class="line4" onclick="getsearch(${3})">이메일</th>
						<th class="line5" onclick="getsearch(${4})">전화번호</th>
						<th class="line6" onclick="getsearch(${5})">주소</th>
						<th class="line7" onclick="getsearch(${6})">생일</th>
						<th class="line8" onclick="getsearch(${7})">등급</th>
					</tr>`
			r.memberList.forEach((o)=>{
				if(o.member_rank>1){
					html += `	<tr>
									<td>${o.member_no}</td>
									<td>${o.member_name}</td>
									<td>${o.member_id}</td>
									<td>${o.member_email}</td>
									<td>${o.member_phone}</td>
									<td>${o.member_address}</td>
									<td>${o.member_birth}</td>
									<td>${o.member_rank}</td>
								</tr>`
				}
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












