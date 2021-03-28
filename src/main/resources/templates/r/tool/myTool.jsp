<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>myTool</title>
   
    <script type="text/javascript" src="<c:url value=" resources/tool/js/jquery-3.3.1.min.js" />"></script>
    	<script type="text/javascript" src=" <c:url value="/resources/js/jquery-3.2.1.js" />" > </script>
    <script src ="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
 
	<script>
    
   $(function(){
	
	   var list = $("#list").attr("value");
		console.log(list);
		alert(list);		
		
		var str = "";
		
		$.each(list,function(index,item){
			str += '<img id="'+index+'" src='/resources/'+item.frontimgsaved+'>';
		});
		
		$(".output").append(str);	
		
   });//레디 함수

  
   
    </script>
</head>
<body>
	<input type="hidden" id="list" name="list" value="${list}">
		
	<div class="output"></div>
</body>
</html>
