<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- sign -->
<html lang="en" class="no-js">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
		<meta name="viewport" content="width=device-width, initial-scale=1"> 
		<title>Fullscreen Form Interface</title>
		<meta name="description" content="Fullscreen Form Interface: A distraction-free form concept with fancy animations" />
		<meta name="keywords" content="fullscreen form, css animations, distraction-free, web design" />
		<meta name="author" content="Codrops" />
		<!-- 
		<link rel="shortcut icon" href="../favicon.ico">
		 -->
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/sign/css/normalize.css " />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/sign/css/demo.css " />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/sign/css/component.css " />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/sign/css/cs-select.css"  />" />
		<link rel="stylesheet" type="text/css" href="<c:url value="/resources/sign/css/cs-skin-boxes.css " />" />
		<script src="<c:url value="/resources/sign/js/modernizr.custom.js " />"></script>
	</head>
	<body>
		<div class="container">

			<div class="fs-form-wrap" id="fs-form-wrap">
				<div class="fs-title">
					<h1>Join us</h1>
				</div>
				<form id="myform" class="fs-form fs-form-full" autocomplete="off" action="join" method="post">
					<ol class="fs-fields">
						<li>
							<label class="fs-field-label fs-anim-upper" for="q1">What's your name?</label>
							<input class="fs-anim-lower" id="username" name="username" type="text" placeholder="이현호" required/>
						</li>
						<li>
							<label class="fs-field-label fs-anim-upper" for="q2" data-info="We won't send you spam, we promise...">What's your email address?(This your ID)</label>
							<input class="fs-anim-lower" id="userid" name="userid" type="email" placeholder="hyeonho@arias.com" required/>
						</li>
						<li>
							<label class="fs-field-label fs-anim-upper" for="q1">What's your password?</label>
							<input class="fs-anim-lower" id="userpw" name="userpw" type="password" placeholder="password" required/>
						</li>
						<li>
							<label class="fs-field-label fs-anim-upper" for="q1">Again What's your password?</label>
							<input class="fs-anim-lower" id="userpw2" name="" type="password" placeholder="password" required/>
						</li>
					</ol><!-- /fs-fields -->
					<button class="fs-submit" type="submit">Join Us(Please check your e-mail)</button>
				</form><!-- /fs-form -->
			</div><!-- /fs-form-wrap -->
		</div><!-- /container -->
		<script src="<c:url value="/resources/sign/js/classie.js " />"></script>
		<script src="<c:url value="/resources/sign/js/selectFx.js " />"></script>
		<script src="<c:url value="/resources/sign/js/fullscreenForm.js " />"></script>
		<script>
			(function() {
				var formWrap = document.getElementById( 'fs-form-wrap' );

				[].slice.call( document.querySelectorAll( 'select.cs-select' ) ).forEach( function(el) {	
					new SelectFx( el, {
						stickyPlaceholder: false,
						onChange: function(val){
							document.querySelector('span.cs-placeholder').style.backgroundColor = val;
						}
					});
				} );

				new FForm( formWrap, {
					onReview : function() {
						classie.add( document.body, 'overview' ); // for demo purposes only
					}
				} );
			})();
		</script>
	</body>
</html>