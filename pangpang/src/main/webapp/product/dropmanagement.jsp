<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 폐기 대상 관리 </title>

	<!-- 사용자정의   -->
	<link href="/pangpang/product/css/stockmanagement.css" rel="stylesheet">
	
</head>
<body>

	<%@ include file = "/header.jsp" %>	
	<script type="text/javascript">
		// 권한제어 
		if( memberInfo.member_rank != 3 ){
			alert('접급할 수 없는 권한입니다.'); 
			location.href="/pangpang/index.jsp";
		} 
	</script>
	<div class="content_box">
		<h3  class="content_box_header"> 금일 폐기 대상 목록 </h3>
		<div class="dropbtnbox">
			<button class="dropbtn btn" type="button" onclick="drop()"> 폐기 </button>
		</div>	
		
		<!-- 폐기 대상 출력 구역 -->
		<div class="droplistbox">
			<table class="droplist table table-hover" >

			</table>
		</div>	
	</div> <!-- container e -->

	<script src="/pangpang/product/js/dropmanagement.js" type="text/javascript"></script>


</body>
</html>