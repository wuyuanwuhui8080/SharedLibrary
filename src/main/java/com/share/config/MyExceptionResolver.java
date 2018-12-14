package com.share.config;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * shiro没有权限自定义跳转的页面
 *
 * @author 博博
 * @Title: MyExceptionResolver
 * @ProjectName SharedLibrary
 * @time 2018/12/14 11:41
 */
@Configuration
public class MyExceptionResolver  {


    /**
     * 自定义异常403跳转
     * @return
     */
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver mappingExceptionResolver =  new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.setProperty("org.apache.shiro.authz.UnauthorizedException","/403");
        properties.setProperty("org.apache.shiro.authz.UnauthenticatedException","/403");
        mappingExceptionResolver.setExceptionMappings(properties);
        return  mappingExceptionResolver;
    }
}
