<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <#if type??>
        <#if type ==2>
            <title>关注列表</title>
        <#else >
            <title>粉丝列表</title>
        </#if>
    </#if>
    <#include "../comm/script.ftl">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <diFv class="row">
         <#if (page.list?size > 0) >
             <#list page.list as li  >
                <div class="col-sm-4">
                 <#if type??>
                     <#if type==2>
                        <button class="btn-primary toFriem"
                                href="${basePath}/sharedUsers/lookProfile/${li.meId}">
                     <#else>
                        <button class="btn-primary toFriem"
                                href="${basePath}/sharedUsers/lookProfile/${li.fansId}">
                     </#if>
                 </#if>
                    <div class="col-sm-4">
                        <div class="text-center">
                            <img alt="image" class="img-circle m-t-xs img-responsive"
                                 src="${basePath}/images/${li.users.headImg}">
                        </div>
                    </div>
                    <div class="col-sm-8">
                        <h3><strong>${li.users.userName}(${li.users.realName})</strong></h3>
                        <address>
                            <abbr title="Phone">联系方式:</abbr> ${li.users.phone}
                        </address>
                        <address>
                            <strong>个性签名</strong><br>
                            <#if li.users.individual?? && li.users.individual!=''>
                                ${li.users.individual}
                            <#else >
                                这人比较懒，暂时没有...
                            </#if>
                        </address>
                    </div>
                    <div class="clearfix"></div>
                </button>
                </div>
             </#list>
         <#else >
             <#if type??>
                 <#if type ==2>
                    <div align="center">
                        <h2>您还没有关注的用户...</h2>
                    </div>
                 <#else >
                    <div align="center">
                        <h2>您还没有粉丝用户...</h2>
                    </div>
                 </#if>
             </#if>
         </#if>
    </diFv>
    <#include "../comm/page.ftl">
</div>

<!-- 全局js -->
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.6"></script>


<!-- 自定义js -->
<script src="js/content.js?v=1.0.0"></script>


<script>
    $(document).ready(function () {
        $('.contact-box').each(function () {
            animationHover(this, 'pulse');
        });
    });

    $(".toFriem").click(function () {
        var dataUrl = $(this).attr('href'),
                dataIndex = $(this).data('index'),
                menuName = "好友资料";
        parent.addMenuTab(dataUrl, menuName, dataIndex);
    });
</script>

<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
<!--统计代码，可删除-->

</body>

</html>
