<#--导入头部-->
<#include "../comm/head.ftl">
<script src="${basePath}/js/reception/message.js"></script>
  <div class="fly-panel fly-panel-user" pad20>
      <div class="layui-tab layui-tab-brief" lay-filter="user" id="LAY_msg" style="margin-top: 15px;">
          <div id="LAY_minemsg" style="margin-top: 10px;">

              <ul class="mine-msg classLiUl">
                  <#if list?? && (list?size>0) >
                      <a class="layui-btn layui-btn-danger" href="javascript:;"
                         id="LAY_delallmsg">清空全部消息
                      </a>
                      <#list list?sort_by("creationTime")?reverse as li>
                          <li data-id="123" id="messageLi">
                              <blockquote class="layui-elem-quote">
                                  ${li.msg}<#--<a
                                      target="_blank"
                                      href="/jie/8153.html/page/0em-1489505778669"><cite>layui后台框架</cite></a>-->
                              </blockquote>
                              <p><a href="javascript:;"
                                    class="layui-btn layui-btn-small layui-btn-danger deleteMessage"
                                    message="${li.msg}"
                                    messageDate='${li.creationTime}'>删除</a>
                              </p>
                          </li>
                      </#list>/#it
                  <#else>
                        <div class="fly-none">您暂时没有最新消息</div>
                  </#if>

              </ul>
          </div>
      </div>
  </div>
<#include "../comm/tail.ftl">
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