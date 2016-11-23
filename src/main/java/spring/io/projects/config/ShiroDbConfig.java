package spring.io.projects.config;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import spring.io.projects.security.ShiroDbRealm;

/**
 * 
 * @author Mr.Zhang
 * @Date 2016年11月23日
 * @Email zhangjun150429@qq.com
 */
@Configuration
public class ShiroDbConfig {

    static Map<String, String> filterChainDefinitionMap() {
        Map<String, String> map = ShiroBasConfig.filterChainDefinitionMap();
        return map;
    }

    @Bean(name = "shiroFilter")
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/login/success");
        bean.setUnauthorizedUrl("/unauthorized.html");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap());
        bean.setFilters(ShiroBasConfig.filters());
        return bean;
    }

    @Bean
    DelegatingFilterProxy delegatingFilterProxy() {
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetBeanName("shiroFilter");
        return proxy;
    }

    @Bean
    SecurityManager securityManager() {
        DefaultSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(new IniRealm());
        manager.setSessionManager(sessionManager());
        manager.setRememberMeManager(rememberMeManager());
        manager.setRealm(new ShiroDbRealm());
        manager.setCacheManager(new EhCacheManager());
        return manager;
    }

    @Bean
    SessionManager sessionManager() {
        // DefaultWebSessionManager manager = new DefaultWebSessionManager();
        // manager.setGlobalSessionTimeout(1000 * 60 * 60);
        // manager.setSessionDAO(sessionDAO());
        ServletContainerSessionManager manager = new ServletContainerSessionManager();
        return manager;
    }

    @Bean
    RememberMeManager rememberMeManager() {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCipherKey(Base64.decodeBase64("4AvVhmFLUs0KTA3Kprsdag=="));
        manager.setCookie(rememberMeCookie());
        return manager;
    }

    @Bean
    SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie(CookieRememberMeManager.DEFAULT_REMEMBER_ME_COOKIE_NAME);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24 * 7);// 7天
        return cookie;
    }

    // @Bean
    // SessionDAO sessionDAO() {
    // SessionDAO dao = new MemorySessionDAO();
    // return dao;
    // }

}
