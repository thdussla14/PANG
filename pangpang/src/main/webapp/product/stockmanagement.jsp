<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고 관리 페이지</title>

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
	
		<h3 class="content_box_header"> 입력 </h3>
		<!-- 재고 입력 구역 -->
		<div class="inputbox">
			<table class="table table-hover inputtable">
				<tr>
					<th> 제품번호</th> <th> 구분 </th><th> 업체      </th><th> 시행일자 </th>
					<th> 수량  </th> <th> 단가 </th><th> 예정 폐기일 </th><th> 비고    </th>
				</tr>
				<tr>
					<td> <input class="product_no_input" type="text">  </td> 
					<td>  
						<select class="stocktype_input">
							<option value="0"> 구분 </option>
							<option value="1"> 입고 </option>
							<option value="2"> 출고 </option>
							<option value="3"> 폐기 </option>
						</select>
					</td>
					<td> <input class="company_input" 		type="text"> </td>
					<td> <input class="stock_date_input" 	type="datetime-local"> </td>
					<td> <input class="stock_amount_input" 	type="text"> </td>
					<td> <input class="stock_price_input" 	type="text"> </td>
					<td> <input class="drop_date_input" 	type="datetime-local"> </td>
					<td> <button class="setbtn btn" onclick="setStock()" type="button"> 등록 </button> </td>
				</tr>				
			</table>
		</div>	
	
		<h3 class="content_box_header"> 재고 내역 </h3>
		<!-- 상단 메뉴 구역 -->
		<div class="topbox"> 
			<div class="searchbox">
				<select class="key">
					<option value="product_no"> 제품번호 	</option>
					<option value="stockmanagementdate"> 시행일자 	</option>
					<option value="stockmanagementcompany"> 대상업체 	</option>
				</select>
				<input  class="keyword" type="text">
				<button class="searchbtn btn" onclick="search()" type="button"> 검색 </button>
			</div>
		</div>
		
		<!-- 재고 내역 출력 구역 -->
		<div class="stocklistbox">
			<table class="stocklist table table-hover" >

			</table>
		</div>	
		<!-- 페이지버튼 출력 구역 -->
		<nav class="Page navigation example">
			 <ul class="pagination">
			    
			  </ul>		
		</nav>	
	</div> <!-- container e -->

	<script src="/pangpang/product/js/stockmanagement.js" type="text/javascript"></script>

</body>
</html>