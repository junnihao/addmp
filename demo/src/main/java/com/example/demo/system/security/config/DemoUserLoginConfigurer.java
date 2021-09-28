package com.example.demo.system.security.config;

import com.addmp.security.config.SecurityConfig;
import com.addmp.security.config.UserLoginConfigurer;

public class DemoUserLoginConfigurer extends UserLoginConfigurer {

    public DemoUserLoginConfigurer(SecurityConfig securityConfig) {
        super(securityConfig);
    }
}
