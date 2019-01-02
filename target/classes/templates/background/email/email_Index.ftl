<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>H+ 后台主题UI框架 - 收件箱</title>
    <#include "../comm/script.ftl">
    <link href="${basePath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${basePath}/css/plugins/iCheck/custom.css" rel="stylesheet">
</head>
<body class="gray-bg">
<input type="hidden" id="path" value="${basePath}"/>
<div class="wrapper wrapper-content">
    <div class="row">
    <#--引入右列表-->
        <#include "email_left.ftl">
    <#--根据state判断显示那种列表-->
         <#if state==0>
             <#include "email_List.ftl">
         <#elseif state ==1>
             <#include "email_StateList.ftl">
         <#elseif state ==2 >
             <#include "email_compose.ftl">
         </#if>

    </div>
</div>

<script>
    $(document).ready(function () {
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });
    });
</script>
<script src="${basePath}/js/email/Email_Haveread.js"></script>
<script src="${basePath}/js/email/Email_SelState.js"></script>
<script src="${basePath}/js/email/Email_Page.js"></script>
<!-- 注意， 只需要引用 JS，无需引用任何 CSS ！！！-->
<script src="${basePath}/js/email/wangEditor.js"></script>
<script src="${basePath}/js/email/Email_Submit.js"></script>
</body>

</html>
