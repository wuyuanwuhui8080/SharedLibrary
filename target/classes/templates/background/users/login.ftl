<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>H+ 后台主题UI框架 - 登录</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <#include "../comm/script.ftl">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <script>if (window.top !== window.self) {
        window.top.location = window.location;
    }</script>
</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>

            <h1 class="logo-name">V</h1>

        </div>
        <h3>欢迎使用内部博客</h3>

        <form class="m-t" method="post" role="form" action="${basePath}/sharedUsers/doLogin">
            <div class="form-group">
                <input type="text" class="form-control" name="userName" id="userName" placeholder="用户名" required="">
            </div>
            <i id='icon'></i>
            <div class="form-group">
                <input type="password" class="form-control" name="password" id="password" placeholder="密码" required="">
            </div>
            <div class="form-group">
                验证码：<input type="text" id="captcha" name="captcha"/><a href="javascript:refreshCaptcha()"><img alt="验证码"
                                                                                                               src="${basePath}/Captcha.jpg"
                                                                                                               title="点击更换"
                                                                                                               id="captcha_img"/></a>
            </div>
            <button type="submit" id="sumitIdLogin" class="btn btn-primary block full-width m-b">登 录</button>
        </form>
        <p class="text-muted text-center"><a href="login.ftl#">
            <small>忘记密码了？</small>
        </a> | <a href="${basePath}/sharedUsers/goRegister">注册一个新账号</a>
        </p>
    </div>
</div>

</body>
<#-- <div class="ibox-content">
                        <div class="spiner-example">
                            <div class="sk-spinner sk-spinner-three-bounce">
                                <div class="sk-bounce1"></div>
                                <div class="sk-bounce2"></div>
                                <div class="sk-bounce3"></div>
                            </div>
                        </div>
                    </div>-->
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
                swal({
                    title: "登录成功！",
                    type: "success",
                }, function () {
                    location.href = "/sharedUsers/goIndex";
                });
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
