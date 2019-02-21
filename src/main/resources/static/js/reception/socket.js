// 登陆
const FORUM_LOGIN_CODE = 10001;
// 群聊
const GROUP_CHAT_CODE = 1002;
// 私聊
const PRIVATE_CHAT_CODE = 1003;
// pong 信息
const PONG_CHAT_CODE = 1004;

const FORUM_MESSAGEBYME_CODE = 20002;

// 论坛注销
const LOGIN_OUT = 10002;

/*------------上面是客户端传输到客户端的常量-----------*/
// 群聊信息
const FORUM_MESSAGE_CODE = 20001;

const WEB_SOCKET_URL = "ws://127.0.0.1:8090/ws";

var socket = null;
$(function () {
    // 如果已经登录
    if (forumWirte) {
        // 判断是否支持websocket
        if (window.WebSocket) {
            socket = new WebSocket(WEB_SOCKET_URL);

            // 打开连接方法
            socket.onopen = function () {
                loginSend();
            }

            socket.onmessage = function (ev) {
                let obj = JSON.parse(ev.data);
                switch (obj.code) {
                    // 推送给@的人
                    case FORUM_MESSAGE_CODE:
                        toastr.options = {
                            "closeButton": false,//显示关闭按钮
                            "debug": false,//启用debug
                            "progressBar": true,
                            "positionClass": "toast-bottom-right",//弹出的位置
                            "showDuration": "400",//显示的时间
                            "hideDuration": "1000",//消失的时间
                            "timeOut": "3500",//停留的时间
                            "extendedTimeOut": "1000",//控制时间
                            "showEasing": "swing",//显示时的动画缓冲方式
                            "hideEasing": "linear",//消失时的动画缓冲方式
                            "showMethod": "fadeIn",//显示时的动画方式
                            "hideMethod": "fadeOut"//消失时的动画方式
                        };
                        toastr.info(obj.message);
                        break;
                    // 推送给自己
                    case FORUM_MESSAGEBYME_CODE:
                        toastr.options = {
                            "closeButton": false,//显示关闭按钮
                            "debug": false,//启用debug
                            "progressBar": true,
                            "positionClass": "toast-bottom-right",//弹出的位置
                            "showDuration": "400",//显示的时间
                            "hideDuration": "1000",//消失的时间
                            "timeOut": "3500",//停留的时间
                            "extendedTimeOut": "1000",//控制时间
                            "showEasing": "swing",//显示时的动画缓冲方式
                            "hideEasing": "linear",//消失时的动画缓冲方式
                            "showMethod": "fadeIn",//显示时的动画方式
                            "hideMethod": "fadeOut"//消失时的动画方式
                        };
                        toastr.info(obj.message);
                        break;
                }
            }


            socket.onclose = function (ev) {
                var obj = {};
                obj.code = LOGIN_OUT;
                send(JSON.stringify(obj));
            }

        }
    }
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
            if (app.isNull(html)) {
                layer.msg("评论内容不能为空!", {shift: 6});
                return false;
            }
            var forumUserId = obj.attr("forumUserId");

            var context = fly.content(html)
            var loadIndex = null;

            var ss = fly.escape(html || '').replace(/@(\S+)(\s+?|$)/g, '@<a href="javascript:;" class="fly-aite">$1</a> $2');
            $("#RelpyTest").html(ss);
            var list = new ArrayList();
            $("#RelpyTest .fly-aite").each(function (index) {
                list.add($(this).html());
            });
            if (list.isEmpty()) {
                list = "";
            } else {
                list = JSON.stringify(list.getAll());
            }
            app.loadJson(path + "/sharedForumComment/saveComment", {
                userId: userId,
                content: context,
                forumId: forumId,
                commUsersName: list
            }, function (date) {
                var div = '<li data-id="111" class="jieda-daan" id="comm' + date.obj.id + '">\n' +
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
                    '                                                              <i class="iconfont icon-zan" commentId="' + date.obj.id + '"\n' +
                    '                                                                 style="color: #999999;"></i>\n' +
                    '                                                                       <em>0</em>\n' +
                    '                                                                  </span>\n' +
                    '                                    </span>\n' +
                    '                                    <div class="jieda-admin">\n' +
                    '                                        <span type="del" class="commentDel" commentId="' + date.obj.id + '">删除</span>\n' +
                    '                                    </div>\n' +
                    '                                </div>\n' +
                    '                            </li>';
                $("#jieda").append(div);
                $("#L_content").val("");
                var forumName = $(".forumTitle").val();
                // 帖子用户名
                var forumUserName = $(".forumUserName").val();
                pushMessage(forumName, realName, forumUserName, list);
            });
        }
    });

});

/**
 * 登录方法
 * @author 博博大人
 * @time 2019/1/10 15:58
 */
function loginSend() {
    var obj = {};
    obj.userName = userName;
    obj.headImg = headImg;
    obj.realName = realName;
    obj.id = userId;
    obj.code = FORUM_LOGIN_CODE;
    // 调用传输数据方法，并把obj序列化
    send(JSON.stringify(obj));
}

/**
 * 推送消息
 * @param forumName 帖子标题
 * @param realName 真实姓名
 * @param list @人集合
 */
function pushMessage(forumName, realName, myUserName, list) {
    var obj = {};
    obj.realName = realName;
    obj.forumName = forumName;
    obj.forumUserNameList = list;
    obj.myUserName = myUserName;
    obj.code = FORUM_MESSAGE_CODE;
    send(JSON.stringify(obj));
}

/**
 * 传输数据到服务器
 * @param obj 传入的json数据
 * @author 博博大人
 * @time 2019/1/10 15:58
 */
function send(obj) {
    if (!window.WebSocket) {
        swal("您的浏览器不支持websocket!", null, "warning");
        return;
    }
    if (socket.readyState === WebSocket.OPEN) {
        socket.send(obj);
    } else {
        swal("websocket没有建立成功！", null, "error");
    }
}