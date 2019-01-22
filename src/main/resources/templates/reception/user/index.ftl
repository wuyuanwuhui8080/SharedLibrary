<#--导入头部-->
<#include "../comm/head.ftl">

  <div class="fly-panel fly-panel-user" pad20>
      <div class="layui-tab layui-tab-brief" lay-filter="user">
          <ul class="layui-tab-title" id="LAY_mine">
              <li data-type="mine-jie" lay-id="index" class="layui-this">我发的帖（<span>89</span>）</li>
              <li data-type="collection" data-url="/collection/find/" lay-id="collection">我收藏的帖（<span>16</span>）</li>
          </ul>
          <div class="layui-tab-content" style="padding: 20px 0;">
              <div class="layui-tab-item layui-show">

                  <table class="layui-table" lay-skin="line">
                      <colgroup>
                          <col width="150">
                          <col width="150">
                          <col width="200">
                          <col>
                      </colgroup>
                      <thead>
                      <tr>
                          <th>帖子名称</th>
                          <th>发帖时间</th>
                          <th>操作</th>
                      </tr>
                      </thead>
                      <tbody>
                      <#if (foryms?size > 0)>
                          <#list foryms?sort_by("creationDate")?reverse as forym>
                             <tr>
                                 <td><a href="${basePath}/sharedForum/goForumDetailed/${forym.id} "
                                        class="jie-title">${forym.title}</a></td>
                                 <td><i>${forym.creationDate?datetime}</i></td>
                                 <td><a class="mine-edit" href="/jie/edit/8116">编辑</a></td>
                             </tr>
                          </#list>
                      <#else>
                      <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><i
                              style="font-size:14px;">没有发表任何求解</i></div>
                      </#if>
                      </tbody>
                  </table>
                  <div id="LAY_page"></div>
              </div>
              <div class="layui-tab-item">
                  <ul class="mine-view jie-row">
                      <li>
                          <a class="jie-title" href="../jie/detail.html" target="_blank">基于 layui 的极简社区页面模版</a>
                          <i>收藏于23小时前</i></li>
                  </ul>
                  <div id="LAY_page1"></div>
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