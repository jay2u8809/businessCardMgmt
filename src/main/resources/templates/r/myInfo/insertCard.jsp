<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- insertCard -->
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
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
	<link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
	
	<link href="<c:url value="/resources/css/newMain_css/menu/normalize.css" />" rel="stylesheet" media="screen">
	<link href="<c:url value="/resources/css/newMain_css/menu/menu.css" />" rel="stylesheet" media="screen">
	<link href="<c:url value="/resources/css/newMain_css/menu/icons.css" />" rel="stylesheet" media="screen">
	<link href="<c:url value="/resources/css/newMain_css/menu/menustyle.css" />" rel="stylesheet" media="screen">
	<link href="<c:url value="/resources/myinfo/insertYourCard/css/ocr.css"/>" rel="stylesheet" media="screen">
	<link href="<c:url value="/resources/myinfo/insertYourCard/css/main.css"/>" rel="stylesheet" media="screen" rel="stylesheet">
	<link href="<c:url value="/resources/myinfo/insertYourCard/css/layout.css"/>" rel="stylesheet" media="screen" rel="stylesheet">
	<link href="<c:url value="/resources/myinfo/insertYourCard/css/swiper.css"/>" rel="stylesheet" media="screen" rel="stylesheet">
	
	<script src="<c:url value="/resources/js/jquery-3.3.1.min.js" />"> </script>
	<script src="<c:url value="/resources/js/newMain_js/modernizr.custom.js" />"> </script>
	<script src="<c:url value="/resources/myinfo/insertYourCard/js/insertValid.js" />"></script>
	<script src="<c:url value="/resources/myinfo/insertYourCard/js/ocr.js" />"></script>
	<script src="<c:url value="/resources/myinfo/insertYourCard/js/inserContent.js" />"></script>
	<script src="<c:url value="/resources/myinfo/insertYourCard/js/swiper.js" />"></script>
	<script>
		window.onload = function(){
			var swiper = new Swiper('.swiper-container', {
				pagination: '.swiper-pagination',
				paginationType: 'progress',
				slidesPerView: 'auto',
				paginationClickable: true,
				spaceBetween: 0,
				freeMode: true,
				nextButton: '.next',
				prevButton: '.back'
			});
		};
	</script>
