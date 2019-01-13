package com.share;

import com.share.util.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *  spring boot启动类
 * @author 博博大人
 * @time 2018/12/13 9:07
 */
@SpringBootApplication
@MapperScan(basePackages = { "com.share.blogs.mapper",
		"com.share.invitation.mapper", "com.share.users.mapper" })
public class SharedlibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharedlibraryApplication.class, args);
	}
}
