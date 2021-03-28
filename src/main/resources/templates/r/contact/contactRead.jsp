<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html >
<!-- 정보승 : CSS 및 JS 공통부분 -->
<%@ include file="/WEB-INF/views/common/headPart.jsp" %>
<html lang="ko">
<head>
<title>contactRead</title>
<!-- <link rel="stylesheet" type="text/css" href="<c:url value="resources/contact/contactRead.css" />"/> 
    <script type="text/javascript" src=" <c:url value="/resources/js/jquery-3.2.1.js" />" > </script>  -->
   <script type="text/javascript">
   
   /* 지원   : 수정하기 &삭제하기 버튼을 눌렀을때 ajax발동시키기 */
   $(function(){
	   commentList();
      
      /* 게시글을 처음 가져올 때 공지글여부를 체크하여 미리 체크한다 */
      <c:if test="${board.check_notice =='Y'}">
            $("#ckbox").attr("checked", true);
      </c:if>
      
      $('input').on('click',function(){
         $(this).each(function(){
            /* 수정하기버튼을 클리했을 때*/
            if( $(this).is('#fix')){
               
               /* readonly를 없애준다 */
               $('#board_title').removeAttr("readonly");
               $('#board_content').removeAttr("readonly");
               $('#ckbox').removeAttr("onclick");
               /* 클릭하면 '수정하기'버튼의 value값이 '수정완료'로 바뀐다. */
               $(this).attr("value","수정 완료").attr("onclick","alarmFixcomplete()"); 
               
               return false;
            };
         });
      
      });
      
      $('[name = insertBTN]').click(function(){ //댓글 등록 버튼 클릭시 

   	    var insertData = $('#commentInsertForm').serialize(); //commentInsertForm의 내용을 가져옴
   	  	 insertComment(insertData); //Insert 함수호출(아래)
   	  	
   	});
   });
   
   /* 수정완료 버튼을 누르면 form이 submit이 된다. */
   function alarmFixcomplete(){
      
      //수정완료 버튼을 누르고 공지글이 체크되어 있으면 값을 'Y'로 넘긴다.
      if($("#ckbox").is(":checked")){
         $("#ckbox").attr("value", "Y");
      }
      $('#formID').submit();
      
      return false;
   }
   
   function deleteBoard(boardnum){
	   $.ajax({
		  url: 'deleteBoard'
		  ,type: 'post'
		  ,data :{'boardnum' : boardnum}
	   	  ,success : function (result){
	   		  if(result==1){
	   			  alert("삭제했습니다.");
	   			window.location.reload();
	   		  }
	   	  }
	   });
   }
   
   



   //댓글 등록하기
   function insertComment(insertData){
	   
	    $.ajax({
	        url : 'insertComment'
	        ,type : 'post'
	        ,data : insertData
	        ,success : function(result){
	            if(result == 1) {
	                 //댓글 작성 후 댓글 목록 reload
	                $('#com_content').val('');
	                commentList();
	            };
	        }
	    });
	}

   //댓글 리스트 출력
   function commentList(){
	   var boardnum = $('#boardnum').val();
	    $.ajax({
	        url : 'commentList',
	        type : 'get',
	        data : {'boardnum': boardnum},
	        success : function(data){
	            var str =''; 
	            var list = data.list;// 해쉬맵에 댓글리스트가 담겨있다.
	            var id = data.loginid;//session에 담겨있던 loginid 값 (string)
	        
	            $.each(list, function(key, value){ 
	            	var writer = (value.com_writer).substring(0,4);
                    var showWriter = writer+"***";
	            	str += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
	            	str += '<div class="commentInfo'+value.commentnum+'">'+'_작성자 : '+showWriter;
	            	
                   
	            	if(id == value.com_writer){
	            		str += '<a onclick="commentUpdate('+value.commentnum+',   \'   '+value.com_content+'   \'  )"> [수정] </a>';
	            		str += '<a onclick="deleteComment('+value.commentnum+');"> [삭제] </a> </div>';
	            	} 
	            	
	            	
	            	
	            	str += '<div class="commentContent'+value.commentnum+'"> <p> 내용 : '+value.com_content +'</p>';
	            	str += '</div></div>';
	            });
	            
	            $(".commentList").html(str);
	        }
	    });
	}

   
   
 //댓글 수정 - 댓글 내용 출력을 input 폼으로 변경 
   function commentUpdate(commentnum, com_content){
       var str ='';
       
       str += '<div class="input-group">';
       str += '<input type="text" class="form-control" name="content_'+commentnum+'" value="'+com_content+'"/>';
       str += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="commentUpdateProc('+commentnum+');">[수정]</button> </span>';
       str += '</div>';
       
       $('.commentContent'+commentnum).html(str);
       
       
   }
 
 //댓글 수정
   function commentUpdateProc(commentnum){
       var updateContent = $('[name=content_'+commentnum+']').val();
       $.ajax({
           url : 'updateComment',
           type : 'post',
           data : {'com_content' : updateContent, 'commentnum' : commentnum},
           success : function(result){
               if(result == 1) commentList(); //댓글 수정후 목록 출력 
           }
       });
   }
 
 //댓글 삭제 
   function deleteComment(commentnum){
	var bool = confirm('삭제하시겠습니까?');
		if(!bool){
			return false;
		}
       $.ajax({
           url : 'deleteComment'
           ,data :{ 'commentnum' : commentnum}
           ,type : 'post'
           ,success : function(result){
               if(result == 1)  commentList(); //댓글 삭제후 목록 출력 
           }
       });
   }




   </script>
   <style>
   .div_board{
      margin-left: 100px;
          margin-right: 900px;
         font-size: large;
         font-style: inherit;
   }
   
   table {border-collapse: collapse; background-color: #ccddff;}
         table, th, td {border: 1px solid black; border-color: #4d88ff; padding: 5px;}
   </style>
</head>

<body class="background">
      <!-- 정현수 : <header>부분 -->
      <%@ include file="/WEB-INF/views/common/bodyHeader.jsp" %>
      
      <article>
         <!-- 여기가 작업 공간 -->
      <div class="div_outline" > <!-- contactMain 작업 영역 -->
      <div class="div_header"> <!-- header 영역 --> </div>
         
            <!-- 게시글 영역 -->
            <div class="div_board" >   
   <div class="container">
    <div class="col-xs-12" style="margin:15px auto;">
        <label style="font-size:20px;"><span class="glyphicon glyphicon-list-alt"></span>게시글 상세</label>
    </div>

            
            <!-- 게시글 form태그 시작 -->
            <form action="updateBoard" id="formID" method="post">
               <input type="hidden" name="boardnum" id="boardnum" value="${board.boardnum}">
               <table>
                  <tr>
                     <th>글쓴이</th>
                     <td>
                      <input type="text" name="board_writer" id="board_writer"  readonly="readonly" value="${board.board_writer}"> 
                     </td>
                  </tr>
                  <tr >
                     <th>제목</th>
                     <td>
                      <input type="text" name="board_title" id="board_title" readonly="readonly" value="${board.board_title}"> 
                     </td>
                  </tr>
                  <tr>
                     <th>내용</th>
                     <td>
                      <textarea type="text" cols="40" rows="10" name="board_content"  id="board_content"  readonly="readonly"  >${board.board_content}</textarea> 
                     </td>
                  </tr>
                  <tr>
                     <th>공지글</th>
                     <td>
                        <!-- onclick 속성은 처음 페이지 들어왔을 때 readonly만 하는 속성 -->
                        <input type="checkbox" name="check_notice" id="ckbox"  onclick="return false;">
                     </td>
                  </tr>
               </table>
               
               <c:if test= "${sessionScope.username == board.board_writer}" >
                   <div class="div_write"><br>
                     <input type="button" value="수정하기" id="fix" >
                     <input type="button" value="삭제하기" onclick="deleteBoard(${board.boardnum});">
                  </div>   
               </c:if>   
            </form>   
            </div>
            <br><br>
                <!--  댓글  -->
             <div class="container">
                    <h2>comment</h2>
                 	<br><br>
                   <form id="commentInsertForm">
                        <div class="input-group">
                           <input type="hidden" name="boardnum" value="${board.boardnum}"/>
                           <input type="hidden" name="com_writer" value="${sessionScope.userid}">
                           <input type="text" id="com_content" name="com_content" size="30" placeholder="내용을 입력하세요.">
                           <button name="insertBTN">등록</button> 
                           <br><br>
                       </div>
                 </form>
                </div>
             <div class="commentList"></div>

</div>
 

               
                        
      </article><!-- article end -->
      <footer></footer><!-- footer end -->
      <!-- 
	<script type="text/javascript" src="<c:url value="/resources/js/anime.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/main.js"/>"></script>
	 -->
	<script src="<c:url value="/resources/js/newMain_js/menu/classie.js" />"> </script>
	<script src="<c:url value="/resources/js/newMain_js/menu/borderMenu.js" />"></script>
      
</body>
</html>