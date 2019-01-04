$(function () {
    $(document).ready(function () {
        $('.summernote').summernote({
            lang: 'zh-CN'
        });

    });
    var edit = function () {
        $("#eg").addClass("no-padding");
        $('.click2edit').summernote({
            lang: 'zh-CN',
            focus: true
        });
    };
    var save = function () {
        $("#eg").removeClass("no-padding");
        var aHTML = $('.click2edit').code(); //save HTML If you need(aHTML: array).
        $('.click2edit').destroy();
    };

    /*----------------------------- 上面是初始化编辑框 ---------------------------*/

    /**
     * 发送博客
     * @author 博博大人
     * @time 2018/12/29 19:23
     */
    $("#sumitEditTest").click(function () {
        var sHTML = $('.summernote').code();
        if (sHTML.length == 11 || sHTML == null || sHTML == "") {
            swal({
                title: "清先输入文字！",
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