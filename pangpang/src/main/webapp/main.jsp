<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title> PANGPANG 쇼핑 </title>
	
	<!-- 폰트어썸 -->
	<script src="https://kit.fontawesome.com/ca0196c650.js"></script>
	<link href="/pangpang/css/main.css" rel="stylesheet">
	 
</head>
<body>
	
	<div class="main_wrap">
		<div class="main_login_box">
			<div class="login_left_box">
				<div class="main_logo_box">
					<img alt="" src="/pangpang/map/img/logo.png">
				</div>
			</div>
			<div class="login_right_box">
				<div class="login_info_box">
					<div class="id_box">
						<div class="login_input_box">
							<span> <i class="fas fa-id-card"></i> </span>
							<input type="text" class="input_box member_id">
						</div>
					</div>
					<div class="pwd_box"> 
						<div class="login_input_box"> 
							<span><i class="fas fa-key"></i></span>
							<input type="password" class="input_box member_pwd">
						</div>
					</div>
					<div class="sign_menu_box">
						<a href="/pangpang/member/signup.jsp"> Sign up </a> <span>  |  </span>
						<a href="#" onclick="onpenFindidModal()"> Forgot Id? </a><span>  |  </span>
						<a href="#" onclick="onpenFindpwdModal()"> Forgot Password? </a>
					</div>
					<div class="login_btn_box">
						<button type="button" class="login_btn" onclick="login()"> 로그인 </button>
					</div>
					
					<div class="nologin">
						<a href="/pangpang/product/product_index.jsp"> 비로그인으로 둘러보기 > </a>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	
	
	
	<div class="findid_modal_wrap modal_wrap">
		<div class="modal_box">
			<div class="modal_title_box">
				<span>아이디 찾기</span> 
				<button type="button" onclick="closeFindidModal()" class="close_findid">X</button>
			</div>
			<div class="findid_box resultbox ">
			</div>	
		</div>
	</div>
	
	<div class="findpwd_modal_wrap modal_wrap">
		<div class="modal_box box2">
			<div class="modal_title_box">
				<span>비밀번호 찾기</span> 
				<button type="button" onclick="closeFindpwdModal()" class="close_findid">X</button>
			</div>
			<div class="findpwd_box resultbox ">
			</div>
		</div>
	</div>
	
	
	<!-- jquery -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="/pangpang/member/js/login.js" type="text/javascript"></script>
</body>
</html>