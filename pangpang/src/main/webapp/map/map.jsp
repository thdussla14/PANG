<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>PANGPANG : Map </title>
	<link href="/pangpang/map/css/modal.css" rel="stylesheet">
	<link href="/pangpang/map/css/map.css" rel="stylesheet">
	
	<!-- 폰트어썸 -->
	<script src="https://kit.fontawesome.com/ca0196c650.js" crossorigin="anonymous"></script>
</head>
<body>
	
	<%@ include file="/header.jsp" %>
	
	<div class="content_box ">
		<div class="main_box">
			<div class="member_info_box">
				<div class="member_info_main">
					<div class="info_top content_box_header">
						
					</div>
				</div>
				<div class="personal_info">
					
				</div>
			</div>
			
			<div class="dispatch_main_box">
				
			</div>
			<div class="delivery_info_box">
				<div>
					<div class="content_box_header" >
							배송정보
					</div>
					<div class="info_table_box">
						<table class="table d_info_table table1">
							
						</table>
					</div>
				</div>
				<div class="selectbtn">
					<button type="button" onclick="addrSelect()" class="btn"> 선택완료 </button>
				</div>
			</div>
		
			
			<div>
				<div class="content_box_header" >
					운행정보
				</div>
				<div class="set_starting_box">
					<div class="select_item_box">
						<h3> 차고지 선택 </h3>
						<p> 한개의 센터를 선택한 경우 출발지/목적지가 동일하게 설정됩니다.</p>
						<p> 2개 선택시 출발지/목적지를 설정할 수 있습니다. </p>
						<br>
						<p class="notice_info"> [주의] 세개 이상의 차고지를 선택할 수 없습니다. </p>
						<div class="distribution_center">
							<input type="checkbox" name="distribution" value="1"> 서울
							<input type="checkbox" name="distribution" value="2"> 안산
							<input type="checkbox" name="distribution" value="3"> 부천
							<input type="checkbox" name="distribution" value="4"> 시흥
						</div>
						
						<div class="start_end_point">
							<table class="table s_e_table">
								
							</table>
						</div>
						
						<h3> 경유지 정보 </h3>
						<div class="select_table_box">
							<table class="table select_table">
								
							</table>
						</div>
					</div>
					
					<div class="map_box">
						<p id="result"></p>
						<div id="map_div"></div>
					</div>
				</div>
			</div>
			
			<div class="result_btn_box">
				<button onclick="setAddress()" type="button"  class="btn"> 경로설정 </button>
			</div>
		</div>
		
		
		
		<!-- 모달 HTML -->
		<div class="modal_wrap">
			<div class="modal_box">
				<h3 class="modal_title">
					출발 / 도착지 선택
				</h3>
				<div class="modal_content">
					
				</div>
				<div class="modal_btns">
					<button class="modal_check" onclick="centerAddr()" type="button"  class="btn">확인</button>
					<button onclick="closeModal()" class="modal_cencel" type="button"  class="btn">닫기</button>
				</div>
			</div>
		</div>
		
		
		
		<div class="footer">
		</div>
		
	</div>

	
	
	
	<!-- 카카오 지도 js -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=10ba1da6842b6225a9594bb0758c576f&libraries=services,clusterer"></script>
	<!-- TMAP API -->
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://apis.openapi.sk.com/tmap/jsv2?version=1&appKey=FTgL4h9DokizpClCioLn7EvI4rM9aVhU0GIvct20"></script>
	<script src="https://apis.openapi.sk.com/tmap/vectorjs?version=1&appKey=FTgL4h9DokizpClCioLn7EvI4rM9aVhU0GIvct20"></script>
	<!-- 사용자정의 -->
	<script src="/pangpang/map/js/map.js" type="text/javascript"></script> 
	<!-- 사용자정의 : 모달 -->
	<script src="/pangpang/map/js/modal.js" type="text/javascript"></script>
</body>
</html>
