package com.share.config;

import com.share.util.SpringUtil;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 *
 * 自定义错误页面
 *
 * @author 博博
 * @Title: SpringBootConfig
 * @ProjectName SharedLibrary
 * @time 2018/12/14 11:09
 */
@Configuration
public class SpringBootConfig {

    @Bean
    public SpringUtil getSpringutil(){
        return new SpringUtil();
    }

    /**
     * 自定义错误信息跳转的页面
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
        return factory -> {
            ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND,"/404");
            ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/500");
            ErrorPage errorPage403 = new ErrorPage(HttpStatus.FORBIDDEN,"/403");
            factory.addErrorPages(errorPage403,errorPage404,errorPage500);
        };
    }

}
