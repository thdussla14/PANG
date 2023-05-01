<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자index</title>
	
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

	
	<div class="container">
	
		<!-- 사용자 헤더  -->
		<div class="wrap">
			<!-- 메인로고  -->
			<div class="mainlogo"><a href="/pangpang/index.jsp"><img width="100px" alt="" src="/pangpang/product/pimg/PANGPANG.png"></a> </div>
			<div class="header">
				<div class="dropdown">
					<a href="/pangpang/member/signup.jsp"><button type="button"> 회원가입 </button></a> <!-- 가입시 비밀번호 암호화 -->
				</div>
				<div class="dropdown">
					<a href="/pangpang/main.jsp"><button type="button"> 로그인 </button></a> <!-- 가입시 비밀번호 암호화 -->
				</div>
				<div class="dropdown">
					<button type="button" data-bs-toggle="dropdown"> 로그인회원 </button>
					<ul class="dropdown-menu">	
						<li><a class="dropdown-item" href="#">  결제정보등록	</a></li> <!-- 연동계좌 설정시 계좌정보 암호화  / 계좌사용 비밀번호 암호화 ?-->
						<li><a class="dropdown-item" href="/pangpang/product/cart.jsp">  장바구니		</a></li> <!--  결제  -->
						<li><a class="dropdown-item" href="/pangpang/member/mypage.jsp">  MYPAGE		</a></li> <!-- 회원정보 / 로그인한 회원 결제내역 -->
						<li><a class="dropdown-item" href="/pangpang/member/logout.jsp"">  로그아웃		</a></li> <!-- 로그아웃 -->
					</ul>
				</div>
			</div>
		</div>
		
		<!-- 관리자 헤더  -->
		<div class="wrap">
			<!-- 메인로고  -->
			<div class="mainlogo"> <a href="/pangpang/index.jsp"><img width="100px" alt="" src="/pangpang/product/pimg/PANGPANG.png"></a></div>
			<div class="header">
				<div class="dropdown">
					<a href="/pangpang/member/member_info.jsp"><button type="button"> 직원보기 </button></a>
				</div>
				<div class="dropdown">
					<a href="/pangpang/member/member_management.jsp"><button type="button"> 인사관리 </button></a>
				</div>			
				<div class="dropdown">
					<button type="button" data-bs-toggle="dropdown"> 제품관리 </button> 
					<ul class="dropdown-menu">	
						<li><a class="dropdown-item" href="/pangpang/product/item_list.jsp">  		품목관리	</a></li>   <!-- 카테고리/제품 CRUD -->
						<li><a class="dropdown-item" href="/pangpang/product/stockmanagement.jsp">  재고관리	</a></li>	<!-- 입고/ 주문결제완료시 출고처리 / 폐기일자도달 재고 팝업? 폐기 버튼 -->
						<li><a class="dropdown-item" href="/pangpang/product/dropmanagement.jsp">   폐기내역	</a></li>   <!-- 폐기내역 출력 -->
					</ul>
				</div>	
				<div class="dropdown">
					<button type="button" data-bs-toggle="dropdown"> 주문관리 </button> 
					<ul class="dropdown-menu">	
						<li><a class="dropdown-item" href="/pangpang/product/ordermanagement.jsp">  주문관리	</a></li>	<!-- 주문 내역 출력 / 상태 변경  -->
					</ul>
				</div>	
				<div class="dropdown">
					<button type="button" data-bs-toggle="dropdown"> 차량관리 </button>  
					<ul class="dropdown-menu">	
						<li><a class="dropdown-item" href="/pangpang/car/carmanagement/carmanagement.jsp">  차량관리	</a></li>	<!-- 신규 사내 차량 등록 -->
						<li><a class="dropdown-item" href="/pangpang/car/bookcar/bookcar.jsp">  			배차신청	</a></li>	<!-- 보고서 체계? 사용목적? 게시판 ? -->
						<li><a class="dropdown-item" href="/pangpang/car/carmanagement/permission.jsp">  			배차허가	</a></li>	<!-- 보고서 체계? 사용목적? 게시판 ? -->
						<li><a class="dropdown-item" href="/pangpang/car/bookcar/view.jsp">  			과거기록확인	</a></li>	<!-- 보고서 체계? 사용목적? 게시판 ? -->
						<li><a class="dropdown-item" href="/pangpang/map/map.jsp"> 							배송지설정	</a></li>	<!-- 지도API 활용한 배송지 경로설정 -->
						<li><a class="dropdown-item" href="/pangpang/car/drivecar/drivecar.jsp"> 			운영관리	</a></li>	<!-- 차량 운행 내역 --> 
						<li><a class="dropdown-item" href="#"> 	폐차관리	</a></li>	<!-- 차량 폐기 등록 / 폐기 내역 출력 --> 
					</ul>
				</div>
				<div class="dropdown">
					<button type="button" data-bs-toggle="dropdown"> 통계 </button>
					<ul class="dropdown-menu">	
						<li><a class="dropdown-item" href="#">  	</a></li>
						<li><a class="dropdown-item" href="#">  	</a></li>
						<li><a class="dropdown-item" href="#"> 		</a></li>
					</ul>
				</div>	
			</div>
		</div>
		
		
	</div> <!-- container e -->
	
	<!-- 모든 페이지 공통 js -->
	<!-- jquery -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<!-- 부트스트랩 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" ></script> 
	<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"></script> -->
	<script src="/pangpang/js/header.js" type="text/javascript"></script>		
	
</body>
</html>