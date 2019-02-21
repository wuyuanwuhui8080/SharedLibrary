var layer = layui.layer;
$(function () {
    /**
     * 刪除单条消息
     */
    $(".deleteMessage").click(function () {
       var obj = $(this);
       var msg = obj.attr("message");
       var date = obj.attr("messageDate");

       layer.confirm("确认要删除吗?",function (index) {
           layer.close(index);
           app.loadJson(paths+"/sharedForum/deleteMessage/"+msg+"/"+date+"/"+userName,null,function (date) {
               layer.msg("删除成功..");
               obj.parent().parent().remove();
           });
       });
    });

    /**
     * 清空所有消息
     */
    $("#LAY_delallmsg").click(function () {
        layer.confirm("确认要清空全部消息吗？",function (index) {
            layer.close(index);
            app.loadJson(paths+"/sharedForum/deleteAll/"+userName,null,function (date) {
                layer.msg("清空成功..");
                $(".classLiUl").html('<div class="fly-none">您暂时没有最新消息</div>');
            });
        });
    });
});