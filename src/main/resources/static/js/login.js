$(document).ready(function(){
    var email = cookieUtil.getCookie("signupInputEmail");
    if(email != null){
        $('#inputEmail').val(email);
    }
});