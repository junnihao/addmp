package com.example.demo.system.config;

import com.addmp.security.config.WebSecurityConfig;
import com.example.demo.system.security.AuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.thymeleaf.expression.Lists;

import java.util.Arrays;
import java.util.Collections;

@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class DemoWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("x>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<-----------XXXy");
        //http.cors().and();

        http.authorizeRequests()
                // 配置白名单（比如登录接口）
                .antMatchers("OPTIONS").permitAll()
                .antMatchers("http://localhost:8080").permitAll()
                .antMatchers("http://localhost:8089").permitAll()
                // 匿名访问的URL，即不用登录也可以访问（比如广告接口）
                .antMatchers("/demo/user/login").permitAll()
                // 买家接口需要 “ROLE_BUYER” 角色权限才能访问
                //.antMatchers("/buyer/**").hasRole("BUYER")
                // 其他任何请求满足 rbacService.hasPermission() 方法返回true时，能够访问
                //.anyRequest().access("@rbacService.hasPermission(request, authentication)")
                // 其他URL一律拒绝访问
                //.anyRequest().denyAll()
                .and()
                // 禁用跨站点伪造请求
                //.csrf().disable()
                // 启用跨域资源共享
                //.cors()
                //.and()
                // 添加请求头
                .headers().addHeaderWriter(
                new StaticHeadersWriter(Collections.singletonList(
                        new Header("Access-control-Allow-Origin", "http://localhost:8080")))).
                addHeaderWriter(
                        new StaticHeadersWriter(Collections.singletonList(
                                new Header("Access-Control-Allow-Credentials", "true")))
                ).addHeaderWriter(
                new StaticHeadersWriter(Collections.singletonList(
                        new Header("Access-Control-Allow-Headers", "Content-Type, Content-Length, Authorization, Accept, X-Requested-With, token, x-token")))
                ).addHeaderWriter(
                        new StaticHeadersWriter(Collections.singletonList(
                                new Header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")))
                ).addHeaderWriter(
                        new StaticHeadersWriter(Collections.singletonList(
                                new Header("Access-Control-Max-Age", "3600")))
                ).addHeaderWriter(
                        new StaticHeadersWriter(Collections.singletonList(
                                new Header("Access-Control-Expose-Headers", "*")))
                )
                .and()
                // 自定义的登录过滤器，不同的登录方式创建不同的登录过滤器，一样的配置方式
                //.apply(new com.addmp.security.config.UserLoginConfigurer<>(securityConfig))
                //.and()
                // 自定义的JWT令牌认证过滤器
                //.apply(new JwtLoginConfigurer<>(securityConfig))
                //.and()
                // 登出过滤器
                .logout()
                // 登出成功处理器
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .and()
                // 禁用Session会话机制（我们这个demo用的是JWT令牌的方式）
                .sessionManagement().disable()
                // 禁用SecurityContext，这个配置器实际上认证信息会保存在Session中，但我们并不用Session机制，所以也禁用
                .securityContext().disable();
    }


    /*@Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        log.info("yyyyyyyyyyyyyyyyyyyy----------------");
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Collections.singletonList("Content-Type, Content-Length, Authorization, Accept, X-Requested-With, token, x-token"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "HEAD", "DELETE", "PUT", "OPTION"));
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
        configuration.setExposedHeaders(Arrays.asList("*"));
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }*/

    @Bean(name = "corsFilter")
    public FilterRegistrationBean corsFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new AuthenticationFilter());
        //registrationBean.setUrlPatterns(Lists.newArrayList("/*"));
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
