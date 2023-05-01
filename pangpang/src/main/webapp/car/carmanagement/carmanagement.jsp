<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<!-- 모달   -->
	<link href="/pangpang/car/css/Modal.css" rel="stylesheet">
	<link href="/pangpang/car/css/carmanagement.css" rel="stylesheet">
</head>
<body>
	
	 <%@ include file="/header.jsp" %> 
	<script type="text/javascript">
		// 권한제어 
		if( memberInfo.member_rank != 3 ){
			alert('접급할 수 없는 권한입니다.'); 
			location.href="/pangpang/index.jsp";
		} 
	</script>
	<%String carmanage_no=	request.getParameter("carmanage_no");%>
	<input type="hidden" class="carmanage_no" value="<%=carmanage_no%>"> 
	
	
	<div class="content_box">

		<div class="content_box_header" >
				보유차량관리
		</div>
		<div class="content_box_text">
			<button onclick="onpenModal(1 , 0)" type="button"  class="btn" style="margin-bottom: 10px;">등록하기</button>
		   	<table class="table carmanage table1">
		      
		   	</table>
	   </div>
	</div>
	

	<div class="modal_wrap">
	<!-- 	<div class="modal_box">
			<h3 class="modal_title">
				등록하실내용을입력해주세요
			</h3>
			<div class="modal_content">
				   <form class="car_formdata">
				   
				      차번호:       	<input name="carmanage_number" type="text">			  		<br>
				      차량이름:      	<input name="carmanage_name" type="text">			     	<br>
				      차이미지:        
				      	<div class="carmanage_img">
							<input type="file" class="cimg"	 name="carmanage_img">	<br>
						</div>
				      사용가능여부:    	<input name="carmanage_use_yn" type="text">			 		<br>
				      차량등록일자:    	<input name="carmanage_start" type="text">			     	<br>
				      차량폐기일자:    	<input name="carmanage_finish" type="text">			  		<br>
				</form> 	
			</div>
			<div class="modal_btns">
				<button onclick="regi()" class="modal_check" type="button">확인</button>
				<button onclick="closeModal()" class="modal_cencel" type="button">닫기</button>
			</div>
		</div> -->
	</div>  
  

     <!-- 수정하기모달 -->
   	<button onclick="onpenModal2()" style="display: none" type="button">수정하기</button>
	
	<div class="modal_wrap2">
		<div class="modal_box">
			<h3 class="modal_title">
				수정하실내용을입력해주세요
			</h3>
			<div class="modal_content">
				<div class="namebox">
					<input name="carmanage_number" disabled></input>
					<input name="carmanage_name" disabled></input>
				</div>
				 <form class="updateForm">
	
				 	<table class="table updatetable">
				 		<tr>
				 			<td> 차이미지 </td> <td> <div name="carmanage_img" class="carmanage_img"></div> </td>
				 		</tr>
				 		<tr>
				 			<td> 차량등록여부 </td> 
				 			<td> 
					 			<select class="carmanage_use_yn" name="carmanage_use_yn" onchange="selectChange()">
					 				<option value="Y">Y</option>
					 				<option value="N">N</option>
					 			</select> 
				 			</td>
				 		</tr>
				 		<tr class="carmanage_finish">
				 			<td> 차량폐기일자 </td> <td> <input name="carmanage_finish" type="text"> </td>
				 		</tr>
		      		</table>
				</form> 	
			</div>
			<div class="modal_btns">
				<button onclick="carupdate()" class="modal_check" type="button">확인</button>
				<button onclick="closeModal2()" class="modal_cencel" type="button">닫기</button>
			</div>
		</div>
	</div>


   
      <!--jquery -->
   <script src="http://code.jquery.com/jquery-latest.min.js"></script>   
   <script src="/pangpang/car/js/carmanagement.js" type="text/javascript"></script>
	
	<script src="/pangpang/car/js/Modal.js" type="text/javascript"></script>
	
	
</body>
</html>




<!--

		
 
 -->