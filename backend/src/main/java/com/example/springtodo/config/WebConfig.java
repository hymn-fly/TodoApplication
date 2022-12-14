package com.example.springtodo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final long MAX_AGE_SECS = 3600;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins("http://localhost:3000",
				"http://hymnfly.ap-northeast-2.elasticbeanstalk.com",
				"http://app.hymn-fly.com",
				"https://app.hymn-fly.com")
			.allowedMethods("*")
			.allowCredentials(true)
			.maxAge(MAX_AGE_SECS);
	}
}
