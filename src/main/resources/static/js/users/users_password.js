function refreshCaptcha() {
    $("#captcha_img").attr("src", "/Captcha.jpg?id=" + new Date() + Math.floor(Math.random() * 24));
}

$(function () {
    var pro = $("body");
    /**
     * 校验旧密码
     */
    pro.on("click", "#goOldPwd", function () {
        var password = $("#password1").val();
        var captcha = $("#captcha").val();
        if (app.isNull(password)) {
            swal("密码不能为空！", null, "warning");
            return false;
        } else if (app.isNull(captcha)) {
            swal("验证码不能为空！", null, "warning");
            return false;
        } else {
            $.ajax({
                type: "post",
                url: path + "/sharedUsers/verifyOdlPaassword/" + password + "/" + usersName + "/" + captcha,
                beforeSend: function () {
                    $("body").append(app.loads());
                },
                timeout: 1000,
                success: function (date) {
                    if (date.status == 200) {
                        var div = '<h2>当前密码</h2>\n' +
                            '        <form class="m-t" role="form" id="registerFromId">\n' +
                            '         <input type="hidden" id="userId" name="id" value="' + date.obj + '"/>' +
                            '            <div class="form-group">\n' +
                            '                <input type="password" id="password1" name="password" class="form-control"\n' +
                            '                       placeholder="请输入新密码" onblur="password1blur()" required="">\n' +
                            '                <span id="password1SpanId"></span>\n' +
                            '            </div>\n' +
                            '        <div class="form-group">\n' +
                            '                <input type="password" id="password2" name="password" onblur="password2blur();" class="form-control"\n' +
                            '                       placeholder="确认密码" required="">\n' +
                            '                <span id="password2SpanId"></span>\n' +
                            '            </div>\n' +
                            '            <div class="form-group">\n' +
                            '                验证码：<input type="text" id="captcha" name="captcha"/><a href="javascript:refreshCaptcha()"><img alt="验证码"\n' +
                            '                                                                                                               src="' + path + '/Captcha.jpg"\n' +
                            '                                                                                                               title="点击更换"\n' +
                            '                                                                                                               id="captcha_img"/></a>\n' +
                            '            </div>\n' +
                            '            <button type="submit" id="goNewPwd" class="btn btn-primary block full-width m-b">确认修改</button>\n' +
                            '        </form>';
                        $("#pwdDiv").html(div);
                    } else {
                        swal(date.msg, null, "error");
                        $("#captcha").val("");
                        refreshCaptcha();
                    }
                },
                error: function () {
                    swal("网络超时", null, "error");
                },
                complete: function () {
                    $("#ibox").remove();
                }
            });
        }
        return false;
    });

    /**
     * 修改新密码
     */
    pro.on("click", "#goNewPwd", function () {
        var password = document.getElementById("password1").value;
        var id = $("#userId").val();
        var captcha = $("#captcha").val();
        if (app.isNull(captcha)) {
            swal("验证码不能为空！", null, "warning");
            return false;
        } else {
            if (password1blur() && password2blur()) {
                if(confirm("您确定要修改密码吗")){
                    $.ajax({
                        type: "post",
                        url: path + "/sharedUsers/updateNewPassword",
                        data: {userName: usersName, password: password, id: id, captcha: captcha},
                        dataType: "json",
                        timeout: 1000,
                        beforeSend: function () {
                            $("body").append(app.loads());
                        },
                        success: function (date) {
                            if (date.status == 200) {
                                alert("修改成功！");
                                location.href = path + "/sharedUsers/loginOut";
                            } else {
                                swal(date.msg, null, "error");
                            }

                        },
                        complete: function () {
                            $("#ibox").remove();
                        },
                        error: function () {
                            swal("网络连接超时，请检查您的网络!", null, "error");
                        }
                    });
                }
            } else {
                swal("输入的不符合规则！", null, "warning");
            }
        }
        return false;
    })
});

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