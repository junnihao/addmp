package com.addmp.security.config;

import com.addmp.security.filter.JwtAuthenticationFilter;
import com.addmp.security.filter.UserAuthenticationFilter;
import com.addmp.security.handler.HttpStatusLoginFailureHandler;
import com.addmp.security.handler.JwtRefreshSuccessHandler;
import com.addmp.security.handler.UserLoginSuccessHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

/**
 * JWT认证过滤器配置
 *
 * @author HuaDong
 * @since 2021/4/26 21:39
 */
public class JwtLoginConfigurer<T extends JwtLoginConfigurer<T, B>, B extends HttpSecurityBuilder<B>> extends AbstractHttpConfigurer<T, B> {

	private com.addmp.security.config.SecurityConfig securityConfig;

	public JwtLoginConfigurer(com.addmp.security.config.SecurityConfig securityConfig) {
		this.securityConfig = securityConfig;
	}

	@Override
	public void configure(B http) throws Exception {

		JwtAuthenticationFilter authFilter = getJwtAuthenticationFilter() ;
		authFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));

		// 配置白名单
		authFilter.setPermissiveUrl(securityConfig.getPermitUrls());
		// 配置匿名用户可访问的接口
		authFilter.setAnonymityRequestMatchers(securityConfig.getAnonymousUrls());

		JwtAuthenticationFilter filter = postProcess(authFilter);
		http.addFilterAfter(filter, AnonymousAuthenticationFilter.class);
	}

	protected JwtAuthenticationFilter getJwtAuthenticationFilter(){
		JwtAuthenticationFilter authFilter = new JwtAuthenticationFilter(securityConfig.getTokenName());
		// 成功处理器
		authFilter.setAuthenticationSuccessHandler(new JwtRefreshSuccessHandler(securityConfig));
		// 失败处理器
		authFilter.setAuthenticationFailureHandler(new HttpStatusLoginFailureHandler());
		return authFilter;
	}

}
