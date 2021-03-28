$(function(){
    //move input label function
$('.input-container').find('input').on('keyup blur focus', function (e) { 
  var $this = $(this),
  label = $this.next('label');
  if(e.type == 'blur') {
    if($this.val() === '' ) {
      label.removeClass('active'); 
  }  
  } else if (e.type === 'focus') {
    if( $this.val() === '' ) {
      label.addClass("active");
    } 
  }
});

//Toggle Function
$('.toggle').click(
		function() {
			// Switches the Icon
			$(this).children('i').toggleClass('fa-pencil');
			$('.tooltip').text(
					($('.tooltip').text() === 'Info') ? 'Update'
							: 'Info');
			// Switches the forms
			$('.login-register').animate({
				height : "toggle",
				'padding-top' : 'toggle',
				'padding-bottom' : 'toggle',
				opacity : "toggle"
			}, "slow");
		});


  });



	/*
	 * @comment	:	마이카드 삭제
	 * @author	:	여지원
	 */
	function deleteCard(mycardnum){
	
		if(confirm("명함을 삭제하시겠습니까?")){
			   
			location.href = 'deleteMyCard?mycardnum='+mycardnum;
		} 
		else {
			   
			return false;
		}
	
	}
	
	
	
	/*
	 * @comment	:	대표 명함 설정 메소드
	 * @author	:	여지원
	 */
	function insertProfileImage(mycardnum){
		if(confirm("대표 명함으로 변경하시겠습니까?")){
			location.href = 'insertProfileImage?mycardnum='+mycardnum;
		}
		else {
			return false;
		}
		
	}