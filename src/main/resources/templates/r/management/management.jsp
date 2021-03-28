<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<!-- 정보승 : CSS 및 JS 공통부분 -->
	<%@ include file="/WEB-INF/views/common/headPart.jsp" %>
	<title>Swiper demo</title>

	<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">

	<!-- Live Search를 위한 소스 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
	<link rel="stylesheet" href="<c:url value="/resources/management/css/liveSearch.css"/>" />
	
	<!-- Management CSS -->
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/management/css/normalize.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/management/css/demo.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/management/css/style2.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/management/css/management-search.css" />" />
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css" integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">
	
	
	<!-- Management JS--> 
	<script src="<c:url value="/resources/management/js/modernizr-custom.js" />"></script>
	<!-- 여지원 : 명함 삭제 및 수정, 메시지 스크립트 -->
	<script src="<c:url value="/resources/management/js/yourCardList.js" />"></script>
	<script src="<c:url value="/resources/management/js/management-search.js" />"></script>
	<script src="<c:url value="/resources/management/js/liveSearch.js" />"></script>
	<script type="text/javascript">


		$(function() {

			yourCardInfoList();
		});

		var resultObj = ${jsonList};

		function yourCardInfoList() {

			$.ajaxSetup({  

				cache: false 
			});

	  		$('#search-text').keyup(function(){	// 뭐라도 키보드가 눌릴때 실행.

	  			$('#result').html('');
	  			$('#state').val('');
	  			
	  			var searchField = $('#search-text').val();
	  			var expression = new RegExp(searchField, "i");
	  			
	  			
		  		// 검색부분(리스트출력)		
		  		$.each(resultObj, function(key, value){

		  			if(searchField.length == 0 || searchField == "") {


		  			} else if ( value.name1.search(expression) != -1
		  				|| value.company.search(expression) != -1
		  				|| value.job.search(expression) != -1
//						|| value.phone.search(expression) != -1	
) {

		  				$('#result').append('<li class="list-group-item link-class" style="color:black;">'
		  					+ value.name1 
		  					+ ' | <span class="text-muted">'
		  					+ value.company 
		  					+ '</span>'
		  					+ ' | <span class="text-muted">'
		  					+ value.job 
		  					+ '</span>'
		  					+ ' | <span class="text-muted">'
		  					+ value.phone 
		  					+ '</span></li>'
		  					);
		  			}
		  		});   
		  	});
	  		
	  		$('#result').on('click', 'li', function() {
	  			
	  			var click_text = $(this).text().split('|');
	  			
	  			$('#search-text').val($.trim(click_text[0]));
	  			$("#result").html('');
	  		});
	  		
	  	}
		
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
	  <script type="text/javascript">
	  	$(function(){
	  		$('.grid__item').click(function(){
	  			$('#search-btn').css('display','none');	
	  		});
	  	});
	  	$(function(){
	  		$('.action--close').click(function(){
	  			$('#search-btn').css('display','block');	
	  		});
	  	});
	  </script>
	</head>
	<body>

		<!-- 정보승 : <header>부분 -->
			<%@ include file="/WEB-INF/views/common/bodyHeader.jsp" %>

			<!-- 이현호 : 검색부분 -->
			<i id="search-btn" class="fas fa-search fa-2x"></i>
			<div id="search-overlay" class="block">
				<div class="centered">
					<div id='search-box'>
						<i id="close-btn" class="fas fa-times fa-2x"></i>
						<form action='search' id='search-form' method='post' target='_top'>
							<div>
								<input id='search-text' name='q' placeholder='Search' type='text' />
								<button id='search-button' type='submit'>                     
									<span>Search</span>
								</button>
							</div>
							<ul class="list-group" id="result"></ul>
						</form>
					</div>
				</div>
			</div>
			<article class="container" style="margin-top: 75px;">
				<section class="ocr_page_go">
					<!-- 정보승 : 카드 등록 -->
					<!-- <a href="yourcard/insertYourCard">
						<img src="<c:url value="/resources/management/img/icon/yourCardPlus.png"/>" />
					</a> -->
				</section>
				<section class="content">
					<%-- 정보승 : 회원이 관리하는 카드 이미지 리스트 --%>
					<c:if test="${sessionScope.userid ne null}">

					<div class="grid">
						<!-- 수정 -->
						
						<!-- 정보승 : 카드 등록 -->
						<a class="grid__item" style="height: 211px" href="#" onclick="location.href='/www/yourcard/insertYourCard'">
							<img src="<c:url value="/resources/management/img/plus.png"/>">
						</a>
						<!-- 수정 -->

						<c:forEach var="yourCardList" items="${yourCardList }">

						<div class="grid__item" data-size="700x800">
							<%-- 정보승 : 명함 이미지 --%>
							<a href="<c:url value="yourcard/download?yourcardnum=${yourCardList.yourcardnum}" />" class="img-wrap">
							<%-- 정보승 : 썸네일 이미지 (Resourcese) --%>
							<c:choose>
								<c:when test="${yourCardList.sex eq 'M'.charAt(0)}">
								<%-- 정보승 : 남자에게 받은 명함 썸네일 --%>
									<img src="<c:url value="/resources/management/img/collage/man_bg.jpg" />" alt="man_bg" />
								</c:when>
								<c:otherwise>
									<%-- 정보승 : 여자에게 받은 명함 썸네일 --%>
									<img src="<c:url value="/resources/management/img/collage/woman_bg.jpg" />" alt="woman_bg" />
								</c:otherwise>	
							</c:choose>

				<%-- 정보승 : DB에서 불러온 명함의 상세 정보 --%>
				<div class="description description--grid">
					<input type="hidden" value="${yourCardList.yourcardnum}" />
					<h2 class="cardName1">${yourCardList.name1}</h2>
					<div class="details">
						<ul>
						
							<li><i class="far fa-building icon"></i><span>${yourCardList.company}</span></li>
							<li><i class="fas fa-phone icon"></i><span>${yourCardList.phone}</span></li>
							
							<c:if test="${yourCardList.name2 ne null}">
							<li><i class="far fa-user icon"></i><span>${yourCardList.name2}</span></li>
							</c:if>
							<c:if test="${yourCardList.name3 ne null}">
							<li><i class="far fa-user icon"></i><span>${yourCardList.name3}</span></li>
							</c:if>
							<c:if test="${yourCardList.email ne null}">
							<li><i class="fas fa-at icon"></i><span>${yourCardList.email}</span></li>
							</c:if>
							<c:if test="${yourCardList.department ne null}">
							<li><i class="fas fa-building icon"></i><span>${yourCardList.department}</span></li>
							</c:if>
							<c:if test="${yourCardList.job ne null}">
							<li><i class="fas fa-user-md icon"></i><span>${yourCardList.job}</span></li>
							</c:if>
							<c:if test="${yourCardList.tel ne null}">
							<li><i class="fas fa-phone-square icon"></i><span>${yourCardList.tel}</span></li>
							</c:if>
							<c:if test="${yourCardList.fax ne null}">
							<li><i class="fas fa-fax icon"></i><span>${yourCardList.fax}</span></li>
							</c:if>
							<c:if test="${yourCardList.address ne null}">
							<li><i class="fas fa-map-marker icon"></i><span>${yourCardList.address}</span></li>
							</c:if>
							<c:if test="${yourCardList.memo ne null}">
							<li><i class="far fa-comment-alt icon"></i><span>${yourCardList.memo}</span></li>
							</c:if>
							<c:if test="${yourCardList.otherinfo ne null}">
							<li><i class="fas fa-info-circle icon"></i><span>${yourCardList.otherinfo}</span></li>
							</c:if>
							
						</ul>
					</div>
					<div>
						<button class="button" type="button" onclick="cardRemove('${yourCardList.yourcardnum}')"> 삭제 </button>
						<button class="button" type="button" onclick="cardUpdate('${yourCardList.yourcardnum}')"> 수정 </button>
						<button class="button" type="button" onclick="cardInfo('${yourCardList.mycardnum}')"> 교환한 명함 </button>
					</div>

				</div>
				<div class="black">
					<span>${yourCardList.name1}</span>
				</div>
				
			</a>
			<!-- <script type="text/javascript">
				$('.grid__item').hover(function(){
		  			$( this ).append("<h3 class='namename'>${yourCardList.name1}</h3>");
		  		});
			</script> -->
			<%-- <h3 class="namename">${yourCardList.name1}</h3> --%>
		</div>
	</c:forEach>
