<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- my_card_text_ -->

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
	<script type="text/javascript" src="<c:url value="/resources/mycardtextlist/js/my_card_text_list.js" />"></script>
	<script type="text/javascript">
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
<body>

	<!-- 정보승 : <header>부분 -->
	<%@ include file="/WEB-INF/views/common/bodyHeader.jsp" %>
	
	<article>
			<ul>
				<li>
					<div class="img_box">
						<img src="<c:url value="javascript:location.href='mycard/myCardOneInfo?mycardnum=${mycard.mycardnum}" />">
					</div>
				</li>
				<LI>
					<div class="card-container">
						<div class="toggle"><i class="fa fa-user fa-pencil fa-lg"></i></div>
						<div class="card login-register login-reset">
							<h1 class="title">Card info</h1>
							<form> 
								<div class = "input-container has-feedback">
									<input type="text" id="name1" name="name1" required />
									<label for="">Name</label>
									<div class="check"></div>
									<div class="bar"></div>
								</div>
								<div class = "input-container has-feedback">
									<input type="type" id="phone" name="phone" required />
									<label for="">Phone</label>
									<div class="check"></div>
									<div class="bar"></div>
								</div>
								<div class = "input-container has-feedback">
									<input type="email" id="email" name="email"  />
									<label for="">E-mail</label>
									<div class="check"></div>
									<div class="bar"></div>
								</div>
								<div class = "input-container has-feedback">
									<input type="type" id="department" name="department"  />
									<label for="">department</label>
									<div class="check"></div>
									<div class="bar"></div>
								</div>
								<div class="button-container">
									<button class="rkmd-btn btn-lightBlue ripple-effect float-right"><span>send information</span></button>
								</div>
							</form>
						</div>
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
