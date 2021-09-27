package com.addmp.security.config;

import com.addmp.security.filter.JwtAuthentication1Filter;
import com.alibaba.csp.sentinel.cluster.ClusterStateManager;
import com.addmp.security.provider.JwtAuthenticationProvider;
import com.addmp.security.provider.UserAuthenticationProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * 核心 WebSecurity 配置器
 *
 * @author HuaDong
 * @since 2021/4/24 20:10
 */
@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityConfig securityConfig;

	@Override
	public void configure(WebSecurity web) throws Exception {
		/*真正解决 OPTIONS 403问题的 方法*/
		log.info("ignoring() config ---------------") ;
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
		/*开启后,spring security 会直接忽略请求*/
		//web.ignoring().antMatchers(securityConfig.getPermitUrls());
		web.ignoring().antMatchers(securityConfig.getAnonymousUrls());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		        //.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				//.antMatchers("OPTIONS").permitAll()
				// 配置白名单（比如登录接口）
				.antMatchers(securityConfig.getPermitUrls()).permitAll()
				// 匿名访问的URL，即不用登录也可以访问（比如广告接口）
				//.antMatchers(securityConfig.getAnonymousUrls()).permitAll()
				// 买家接口需要 “ROLE_BUYER” 角色权限才能访问
				.antMatchers("/buyer/**").hasRole("BUYER")
				// 其他任何请求满足 rbacService.hasPermission() 方法返回true时，能够访问
				//.anyRequest().access("@rbacService.hasPermission(request, authentication)")
				// 其他URL一律拒绝访问
                //.anyRequest().denyAll()
				.and()
				// 禁用跨站点伪造请求
				.csrf().disable()
				// 启用跨域资源共享
				.cors()
				.and()
				// 添加请求头
				.headers().addHeaderWriter(
				new StaticHeadersWriter(Collections.singletonList(
						new Header("Access-control-Allow-Origin", "http://localhost:8080")))).
				addHeaderWriter(
						new StaticHeadersWriter(Collections.singletonList(
								new Header("Access-Control-Allow-Credentials", "true")))
				).addHeaderWriter(
						new StaticHeadersWriter(Collections.singletonList(
								new Header("Access-Control-Allow-Headers", "Content-Type, Content-Length, Authorization, Accept, X-Requested-With, token, x-token"))
				)).addHeaderWriter(
						new StaticHeadersWriter(Collections.singletonList(
								new Header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS"))
						)
				)
				.and()
				// 自定义的登录过滤器，不同的登录方式创建不同的登录过滤器，一样的配置方式
				.apply(new com.addmp.security.config.UserLoginConfigurer<>(securityConfig))
				.and()
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

		//this.sentinelConfig();
	}

	private void sentinelConfig() {
		// 指定当前身份为 Token Client
		ClusterStateManager.applyState(ClusterStateManager.CLUSTER_CLIENT);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*auth.authenticationProvider(userAuthenticationProvider())
				.authenticationProvider(jwtAuthenticationProvider());*/
		auth.authenticationProvider(userAuthenticationProvider());
	}

	@Bean
	protected AuthenticationProvider userAuthenticationProvider() throws Exception {
		return new UserAuthenticationProvider();
	}

	@Bean
	protected AuthenticationProvider jwtAuthenticationProvider() throws Exception {
		return new JwtAuthenticationProvider(securityConfig);
	}

	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	//这个配置应该是不需要，所有的配置在configure(HttpSecurity http)  中完成
	/*@Bean
	protected CorsConfigurationSource corsConfigurationSource() {
		log.info("set corsConfigurationSource is:>>>>>>>>>>>>>>>>> ");
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
}
