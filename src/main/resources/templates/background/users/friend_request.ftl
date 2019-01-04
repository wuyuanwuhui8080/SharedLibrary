<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>好友请求</title>
    <#include "../comm/script.ftl">
    <script src="${basePath}/js/plugins/suggest/bootstrap-suggest.min.js"></script>
    <script src="${basePath}/js/users/friendRequest.js"></script>
</head>

<body class="gray-bg">
<input type="hidden" value="${basePath}" id="path"/>
<input type="hidden" value="${users.id}" id="userid"/>
<div class="wrapper wrapper-content animated fadeInRight">
<div class="row">
        <#if friendRequestList??&&(friendRequestList?size>0)>
        <div id="MyfendList">
            <#list friendRequestList as li>
                <div class="col-sm-4">
                    <div class="contact-box">
                        <a href="${basePath}/sharedUsers/lookProfile/${li.sharedUsers.id}">
                            <div class="col-sm-4">
                                <div class="text-center">
                                    <img alt="image" class="img-circle m-t-xs img-responsive"
                                         src="${basePath}/images/${li.sharedUsers.headImg!}">
                                    <div class="m-t-xs font-bold"></div>
                                </div>
                            </div>
                            <div class="col-sm-8">
                                <h3><strong>${li.sharedUsers.realName!}</strong></h3>
                                <address>
                                    <strong>请求时间</strong><br>
                                    ${li.creationDate?datetime}
                                    <br>
                                </address>
                        </a>
                        <div class="butClass">
                         <#if (li.status == 1)>
                             <button class="btn btn-primary toSuccess" requestId="${li.id}" miId="${li.meId}"
                                     byrequestId="${li.requestId}">同意
                             </button>
                             <button class="btn btn-info toError" requestId="${li.id}" style="margin-left: 20px;">拒绝
                             </button>
                         <#else >
                        <button class="btn">${li.friendRequestStatus.statusName}</button>
                         </#if>
                        </div>
                    </div>

                    <div class="clearfix"></div>

                </div>
                </div>
            </#list>
            </div>
        <#else >
                            <div align="center">
                                <h2>您还没有好友请求...</h2>
                            </div>
        </#if>
</div>
</div>

<script>
    var path = '${basePath}';
</script>
</body>
</html>
