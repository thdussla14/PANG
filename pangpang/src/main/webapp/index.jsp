
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자index</title>
<link href="/pangpang/css/index.css" rel="stylesheet">

</head>
<body>

	<%@ include file = "/header.jsp" %>	<!-- JSP 별도의 페이지를 현재 페이지에 삽입 -->
	
	
	
	<div class="container">
		<div class="weather">
			<div class="current">
				<div class="icon"><i class="fas fa-wind"></i></div>
				<div class="temp_area"></div>
			</div>
		</div>
		
		<div class="chart_box">
		  <canvas id="myChart"></canvas>
		</div>
		
	</div> <!-- container e -->
	
	<!-- jquery -->
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="/pangpang/member/js/login.js" type="text/javascript"></script>
	<script src="/pangpang/js/index.js" type="text/javascript"></script>
</body>
</html> 