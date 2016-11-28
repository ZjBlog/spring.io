/**
 * 
 */
define(function(require, exports, module) {

    // require dependence
    require('jquery');
    require('bootstrap');
    require('system');
    
    /**
     * 页面从新加载后,判断是否登录陈功
     */
    exports.loginSuccessVerification = function(){
        $('#password').val('');
        var status = $('#login-status').attr('login_status');
        //登录失败了
        if (status == '0') {
            var login_error_msg = $('#login_error_msg').attr('login_error_msg');
            System.create_bootstarp_alert({
                type: 'danger',
                content: login_error_msg
            });
        }
    };
    
    /**
     * 电话号码验证
     */
    exports.mobileVerification = function(){
        var mobile = $('#mobile').val();
        if (!mobile) {
            $('#mobile-register').html('手机号码不能为空');
            $('#mobile').focus();
            return false;
        }
        var isTrue = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9]|17[0-9])\d{8}$/.test(mobile);
        if (!isTrue) {
            $('#mobile-register').html('请输入正确手机号码');
            return false;
        }
        $('#mobile-register').empty();
        return true;
    };
    
    /**
     * 密码验证
     */
    exports.passwordVerification = function(){
        var password = $('#password').val();
        if (!password) {
            $('#password-register').html('密码不能为空');
            return false;
        }
        var len = password.length;
        if (len < 6 || len > 20) {
            $('#password-register').html('密码要在6-20个字符之间');
            return false;
        }
        $('#password-register').empty();
        return true;
    };
    
    /**
     * 登录
     */
    exports.login = function(){
        //点击登陆，验证手机号码，返回验证结果
        var isTrue = exports.mobileVerification();
        if (!isTrue) {
            return isTrue;
        }
        //返回验证密码结果
        return exports.passwordVerification();
    };

});
