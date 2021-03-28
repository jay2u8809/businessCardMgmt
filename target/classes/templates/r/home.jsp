<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Making Business Card</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css" integrity="sha384-v2Tw72dyUXeU3y4aM2Y0tBJQkGfplr39mxZqlTBDUZAb9BGoC40+rdFCG0m10lXk" crossorigin="anonymous">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/fontawesome.css" integrity="sha384-q3jl8XQu1OpdLgGFvNRnPdj5VIlCvgsDQTQB6owSOHWlAurxul7f+JpUOVdAiJ5P" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="resources/css/newMain_css/common.css">
	<link href="https://fonts.googleapis.com/css?family=Playfair+Display:700i" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="resources/css/newMain_css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="resources/css/newMain_css/index.css" />
	<link rel="stylesheet" type="text/css" href="resources/css/newMain_css/component.css" />
	<link rel="stylesheet" type="text/css" href="resources/css/newMain_css/link_hover_basic.css">
	<link rel="stylesheet" type="text/css" href="resources/css/newMain_css/link_hover.css">
	<link rel="stylesheet" type="text/css" href="resources/css/newMain_css/logoutbutton.css">
	<link rel="stylesheet" type="text/css" href="resources/css/newMain_css/logoutbutton_default.css">
	
	<script type="text/javascript" src="resources/js/newMain_js/modernizr.custom.js"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.2.1.js"/>"></script>
	<script>document.documentElement.className = 'js';</script>
	<script>
	
	/*
	 *	뒤로가기 금지 : Histroy 페이지를 현재 페이지로 하여 계속 현재 페이지에 머물게 한다
	 */
	 <c:if test="${sessionScope.userid ne null }">
	 
		history.pushState(null, null, location.href);

	 	window.onpopstate = function(event) {

	 		history.go(1);
	 	};
	 </c:if>
	 	
	
	/*
		로그아웃 확인 메소드
	*/
		function logoutValid() {
			
			if(confirm("로그아웃 하시겠습니까?")) {
				
				location.href="users/logout";
				return true;
			}
		
			return false;
		}
	
	</script>

