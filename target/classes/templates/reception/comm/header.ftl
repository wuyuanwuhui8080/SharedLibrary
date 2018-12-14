<header><#assign basePath=springMacroRequestContext.contextPath>
    <div id="mnav">
        <div class="logo"><a href="/">论坛博客</a></div>
        <h2 id="mnavh"><span class="navicon"></span></h2>
        <ul id="starlist">
            <li><a href="list.html">学无止境</a></li>
            <li><a href="info.html">生活</a></li>
            <li><a href="shareinfo.html">发表文章</a></li>
        <li><a href="gbook.html">用户管理</a>
             <@shiro.guest>
                <li><a href="${basePath}/sharedUsers/goLogin">去登陆</a></li>
             </@shiro.guest>
            <@shiro.user>
<li><a href="gbook.html">个人中心</a></li>
            </@shiro.user>

            <li><a href="about.html">关于我们</a></li>
        </ul>
    </div>
    <script>
        window.onload = function () {
            var oH2 = document.getElementById("mnavh");
            var oUl = document.getElementById("starlist");
            oH2.onclick = function () {
                var style = oUl.style;
                style.display = style.display == "block" ? "none" : "block";
                oH2.className = style.display == "block" ? "open" : ""
            }
        }
    </script>
</header>