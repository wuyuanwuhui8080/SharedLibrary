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
     * 删除评论
     * @author 博博大人
     * @time 2019/1/21 21:53
     */
    $("body").on("click", ".commentDel", function () {
        var obj = $(this);
        var commentId = obj.attr("commentId");
        layer.confirm("确认要删除这条回复吗？", function (index) {
            layer.close(index);
            app.loadJson(path + "/sharedForumComment/deleteComment", {commentId: commentId}, function (date) {
                obj.parent().parents("#comm" + commentId + "").remove();
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
            app.loadJson(path + "/sharedForum/deleteForum/" + forumId, null, function (date) {
                location.href = path + "/sharedForum/goIndex";
            });
        })
    });

    /**
     * 把帖子添加顶置
     * @author 博博大人
     * @time 2019/1/22 17:43
     */
    $("body").on("click", ".forumOverhead", function () {
        var obj = $(this);
        layer.confirm('确认要加入置顶?(最多存在四条置顶)', {icon: 3, title: '提示'}, function (index) {
            layer.close(index);
            var forumId = obj.attr("forumId");
            // 发送ajax请求
            app.loadJson(paths + "/sharedForum/doStick", {forumId: forumId}, function (date) {
                layer.msg('置顶添加成功！', {icon: 6});
                var div = ' <span class="layui-btn layui-btn-xs jie-admin cancelForumOverhead" forumId="' + forumId + '">取消置顶</span>';
                $(".spickZHiding").html(div);
            });
        });
    });

    /**
     * 取消顶置
     */
    $("body").on("click", ".cancelForumOverhead", function () {
        var obj = $(this);
        layer.confirm('确认取消顶置?', {icon: 3, title: '提示'}, function (index) {
            layer.close(index);
            var forumId = obj.attr("forumId");
            // 发送ajax请求
            app.loadJson(paths + "/sharedForum/cancelStick", {forumId: forumId}, function (date) {
                layer.msg('置顶取消成功！', {icon: 6});
                var div = ' <span class="layui-btn layui-btn-xs jie-admin forumOverhead" forumId = "' + forumId + '" >置顶</span>';
                $(".spickZHiding").html(div);
            });
        });
    });

    /**
     * 点击收藏
     */
    $("body").on("click", ".yesCollect", function () {
        var obj = $(this);
        layer.confirm("确认要收藏该文章吗?", {icon: 3, title: "提示"}, function (index) {
            layer.close(index);
            var forumId = obj.attr("forumId");
            var title = $(".forumTitle").val();
            app.loadJson(paths + "/sharedForum/confirmTheCollection", {
                forumId: forumId,
                userId: userId,
                title: title
            }, function (date) {
                layer.msg("收藏成功！", {icon: 6});
                $(".CollectSpan").html('<span class="layui-btn layui-btn-xs jie-admin noCollect"  forumId="' + forumId + '">取消收藏</span>');
            });
        });
    });


    /**
     * 取消收藏
     */
    $("body").on("click", ".noCollect", function () {
        var obj = $(this);
        layer.confirm("确认要取消收藏该文章吗?", {icon: 3, title: "提示"}, function (index) {
            layer.close(index);
            var forumId = obj.attr("forumId");
            app.loadJson(paths + "/sharedForum/cancelTheCollection", {forumId: forumId}, function (date) {
                layer.msg("取消收藏成功!", {icon: 6});
                $(".CollectSpan").html('<span class="layui-btn layui-btn-xs jie-admin yesCollect"  forumId="' + forumId + '">收藏此帖</span>');
            });
        });
    });

});