</head>
<body class="demo-1">
	<%-- <header class="codrops-header">
		<div class="codrops-links">
			<c:choose>
				<c:when test="${sessionScope.userid eq null}">
					<a class=""  href="users/join">회원가입</a>
					<a class=""  href="users/loginForm">로그인</a>
				</c:when>
				<c:otherwise>
					<pre class="">${sessionScope.username}(${sessionScope.userid})님		</pre>
					<a class=""  style="cursor:pointer;" onclick="return logoutValid();">로그아웃</a>
				</c:otherwise>
			</c:choose>
		</div> 
	</header> --%>
	<!-- 정현수가 넣음 - 아리소루 로고 -->
	<div class="logoBox"><img class="logo" src="<c:url value="/resources/img/ArisoruLogo(Big).png" />"/></div>
	<!-- 정보승이 넣음 -->
	<div class="videoBox">
		<video autoplay loop="100" muted  id="videoBG">
			<source src="resources/img/tutorial.mp4" type="video/mp4">
		</video>
	</div>
	<!-- 
	<div class="view">
		<div class="content">
			<header class="codrops-header">
				<div class="codrops-links">
					<c:choose>
						<c:when test="${sessionScope.userid eq null}">
							<a class=""  href="users/join">회원가입</a>
							<a class=""  href="users/loginForm">로그인</a>
						</c:when>
						<c:otherwise>
							<pre class="">${sessionScope.username}(${sessionScope.userid})님		</pre>
							<a class=""  style="cursor:pointer;" onclick="return logoutValid();">로그아웃</a>
						</c:otherwise>
					</c:choose>
				</div>
				
				<p class="codrops-header__info">Do you want to make yourself?<br/>Make it your own.</p>
				<span class="codrops-header__deco">hitherto</span>
				<h1 class="codrops-header__title">Arias</h1>
				<p class="codrops-header__tagline">make a business card</p>
				
			</header>
		</div>
		 -->
	<button class="btn btn--menu">
		<svg class="icon icon--menu"><use xlink:href="#icon-menu"></use></svg>
		<svg class="icon icon--cross"><use xlink:href="#icon-cross"></use></svg>
	</button>
	<nav class="tabsnav tabsnav--vertical tabsnav--ander">
		<div class="tabsnav__item">
			<div class="tabsnav__bar"></div>
			<h3 class="tabsnav__title">TemplateList</h3>
		</div>
		<div class="tabsnav__item">
			<div class="tabsnav__bar"></div>
			<h3 class="tabsnav__title">Tools</h3>
		</div>
		<div class="tabsnav__item">
			<div class="tabsnav__bar"></div>
			<h3 class="tabsnav__title">Myinfo</h3>
		</div>
		<div class="tabsnav__item">
			<div class="tabsnav__bar"></div>
			<h3 class="tabsnav__title">Contact</h3>
		</div>
	</nav>
	<div class="logout">
		<button class="btn-5" onclick="logoutValid()">LogOut</button>
	</div>
	<div class="tabscontent">
		<div class="tabscontent__item">
			<figure class="poster">
				<img class="poster__img" src="resources/img/newMain_img/img1.jpg" alt="Poster 1"/>
				<figcaption class="poster__caption">
					<h2 class="poster__title" >TemplateList</h2>
					<p class="poster__deco">Choose a simple template</p>
					<div class="poster__box"></div>
					<span class="poster__number"><i class="fas fa-location-arrow"></i></span>
				</figcaption>
			</figure>
			<div class="cl-effect-21">
				<a href="/www/templateList">TemplateList</a>
			</div>
		</div>
		<div class="tabscontent__item">
			<figure class="poster">
				<img class="poster__img" src="resources/img/newMain_img/img1.jpg" alt="Poster 1"/>
				<figcaption class="poster__caption">
					<h2 class="poster__title">Tools</h2>
					<p class="poster__deco">명함 만들기</p>
					<div class="poster__box"></div>
					<span class="poster__number"><i class="fas fa-location-arrow"></i></span>
					</figcaption>
				</figure>
				<div class="cl-effect-21">
					<a href="/www/tool">Tools</a>
				</div>
			</div>
			<div class="tabscontent__item">
				<figure class="poster">
					<img class="poster__img" src="resources/img/newMain_img/img1.jpg" alt="Poster 1"/>
					<figcaption class="poster__caption">
						<h2 class="poster__title">MyInfo</h2>
						<p class="poster__deco">카드관리</p>
						<div class="poster__box"></div>
						<span class="poster__number" ><i class="fas fa-location-arrow"></i></span></a>
					</figcaption>
				</figure>
				<div class="cl-effect-21">
					<a href="/www/myInfo">MyInfo</a>
				</div>
			</div>
			<div class="tabscontent__item">
				<figure class="poster">
					<img class="poster__img" src="resources/img/newMain_img/img1.jpg" alt="Poster 1"/>
					<figcaption class="poster__caption">
						<h2 class="poster__title">Contact</h2>
						<p class="poster__deco">공지사항</p>
						<div class="poster__box"></div>
						<span class="poster__number" href="/www/contact"><i class="fas fa-location-arrow"></i></span>
					</figcaption>
				</figure>
				<div class="cl-effect-21">
					<a href="/www/contact">Contact</a>
				</div>
			</div>
			<button class="btn btn--back"><i class="fas fa-angle-left"></i></button>
		</div>
	</div>
	<script src="resources/js/newMain_js/anime.min.js"></script>
	<script src="resources/js/newMain_js/enquire.min.js"></script>
	<script src="resources/js/newMain_js/tabsnav.js"></script>
	<script src="resources/js/newMain_js/demo1.js"></script>
</body>
</html>