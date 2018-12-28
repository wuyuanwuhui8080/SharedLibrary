$(document).ready(function () {
    $("#up-img-touch").click(function () {
        $("#doc-modal-1").modal({width: '600px'});
    });
});
$(function () {
    'use strict';
    // 初始化
    var $image = $('#image');
    /* $image.cropper({
         aspectRatio: '1',
         autoCropArea: 0.8,
         preview: '.up-pre-after',

     });*/

    // 事件代理绑定事件
    $('.docs-buttons').on('click', '[data-method]', function () {

        var $this = $(this);
        var data = $this.data();
        var result = $image.cropper(data.method, data.option, data.secondOption);
        switch (data.method) {
            case 'getCroppedCanvas':
                if (result) {
                    // 显示 Modal
                    $('#cropped-modal').modal().find('.am-modal-bd').html(result);
                    $('#download').attr('href', result.toDataURL('image/jpeg'));
                }
                break;
        }
    });


    // 上传图片
    var $inputImage = $('#inputImage');
    var URL = window.URL || window.webkitURL;
    var blobURL;

    if (URL) {
        $inputImage.change(function () {
            var files = this.files;
            var file;

            if (files && files.length) {
                file = files[0];

                if (/^image\/\w+$/.test(file.type)) {
                    blobURL = URL.createObjectURL(file);
                    $image.one('built.cropper', function () {
                        // Revoke when load complete
                        URL.revokeObjectURL(blobURL);
                    }).cropper('reset').cropper('replace', blobURL);
                    $inputImage.val('');
                } else {
                    window.alert('Please choose an image file.');
                }
            }

            // Amazi UI 上传文件显示代码
            var fileNames = '';
            $.each(this.files, function () {
                fileNames += '<span class="am-badge">' + this.name + '</span> ';
            });
            $('#file-list').html(fileNames);
        });
    } else {
        $inputImage.prop('disabled', true).parent().addClass('disabled');
    }

    //绑定上传事件
    $('#up-btn-ok').on('click', function () {
        var $modal = $('#my-modal-loading');
        var $modal_alert = $('#my-alert');
        var img_src = $image.attr("src");
        if (img_src == "") {
            set_alert_info("没有选择上传的图片");
            $modal_alert.modal();
            return false;
        }

        $modal.modal();
        var imgSrc = $("#userImg").val();
        var url = app.path() + "/sharedUsers/uploadHeadImg";
        var canvas = $("#image").cropper('getCroppedCanvas');
        var data = canvas.toDataURL(); //转成base64
        var userId = $("#userId").val();
        $.ajax({
            url: url,
            dataType: 'json',
            type: "POST",
            data: {"image": data.toString(), userId: userId, userImg: imgSrc},
            success: function (date) {
                $modal.modal('close');
                if (date.status == 200) {
                    swal({
                        title: "修改成功!",
                        type: "success"
                    }, function () {
                        // window.parent.location.reload();//刷新父页面
                        // 局域刷新父节点
                        parent.$("#MyLiImg").load(window.parent.location.href + " #MyLiImg");
                        parent.$("#modelHeadImgId").load(window.parent.location.href + " #modelHeadImgId");
                        location.href = app.path() + "/sharedUsers/goUpload";
                    });
                } else {
                    swal({
                        title: date.msg,
                        type: "success"
                    });
                }

            },
            error: function () {
                $modal.modal('close');
                swal({
                    title: "服务器发生错误！",
                    type: "success"
                });
                $modal_alert.modal();
            }
        });

    });
});

function rotateimgright() {
    $("#image").cropper('rotate', 90);
}


function rotateimgleft() {
    $("#image").cropper('rotate', -90);
}

function set_alert_info(content) {
    $("#alert_content").html(content);
}
