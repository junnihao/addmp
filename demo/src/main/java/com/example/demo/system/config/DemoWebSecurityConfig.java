package com.example.demo.system.config;
import com.addmp.security.config.WebSecurityConfig;
import com.addmp.security.config.SecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoWebSecurityConfig extends WebSecurityConfig{

    @Bean
    public SecurityConfig securityConfig(){
        return new SecurityConfig() ;
    }
}
