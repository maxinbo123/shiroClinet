package com.kenan.shrio.config;

import com.kenan.shrio.filter.LogoutFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by maxb on 2020/2/20.
 */

@Configuration
public class ShiroFilterRegisterConfig {



    /**
     * @desc 方法名字可以随便取
     * @param filter
     * @return
     */
    @Bean
    public FilterRegistrationBean<LogoutFilter> shiroCheckSessionRegistration(LogoutFilter logoutFilter) {
        FilterRegistrationBean<LogoutFilter> registration = new FilterRegistrationBean<LogoutFilter>(logoutFilter);
        registration.setEnabled(false);
        return registration;
    }
}
