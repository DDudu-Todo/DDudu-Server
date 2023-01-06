package com.ddudu.todo.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropertyConfig {

    @Bean(name = "kakaoResource")
    public PropertiesFactoryBean kakao() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        ClassPathResource classPathResource = new ClassPathResource("./application-kakao.properties");
        propertiesFactoryBean.setLocation(classPathResource);
        return propertiesFactoryBean;
    }

    @Bean(name = "jwtResource")
    public PropertiesFactoryBean jwt() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        ClassPathResource classPathResource = new ClassPathResource("./application-jwt.properties");
        propertiesFactoryBean.setLocation(classPathResource);
        return propertiesFactoryBean;
    }
}
