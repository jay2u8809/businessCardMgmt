/**
 * Login and Register, reset password
 */
/*
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
});
*/

const REGISTER_MEMBER = {
    init: function() {
        let self = this;

        // login btn
        $('#loginBtn').on('click', function() {
            self.loginProcess()
        });
        // register btn
        $('#registerBtn').on('click', function() {
            self.register()
        });

        if (registerResult != null && registerResult == false) {
            alert("Fail to Register member");
        }
    },
    register: () => {

        let validationUrl = "/api/v1/member/register/check/";

        let form = $('#registerForm');

        let memberName = $('#registerMemberName').val();
        $('input[name="memberName1"]').remove();
        $('<input>').attr({
            'type': 'hidden',
            'name': 'memberName1',
            'value': memberName
        }).appendTo(form);

        let formData = $('#registerForm').serialize();

        $.ajax({
            url: validationUrl,
            type: 'POST',
            contentType: 'application/x-www-form-urlencoded; charset=utf-8;',
            data: formData,
            beforeSend: () => {

            }, success: (result) => {
                let token = $("meta[name='_csrf']").attr("content") || null;
                $('<form/>', {
                    action: result.nextUrl,
                    method: 'post'
                }).append($('<input/>', {
                    type:'hidden',
                    name:'_csrf',
                    value: token
                })).appendTo(document.body)
                .submit();
            }, error: (err) => {

            }, complete : (data) => {

            }
        }).done((result) => {

        }).fail((jqXHR, textStatus, errorThrown) => {

        }).always((data) => {

        });

    },
    loginProcess: () => {

    }
};

REGISTER_MEMBER.init();