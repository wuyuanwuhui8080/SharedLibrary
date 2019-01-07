<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>查看用户</title>
    <#include "../comm/script.ftl">
</head>

<body class="gray-bg">

<div class="lock-word animated fadeInDown">
</div>
<div class="middle-box text-center lockscreen animated fadeInDown">
    <div>
        <div class="m-b-md">
            <img alt="image" style="width: 50%" class="img-circle circle-border"
                 src="${basePath}/images/${user.headImg}">
        </div>
        <center>
            <div class="row">
                <div class="col-sm-5 text-right"><h3>用户名:</h3></div>
                <div class="col-sm-7 text-left"><h3>${user.userName}</h3></div>
                <div class="col-sm-5 text-right"><h3>电话:</h3></div>
                <div class="col-sm-7 text-left"><h3>${user.phone}</h3></div>
                <div class="col-sm-5 text-right"><h3>真实姓名:</h3></div>
                <div class="col-sm-7 text-left"><h3>${user.realName}</h3></div>
            </div>
            <div class="row">
                <div class="col-sm-5 text-right"><h3>生日:</h3></div>
                <div class="col-sm-7 text-left"><h3>${user.birthday?date}</h3></div>
                <div class="col-sm-5 text-right"><h3>创建时间:</h3></div>
                <div class="col-sm-7 text-left"><h3>${user.creationDate?datetime}</h3></div>
            </div>
        </center>
        <button type="button" class="btn btn-primary block full-width" style="margin-top: 20px;" onclick="history.back(-1)">返回</button>
    </div>
</div>
</body>
</html>
