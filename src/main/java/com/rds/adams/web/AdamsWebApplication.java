package com.rds.adams.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import  org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@SpringBootApplication (exclude = SecurityAutoConfiguration.class, scanBasePackages = {"com.rds.rsf.core", "com.rds.adams.web"})
public class AdamsWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdamsWebApplication.class, args);
	}

}
