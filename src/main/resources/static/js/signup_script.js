$(document).ready(function(){
    requireFourLengthCode();
});

function requireFourLengthCode(){
    const codeInput = document.getElementById('emailCode');

    // 입력 필드에 이벤트 리스너 추가
    codeInput.addEventListener('input', function() {
        // 입력된 값이 4자리 숫자인지 확인
        if (this.value.length === 4 && /^\d+$/.test(this.value)) {
            $('#disabledBtn2').css('display','none');
            $('#continueBtn2').css('display','block');
        } else {
            $('#disabledBtn2').css('display','block');
            $('#continueBtn2').css('display','none');
        }
    });
}

function sendEmailCode(){
    $.ajax({
        type: "POST",
        url: "/api/signup/send-email-auth",
        data: {
            inputEmail: $('#inputEmail').val()
        },
        success: function(response) {
            // 요청이 성공한 경우
            console.log(response); // 성공 메시지 출력
            showCodeInput();
        },
        error: function(xhr, status, error) {
            // 요청이 실패한 경우
            var errorMessage = xhr.responseJSON.message;
            console.error(errorMessage); // 실패 메시지 출력
        }
    });
}


function showCodeInput(){
    $('.user_email').css('display','none');
    $('.user_email_validate').css('display','block');

    var inputEmail = $('#inputEmail').val();
    $('#email').val(inputEmail);
}

function backEmailForm(){
    $('.user_email').css('display','block');
    $('.user_email_validate').css('display','none');

    $('#email').val('');
    $('#emailCode').val('');
}


jQuery(function() {
    const emailForm = $('.user_email');

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
        },
        submitHandler:function(emailForm){
            alert("yes");
        }
    });

    $('#inputEmail').on('keyup change', function() {
        if (emailForm.valid()) { // 유효성 검사 통과 여부 확인
            var continueBtnHtml=
                `<a onclick="sendEmailCode();" id="continueBtn" class="btn btn-primary btn-user btn-block">
                    계속하기
                </a>`;
            $("#continueBtnDiv_1").html(continueBtnHtml);
        }
    });
});
