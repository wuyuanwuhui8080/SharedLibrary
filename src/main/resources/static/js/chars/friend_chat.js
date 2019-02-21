// 登陆
const LOGIN_CODE = 1000;
// 群聊
const GROUP_CHAT_CODE = 1002;
// 私聊
const PRIVATE_CHAT_CODE = 1003;
// pong 信息
const PONG_CHAT_CODE = 1004;
// 注销
var LOGIN_OUT = 1001;
/*------------上面是客户端传输到客户端的常量-----------*/
// 群聊信息
const GROUP_CHAT_MESSAGE_CODE = 2000;
// 系统信息
const SYSTEM_MESSAGE_CODE = 2001;
// 私聊信息
const PRIVATE_CHAT_MESSAGE_CODE = 2002;
// ping 信息
const PING_MESSAGE_CODE = 2003;
/*  ------------上面是聊天常量，下面的系统常量------------- */
// 普通系统信息：用户上线，下线广播通知等
const NORMAL_SYSTEM_MESSGAE_CODE = 3000;
// 更新当前用户数量的系统信息
const UPDATE_USERCOUNT_SYSTEM_MESSGAE_CODE = 3001;
// 更新当前用户列表的系统信息
const UPDATE_USERLIST_SYSTEM_MESSGAE_CODE = 3002;
// 获取个人信息的系统信息
const PERSONAL_SYSTEM_MESSGAE_CODE = 3003;

const WEB_SOCKET_URL = "ws://127.0.0.1:8090/ws";

