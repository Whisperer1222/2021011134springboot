package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.interceptor.AuthorizationInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport{
	//拦截器
	@Bean
    public AuthorizationInterceptor getAuthorizationInterceptor() {
        return new AuthorizationInterceptor();
    }
		//定义拦截器，用于处理诸如权限验证、日志记录等任务
	@Override
    public void addInterceptors(InterceptorRegistry registry) {//用于注册拦截器。InterceptorRegistry是一个注册表，用于添加拦截器
				//将拦截器添加到注册表中，使其对所有路径(/**)生效,除了
        registry.addInterceptor(getAuthorizationInterceptor()).addPathPatterns("/**").excludePathPatterns("/static/**");
        super.addInterceptors(registry);
	}
	

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {//用于注册资源处理器
		registry.addResourceHandler("/**")//配置资源管理器
        .addResourceLocations("classpath:/resources/")
        .addResourceLocations("classpath:/static/")
        .addResourceLocations("classpath:/admin/")
        .addResourceLocations("classpath:/front/")
        .addResourceLocations("classpath:/public/");
		super.addResourceHandlers(registry);
    }
}
