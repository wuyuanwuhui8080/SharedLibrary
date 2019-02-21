<#include "../comm/head.ftl">

<div class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20>
        <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title">
                <li><a href="${basePath}/sharedUsers/goForumLogin">登入</a></li>
                <li class="layui-this">注册</li>
            </ul>
            <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
                <div class="layui-tab-item layui-show">
                    <div class="layui-form layui-form-pane">
                        <form method="post" id="registerFromId" action="${basePath}/sharedUsers/saveNorsalUsers">
                            <div class="layui-form-item">
                                <label for="L_email" class="layui-form-label">用户名</label>
                                <div class="layui-input-inline">
                                    <input onblur="userNameblur();" type="text" id="userName" name="userName" required
                                           lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux" id="userNameSpanId">将会成为您唯一的登入名</div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_username" class="layui-form-label">真实姓名</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="realName" name="realName" required lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_username" class="layui-form-label">手机号</label>
                                <div class="layui-input-inline">
                                    <input type="text" id="phone" name="phone" onblur="phoneblur();" required
                                           lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux" id="phoneSpanId">11位手机号</div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_pass" class="layui-form-label">密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="password1" onblur="password1blur();" name="password"
                                           required lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux" id="password1SpanId">
                                    密码以字母开头，长度在6~18之间，只能包含字母、数字和下划线
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_repass" class="layui-form-label">确认密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" id="password2" onblur="password2blur()" required
                                           lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux" id="password2SpanId"></div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_vercode" class="layui-form-label">出生日期</label>
                                <div class="layui-input-inline">
                                    <input type="date" id="birthday" name="birthday" onblur="birthdayblur()" required
                                           lay-verify="required"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <div class="layui-form-mid layui-word-aux" id="birthdaySpanId"></div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_vercode" class="layui-form-label">性别</label>
                                <div class="layui-input-inline">
                                    <input type="radio" name="sex" value="1" title="男" checked>
                                    <input type="radio" name="sex" value="2" title="女" >
                                </div>
                                <div class="layui-form-mid layui-word-aux" id="birthdaySpanId"></div>
                            </div>
                            <div class="layui-form-item">
                                <label for="L_vercode" class="layui-form-label">人类验证</label>
                                <div class="layui-input-inline">
                                    <input type="text"  id="captcha" name="captcha" required lay-verify="required"
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
                                <button class="layui-btn" id="Sumitres" type="submit" >立即注册</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<#include "../comm/footer.html">
<script src="${basePath}/js/resources/register.js"></script>
</body>
</html>
<script>
    var path = "${basePath}";
</script>