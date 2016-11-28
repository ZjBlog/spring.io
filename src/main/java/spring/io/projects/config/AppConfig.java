package spring.io.projects.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

import spring.io.projects.util.ServletContextUtil;
import spring.io.projects.util.SpringContextUtil;

/**
 * APP全局配置
 * 
 * @author Ertao.Fang
 * 
 */
@Configuration
public class AppConfig {

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    SpringContextUtil springContextUtil() {
        return new SpringContextUtil();
    }

    @Bean
    ServletContextUtil ServletContextUtil() {
        return new ServletContextUtil();
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(1024 * 1024 * 50);
        return factory.createMultipartConfig();
    }

    @Bean
    LocaleResolver localeResolver() {

        return new SessionLocaleResolver();
    }

}
