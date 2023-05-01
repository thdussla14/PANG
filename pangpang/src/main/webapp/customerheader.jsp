<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Customer Headr </title>
</head>

	<!-- 모든 페이지 공통 css -->
	<!-- 부트스트랩  -->
	<meta name="viewport" content="width=device-width, initial-scale=1">


	<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet"> -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">

	<!-- 아이콘  -->
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css">
	<!-- 사용자정의   -->
	<link href="/pangpang/css/customerheader.css" rel="stylesheet">

<body>
	<div class="header_toptop">
		<div class="cust_loginbox">
			<a href="/pangpang/main.jsp"><span> 로그인 </span></a>
			<a href="/pangpang/member/signup.jsp"><span class="signup_btn_box"> 회원가입 </span></a>
			<a href="#"><span class="cust_center"> 고객센터 </span></a>
		</div>
	</div>
	<div class="color_point"></div>
	
	<div class="cust_head_wrap">
		<div class="cust_header_top">
			<div>
				<a class="logo_link" href="/pangpang/product/product_index.jsp"><div class="logo_text"> PANG
						<div class="ppp">P</div>
						<div class="aaa">A</div>
						<div class="nnn">N</div>
						<div class="ggg">G</div> 
					</div>
				</a>
			</div>
			<div class="cust_header_btnbox">
				<a href="/pangpang/member/mypage.jsp" class="mypangpang">
					<div class="user_icon" onclick="mypage_drop_down()">
						<div class="icon"><i class="far fa-user"></i></div>
						<div class="text">마이팡팡</div>
					</div>
				</a>
				<a href="/pangpang/product/cart.jsp" class="mypangpang"> 
					<div class="cart_icon">
						<div class="icon"><i class="fas fa-shopping-cart"></i></div>
						<div class="text">장바구니</div>
					</div>
				</a>
			</div>
		</div>
	</div>
	
	
	
	
	
	<div id="carouselExampleControls" class="img_slide_box carousel carousel-dark slide" data-bs-ride="carousel">
	  <div class="carousel-inner">
	    <div class="carousel-item active">
	      <img src="/pangpang/img/slide1.jpg" class="d-block w-100" alt="...">
	    </div>
	    <div class="carousel-item">
	      <img src="/pangpang/img/slide2.jpg" class="d-block w-100" alt="...">
	    </div>
	    <div class="carousel-item">
	      <img src="/pangpang/img/slide3.jpg" class="d-block w-100" alt="...">
	    </div>
	    <div class="carousel-item">
	      <img src="/pangpang/img/slide4.jpg" class="d-block w-100" alt="...">
	    </div>
	    <div class="carousel-item">
	      <img src="/pangpang/img/slide5.jpg" class="d-block w-100" alt="...">
	    </div>
	    <div class="carousel-item">
	      <img src="/pangpang/img/slide6.jpg" class="d-block w-100" alt="...">
	    </div>
	  </div>
	  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
	    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Previous</span>
	  </button>
	  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
	    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Next</span>
	  </button>
	</div>
	
	<div class="cart_item">
		<div class="d_item">
			<a href="#">결제정보등록</a>
		</div>
		<div class="d_item">
			<a href="/pangpang/product/cart.jsp">장바구니</a>
		</div>
	</div>
	

	<!-- 모든 페이지 공통 js -->
	<!-- jquery -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<!-- 부트스트랩 -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" ></script> 
	<!-- <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"></script> -->
	<script src="/pangpang/js/customerheader.js" type="text/javascript"></script>
</body>
</html>