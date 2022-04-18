package com.example.demo.system.security.config;

import com.addmp.security.config.JwtLoginConfigurer;
import com.addmp.security.config.SecurityConfig;
import com.addmp.security.filter.JwtAuthenticationFilter;
import com.addmp.security.handler.HttpStatusLoginFailureHandler;
import com.addmp.security.handler.JwtRefreshSuccessHandler;
import com.example.demo.system.security.filter.DemoJwtAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

public class DemoJwtLoginConfigurer extends JwtLoginConfigurer {
    SecurityConfig securityConfig ;
    public DemoJwtLoginConfigurer(SecurityConfig securityConfig) {
        super(securityConfig);
        this.securityConfig = securityConfig ;
    }

    @Override
    protected JwtAuthenticationFilter getJwtAuthenticationFilter(){
        DemoJwtAuthenticationFilter authFilter = new DemoJwtAuthenticationFilter(securityConfig.getTokenName());
        // 成功处理器
        authFilter.setAuthenticationSuccessHandler(new JwtRefreshSuccessHandler(securityConfig));
        // 失败处理器
        authFilter.setAuthenticationFailureHandler(new HttpStatusLoginFailureHandler());
        return authFilter;
    }
}
