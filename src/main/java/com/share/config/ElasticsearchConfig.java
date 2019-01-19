/*
package com.share.config;

import com.share.constant.ElasticsearchConstant;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

*/
/**
 * elasticearch配置类
 *
 * @author 博博
 * @Title: ElasticsearchConfig
 * @ProjectName SharedLibrary
 * @time 2019/1/18 10:10
 *//*


@Configuration
@Log4j2
public class ElasticsearchConfig {

    @Bean(name = "transportClient")
    public TransportClient transportClient() {
        log.info("Elasticsearch初始化开始。。。。。");
        TransportClient transportClient = null;
        try {
            // 配置信息
            Settings esSetting = Settings.builder()
                    .put("cluster.name", ElasticsearchConstant.CLUSTERNAME) //集群名字
                    .put("client.transport.sniff", true)//增加嗅探机制，找到ES集群
                    .put("thread_pool.search.size", Integer.parseInt(ElasticsearchConstant.POOLSIZE))//增加线程池个数，暂时设为5
                    .build();
            //配置信息Settings自定义
            transportClient = new PreBuiltTransportClient(esSetting);
            TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(ElasticsearchConstant.HOSTNAME), ElasticsearchConstant.PORT);
            transportClient.addTransportAddresses(transportAddress);
        } catch (Exception e) {
            log.error("初始化失败");
        }
        return transportClient;
    }

}
*/
