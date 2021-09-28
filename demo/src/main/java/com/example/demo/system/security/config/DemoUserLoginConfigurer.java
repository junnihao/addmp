package com.example.demo.system.security.config;

import com.addmp.security.config.SecurityConfig;
import com.addmp.security.config.UserLoginConfigurer;
import com.addmp.security.filter.UserAuthenticationFilter;
import com.example.demo.system.security.filter.DemoUserAuthenticationFilter;

public class DemoUserLoginConfigurer extends UserLoginConfigurer {

    public DemoUserLoginConfigurer(SecurityConfig securityConfig) {
        super(securityConfig);
    }

    @Override
    protected UserAuthenticationFilter getUserAuthenticationFilter(){
        return new DemoUserAuthenticationFilter();
    }
}
