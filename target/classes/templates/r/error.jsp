<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%-- 
<%@ include file="/WEB-INF/views/common/header.jsp" %>
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="3; url=/">

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/error/error.css"/>">

<script language="javascript">



      function window_onload(){

         setTimeout('go_url()',2000)  // 5초후 go_url() 함수를 호출한다.

      }

 

      function go_url(){

         location.href="/www/home";  // 페이지 이동...

      }

 

  </script>
<title>오류</title>
</head>
<body>
<body onload="javascript:window_onload()">

<div class="error_div">
<img class="error" src="<c:url value="/resources/img/error_page.png"/>"/>
</div>

</body>
</html>