package com.example.SpringbootApi.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BeanConfig {
	
	@Value("${cors.allowedOrigins}")
	private String allowedOrigins;

	@Bean
	public LocalDateTime startTime() {
		return LocalDateTime.now();
	}
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                		.allowedOrigins(allowedOrigins) 
                        .allowedMethods("*");
            }
        };
    }
	
	@Bean
	public FilterRegistrationBean<CharacterEncodingFilter> registrationBean() {
		FilterRegistrationBean<CharacterEncodingFilter> bean = new FilterRegistrationBean<>();
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		bean.setFilter(filter);
		bean.addUrlPatterns("/*");
		bean.setOrder(1);
		return bean;
	}

}
