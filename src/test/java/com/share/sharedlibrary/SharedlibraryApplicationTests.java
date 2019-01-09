package com.share.sharedlibrary;

import com.share.config.RedisConfig;
import com.share.users.service.SharedEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SharedlibraryApplicationTests {

    @Resource
    private RedisConfig redisConfig;

    @Test
    public void contextLoads() {
        Jedis jedis =new Jedis("192.168.25.128",6379);
        

    }

}
