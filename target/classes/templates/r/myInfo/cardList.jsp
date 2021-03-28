<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- cardList_ -->
<html>
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
	<title>Animated Border Menus | Demo 5</title>
	<meta name="description" content="Responsive Animated Border Menus with CSS Transitions" />
	<meta name="keywords" content="navigation, menu, responsive, border, overlay, css transition" />
	<meta name="author" content="Codrops" />
	<link href="https://fonts.googleapis.com/css?family=Inconsolata:400,700|Poppins:700" rel="stylesheet">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css" integrity="sha384-v2Tw72dyUXeU3y4aM2Y0tBJQkGfplr39mxZqlTBDUZAb9BGoC40+rdFCG0m10lXk" crossorigin="anonymous">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/fontawesome.css" integrity="sha384-q3jl8XQu1OpdLgGFvNRnPdj5VIlCvgsDQTQB6owSOHWlAurxul7f+JpUOVdAiJ5P" crossorigin="anonymous">
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/newMain_css/common.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/newMain_css/menu/normalize.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/newMain_css/menu/menu.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/newMain_css/menu/icons.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/newMain_css/menu/menustyle.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/myinfo/myCardList/css/cardlist.css"/>" />
	
	<script src="<c:url value="/resources/js/newMain_js/menu/modernizr.custom.js"/> "> </script>
	<script src="<c:url value="/resources/js/jquery-3.3.1.min.js"/> "> </script>
	<script>document.documentElement.className = 'js';</script>
	<script>
	
		history.pushState(null, null, location.href);
		
	 	window.onpopstate = function(event) {
	
	 		history.go(1);
	 	};

		$(function() {
			
			$("#mycard_grid").addClass("grid__item--c1", "grid__item--c2");
		});
	
		  $(function(){
			  var count = 0;
		    $('.bt-menu-trigger').click(function(){
		      if (count == 0) {
				$('#bt-menu').css('z-index','10');
			}else if (count == 1) {
				$('#bt-menu').css('z-index','0');
			}
		    });
		  });
	</script>
</head>
<div class="container">

	<%@ include file="/WEB-INF/views/common/bodyHeader.jsp" %>
	
	<article>
		
		<section class="content">
		
			<div class="grid grid--effect-vega">
			<c:forEach items="${myCardList}" var="mycard">
				<a href="javascript:location.href='mycard/myCardOneInfo?mycardnum=${mycard.mycardnum}' " class="grid__item grid__item--c1" id="mycard_grid" >
					<div class="stack">
						<div class="stack__deco"></div>
						<div class="stack__deco"></div>
						<div class="stack__deco"></div>
						<div class="stack__deco"></div>
						<div class="stack__figure">
								<img class="stack__img" src="mycard/download?mycardnum=${mycard.mycardnum }" alt="Image"/>
						</div>
					</div>
					<div class="grid__item-caption">
						<h3 class="grid__item-title">anaerobic</h3>
						<div class="column column--left">
							<span class="column__text">
							<c:if test="${mycard.name1 ne null}">
								Name
							</c:if>
							<c:if test="${mycard.name1 eq null}">
								<br>
							</c:if>
							</span>
							<span class="column__text">
							<c:if test="${mycard.company ne null}">
								Company
							</c:if>
							<c:if test="${mycard.company eq null}">
								<br>
							</c:if>
							</span>
							<span class="column__text">
							<c:if test="${mycard.phone ne null}">
								Phone
							</c:if>
							<c:if test="${mycard.phone eq null}">
								<br><br>
							</c:if>
							</span>
						</div>
						<div class="column column--right">
							<span class="column__text">${mycard.name1} </span>
							<span class="column__text">${mycard.company} </span>
							<span class="column__text">${mycard.phone} </span>
						</div>
					</div>
				</a>
			</c:forEach>
			</div>
			
		</section>
		
		<script src="<c:url value="/resources/myinfo/myCardList/js/anime.min.js" />"></script>
		<script src="<c:url value="/resources/myinfo/myCardList/js/main.js" />" ></script>
		<script>
			(function() {
				[].slice.call(document.querySelectorAll('.grid--effect-vega > .grid__item')).forEach(function(stackEl) {
					new VegaFx(stackEl);
				});
			})();
		</script>
		
	</article><!-- article end -->
</div><!-- /container -->
<script src="<c:url value="/resources/js/newMain_js/menu/classie.js" />"></script>
<script src="<c:url value="/resources/js/newMain_js/menu/borderMenu.js"/>"></script>
</body>
</html>
