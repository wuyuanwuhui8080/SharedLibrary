package com.share.config;
import com.jagregory.shiro.freemarker.ShiroTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;

/**
 * Freekmark 配置类
 * @author 博博
 * @Title: FreekmarkConfig
 * @ProjectName SharedLibrary
 * @time 2018/12/13 21:10
 */
@Component
public class FreekmarkConfig {


   /* @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(){
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("classpath:/templates");
        Map<String, Object> variables = new HashMap<>(1);
        variables.put("shiro", new ShiroTags());
        configurer.setFreemarkerVariables(variables);
        System.out.println();
        Properties settings = new Properties();
        settings.setProperty("default_encoding", "utf-8");
        settings.setProperty("number_format", "0.##");
        configurer.setFreemarkerSettings(settings);
        return configurer;
    }*/

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @PostConstruct
    public void setSharedViariable()  {
        freeMarkerConfigurer.getConfiguration().setSharedVariable("shiro",new ShiroTags());
        freeMarkerConfigurer.getConfiguration().setNumberFormat("#");
    }
}
