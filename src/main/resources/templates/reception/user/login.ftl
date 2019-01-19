<#include "../comm/head.ftl">
<div class="layui-container fly-marginTop">
  <div class="fly-panel fly-panel-user" pad20>
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      <ul class="layui-tab-title">
        <li class="layui-this">登入</li>
        <li><a href="${basePath}/sharedUsers/goForumRegister">注册</a></li>
      </ul>
      <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
        <div class="layui-tab-item layui-show">
          <div class="layui-form layui-form-pane">
            <form  method="post"  action="${basePath}/sharedUsers/doLogin">
              <div class="layui-form-item">
                <label for="L_email" class="layui-form-label">用户名</label>
                <div class="layui-input-inline">
                  <input type="text"   name="userName" id="userName" required lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label for="L_pass" class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                  <input type="password" name="password" id="password" placeholder="请输入密码"  required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label for="L_vercode" class="layui-form-label">人类验证</label>
                <div class="layui-input-inline">
                  <input type="text" id="captcha" name="captcha" required lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid">
                  <span style="color: #c00;"><a style="margin-left: 30%" href="javascript:refreshCaptcha()"><img alt="验证码"
                                                                                                                 src="${basePath}/Captcha.jpg"
                                                                                                                 title="点击更换"
                                                                                                                 id="captcha_img"/></a></span>
                </div>
              </div>
              <div class="layui-form-item">
                <button class="layui-btn" id="sumitLogin" type="submit"  lay-submit>立即登录</button>
                <span style="padding-left:20px;">
                  <a href="forget.html">忘记密码？</a>
                </span>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<#include "../comm/footer.html">
</body>
</html>
<script>
    var path = "${basePath}";
</script>
<script src="${basePath}/js/reception/login.js"></script>