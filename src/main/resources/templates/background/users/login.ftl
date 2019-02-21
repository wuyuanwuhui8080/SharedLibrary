<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title>登录</title>
    <#include "../comm/script.ftl">
    <link href="${basePath}/css/login.css" rel="stylesheet">
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location;
        }
    </script>

</head>

<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            <div class="signin-info">
                <div class="logopanel m-b">
                    <h1>共享资源库</h1>
                </div>
                <div class="m-b"></div>
                <h4>欢迎使用 <strong>共享资源库</strong></h4>
                <ul class="m-b">
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 可以进行实时聊天</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 可以发布问题</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 可以增加公司人员的交流</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 无聊的时候可以玩小游戏</li>
                </ul>
                <strong>还没有账号？<a href="${basePath}/sharedUsers/goRegister">立即注册&raquo;</a> </strong>
            </div>
        </div>
        <div class="col-sm-5">
            <form  method="post" role="form" action="${basePath}/sharedUsers/doLogin">
                <h4 class="no-margins">登录：</h4>
                <p class="m-t-md">登录到共享资源库后台</p>
                <input type="text" class="form-control uname" name="userName" id="userName" placeholder="用户名"/>
                <input type="password" class="form-control pword m-b" name="password" id="password" placeholder="密码"/>
                <input type="text" id="captcha" class="form-control pword m-b" placeholder="验证码" name="captcha"/>
                <p> <a style="margin-left: 30%" href="javascript:refreshCaptcha()"><img alt="验证码"
                                                                                        src="${basePath}/Captcha.jpg"
                                                                                        title="点击更换"
                                                                                        id="captcha_img"/></a></p>
                <a href="">忘记密码了？</a>
                <button type="submit" id="sumitIdLogin" class="btn btn-success btn-block">登录</button>
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">
            &copy; 2019 All Rights Reserved 第一小组
        </div>
    </div>
</div>
</body>

</html>
<script>
    $(function () {
        $("#sumitIdLogin").click(function () {
            sumitFrom();
            return false;
        });
    });
</script>
<script>
    function refreshCaptcha() {
        $("#captcha_img").attr("src", "/Captcha.jpg?id=" + new Date() + Math.floor(Math.random() * 24));
    }

    var option = {
        beforeSend: function () {
            $("body").append(app.loads());
        },
        dataType: "json",
        success: function (date) {
            if (date.status == 200) {
                location.href = "${basePath}/sharedUsers/goIndex";
            } else {
                swal({
                    title: date.msg,
                    type: "error"
                });
                document.getElementById("captcha").value = "";
                refreshCaptcha();
            }
        },
        complete: function () {
            $("#ibox").remove();
        },
        error: function () {
            alert("服务器出错，请联系管理员!");
        }
    }

    function sumitFrom() {
        var userName = document.getElementById("userName").value;
        var password = document.getElementById("password").value;
        var captcha = document.getElementById("captcha").value;
        if (app.isNull(userName)) {
            swal({
                title: "用户名不能为空！",
                type: "error"
            });
            return false;
        } else if (app.isNull(password)) {
            swal({
                title: "密码不能为空！",
                type: "error"
            });
            return false;
        } else if (app.isNull(captcha)) {
            swal({
                title: "验证码不能为空！",
                type: "error"
            });
            return false;
        } else {
            $("form").ajaxSubmit(option);
            return false;
        }
    }
</script>
