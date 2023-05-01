<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

	<link href="/pangpang/car/css/bookcar.css" rel="stylesheet">
<body>


			<%@ include file="/header.jsp" %> 
			<script type="text/javascript">
		// 권한제어 
		if( memberInfo.member_rank != 3 ){
			alert('접급할 수 없는 권한입니다.'); 
			location.href="/pangpang/index.jsp";
		} 
	</script>
			
			<div class="content_box">

		<div class="content_box_header" >
					배차 승인/반려
			</div>
			<div class="content_box_text">
			   	<table class="table booktable table1" id="booktable"><!-- 배차확인용테이블 -->

				</table> 
		   </div>
		</div>
		





		
		




	<!--jquery -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>   
	<script src="/pangpang/car/js/permission.js" type="text/javascript"></script>




</body>
</html>