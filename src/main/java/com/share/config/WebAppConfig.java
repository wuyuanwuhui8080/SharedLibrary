package com.share.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * web前端配置类
 *
 * @author 博博
 * @Title: WebAppConfig
 * @ProjectName SharedLibrary
 * @time 2018/12/17 22:45
 */
public class WebAppConfig extends WebMvcConfigurerAdapter {
	/**
	 * 在配置文件中配置的文件保存路径
	 */
	@Value("${web.upload-path}")
	private String location;

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// 文件最大KB,MB
		factory.setMaxFileSize("2MB");
		// 设置总上传数据总大小
		factory.setMaxRequestSize("10MB");

		factory.setLocation(location);

		return factory.createMultipartConfig();
	}
}
