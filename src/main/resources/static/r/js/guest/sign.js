/**
 * 로그인 및 회원가입 및 비번 찾기
 */

// move input label function
$(function() {

	$('.input-container').find('input').on('keyup blur focus', function(e) {
		var $this = $(this), label = $this.next('label');
		if (e.type == 'blur') {
			if ($this.val() === '') {
				label.removeClass('active');
			}
		} else if (e.type === 'focus') {
			if ($this.val() === '') {
				label.addClass("active");
			}
		}
	});

	// Toggle Function
	$('.toggle').click(function() {
        // Switches the Icon
        $(this).children('i').toggleClass('fa-pencil');
        $('.tooltip').text(
                ($('.tooltip').text() === 'Sign up') ? 'Login'
                        : 'Sign up');
        // Switches the forms
        $('.login-register').animate({
            height : "toggle",
            'padding-top' : 'toggle',
            'padding-bottom' : 'toggle',
            opacity : "toggle"
        }, "slow");
    });

	$('.footer a').click(function() {
		// Switches the forms
		$('.card-container').children('.toggle').toggle();
		$('.login-reset').animate({
			height : "toggle",
			'padding-top' : 'toggle',
			'padding-bottom' : 'toggle',
			opacity : "toggle"
		}, "slow");
	});

	$("#loginButton").on("click",function(){

        var loginForm = $("#loginForm");
        var userId = $("#userid").val();
        var pw = $("#userpw").val();


        if (userId.length == 0) {
            alert("이메일을 입력해주세요");
            return false;
        }

        if (pw.length == 0) {
            alert('비밀번호 입력해주세요');
            return false;

        }

        return true;

        $(loginForm).submit();
    });

    $("#joinButton").on("click",function(){

        var emailReg = /[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+/;

        var joinForm = $("#joinForm");
        var username = $("#name").val();
        var email = $("#id").val();
        var password = $("#pw").val();

        if (username.length == 0) {
            alert("이름을 입력해주세요");
            return false;
        }

        if (email.length == 0) {
            alert('이메일을 입력해주세요');
            return false;
        }

        if (!(emailReg.test(email))) {
            alert("XXX@XXX.XXX 양식으로 작성해주세요");
            return false;
        }

        if (password.length == 0 ) {
            alert("비밀번호를 입력해주세요");
            return false;
        }

        return true;
        joinForm.submit();

    });

    function go_url(){
        location.href="/www";  // 페이지 이동...
     }
});