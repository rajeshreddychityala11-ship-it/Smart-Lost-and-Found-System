package com.lostfound.config;
import org.springframework.context.annotation.*; import org.springframework.web.servlet.config.annotation.*;
@Configuration
public class WebConfig implements WebMvcConfigurer{
 @Override public void addCorsMappings(CorsRegistry r){r.addMapping("/api/**").allowedOriginPatterns("*").allowedMethods("*").allowedHeaders("*");}
}
