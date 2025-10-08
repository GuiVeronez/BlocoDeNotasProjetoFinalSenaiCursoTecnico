package com.blocodenotas.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve os arquivos da pasta "frontend" na raiz do projeto
        registry.addResourceHandler("/**")
                .addResourceLocations("file:frontend/");
    }
}
