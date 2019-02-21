<#--导入头部-->
<#include "../comm/head.ftl">

  <div class="fly-panel fly-panel-user" pad20>
      <div class="layui-tab layui-tab-brief" lay-filter="user">
          <ul class="layui-tab-title" id="LAY_mine">
              <li data-type="mine-jie" lay-id="index" class="layui-this">我发的帖（<span>${page.total}</span>）</li>
              <li data-type="collection" data-url="/collection/find/" lay-id="collection">我收藏的帖（<span>${listSize}</span>）
              </li>
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
                      <#if (page.list?size > 0)>
                          <#list page.list?sort_by("creationDate")?reverse as forym>
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
                  <ul class="mine-view jie-row" id="uiId">
                  <#-- <#list list as li>
                          <li>
                              <a class="jie-title" href="${basePath}/sharedForum/goForumDetailed/${li.forumId}" target="_blank">${li.title}</a>
                              <i>收藏于${li.creatDate}</i><button class="layui-btn layui-btn-danger layui-btn-sm" style="float: right">删除</button></li>
                      </#list>-->
                  </ul>
                  <div id="LAY_page1"></div>
              </div>
          </div>
      </div>
  </div>

<#include "../comm/footer.html">

</body>
</html>
<script>
    layui.use('laypage', function () {
        var laypage = layui.laypage;
        // 我的帖子分页
        laypage.render({
                    elem: 'LAY_page' //注意，这里的 test1 是 ID，不用加 # 号
                    , limit: 12
                    , count: ${page.total} //数据总数，从服务端得到
                    , curr: ${page.pageNum}
                    , jump: function (obj, first) {
                        //首次不执行
                        if (!first) {
                            location.href = paths + "/sharedForum/goUserIndex?pageIndex=" + obj.curr;
                        }
                    }
                }
        )
        ;

        // 后台的list数据
        var data = ${list!}

                // 收藏的分页
                laypage.render({
                    elem: 'LAY_page1'
                    , count: (data == null || data == "") ? 0 : data.length
                    , limit: 12
                    , jump: function (obj) {
                        //模拟渲染
                        document.getElementById('uiId').innerHTML = function () {
                            var arr = []
                                    , thisData = data.concat().splice(obj.curr * obj.limit - obj.limit, obj.limit);
                            layui.each(thisData, function (index, item) {
                                var div = '<li>\n' +
                                        '                              <a class="jie-title" href="${basePath}/sharedForum/goForumDetailed/' + item.forumId + '" target="_blank">' + item.title + '</a>\n' +
                                        '                              <i>收藏于' + item.creatDate + '</i><button class="layui-btn layui-btn-danger layui-btn-sm shouchangDel" style="float: right" forumId="' + item.forumId + '" >删除</button></li>';
                                arr.push(div);
                            });
                            return arr.join('');
                        }();
                    }
                });
    });

    $(function () {
        /**
         * 删除收藏
         */
        $("body").on("click", ".shouchangDel", function () {
            var obj = $(this);
            layer.confirm("确认要取消该收藏吗？", {icon: 3, title: "提示"}, function (index) {
                var forumId = obj.attr("forumId");
                layer.close(index);
                app.loadJson(paths + "/sharedForum/cancelTheCollection", {forumId: forumId}, function (date) {
                    layer.msg("取消成功!", {icon: 6});
                    obj.parent().remove();
                });
            })
        })
    });
    layui.use('element', function () {
        var element = layui.element;

        //获取hash来切换选项卡，假设当前地址的hash为lay-id对应的值
        var layid = location.hash.replace(/^#user=/, '');
        element.tabChange('user', layid); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项

        //监听Tab切换，以改变地址hash值
        element.on('tab(user)', function () {
            location.hash = 'user=' + this.getAttribute('lay-id');
        });
    });
</script>