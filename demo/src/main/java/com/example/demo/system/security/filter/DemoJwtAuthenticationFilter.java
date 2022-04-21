package com.example.demo.system.security.filter;

import com.addmp.security.exception.LoginAuthenticationException;
import com.addmp.security.filter.JwtAuthenticationFilter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.demo.system.security.dto.DemoJwtUserLoginDTO;
import com.example.demo.system.security.exception.DemoAuthenticationException;
import com.example.demo.system.security.token.DemoJwtAuthenticationToken;
import com.example.demo.system.security.utils.DemoTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class DemoJwtAuthenticationFilter extends JwtAuthenticationFilter {
    public DemoJwtAuthenticationFilter(String tokenName) {
        super(tokenName);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("verify step 1: 校验是否白名单");
        // 是否是白名单URL
        if (permissiveRequest(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        Authentication authResult = null;
        AuthenticationException failed = null;
        try {
            log.info("verify step 2: 从request中获取传递过来的token");
            String token = getJwtToken(request);
            log.info("token from request = " + token) ;
            log.info("get user name from token = " + DemoTokenUtil.getUserName(token)) ;
            if (StringUtils.isNotBlank(token)) {
                log.info("verify step 3: 解析并组装token");
                DemoJwtAuthenticationToken authToken = new DemoJwtAuthenticationToken(JWT.decode(token));
                authResult = this.getAuthenticationManager().authenticate(authToken);
            } else {
                failed = LoginAuthenticationException.JWT_IS_EMPTY;
            }
        } catch (JWTDecodeException e) {

            logger.error("JWT format error", e);
            failed = LoginAuthenticationException.JWT_FORMAT_ERROR;

        } catch (InternalAuthenticationServiceException e) {

            logger.error("An internal error occurred while trying to authenticate the user.");
            failed = LoginAuthenticationException.AUTH_ERROR;

        } catch (AuthenticationException e) {

            failed = e;
        }

        if (authResult != null) {
            successfulAuthentication(request, response, filterChain, authResult);
        } else {
            // 是否是匿名用户访问的URL
            if (!anonymityRequest(request)) {
                unsuccessfulAuthentication(request, response, failed);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
