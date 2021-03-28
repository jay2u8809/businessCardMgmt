<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<!-- 
	@comment	: 카드 등록을 테스트하는 페이지
	@author		: 정보승
 -->
<html lang ="ko">
 <head>
	<title>카드 등록</title>
	<%@ include file="/WEB-INF/views/common/headPart.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	  <!-- Bootstrap -->
    <link href="<c:url value="/resources/css/newMain_css/bootstrap.min.css"/>" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/myinfo/insertYourCard/css/imgareaselect-default.css"/>" rel="stylesheet" media="screen">
    <link href="<c:url value="/resources/myinfo/insertYourCard/css/pick-a-color-1.1.7.min.css"/>" rel="stylesheet" media="screen">
	<link href="<c:url value="/resources/myinfo/insertYourCard/css/main.css"/>" rel="stylesheet" media="screen" rel="stylesheet">
	
	<script type="text/javascript">
	

	function deleteImageAction(index) {

		if(sel_files.length == 0) {
			
			// 이미지 정보들을 초기화
			sel_files = [];
			$(".imgs_wrap").empty();
		}
		
		console.log("index : " + index);

		sel_files.splice(index, 1);

		var img_id = "#img_id_" + index;
		$(img_id).remove();

		console.log(sel_files);
	
	}

	// 다중 파일 POST 전송
	function submitAction() {

		var data = new FormData();

		for(var i=0, len = sel_files.length; i < len; i++) {

			var name = "image_" + i;
			data.append(name, sel_files[i]);
		}

		data.append("image_count", sel_files.length);

		var xhr = new XMLHttpRequest();
		// 수정 필요
		xhr.open("POST", "./study01_af.php");
		xhr.onload = function(e) {
			if(this.status == 200) {
				console.log("Result : " + e.currentTarget.responseText);
			}
		}

		xhr.send(data);
	}
	

	

	</script>
	</head>
	<body>

	<%@ include file="/WEB-INF/views/common/bodyHeader.jsp" %>
	
	<form action="../yourcard/insertYourCard" method="post" enctype="multipart/form-data" id="cardInfoForm">
	<div class="mainView" >
		<!-- 정보승 : 받은 명함의 OCR 분석 텍스트 및 직접 입력하는 부분 -->
		<div class="tab-pane right" >
			
			<div class="cardInfoMenu" id="cardInfo">
				
			</div>
		
		    <div class="insertCardBtn" id="cardBtn">
		    	<button type="button" class="btn btn-primary" onclick="return cardInfoSubmit();">Submit</button>
		    	<button type="reset" class="btn btn-default">Reset</button>
		    	<button type="button" class="btn btn-default" id="selectMenuAdd" onclick="selectMenuAdd1();">항목 추가하기</button>
		    </div>
		    
		</div>
	
		<!-- 정보승 : 업로드한 이미지가 나오는 부분 -->
		<div class="col-8">
		    
		    <div class="tab-content" style="float: left; margin-top: 8%;">
		        <div class="tab-pane active" id="image" >
					
					<button type="button" class="btn btn-primary my_button" onclick="fileUploadAction();"> Upload </button>
					<div class ="fileUploadBtn" id="input_imgs">
						
					</div>
				</div>
				
				<!-- 가장 좌측에 이미지들이 세로로 정렬되는 부분 : 미리보기 -->
				<!-- <div class="imgs_wrap" style="float: left; width: 320px;">  -->
				
				<div class="imgs_wrap" style="float: left; width: 300px;height: 1000px; background-color: #ffe7d5;">
					<pre id="comment">NO IMAGES , PLEASE CLICK YOUR IMAGES.</pre>
					
				</div>
				
				<!-- 미리보기 선택시 확대된 이미지가 보이는 부분 -->
				<div class="centerDiv" style=" float: left;  width: 60%; margin-left: 30px; " >
					<div class="selectedImg" style="width=600px; position: relative;" >
						<img src="<c:url value="/resources/myinfo/insertYourCard/img/bg4(B).png "/>" >
						
					</div>
					<!-- 미리보기 밑에 유저의 명함이미지가 보이는 부분(My Card) -->
					<div class="selectMyCard" style=" float: bottom;   top : 580px; left :40px; " >
							
				
					</div>
				</div>
				
				
			
		    </div>
		    
		</div>
	</div>
	</form>
	
	<form id="tempUpload" enctype="multipart/form-data">
	
	</form>
	
		<!-- JavaScript plugins (requires jQuery) -->
		<script src="<c:url value="/resources/myinfo/insertYourCard/js/jquery.min.js" />"></script>	
		<!-- Include all compiled plugins (below), or include individual files as needed -->
		<script src="<c:url value="/resources/myinfo/insertYourCard/js/bootstrap.min.js" />"></script>
		<script src="<c:url value="/resources/myinfo/insertYourCard/js/jquery.imgareaselect.js" />"></script>
		<script src="<c:url value="/resources/myinfo/insertYourCard/js/tinycolor-0.9.15.min.js" />"></script>
		<script src="<c:url value="/resources/myinfo/insertYourCard/js/pick-a-color-1.1.7.min.js" />"></script>
		<script src="<c:url value="/resources/myinfo/insertYourCard/js/main.js" />"></script>
		<script src="<c:url value="/resources/myinfo/insertYourCard/js/inserContent.js" />"></script>
		<script src="<c:url value="/resources/myinfo/insertYourCard/js/insertValid.js" />"></script>
		
		<!-- 
	<script type="text/javascript" src="<c:url value="/resources/js/anime.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/main.js"/>"></script>
	 -->
		
		<script src="<c:url value="/resources/js/newMain_js/menu/classie.js" />"> </script>
		<script src="<c:url value="/resources/js/newMain_js/menu/borderMenu.js" />"></script>
</body>
</html>