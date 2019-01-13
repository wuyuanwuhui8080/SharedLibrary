$(function () {
    $("#goWrite_forum").click(function () {
        if(app.isNull(userName)){
            swal("请先登录再去发帖子哦~",null,"warning");
            return false;
        }else{
            location.href = path+"/sharediInvitation/goWriteInvitation";
        }
    });
});