package com.springboot.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 拦截器
 * @author lihang
 *
 */
@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration ir = registry.addInterceptor(new LoginInterceptor());
		//黑名单
		ir.addPathPatterns("/**");
		
		List<String> patterns = new ArrayList<String>();
		// 静态资源
	    patterns.add("/bootstrap3/**");
	    patterns.add("/css/**");
	    patterns.add("/images/**");
	    patterns.add("/js/**");
	    // 注册和登录页面
	    patterns.add("/web/login.html");
	    patterns.add("/web/register.html");
	    // 注册和登录控制器
	    patterns.add("/users/login");
	    patterns.add("/users/reg");
	    //白名单
		ir.excludePathPatterns(patterns);
		
		
	}
	
}
