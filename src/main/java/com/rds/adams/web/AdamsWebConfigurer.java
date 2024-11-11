package com.rds.adams.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.rds.rsf.core.interceptor.AuthenticInterceptor;
import com.rds.rsf.core.interceptor.AuthorizInterceptor;

import lombok.RequiredArgsConstructor;

/**
 * RDS STANDARD DEVELOP FRAMEWORK
 *
 * @since  : 2024. 11. 7.
 * @author : JEONG HYEONSEUNG
 * E-MAIL  : hs.jeong@rnadatasystem.com
 * <PRE>
 * 개정이력
 * ----------------------------------------------------------
 * 2024-11-07 : 최초 등록
 * ----------------------------------------------------------
 * </PRE>
 */
@Configuration
@RequiredArgsConstructor
@ComponentScan(basePackages = "com.rds.adams.web", excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Service.class),
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Repository.class),
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
	})
public class AdamsWebConfigurer implements WebMvcConfigurer {
	
	@Autowired
	AuthenticInterceptor authenticInterceptor;
	
	@Autowired
	AuthorizInterceptor authorizInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		List<String> commonExcludePathList = new ArrayList<String>();
		List<String> resourceExcludePathList = new ArrayList<String>();
		List<String> excludePathList = new ArrayList<String>();
		
		// 에러화면
		commonExcludePathList.add("/error/*");
		
		// 인증 예외대상
		commonExcludePathList.add("/");
		commonExcludePathList.add("/login");
		commonExcludePathList.add("/adminLogin");
		commonExcludePathList.add("/auth/**");
		commonExcludePathList.add("/logout");
		commonExcludePathList.add("/TokenRefresh");
		commonExcludePathList.add("/error");
		commonExcludePathList.add("/FailAuthentic");
		
		// 웹자원
		resourceExcludePathList.add("/css/**");
		resourceExcludePathList.add("/images/**");
		resourceExcludePathList.add("/js/**");
		resourceExcludePathList.add("/*.ico");
		
		excludePathList.addAll(commonExcludePathList);
		excludePathList.addAll(resourceExcludePathList);
		
		registry.addInterceptor(authenticInterceptor)
			.addPathPatterns("/**/*", "/*")
			.excludePathPatterns(excludePathList);
		registry.addInterceptor(authorizInterceptor)
			.addPathPatterns("/menuLink")
			.excludePathPatterns(excludePathList);

	}

	// -------------------------------------------------------------
	// RequestMappingHandlerMapping 설정 View Controller 추가
	// -------------------------------------------------------------
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/cmmn/validator.do")
			.setViewName("cmmn/validator");
		registry.addViewController("/index.html").setViewName("forward:login.html");
		registry.addViewController("/").setViewName("login");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
	    registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
	    registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
	    registry.addResourceHandler("/layout/**").addResourceLocations("classpath:/templates/layout/");
	    registry.addResourceHandler("/views/**").addResourceLocations("classpath:/templates/views/");
	}

	@Bean
	public SessionLocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("language");
		return interceptor;
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		Properties prop = new Properties();
		prop.setProperty("org.springframework.dao.DataAccessException", "egovSampleError");
		prop.setProperty("org.springframework.transaction.TransactionException", "egovSampleError");
		prop.setProperty("org.springframework.security.AccessDeniedException", "egovSampleError");
		prop.setProperty("java.lang.Throwable", "error/error");

		Properties statusCode = new Properties();
		statusCode.setProperty("error/error_400", "400");
		statusCode.setProperty("error/error_500", "500");
		statusCode.setProperty("error/error_400", "404");
		statusCode.setProperty("error/error_400", "403");
		statusCode.setProperty("error/error_500", "503");

		SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();
		smer.setDefaultErrorView("error/error");
		smer.setExceptionMappings(prop);
		smer.setStatusCodes(statusCode);
		resolvers.add(smer);
	}

}