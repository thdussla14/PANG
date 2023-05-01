<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 주문 관리 페이지 </title>

	<!-- 사용자정의   -->
	<link href="/pangpang/product/css/ordermanagement.css" rel="stylesheet">

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
	
		<h3 class="content_box_header"> 주문 내역 </h3>
		<!-- 상단 메뉴 구역 -->
		<div class="topbox"> 
			<div class="searchbox">
				<select class="key">
					<option value="ordermanagement_no"  > 주문번호 </option>
					<option value="ordermanagement_date"> 주문일자 </option>
				</select>
				<input  class="keyword" type="text">
				<button class="searchbtn btn" onclick="search()" type="button"> 검색 </button>
			</div>
		</div>
		<!-- 주문 내역 출력 구역 -->
		<div class="orderlistbox">
			<table class="orderlist table table-hover" >

			</table>
		</div>	
		<!-- 페이지버튼 출력 구역 -->
		<nav class="Page navigation example">
			 <ul class="pagination">
			    
			  </ul>		
		</nav>
		
	</div> <!-- container e -->

	<!--  주문 상세 모달 -->
	<div class="modal_wrap_detail">	
		<div class="modal_box">				
			<h3  class="modal_title"> 주문 상세 </h3>		
			<div class="modal_content">			
				<div class="order_detail">
					<table class="table orderdetail">
												
					</table>
				</div>
				<button onclick="closemodal()" class="modal_cancel btn" type="button"> 닫기 </button>
			</div>				
		</div>	<!-- modal_box e -->
	</div>	<!-- modal_wrap e -->
	
	
	


	<script src="/pangpang/product/js/ordermanagement.js" type="text/javascript"></script>

</body>
</html>
