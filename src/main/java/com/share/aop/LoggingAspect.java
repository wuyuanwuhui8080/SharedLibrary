package com.share.aop;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.log4j.Log4j2;

/**
 * logging 日志的切面实现
 * 
 * @author 博博大人
 * @time 2018/12/14 11:56
 */
@Aspect
@Component
@Log4j2
public class LoggingAspect {
	@Pointcut("execution(public * com.share.controller.*.*(..))")
	private void Pointcut() {
	}

	@Before("Pointcut()")
	public void doBefore(JoinPoint jp) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		System.out.println();
		log.info("请求路径：" + request.getRequestURL().toString());
		log.info("请求类型:" + request.getMethod());
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = enu.nextElement();
			log.info("属性名:{},属性值:{}", name, request.getParameter(name));
		}
	}

	@AfterReturning(returning = "obj", pointcut = "Pointcut()")
	public void doAfter(Object obj) {
		log.info("返回值:" + obj);
		System.out.println();
	}
}
