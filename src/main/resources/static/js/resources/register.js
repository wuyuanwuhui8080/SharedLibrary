$(function () {
    $("#Sumitres").click(function () {
        var captcha = $("#captcha").val();
        if (app.isNull(captcha)) {
            layer.msg("验证码不能空！", {shift: 6});
            return false;
        }
        sumitfrom();
        return false;
    });
});

/**
 * 制定提交和返回格式，并执行业务!
 * @author 博博大人
 * @time 2018/12/13 14:03
 */
var option = {
    type: "post",
    dataType: "json",
    success: function (date) {
        if (date.status == 200) {
            alert("注册成功！");
            location.href = path + "/sharedUsers/goForumLogin";
        } else {
            layer.msg(date.msg, {shift: 6});
            $("#captcha").val("");
            refreshCaptcha();
        }
    },
    error: function () {
        layer.msg("服务器出错，请联系管理员!", {shift: 6});
    }
}


/**
 *  执行ajax登录
 * @author 博博大人
 * @time 2018/12/13 14:02
 */
function sumitfrom() {
    if (userNameblur() && password1blur() && password2blur() && birthdayblur() && phoneblur()) {
        $("#registerFromId").ajaxSubmit(option);
    } else {
        layer.msg("格式不正确！", {shift: 6});
        return false;
    }

}


var falg = 1;

/**
 *  校验用户名是否合法
 * @author 博博大人
 * @time 2018/12/13 14:02
 */
function userNameblur() {
    var userName = document.getElementById("userName").value;
    if (!app.isNull(userName)) {
        if (app.checkStr(userName, "username")) {
            getUserName();
            if (falg == 1) {
                return false;
            } else if (falg == 0) {
                return true;
            }
        } else {
            document.getElementById("userNameSpanId").innerHTML = "<font color='red'>用户名格式不正确，必须是4到16位（字母，数字，下划线，减号）</font>";
            return false;
        }
    } else {
        return false;
    }

};

function getUserName() {
    var userName = document.getElementById("userName").value;
    if (app.isNull()) {
        $.ajax({
            type: "get",
            data: {userName: userName},
            dataType: "json",
            url: path + "/sharedUsers/getUserName",
            success: function (date) {
                if (date.status == 200) {
                    document.getElementById("userNameSpanId").innerHTML = "<font color='green'>用户名可以使用！</font>";
                    falg = 0;
                } else {
                    document.getElementById("userNameSpanId").innerHTML = "<font color='red'>用户名重复，请重新输入！</font>";
                    falg = 1;
                }
            },
            error: function () {
                falg = 1;
            }
        });
    }
}

/**
 *  校验密码是否符合格式
 * @author 博博大人
 * @time 2018/12/13 14:01
 */
function password1blur() {
    var password1 = document.getElementById("password1").value;
    if (!app.isNull(password1)) {
        if (app.checkStr(password1, "pwd")) {
            document.getElementById("password1SpanId").innerHTML = "";
            return true;
        } else {
            document.getElementById("password1SpanId").innerHTML = "<font color='red'>密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线</font>";
            return false;
        }
    } else {
        return false;
    }
};

/**
 * 校验确认密码是否一样
 * @author 博博大人
 * @time 2018/12/13 14:01
 */
function password2blur() {
    var password1 = document.getElementById("password1").value;
    var password2 = document.getElementById("password2").value;
    if (!app.isNull(password2)) {
        if (password1 != password2) {
            document.getElementById("password2SpanId").innerHTML = "<font color='red'>两次密码输入的不相同!</font>";
            return false;
        } else {
            document.getElementById("password2SpanId").innerHTML = "";
            return true;
        }
    } else {
        return false;
    }
}

/**
 *  校验生日是否合法
 * @author 博博大人
 * @time 2018/12/13 14:01
 */
function birthdayblur() {
    var birthday = document.getElementById("birthday").value;
    if (!app.isNull(birthday)) {
        if (app.checkStr(birthday, "date")) {
            if (!app.contrastTime(birthday)) {
                document.getElementById("birthdaySpanId").innerHTML = "<font color='red'>不能超过当前时间！</font>";
                return false;
            } else {
                document.getElementById("birthdaySpanId").innerHTML = "";
                return true;
            }
        } else if (!app.checkStr(birthday, "date")) {
            document.getElementById("birthdaySpanId").innerHTML = "<font color='red'>格式不正确！</font>";
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


function refreshCaptcha() {
    $("#captcha_img").attr("src", "/Captcha.jpg?id=" + new Date() + Math.floor(Math.random() * 24));
}