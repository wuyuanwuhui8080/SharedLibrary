package com.share.charsocket.config;

import com.share.charsocket.enums.CharServerEnum;
import com.share.charsocket.server.CharServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * 配置netty整合spring boot一起启动
 *
 * @author 博博
 * @Title: NettyBooter
 * @ProjectName SharedLibrary
 * @time 2019/1/10 15:34
 */
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent>{


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent()== null){//如果这个是null的话
            try{
                CharServerEnum.INSTANCE.getInstance().start();
            }catch (Exception e){
                e.printStackTrace();;
            }
        }
    }
}
