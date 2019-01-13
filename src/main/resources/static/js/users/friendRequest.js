$(document).ready(function () {

    /**
     * 同意操作
     * @author 博博大人
     * @time 2019/1/3 22:36
     */
    $(".toSuccess").click(function () {
        var obj = $(this);
        var status = 2;
        var requestFriendId = obj.attr("requestId");
        var miId = obj.attr("miId");
        var requestId = obj.attr("byrequestId");
        $.ajax({
            type: "post",
            url: path + "/sharedlFriendRequest/updateFriendRequest",
            data: {id: requestFriendId, status: status, meId: miId, requestId: requestId},
            dataType: "json",
            beforeSend: function () {
                $("body").append(app.loads());
            },
            success: function (date) {
                if (date.status == 200) {
                    obj.parent().html('<button class="btn">已同意</button>');
                } else {
                    swal("请求失败！", null, "error");
                }
            },
            error: function () {
                swal("网络超时.等稍后再试!", null, "error");
            },
            complete: function () {
                $("#ibox").remove();
            }
        });
    });

    /**
     * 拒绝操作
     * @author 博博大人
     * @time 2019/1/3 22:38
     */
    $(".toError").click(function () {
        var obj = $(this);
        var status = 3;
        var requestFriendId = obj.attr("requestId");

        $.ajax({
            type: "post",
            url: path + "/sharedlFriendRequest/updateFriendRequest",
            data: {id: requestFriendId, status: status},
            dataType: "json",
            beforeSend: function () {
                $("body").append(app.loads());
            },
            success: function (date) {
                if (date.status == 200) {
                    obj.parent().html('<button class="btn">已拒绝</button>');
                } else {
                    swal("请求失败！", null, "error");
                }
            },
            error: function () {
                swal("网络超时.等稍后再试!", null, "error");
            },
            complete: function () {
                $("#ibox").remove();
            }
        });
    });
});