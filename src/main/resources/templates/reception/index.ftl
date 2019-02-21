<#include "comm/head.ftl">

<div class="layui-container">
    <div class="layui-row layui-col-space15">
    <div class="layui-col-md8">
        <div class="fly-panel">
            <div class="fly-panel-title fly-filter">
                <a>置顶</a>
                <a href="#signin" class="layui-hide-sm layui-show-xs-block fly-right" id="LAY_goSignin"
                   style="color: #FF5722;">去签到</a>
            </div>
            <ul class="fly-list">
                <#if listStipk?? && (listStipk?size > 0)>
                    <#list listStipk as li>
                            <li>
                                <a href="${basePath}/sharedForum/gohome/${li.sharedUsers.id}" class="fly-avatar">
                                    <img src="${basePath}/images/${li.sharedUsers.headImg}"
                                         alt="${li.sharedUsers.realName}">
                                </a>
                                <h2>
                                    <a class="layui-badge">${li.forumType.forumType}</a>
                                    <a href="${basePath}/sharedForum/goForumDetailed/${li.id}">${li.title}</a>
                                </h2>
                                <div class="fly-list-info">
                                    <a href="${basePath}/sharedForum/gohome/${li.sharedUsers.id}" link>
                                        <cite>${li.sharedUsers.realName}</cite>
                                    </a>
                                    <span>${li.creationDate?datetime}</span>
                                    <!--<span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>-->
                                    <span class="fly-list-nums">
                <i class="iconfont icon-pinglun1" title="回答"></i> ${li.commCounts}
              </span>
                                </div>
                                <div class="fly-list-badge">
                                    <span class="layui-badge fly-badge-accept layui-hide-xs">置顶</span>
                                </div>
                            </li>
                    </#list>
                <#else >
                 <div align="center" style="color: #999999;margin: 10px">没有相关数据</div>
                </#if>
            </ul>
        </div>

    <div class="fly-panel" style="margin-bottom: 0;">

        <div class="fly-panel-title fly-filter">
            <a href="" class="layui-this">综合</a>
            <span class="fly-filter-right layui-hide-xs">
                        <#if exitHost>
                            <a href="${basePath}/sharedForum/goIndex">按最新</a>
                            <span class="fly-mid"></span>
                           <a href="${basePath}/sharedForum/goIndex?exitHost=1" class="layui-this">按热议</a>
                        <#else >
                        <a href="${basePath}/sharedForum/goIndex" class="layui-this">按最新</a>
                            <span class="fly-mid"></span>
                        <a href="${basePath}/sharedForum/goIndex?exitHost=1">按热议</a>
                        </#if>

            </span>
        </div>

    <ul class="fly-list">
                    <#if page.list?? && (page.list?size>0)>
                    <#if exitHost>
                        <#list page.list?sort_by("commCounts")?reverse as forum>
                        <li>
                            <a href="${basePath}/sharedForum/goForumDetailed/${forum.id}" class="fly-avatar">
                                <img src="${basePath}/images/${forum.sharedUsers.headImg}"
                                     alt="${forum.sharedUsers.realName}">
                            </a>
                            <h2>
                                <a class="layui-badge">${forum.forumType.forumType}</a>
                                <a href="${basePath}/sharedForum/goForumDetailed/${forum.id}">${forum.title}</a>
                            </h2>
                            <div class="fly-list-info">
                                <a href="${basePath}/sharedForum/gohome/${forum.sharedUsers.id}" link>
                                    <cite>${forum.sharedUsers.realName}</cite>
                                </a>
                                <span>${forum.creationDate?datetime}</span>
                                <!--<span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>-->
                                <span class="fly-list-nums">
                <i class="iconfont icon-pinglun1" title="回答"></i> ${forum.commCounts}
              </span>
                            </div>
                            <div class="fly-list-badge">
                                <!--<span class="layui-badge layui-bg-red">精帖</span>-->
                            </div>
                        </li>
                        </#list>
                    <#else >
                        <#list page.list?sort_by("creationDate")?reverse as forum>
                        <li>
                            <a href="${basePath}/sharedForum/goForumDetailed/${forum.id}" class="fly-avatar">
                                <img src="${basePath}/images/${forum.sharedUsers.headImg}"
                                     alt="${forum.sharedUsers.realName}">
                            </a>
                            <h2>
                                <a class="layui-badge">${forum.forumType.forumType}</a>
                                <a href="${basePath}/sharedForum/goForumDetailed/${forum.id}">${forum.title}</a>
                            </h2>
                            <div class="fly-list-info">
                                <a href="${basePath}/sharedForum/gohome/${forum.sharedUsers.id}" link>
                                    <cite>${forum.sharedUsers.realName}</cite>
                                </a>
                                <span>${forum.creationDate?datetime}</span>
                                <!--<span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>-->
                                <span class="fly-list-nums">
                <i class="iconfont icon-pinglun1" title="回答"></i> ${forum.commCounts}
              </span>
                            </div>
                            <div class="fly-list-badge">
                                <!--<span class="layui-badge layui-bg-red">精帖</span>-->
                            </div>
                        </li>
                        </#list>
                    </#if>
                </ul>
                <div style="text-align: center">
                    <div class="laypage-main">
                        <a href="${basePath}/sharedForum/toListForum" class="laypage-next">更多求解</a>
                    </div>
                </div>

            </div>
        </div>
        <div class="layui-col-md4">

            <div class="fly-panel">
                <h3 class="fly-panel-title">温馨提示</h3>
                <h3 style="color: #999999;font-size: 15px;" align="center">欢迎来到共享资源库 论坛专区 ，在这里可以找到各自资料，如果不懂就发帖提问，祝你们学习愉快</h3>
            </div>


            <div class="fly-panel fly-rank fly-rank-reply" id="LAY_replyRank">
                <h3 class="fly-panel-title">回贴周榜</h3>
                 <#if forumCommentList?? && (forumCommentList?size > 0)>
                <dl>
                     <#list forumCommentList?sort_by("commentCount")?reverse  as li>
                             <dd>
                                 <a href="${basePath}/sharedForum/gohome/${li.sharedUsers.id}">
                                     <img src="${basePath}/images/${li.sharedUsers.headImg}"><cite>${li.sharedUsers.realName}</cite><i>${li.commentCount}
                                     次回答</i>
                                 </a>
                             </dd>
                     </#list>
                 </#if>
                    </dl>
                    <#else >
                 <div align="center" style="color: #999999">没有相关数据</div>
                    </#if>
    </div>

        <dl class="fly-panel fly-list-one">
            <dt class="fly-panel-title">本周热议</dt>
                <#if sevenDays?? && (sevenDays?size > 0)>
                    <#list sevenDays as li>
                        <dd>
                            <a href="${basePath}/sharedForum/goForumDetailed/${li.id}">${li.title}</a>
                            <span><i class="iconfont icon-pinglun1"></i> ${li.commCounts}</span>
                        </dd>
                    </#list>
                <#--没有数据的时候-->
                <#else >
                 <div class="fly-none">没有相关数据</div>
                </#if>
        </dl>

    </div>
    </div>
</div>
<#include "comm/footer.html">

<script src="${basePath}/res/layui/layui.js"></script>
<script>
    layui.cache.page = '';
    layui.cache.user = {
        username: '游客'
        , uid: -1
        , avatar: '../res/images/avatar/00.jpg'
        , experience: 83
        , sex: '男'
    };
    layui.config({
        version: "3.0.0"
        , base: '../res/mods/' //这里实际使用时，建议改成绝对路径
    }).extend({
        fly: 'index'
    }).use('fly');
</script>

<script type="text/javascript">
    var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cspan id='cnzz_stat_icon_30088308'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "w.cnzz.com/c.php%3Fid%3D30088308' type='text/javascript'%3E%3C/script%3E"));
</script>

</body>
</html>