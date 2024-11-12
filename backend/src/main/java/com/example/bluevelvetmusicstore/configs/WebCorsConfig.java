package com.example.bluevelvetmusicstore.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    // Allow requests from your frontend
    registry
        .addMapping("/api/**") // Allow all API endpoints starting with /api/
        .allowedOrigins("http://127.0.0.1:5500") // Frontend URL
        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed HTTP methods
        .allowedHeaders("*"); // Allow all headers
  }
}
