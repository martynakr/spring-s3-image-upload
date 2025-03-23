package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @SuppressWarnings("null")
    public void addCorsMappings(CorsRegistry registry) {
        String[] allowedOrigins = { "http://localhost:5173/", "http://127.0.0.1:5173/" };
        registry.addMapping("/**").allowedOrigins(allowedOrigins).allowedMethods("GET", "POST").allowedHeaders("*");
    }

}