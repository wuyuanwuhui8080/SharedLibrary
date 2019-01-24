$(function () {
    $(".goWrite_forum").click(function () {
        if (forumWirte) {
            location.href = paths + "/sharedForum/goWriteForum";
        }else{
            layer.msg("请先登录.再去发帖..", {shift: 6});
        }
    });
});