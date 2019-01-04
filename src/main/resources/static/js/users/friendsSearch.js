$(function () {
    $("#userSearch").click(function () {
        var name = $("#baidu").val();
        var div = "";
        $.ajax({
            type: "get",
            url: app.path() + "/sharedFriends/searchFriendList",
            data: {name: name, userId: userid},
            dataType: "json",
            beforeSend: function () {
                $("body").append(app.loads());
            },
            success: function (date) {
                if (date.list.length > 0) {
                    for (var i = 0; i < date.list.length; i++) {
                        div += '<div class="col-sm-4">\n' +
                            '                <div class="contact-box">\n' +
                            '                    <a href="' + app.path() + '/sharedUsers/lookProfile/' + date.list[i].id + '">\n' +
                            '                        <div class="col-sm-4">\n' +
                            '                            <div class="text-center">\n' +
                            '                                <img alt="image" class="img-circle m-t-xs img-responsive"\n' +
                            '                                     src="' + app.path() + '/images/' + date.list[i].headImg + '">\n' +
                            '                                <div class="m-t-xs font-bold"></div>\n' +
                            '                            </div>\n' +
                            '                        </div>\n' +
                            '                        <div class="col-sm-8">\n' +
                            '                            <h3><strong>' + date.list[i].realName + '</strong></h3>\n' +
                            '                            <address>\n' +
                            '                                <strong>个性签名</strong><br>\n' +
                            '                                ' + date.list[i].individual + '\n' +
                            '                                <br>\n' +
                            '                            </address>\n' +
                            '                        </div>\n' +
                            '                        <div class="clearfix"></div>\n' +
                            '                    </a>\n' +
                            '                </div>\n' +
                            '            </div>';
                    }
                    $("#MyfendList").html(div);
                }
            },
            error: function () {
                swal({
                    title: "服务器发生错误！",
                    type: "error"
                });
            },
            complete: function () {
                $("#ibox").remove();
            }
        });
        return false;
    });
});