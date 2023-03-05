package com.phoenix.implantation;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Classe starter springboot.
 */
@SpringBootApplication
public class DeploymentMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeploymentMapApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    /**
     * Configuração de CORS
     *
     * @return {@link WebMvcConfigurer}
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("*")
                        .allowedOrigins("https://mapabradesco.arlepton.com/ms-mapa-api/*","http://localhost:8080/*","http://localhost:5173/*")
                        .allowedHeaders("*")
                        .allowedMethods("GET", "POST", "PUT", "OPTIONS", "HEAD", "TRACE", "CONNECT");
            }
        };
    }
}
