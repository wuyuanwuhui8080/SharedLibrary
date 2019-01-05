<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>修改旧密码</title>
    <#include "../comm/script.ftl">
    <script src="${basePath}/js/users/users_password.js"></script>
</head>
<body>
<div class="middle-box text-center loginscreen   animated fadeInDown">
    <div id="pwdDiv">
        <h2>当前密码</h2>
        <form class="m-t" role="form" id="registerFromId">
            <div class="form-group">
                <input type="password" id="password1" name="password" class="form-control"
                       placeholder="请输入当前密码" required="">
                <span id="password1SpanId"></span>
            </div>
            <div class="form-group">
                验证码：<input type="text" id="captcha" name="captcha"/><a href="javascript:refreshCaptcha()"><img alt="验证码"
                                                                                                               src="${basePath}/Captcha.jpg"
                                                                                                               title="点击更换"
                                                                                                               id="captcha_img"/></a>
            </div>
            <button type="submit" id="goOldPwd" class="btn btn-primary block full-width m-b">下一步</button>
        </form>
    </div>
</div>
</body>
</html>
<script>
    var path = "${basePath}";
    var usersName = "${users.userName}";
</script>