$(document).ready(function(){
    setEmailField();
});

function setEmailField(){
    var email = cookieUtil.getCookie("signupInputEmail");
    if(email != null){
        $('#inputEmail').val(email);
    }

    cookieUtil.deleteCookie("signupInputEmail");
}