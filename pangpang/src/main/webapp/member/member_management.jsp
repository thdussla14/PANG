<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/pangpang/member/css/modal.css" rel="stylesheet">
	<link href="/pangpang/member/css/member_management.css" rel="stylesheet">
</head>
<body>
	<%@ include file = "/header.jsp" %>	<!-- JSP 별도의 페이지를 현재 페이지에 삽입 -->
	<script type="text/javascript">
		// 권한제어 
		if( memberInfo.member_rank != 3 ){
			alert('접급할 수 없는 권한입니다.'); 
			location.href="/pangpang/index.jsp";
		} 
	</script>
	
	<div class="content_box">
		<div class="content_box_header">
			회원관리
		</div>
		<div class="content_box_text">
			<table class="table table1">
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>아이디</th>
					<th>이메일</th>
					<th>전화번호</th>
					<th>주소</th>
					<th>생일</th>
					<th>등급</th>
					<th width="20%">비고</th>
				</tr>
				<tr>
					<td>번호</td>
					<td>이름</td>
					<td>아이디</td>
					<td>이메일</td>
					<td>전화번호</td>
					<td>주소</td>
					<td>생일</td>
					<td>등급</td>
					<td>
						<button type="button" class="btn" style="width: 100px;" onclick="onpenModal()">수정</button>
						<button type="button" class="btn" style="width: 100px;">삭제</button>
					</td>
				</tr>
			</table>
			<!-- 페이징처리 버튼들 -->
			<div class="pagebox">
			
			</div>
		</div>
		<button type="button" onclick="start()" class="btn">시작</button>
	</div>
	
		

	<!-- 모달 HTML -->
	<div class="modal_wrap">
		<div class="modal_box">
			<h3 class="modal_title">
				<!-- 제목이 들어가는 자리  -->
				정보 수정
			</h3>
			<div class="modal_content">
				<!-- 내용이 들어가는 자리  -->
				<table class="table">
					<tr>
						<th>번호</th>
						<td><input type="text" class="form-control member_no" disabled="disabled"></td>
					</tr>
					<tr>
						<th>이름</th>
						<td><input type="text" class="form-control member_name"></td>
					</tr>
					<tr>
						<th>아이디</th>
						<td><input type="text" class="form-control member_id"></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="text" class="form-control member_email"></td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td><input type="text" class="form-control member_phone"></td>
					</tr>
					<tr>
						<th>주소</th>
						<td><input type="text" class="form-control member_address"></td>
					</tr>
					<tr>
						<th>생일</th>
						<td><input type="text" class="form-control member_birth"></td>
					</tr>
					<tr>
						<th>등급</th>
						<td>
							<select class="form-control member_rank">
								<option>1</option>
								<option>2</option>
								<option>3</option>
							</select>
						</td>
					</tr>
				</table>
				
			</div>
			<div class="modal_btns">
				<button class="modal_check btn" type="button" onclick="update()">수정</button>
				<button onclick="closeModal()" class="modal_cencel btn" type="button">닫기</button>
			</div>
		</div>
	</div>
	
	<script src="/pangpang/member/js/member_management.js" type="text/javascript"></script>
</body>
</html>