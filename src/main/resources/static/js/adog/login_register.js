
$(document).ready(function(){
    //去注册
    $("#show-register-div").click(function(){
        $("#adogid-register").css('display', 'block');
        $("#adogid-login").css('display', 'none');
        $("#adogid-fixpwd").css('display', 'none');
    });
    //去登录
    $("#show-login-div").click(function(){
        $("#adogid-login").css('display', 'block');
        $("#adogid-register").css('display', 'none');
        $("#adogid-fixpwd").css('display', 'none');
    });
    //去修改密码
    $("#show-fixpwd-div").click(function(){
        $("#adogid-fixpwd").css('display', 'block');
        $("#adogid-login").css('display', 'none');
        $("#adogid-register").css('display', 'none');
    });
});