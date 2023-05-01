<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title> header </title>

	<!-- 모든 페이지 공통 css -->
	<!-- 부트스트랩  -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"> -->
	
	<!-- 아이콘  -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css">
	<!-- 사용자정의   -->
	<link href="/pangpang/css/header.css" rel="stylesheet">

</head>
<body>
	
	<div class="newheader_wrap">
		<div class="header_top_box">
			<span class="comp_name"> PANGPANG </span>
			<span class="inc">.inc</span>
			<a href="/pangpang/index.jsp"> <span class="homeicon"> <i class="fas fa-home"> </i> </span> </a>
			<a href="/pangpang/member/logout.jsp"> <span class="logouticon"> <i class="fas fa-sign-out-alt"> </i> </span> </a>
			<a href="/pangpang/member/mypage.jsp"> <span class="usericon"> <i class="fas fa-user"> </i> </span> </a>
		</div>
		
		<div class="side_bar">
			<div class="main_menu_box">
				<button class="menu_title top_title" onclick="viewMenu(1)"> 인사관리 </button>
				<ul class="main_menu1">	
					<li><a href="/pangpang/member/member_info.jsp"> 직원정보보기 </a> </li>
					<li><a href="/pangpang/member/member_management.jsp"> 회원관리 </a> </li>
					<li><a href="#"> 연차관리 </a> </li>
				</ul>
				<button class="menu_title" onclick="viewMenu(2)"> 제품관리 </button>
				<ul class="main_menu2">	
					<li><a href="/pangpang/product/item_list.jsp"> 품목관리 </a> </li>
					<li><a href="/pangpang/product/stockmanagement.jsp"> 재고관리 </a> </li>
					<li><a href="/pangpang/product/dropmanagement.jsp"> 폐기관리 </a> </li>
				</ul>
				<button class="menu_title" onclick="viewMenu(3)"> 주문관리 </button>
				<ul class="main_menu3">	
					<li><a href="/pangpang/product/ordermanagement.jsp"> 주문관리 </a> </li>
					
				</ul>
				<button class="menu_title" onclick="viewMenu(4)"> 차량관리 </button>
				<ul class="main_menu4">	
					<li><a href="/pangpang/car/carmanagement/carmanagement.jsp"> 보유차량관리 </a> </li>
					<li><a href="/pangpang/car/bookcar/bookcar.jsp"> 배차신청 </a> </li>
					<li><a href="/pangpang/car/carmanagement/permission.jsp"> 배차승인/반려 </a> </li>
					<li><a href="/pangpang/car/bookcar/view.jsp"> 배차 이력확인 </a> </li>
					<li><a href="/pangpang/map/map.jsp"> 배송지 설정하기 </a> </li>
					<li><a href="/pangpang/car/drivecar/drivecar.jsp"> 운행관리 </a> </li>
				</ul>
				
				<!-- 채팅박스 -->
				<div class="chating_box">
				
					
				</div>
				
			</div>
			
			<button class="sidebar_onoff" onclick="sidebar_onoff()"><i class="fas fa-chevron-left"></i></button>
			
			<div class="user_profile">
			
				<!-- 접속명단 표시 --> 
				<div class="connectlistbox"> 
					<!-- 접속명단 1명  표시 -->
					<button type="button" class="onePerson" onclick="chatbox()">
						<div class="connectbox">
							<div><img src="/jspweb/member/pimg/default.webp" class="hpimg" > </div>
							<div class="name"> 유재석	</div>
						</div>
					</button>
					<!-- 접속명단 1명  표시 -->
					<div class="connectbox">
						<div><img src="/jspweb/member/pimg/default.webp" class="hpimg" > </div>
						<div class="name"> 유재석	</div>
					</div>
					<!-- 접속명단 1명  표시 -->
					<div class="connectbox">
						<div><img src="/jspweb/member/pimg/default.webp" class="hpimg" > </div>
						<div class="name"> 유재석	</div>
					</div>
				</div>
				<!-- 로그인 정보 표시 --> 
				<button type="button" class="loginbox_btn" onclick="login_info_scroll()">
					<div class="loginbox"> 
						<div><img src="/jspweb/member/pimg/default.webp" class="hpimg" > </div>
						<div> 강호동 </div>
					</div>
				</button>
			</div>
			
			
		</div>
	
	</div>
	




	<!-- 모든 페이지 공통 js -->
	<!-- jquery -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<!-- 부트스트랩 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" ></script> 
	<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"></script> -->
	<script src="/pangpang/js/header.js" type="text/javascript"></script>
</body>
</html>