</head>
<body>
	<div class="container">

		<%@ include file="/WEB-INF/views/common/bodyHeader.jsp" %>
		
		<article>
			<!-- OCR 분석을 위한 이미지 Form -->
			<form id="tempUpload" enctype="multipart/form-data">

			</form>
			<div class="wrap">
				<form action="../yourcard/insertYourCard" method="post" enctype="multipart/form-data" id="cardInfoForm">
					<!-- 업로드한 사진의 미리보기가 보이는 부분 -->
					<div id="menu" class="menu">
						<!-- <div class="close-icon"><i class="fa fa-bars"  onclick="myFunction()"></i></div> -->

						<ul class="a">
							<li id="input_imgs"></li>
							<li>
								<a href="#" onclick="fileUploadAction();">
									<i class="fas fa-plus"></i>
									<%-- <img class="uploadBtn" src="<c:url value="/resources/myinfo/insertYourCard/img/addImg.png "/>" > --%>
								</a>
							</li>
							<li class="imgs_wrap"></li>
						</ul>
					</div>
					<!-- 미리보기 사진을 선택했을 때 크게 보이는 부분 -->
					<div id="main" class="main">
						<div class="ocr img selectedImg" ><!--  -->
							<%-- <img src="<c:url value="/resources/myinfo/insertYourCard/img/mycard.png "/>" > --%>
							<img src="<c:url value="/resources/img/ArisoruSketch(Blue).png"/>">
						</div>


						<section class="feature" style="margin-left: 10%;">
						<div class="inWrap">
							<div class="fInner swiper-container">
							<ul class="swiper-wrapper">
								<!-- <ul class="swiper-wrapper"> -->
									<!-- <li class="swiper-slide"><a href="#"><span>Finding Dory<br>(2016)</span></a></li> -->
									
								</ul>
							</div>
						</div>
					</section>
					</div>

					<!-- OCR 분석결과가 나오는 부분 -->
					<div class="menu2">
						<div class="close-icon" id="target" ><i class="fa fa-bars"></i></div>
						<div class="insertCardBtn" id="cardBtn">
							<ul style="list-style: none; overflow: hidden;">
								<li style="float: left; width: 50%">
									<button type="button" class="btn btn-3 btn-sep icon-heart" onclick="return cardInfoSubmit();">submit</button>
								</li>
								<li style="float: left; width: 50%">
									<button type="reset" class="btn btn-4 btn-sep icon-send">reset</button>
								</li>
							</ul>
							<!-- <button type="button" class="" onclick="return cardInfoSubmit();">Submit</button>
							<button type="reset" class="">Reset</button> -->
							<!-- <button type="button" class="" id="selectMenuAdd" onclick="selectMenuAdd1();">항목 추가하기</button> -->
						</div>
						<ul id="cardinfo">
							<!-- 등록을 위한 버튼 부분 -->
							<li>Result		<br /><textarea name="" id="etc" rows="5" cols="32" value=""></textarea></li>
							<li>Name  		<br /><input type="text" name="name1" id="name1" size="32" value="">		</li>
							<li>Name  		<br /><input type="text" name="name2" id="name2" size="32" value="">		</li>
							<!-- <li>Name  		<br /><input type="text" name="name3" id="name3" size="32"" value="">		</li> -->
							<li>Company 	<br /><input type="text" name="company" id="company" size="32" value="">	</li>
							<li>Department 	<br /><input type="text" name="department" id="department" size="32" value=""></li>
							<li>Job 		<br /><input type="text" name="job" id="job" size="32" value="">		</li>
							<li>Phone 		<br /><input type="text" name="phone" id="phone" size="32" value="">		</li>
							<li>Tel 		<br /><input type="text" name="tel" id="tel" size="32" value="">		</li>
							<li>Fax 		<br /><input type="text" name="fax" id="fax" size="32" value="">		</li>
							<li>E-Mail 		<br /><input type="text" name="email" id="email" size="32" value="">		</li>
							<li>Sex			<br />
								<input type ='radio' name='sex' size="32" value='M' checked="checked" >남성
								<input type ='radio' name='sex' size="32" value='W'>여성
							</li>
							<li>Address		<br /><textarea name="address" id="address" rows="5" cols="32" ></textarea></li>
							<li>Memo		<br /><textarea name="memo" id="memo" rows="5" cols="32"></textarea></li>
							<li>OtherInfo	<br /><input type="text" name="otherinfo" id="otherinfo" size="32" value="">	</li>
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
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
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
	<link href="<c:url value="/resources/myinfo/insertYourCard/css/layout.css"/>" rel="stylesheet" media="screen" rel="stylesheet">
	<link href="<c:url value="/resources/myinfo/insertYourCard/css/swiper.css"/>" rel="stylesheet" media="screen" rel="stylesheet">
	
	<script src="<c:url value="/resources/js/jquery-3.3.1.min.js" />"> </script>
	<script src="<c:url value="/resources/js/newMain_js/modernizr.custom.js" />"> </script>
	<script src="<c:url value="/resources/myinfo/insertYourCard/js/ocr.js" />"></script>
	<script src="<c:url value="/resources/myinfo/insertYourCard/js/insertValid.js" />"></script>
	<script src="<c:url value="/resources/myinfo/insertYourCard/js/inserContent.js" />"></script>
	<script src="<c:url value="/resources/myinfo/insertYourCard/js/swiper.js" />"></script>
