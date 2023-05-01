<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 장바구니 </title>

	<!-- 사용자정의   -->
	<link href="/pangpang/product/css/cart.css" rel="stylesheet">

</head>
<body>
	
	<%@ include file = "/customerheader.jsp" %>		
	
	<div class="container">

		<h3 class="title"> 장바구니 </h3>
		
		<div class="cart_wrap"> 
		
			<div class="top_menu">	 
				<span class="product_info">상품정보</span><span class="product_price">상품금액</span>
			</div>
			<div class="top_menu">
					<span> 팡팡배송 상품 무료배송 (19,000원 이상 무료 배송)</span>
			</div>
			
			<!-- 장바구니 제품 출력 구역 -->
			<div class="cartlist"> 

			</div><!-- cartlist e -->
			
			<div class="now_pricebox">

			</div>
			
			<div class="deletebox">
				<input type="checkbox" name="cart" onclick="SelectAll()"> <span class="selectAll"> 전체선택 </span>			
				<button class="cart_delete_btn" onclick="cartOutAll()"  type="button"> 전체삭제 </button> 
				<button class="cart_delete_btn" onclick="cartOut()"  	type="button"> 선택삭제 </button>
			</div>
			
			<div class="order_price">

			</div>			
			
			<div class="extra_info_box">					 
				<div class="extra_info">
					<span> <i class="fas fa-coins"></i> 캐시적립 해택 </span> <br>
					팡페이 머니 결제시 1% 적립 <br>
					[팡팡와우 + 팡페이 계좌이체] 결제 시 2% 적립	<br>
					[팡팡와우 + 팡페이 머니] 결제 시 4% 추가 적립<br>
				</div>
				<button onclick="order()" class="pangpangbtn" type="button"> 결제하기</button>				
			</div>

		</div> <!-- cart_wrap e -->
		
	</div> <!-- container e -->
	
	<%@ include file = "/footer.jsp" %>
	
	<script src="/pangpang/product/js/cart.js" type="text/javascript"></script>
	
</body>
</html>