var socket = null;
$(function () {

    // 初始化聊天窗口的时候，提示
    $(".headTime").html("暂无当前聊天信息");

    // 判断是否支持websocket
    if (window.WebSocket) {
        socket = new WebSocket(WEB_SOCKET_URL);
        // 接受到信息
        socket.onmessage = function (event) {
            let obj = JSON.parse(event.data);
            switch (obj.code) {
                // 系统信息
                case SYSTEM_MESSAGE_CODE:
                    switch (obj.body.systemMessageCode) {
                        case NORMAL_SYSTEM_MESSGAE_CODE:
                            let div = '<div class="col-md-12" align="center">\n' +
                                '      <p>' + obj.message + '.</p>\n' +
                                '      </div>';
                            $("#GroupmessageChar").append(div);
                            break;
                        // 更新在线人数
                        case UPDATE_USERCOUNT_SYSTEM_MESSGAE_CODE:
                            // $(".charUserNum").html("" + obj.body.obj + "");
                            /*  setTimeout(function () {
                                  $.gritter.add({
                                      title: '有新用户加入了聊天室',
                                      text: '请前往<a href="mailbox.html" class="text-warning">收件箱</a>查看今日任务',
                                      time: 5
                                  });
                              }, 5);*/
                            break;
                        // 更新用户
                        case UPDATE_USERLIST_SYSTEM_MESSGAE_CODE:
                            var userList = '';
                            // 循环赋值
                            /*var userIds = new Array();
                            for (var i = 0; i < obj.body.obj.length; i++) {
                                userIds[i] = obj.body.obj[i].id;
                                if(app.isNotNull($("#charUser"+userIds[i]+""))){
                                    $("#charUser"+userIds[i]+"").children().find(".messageCode").html("<span style='margin-left: 40%' class='label label-primary'>在线</span>");
                                    continue;
                                }else if(app.isNull($("#charUser"+userIds[i]+"")) && (i + 1) == obj.body.obj.length){
                                    obj.children().find(".messageCode").html('<span class="label label-default" style="margin-left: 40%">离线</span>');
                                    break;
                                }
                            }*/
                            break;
                    }
                    break;
                // 群发信息
                case GROUP_CHAT_MESSAGE_CODE:
                    if (obj.user.id == userId) {
                        let myUserDiv = '' +
                            '<div class="col-md-7 pull-right" id="' + obj.user.id + '">' +
                            '<div class="chat-message">\n' +
                            '    <img class="message-avatar" src="' + path + '/images/' + obj.user.headImg + '" alt="">\n' +
                            '           <div class="message">\n' +
                            '               <a class="message-author" href="#"> ' + obj.user.realName + ' </a>\n' +
                            '          <span class="message-date">  ' + obj.time + ' </span>\n' +
                            '            <span class="message-content">\n' +
                            '     ' + obj.message + '\n' +
                            '          </span>\n' +
                            '    </div>\n' +
                            ' </div>' +
                            '</div>';
                        $("#GroupmessageChar").append(myUserDiv);
                        $('.messageChar').scrollTop($('.messageChar')[0].scrollHeight);
                    } else {
                        let friendUserDiv = '' +
                            '<div class="col-md-7 pull-left">' +
                            '<div class="chat-message" id="' + obj.user.id + '">\n' +
                            '  <img class="message-avatar" src="' + path + '/images/' + obj.user.headImg + '" alt="">\n' +
                            '       <div class="message">\n' +
                            '           <a class="message-author" href="#">' + obj.user.realName + '</a>\n' +
                            '              <span class="message-date"> ' + obj.time + '</span>\n' +
                            '                 <span class="message-content">\n' +
                            '   ' + obj.message + '\n' +
                            '                 </span>\n' +
                            '         </div>\n' +
                            '     </div>' +
                            '</div>';
                        $("#GroupmessageChar").append(friendUserDiv);
                        $('.messageChar').scrollTop($('.messageChar')[0].scrollHeight);
                    }
                    $(".headTime").html(obj.time);
                    break;
                // 单聊
                case PRIVATE_CHAT_MESSAGE_CODE:
                    // 说明是对方
                    if (userId != obj.user.id) {
                        let privateMessageMyuser = '' +
                            '<div class="col-md-7 pull-left">' +
                            '<div class="chat-message" id="' + obj.user.id + '">\n' +
                            '  <img class="message-avatar" src="' + path + '/images/' + obj.user.headImg + '" alt="">\n' +
                            '       <div class="message">\n' +
                            '           <a class="message-author" href="#">' + obj.user.realName + '</a>\n' +
                            '              <span class="message-date"> ' + obj.time + '</span>\n' +
                            '                 <span class="message-content">\n' +
                            '   ' + obj.message + '\n' +
                            '                 </span>\n' +
                            '         </div>\n' +
                            '     </div>' +
                            '</div>';
                        setTimeout(function () {
                            $.gritter.add({
                                text: obj.user.realName + ":" + obj.message,
                                time: 2500
                            });
                        }, 100);
                        $("#chatDiv" + obj.user.id + "").append(privateMessageMyuser);
                        $('.messageChar').scrollTop($("#chatDiv" + obj.user.id + "")[0].scrollHeight);
                    } else {
                        let friendforUserDiv = '' +
                            '<div class="col-md-7 pull-right">' +
                            '<div class="chat-message" id="' + obj.user.id + '">\n' +
                            '  <img class="message-avatar" src="' + path + '/images/' + obj.user.headImg + '" alt="">\n' +
                            '       <div class="message">\n' +
                            '           <a class="message-author" href="#">' + obj.user.realName + '</a>\n' +
                            '              <span class="message-date"> ' + obj.time + '</span>\n' +
                            '                 <span class="message-content">\n' +
                            '   ' + obj.message + '\n' +
                            '                 </span>\n' +
                            '         </div>\n' +
                            '     </div>' +
                            '</div>';
                        $("#chatDiv" + obj.receiverId + "").append(friendforUserDiv);
                        $('.messageChar').scrollTop($("#chatDiv" + obj.receiverId + "")[0].scrollHeight);
                    }
                    $(".headTime").html(obj.time);
                    break;
            }
        };

        // 打开连接方法
        socket.onopen = function () {
            loginSend();
        }

        socket.onclose = function () {
            var obj = {};
            obj.code = LOGIN_OUT;
            send(JSON.stringify(obj));
        }

    } else {
        swal("您的浏览器不支持websocket!", null, "warning");
        return false;
    }

    $("#sumitCharMassage").keypress(function () {
        back(this, event)
    });

});

