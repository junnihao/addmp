package com.example.demo.system.security.config;
import com.addmp.security.config.WebSecurityConfig;
import com.addmp.security.config.SecurityConfig;
import com.addmp.security.provider.UserAuthenticationProvider;
import com.example.demo.system.security.provider.DemoJwtAuthenticationProvider;
import com.example.demo.system.security.provider.DemoUserAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
       http.apply(new DemoUserLoginConfigurer(securityConfig()));
    }

    @Override
    protected void customizedJwtConfig(HttpSecurity http) throws Exception {
        http.apply(new DemoJwtLoginConfigurer(securityConfig()));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(demoUserAuthenticationProvider()).authenticationProvider(demoJwtAuthenticationProvider());
    }

    protected AuthenticationProvider demoUserAuthenticationProvider() throws Exception {
        return new DemoUserAuthenticationProvider();
    }

    protected AuthenticationProvider demoJwtAuthenticationProvider() throws Exception {
        return new DemoJwtAuthenticationProvider(super.securityConfig);
    }
}
