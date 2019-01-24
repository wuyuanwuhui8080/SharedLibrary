<#--导入头部-->
<#include "../comm/head.ftl">
  <div class="fly-panel fly-panel-user" pad20>
      <div class="layui-tab layui-tab-brief" lay-filter="user" id="LAY_msg" style="margin-top: 15px;">
          <div id="LAY_minemsg" style="margin-top: 10px;">

              <ul class="mine-msg">
                  <#if Reply?? && (Reply?size>0) >
                      <a class="layui-btn layui-btn-danger" href="${basePath}/sharedForum/delAllMessage"
                         id="LAY_delallmsg">清空全部消息
                      </a>
                      <#list Reply?sort_by("eventTime")?reverse as reply>
                          <li data-id="123">
                              <blockquote class="layui-elem-quote">
                              <#--拆分字符串-->
                                  <a href="/jump?username=Absolutely" target="_blank">
                                      <cite>${reply.eventDescription?substring(0,reply.eventDescription?index_of(","))}</cite>
                                  </a>
                                  ${reply.eventDescription?substring(reply.eventDescription?index_of(",")+1,reply.eventDescription?index_of(",")+8)}
                                  <a href="${basePath}/sharedForumComment/toForumDetailed/${reply.eventid}"
                                     target="_blank">
                                      <cite>${reply.eventDescription?substring(reply.eventDescription?index_of(",")+9,reply.eventDescription?length)}</cite>
                                  </a>
                              </blockquote>
                              <p>
                                  <span>${reply.eventTime?datetime}</span>
                                  <a href="${basePath}/sharedForum/delMessage/${reply.eventid}"
                                     class="layui-btn layui-btn-small layui-btn-danger">
                                      删除
                                  </a>
                              </p>
                          </li>
                      </#list>
                  <#else>
                        <div class="fly-none">您暂时没有最新消息</div>
                  </#if>

              </ul>
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