var option = {
    dataType: "json",
    beforeSend: function () {
        $("#drop_area").append(app.loads());
    },
    success: function (date) {
        if (date.status == 200) {
            swal({
                title: "头像上传成功!",
                type: "success"
            }, function () {

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
            title: "服务器发生错误！",
            type: "error"
        });
    }
}

/*
function sumitHeadImg() {
    alert(document.getElementById("fileImage").value);
    // $("#headImgFrom").ajaxSubmit(option);
}*/
$(function () {
    $("#demo").on("click", ".upload_btn", function () {
        var userId = $("#userId").val();
        $.ajaxFileUpload({
            url: app.path() + "/sharedUsers/uploadHeadImg",
            fileElementId: "fileImage",    //需要上传的文件域的ID，即<input type="file">的ID。
            dataType: "json",
            data: {userId: userId},
            secureuri: false,   //是否启用安全提交，默认为false。
            beforeSend: function () {
                $("#demo").append(app.loads());
            },
            success: function (date) {
                if (date.status == 200) {
                    swal({
                        title: "头像上传成功!",
                        type: "success"
                    }, function () {

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
                    title: "服务器发生错误！",
                    type: "error"
                });
            }
        });
    });
});