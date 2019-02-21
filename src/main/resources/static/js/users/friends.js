$(function () {
    var falg = true;
    /**
     * 查看用户资料
     * @author 博博大人
     * @time 2018/12/29 19:23
     */
    $("#tbodyId").on("click", ".userName", function () {
        var obj = $(this);
        if (falg) {
            $("#MyFriendsFIst").attr("class", "col-sm-7");
            $("#FrendsUsers").show();
            var userName = obj.attr("userName");
            var div = "";
            var userMsg = obj.attr("userMsg");
            $.ajax({
                type: "get", // 请求类型
                beforeSend: function () { // ajax请求开始的时候做的事情
                    $("#tbodyId").append(app.loads());
                },
                data: {userName: userName}, // 传的参数
                url: app.path() + "/sharedFriends/userDetails", // 请求的地址
                dataType: "json", // 返回类型
                success: function (date) { // date 返回的东西
                    div += '<div id="contact-1" class="tab-pane active">\n' +
                        '                            <div class="row m-b-lg">\n' +
                        '                                <div class="col-lg-4 text-center">\n' +
                        '                                    <h2>' + date.realName + '</h2>\n' +
                        '\n' +
                        '                                    <div class="m-b-sm">\n' +
                        '                                        <img alt="image" class="img-circle" src="' + app.path() + '/images/' + date.headImg + '" style="width: 62px">\n' +
                        '                                    </div>\n' +
                        '                                </div>\n' +
                        '                                <div class="col-lg-8">\n' +
                        '                                    <h3>\n' +
                        '                                        关于我\n' +
                        '                                    </h3>\n' +
                        '\n' +
                        '                                    <p>\n' +
                        '                                       ' + date.individual + '\n' +
                        '                                    </p>\n' +
                        '                                    <br>\n';
                    if (userMsg == 1) {
                        div += '                                    <button type="button" onclick="goMyIndex();" class="btn btn-primary btn-sm btn-block"><i\n' +
                            '                                            class="fa fa-envelope"></i> 查看自己的资料\n' +
                            '                                    </button>\n';

                    } else if (userMsg == 2) {
                        div += '                                    <button type="button" onclick="goSendMsg ();" class="btn btn-primary btn-sm btn-block"><i\n' +
                            '                                            class="fa fa-envelope"></i> 发送消息\n' +
                            '                                    </button>\n';
                    } else if (userMsg == 3) {
                        div += '                                    <button type="button" id="AddFriends" class="btn btn-primary btn-sm btn-block"><i\n' +
                            '                                            class="fa fa-envelope"></i> 发送好友请求\n' +
                            '                                    </button>\n';
                    }
                    div += '                                </div>\n' +
                        '                            </div>\n' +
                        '                            <div class="client-detail">\n' +
                        '                                <div class="full-height-scroll">\n' +
                        '\n' +
                        '                                    <strong>详细信息</strong>\n' +
                        '\n' +
                        '                                   <br /> <ul class="list-group clear-list">\n' +
                        '                                        <li class="list-group-item fist-item">\n' +
                        '                                             <strong>用户名:</strong> ' + date.userName + '\n' +
                        '                                        </li>\n' +
                        '                                         <li class="list-group-item fist-item">\n' +
                        '                                             <strong>年龄:</strong> ' + date.age + '\n' +
                        '                                        </li>\n' +
                        '                                         <li class="list-group-item fist-item">\n' +
                        '                                            <strong> 生日:</strong> ' + app.dateTime(date.birthday, "YY-MM-DD") + '\n' +
                        '                                        </li>\n' +
                        '                                    </ul>\n' +
                        '                                </div>\n' +
                        '                            </div>\n' +
                        '                        </div>';
                    $("#UserDivId").html(div);

                    // 发送好友请求
                    $("#UserDivId").on("click", "#AddFriends", function () {
                        var userId = $("#userId").val();
                        $.ajax({
                            type: "get", // 请求类型
                            beforeSend: function () { // ajax请求开始的时候做的事情
                                $("#tbodyId").append(app.loads());
                            },
                            data: {meId: userId, requestId: date.id}, // 传的参数
                            url: app.path() + "/sharedlFriendRequest/toFriendsRTS", // 请求的地址
                            dataType: "json", // 返回类型
                            success: function (date) { // date 返回的东西
                                if (date.status == 200) {
                                    swal({
                                        title: "发送好友请求成功！",
                                        type: "success"
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
                    })
                },
                complete: function () { // 不管请求成功还是失败都会执行
                    $("#ibox").remove();
                    falg = false;
                },
                error: function () { // 请求失败的操作
                    swal({
                        title: "服务器请求错误！",
                        type: "error"
                    });
                }
            });
        } else if (falg == false) {
            $("#MyFriendsFIst").attr("class", "col-sm-12");
            $("#FrendsUsers").hide();
            falg = true;
        }
    });

    /**
     * 查询用户
     * @author 博博大人
     * @time 2018/12/29 19:23
     */
    $("#sachFriendsSumit").click(function () {
        var name = $("#firendName").val();
        if (app.isNull(name)) {
            swal({
                title: "查询的好友不能为空",
                type: "error"
            });
            return false;
        } else {
            $.ajax({
                type: "post", // 请求类型
                beforeSend: function () { // ajax请求开始的时候做的事情
                    $("#tbodyId").append(app.loads());
                },
                data: {name: name}, // 传的参数
                url: app.path() + "/sharedFriends/searchFriend", // 请求的地址
                dataType: "json", // 返回类型
                timeout: 1000,
                success: function (date) { // date 返回的东西
                    var tr = "";
                    $("#tbodyId").html("");
                    if (date.status == 200) {
                        if (date.list.length > 0) {
                            for (var i = 0; i < date.list.length; i++) {
                                tr += '<tr>\n' +
                                    '  <td class="client-avatar">\n' +
                                    ' <img alt="image" src="' + app.path() + '/images/' + date.list[i].headImg + '">\n' +
                                    '   \n' +
                                    '   </td>\n' +
                                    '    <td><a data-toggle="tab" userName="' + date.list[i].userName + '" userMsg = "' + date.list[i].msg + '"  class="userName" href="#contact-4"\n' +
                                    '                                                       class="client-link">' + date.list[i].realName + '</a>\n' +
                                    '  </td>\n' +
                                    '  <td>' + date.list[i].userName + '</td>\n' +
                                    '     <td class="contact-type"><i class="fa fa-phone"> </i>\n' +
                                    '    </td>\n' +
                                    '   <td> ' + date.list[i].phone + '</td>\n';
                                if (date.list[i].msg == 1) {
                                    tr += '     <td class="client-status"><span class="label label-warning">自己</span>\n' +
                                        '     </td>\n' +
                                        '       </tr>';
                                } else if (date.list[i].msg == 2) {
                                    tr += '     <td class="client-status"><span class="label label-warning">已是您的好友</span>\n' +
                                        '     </td>\n' +
                                        '       </tr>';
                                } else if (date.list[i].msg == 3) {
                                    tr += '     <td class="client-status"><span class="label label-primary">点击用户名添加哦</span>\n' +
                                        '     </td>\n' +
                                        '       </tr>';
                                }
                            }
                        } else {
                            tr += '<tr>\n' +
                                '  <td class="client-avatar" colspan="4" align="center">\n' +
                                ' 没有此用户哦...</td>';
                        }
                        $("#fendIDFriend").html(date.list.length + "个好友");
                        $("#tbodyId").html(tr);
                    } else {
                        swal("查询失败！", null, "error");
                    }

                },
                complete: function () { // 不管请求成功还是失败都会执行
                    $("#ibox").remove();
                },
                error: function () { // 请求失败的操作
                    swal({
                        title: "请求超时，请检查网络！",
                        type: "error"
                    });
                }
            });
        }
    });
});

function goSendMsg() {
    location.href = app.path() + "/sharedFriends/goChar/" + userId;
}

function goMyIndex() {
    var userId = document.getElementById("userId").value;
    location.href = app.path() + "/sharedUsers/lookProfile/" + userId;
}

