package com.scsc.rbac.configuration;

import com.scsc.rbac.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author qing
 * @date 2019/4/30 14:11
 */
@Configuration
public class LoginConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public LoginInterceptor myLoginInterceptor(){
        return new LoginInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
//        LoginInterceptor loginInterceptor = new LoginInterceptor();
        InterceptorRegistration loginRegistry = registry.addInterceptor(myLoginInterceptor());
        //拦截路径
//        loginRegistry.addPathPatterns("/**");
        //排除路径
//        loginRegistry.excludePathPatterns("/user/login");
//        loginRegistry.excludePathPatterns("/swagger-ui.*");

        loginRegistry.excludePathPatterns("/**");
        //排除资源请求
        loginRegistry.excludePathPatterns("/webjars/**");
        loginRegistry.excludePathPatterns("/swagger-resources/**");
    }
}
