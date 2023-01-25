package com.ddudu.todo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Value("#{jwtResource['HEADER_STRING']}")
    String JWT_HEADER_STRING;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("http://localhost:3000");
        config.setAllowedMethods(Arrays.asList("GET","POST","DELETE","UPDATE","OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("Origin", "Accept", "X-Requested-With", "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers", JWT_HEADER_STRING));
        config.addExposedHeader("*");
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
