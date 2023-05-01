<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


	<link href="/pangpang/car/css/drivecar.css" rel="stylesheet">

</head>
<body>
	<%@ include file="/header.jsp" %>


	<%String bookcar_no = request.getParameter("bookcar_no"); %>
	
	<input class="bookcar_no" type="hidden" value="<%=bookcar_no%>">
	
	<div class="content_box">

		<div class="content_box_header" >
				운행일지작성하기
		</div>
		<div class="content_box_text">
			<table class="table drivetable table1">
				<tr>
					<th>작성일자</th>
					<th><input type="text" class="reportday input1"></th>
	
				</tr>
				<tr>
					<th>행선지</th>
					<th><input type="text" class="drivecar_distance input1"></th>
	
				</tr>
				<tr>
					<th>운행목적</th>
					<th><input type="text" class="purpose input1"></th>
				</tr>
				<tr>
					<th>보고서내용</th>
					<th><textarea name="text" class="report_content input2"></textarea></th>
				</tr>											
			</table>
			<button class="repottbtn btn" onclick="report()">등록</button>
	   </div>
	</div>


	
	<!--jquery -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>   
	<script src="/pangpang/car/js/drivecar.js" type="text/javascript"></script>
</body>
</html>