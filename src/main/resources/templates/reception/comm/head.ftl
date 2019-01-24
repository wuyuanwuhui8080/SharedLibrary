<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>共享资源库</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <#include "script.ftl">
    <style>

        .sk-spinner-three-bounce.sk-spinner {
            margin: 0 auto;
            width: 70px;
            text-align: center;
        }

        .sk-spinner-three-bounce div {
            width: 18px;
            height: 18px;
            background-color: #1ab394;
            border-radius: 100%;
            display: inline-block;
            -webkit-animation: sk-threeBounceDelay 1.4s infinite ease-in-out;
            animation: sk-threeBounceDelay 1.4s infinite ease-in-out;
            /* Prevent first frame from flickering when animation starts */
            -webkit-animation-fill-mode: both;
            animation-fill-mode: both;
        }

        .sk-spinner-three-bounce .sk-bounce1 {
            -webkit-animation-delay: -0.32s;
            animation-delay: -0.32s;
        }

        .sk-spinner-three-bounce .sk-bounce2 {
            -webkit-animation-delay: -0.16s;
            animation-delay: -0.16s;
        }
    </style>
</head>
<body>
<div class="fly-header layui-bg-black">
    <div class="layui-container">
        <a class="fly-logo" href="${basePath}/sharedForum/goIndex">
            <img src="${basePath}/res/images/logo.png" height="44px" alt="layui">
        </a>
        <ul class="layui-nav fly-nav-user">
            <#if Session.users?? >
                <!-- 登入后的状态 -->
                <li class="layui-nav-item">
                    <a class="fly-nav-avatar" href="javascript:;">
                        <cite class="layui-hide-xs">${Session.users.realName}</cite>
                        <img src="${basePath}/images/${users.headImg}">
                    </a>
                    <dl class="layui-nav-child">

                        <dd><a href="${basePath}/sharedUsers/goIndex">
                            <i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>进入后台</a>
                        </dd>
                        <dd>
                            <a href="${basePath}/sharedForum/gohome/${users.id}">
                                <i class="layui-icon">&#xe609;</i>我的主页
                            </a>
                        </dd>
                        <dd><a href="${basePath}/sharedForum/goUserIndex">
                            <i class="layui-icon">&#xe612;</i>用户中心</a>
                        </dd>
                        <dd><a href="${basePath}/sharedForum/goMessage">
                            <i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息</a>
                        </dd>

                        <hr style="margin: 5px 0;">
                        <dd><a href="${basePath}/sharedUsers/forumLoginOut" style="text-align: center;">退出</a></dd>
                    </dl>
                </li>
            <#else >
                <!-- 未登入的状态 -->
                <li class="layui-nav-item">
                    <a class="iconfont icon-touxiang layui-hide-xs" href="/sharedUsers/goForumLogin"></a>
                </li>
                <li class="layui-nav-item">
                    <a href="/sharedUsers/goForumLogin">登入</a>
                </li>
                <li class="layui-nav-item">
                    <a href="/sharedUsers/goForumRegister">注册</a>
                </li>
            </#if>
        </ul>
    </div>
</div>

<div class="fly-panel fly-column">
    <div class="layui-container">
        <ul class="layui-clear">
            <li class="layui-hide-xs layui-this"><a href="${basePath}/sharedForum/goIndex">首页</a></li>
            <li><a href="jie/index.html">提问</a></li>
            <li><a href="jie/index.html">分享<span class="layui-badge-dot"></span></a></li>
            <li><a href="jie/index.html">讨论</a></li>
            <li><a href="jie/index.html">建议</a></li>
            <li><a href="jie/index.html">公告</a></li>
            <li><a href="jie/index.html">动态</a></li>
            <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><span class="fly-mid"></span></li>

            <!-- 用户登入后显示 -->
            <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="user/index.html">我发表的贴</a></li>
            <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="user/index.html#collection">我收藏的贴</a>
            </li>
        </ul>
        <div class="fly-column-right layui-hide-xs">
            <span class="fly-search"><i class="layui-icon"></i></span>
            <a href="javascript:;" class="layui-btn goWrite_forum">发表新帖</a>
        </div>
        <div class="layui-hide-sm layui-show-xs-block"
             style="margin-top: -10px; padding-bottom: 10px; text-align: center;">
            <a href="javascript:;" class="layui-btn goWrite_forum">发表新帖</a>
        </div>
    </div>
</div>
<script>
    var forumWirte = false;
    <#if Session.users??>
    var paths = "${basePath}";
        forumWirte = true;
    </#if>
</script>