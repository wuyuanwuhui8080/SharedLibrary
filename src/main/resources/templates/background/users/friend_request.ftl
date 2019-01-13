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
<#if page.list??&&(page.list?size==0)  >
            <div align="center">
                <h2>您还没有好友请求...</h2>
            </div>
</#if>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <#if page.list??&&(page.list?size>0)>
            <div align="center">
                <h2>加为好友请求</h2>
            </div>
            <div id="MyfendList">
                <#list page.list as li>
                    <div class="col-sm-4">
                        <div class="contact-box">
                            <div class="col-sm-4">
                                <div class="text-center">
                                    <img alt="image" class="img-circle m-t-xs img-responsive"
                                         src="${basePath}/images/${li.sharedUsers.headImg!}">
                                    <div class="m-t-xs font-bold"></div>
                                </div>
                            </div>
                            <div class="col-sm-8">
                                <a href="${basePath}/sharedUsers/lookProfile/${li.sharedUsers.id}">
                                    <h3><strong>${li.sharedUsers.realName!}(${li.sharedUsers.userName!})</strong></h3>
                                </a>
                                </h3>
                                <address>
                                    <strong>请求时间</strong><br>
                                    ${li.creationDate?datetime}
                                    <br>
                                </address>
                                <div class="butClass">
                                 <#if (li.status == 1)>
                                     <button class="btn btn-primary toSuccess" requestId="${li.id}" miId="${li.meId}"
                                             byrequestId="${li.requestId}">同意
                                     </button>
                                     <button class="btn btn-info toError" requestId="${li.id}"
                                             style="margin-left: 20px;">拒绝
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
        </#if>
    </div>

    <ul class="pager">
        <li><a href="/sharedlFriendRequest/goFirendRequest/${Session.users.id}/1">首页</a></li>
    <#if page.hasPreviousPage>
        <li class="">
            <a href="/sharedlFriendRequest/goFirendRequest/${Session.users.id}/${page.pageNum - 1}">&larr; 上一页</a>
        </li>
    <#else >
         <li class="disabled"><a href="javascript:;">&larr; 上一页</a></li>
    </#if>
    <#--<#list page.navigatepageNums as navigatepageNum>
        <#if navigatepageNum==page.pageNum>
           <li><a href="#">${navigatepageNum}</a></li>
        </#if>
        <#if navigatepageNum!=page.pageNum>
           <li><a href="#">${navigatepageNum}</a></li>
        </#if>
    </#list>-->
     <#if page.hasNextPage>
            <li><a href="/sharedlFriendRequest/goFirendRequest/${Session.users.id}/${page.pageNum + 1}">下一页 &rarr;</a></li>
     <#else>
            <li class="disabled"><a href="javascript:;">下一页 &rarr;</a></li>
     </#if>
        <li><a href="/sharedlFriendRequest/goFirendRequest/${Session.users.id}/${page.pages}">尾页</a></li>
    </ul>
    <script>
        var path = '${basePath}';
    </script>
</body>
</html>
