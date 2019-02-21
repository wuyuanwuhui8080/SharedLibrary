$(function () {
    $("#sumitLogin").click(function () {
        sumitFrom();
        return false;
    });
});

/**
 * 执行form表单的提交
 * @author 博博大人
 * @time 2019/1/19 19:00
 */

/*var option = {
    beforeSend: function () {
        $("body").append(app.loads());
    },
    dataType: "json",
    success: function (date) {
        if (date.status == 200) {
            location.href = path + "/sharedForum/goIndex";
        } else {
            layer.msg(date.msg, {shift: 6});
            document.getElementById("captcha").value = "";
            refreshCaptcha();
        }
    },
    complete: function () {
        $("#ibox").remove();
    },
    error: function () {
        layer.msg("服务器出错，请联系管理员!", {shift: 6});
    }
}*/

function sumitFrom() {
    var userName = document.getElementById("userName").value;
    var password = document.getElementById("password").value;
    var captcha = document.getElementById("captcha").value;
    if (app.isNull(userName)) {
        layer.msg("用户名不能为空！", {shift: 6});
        return false;
    } else if (app.isNull(password)) {
        layer.msg("密码不能为空！", {shift: 6});
        return false;
    } else if (app.isNull(captcha)) {
        layer.msg("验证码不能为空！", {shift: 6});
        return false;
    } else {
        app.loadJson(path + "/sharedUsers/doLogin", {
            userName: userName,
            password: password,
            captcha: captcha
        }, function (date) {
            location.href = path + "/sharedForum/goIndex";
        }, {
            error: function () {
                document.getElementById("captcha").value = "";
                refreshCaptcha();
            }
        });
        return false;
    }
}

function refreshCaptcha() {
    $("#captcha_img").attr("src", "/Captcha.jpg?id=" + new Date() + Math.floor(Math.random() * 24));
}
