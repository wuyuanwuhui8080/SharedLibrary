$(function () {

    /**
     * 发论坛
     * @author 博博大人
     * @time 2019/1/18 16:43
     */
    $("#sumitForum").click(function () {
        // 获取标题
        var title = $(".forum_title").val();
        // 获取内容
        var content = editor.txt.html();
        // 获取类别id
        var classId = $(".selectClassId").val();

        if (app.isNull(title)) {
            swal("标题不能为空...", null, "warning");
        } else if (content.length == 11) {
            swal("内容不能为空!", null, "warning");
        } else if (app.isNull(classId)) {
            swal("请选择专栏！", null, "warning");
        } else {
            $.ajax({
                type: "post",
                url: path + "/sharedForum/saveForum",
                data: {content: content, classId: classId, title: title},
                dataType: "json",
                timeout: 1000,
                beforeSend: function () {
                    $("body").append(app.loads());
                },
                success: function (date) {
                    if (date.status == 200) {
                        alert("发帖成功.");
                        // 跳转到详细页面
                    } else {
                        swal(date.msg, null, "error");
                    }
                },
                error: function () {
                    swal("网络连接超时!", null, "error");
                },
                complete: function () {
                    $("#ibox").remove();
                }
            });
        }

        return false;

    });

});

/**
 * 刷新验证码
 * @author 博博大人
 * @time 2019/1/18 16:47
 */
function refreshCaptcha() {
    $("#captcha_img").attr("src", "/Captcha.jpg?id=" + new Date() + Math.floor(Math.random() * 24));
}