$(function () {
    var E = window.wangEditor;
    var editor = new E('#editor');
    var emailContent = $('#emailContent');
    editor.customConfig.onchange = function (html) {
        // 监控变化，同步更新到 textarea
        emailContent.val(html)
    };
    // 使用 base64 保存图片
    editor.customConfig.uploadImgShowBase64 = true;
    editor.create();
    // 初始化 textarea 的值
    emailContent.val(editor.txt.html());
    /*----------------------------- 上面是初始化编辑框 ---------------------------*/

    /**
     * 发送博客
     * @author 博博大人
     * @time 2018/12/29 19:23
     */
    $("#sumitEditTest").click(function () {
        // 获取内容
        var sHTML = editor.txt.html();
        if (sHTML.length == 11 || sHTML == null || sHTML == "") {
            swal({
                title: "请先输入文字！",
                type: "error"
            });
            return;
        } else {
            $.ajax({
                type: "post",
                url: path + "/shareBlogs/saveBlos",
                data: {userId: userid, content: sHTML},
                dataType: "json",
                timeout: 10000,
                beforeSend: function () {
                    $("body").append(app.loads());
                },
                success: function (date) {
                    if (date.status == 200) {
                        swal({
                            title: "博客发布成功！",
                            type: "success"
                        }, function () {
                            location.reload();
                        });

                    } else {
                        swal({
                            title: date.msg,
                            type: "error"
                        });
                    }
                },
                complete: function () {
                    $("#ibox").remove();
                },
                error: function () {
                    swal({
                        title: "网络连接超时，请检查您当前的网络!",
                        type: "error"
                    });
                }
            });
        }
    });
});