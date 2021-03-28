function myFunction() {
  var element1 = document.getElementById("menu");
  var element2 = document.getElementById("main");
  element1.classList.toggle("toggle");
  element2.classList.toggle("toggle2");
    //$('.main').css('transition','left 1s ease-in-out');
  }


/*function myFunction2() {
    var element1 = document.getElementById("menu2");
    var element2 = document.getElementById("main");
    element1.classList.toggle("toggle3");
    element2.classList.toggle("toggle4");
    //$('.main').css('transition','right 1s ease-in-out');
    alert("왜 안된??");
  }*/


	$(function(){
		var count = 0;

		$('#target').click(function() { 
			if (count == 0) {
				
				$('.menu2').css('right','0');
				count++;
			} else if (count == 1) {
				
				$('.menu2').css('right','320');
				count--;
			}
		});
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