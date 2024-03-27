 function signup(){
    console.log("회원가입 진행");
}

function checkNickName(){
    if(false){
        var validContent = $('<div class="valid-feedback ml-1" style="display:block; width: 100%;margin-top: .25rem;font-size: 80%;color: #f00;"><i class="bi bi-exclamation-circle"></i> 중복된 닉네임입니다.</div>');
        validContent.insertAfter($('#nicknameInputDiv'));
    }else {
        var validContent = $('<div class="valid-feedback ml-1" style="display:block; width: 100%;margin-top: .25rem;font-size: 80%;color: #1cc88a;"><i class="bi bi-check-circle"></i> 사용가능한 닉네임입니다.</div>');
        validContent.insertAfter($('#nicknameInputDiv'));
    }

    alert("먼저 닉네임 규격을 맞춰주세요.");

}

jQuery(function() {
    const userForm = $('.user_info');

    $.validator.addMethod("regex", function(value, element, regex){
        var regExp = new RegExp(regex);
        return regExp.test(value);
    });

    $.validator.addMethod("over14", function(value, element) {
        var birthDate = new Date(value.substring(0, 4), parseInt(value.substring(4, 6)) - 1, value.substring(6, 8));
        var currentDate = new Date();
        // 14년 후의 날짜를 계산합니다.
        var minDate = new Date(birthDate.getFullYear() + 14, birthDate.getMonth(), birthDate.getDate());
        return minDate <= currentDate;
    });

    $.validator.addMethod("noSpaceOrSpecialChars", function(value, element) {
        // 닉네임에 띄어쓰기나 특수문자가 있는지 확인합니다.
        return /^[a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣]+$/i.test(value);
    });

    $.validator.addMethod("nicknameLength", function(value, element) {
        // 닉네임의 길이가 2글자 이상 10글자 이하인지 확인합니다.
        return value.length >= 2 && value.length <= 10;
    });

    $.validator.addMethod("checkPwdLength", function(value, element) {
        // 비밀번호가 8글자~16글자인지 확인합니다.
        return value.length >= 8 && value.length <= 16;
    });

    $.validator.addMethod("checkEmailContains", function(value, element) {
        // 비밀번호가 이메일 주소를 포함하는지 확인합니다.
        var inputEmail = $('#email').val();
        var emailPrefix = inputEmail.split('@')[0];
        return value.indexOf(emailPrefix) === -1;
    });

    $.validator.addMethod("checkPwdRule", function(value, element) {
        // 비밀번호가 영문 대/소문자, 숫자, 기호 중 2개 이상 조합되어 있는지 확인합니다.
        var hasUpperCase = /[A-Z]/.test(value);
        var hasLowerCase = /[a-z]/.test(value);
        var hasDigit = /\d/.test(value);
        var hasSpecialChar = /[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]/.test(value);
        var totalChars = hasUpperCase + hasLowerCase + hasDigit + hasSpecialChar;
        return totalChars >= 2;
    });

    userForm.validate({
        debug:true,

        rules: {                    // 유효성 검사 규칙
            birthDate: {                // 이메일 필드
                required: true, // 필수 필드 여부
                digits: true, // 숫자만 입력되는지 확인합니다.
                minlength: 8, // 최소 8자리여야 합니다. (년월일 8자리)
                maxlength: 8, // 최대 8자리까지 허용됩니다. (년월일 8자리)
                regex: /^(?:19|20)\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12][0-9]|3[01])$/,
                over14: true
            },
            nickname: {
                required: true,
                noSpaceOrSpecialChars : true,
                nicknameLength:true
            },
            password: {
                required: true,
                checkPwdLength: true,
                checkEmailContains: true,
                checkPwdRule: true
            }
        },
        messages: {                 // 오류값 발생시 출력할 메시지 수동 지정
            birthDate: {
                required: '필수 입력 항목입니다.',
                digits : '숫자만 입력해주세요.',
                minlength : '8자리로 입력해주세요',
                maxlength : '8자리로 입력해주세요',
                regex : '올바른 형식으로 입력하세요.',
                over14 : '14세 이상만 가입이 가능합니다.'
            },
            nickname: {
                required: '필수 입력 항목입니다.',
                nicknameLength : '닉네임은 2글자 이상, 10글자 이하로 입력해야 합니다.',
                noSpaceOrSpecialChars: '닉네임에 띄어쓰기 혹은 특수문자를 사용하실 수 없습니다.'
            },
            password: {
                required: '필수 입력 항목입니다.',
                checkPwdLength: '8~16글자 이내로 입력해주세요.',
                checkEmailContains: '이메일 주소가 포함되면 안됩니다.',
                checkPwdRule: '영문, 숫자, 기호 중 2가지 이상 조합해주세요.'
            }
        },
        errorPlacement: function(error, element) {
            if (element.attr("name") == "birthDate") {
                error.addClass('mt-2');
                error.addClass('mx-2');
                error.insertAfter($('#birthInputDiv'));
            } else if (element.attr("name") == "nickname") {
                error.addClass('mt-2');
                error.addClass('mx-2');
                error.insertAfter($('#nicknameInputDiv'));
            } else if (element.attr("name") == "password") {
                error.addClass('mt-2');
                error.addClass('mx-2');
                error.insertAfter($('#passwordInputDiv'));
            }
        }
    });

    $("form input").on('keyup change', function() {
        if (userForm.valid()) {
            var continueBtnHtml=
                `<a onclick="signup();" id="continueBtn" class="btn btn-primary btn-user btn-block">
                    계속하기
                </a>`;
            $("#continueBtnDiv").html(continueBtnHtml);
        } else {
            var continueBtnHtml=
                `<a href="#" id="disabledBtn" class="btn btn-secondary btn-user btn-block disabled">
                    계속하기
                </a>`;
            $("#continueBtnDiv").html(continueBtnHtml);
        }
    });
});