<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>最近事情</title>
    <#include "../comm/script.ftl">
</head>

<body class="gray-bg">
<div class="row">
    <div class="col-sm-12">
        <div class="wrapper wrapper-content">
            <div class="row animated fadeInRight">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="" id="ibox-content">
                            <div id="vertical-timeline" class="vertical-container light-timeline">
                                <#if (events?size>0) >
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
                                                    <button id="newPage"
                                                            href="${basePath}/sharedlFriendRequest/goRequestFirend/${Session.users.id}/1"
                                                            class="btn btn-sm btn-primary" onclick="topage()">添加好友
                                                    </button>
                                                 <#elseif event.eventName=="好友添加请求">
                                                    <button id="newPage"
                                                            href="${basePath}/sharedlFriendRequest/goFirendRequest/${Session.users.id}/1"
                                                            class="btn btn-sm btn-primary" onclick="topage()">好友请求
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
                                                    <h2>给张三发送文档</h2>
                                                    <p>发送上年度《销售业绩报告》</p>
                                                    <a href="#" class="btn btn-sm btn-success"> 下载文档 </a>
                                                    <span class="vertical-date">
                                                      今天 <br>
                                                        <small>2月3日</small>
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
                                                    <a href="#" class="btn btn-sm btn-success"> 下载文档 </a>
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
         <#if (events?size>0) >
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
    /*跳转页面*/
    function topage() {
        var dataUrl = $("#newPage").attr('href'),
                dataIndex = $("#newPage").data('index'),
                menuName = $.trim($("#newPage").text());
        parent.addMenuTab(dataUrl, menuName, dataIndex);
    }

    /*$(function () {
        var div = "";
        //页面加载完成ajax请求数据
        $.get("/sharedUsers/showEvent", function (data) {
            div += '<div class="vertical-timeline-block">\n' +
                    '<div class="vertical-timeline-icon navy-bg">\n' +
                    '<i class="fa fa-briefcase"></i>\n' +
                    '</div>\n' +
                    '<div class="vertical-timeline-content">\n' +
                    '<h2>好友请求</h2>\n' +
                    '<p>' + data[0].requesterName + '(' + data[0].requesterUsername + ')</p>\n' +
                    '<a href="#" class="btn btn-sm btn-primary"> 更多信息</a>\n' +
                    '<span class="vertical-date"><br>\n' +
                    '<small>' + data[0].requestTime + '</small>\n' +
                    '</span>\n' +
                    '</div>\n' +
                    '</div>\n';
        });
    });*/


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
