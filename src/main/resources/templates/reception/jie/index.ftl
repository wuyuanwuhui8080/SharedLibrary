<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>基于 layui 的极简社区页面模版</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <#include "../comm/script.ftl">
</head>
<body>

<#include "../comm/head.ftl">

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
            <div class="fly-panel" style="margin-bottom: 0;">

                <div class="fly-panel-title fly-filter">
                    <a href="" class="layui-this">综合</a>
                    <span class="fly-filter-right layui-hide-xs">
                <#if exitHost>
                            <a href="${basePath}/sharedForum/toListForum?typeId=${typeId!}">按最新</a>
                            <span class="fly-mid"></span>
                           <a href="${basePath}/sharedForum/toListForum?exitHost=1" class="layui-this">按热议</a>
                <#else >
                        <a href="${basePath}/sharedForum/toListForum?typeId=${typeId!}&typeId=${typeId!}"
                           class="layui-this">按最新</a>
                            <span class="fly-mid"></span>
                        <a href="${basePath}/sharedForum/toListForum?exitHost=1&typeId=${typeId!}">按热议</a>
                </#if>
                    </span>
                </div>
                <input type="hidden" value="${typeId!}" class="typeId"/>
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
          <#else >
              <div class="fly-none">没有相关数据</div>
          </#if>
                </ul>

                <!-- <div class="fly-none">没有相关数据</div> -->

                <div style="text-align: center">
                    <div id="demo7"></div>
                </div>

            </div>
        </div>
        <div class="layui-col-md4">
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

            <div class="fly-panel">
                <div class="fly-panel-title">
                    广告
                </div>
                <div class="fly-panel-main">
                    <a href="" class="fly-zanzhu" style="background-color: #393D49;">虚席以待</a>
                </div>
            </div>

        </div>
    </div>
</div>

<#include "../comm/footer.html">

<script>
    var exitHostNmb = "${exitHostNmb!}";
    layui.use(['laypage', 'layer', 'jquery'], function () {
        var laypage = layui.laypage
                , layer = layui.layer
                , $ = layui.jquery;
        //完整功能
        laypage.render({
            elem: 'demo7'
            , count: ${page.total}
            , limit: 6
            , curr: ${page.pageNum}
            , layout: ['count', 'prev', 'page', 'next', 'refresh', 'skip']
            , jump: function (obj, first) {
                //首次不执行
                if (!first) {
                    var typeId = $(".typeId").val();
                    location.href = paths + "/sharedForum/toListForum?pageIndex=" + obj.curr + "&typeId=" + typeId + "&exitHost=" + exitHostNmb;
                }
            }
        });
    });
</script>

</body>
</html>