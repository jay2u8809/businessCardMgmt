<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- 정보승 : CSS 및 JS 공통부분 -->

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
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/templateList/css/slideList.css" />">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/templateList/css/uncover.css" />">
	
	<script src="<c:url value="/resources/js/newMain_js/menu/modernizr.custom.js"/>"></script>
	<script type="text/javascript" src="<c:url value=" resources/tool/js/jquery-3.3.1.min.js" />"></script>
	
	<script>
		var supportsCssVars = function() { var e, t = document.createElement("style"); return t.innerHTML = "root: { --tmp-var: bold; }", document.head.appendChild(t), e = !!(window.CSS && window.CSS.supports && window.CSS.supports("font-weight", "var(--tmp-var)")), t.parentNode.removeChild(t), e };
		supportsCssVars() || alert("Please view this demo in a modern browser that supports CSS Variables.");
		
		$(function(){
			
			
			//스크롤 이미지에 마우스가 들어올경우 포인터가 바뀌고 그 상태에서 클릭할 경우 함수가 발동한다.
			$(".scroll-img").mouseenter().css('cursor', 'pointer').on('click',function(){
				
			   var src = $(this).attr("data"); //data 속성의 값을 가져와서 변수에 넣는다.
					
			   	location.href="/www/tool?src="+src; // (HomeController 부분)tool의 값을 가진 컨트롤러에 parameter 값으로 src를 가지고 간다.
			});
			
		});
		
		
	</script>
</head>
<body>

	<!-- 정보승 : <header>부분 -->
	<%@ include file="/WEB-INF/views/common/bodyHeader.jsp" %>
	
	<article>
		<div class="content grid">
			<div class="grid__item">
				<div class="scroll-img" style="background-image: url(resources/templateList/img/bg1_1.png);" data="resources/templateList/img/bg1_1.png"></div>
				<div class="grid__item-titlewrap">
					<h2 class="grid__item-title">#Template1</h2>
					<p class="grid__item-description">Find me tomorrow or never</p>
				</div>
			</div>
			<div class="grid__item">
				<div class="scroll-img" style="background-image: url(resources/templateList/img/bg2_1.png);" data="resources/templateList/img/bg2_1.png"></div>
				<div class="grid__item-titlewrap">
					<h2 class="grid__item-title">#Template2</h2>
					<p class="grid__item-description">Never end it like that</p>
				</div>
			</div>
			<div class="grid__item">
				<div class="scroll-img" style="background-image: url(resources/templateList/img/bg3_1.png);" data="resources/templateList/img/bg3_1.png"></div>
				<div class="grid__item-titlewrap">
					<h2 class="grid__item-title">#Template3</h2>
					<p class="grid__item-description">Why make noise like that?</p>
				</div>
			</div>
			<div class="grid__item">
				<div class="scroll-img" style="background-image: url(resources/templateList/img/bg4_1.png);" data="resources/templateList/img/bg4_1.png"></div>
				<div class="grid__item-titlewrap">
					<h2 class="grid__item-title">#Template4</h2>
					<p class="grid__item-description">No point in running</p>
				</div>
			</div>
			<div class="grid__item">
				<div class="scroll-img" style="background-image: url(resources/templateList/img/bg5_1.png);" data="resources/templateList/img/bg5_1.png"></div>
				<div class="grid__item-titlewrap">
					<h2 class="grid__item-title">#Template5</h2>
					<p class="grid__item-description">When you get there</p>
				</div>
			</div>
			<div class="grid__item">
				<div class="scroll-img" style="background-image: url(resources/templateList/img/bg6_1.png);" data="resources/templateList/img/bg6_1.png"></div>
				<div class="grid__item-titlewrap">
					<h2 class="grid__item-title">#Template6</h2>
					<p class="grid__item-description">Timeless manners count</p>
				</div>
			</div>
			<div class="grid__item">
				<div class="scroll-img" style="background-image: url(resources/templateList/img/bg7_1.png);" data="resources/templateList/img/bg7_1.png"></div>
				<div class="grid__item-titlewrap">
					<h2 class="grid__item-title">#Template7</h2>
					<p class="grid__item-description">Together we can sit</p>
				</div>
			</div>
			<div class="grid__item">
				<div class="scroll-img" style="background-image: url(resources/templateList/img/bg8-1.png);" data="resources/templateList/img/bg8-1.png"></div>
				<div class="grid__item-titlewrap">
					<h2 class="grid__item-title">#Template8</h2>
					<p class="grid__item-description">The new kid on the block</p>
				</div>
			</div>
			<div class="grid__item">
				<div class="scroll-img" style="background-image: url(resources/templateList/img/bg9_1.png);" data="resources/templateList/img/bg9_1.png"></div>
				<div class="grid__item-titlewrap">
					<h2 class="grid__item-title">#Template9</h2>
					<p class="grid__item-description">The new kid on the block</p>
				</div>
			</div>
			<div class="grid__item">
				<div class="scroll-img" style="background-image: url(resources/templateList/img/bg10_1.png);" data="resources/templateList/img/bg10_1.png"></div>
				<div class="grid__item-titlewrap">
					<h2 class="grid__item-title">#Template10</h2>
					<p class="grid__item-description">Find me tomorrow or never</p>
				</div>
			</div>
		</div>
		
		<script src="<c:url value="resources/js/newMain_js/anime.min.js" />"></script>
		
		<script src="<c:url value="resources/templateList/js/imagesloaded.pkgd.min.js"/>"></script>
		<script src="<c:url value="resources/templateList/js/uncover.js"/>"></script>
		<script src="<c:url value="resources/templateList/js/demo3.js"/>"></script>
		
	</article><!-- article end -->
	<footer></footer><!-- footer end -->
	<!-- 
	<script src="resources/js/commonmain.js"></script>
	 -->
	<script src="<c:url value="/resources/js/newMain_js/menu/classie.js" />"></script>
	<script src="<c:url value="/resources/js/newMain_js/menu/borderMenu.js" />"></script>
	
</body>
</html>
