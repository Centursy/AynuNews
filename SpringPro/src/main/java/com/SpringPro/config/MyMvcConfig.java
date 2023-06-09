package com.SpringPro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//全面接管mvc配置
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login.html").setViewName("loginl");
        registry.addViewController("/Main.html").setViewName("test");
        registry.addViewController("/test").setViewName("test2");
        registry.addViewController("/reg").setViewName("register");
        registry.addViewController("/233").setViewName("233");
        registry.addViewController("/bbs").setViewName("indexb");
        registry.addViewController("/adm").setViewName("indexc");
    }

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).
                addPathPatterns("/**").
                excludePathPatterns("/loginl.html","/reg","/rege","/","/login","/asserts/**","/bbs","/adm","/admlogin","/snews","/indnewss/**","/newlis/**","/login.html","/adlogin");
    }
}
