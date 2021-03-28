$(document).ready(function() {
   var cnt=0;
   var cnt1=0;
   var cnt2=0;
   $('p.template').click(function(){
       cnt++;
       if(cnt%2==1){
         $(this).next("div.menuitems").slideDown(500);
           //next 는 자기 다음에 나오는 같은 계층의 태그를 잡음
         $(this).siblings().next("div.menuitems").slideUp("slow"); //siblings()-자신을 제외한 형제 노드
         cnt1=cnt2=0;
       }else{
         $(this).next("div.menuitems").slideUp("slow");
       }
    });

   $('p.logo').click(function(){
       cnt1++;
       if(cnt1%2==1){
         $(this).next("div.menuitems").slideDown(500);
         $(this).siblings().next("div.menuitems").slideUp("slow");
          cnt=cnt2=0;
       }else{
          $(this).next("div.menuitems").slideUp("slow");
       }
    });

    $('p.text').click(function(){
       cnt2++;
       if(cnt2%2==1){
         $(this).next("div.menuitems").slideDown(500);
         $(this).siblings().next("div.menuitems").slideUp("slow");
         cnt=cnt1=0;
       }else{
         $(this).next("div.menuitems").slideUp("slow");
       }
    });

     $('p.menus').hover(function(){
         /*$(this).css({'background-image':"url(img/down.png)", 'background-repeat':"no-repeat", 
               'background-position':"right"});*/
         $(this).siblings().css({'background-image':"none"});
     });
         //탭키처리
         $('.menus a').bind('focus', function () {        
                   $(this).parent('.menus').next().slideDown('fast');
        });

       $('.menuitems').find('li:last a,this').bind('blur', function () { 	      
                  $('.menuitems').slideUp('fast');
       });

  });