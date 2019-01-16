<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>好友聊天</title>
    <#include  "../comm/script.ftl">
    <link href="${basePath}/css/plugins/jsTree/style.min.css" rel="stylesheet">
    <!-- Gritter -->
    <link href="${basePath}/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">
    <script src="${basePath}/js/chars/friend_chat.js"></script>
    <!-- GITTER -->
    <script src="${basePath}/js/plugins/gritter/jquery.gritter.min.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content  animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox chat-view">
                <div class="ibox-title">
                    <small class="pull-right text-muted">最新消息：<span class="headTime">2015-02-02 18:39:23</span></small>
                    <span class="charHeadName" id="charHeadName" meId="" receiverId="" realName="">聊天室</span><span
                        style="margin-left: 31%;"><a
                        href="javascript:clirMessage(document.getElementById('charHeadName').getAttribute('meId'),document.getElementById('charHeadName').getAttribute('receiverId'),document.getElementById('charHeadName').getAttribute('realName'));">清空消息记录</a> </span>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-md-9 divFistChar">
                            <div class="chat-discussion messageChar" id="GroupmessageChar" style="overflow-y=auto;">
                            <#--聊天窗口-->
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="chat-users">
                                <div class="users-list">
                                    <div class="chat-user">
                                        <img class="chat-avatar" src="${basePath}/img/chatroom.png" alt="">
                                        <div class="chat-user-name">
                                            <a href="javascript:;" onclick="chooseUser(null,null);">聊天室</a>
                                            <span class="label label-default" style="margin-left: 40%">在线人数<span
                                                    class="charUserNum">0</span>人</span>
                                        </div>
                                    </div>
                                    <div class="users-lists">
                                        <#if list?? && (list?size > 0)>
                                            <#list list as li>
                                                <div class="chat-user chatMessageUser" id="charUser${li.id}">
                                                    <img class="chat-avatar" src="${basePath}/images/${li.headImg}"
                                                         alt="">
                                                    <div class="chat-user-name">
                                                        <a href="javascript:;"
                                                           onclick="chooseUser('${li.realName}','${li.id}','${li.headImg}');">${li.realName}</a>
                                                    </div>
                                                </div>
                                            </#list>
                                        </#if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <form>
                                <div class="chat-message-form" style="z-index: 99999999999999999999">
                                    <div class="form-group">
                                    <textarea style="resize:none" class="form-control message-input "
                                              id="sumitCharMassage" name="message" byUserid=""
                                              placeholder="输入消息内容，按回车键发送"></textarea>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script>
    // 获取用户名
    var userName = "${Session.users.userName}";
    // 获取真实姓名
    var realName = "${Session.users.realName}";
    // 获取id
    var userId = "${Session.users.id}";
    // 获取头像
    var headImg = "${Session.users.headImg}";

    var path = "${basePath}";

</script>