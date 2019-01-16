<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>写博客</title>
    <#assign basePath=springMacroRequestContext.contextPath>

  <#include "comm/script.ftl">
<#-- <#include "../background/comm/script.ftl">-->
    <link rel="shortcut icon" href="favicon.ico">
    <link href="${basePath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${basePath}/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${basePath}/css/animate.css" rel="stylesheet">
    <link href="${basePath}/css/style.css?v=4.1.0" rel="stylesheet">
    <script src="${basePath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${basePath}/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${basePath}/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${basePath}/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${basePath}/js/jsUtil.js"></script>

    <!-- 第三方插件 -->
    <script src="${basePath}/js/hplus.js?v=4.1.0"></script>
    <script type="text/javascript" src="${basePath}/js/contabs.js"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    <!-- 第三方插件 -->
    <script src="${basePath}/js/plugins/pace/pace.min.js"></script>
    <!-- 自定义js -->
    <script src="${basePath}/js/content.js?v=1.0.0"></script>

    <!-- SUMMERNOTE -->
    <script src="${basePath}/js/plugins/summernote/summernote.min.js"></script>
    <script src="${basePath}/js/plugins/summernote/summernote-zh-CN.js"></script>
    <link href="${basePath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${basePath}/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${basePath}/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
</head>
<body>
<#include "comm/head.ftl">
<article>
    <div class="ibox float-e-margins">
        <h3 align="center" style="color: #999999">分享你遇到的有趣的事情吧</h3>
        <div class="ibox-content no-padding" style="background-color: #efefef;" id="EditorDiv">
            <textarea class="summernote"></textarea>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2 "><h3>帖子分类</h3></div>
        <div class="col-md-3 col-md-pull-1">
            <select class="form-control">
                <option value="">--请选择--</option>
                <#list classifyList as li>
                    <option value="${li.id}">${li.classifyName}</option>
                </#list>
            </select>
        </div>
        <div class="col-md-5">
            <span>封面图片</span>
            <input type="file" />
        </div>
        <div class="col-md-2 pull-right col-md-pull-2">
            <button class="btn">完成</button>
        </div>
    </div>
    <center>
        <div class="ibox float-e-margins">

        </div>
    </center>
</article>
<#include "comm/footer.ftl">
</body>
</html>
<script>
    $(function () {
        $('.summernote').summernote({
            lang: 'zh-CN'
        });

        var edit = function () {
            $("#eg").addClass("no-padding");
            $('.click2edit').summernote({
                lang: 'zh-CN',
                focus: true
            });
        };
        var save = function () {
            $("#eg").removeClass("no-padding");
            var aHTML = $('.click2edit').code(); //save HTML If you need(aHTML: array).
            $('.click2edit').destroy();
        };

    });

</script>