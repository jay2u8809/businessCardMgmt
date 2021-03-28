<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
	
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
	<title>Insert Business Card</title>
	<meta name="description" content="Responsive Animated Border Menus with CSS Transitions" />
	<meta name="keywords" content="navigation, menu, responsive, border, overlay, css transition" />
	<meta name="author" content="Codrops" />
	
	<link href="https://fonts.googleapis.com/css?family=Inconsolata:400,700|Poppins:700" rel="stylesheet">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css" integrity="sha384-v2Tw72dyUXeU3y4aM2Y0tBJQkGfplr39mxZqlTBDUZAb9BGoC40+rdFCG0m10lXk" crossorigin="anonymous">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/fontawesome.css" integrity="sha384-q3jl8XQu1OpdLgGFvNRnPdj5VIlCvgsDQTQB6owSOHWlAurxul7f+JpUOVdAiJ5P" crossorigin="anonymous">
	
	
	<link href="<c:url value="/resources/css/newMain_css/menu/normalize.css" />" rel="stylesheet" media="screen">
	<link href="<c:url value="/resources/css/newMain_css/menu/menu.css" />" rel="stylesheet" media="screen">
	<link href="<c:url value="/resources/css/newMain_css/menu/icons.css" />" rel="stylesheet" media="screen">
	<link href="<c:url value="/resources/css/newMain_css/menu/menustyle.css" />" rel="stylesheet" media="screen">
	<link href="<c:url value="/resources/myinfo/insertYourCard/css/ocr.css"/>" rel="stylesheet" media="screen">
	<link href="<c:url value="/resources/myinfo/insertYourCard/css/main.css"/>" rel="stylesheet" media="screen" rel="stylesheet">
	
	<script src="<c:url value="/resources/js/jquery-3.3.1.min.js" />"> </script>
	<script src="<c:url value="/resources/js/newMain_js/modernizr.custom.js" />"> </script>
	<script src="<c:url value="/resources/myinfo/insertYourCard/js/insertValid.js" />"></script>
	<script src="<c:url value="/resources/myinfo/insertYourCard/js/ocr.js" />"></script>
	
	
</head>
<body>
	<div class="container">
	
		<%@ include file="/WEB-INF/views/common/bodyHeader.jsp" %>
		
		<article>
			
			<div class="wrap">
			<form action="yourCardUpdate" method="post" id="cardInfoForm" onsubmit="return updateValid();">
				<input type="hidden" name="yourcardnum" value="${originalYourCardInfo.yourcardnum }" />
				<!-- 미리보기 사진을 선택했을 때 크게 보이는 부분 -->
				<div id="main" class="main">
					<div class="selectedImg2" ><!--  -->
						<img src="../yourcard/download?yourcardnum=${originalYourCardInfo.yourcardnum }" >
					</div>
				</div>
				
				<!-- OCR 분석결과가 나오는 부분 -->
				<div class="menu2">
					<div class="close-icon" id="target" ><i class="fa fa-bars"></i></div>
						<div class="" id="cardBtn">
					    	<ul style="list-style: none; overflow: hidden;">
								<li style="float: left; width: 50%">
									<button type="submit" class="btn btn-3 btn-sep icon-heart">submit</button>
								</li>
								<li style="float: left; width: 50%">
									<button type="reset" class="btn btn-4 btn-sep icon-send">reset</button>
								</li>
							</ul>
					    </div>
						<ul id="cardinfo">
						<!-- 등록을 위한 버튼 부분 -->
							<li>Name  		<br /><input type="text" name="name1" id="name1" size="32" value="${originalYourCardInfo.name1 }">		</li>
							<li>Name  		<br /><input type="text" name="name2" id="name2" size="32" value="${originalYourCardInfo.name2 }">		</li>
							<li>Name  		<br /><input type="text" name="name3" id="name3" size="32" value=""${originalYourCardInfo.name3 }>		</li>
							<li>Company 	<br /><input type="text" name="company" id="company" size="32" value="${originalYourCardInfo.company }">	</li>
							<li>Department 	<br /><input type="text" name="department" id="department" size="32" value="${originalYourCardInfo.department }"></li>
							<li>Job 		<br /><input type="text" name="job" id="job" size="32" value="${originalYourCardInfo.job }">		</li>
							<li>Phone 		<br /><input type="text" name="phone" id="phone" size="32" value="${originalYourCardInfo.phone }">		</li>
							<li>Tel 		<br /><input type="text" name="tel" id="tel" size="32" value="${originalYourCardInfo.tel }">		</li>
							<li>Fax 		<br /><input type="text" name="fax" id="fax" size="32" value="${originalYourCardInfo.fax }">		</li>
							<li>E-Mail 		<br /><input type="text" name="email" id="email" size="32" value="${originalYourCardInfo.email }">		</li>
							<li>Sex			<br />
											<input type ="radio" name="sex" size="32" value="M" <c:if test="${originalYourCardInfo.sex == 'M'.charAt(0)}"> checked="checked" </c:if> >남성
											<input type ="radio" name="sex" size="32" value="W" <c:if test="${originalYourCardInfo.sex == 'W'.charAt(0)}"> checked="checked" </c:if> >여성
											
							</li>
							<li>Address		<br /><textarea name="address" id="address" rows="5" cols="32" value="">${originalYourCardInfo.address }</textarea></li>
							<li>Memo		<br /><textarea name="memo" id="memo" rows="5" cols="32" value="">${originalYourCardInfo.memo }</textarea></li>
							<li>OtherInfo	<br /><input type="text" name="otherinfo" id="otherinfo" size="32" value="${originalYourCardInfo.otherinfo }">	</li>
							<%-- <li>Address 	<br /><input type="text" name="address" id="address" size="32" value="${originalYourCardInfo.address }">	</li>
							<li>Memo 		<br /><input type="text" name="memo" id="memo" size="32" value="${originalYourCardInfo.memo }">		</li>
							<li>OtherInfo	<br /><input type="text" name="otherinfo" id="otherinfo" size="32" value="${originalYourCardInfo.otherinfo }">	</li> --%>
						</ul> 	
				</div>
			</form>
			</div>
		</article>
	</div><!-- /container -->
</body>
	<script src="<c:url value="/resources/js/newMain_js/menu/classie.js" />"> </script>
	<script src="<c:url value="/resources/js/newMain_js/menu/borderMenu.js" />"></script>
	
</html>