<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script>if (window.top !== window.self) {
        window.top.location = window.location;
    }</script>

    <title>403错误</title>
    <#include "background/comm/script.ftl">
</head>

<body class="gray-bg">


    <div class="middle-box text-center animated fadeInDown">
        <h1>403</h1>
        <h3 class="font-bold">服务器内部错误</h3>

        <div class="error-desc">
            你没有权限访问这个页面
            <br/>您可以返回主页看看
            <br/><a href="index.ftl" class="btn btn-primary m-t">主页</a>
        </div>
    </div>


</body>

</html>
