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
var option = {
    beforeSend: function () {
        $("body").append(app.loads());
    },
    dataType: "json",
    success: function (date) {
        if (date.status == 200) {
            location.href = path + "/sharedForum/goIndex";
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
        swal("服务器出错，请联系管理员!",null,"error");
    }
}

function sumitFrom() {
    var userName = document.getElementById("userName").value;
    var password = document.getElementById("password").value;
    var captcha = document.getElementById("captcha").value;
    if (app.isNull(userName)) {
        swal({
            title: "用户名不能为空！",
            type: "warning"
        });
        return false;
    } else if (app.isNull(password)) {
        swal({
            title: "密码不能为空！",
            type: "warning"
        });
        return false;
    } else if (app.isNull(captcha)) {
        swal({
            title: "验证码不能为空！",
            type: "warning"
        });
        return false;
    } else {
        $("form").ajaxSubmit(option);
        return false;
    }
}

function refreshCaptcha() {
    $("#captcha_img").attr("src", "/Captcha.jpg?id=" + new Date() + Math.floor(Math.random() * 24));
}
