var layer = layui.layer;
$(function () {

    var pro = $("body");

    /**
     * 取消点赞
     * @author 博博大人
     * @time 2019/1/21 9:43
     */
    pro.on("click", ".CommentGive", function () {
        // 未登录
        if (falg == false) {
            layer.msg("请先登录，在进行点赞!", {shift: 6});
            return false;
        } else {
            var obj = $(this);
            // 当前帖子评论的id
            var commentId = obj.children().attr("commentId");
            // 获取当前点赞的id
            var giveId = obj.children().attr("giveId");
            // 状态
            var type = 1;
            var commentGive = {};
            commentGive.userId = userId;
            commentGive.forumCommentId = commentId;
            commentGive.id = giveId;
            $.ajax({
                url: path + "/sharedForumCommentGive/commentGive",
                type: "post",
                data: {giveJson: JSON.stringify(commentGive), type: type},
                dataType: "json",
                success: function (date) {
                    if (date.status == 200) {
                        var div = '<span class="jieda-zan zanok NoCommentGive"> <i class="iconfont icon-zan" commentId="' + date.obj.commentId + '" style="color: #999999;"></i>\n' +
                            '                                                            <em>' + date.obj.cuount + '</em>\n' +
                            '                                                          </span>';
                        obj.parent().html(div);
                    } else {
                        layer.msg("点赞失败！", {shift: 6});
                    }
                },
                error: function () {
                    layer.msg("网络连接超时！", {shift: 6});
                }
            });
        }
    });


    /**
     *  点赞
     * @author 博博大人
     * @time 2019/1/21 9:42
     */
    pro.on("click", ".NoCommentGive", function () {
        // 未登录
        if (falg == false) {
            layer.msg("请先登录，在进行点赞!", {shift: 6});
            return false;
        } else {
            var obj = $(this);
            // 当前帖子评论的id
            var commentId = obj.children().attr("commentId");
            // 状态
            var type = 0;
            var commentGive = {};
            commentGive.userId = userId;
            commentGive.forumCommentId = commentId;
            $.ajax({
                url: path + "/sharedForumCommentGive/commentGive",
                type: "post",
                data: {giveJson: JSON.stringify(commentGive), type: type},
                dataType: "json",
                success: function (date) {
                    if (date.status == 200) {
                        var div = '<span class="jieda-zan zanok CommentGive"> <i class="iconfont icon-zan" commentId="' + date.obj.commentId + '" giveId="' + date.obj.giveId + '"   style="color: red;"></i>\n' +
                            '                                                            <em>' + date.obj.cuount + '</em>\n' +
                            '                                                          </span>';
                        obj.parent().html(div);
                    } else {
                        layer.msg("点赞失败！", {shift: 6});
                    }
                },
                error: function () {
                    layer.msg("网络连接超时！", {shift: 6});
                }
            });
        }
    });

    var userNameArray = new Array();

    /**
     * 回复的时候操作
     */
    $(".CommentSpan").click(function () {
        if (falg == false) {
            layer.msg("请先登录，在进行回复!", {shift: 6});
            return false;
        }
        var obj = $(this);
        var val = $("#L_content").val();
        var userName = obj.attr("commentuserlName");
        var aite = '@' + userName + ''.replace(/\s/g, '');
        $("#L_content").focus();
        // if (val.indexOf(aite) != -1) return;
        if (val.indexOf(aite) >= 0) {
            return;
        }
        $("#L_content").val(aite + ' ' + val);
    });

    /**
     * 点击提交回复的时候操作
     */
    $("#sumitComment").click(function () {
        if (falg == false) {
            layer.msg("请先登录，在进行回复!", {shift: 6});
            return false;
        } else {
            var fly = layui.fly;
            var obj = $(this);
            // 帖子id
            var forumId = obj.attr("forumId");
            // 回复内容
            var html = $("#L_content").val();

            var forumUserId = obj.attr("forumUserId");

            var context = fly.content(html)
            var loadIndex = null;

          var ss =   fly.escape(html || '').replace(/@(\S+)(\s+?|$)/g, '@<a href="javascript:;" class="fly-aite">$1</a> $2');
          $("#RelpyTest").html(ss);

            $("#RelpyTest .fly-aite").each(function (index) {
               alert('下标 '+$(this).html()+" "+index)
            });
           /* $.ajax({
                type: "post",
                url: path + "/sharedForumComment/saveComment",
                data: {userId: userId, content: context, forumId: forumId},
                dataType: "json",
                beforeSend: function () {
                    loadIndex = layer.load(1, {shade: 0.8});
                },
                success: function (date) {
                    if (date.status == 200) {
                        var div = '<li data-id="111" class="jieda-daan">\n' +
                            '                                <a name="item-1111111111"></a>\n' +
                            '                                <div class="detail-about detail-about-reply">\n' +
                            '                                    <a class="fly-avatar" href="">\n' +
                            '                                        <img src="' + path + '/images/' + headImg + '" alt="' + realName + '"\n' +
                            '                                             alt=" ">\n' +
                            '                                    </a>\n' +
                            '                                    <div class="fly-detail-user">\n' +
                            '                                        <a href="" class="fly-link">\n' +
                            '                                            <cite>' + realName + "(" + userName + ")" + '</cite>\n' +
                            '                                        </a>\n' +
                            '                                        <span>\n';
                        if (forumUserId == userId) {
                            div += '                                                (楼主)\n';
                        } else if (positionId == 2) {
                            div += '                                                    <span style="color:#FF9E3F">（帖子管理员）</span>\n';
                        } else if (positionId == 3) {
                            div += '                                                    <span style="color:#5FB878">(系统管理员)</span>\n'
                        }
                        div += '                                            </span>\n' +
                            '                                    </div>\n' +
                            '                                    <div class="detail-hits">\n' +
                            '                                        <span>' + app.dateTime(date.obj.creationDate, "YY-MM-DD hh:mm:ss") + '</span>\n' +
                            '                                    </div>\n' +
                            '                                </div>\n' +
                            '                                <div class="detail-body jieda-body photos">\n' +
                            '                                    <p>' + date.obj.content + '</p>\n' +
                            '                                </div>\n' +
                            '                                <div class="jieda-reply">\n' +
                            '                                    <span class="GiveDIv">\n' +
                            '                                                                  <span class="jieda-zan zanok NoCommentGive">\n' +
                            '                                                              <i class="iconfont icon-zan" commentId="${li.commentId}"\n' +
                            '                                                                 style="color: #999999;"></i>\n' +
                            '                                                                       <em>0</em>\n' +
                            '                                                                  </span>\n' +
                            '                                    </span>\n' +
                            '                                    <div class="jieda-admin">\n' +
                            '                                        <span type="del">删除</span>\n' +
                            '                                    </div>\n' +
                            '                                </div>\n' +
                            '                            </li>';
                        $("#jieda").append(div);
                        $("#L_content").val("");
                    } else {
                        layer.msg(date.msg);
                    }
                },
                error: function () {
                    layer.msg("网络连接超时...", {shift: 6});
                },
                complete: function () {
                    layer.close(loadIndex);
                }

            });*/
        }
    });


    /**
     * 删除评论
     * @author 博博大人
     * @time 2019/1/21 21:53
     */
    $(".commentDel").click(function () {
        var obj = $(this);
        var commentId = obj.attr("commentId");

        var loadIndex = null;
        layer.confirm("确认要删除这条回复吗？", function (index) {
            layer.close(index);
            $.ajax({
                type: "post",
                url: path + "/sharedForumComment/deleteComment",
                data: {commentId: commentId},
                dataType: "json",
                beforeSend: function () {
                    loadIndex = layer.load(1, {shade: 0.8});
                },
                success: function (date) {
                    if (date.status == 200) {
                        obj.parent().parents("#comm" + commentId + "").remove()
                    } else {
                        layer.msg(date.msg, {shift: 6});
                    }
                },
                complete: function () {
                    layer.close(loadIndex);
                },
                error: function () {
                    layer.msg("网络连接超时....", {shift: 6});
                }
            });
        })

    });


    /**
     * 删除帖子
     * @author 博博大人
     * @time 2019/1/22 15:24
     */
    $("#deleteForum").click(function () {
        var obj = $(this);
        var forumId = obj.attr("froumId");
        var loadIndex = null;
        layer.confirm("确认要删除这条帖子吗?", function (index) {
            layer.close(index);
            $.ajax({
                type: "post",
                url: path + "/sharedForum/deleteForum/" + forumId,
                dataType: "json",
                beforeSend: function () {
                    loadIndex = layer.load(1, {shade: 0.8});
                },
                success: function (date) {
                    if (date.status == 200) {
                        location.href=path+"/sharedForum/goIndex";
                    } else {
                        layer.msg("删除失败...", {shift: 6});
                    }
                },
                error: function () {
                    layer.msg("网络连接超时...", {shift: 6});
                },
                complete: function () {
                    layer.close(loadIndex);
                }
            });
        })
    });

    /**
     * 把帖子添加顶置
     * @author 博博大人
     * @time 2019/1/22 17:43
     */
    $(".forumOverhead").click(function () {
        alert("ss")
    });

});