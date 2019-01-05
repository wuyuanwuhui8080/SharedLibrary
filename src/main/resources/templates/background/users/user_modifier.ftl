<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册</title>
    <#include "../comm/script.ftl">
    <!-- layerDate plugin javascript -->
    <script src="${basePath}/js/plugins/layer/laydate/laydate.js"></script>
    <link href="${basePath}/css/plugins/iCheck/custom.css" rel="stylesheet">

</head>
<body class="gray-bg">
<input type="hidden" id="path" value="${basePath}"/>
<div class="middle-box text-center loginscreen   animated fadeInDown">
    <div>
        <h2>修改个人资料</h2>
        <form class="m-t" role="form" id="registerFromId" method="post"
              action="${basePath}/sharedUsers/updateUsers">
            <input type="hidden" name="id" value="${users.id}"/>
            <div class="form-group">
                <input type="text" id="userName" name="userName" onblur="userNameblur();" value="${users.userName}"
                       class="form-control"
                       placeholder="请输入用户名" required="">
                <span id="userNameSpanId"></span>
            </div>
            <div class="form-group">
                <input type="text" id="realName" name="realName" class="form-control" value="${users.realName}"
                       placeholder="请输入真实姓名" required="">
            </div>
            <div class="form-group">
                <input type="text" id="phone" name="phone" onblur="phoneblur();" value="${users.phone}"
                       class="form-control"
                       placeholder="请输入手机号" required="">
                <span id="phoneSpanId"></span>
            </div>
            <div class="form-group">
                <input id="birthday" value="${users.birthday?date}" name="birthday" style="width: 100%"
                       class="laydate-icon form-control layer-date"
                       placeholder="请输入生日">
                <span id="birthdaySpanId"></span>
            </div>
            <div class="form-group">
                <#if users.sex == 1>
                    <input type="radio" checked="true" name="sex" value="1"/>男
                    <input type="radio" name="sex" value="2">女
                <#else >
                    <input type="radio" name="sex" value="1"/>男
                    <input type="radio" checked="true" name="sex" value="2">女
                </#if>
            </div>
            <div class="form-group">
                验证码：<input type="text" id="captcha" name="captcha"/><a href="javascript:refreshCaptcha()"><img alt="验证码"
                                                                                                               src="${basePath}/Captcha.jpg"
                                                                                                               title="点击更换"
                                                                                                               id="captcha_img"/></a>
            </div>
            <button type="submit" id="upadte" class="btn btn-primary block full-width m-b">修 改</button>
        </form>
    </div>
    <input value="${users.userName}" type="hidden" id="userNameLoad"/>
</div>

<!-- iCheck -->
<script src="${basePath}/js/plugins/iCheck/icheck.min.js"></script>
<script>
    $(document).ready(function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
    });
</script>

<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>
</html>
 <script>
     function refreshCaptcha() {
         $("#captcha_img").attr("src", "/Captcha.jpg?id=" + new Date() + Math.floor(Math.random() * 24));
     }
     //外部js调用
     laydate({
         elem: '#birthday', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
         event: 'focus' //响应事件。如果没有传入event，则按照默认的click
     });

     //日期范围限制
     var start = {
         elem: '#start',
         format: 'YYYY/MM/DD hh:mm:ss',
         min: laydate.now(), //设定最小日期为当前日期
         max: '2099-06-16 23:59:59', //最大日期
         istime: true,
         istoday: false,
         choose: function (datas) {
             end.min = datas; //开始日选好后，重置结束日的最小日期
             end.start = datas //将结束日的初始值设定为开始日
         }
     };
     var end = {
         elem: '#end',
         format: 'YYYY/MM/DD hh:mm:ss',
         min: laydate.now(),
         max: '2099-06-16 23:59:59',
         istime: true,
         istoday: false,
         choose: function (datas) {
             start.max = datas; //结束日选好后，重置开始日的最大日期
         }
     };
     laydate(start);
     laydate(end);
 </script>
<script src="${basePath}/js/users/users_modifier.js"></script>