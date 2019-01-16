<#include "../comm/head.ftl">

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
<#include "../comm/tail.ftl">
<script src="../../res/layui/layui.js"></script>
<script>
    layui.cache.page = 'user';
    layui.cache.user = {
        username: '游客'
        , uid: -1
        , avatar: '../../res/images/avatar/00.jpg'
        , experience: 83
        , sex: '男'
    };
    layui.config({
        version: "3.0.0"
        , base: '../../res/mods/'
    }).extend({
        fly: 'index'
    }).use('fly');
</script>
