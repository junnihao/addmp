package com.example.demo.system.config;
import com.addmp.security.config.WebSecurityConfig;
import com.addmp.security.config.SecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
public class DemoWebSecurityConfig extends WebSecurityConfig{

    @Bean
    public SecurityConfig securityConfig(){
        return new SecurityConfig() ;
    }

    @Override
    protected void customizedSecurityConfig(HttpSecurity http) throws Exception {
       http.apply(new com.addmp.security.config.UserLoginConfigurer<>(securityConfig()));
    }
}
