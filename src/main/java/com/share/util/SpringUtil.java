package com.share.util;

import net.bull.javamelody.SpringContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;


/**
 * @author 博博大人
 * @Description: 提供手动获取被spring管理的bean对象
 * @time 2019/1/12 23:51
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    /**
     * spring容器上下文
     */
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public final static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public final static <T> Collection<T> getBeansOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz).values();
    }

    public final static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }


}
