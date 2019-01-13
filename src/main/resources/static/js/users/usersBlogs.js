$(function () {

    var pro = $("body");

    /**
     * 博客点赞
     * @author 博博大人
     * @time 2018/12/29 19:24
     */
    // 获取当前点赞数
    pro.on("click", ".BtnGive", function () {
        var obj = $(this);
        var id = obj.attr("blogId");
        // 当前点赞的id
        var giveId = 0;
        // 获取当前登录的id
        var userId = $("#userId").val();
        $.ajax({
            type: "post",
            url: app.path() + "/shareBlogsGive/getGiveNum",
            data: {blogsId: id, giveId: giveId, giveUserId: userId},
            dateType: "json",
            success: function (date) {
                if (date.status == 200) {
                    obj.parent().html(' <button class="btn btn-white btn-xs BtnGiveTrue" giveId="' + date.obj.blogGiveId + '" blogId="' + date.obj.blogId + '">' +
                        ' <i class="fa fa-thumbs-up" style="color: red;"></i> <span>' + date.obj.count + '</span>人已赞 ' +
                        '  </button> <button class="btn btn-white btn-xs"><i class="fa fa-comments Mydiv"></i> 评论</button> ');
                } else {
                    swal({
                        title: date.msg,
                        type: "error"
                    });
                }
            },
            error: function () {
                swal({
                    title: "网络错误!",
                    type: "error"
                });
            }
        });
    });

    /**
     * 删除点赞记录
     * @author 博博大人
     * @time 2018/12/29 21:07
     */
    pro.on("click", ".BtnGiveTrue", function () {
        var obj = $(this);
        // 当前点赞的id
        var giveId = obj.attr("giveId");
        var blogId = obj.attr("blogId");
        // 获取当前登录的id
        var userId = $("#userId").val();

        $.ajax({
            type: "post",
            url: app.path() + "/shareBlogsGive/getGiveNum",
            data: {giveId: giveId, blogsId: blogId},
            dateType: "json",
            success: function (date) {
                if (date.status == 200) {
                    obj.parent().html(' <button class="btn btn-white btn-xs BtnGive"   blogId="' + date.obj.blogId + '">' +
                        ' <i class="fa fa-thumbs-up"></i> <span>' + date.obj.count + '</span>人赞 ' +
                        '  </button> <button class="btn btn-white btn-xs"><i class="fa fa-comments Mydiv"></i> 评论</button> ');
                } else {
                    swal({
                        title: date.msg,
                        type: "error"
                    });
                }
            },
            error: function () {
                swal({
                    title: "网络错误!",
                    type: "error"
                });
            }
        });
    });


    /**
     * 删除博客,校验只有自己才能删除
     * @author 博博大人
     * @time 2019/1/1 16:32
     */
    pro.on("click", ".delBlos", function () {
        var obj = $(this);
        var id = obj.attr("blosId");
        swal({
            title: "您确定要删除这条博客吗",
            text: "删除后将无法恢复，请谨慎操作！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            closeOnConfirm: false
        }, function () {
            $.ajax({
                type: "post",
                url: app.path() + "/shareBlogs/deleteBlos/" + id,
                dataType: "json",
                timeout: 1000,
                beforeSend: function () {
                    $("body").append(app.loads());
                },
                success: function (date) {
                    if (date.status == 200) {
                        swal("删除成功！", null, "success");
                        obj.parent().parent().parent().parent().remove();
                    } else {
                        swal("删除失败", null, "error");
                    }

                },
                complete: function () {
                    $("#ibox").remove();
                },
                error: function () {
                    swal("网络连接超时，请检查您的网络!", null, "error");
                }
            });
        });
    });

    /**
     * 用来操作普通评论
     * @author 博博大人
     * @time 2019/1/1 16:31
     */
    pro.on("click", ".btntoBlogsComm", function () {
        var obj = $(this);
        // 获取文本框的值
        var commentRetext = obj.parent().prev().children().val();
        // 获取当前登录的id
        var userId = $("#userId").val();
        // 获取当前博客id
        var blogsId = obj.attr("blogsId");
        $(".commhuifu").html()

        // obj.parent().parent().parent().parent().parent().prev().html("ssdsad")
        if (app.isNull(commentRetext)) {
            swal("内容不能为空！", null, "warning");
            return false;
        } else {
            var div = "";
            $.ajax({
                type: "post",
                url: app.path() + "/shareBlogsComment/saveBlosgComm",
                data: {blogsId: blogsId, commentRetext: commentRetext, commentUserId: userId},
                dataType: "json",
                success: function (date) {
                    if (date.status == 200) {
                        div += ' <div class="social-comment ' + date.obj.commId + '">\n' +
                            '                                    <a href="" class="pull-left">\n' +
                            '                                        <img alt="image" src="' + app.path() + '/images/' + date.obj.headImg + '">\n' +
                            '                                    </a>\n' +
                            '                                    <div class="media-body">\n' +
                            '                                        <a href="#">\n' +
                            '                                            ' + date.obj.realName + '\n' +
                            '                                        </a> ' + date.obj.blogsComm + '\n' +
                            '<a href="javascript:;" class="pull-right deleteComm"   commId="' + date.obj.commId + '">删除</a>' +
                            '                                        <br/>\n' +
                            '                                        <small class="text-muted">' + app.dateTime(date.obj.commentDate, "YY-MM-DD hh:ss:mm") + '</small>\n' +
                            '                                    </div>\n' +
                            '                                </div>';
                        obj.parent().parent().parent().parent().parent().prev().append(div);
                        obj.parent().prev().children().val("");
                    } else {
                        swal(date.msg, null, "error");
                    }
                },
                error: function () {
                    swal("请求超时！", null, "error");
                }
            });
        }
    });


    /**
     * 删除评论回复 (删除回复回吧其他回复这个人的信息全部删掉)
     * @author 博博大人
     * @time 2019/1/2 20:09
     */
    pro.on("click", ".commRelyDel", function () {
        var obj = $(this);
        // 获取当前评论id
        var repyId = obj.attr("repyId");

        // 获取当前被回复的id
        var byUserId = obj.attr("byUserId");
        // 获取当前博客评论id
        var blogCommId = obj.attr("blogCommId");
        var deleteReplyId = obj.attr("deleteReplyId");
        // var deleteId = repyId + blogCommId;
        /*   obj.parent().parents(".moveCommRigh").remove();
           $("."+deleteId+"").remove();*/
        swal({
            title: "您确定要删除这条回复吗",
            text: "删除后将无法恢复，请谨慎操作！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            closeOnConfirm: false
        }, function () {
            $.ajax({
                type: "post",
                url: app.path() + "/shareBlogsCommentReply/deleteReply/" + repyId,
                dataType: "json",
                timeout: 1000,
                beforeSend: function () {
                    $("body").append(app.loads());
                },
                success: function (date) {
                    if (date.status == 200) {
                        swal("删除成功！", null, "success");
                        obj.parent().parents(".moveCommRigh").remove();
                        var deleteId = repyId + blogCommId;
                        $("." + deleteId + "").remove();
                    } else {
                        swal("删除失败", null, "error");
                    }

                },
                complete: function () {
                    $("#ibox").remove();
                },
                error: function () {
                    swal("网络连接超时，请检查您的网络!", null, "error");
                }
            });
        });

    });

    var blog = true;
    /**
     * 初始化回复框
     * @author 博博大人
     * @time 2019/1/2 20:09
     */
    pro.on("click", ".commRelf", function () {
        var obj = $(this);
        // 被评论的id
        var byUserId = obj.attr("byreplyId");
        // 当前的用户id
        var userId = obj.attr("replyId");
        // 评论的id
        var commId = obj.attr("commId");
        // 博客的id
        var blogsId = obj.attr("blogsId");

        var reply_id = obj.attr("reply_id");

        var userRealName = obj.attr("userRealName");
        if (blog == true) {
            obj.parent().parents(".commhuifu").next().children().find(".testareafist").prepend("<span id='textareaFIstid'>回复" + userRealName + ":</span>");
            obj.parent().parents(".commhuifu").next().children().find(".btnsumitTest").html(' <button class="btn btn-info btn-circle btn-lg btntoreplyComm"  reply_id = "' + reply_id + '" byreplyId="' + byUserId + '"  replyId = "' + userId + '" commId="' + commId + '" userRealName="' + userRealName + '" \n' +
                '                                            type="button"><i\n' +
                '                                            class="fa fa-check"></i>\n' +
                '                                    </button>');
            blog = false;
        } else {
            $("#textareaFIstid").remove();
            obj.parent().parents(".commhuifu").next().children().find(".btnsumitTest").html(' <button class="btn btn-info btn-circle btn-lg btntoBlogsComm" blogsId="' + blogsId + '"\n' +
                '                                            type="button"><i\n' +
                '                                            class="fa fa-check"></i>\n' +
                '                                    </button>');
            blog = true;
        }
        /*  if(app.isNull($("#textareaFIstid").val())){
              swal("请选择回复的人！",null,"waining");
              return false;
          }else{
              alert("ss");
          }*/
    });

    /**
     * 评论回复
     * @author 博博大人
     * @time 2019/1/2 20:09
     */
    pro.on("click", ".btntoreplyComm", function () {
        var obj = $(this);
        // 被评论的id
        var byUserId = obj.attr("byreplyId");
        // 当前的用户id
        var userId = obj.attr("replyId");
        // 评论的id
        var commId = obj.attr("commId");

        // 博客id
        var blogsId = obj.attr("blogsId");

        // 获取文本框的值
        var commentRetext = obj.parent().prev().children().next().val();
        // 获取被回复者的名字
        var userRealName = obj.attr("userRealName");

        var relpyId = obj.attr("reply_id");

        if (app.isNull(commentRetext)) {
            swal("回复不能为空！", null, "warning");
            return false;
        } else {
            var div = "";
            $.ajax({
                url: app.path() + "/shareBlogsCommentReply/saveReply",
                type: "post",
                data: {
                    commentId: commId,
                    commentUserId: userId,
                    commentRetext: commentRetext,
                    commentByuserId: byUserId,
                    byRealName: userRealName,
                    blogsId: blogsId,
                    commReplyId: relpyId
                },
                timeout: 1000,
                dataType: "json",
                success: function (date) {
                    if (date.status == 200) {
                        obj.parent().parent().parent().parent().parent().prev().find("." + date.obj.commId + "").children().next().next().append('' +
                            '                                           <div class="social-comment moveCommRigh '+date.obj.commReplyId+date.obj.commId+'">\n' +
                            '                                                <a href="" class="pull-left">\n' +
                            '                                                    <img alt="image"\n' +
                            '                                                         src="' + app.path() + '/images/' + date.obj.headImg + '">\n' +
                            '                                                </a>\n' +
                            '                                                <div class="media-body">\n' +
                            '                                                    <a href="#"> ' + date.obj.realName + '</a><span\n' +
                            '                                                        style="color: #999999;"> 回复 </span><a\n' +
                            '                                                        href="#">' + date.obj.byRealName + '</a>\n' +
                            '                                                    <span class="moveChen">\n' +
                            '                                                         : ' + date.obj.commentRetext + '\n' +
                            '                                                    \n' +
                            '                                           <a href="javascript:;" class="pull-right commRelyDel"\n' +
                            '                                                            repyId="' + date.obj.relpyId + '"\n' +
                            '                                                              byUserId="' + date.obj.byUsersId + '"\n' +
                            '                                                            blogCommId="' + date.obj.commId + '">删除</a>' +
                            '                                                    </span>\n' +
                            '\n' +
                            '                                                    <br/>\n' +
                            '                                                    <small class="text-muted">' + app.dateTime(date.obj.creatDate, "YY-MM-DD hh:mm:ss") + '</small>\n' +
                            '                                              </div>\n' +
                            '                                           </div>'
                        )
                        ;
                        obj.parent().prev().children().val("");
                        $("#textareaFIstid").remove();
                        obj.parent().html(' <button class="btn btn-info btn-circle btn-lg btntoBlogsComm" blogsId="' + blogsId + '"\n' +
                            '                                            type="button"><i\n' +
                            '                                            class="fa fa-check"></i>\n' +
                            '                                    </button>');
                        blog = true;
                    } else {
                        swal("回复失败！", null, "error");
                    }
                },
                error: function () {
                    swal("网络连接超时，请稍后再试！", null, "error");
                }
            });
        }
    });

    /**
     * 删除评论 删除评论需要把子回复全部删除掉
     * @author 博博大人
     * @time 2019/1/2 20:19
     */
    pro.on("click", ".deleteComm", function () {
        var obj = $(this);
        var commId = obj.attr("commId");
        swal({
            title: "您确定要删除这条评论吗",
            text: "删除后将无法恢复，请谨慎操作！",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "删除",
            closeOnConfirm: false
        }, function () {
            $.ajax({
                type: "post",
                url: app.path() + "/shareBlogsComment/deleteblogComm/" + commId,
                dataType: "json",
                timeout: 1000,
                beforeSend: function () {
                    $("body").append(app.loads());
                },
                success: function (date) {
                    if (date.status == 200) {
                        swal("删除成功！", null, "success");
                        obj.parent().parents("." + commId + "").remove();
                    } else {
                        swal("删除失败", null, "error");
                    }

                },
                complete: function () {
                    $("#ibox").remove();
                },
                error: function () {
                    swal("网络连接超时，请检查您的网络!", null, "error");
                }
            });
        });
    });

    /**
     * 点击加载更多   执行
     * @author 博博大人
     * @time 2019/1/3 10:12
     */
    pro.on("click", ".gomany", function () {
        // 获取当前登录用户id
        var userId = $("#userId").val();
        var pageIndex = $("#pageIndex").val();
        pageIndex = parseInt(pageIndex) + 1;
        $.ajax({
            type: "get",
            url: app.path() + "/shareBlogs/toManyList/" + userId + "/" + pageIndex,
            dataType: "json",
            beforeSend: function () {
                $("body").append(app.loads());
            },
            success: function (date) {
                if (date.status == 200) {
                    var div = "";
                    // 获取list长度
                    var lenth = date.list.length;
                    // 循环赋值
                    if (lenth != 0) {
                        // 循环外部博客
                        for (var i = 0; i < lenth; i++) {
                            div += '<div class="social-feed-box">\n' +
                                '                <div class="pull-right social-action dropdown">\n';
                            // 判断当前博客是否和当前登录的用户一致 一致的话就有删除框
                            if (userId == date.list[i].blosUserId) {
                                div += ' <button data-toggle="dropdown" class="dropdown-toggle btn-white"> ' +
                                    '   <i class="fa fa-angle-down"></i>\n' +
                                    '     </button>\n' +
                                    '   <ul class="dropdown-menu m-t-xs">     ' +
                                    '<li><a href="javascript:;" class="delBlos" blosId="' + date.list[i].blosId + '">删除当前博客</a>' +
                                    '</li> ' +
                                    '</ul>';
                            }
                            div += '                </div>\n' +
                                '                <div class="social-avatar">\n' +
                                '                    <a href="" class="pull-left">\n' +
                                '                        <img alt="image" src="' + app.path() + '/images/' + date.list[i].blosUserImg + '">\n' +
                                '                    </a>\n' +
                                '                    <div class="media-body">\n' +
                                '                        <a href="#">\n' +
                                '                           ' + date.list[i].blosUserRealName + '\n' +
                                '                        </a>\n' +
                                '                        <small class="text-muted">' + app.dateTime(date.list[i].blosDate, "YY-MM-DD hh:mm:ss") + '</small>\n' +
                                '                    </div>\n' +
                                '                </div>\n' +
                                '                <div class="social-body">\n' +
                                '                    <p>    ' + date.list[i].blosContent + '    </p> ' +
                                ' <div class="btn-group"> ' +
                                '<div class="fistDivOk">';
                            // 判断点赞数是否为0
                            if (date.list[i].giveCount != 0) {
                                // 把当前的点赞集合赋值给变量
                                var giveList = date.list[i].blogsGiveBOList;
                                // 循环点赞集合
                                for (var b = 0; b < giveList.length; b++) {
                                    // 如果当前点赞的id等于登录的用户id 就把按钮变红
                                    if (giveList[b].giveUserid == userId) {
                                        div += '<button class="btn btn-white btn-xs BtnGiveTrue " blogId="' + date.list[i].blosId + '"\n' +
                                            '         name="btnFist" giveId="' + giveList[b].giveId + '"\n' +
                                            '         giveCount="' + date.list[i].giveCount + '">\n' +
                                            '            <i class="fa fa-thumbs-up" style="color: red;"></i> ' +
                                            '   <span>' + date.list[i].giveCount + '</span>人已赞\n' +
                                            '  </button>\n';
                                        continue;
                                    } else { // 如果不是就没有点过赞就是普通颜色
                                        if (b + 1 == giveList.length) {
                                            div += '                                        <button class="btn btn-white btn-xs BtnGive" name="btnFist"\n' +
                                                '                                                blogId="' + date.list[i].blosId + '">\n' +
                                                '                                            <i class="fa fa-thumbs-up"></i> <span>' + date.list[i].giveCount + '</span>人赞\n' +
                                                '                                        </button>\n';
                                        }

                                    }
                                }
                                // 沒有点赞数
                            } else {
                                div += '                            <button class="btn btn-white btn-xs BtnGive" blogId="' + date.list[i].blosId + '" name="btnFist">\n' +
                                    '                                <i class="fa fa-thumbs-up"></i> <span>0</span>人赞 </button>\n';
                            }
                            div += '                            <button class="btn btn-white btn-xs"><i class="fa fa-comments Mydiv"></i> 评论</button>\n' +
                                '                        </div>\n' +
                                '                    </div>\n' +
                                '                </div>\n' +
                                '                <div class="social-footer commhuifu">\n';
                            // 判断回复集合是否为空，如果为空就代表当前没有回复
                            if (app.isNotNull(date.list[i].blogsCommBOList)) {
                                // 把集合值赋值给变量
                                var rowList = date.list[i].blogsCommBOList;
                                // 进行时间排序
                                rowList.sort(function (a, b) {
                                    return new Date(a.blosCommDate).getTime() - new Date(b.blosCommDate).getTime();
                                });
                                // 循环回复集合
                                for (var c = 0; c < rowList.length; c++) {
                                    div += '                                <div class="social-comment ' + rowList[c].blosCommId + '">\n' +
                                        '                                    <a href="" class="pull-left">\n' +
                                        '                                        <img alt="image" src="' + app.path() + '/images/' + rowList[c].blosCommUsersImg + '">\n' +
                                        '                                    </a>\n' +
                                        '                                    <div class="media-body bodyCLass">\n' +
                                        '                                        <a href="#">\n' +
                                        '                                           ' + rowList[c].blosCommUsersRealName + '\n' +
                                        '                                        </a>:\n' +
                                        '                                        ' + rowList[c].blosCommRetext + '<span>\n';
                                    // 判断当前回复是否是自己的，如果是自己的就不显示回复
                                    if (userId != rowList[c].blosCommUsersId) {
                                        div += ' <a href="javascript:;" style="margin-left: 10px;"\n' +
                                            '    byreplyId="' + rowList[c].blosCommUsersId + '"\n' +
                                            '    replyId="' + userId + '"\n' +
                                            '    commId="' + rowList[c].blosCommId + '"\n' +
                                            '    blogsId="' + date.list[i].blosId + '"\n' +
                                            '    userRealName="' + rowList[c].blosCommUsersRealName + '"\n' +
                                            '    class="pull-right commRelf">回复</a>\n';
                                    }
                                    // 判断当前评论是否是自己的，或者这个博客是否是自己的
                                    if (userId == rowList[c].blosCommUsersId || userId == date.list[i].blosUserId) {
                                        div += '  <a href="javascript:;" class="pull-right deleteComm"\n' +
                                            '        commId="' + rowList[c].blosCommId + '">删除</a>\n';
                                    }
                                    div += '                                        </span>\n' +
                                        '                                        <br/>\n' +
                                        '                                        <small class="text-muted">' + app.dateTime(rowList[c].blosCommDate, "YY-MM-DD hh:mm:ss") + '</small>\n  ' +
                                        '  </div>\n' +
                                        '                                    <div class="mydivcomm">\n';
                                    // 判断评论回复是否为空
                                    if (app.isNotNull(date.list[i].blosCommReplyBOList)) {
                                        // 把评论集合赋值给变量
                                        var replyList = date.list[i].blosCommReplyBOList;
                                        // 根据时间排序
                                        replyList.sort(function (a, b) {
                                            return new Date(a.blosCommReplyDate).getTime() - new Date(b.blosCommReplyDate).getTime();
                                        });
                                        // 定义一个变量，因为js和freemarker有点不同，但是为了保证数据的一致性，定义了一个布尔变量
                                        var blog = false;
                                        // 循环回复集合
                                        for (var d = 0; d < replyList.length; d++) {
                                            // 判断当前的博客评论id 和 回复表的评论id是否一致，一致的话就拼接
                                            if (replyList[d].commReplyBlosId == rowList[c].blosCommId) {
                                                div += ' <div class="social-comment moveCommRigh ' + replyList[d].commentByuserId + rowList[c].blosCommId + '">\n' +
                                                    '                                                <a href="" class="pull-left">\n' +
                                                    '                                                    <img alt="image"\n' +
                                                    '                                                         src="' + app.path() + '/images/' + replyList[d].blosCommReplyUsersImg + '">\n' +
                                                    '                                                </a>\n' +
                                                    '                                                <div class="media-body">\n' +
                                                    '                                                    <a href="#">' + replyList[d].blosCommReplyUsersRealName + '</a><span\n' +
                                                    '                                                        style="color: #999999;"> 回复 </span><a\n' +
                                                    '                                                        href="#">' + replyList[d].commentByuserRealName + '</a>\n' +
                                                    '                                                    <span class="moveChen">\n' +
                                                    '                                                         : ' + replyList[d].blosCommReplyRetext + '\n';
                                                // 判断当前评论回复的用户id是否和当前登录的id一致，不一致的时候可以回复
                                                if (userId != replyList[d].blosCommReplyUsersId) {
                                                    div += ' <a href="javascript:;" style="margin-left: 10px;"\n' +
                                                        '        class="pull-right commRelf"\n' +
                                                        '        byreplyId="' + replyList[d].blosCommReplyUsersId + '"\n' +
                                                        '        replyId="' + userId + '"\n' +
                                                        '        commId="' + rowList[c].blosCommId + '"\n' +
                                                        '        blogsId="' + date.list[i].blosId + '"\n' +
                                                        '        userRealName="' + replyList[d].blosCommReplyUsersRealName + '">回复</a>\n';
                                                }
                                                // 判断当前用户id是否和评论回复id一致，一致的时候就代表当前的评论是自己的，就可以删除
                                                if (userId == replyList[d].blosCommReplyUsersId) {
                                                    div += '  <a href="javascript:;" class="pull-right commRelyDel"\n' +
                                                        '           repyId="' + replyList[d].blosCommReplyId + '"\n' +
                                                        '           byUserId="' + replyList[d].blosCommReplyUsersId + '"\n' +
                                                        '           blogCommId="' + rowList[c].blosCommId + '">删除</a>\n';
                                                    // 如果不一致就判断
                                                } else {
                                                    // 判断长度，因为有些评论是没有回复，然后js就取不到值，会报错，所以我们进行判断
                                                    if (date.list[i] != null && date.list.length != 0 || blog == true) {
                                                        // 如果当前的评论回复和当前登录id相同或者设置的变量是true就有删除按钮
                                                        if (blog == true || date.list[i].blosUserId == userId) {
                                                            blog = true;
                                                            div += '   <a href="javascript:;" class="pull-right commRelyDel"\n' +
                                                                '          repyId="${blosCommReply.blosCommReplyId}"\n' +
                                                                '          byUserId="${blosCommReply.blosCommReplyUsersId}"\n' +
                                                                '           blogCommId="${blogsComm.blosCommId}">删除</a>\n';
                                                        }
                                                    }
                                                }

                                                div += '                                                    </span>\n' +
                                                    '\n' +
                                                    '                                                    <br/>\n' +
                                                    '                                                    <small class="text-muted">' + app.dateTime(replyList[d].blosCommReplyDate, "YY-MM-DD hh:mm:ss") + '</small>\n' +
                                                    '                                                </div>\n' +
                                                    '                                            </div>';
                                            }
                                        }
                                    }
                                    div += ' </div>\n' +
                                        '                                </div>';
                                }
                            }
                            div += '  ' +
                                '      </div>\n' +
                                '                <div class="social-footer">\n' +
                                '                    <div class="social-comment">\n' +
                                '                        <a href="" class="pull-left">\n' +
                                '                            <img alt="image" src="' + app.path() + '/images/' + userHeadImg + '">\n' +
                                '                        </a>\n' +
                                '                        <div class="media-body">\n' +
                                '                            <div class="row">\n' +
                                '                                <div class="col-sm-11 testareafist">\n' +
                                '                                    <textarea style="resize:none" class="form-control" placeholder="填写评论..."></textarea>\n' +
                                '                                </div>\n' +
                                '                                <div class="col-sm-1 btnsumitTest">\n' +
                                '                                    <button class="btn btn-info btn-circle btn-lg btntoBlogsComm" blogsId="' + date.list[i].blosId + '"\n' +
                                '                                            type="button"><i  class="fa fa-check"></i>\n' +
                                '                                    </button>\n' +
                                '                                </div>\n' +
                                '                            </div>\n' +
                                '                        </div>\n' +
                                '                    </div>\n' +
                                '                </div>\n' +
                                '            </div>\n' +
                                '</div>' +
                                '</div>\n' +
                                '    </div>';
                        }
                        // 查询不到数据
                    } else {
                        div += ' <h3 class="text-center">暂时没有博客动态...</h3>\n';
                    }
                    // 先把加载更多的按钮删掉
                    $(".gomany").remove();
                    // 把拼接的元素拼接上
                    $(".fistClassBlosg").append(div);
                    // 判断长度是否大于4，大于4就显示加载更多
                    if (lenth > 4) {
                        $(".fistClassBlosg").append('<button class="btn btn-primary btn-block gomany" ><i class="fa fa-arrow-down"></i>\n' +
                            '         显示更多\n' +
                            '     </button>');
                    }
                    // 更新起始页
                    $("#pageIndex").val(pageIndex);
                } else {
                    swal("数据获取失败！", null, "error");
                }
            },
            complete: function () {
                $("#ibox").remove();
            },
            error: function () {
                swal("网络连接超时！", null, "error");
            }
        });
    });

});
