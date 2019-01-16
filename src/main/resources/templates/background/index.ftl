<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>主页</title>

    <meta name="keywords" content="内部后台">
    <#include "comm/script.ftl">
    <script src="${basePath}/js/users/users_modifier.js"></script>
    <script src="${basePath}/js/custom_up_img.js"></script>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element" style="text-align: center;">
                        <span id="MyLiImg"><img alt="image" class="img-circle" width="70px"
                                                src="${basePath}/images/${users.headImg}"/></span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs MyUsersName"><strong
                                       class="font-bold">欢迎你：${users.realName}</strong></span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a id="modal-926547" href="${basePath}/sharedUsers/goUpload" class="J_menuItem">修改头像</a>
                            </li>
                            <li><a class="J_menuItem" href="${basePath}/sharedUsers/lookProfile/${users.id}">个人资料</a>
                            </li>
                            <li><a class="J_menuItem" href="${basePath}/sharedUsers/goUpdateUsers">修改资料</a>
                            </li>
                            <li><a class="J_menuItem" href="${basePath}/sharedUsers/goOldPassword/${users.id}">修改密码</a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="${basePath}/sharedUsers/loginOut">安全退出</a>
                            </li>
                        </ul>
                    </div>
                    <!-- 模态框（Modal） -->
                    <div id="modelHeadImgId" class="logo-element"><a href="#">
                        <img class="img-circle" src="${basePath}/images/${users.headImg}" width="50px"></a>
                    </div>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-home"></i>
                        <span class="nav-label">主页</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="${basePath}/sharedUsers/GoIndex" data-index="0">最近事情</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-user"></i>
                        <span class="nav-label">好友管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class=" J_menuItem" href="${basePath}/sharedFriends/goSearchFriend">查找用户</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${basePath}/sharedFriends/goListFriendList/${users.id}">全部好友</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${basePath}/shareBlogs/goBlos/${users.id}">好友博客</a>
                        </li>
                        <li>
                            <a class="J_menuItem"
                               href="${basePath}/sharedlFriendRequest/goFirendRequest/${Session.users.id}/1">好友请求</a>
                        </li>
                        <li>
                            <a class="J_menuItem"
                               href="${basePath}/sharedlFriendRequest/goRequestFirend/${Session.users.id}/1">好友添加</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${basePath}/sharedFriends/goChar/${Session.users.id}">好友聊天</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="${basePath}/shareBlogs/goEditorsBlos">写博客</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="mailbox.html"><i class="fa fa-envelope"></i>
                        <span class="nav-label">信箱
                            <span class="fa arrow"></span>
                        </span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="${basePath}/sharedReceiveMail/emailIndex">收件箱</a></li>
                        <li><a class="J_menuItem" href="${basePath}/sharedReceiveMail/emailCompose">写信</a></li>
                    </ul>
                </li>
                <#--<li>
                    <a href="#">
                        <i class="fa fa-road"></i>
                        <span class="nav-label">论坛管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="${basePath}/sharediForum/goWriteInvitation">写博客</a></li>
                    &lt;#&ndash;// TODO: 2019\1\15 0015 判断权限显示&ndash;&gt;
                        <li><a class="J_menuItem" hrefreception="${basePath}/sharediForum/#">写论坛</a></li>
                        <li><a class="J_menuItem" href="${basePath}/sharediForum/#">管理论坛</a></li>
                        <li><a class="J_menuItem" href="${basePath}/sharediForum/#">管理论坛分类</a></li>
                    </ul>
                </li>-->
                <li>
                    <a href="#"><i class="fa fa-edit"></i> <span class="nav-label">用户管理</span>
                        <span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="${basePath}/sharedUsers/goUserList">全部用户</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-picture-o"></i> <span class="nav-label">休闲游戏</span><span
                            class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="${basePath}/gogluttonous">贪吃蛇</a>
                        </li>
                        <li><a class="J_menuItem" href="${basePath}/goAAutomobile">竞速赛车</a>
                        </li>
                        <li><a class="J_menuItem" href="${basePath}/gozombie">植物大战僵尸</a>
                        </li>
                        <li><a class="J_menuItem" href="${basePath}/goFragment">打砖块</a>
                        </li>
                        <li><a class="J_menuItem" href="${basePath}/godonkeyJump">秃驴跳跃</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i
                        class="fa fa-bars"></i> </a>
                    <a href="#"></a>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                            <i class="fa fa-envelope"></i> <span class="label label-warning">16</span>
                        </a>
                        <ul class="dropdown-menu dropdown-messages">
                            <li class="m-t-xs">
                                <div class="dropdown-messages-box">
                                    <a href="profile.html" class="pull-left">
                                        <img alt="image" class="img-circle" src="img/a7.jpg">
                                    </a>
                                    <div class="media-body">
                                        <small class="pull-right">46小时前</small>
                                        <strong>小四</strong> 这个在日本投降书上签字的军官，建国后一定是个不小的干部吧？
                                        <br>
                                        <small class="text-muted">3天前 2014.11.8</small>
                                    </div>
                                </div>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="dropdown-messages-box">
                                    <a href="profile.html" class="pull-left">
                                        <img alt="image" class="img-circle" src="img/a4.jpg">
                                    </a>
                                    <div class="media-body ">
                                        <small class="pull-right text-navy">25小时前</small>
                                        <strong>国民岳父</strong> 如何看待“男子不满自己爱犬被称为狗，刺伤路人”？——这人比犬还凶
                                        <br>
                                        <small class="text-muted">昨天</small>
                                    </div>
                                </div>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="text-center link-block">
                                    <a class="J_menuItem" href="mailbox.html">
                                        <i class="fa fa-envelope"></i> <strong> 查看所有消息</strong>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                            <i class="fa fa-bell"></i> <span class="label label-primary">8</span>
                        </a>
                        <ul class="dropdown-menu dropdown-alerts">
                            <li>
                                <a href="mailbox.html">
                                    <div>
                                        <i class="fa fa-envelope fa-fw"></i> 您有16条未读消息
                                        <span class="pull-right text-muted small">4分钟前</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="profile.html">
                                    <div>
                                        <i class="fa fa-qq fa-fw"></i> 3条新回复
                                        <span class="pull-right text-muted small">12分钟钱</span>
                                    </div>
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <div class="text-center link-block">
                                    <a class="J_menuItem" href="notifications.html">
                                        <strong>查看所有 </strong>
                                        <i class="fa fa-angle-right"></i>
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li class="dropdown hidden-xs">
                        <a class="right-sidebar-toggle" aria-expanded="false">
                            <i class="fa fa-tasks"></i> 主题
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="${basePath}/sharedUsers/GoIndex">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive"><a>定位当前选项卡</a>
                    </li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                    </li>
                </ul>
            </div>
            <a href="${basePath}/sharediForum/goIndex" class="roll-nav roll-right J_tabExit"><i
                    class="fa fa fa-sign-out"></i> 论坛</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${basePath}/sharedUsers/GoIndex"
                    frameborder="0"
                    data-id="${basePath}/sharedUsers/GoIndex" seamless></iframe>

        </div>
        <div class="footer">
            <div class="pull-right"><a href="tencent://AddContact/?fromId=45&fromSubId=1&subcmd=all&uin=66741255"
                                       target="_blank">添加作者QQ</a>
            </div>
        </div>
    </div>
    <!--右侧部分结束-->
    <!--右侧边栏开始-->
    <div id="right-sidebar">
        <div class="sidebar-container">
            <ul class="nav nav-tabs navs-3">
                <li class="active">
                    <a data-toggle="tab" href="#tab-1">
                        <i class="fa fa-gear"></i> 主题
                    </a>
                </li>
            </ul>

            <div class="tab-content">
                <div id="tab-1" class="tab-pane active">
                    <div class="sidebar-title">
                        <h3><i class="fa fa-comments-o"></i> 主题设置</h3>
                        <small><i class="fa fa-tim"></i> 你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>
                    </div>
                    <div class="skin-setttings">
                        <div class="title">主题设置</div>
                        <div class="setings-item">
                            <span>收起左侧菜单</span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox"
                                           id="collapsemenu">
                                    <label class="onoffswitch-label" for="collapsemenu">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                            <span>固定顶部</span>

                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="fixednavbar" class="onoffswitch-checkbox"
                                           id="fixednavbar">
                                    <label class="onoffswitch-label" for="fixednavbar">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                                <span>
                        固定宽度
                    </span>

                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="boxedlayout" class="onoffswitch-checkbox"
                                           id="boxedlayout">
                                    <label class="onoffswitch-label" for="boxedlayout">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="title">皮肤选择</div>
                        <div class="setings-item default-skin nb">
                                <span class="skin-name ">
                         <a href="#" class="s-skin-0">
                             默认皮肤
                         </a>
                    </span>
                        </div>
                        <div class="setings-item blue-skin nb">
                                <span class="skin-name ">
                        <a href="#" class="s-skin-1">
                            蓝色主题
                        </a>
                    </span>
                        </div>
                        <div class="setings-item yellow-skin nb">
                                <span class="skin-name ">
                        <a href="#" class="s-skin-3">
                            黄色/紫色主题
                        </a>
                    </span>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!--右侧边栏结束-->
</div>

</body>
</html>
