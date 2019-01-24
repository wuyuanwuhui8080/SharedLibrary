<#include "../comm/head.ftl">
<!-- 注意， 只需要引用 JS，无需引用任何 CSS ！！！-->
<script src="${basePath}/js/email/wangEditor.js"></script>
<script src="${basePath}/js/reception/add_forum.js"></script>
<div class="layui-container fly-marginTop">
    <div class="fly-panel" pad20 style="padding-top: 5px;">
        <!--<div class="fly-none">没有权限</div>-->
        <div class="layui-form layui-form-pane">
            <div class="layui-tab layui-tab-brief" lay-filter="user">
                <ul class="layui-tab-title">
                    <li class="layui-this">发表新帖<!-- 编辑帖子 --></li>
                </ul>
                <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                    <div class="layui-tab-item layui-show">
                        <form>
                            <div class="layui-form-item layui-form-text">
                                <div class="layui-input-block">
                                    <div id="editor"></div>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <div class="layui-row layui-col-space15 layui-form-item">
                                    <div class="layui-col-md3">
                                        <label class="layui-form-label">所在专栏</label>
                                        <div class="layui-input-block">
                                            <select style="z-index: 999999999;" class="selectClassId">
                                                <option></option>
                                            <#list list as li>
                                                <option value="${li.id}">${li.classifyName}</option>
                                            </#list>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="layui-col-md9">
                                        <label for="L_title" class="layui-form-label">标题</label>
                                        <div class="layui-input-block">
                                            <input type="text" id="L_title" name="title"
                                                   autocomplete="off" class="layui-input forum_title">
                                            <!-- <input type="hidden" name="id" value="{{d.edit.id}}"> -->
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="layui-form-item">
                             <#if (Session.users.positionId == 3)>
                                <div class="layui-col-md3" style="margin-right: 20px;">
                                    <label class="layui-form-label">帖子类型</label>
                                    <div class="layui-input-block">
                                        <select style="z-index: 999999999;" class="selecttypeId">
                                            <option value=""></option>
                                            <option value="4">公告</option>
                                        </select>
                                    </div>
                                </div>
                             </#if>
                                <label for="L_vercode" class="layui-form-label">人类验证</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="captcha" name="captcha"
                                           placeholder="请输入验证码" autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid">
                                    <span style="color: #c00;"><a href="javascript:refreshCaptcha()"><img alt="验证码"
                                                                                                          src="${basePath}/Captcha.jpg"
                                                                                                          title="点击更换"
                                                                                                          id="captcha_img"/></a></span>
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <button class="layui-btn" type="submit" id="sumitForum">立即发布</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "../comm/tail.ftl">

<script src="../../res/layui/layui.js"></script>
<script>
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
    }).use('fly');
</script>

</body>
</html>
<script>


    var E = window.wangEditor;
    var editor = new E('#editor');
    var emailContent = $('#emailContent');
    editor.customConfig.onchange = function (html) {
        // 监控变化，同步更新到 textarea
        emailContent.val(html)
    };
    // 使用 base64 保存图片
    editor.customConfig.uploadImgShowBase64 = true;
    editor.create();
    // 初始化 textarea 的值
    emailContent.val(editor.txt.html());

    // 取路径
    var path = "${basePath}";
</script>