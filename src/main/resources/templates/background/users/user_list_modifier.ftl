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
        <h2>修改用户资料</h2>
        <form class="m-t" role="form" id="registerFromId" method="post"
              action="${basePath}/sharedUsers/updateUser">
            <input type="hidden" name="positionId" value="${users.positionId}"/>
            <input type="hidden" name="id" value="${users.id}"/>
            <div class="form-group">
                <input readonly="readonly" type="text" id="userName" value="${users.userName}"
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
            <div class="row">
                <div class="col-sm-6">
                    <button type="submit" id="upadte" class="btn btn-primary block full-width m-b">修 改</button>
                </div>
                <div class="col-sm-6">
                    <button type="button" class="btn btn-primary block full-width m-b" onclick="history.back(-1)">返 回
                    </button>
                </div>
            </div>
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
<script>
    var path = "${basePath}";
    /**
     * 制定提交和返回格式，并执行业务!
     * @author 博博大人
     * @time 2018/12/13 14:03
     */
    var option = {
        type: "post",
        dataType: "json",
        timeout: 1000,
        beforeSend: function () {
            $("body").append(app.loads());
        },
        success: function (date) {
            if (date.status == 200) {
                location.href = path + "/sharedUsers/goUserList";
            } else {
                swal({
                    title: date.msg,
                    type: "error",
                });
            }
        },
        error: function () {
            swal({
                title: "网络连接超时！",
                type: "error",
            });
        },
        complete: function () {
            $("#ibox").remove();
        }
    }

    $(function () {
        $("#upadte").click(function () {
            sumitfrom();
            return false;
        });
    });

    /**
     *  执行ajax登录
     * @author 博博大人
     * @time 2018/12/13 14:02
     */
    function sumitfrom() {
        var captcha = $("#captcha").val();
        if (app.isNull(captcha)) {
            swal("验证码不能为空！", null, "warning");
            return false;
        }
        if (phoneblur() && birthdayblur()) {
            $("#registerFromId").ajaxSubmit(option);
        } else {
            swal({
                title: "格式不正确！",
                type: "error",
            });
            return false;
        }

    }


    var falg = 1;


    /**
     *  校验生日是否合法
     * @author 博博大人
     * @time 2018/12/13 14:01
     */
    function birthdayblur() {
        var birthday = document.getElementById("birthday").value;
        if (!app.isNull(birthday)) {
            if (app.checkStr(birthday, "date")) {
                document.getElementById("birthdaySpanId").innerHTML = "";
                return true;
            } else {
                document.getElementById("birthdaySpanId").innerHTML = "<font color='red'>日期格式不正确！</font>";
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 校验电话号码是否合法
     * @author 博博大人
     * @returns {boolean}
     */
    function phoneblur() {
        var phone = document.getElementById("phone").value;
        if (!app.isNull(phone)) {
            if (app.checkStr(phone, "phone")) {
                document.getElementById("phoneSpanId").innerHTML = "";
                return true;
            } else {
                document.getElementById("phoneSpanId").innerHTML = "<font color='red'>手机号格式不正确！</font>";
                return false;
            }
        } else {
            return false;
        }
    }
</script>