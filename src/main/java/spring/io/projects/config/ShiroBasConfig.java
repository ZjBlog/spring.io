package spring.io.projects.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * 权限控制
 * 
 * @author Administrator
 * 
 */
@Configuration
public class ShiroBasConfig {

    static Map<String, String> filterChainDefinitionMap() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        // anon
        map.put("/webjars/**", "anon");
        // login|logout
        map.put("/login", "authc");
        map.put("/logout", "logout");
        // authc

        map.put("/**", "anon");
        return map;
    }

    static Map<String, Filter> filters() {
        //
        Map<String, Filter> map = new HashMap<>();
        // map.put("authc", new CustomFormAuthenticationFilter());
        // map.put("rolesAny", new RolesAnyAuthorizationFilter());
        return map;
    }

    @Bean
    @DependsOn(value = { "lifecycleBeanPostProcessor" })
    DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        return creator;
    }

    @Bean
    LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        LifecycleBeanPostProcessor processor = new LifecycleBeanPostProcessor();
        return processor;
    }

    @Bean
    ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}
