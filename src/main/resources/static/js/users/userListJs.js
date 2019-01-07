$(function () {

    var pro = $("body");

    /**
     * 删除用户
     * @author 博博大人
     * @time 2019/1/6 21:35
     */
    pro.on("click",".del",function () {
        var obj = $(this);
        var userId = obj.attr("userId");
        if (confirm("您确定要删除该用户吗?")) {
            $.ajax({
                type: "post",
                url: path + "/sharedUsers/deleteUsers/" + userId,
                dataType: "json",
                timeout: 1000,
                beforeSend: function () {
                    $("body").append(app.loads());
                },
                success: function (date) {
                    if (date.status == 200) {
                        obj.parent().parent().remove();
                    } else {
                        swal(date.msg, null, "error");
                    }

                },
                complete: function () {
                    $("#ibox").remove();
                },
                error: function () {
                    swal("网络连接超时，请检查您的网络!", null, "error");
                }
            });
        }
    })

    /**
     * 用户搜索
     * @author 博博大人
     * @time 2019/1/6 21:35
     */
    pro.on("click",".sarchSumitUser",function () {
        // 获取下拉框的值
        var position = $(".selectClass").val();
        // 获取文本框的值
        var name = $(".RealnameOrUsername").val();

        $.ajax({
            type: "get",
            url: path + "/sharedUsers/jsonUserList",
            data: {name: name, position: position},
            dataType: "json",
            beforeSend: function () {
                $("body").append(app.loads());
            },
            success: function (date) {
                // 循环查询出来的值
                var div = "";
                if(date.obj.page.list.length > 0){
                    for (var i = 0; i < date.obj.page.list.length; i++) {
                        div += '<tr>\n' +
                            '    <td>' + date.obj.page.list[i].userName + '</td>\n' +
                            '    <td>' + date.obj.page.list[i].realName + '</td>\n' +
                            '    <td>' + date.obj.page.list[i].phone + '</td>\n' +
                            '    <td>' + date.obj.page.list[i].positionName + '</td>\n' +
                            '    <td>' + app.dateTime(date.obj.page.list[i].creationDate, "YY-MM-DD hh:mm:ss") + '</td>\n' +
                            '    <td><a href="' + path + '/sharedUsers/showUsers/' + date.obj.page.list[i].id + '">查看</a> <a\n' +
                            '             href="javascript:;" class="del" userId="' + date.obj.page.list[i].id + '">删除</a>\n' +
                            '             <a href="' + path + '/sharedUsers/goUpdate/' + date.obj.page.list[i].id + '">修改</a></td>\n' +
                            '    </tr>';
                    }
                }else{
                    div += '<tr>\n' +
                        '    <td colspan="6" align="center">暂无数据！</td>\n' +
                        '    </tr>';
                }
                $(".tableTbody").html(div);
            },
            error: function () {

            },
            complete: function () {
                $("#ibox").remove();
            }
        });

        $(".tableTbody").html("");
        return false;
    });

});