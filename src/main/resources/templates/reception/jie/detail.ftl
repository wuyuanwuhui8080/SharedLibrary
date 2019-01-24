<#include "../comm/head.ftl">

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8 content detail">
            <div class="fly-panel detail-box">
                <h1>${forumAndComment.forumTitle!}</h1>
                <div class="fly-detail-info">
                    <!-- <span class="layui-badge">审核中</span> -->
                    <span class="layui-badge layui-bg-green fly-detail-column">${forumAndComment.classifyName}</span>
                    <span class="layui-badge" style="background-color: #00C1B3;">${forumAndComment.typeName}</span>

                <#-- <span class="layui-badge" style="background-color: #999;">未结</span>-->
                    <!-- <span class="layui-badge" style="background-color: #5FB878;">已结</span> -->

                <#-- <span class="layui-badge layui-bg-black">置顶</span>
                 <span class="layui-badge layui-bg-red">精帖</span>-->

                    <div class="fly-admin-box" data-id="123">
                        <#if Session.users??>
                            <#if forumAndComment.forumUsersId == Session.users.id>
                                 <span class="layui-btn layui-btn-xs jie-admin" style="background-color: red" froumId="${forumAndComment.forumId}" id="deleteForum">删除</span>
                            </#if>
                         <#--<span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="1">加精</span>-->
                        <#if (Session.users.positionId == 3)>
                           <span class="layui-btn layui-btn-xs jie-admin forumOverhead" >置顶</span>
                        </#if>
                        </#if>
                        <!-- <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="0" style="background-color:#ccc;">取消置顶</span> -->
                        <!-- <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="0" style="background-color:#ccc;">取消加精</span> -->
                    </div>
                    <span class="fly-list-nums">
            <a href="#comment"><i class="iconfont" title="回答">&#xe60c;</i> ${forumAndComment.countReply}</a>
            <i class="iconfont" title="人气">&#xe60b;</i> 99999
          </span>
                </div>
                <div class="detail-about">
                    <a class="fly-avatar" href="../user/home.html">
                        <img src="${basePath}/images/${forumAndComment.forumUsersHeadImg}"
                             alt="贤心">
                    </a>
                    <div class="fly-detail-user">
                        <a href="../user/home.html" class="fly-link">
                            <cite>${forumAndComment.forumUsersRealName!}</cite>
                        </a>
                        <span>${forumAndComment.forumCreationDate?datetime}</span>
                    </div>
                    <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
                    <#if Session.users??>
                        <#if forumAndComment.forumUsersId == Session.users.id>
                             <span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="add.html">编辑此贴</a></span>
                        <#else >
                            <span>欢迎您</span>
                        </#if>
                        <#else >
                        <span>请登录在回复哦</span>
                    </#if>
                    </div>
                </div>
            <#--帖子内容-->
                <div class="detail-body photos">
                ${forumAndComment.forumContent!}
                </div>
            </div>

            <div class="fly-panel detail-box" id="flyReply">
                <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                    <legend>回帖</legend>
                </fieldset>

                <ul class="jieda" id="jieda">
                    <#if forumAndComment.commentBOList?? && (forumAndComment.commentBOList?size > 0)>
                        <#list forumAndComment.commentBOList?sort_by("commentCreationTime") as li>
                            <li data-id="111" class="jieda-daan" id="comm${li.commentId}">
                                <a name="item-1111111111"></a>
                                <div class="detail-about detail-about-reply">
                                    <a class="fly-avatar" href="">
                                        <img src="${basePath}/images/${li.commentUsersHeadImg}"
                                             alt=" ">
                                    </a>
                                    <div class="fly-detail-user">
                                        <a href="" class="fly-link">
                                            <cite>${li.commentUsersRealName} (${li.commentUsersUserName})</cite>
                                        </a>
                                        <span>
                                            <#--校验是否是自己-->
                                            <#if li.commentUsersId == forumAndComment.forumUsersId>
                                                (楼主)
                                            <#elseif (li.commentUsersPositionid == 2)>
                                                    <span style="color:#FF9E3F">（帖子管理员）</span>
                                            <#elseif  (li.commentUsersPositionid == 3)>
                                                    <span style="color:#5FB878">(系统管理员)</span>
                                            <#else >
                                            </#if>
                                            </span>
                                        <!--
                                        <span style="color:#999">（该号已被封）</span>
                                        -->
                                    </div>

                                    <div class="detail-hits">
                                        <span>${li.commentCreationTime?datetime}</span>
                                    </div>

                                    <i class="iconfont icon-caina" title="最佳答案"></i>
                                </div>
                                <div class="detail-body jieda-body photos">
                                    <p>${li.commentContent!}</p>
                                </div>
                                <div class="jieda-reply">
                                    <span class="GiveDIv">
                                    <#--点赞-->
                                    <#--判断当前评论有没有点赞-->
                                              <#if li.commentGive?? && li.commentGive != 0>
                                              <#--循环所有点赞-->
                                                  <#list forumAndComment.commentGiveBOList as reply>
                                                  <#--判断当前循环的点赞数是不是对应当前评论的-->
                                                      <#if reply.commentId == li.commentId>
                                                      <#--判断用户登录没有-->
                                                          <#if Session.users??>
                                                          <#--判断点赞的用户是不是当前登录用户，如果是就代表是自己点的赞-->
                                                              <#if reply.userId == Session.users.id>
                                                               <span class="jieda-zan zanok CommentGive">
                                                                <i class="iconfont icon-zan"
                                                                   commentId="${li.commentId!}"
                                                                   giveId="${reply.giveId!}" style="color: red"></i>
                                                                    <em>${li.commentGive}</em>
                                                              </span>
                                                              <#--结束循环-->
                                                                  <#break >
                                                              <#else >
                                                              <#--如果不是当前用户就判断有没有到最大的索引，如果到了最大的索引就代表，没有点赞-->
                                                                  <#if (reply_index+1) == forumAndComment.commentGiveBOList?size>
                                                                  <span class="jieda-zan zanok NoCommentGive">
                                                              <i class="iconfont icon-zan" commentId="${li.commentId}"
                                                                 style="color: #999999;"></i>
                                                                       <em>${li.commentGive}</em>
                                                                  </span>
                                                                  </#if>
                                                              </#if>
                                                          <#else >
                                                          <span class="jieda-zan zanok NoCommentGive">
                                                          <i class="iconfont icon-zan" commentId="${li.commentId}"
                                                             style="color: #999999;"></i>
                                                            <em>${li.commentGive}</em>
                                                          </span>
                                                              <#break >
                                                          </#if>
                                                      </#if>
                                                  </#list>
                                              <#else >
                                              <span class="jieda-zan zanok NoCommentGive">
                                                 <i class="iconfont icon-zan" commentId="${li.commentId}"
                                                    style="color: #999999;"></i>
                                                            <em>${li.commentGive}</em>
                                              </span>
                                              </#if>
                                    </span>
                                    <span class="CommentSpan" commentuserlName="${li.commentUsersUserName}">
                                        <i class="iconfont icon-svgmoban53"></i>
                                        回复
                                      </span>
                                    <div class="jieda-admin">
                                        <span type="edit">编辑</span>
                                        <span class="commentDel" commentId="${li.commentId}">删除</span>
                                        <!-- <span class="jieda-accept" type="accept">采纳</span> -->
                                    </div>
                                </div>
                            </li>
                        </#list>
                    <#else >
                    <!-- 无数据时 -->
                     <li class="fly-none">消灭零回复</li>
                    </#if>
                </ul>

                <div class="layui-form layui-form-pane">
                    <div class="layui-form-item layui-form-text">
                        <a name="comment"></a>
                        <div class="layui-input-block">
                                <textarea id="L_content" name="content" required lay-verify="required"
                                          placeholder="请输入内容" class="layui-textarea fly-editor"
                                          style="height: 200px;resize:none"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <button class="layui-btn" id="sumitComment" forumUserId="${forumAndComment.forumUsersId}"
                                forumId="${forumAndComment.forumId}" lay-filter="*">
                            提交回复
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <dl class="fly-panel fly-list-one">
                <dt class="fly-panel-title">本周热议</dt>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>
                <dd>
                    <a href="">基于 layui 的极简社区页面模版</a>
                    <span><i class="iconfont icon-pinglun1"></i> 16</span>
                </dd>

                <!-- 无数据时 -->
                <!--
                <div class="fly-none">没有相关数据</div>
                -->
            </dl>

        <#--<div class="fly-panel">
          <div class="fly-panel-title">
            这里可作为广告区域
          </div>
          <div class="fly-panel-main">
            <a href="http://layim.layui.com/?from=fly" target="_blank" class="fly-zanzhu" time-limit="2017.09.25-2099.01.01" style="background-color: #5FB878;">LayIM 3.0 - layui 旗舰之作</a>
          </div>
        </div>

        <div class="fly-panel" style="padding: 20px 0; text-align: center;">
          <img src="../../res/images/weixin.jpg" style="max-width: 100%;" alt="layui">
          <p style="position: relative; color: #666;">微信扫码关注 layui 公众号</p>
        </div>-->

        </div>
    </div>
    <div style="display: none" id="RelpyTest">

    </div>
</div>
<script src="${basePath}/res/layui/layui.js"></script>
<script src="${basePath}/js/reception/detail.js"></script>
<script>

    var falg = false;

    var path = "${basePath}";

    <#if Session.users??>
            var userId = "${Session.users.id}";
            falg = true;
            var headImg = "${Session.users.headImg}";

            var userName = "${Session.users.userName}";

            var realName = "${Session.users.realName}";

            var positionId = "${Session.users.positionId}";
    </#if>

    layui.cache.page = 'jie';
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
    }).use(['fly', 'face'], function () {
        var $ = layui.$
                , fly = layui.fly;
        //如果你是采用模版自带的编辑器，你需要开启以下语句来解析。
        /*
        $('.detail-body').each(function(){
          var othis = $(this), html = othis.html();
          othis.html(fly.content(html));
        });
        */
    });
</script>
<div class="fly-footer">
    <p><a href="http://fly.layui.com/" target="_blank">资源共享库</a> 2018-2019 &copy; <a href="#" target="_blank">第三小组完成</a>
    </p>
</div>
</body>
</html>