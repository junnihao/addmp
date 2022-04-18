package com.example.demo.system.security.provider;

import com.addmp.security.config.SecurityConfig;
import com.addmp.security.provider.JwtAuthenticationProvider;

public class DemoJwtAuthenticationProvider extends JwtAuthenticationProvider {
    public DemoJwtAuthenticationProvider(SecurityConfig securityConfig) {
        super(securityConfig);
    }
}
