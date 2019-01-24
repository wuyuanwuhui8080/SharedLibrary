<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>最近事情</title>
    <#include "../comm/script.ftl">
</head>

<body class="gray-bg">
<div id="dg" style="z-index: 9999; position: fixed ! important; right: 0px; top: 30%;right: 20px">
    <table width="" 100% style="position: absolute; width:260px; right: 0px; top: 0px;">
        <button class="btn btn-success btn-circle btn-lg" title="点我刷新" type="button" id="rightreigh"><i
                class="fa fa-refresh"></i>
        </button>
    </table>
</div>
<div class="row">
    <div class="col-sm-12">
        <div class="wrapper wrapper-content">
            <div class="row animated fadeInRight">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="" id="ibox-content">
                            <div id="vertical-timeline" class="vertical-container light-timeline">
                                <#if events?? && (events?size>0) >
                                <#--集合根据时间倒序输出-->
                                    <#list events?sort_by("eventTime")?reverse as event>
                                    <#--好友事件-->
                                        <#if event.type==("FRIEND_REQUEST_EVENT")>
                                         <div class="vertical-timeline-block">
                                             <div class="vertical-timeline-icon navy-bg">
                                                 <i class="fa fa-home"></i>
                                             </div>
                                             <div class="vertical-timeline-content">
                                                 <h2>${event.eventName}</h2>
                                                 <p>${event.eventDescription}</p>
                                                 <#if event.eventName == "添加好友">
                                                    <button id="friendRequest"
                                                            href="${basePath}/sharedlFriendRequest/goRequestFirend/${Session.users.id}/1"
                                                            class="btn btn-sm btn-primary"
                                                            onclick="friendRequestTopage()">添加好友
                                                    </button>
                                                 <#elseif event.eventName=="好友添加请求">
                                                    <button id="friendRequest"
                                                            href="${basePath}/sharedlFriendRequest/goFirendRequest/${Session.users.id}/1"
                                                            class="btn btn-sm btn-primary"
                                                            onclick="friendRequestTopage()">好友请求
                                                    </button>
                                                 </#if>
                                                 <span class="vertical-date"><br>
                                                    <small>${event.eventTime?datetime}</small>
                                                 </span>
                                             </div>
                                         </div>
                                        <#elseif event.type==("BLOG_EVENT")>
                                            <div class="vertical-timeline-block">
                                                <div class="vertical-timeline-icon blue-bg">
                                                    <i class="fa fa-file-text"></i>
                                                </div>
                                                <div class="vertical-timeline-content">
                                                    <h2>${event.eventName}</h2>
                                                    <p>${event.eventDescription}</p>
                                                    <button id="blog"
                                                            href="${basePath}/shareBlogs/goBlos/${Session.users.id}"
                                                            class="btn btn-sm btn-success" onclick="blogTopage()"> 好友博客
                                                    </button>
                                                    <span class="vertical-date"><br>
                                                    <small>${event.eventTime?datetime}</small>
                                                 </span>
                                                </div>
                                            </div>
                                        <#elseif event.type==("POST_EVENT")>
                                            <div class="vertical-timeline-block">
                                                <div class="vertical-timeline-icon blue-bg">
                                                    <i class="fa fa-briefcase"></i>
                                                </div>
                                                <div class="vertical-timeline-content">
                                                    <h2>给张三发送文档</h2>
                                                    <p>发送上年度《销售业绩报告》</p>
                                                    <button id="tiezi" href="#" class="btn btn-sm btn-success"
                                                            onclick="tieziTopage()"> 下载文档
                                                    </button>
                                                    <span class="vertical-date">
                                                      今天 <br>
                                                      <small>2月3日</small>
                                                    </span>
                                                </div>
                                            </div>
                                        </#if>
                                    </#list>
                                <#else >
                                    <div align="center">
                                        <h2>您最近没有事件发生...</h2>
                                    </div>
                                </#if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
         <#if events?? &&(events?size>0) >
             <div class="text-center float-e-margins p-md">
                 <span>预览：</span>
                 <a href="#" class="btn btn-xs btn-primary" id="lightVersion">浅色</a>
                 <a href="#" class="btn btn-xs btn-primary" id="darkVersion">深色</a>
                 <a href="#" class="btn btn-xs btn-primary" id="leftVersion">布局切换</a>
             </div>
         </#if>
    </div>
</div>

<script>
    $(document).ready(function () {
        $("#rightreigh").click(function () {
            location.reload();
        });
    });

    /*好友请求跳转页面*/
    function friendRequestTopage() {
        var dataUrl = $("#friendRequest").attr('href'),
                dataIndex = $("#friendRequest").data('index'),
                menuName = $.trim($("#friendRequest").text());
        parent.addMenuTab(dataUrl, menuName, dataIndex);
    }

    /*博客请求跳转页面*/
    function blogTopage() {
        var dataUrl = $("#blog").attr('href'),
                dataIndex = $("#blog").data('index'),
                menuName = $.trim($("#blog").text());
        parent.addMenuTab(dataUrl, menuName, dataIndex);
    }

    /*论坛请求跳转页面*/
    function tieziTopage() {
        var dataUrl = $("#tiezi").attr('href'),
                dataIndex = $("#tiezi").data('index'),
                menuName = $.trim($("#tiezi").text());
        parent.addMenuTab(dataUrl, menuName, dataIndex);
    }

    $(document).ready(function () {
        // Local script for demo purpose only
        $('#lightVersion').click(function (event) {
            event.preventDefault()
            $('#ibox-content').removeClass('ibox-content');
            $('#vertical-timeline').removeClass('dark-timeline');
            $('#vertical-timeline').addClass('light-timeline');
        });

        $('#darkVersion').click(function (event) {
            event.preventDefault()
            $('#ibox-content').addClass('ibox-content');
            $('#vertical-timeline').removeClass('light-timeline');
            $('#vertical-timeline').addClass('dark-timeline');
        });

        $('#leftVersion').click(function (event) {
            event.preventDefault()
            $('#vertical-timeline').toggleClass('center-orientation');
        });


    });
</script>
</body>
