var path = document.getElementById("path").value;
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
            swal({
                title: "修改成功！",
                type: "success",
            });
            $("#captcha").val("");
            parent.$(".MyUsersName").load(window.parent.location.href + " .MyUsersName");
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
    if (userNameblur() && birthdayblur() && phoneblur()) {
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

/**
 * 校验用户名是否重复
 * @author 博博大人
 * @time 2019/1/4 22:17
 */
function getUserName() {
    var userName1 = document.getElementById("userName").value;
    var userNameLoad = document.getElementById("userNameLoad").value;
    if (app.isNull(userName1)) {
        return false;
    } else {
        if (userNameLoad == userName1) {
            document.getElementById("userNameSpanId").innerHTML = "";
            falg = 0;
        } else {
            $.ajax({
                type: "get",
                data: {userName: userName1},
                dataType: "json",
                url: path + "/sharedUsers/getUserName",
                success: function (date) {
                    if (date.status == 200) {
                        document.getElementById("userNameSpanId").innerHTML = "";
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
