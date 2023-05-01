<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="/pangpang/member/css/member_info.css" rel="stylesheet">
	<!-- 폰트어썸 -->
	<script src="https://kit.fontawesome.com/ca0196c650.js" crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file = "/header.jsp" %>	<!-- JSP 별도의 페이지를 현재 페이지에 삽입 -->
	<div class="content_box">
		<div class="content_box_header">
			직원정보
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
				</tr>
			</table>
			<!-- 페이징처리 버튼들 -->
			<div class="pagebox">
			
			</div>
		</div>
	</div>
	<script src="/pangpang/member/js/member_info.js" type="text/javascript"></script>
</body>
</html>