/**
 * 登录方法
 * @author 博博大人
 * @time 2019/1/10 15:58
 */
function loginSend() {
    let obj = {};
    obj.userName = userName;
    obj.headImg = headImg;
    obj.realName = realName;
    obj.id = userId;
    obj.code = LOGIN_CODE;
    // 调用传输数据方法，并把obj序列化
    send(JSON.stringify(obj));
}


/**
 * 群发消息/或者单发
 * @param message 传入的消息
 * @author 博博大人
 * @time 2019/1/11 10:44
 */
function sendMessChar(message, byUserid, code) {
    let obj = {};
    obj.headImg = headImg;
    obj.realName = realName;
    obj.id = userId;
    obj.code = code;
    obj.receiverId = byUserid;
    obj.chatMassage = message;
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

/**
 * 切换用户
 * @param id 用户id
 * @param realName 用户姓名
 * @author 博博大人
 * @time 2019/1/10 23:03
 */
function onToUsers(id, realName) {

}

/**
 * enter事件管理
 * @author 博博大人
 * @time 2019/1/11 9:52
 */
function back(ele, event) {
    if (event.keyCode == 13) {
        event.returnValue = false;
        var message = document.getElementById("sumitCharMassage").value;
        var byUserId = document.getElementById("sumitCharMassage").getAttribute("byUserid");
        if (app.isNull(message)) {
            swal("发送内容不能为空", null, "warning");
            return false;
        }
        document.getElementById("sumitCharMassage").value = "";
        if (app.isNotNull(byUserId) && byUserId != "null") {
            var byreceiverHeadImg = document.getElementById("sumitCharMassage").getAttribute("receiverheadimg");
            sendMessChar(message, byUserId, PRIVATE_CHAT_CODE);
            return false;
        } else {
            sendMessChar(message, null, GROUP_CHAT_CODE);
            return false;
        }
    }
}


/**
 * 滚动条置底
 * @param o document.getElementById("id")
 */
function boxScroll(o) {
    o.scrollTop = o.scrollHeight;
}

/**
 *
 * 切换聊天窗口
 *
 * @param realName
 * @param id
 * @author 博博大人
 * @time 2019/1/11 14:10
 */

function chooseUser(realName, id, receiverHeadImg) {
    // 设置所有聊天窗口为隐藏
    $(".messageChar").css("display", "none");
    // 判断id是否为空，为空聊天室
    if (app.isNull(id)) {
        var charHeadName = document.getElementById("charHeadName");
        charHeadName.innerHTML = "<span>聊天室</span>";
        var sumitCharMassage = document.getElementById("sumitCharMassage");
        sumitCharMassage.setAttribute("byUserid", null);
        charHeadName.removeAttribute("meId");
        charHeadName.removeAttribute("receiverId");
        charHeadName.removeAttribute("realName");
        $("#GroupmessageChar").show();
    } else {
        // 先获取看看页面是否有这个div
        var myChatDiv = document.getElementById("chatDiv" + id + "");
        var div;
        // 如果没有就创建一个
        if (app.isNull(myChatDiv)) {
            div = ' <div class="chat-discussion messageChar" id="chatDiv' + id + '" style="overflow-y=auto;" >\n' +
                '                              \n' +
                '                            </div>';
            $(".divFistChar").append(div);
            // 否则就让他展现
        } else {
            myChatDiv.style.display = "";
        }

        $(function () {
            var chardiv = '';
            $.ajax({
                type: "get",
                url: path + "/char/friendAndUsersList/" + userId + "/" + id,
                dataType: "json",
                beforeSend: function () {
                    $("body").append(app.loads());
                },
                success: function (date) {
                    var charLength = date.length;
                    if (charLength == 0) {
                        chardiv = '<div class="col-md-12" align="center">\n' +
                            '      <p>暂时无消息哦...</p>\n' +
                            '      </div>';
                    } else {
                        // 把评论集合赋值给变量
                        var charList = date;
                        // 根据时间排序
                        charList.sort(function (a, b) {
                            return new Date(a.time).getTime() - new Date(b.time).getTime();
                        });
                        for (var i = 0; i < charList.length; i++) {
                            // 说明是自己的信息
                            if (charList[i].myId == userId) {
                                chardiv += '<div class="col-md-7 pull-right">' +
                                    '<div class="chat-message">\n' +
                                    '  <img class="message-avatar" src="' + path + '/images/' + headImg + '" alt="">\n' +
                                    '       <div class="message">\n' +
                                    '           <a class="message-author" href="#">' + charList[i].realName + '</a>\n' +
                                    '              <span class="message-date"> ' + charList[i].time + '</span>\n' +
                                    '                 <span class="message-content">\n' +
                                    '   ' + charList[i].message + '\n' +
                                    '                 </span>\n' +
                                    '         </div>\n' +
                                    '     </div>' +
                                    '</div>';
                            } else {
                                chardiv += '<div class="col-md-7 pull-left">' +
                                    '<div class="chat-message">\n' +
                                    '  <img class="message-avatar" src="' + path + '/images/' + charList[i].receiverHeadImg + '" alt="">\n' +
                                    '       <div class="message">\n' +
                                    '           <a class="message-author" href="#">' + charList[i].realName + '</a>\n' +
                                    '              <span class="message-date"> ' + charList[i].time + '</span>\n' +
                                    '                 <span class="message-content">\n' +
                                    '   ' + charList[i].message + '\n' +
                                    '                 </span>\n' +
                                    '         </div>\n' +
                                    '     </div>' +
                                    '</div>';
                            }
                        }
                    }
                    $("#chatDiv" + id + "").html(chardiv);
                    $('.messageChar').scrollTop($("#chatDiv" + id + "")[0].scrollHeight);
                },
                error: function () {
                    swal("网络连接超时！", "请检查网络设置", "error");
                },
                complete: function () {
                    $("#ibox").remove();
                }
            });
        });

        var charHeadName = document.getElementById("charHeadName");
        charHeadName.setAttribute("meId", userId);
        charHeadName.setAttribute("receiverId", id);
        charHeadName.setAttribute("realName", realName);
        charHeadName.innerHTML = "<span>" + realName + "</span>";
        var sumitCharMassage = document.getElementById("sumitCharMassage");
        sumitCharMassage.setAttribute("byUserid", id);
    }
}

/**
 *
 * 清空聊天记录操作 / 单聊、聊天室
 *
 * 需要注意这个地方，meId和receiverId不传入就代表是聊天室的内容，所以会清空div内容
 * 如果传入，就代表是和好友聊天，会根据传入的myid和receiverId删除redis对应的key
 *
 * @param meId 自己的id
 * @param receiverId 朋友的id
 * @author 博博大人
 * @time 2019/1/13 19:37
 */
function clirMessage(meId, receiverId, realName) {
    // 代表聊天室
    if (app.isNull(meId) && app.isNull(receiverId)) {
        if (confirm("您确认要清空聊天室的聊天记录吗？")) {
            $("#GroupmessageChar").html("");
        }
        // 代表私聊
    } else {
        if (confirm("确认要清空您和" + realName + "的聊天记录吗？")) {
            $.ajax({
                type: "post",
                url: path + "/char/deleteMessage/" + meId + "/" + receiverId,
                dataType: "json",
                beforeSend: function () {
                    $("body").append(app.loads());
                },
                success: function (date) {
                    if (date.status == 200) {
                        $("#chatDiv" + receiverId + "").html("");
                    } else {
                        swal("清空失败！", null, "warning");
                    }
                },
                error: function () {
                    swal("网络超时..", null, "error");
                },
                complete: function () {
                    $("#ibox").remove();
                }
            });
        }
    }
}