</div>	   
<!-- /grid -->

<%-- 정보승 : 명함 상세정보(라이트박스)의 [닫기] 버튼 --%>
<div class="preview">
	<button class="action action--close">
		<i class="fa fa-times"></i>
		<span class="text-hidden">Close</span>
	</button>
	<div class="description description--preview"></div>
</div>
<!-- /preview --> 	

</c:if>
</section>
<!-- /content -->

</article>
<!-- /container -->


<script src="<c:url value="/resources/management/js/imagesloaded.pkgd.min.js" />"></script>
<script src="<c:url value="/resources/management/js/masonry.pkgd.min.js" />"></script>
<script src="<c:url value="/resources/management/js/classie.js" />"></script>
<script src="<c:url value="/resources/management/js/main.js" />"></script>

<script>
	(function() {
		var support = { transitions: Modernizr.csstransitions },
			// transition end event name
			transEndEventNames = { 'WebkitTransition': 'webkitTransitionEnd', 'MozTransition': 'transitionend', 'OTransition': 'oTransitionEnd', 'msTransition': 'MSTransitionEnd', 'transition': 'transitionend' },
			transEndEventName = transEndEventNames[ Modernizr.prefixed( 'transition' ) ],
			onEndTransition = function( el, callback ) {
				var onEndCallbackFn = function( ev ) {
					if( support.transitions ) {
						if( ev.target != this ) return;
						this.removeEventListener( transEndEventName, onEndCallbackFn );
					}
					if( callback && typeof callback === 'function' ) { callback.call(this); }
				};
				if( support.transitions ) {
					el.addEventListener( transEndEventName, onEndCallbackFn );
				}
				else {
					onEndCallbackFn();
				}
			};

			new GridFx(document.querySelector('.grid'), {
				imgPosition : {
					x : -0.5,
					y : 1
				},
				onOpenItem : function(instance, item) {
					instance.items.forEach(function(el) {
						if(item != el) {
							var delay = Math.floor(Math.random() * 50);
							el.style.WebkitTransition = 'opacity .5s ' + delay + 'ms cubic-bezier(.7,0,.3,1), -webkit-transform .5s ' + delay + 'ms cubic-bezier(.7,0,.3,1)';
							el.style.transition = 'opacity .5s ' + delay + 'ms cubic-bezier(.7,0,.3,1), transform .5s ' + delay + 'ms cubic-bezier(.7,0,.3,1)';
							el.style.WebkitTransform = 'scale3d(0.1,0.1,1)';
							el.style.transform = 'scale3d(0.1,0.1,1)';
							el.style.opacity = 0;
						}
					});
				},
				onCloseItem : function(instance, item) {
					instance.items.forEach(function(el) {
						if(item != el) {
							el.style.WebkitTransition = 'opacity .4s, -webkit-transform .4s';
							el.style.transition = 'opacity .4s, transform .4s';
							el.style.WebkitTransform = 'scale3d(1,1,1)';
							el.style.transform = 'scale3d(1,1,1)';
							el.style.opacity = 1;

							onEndTransition(el, function() {
								el.style.transition = 'none';
								el.style.WebkitTransform = 'none';
							});
						}
					});
				}
			});
		})();
	</script>
	
	<footer></footer><!-- footer end -->
	
	 <!-- 
	<script type="text/javascript" src="<c:url value="/resources/js/anime.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/main.js"/>"></script>
-->
<script src="<c:url value="/resources/js/newMain_js/menu/classie.js" />"> </script>
<script src="<c:url value="/resources/js/newMain_js/menu/borderMenu.js" />"></script>


</body>
</html>