</head>
<body>
	<div class="container">
	
		<%@ include file="/WEB-INF/views/common/bodyHeader.jsp" %>
		
		<article>
			<!-- OCR 분석을 위한 이미지 Form -->
			<form id="tempUpload" enctype="multipart/form-data">
	
			</form>
			<div class="wrap">
			<form action="../yourcard/insertYourCard" method="post" enctype="multipart/form-data" id="cardInfoForm" >
				<!-- 업로드한 사진의 미리보기가 보이는 부분 -->
				<div id="menu" class="menu">
					<div class="close-icon"><!-- <i class="fa fa-bars"  onclick="myFunction()"></i> --></div>
					
						<ul>
							<li id="input_imgs"></li>
							<li>
								<a href="#" onclick="fileUploadAction();">
									<img class="uploadBtn" src="<c:url value="/resources/myinfo/insertYourCard/img/addImg.png "/>" >
								</a>
							</li>
							<li class="imgs_wrap"></li>
						</ul>
				</div>
				
				<!-- 미리보기 사진을 선택했을 때 크게 보이는 부분 -->
				<div id="main" class="main">
					<div class="ocr img selectedImg" ><!--  -->
						<img src="<c:url value="/resources/myinfo/insertYourCard/img/mycard.png "/>" >
					</div>
					
					<section class="feature" style="margin-left: 10%;">
						<div class="inWrap">
							<div class="fInner swiper-container">
								<ul class="swiper-wrapper">
									<li class="swiper-slide"><a href="#"><span>Finding Dory<br>(2016)</span></a></li>
									<li class="swiper-slide"><a href="#"><span>The Good Dinosaur<br>(2015)</span></a></li>
									<li class="swiper-slide"><a href="#"><span>Inside Out<br>(2015)</span></a></li>
									<li class="swiper-slide"><a href="#"><span>Brave<br>(2012)</span></a></li>
									<li class="swiper-slide"><a href="#"><span>Car 2<br>(2011)</span></a></li>
									<li class="swiper-slide"><a href="#"><span>Toy Story 3<br>(2010)</span></a></li>
									<li class="swiper-slide"><a href="#"><span>Up<br>(2009)</span></a></li>
									<li class="swiper-slide"><a href="#"><span>WALL·E<br>(2008)</span></a></li>
									<li class="swiper-slide"><a href="#"><span>Ratatouille<br>(2007)</span></a></li>
									<li class="swiper-slide"><a href="#"><span>Cars<br>(2006)</span></a></li>
									<li class="swiper-slide"><a href="#"><span>The Incredibles<br>(2004)</span></a></li>
									<li class="swiper-slide"><a href="#"><span>Finding Nemo<br>(2003)</span></a></li>
									<li class="swiper-slide"><a href="#"><span>Monsters, Inc.<br>(2001)</span></a></li>
									<li class="swiper-slide"><a href="#"><span>Toy Story 2<br>(1999)</span></a></li>
									<li class="swiper-slide"><a href="#"><span>A Bug's Life<br>(1998)</span></a></li>
									<li class="swiper-slide"><a href="#"><span>Toy Story<br>(1995)</span></a></li>
								</ul>
							</div>
						</div>
					</section>
					
					<div class="selectMyCard" >
							
				
					</div>
				</div>
				
				<!-- OCR 분석결과가 나오는 부분 -->
				<div class="menu2">
					<div class="close-icon" id="target" ><i class="fa fa-bars"></i></div>
						<div class="insertCardBtn" id="cardBtn">
					    	<button type="button" class="" onclick="return cardInfoSubmit()">Submit</button>
					    	<button type="reset" class="">Reset</button>
					    	<!-- <button type="button" class="" id="selectMenuAdd" onclick="selectMenuAdd1();">항목 추가하기</button> -->
					    </div>
						<ul id="cardinfo">
						<!-- 등록을 위한 버튼 부분 -->
							<li>Result		<br /><textarea name="" id="etc" rows="10" cols="35" value=""></textarea></li>
							<li>Name  		<br /><input type="text" name="name1" id="name1" size="32" value="">		</li>
							<li>Name  		<br /><input type="text" name="name2" id="name2" size="32" value="">		</li>
							<li>Name  		<br /><input type="text" name="name3" id="name3" size="32" value="">		</li>
							<li>Company 	<br /><input type="text" name="company" id="company" size="32" value="">	</li>
							<li>Department 	<br /><input type="text" name="department" id="department" size="32" value=""></li>
							<li>Job 		<br /><input type="text" name="job" id="job" size="32" value="">		</li>
							<li>Phone 		<br /><input type="text" name="phone" id="phone" size="32" value="">		</li>
							<li>Tel 		<br /><input type="text" name="tel" id="tel" size="32" value="">		</li>
							<li>Fax 		<br /><input type="text" name="fax" id="fax" size="32" value="">		</li>
							<li>E-Mail 		<br /><input type="text" name="email" id="email" size="32" value="">		</li>
							<li>Sex			<br />
											<input type ='radio' name='sex' size="32" value='M' checked="checked" >남성
											<input type ='radio' name='sex' size="32" value='W'>여성
							</li>
							<li>Address 	<br /><input type="text" name="address" id="address" value="">	</li>
							<li>Memo 		<br /><input type="text" name="memo" id="memo" value="">		</li>
							<li>OtherInfo	<br /><input type="text" name="otherinfo" id="otherinfo" value="">	</li>
							
						</ul>
						
				</div>
			</form>
			</div>
		</article>
	</div><!-- /container -->
</body>
	<script src="<c:url value="/resources/js/newMain_js/menu/classie.js" />"> </script>
	<script src="<c:url value="/resources/js/newMain_js/menu/borderMenu.js" />"></script>
	
</html> --%>