<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <#include "../comm/script.ftl">
    <script src="${basePath}/res/layui/lay/modules/layer.js"></script>
    <link type="text/css" rel="stylesheet" href="${basePath}/res/layui/css/modules/layer/default/layer.css"/>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>添加分类</h5>
                </div>
                <div class="ibox-content">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">分类</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control classifyText" placeholder="分类名称">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-primary" type="button" id="saveclassify">保存内容</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<script>

    var path = "${basePath}";

    $(document).ready(function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });

        /**
         * 添加分类
         */
        $("#saveclassify").click(function () {
            var className = $(".classifyText").val();
            if (app.isNull(className)) {
                layer.msg("分类名称不能为空！", {shift: 6});
            } else {
                $.ajax({
                    type: "post",
                    url: path + "/sharedlClassify/saveForumClass",
                    data: {className: className},
                    beforeSend: function () {
                        $("body").append(app.loads());
                    },
                    success: function (date) {
                        if (date.status == 200) {
                            layer.msg("添加成功！", {icon: 6});
                            $(".classifyText").val("");
                        } else {
                            layer.msg(date.msg, {icon: 7}});
                        }
                    },
                    error : function () {
                        layer.msg("连接超时！",{shift : 6});
                    },
                    complete : function () {
                        $("#ibox").remove();
                    }
                });
            }
        });

    });
</script>


</body>
</html>