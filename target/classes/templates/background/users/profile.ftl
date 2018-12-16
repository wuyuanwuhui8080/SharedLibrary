<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>个人资料</title>

    <#include "../comm/script.ftl">
    <!-- Peity -->
    <script src="${basePath}/js/plugins/peity/jquery.peity.min.js"></script>
    <!-- Peity -->
    <script src="${basePath}/js/demo/peity-demo.js"></script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">
    <div class="row animated fadeInRight">
        <div class="col-sm-4">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>个人资料</h5>
                </div>
                <div>
                    <div class="ibox-content no-padding border-left-right">
                        <img alt="image" class="img-responsive" src="${basePath}/images/${users.headImg}">
                    </div>
                    <div class="ibox-content profile-content">
                        <h4>用户名：<strong>${users.userName}</strong></h4>
                        <p><strong>真实姓名：</strong><strong>${users.realName}</strong></p>
                        <h5>
                            关于我
                        </h5>
                        <p>
                        <#if users.individual?? && users.individual!=''>
                            ${users.individual}
                        <#else >
                                这人比较懒，暂时没有...
                        </#if>
                        </p>
                        <div class="row m-t-lg">
                            <div class="col-sm-4">
                                <span class="bar">5,3,9,6,5,9,7,3,5,2</span>
                                <h5><strong>${getBlogs}</strong> 文章</h5>
                            </div>
                            <div class="col-sm-4">
                                <span class="line">5,3,9,6,5,9,7,3,5,2</span>
                                <h5><strong>${getAttention}</strong> 关注</h5>
                            </div>
                            <div class="col-sm-4">
                                <span class="bar">5,3,2,-1,-3,-2,2,3,5,2</span>
                                <h5><strong>${getFensCuont}</strong> 关注者</h5>
                            </div>
                        </div>
                        <div class="user-button">
                            <div class="row">
                                <div class="col-sm-6">
                                    <button type="button" class="btn btn-primary btn-sm btn-block"><i
                                            class="fa fa-envelope"></i> 发送消息
                                    </button>
                                </div>
                                <div class="col-sm-6">
                                    <button type="button" class="btn btn-default btn-sm btn-block"><i
                                            class="fa fa-coffee"></i> 赞助
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-8">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>最新动态</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="profile.ftl#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="profile.ftl#">选项1</a>
                            </li>
                            <li><a href="profile.ftl#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">

                <div>
                <div class="feed-activity-list">
            <#if blogsList?? && (blogsList?size>0)>
                <#list blogsList as li>
 <div class="feed-element">
     <a href="profile.ftl#" class="pull-left">
         <img alt="image" class="img-circle" src="${basePath}/images/${li.users.headImg}">
     </a>
     <div class="media-body ">
         <small class="pull-right">${li.creationDate?datetime}</small>
         <strong>${li.blogsTitle}</strong>
         <br>
         <small class="text-muted">来自 ${li.users.realName}</small>
         <div class="well">
             ${li.blogsDigest}
         </div>
         <div class="pull-right">
             <a class="btn btn-xs btn-white"><i class="fa fa-thumbs-up"></i> 赞 </a>
             <a class="btn btn-xs btn-white"><i class="fa fa-heart"></i> 收藏</a>
             <a class="btn btn-xs btn-primary"><i class="fa fa-pencil"></i> 评论</a>
         </div>
     </div>
 </div>
    </div>
                    <#if (blogsList?size > 5)>
                        <button class="btn btn-primary btn-block m"><i class="fa fa-arrow-down"></i> 显示更多</button>
                    </#if>

                    </div>
                </#list>
            <#else >
            <div class="feed-element">
                <div class="media-body ">
                    <br>
                    <div style="text-align: center;"> <strong class="" >来自 系统消息</strong></div>

                    <div class="well">
                        您的好友和您暂时没有动态哦..
                    </div>
                </div>
            </div>
            </#if>


                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>