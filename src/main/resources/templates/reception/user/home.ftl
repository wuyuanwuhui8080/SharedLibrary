<#include "../comm/head.ftl">
<div class="fly-home fly-panel" style="background-image: url();">
    <img src="${basePath}/images/${users.headImg}" alt="${users.userName}">
    <h1>
    ${users.userName}(${users.realName})
        <#if users.sex==1>
            <i class="iconfont icon-nan"></i>
        <#else >
        <i class="iconfont icon-nv"></i>
        </#if>
    </h1>
    <p class="fly-home-info">
        <i class="iconfont icon-shijian"></i><span>${users.creationDate?date} 加入</span>
    </p>

    <#if users.individual?? && users.individual!=''>
        ${users.individual}
    <#else >
        这人比较懒，暂时没有...
    </#if>

    <div class="fly-sns" data-user="">
        <a href="javascript:;" class="layui-btn layui-btn-primary fly-imActive" data-type="addFriend">加为好友</a>
    </div>

</div>

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md6 fly-home-jie">
            <div class="fly-panel">
                <h3 class="fly-panel-title">${users.userName} 最近的提问</h3>
                <ul class="jie-row">
                    <#if (foryms?size > 0)>
                        <#list foryms?sort_by("creationDate")?reverse as forym>
                            <li>
                            <#--<span class="fly-jing">精</span>-->
                                <a href="${basePath}/sharedForum/goForumDetailed/${forym.id}"
                                   class="jie-title">${forym.title}</a>
                                <i>${forym.creationDate?date}</i>
                            <#--<em class="layui-hide-xs">1136阅/27答</em>-->
                            </li>
                        </#list>
                    <#else>
                      <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><i
                              style="font-size:14px;">没有发表任何求解</i></div>
                    </#if>
                </ul>
            </div>
        </div>

        <div class="layui-col-md6 fly-home-da">
            <div class="fly-panel">
                <h3 class="fly-panel-title">${users.userName} 最近的回答</h3>
                <ul class="home-jieda">
                    <#if (forumComments?size>0)>
                        <#list forumComments?sort_by("creationDate")?reverse as forumComment>
                             <li>
                                 <p>
                                     <span>${forumComment.creationDate}</span>
                                     在<a href="${basePath}/sharedForum/goForumDetailed/${forumComment.forumId}"
                                         target="_blank">${forumComment.sharedForum.title}</a>中回答：
                                 </p>
                                 <div class="home-dacontent">
                                     ${forumComment.content}
                                 </div>
                             </li>
                        </#list>

                    <#else>
                        <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;">
                            <span>没有回答任何问题</span></div>
                    </#if>

                </ul>
            </div>
        </div>
    </div>
</div>
<#include "../comm/tail.ftl">

<script src="../../res/layui/layui.js"></script>
<script>
    layui.cache.page = 'user';
    layui.cache.user = {
        username: '游客'
        , uid: -1
        , avatar: '../../res/images/avatar/00.jpg'
        , experience: 83
        , sex: '男'
    };
    layui.config({
        version: "3.0.0"
        , base: '../../res/mods/'
    }).extend({
        fly: 'index'
    }).use('fly');
</script>

</body>
</html>