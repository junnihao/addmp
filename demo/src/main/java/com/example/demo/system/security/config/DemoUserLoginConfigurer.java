package com.example.demo.system.security.config;

import com.addmp.security.config.SecurityConfig;
import com.addmp.security.config.UserLoginConfigurer;
import com.addmp.security.filter.UserAuthenticationFilter;
import com.addmp.security.handler.HttpStatusLoginFailureHandler;
import com.addmp.security.handler.UserLoginSuccessHandler;
import com.example.demo.system.security.filter.DemoUserAuthenticationFilter;
import com.example.demo.system.security.handler.DemoUserLoginSuccessHandler;

public class DemoUserLoginConfigurer extends UserLoginConfigurer {

    SecurityConfig securityConfig ;
    public DemoUserLoginConfigurer(SecurityConfig securityConfig) {
        super(securityConfig);
        this.securityConfig = securityConfig ;
    }

    @Override
    protected UserAuthenticationFilter getUserAuthenticationFilter(){
        DemoUserAuthenticationFilter demoUserAuthenticationFilter = new DemoUserAuthenticationFilter();
        // 登录成功处理器
        demoUserAuthenticationFilter.setAuthenticationSuccessHandler(new DemoUserLoginSuccessHandler(securityConfig));
        // 登录失败处理器
        demoUserAuthenticationFilter.setAuthenticationFailureHandler(new HttpStatusLoginFailureHandler());
        return demoUserAuthenticationFilter;
    }
}
