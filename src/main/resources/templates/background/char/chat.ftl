<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>netty_websocket</title>
    <!-- Bootstrap core CSS -->
    <#include "../comm/script.ftl">
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="${basePath}/css/chat.css">
    <!--<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">-->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="http://cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script>
    <script src="../bootstrap/js/chat.js"></script>
</head>

<body class="background">
<div class="mianFrame">
    <div class="panel scroll" id="panel">
    <div class="header">
        <img class="avatar img-rounded" id="myAvatar" src="${basePath}/images/${Session.users.headImg}"
             style="height: 50px;width: 50px">
        <div id="nick" style="color: white;font-size: large">${Session.users.realName}</div>
    </div>
    <h3 id="userCount" style="color: white;font-size: large;margin-left: 20px">saddsa</h3>
    <div id="userList" style="border-top:1px solid #cccccc">asdsad</div>
</div>

    <div id="repeatBox">
        <div class="box" id="box">
            <div class="textareaHead" id="textareaHead">群聊室</div>
            <div class="textarea scroll" id="responseContent"></div>
            <form onSubmit="return false;">
                <label>
                    <textarea id="sendTextarea" class="box_ft" name="message"></textarea>
                </label>
                <div class="send">
                    <button class="sendButton" onClick="sendMessage(this.form.message.value)">发送</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>

<script>
    window.onload = function () {
        $('#nick').text(GetQueryString("nick"));
        websocket();
    };
</script>
</html>