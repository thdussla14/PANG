<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 품목 리스트 </title>
	<!-- 사용자정의   -->
	<link href="/pangpang/product/css/item_list.css" rel="stylesheet">

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
	
		<h3 class="content_box_header"> 품목 관리 </h3>
		<!-- 상단 메뉴 구역 -->
		<div class="topbox"> 
			<div class="searchbox">
				<select class="key">
					<option value="p.product_no">   제품번호 </option>
					<option value="p.product_name"> 제품이름 </option>
				</select>
				<input  class="keyword" type="text">
				<button class="searchbtn btn" onclick="search()" type="button"> 검색 </button>
			</div>
			<div>
				<button class="registerbtn btn" onclick="openmodal_R()" type="button"> 제품 등록 </button>
			</div>
		</div>
		<!-- 품목 출력 구역 -->
		<div class="itemlistbox">
			<table class="itemlist table table-hover" >
	
			</table>
		</div>	
		<!-- 페이지버튼 출력 구역 -->
		<nav class="Page navigation example">
			 <ul class="pagination">
			    
			  </ul>		
		</nav>
		
	</div> <!-- container e -->
	
	<!-- 제품 등록 모달 구역 -->
	<div class="modal_wrap modalregister">	
		<div class="modal_box">
			
			<h3  class="modal_title"> 제품정보등록 </h3>		
			<div class="modal_content">
				<form class="registerForm">
					<div class="title"> 카테고리 
						<select class="categorylist1" name="category_no" >
						
						</select>
					</div>		
					<div class="title"> 제품명 
						<input type="text" 	   name="product_name"   	class="product_name">
					</div>			
					<div class="title"> 제품규격 
						<input type="text" 	   name="product_option" 	class="product_option">
					</div>		
					<div class="title"> 포장단위 
						<input type="text" 	   name="product_unit"   	class="product_unit">		
					</div>
					<div class="title"> 제품상세 
						<textarea name="product_content"   				class="product_content" rows="" cols=""></textarea>		
					</div>
					<div class="title"> 제품이미지 
						<input type="file" 	   name="product_img"  		class="product_img">		
					</div>
					<div class="title"> 판매가격설정
						<input type="text" 	   name="product_price"   	class="product_price">		
					</div>
					<div class="title"> 최대할인율설정 
						<input type="text" 	   name="product_discount"  class="product_discount">		
					</div>
					<div class="modalbtnbox">
						 <button onclick="item_register()" class="modal_cancel btn" type="button"> 등록 </button>	
		  				 <button onclick="closemodal_R()"   class="modal_cancel btn" type="button"> 닫기 </button>
					</div>
				</form>
				
			</div>
				
		</div>	<!-- modal_box e -->
	</div>	<!-- modal_wrap e -->
	
	<!-- 제품 수정 모달 구역 -->
	<div class="modal_wrap modalupdate">	
		<div class="modal_box">
			
			<h3  class="modal_title"> 제품정보수정 </h3>		
			<div class="modal_content">
				<form class="updateForm">
					<div class="title"> 카테고리 
						<select class="categorylist2" name="category_no" >
						
						</select>
					</div>		
					<div class="title"> 제품명 
						<input type="text" 	   name="product_name_U"   	class="product_name_U">
					</div>			
					<div class="title"> 제품규격 
						<input type="text" 	   name="product_option_U" 	class="product_option_U">
					</div>		
					<div class="title"> 포장단위 
						<input type="text" 	   name="product_unit_U"   	class="product_unit_U">		
					</div>
					<div class="title"> 제품상세 
						<textarea name="product_content_U"   			class="product_content_U" rows="" cols=""></textarea>		
					</div>
					<div class="title"> 제품이미지 
						<input type="file" 	   name="product_img_U"  	class="product_img_U">		
					</div>
					<div class="title"> 판매가격설정
						<input type="text" 	   name="product_price_U"   class="product_price_U">		
					</div>
					<div class="title"> 최대할인율설정 
						<input type="text" 	   name="product_discount_U"  class="product_discount_U">		
					</div>
					<div class="modalbtnbox_U">

					</div>
				</form>				
			</div>
				
		</div>	<!-- modal_box e -->
	</div>	<!-- modal_wrap e -->
	
	<!-- 제품 삭제 모달 구역 -->
	<div class="modal_wrap modaldelete">	
		<div class="modal_box_delete">
	
		</div>	<!-- modal_box e -->
	</div>	<!-- modal_wrap e -->
	
	
	
	<script src="/pangpang/product/js/item_list.js" type="text/javascript"></script>
	
</body>
</html>