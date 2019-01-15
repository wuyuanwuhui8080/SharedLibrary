$(function () {
    $("#goWrite_forum").click(function () {
        if (app.isNull(userName)) {
            swal("请先登录再去发布讨论哦~", null, "warning");
            return false;
        } else {
            location.href = path + "/sharediForum/goWriteInvitation";
        }
    });
});