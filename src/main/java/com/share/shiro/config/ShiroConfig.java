package com.share.shiro.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.share.shiro.realm.CustomRealm;

/**
 * 自定义拦截器
 *
 * @author 博博大人
 * @time 2018/11/18 19:17
 */
/*@Configuration*/
public class ShiroConfig {

    // 下面两个方法对 注解权限起作用有很大的关系，请把这两个方法，放在配置的最上面
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }

    /**
     * 密码校验规则HashedCredentialsMatcher
     * 这个类是为了对密码进行编码的 ,
     * 防止密码在数据库里明码保存 , 当然在登陆认证的时候 ,
     * 这个类也负责对form里输入的密码进行编码
     * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
     */
    @Bean
    public CustomRealm createMyRealm() {
        // 加密相关
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        // 散列次数
        hashedCredentialsMatcher.setHashIterations(1024);
        CustomRealm myRealm = new CustomRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return myRealm;
    }

    //设置过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        System.out.println("开始拦截器");
        //必须设置securityManager 登录登出什么的
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //setLoginUrl 如果不设置值，默认会寻找web工程下的/login.jsp 页面或者/login映射
        shiroFilterFactoryBean.setLoginUrl("/sharediForum/goIndex");
        //登录成功跳转的urls
//        shiroFilterFactoryBean.setSuccessUrl("/sharedUsers/goIndex");
        //设置无权限时跳转的url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403.jsp");
     /*   shiroFilterFactoryBean.setFilterChainDefinitions("/sharedUsers/goLogin = authc");
        shiroFilterFactoryBean.setFilterChainDefinitions("/logout = logout");
        Map<String,Filter> logout = new HashMap<String, Filter>();
        logout.put("logout", logoutFilter());
        shiroFilterFactoryBean.setFilters(logout);*/
        //设置拦截器
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        //开放登录接口
        linkedHashMap.put("/sharedUsers/doLogin", "anon");
        linkedHashMap.put("/sharedUsers/goLogin", "anon");
        linkedHashMap.put("/sharedUsers/goRegister", "anon");
        linkedHashMap.put("/sharedUsers/saveNorsalUsers", "anon");
        linkedHashMap.put("/sharedUsers/getUserName", "anon");
        linkedHashMap.put("/Captcha.jpg", "anon");
        linkedHashMap.put("/background/users/login.ftl", "anon");
        linkedHashMap.put("/background/users/register.ftl", "anon");
        linkedHashMap.put("/background/comm/script.ftl", "anon");
        linkedHashMap.put("/index.ftl", "anon");
        linkedHashMap.put("/sharediForum/tologin", "anon");
        linkedHashMap.put("/sharediForum/goIndex", "anon");
        linkedHashMap.put("/sharediForum/goWriteForum", "anon");
        linkedHashMap.put("/loginOut", "logout");
        //css开放
        linkedHashMap.put("/css/**", "anon");
        linkedHashMap.put("/images/**", "anon");
        linkedHashMap.put("/img/**", "anon");
        linkedHashMap.put("/fonts/**", "anon");
        linkedHashMap.put("/blue/**", "anon");
        linkedHashMap.put("/docs/**", "anon");

        linkedHashMap.put("/__MACOSX/**", "anon");
        linkedHashMap.put("/plugins/**", "anon");
        linkedHashMap.put("/js/**", "anon");
        linkedHashMap.put("/res/**", "anon");

        //其他接口全部拦截
        linkedHashMap.put("/**", "authc");
        //把写的权限map set进值
        shiroFilterFactoryBean.setFilterChainDefinitionMap(linkedHashMap);
        System.out.println("结束拦截器");
        return shiroFilterFactoryBean;
    }

    /**
     * @return
     * @Title: securityManager
     * @Description: 注入自定义的realm
     * @Description: 注意方法返回值SecurityManager为org.apache.shiro.mgt.SecurityManager
     * ,不要导错包
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(createMyRealm());
        return securityManager;
    }

    /**
     * 自定义身份认证 realm;
     * <p>
     * EmployeeService 等类，接口无法通过 @Resource 注入进来，
     * 跑程序的时候会报 NullPointerException，网上说了很多诸如是 Spring 加载顺序等原因，
     * 但其实有一个很重要的地方要大家注意，CustomRealm 这个类是在 shiro 配置类的
     * securityManager.setRealm() 方法中设置进去的，而很多人直接写securityManager.setRealm(new CustomRealm());
     * 这样是不行的，必须要使用 @Bean 注入 MyRealm，不能直接 new 对象
     * <p>
     * 必须写这个类，并加上 @Bean 注解，目的是注入 CustomRealm，
     * 否则会影响 CustomRealm类 中其他类的依赖注入
     */
   /* @Bean
    public CustomRealm customRealm() {
        return new CustomRealm();
    }*/

    /**
     * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持，调用授权的方法必须要有此支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 配置session监听
     *
     * @return
     */
 /*   @Bean("sessionListener")
    public ShiroSessionListener sessionListener() {
        ShiroSessionListener sessionListener = new ShiroSessionListener();
        return sessionListener;
    }*/

    /**
     * 配置会话ID生成器
     *
     * @return
     */
   /* @Bean
    public SessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }*/


}
