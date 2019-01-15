<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>发博客</title>
    <#include  "../comm/script.ftl">
    <link href="${basePath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${basePath}/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${basePath}/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <h3 align="center" style="color: #999999">分享你遇到的有趣的事情吧</h3>
                <div class="ibox-content no-padding" id="EditorDiv">
                    <textarea class="summernote"></textarea>
                </div>
            </div>
            <div align="right">
                <button id="sumitEditTest" class="btn btn-primary">完成</button>
            </div>
        </div>
    </div>
</div>
<!-- SUMMERNOTE -->
<script src="${basePath}/js/plugins/summernote/summernote.min.js"></script>
<script src="${basePath}/js/plugins/summernote/summernote-zh-CN.js"></script>
<script src="${basePath}/js/users/blogEditors.js"></script>
</body>
</html>
<script>
    var userid = "${Session.users.id}";
    var path = "${basePath}";
</script>