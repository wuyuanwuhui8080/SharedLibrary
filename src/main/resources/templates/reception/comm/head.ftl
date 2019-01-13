<script src="${basePath}/js/reception/head.js"></script>
<header class="header-navigation" id="header" style="background-color: #efefef;">
    <nav>
        <div class="logo"><a href="http://www.yangqq.com">论坛博客</a></div>
        <h2 id="mnavh"><span class="navicon"></span></h2>
        <ul id="starlist">
            <li><a href="index.ftl">网站首页</a></li>
            <li><a href="#" id="goWrite_forum">发论坛</a></li>
            <li><a href="reception/share.html">模板分享</a></li>
            <li><a href="list.html">博客日记</a></li>
            <li class="menu"><a href="reception/fengmian.ftl">学无止境</a>
                <ul class="sub">
                    <li><a href="#">慢生活</a></li>
                    <li><a href="#">美文欣赏</a></li>
                </ul>
            </li>
            <li>
              <@shiro.guest>
                <a href="${basePath}/sharedUsers/goLogin">去登录</a>
              </@shiro.guest>
                <@shiro.user>
                    <a href="${basePath}/sharedUsers/goIndex">个人中心</a>
                </@shiro.user>
            </li>
        </ul>
        <div class="searchbox">
            <div id="search_bar" class="search_bar">
                <form id="searchform" action="[!--news.url--]e/search/index.php" method="post" name="searchform">
                    <input class="input" style="background-color: #efefef;" placeholder="想搜点什么呢.." type="text" name="keyboard" id="keyboard">
                    <input type="hidden" name="show" value="title"/>
                    <input type="hidden" name="tempid" value="1"/>
                    <input type="hidden" name="tbname" value="news">
                    <input type="hidden" name="Submit" value="搜索"/>
                    <p class="search_ico"><span></span></p>
                </form>
            </div>
        </div>
    </nav>
</header>
<script>
    var path = "${basePath}";
    var userName = "${(users.userName)?default('')}";
</script>