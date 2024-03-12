jQuery(function() {
    const emailForm = $('.user_password');

    $.validator.addMethod("regex", function(value, element, regex){
        var regExp = new RegExp(regex);
        return regExp.test(value);
    });

    emailForm.validate({
        debug:true,

        rules: {                    // 유효성 검사 규칙
            email: {                // 이메일 필드
                required: true,     // 필수 입력
                regex: /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/
            }
        },
        messages: {                 // 오류값 발생시 출력할 메시지 수동 지정
            email: {
                required: '필수 입력 항목입니다.',
                regex : '올바른 이메일 형식으로 입력하세요.'
            }
        },
        errorPlacement: function(error, element) {
            if (element.attr("name") == "email") {
                error.addClass('mt-2');
                error.addClass('mx-2');
                error.insertAfter(element);
            } else {
                error.insertAfter(element);
            }
        }
    });

    $('#inputEmail').on('keyup change', function() {
        if (emailForm.valid()) { // 유효성 검사 통과 여부 확인
            $('#resetPwdBtn').removeClass('disabled');
        } else {
            $('#resetPwdBtn').addClass('disabled');
        }
    });
});