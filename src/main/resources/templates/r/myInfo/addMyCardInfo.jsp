<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- 정보승 : CSS 및 JS 공통부분 -->
<!-- addMyCardInfo -->
<html lang="ko">
<head>

	<title>Templates</title>
	
	<!-- 템플릿 리스트 CSS -->
	<link href="https://fonts.googleapis.com/css?family=Inconsolata:400,700|Poppins:700" rel="stylesheet">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css" integrity="sha384-v2Tw72dyUXeU3y4aM2Y0tBJQkGfplr39mxZqlTBDUZAb9BGoC40+rdFCG0m10lXk" crossorigin="anonymous">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/fontawesome.css" integrity="sha384-q3jl8XQu1OpdLgGFvNRnPdj5VIlCvgsDQTQB6owSOHWlAurxul7f+JpUOVdAiJ5P" crossorigin="anonymous">
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/newMain_css/menu/normalize.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/newMain_css/menu/menu.css "/>" >
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/newMain_css/menu/icons.css "/>" >
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/newMain_css/menu/menustyle.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/mycardtextlist/css/my_card_text_list.css" />">
	
	<script src="<c:url value="/resources/js/jquery-3.3.1.min.js"/> "> </script>
	<script src="<c:url value="/resources/js/newMain_js/menu/modernizr.custom.js"/>"></script>
	<script src="<c:url value="/resources/myinfo/insertYourCard/js/insertValid.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/mycardtextlist/js/my_card_text_list.js" />"></script>
</head>
<body>
	<!-- 정보승 : <header>부분 -->
	<%@ include file="/WEB-INF/views/common/bodyHeader.jsp" %>
	
	<article>
			<ul>
				<li>
					<div class="mycard">
						<img src='../mycard/download?mycardnum=${myCardNum }'>
					</div>
				</li>
				<LI>
					<div class="card-container">
					
						 <div class="toggle1">
							<!-- <i class="fa fa-user fa-pencil fa-lg"></i>
							<div class="tooltip">수정하기</div> -->
						</div>
						
						<div class="card login-register login-reset">
							<h1 class="title">Card info</h1>
							<form method="post" action="../mycard/insertMyCard" onsubmit="return insertValid();"> 
							
							<!-- 제작한 명함의 번호 -->
							<input type="hidden" value="${myCardNum }" name="mycardnum" >
							
								<!-- 대표이름 -->
								<div class = "input-container has-feedback">
									<input type="text" id="name1" name="name1" placeholder="Name1"  />
									<div class="check"></div>
									<div class="bar"></div>
								</div>
								
								<!-- 추가 이름 -->
								<div class = "input-container has-feedback">
									<input type="text" id="name2" name="name2" placeholder="Name2"  />
									<div class="check"></div>
									<div class="bar"></div>
								</div>
								
								<!-- 추가 이름2 -->
								<!-- <div class = "input-container has-feedback">
									<input type="text" id="name3" name="name3" placeholder="이름(추가)"  />
									<div class="check"></div>
									<div class="bar"></div>
								</div> -->
								
								<!-- 회사명 -->
								<div class = "input-container has-feedback">
									<input type="text" id="company" name="company" placeholder="Company" />
									<div class="check"></div>
									<div class="bar"></div>
								</div>
								
								<!-- 부서명 -->
								<div class = "input-container has-feedback">
									<input type="text" id="department" name="department" placeholder="Deparment"  />
									<div class="check"></div>
									<div class="bar"></div>
								</div>
								
								<!-- 직책명 -->
								<div class = "input-container has-feedback">
									<input type="text" id="job" name="job" placeholder="Job"  />
									<div class="check"></div>
									<div class="bar"></div>
								</div>
								
								<!-- 전화번호 -->
								<div class = "input-container has-feedback">
									<input type="text" id="tel" name="tel" placeholder="Tel"  />
									<div class="check"></div>
									<div class="bar"></div>
								</div>
								
								<!-- 휴대폰 번호 -->
								<div class = "input-container has-feedback">
									<input type="text" id="phone" name="phone" placeholder="Phone" />
									<div class="check"></div>
									<div class="bar"></div>
								</div>
								
								<!-- Fax 번호 -->
								<div class = "input-container has-feedback">
									<input type="text" id="fax" name="fax" placeholder="Fax"  />
									<div class="check"></div>
									<div class="bar"></div>
								</div>
								
								<!-- E-Mail 주소 -->
								<div class = "input-container has-feedback">
									<input type="email" id="email" name="email" placeholder="E-Mail"  />
									<div class="check"></div>
									<div class="bar"></div>
								</div>
								
								<!-- 회사 주소 -->
								<div class = "input-container has-feedback">
									<input type="text" id="address" name="address" placeholder="Address"  />
									<div class="check"></div>
									<div class="bar"></div>
								</div>
								
								<!-- 메모(otherinfo2) -->
								<div class = "input-container has-feedback">
									<input type="text" id="otherinfo2" name="otherinfo2" placeholder="Memo"  />
									<div class="check"></div>
									<div class="bar"></div>
								</div>
								
								<!-- 제작한 명함의 추가 정보 전송 버튼 -->
								<div class="button-container">
									<button type="submit" class="rkmd-btn btn-lightBlue ripple-effect float-right">
										<span>send information</span>
									</button>
								</div>
								
							</form>
						</div><!-- 명함 정보 보기 -->
					</div>
				</LI>
			</ul>
		</article>
	<footer></footer><!-- footer end -->
	<!-- 
	<script src="resources/js/commonmain.js"></script>
	 -->
	<script src="<c:url value="/resources/js/newMain_js/menu/classie.js" />"></script>
	<script src="<c:url value="/resources/js/newMain_js/menu/borderMenu.js" />"></script>
	
</body>
</html>
