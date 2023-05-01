<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 제품 상세 </title>

	<!-- 사용자정의   -->
	<link href="/pangpang/product/css/product_view.css" rel="stylesheet">

</head>
<body>


	<%@ include file = "/customerheader.jsp" %>		<!-- JSP 별도의 페이지를 현재 페이지에 삽입 -->

	<%
		// 1. jsp 이용한 http url 변수 호출
		String cno = request.getParameter("cno");
		String pno = request.getParameter("pno");
	%>
	 <!-- java 코드 HTML 출력  -->
	<!-- pno 숨겨서 js 전달 -->
	<input type="hidden" class="cno" value="<%=cno%>">
	<input type="hidden" class="pno" value="<%=pno%>">
	
	<div class="container">
	
		<div class="bind">
		
			<!-- 사이드 카테고리바 -->
			<div class="categorylist list-group">
	
			</div> <!-- categorylist e -->
			
			<!-- 제품상세 출력 구역 -->
			<div class="product_view_wrap"> 
		
			</div> <!-- product_view_wrap e -->
			
		</div> <!-- bind e -->
		
	</div> <!-- container e -->
	
	<%@ include file = "/footer.jsp" %>

	<script src="/pangpang/product/js/product_view.js" type="text/javascript"></script>
	
</body>
</html>