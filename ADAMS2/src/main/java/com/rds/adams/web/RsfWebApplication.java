package com.rds.adams.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class, scanBasePackages = {"com.rds"})
public class RsfWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(RsfWebApplication.class, args);
	}

}
