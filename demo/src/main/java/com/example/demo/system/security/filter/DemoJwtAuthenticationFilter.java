package com.example.demo.system.security.filter;

import com.addmp.security.exception.LoginAuthenticationException;
import com.addmp.security.filter.JwtAuthenticationFilter;
import com.addmp.security.token.JwtAuthenticationToken;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.demo.system.security.dto.DemoJwtUserLoginDTO;
import com.example.demo.system.security.token.DemoJwtAuthenticationToken;
import com.example.demo.system.security.token.DemoUserAuthenticationToken;
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
        log.info("start verify ......................................");
        // 是否是白名单URL
        if (permissiveRequest(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        Authentication authResult = null;
        AuthenticationException failed = null;
        try {
            String token = getJwtToken(request);
            log.info("start verify token= " + token );
            if (StringUtils.isNotBlank(token)) {
                // DemoJwtAuthenticationToken authToken = new DemoJwtAuthenticationToken(JWT.decode(token));
                //对token进行解析，解析出来之后再去验证解析出来
                DemoJwtUserLoginDTO demoJwtUserLoginDTO  = new DemoJwtUserLoginDTO("jun","12561ss") ;
                DemoJwtAuthenticationToken authToken = new DemoJwtAuthenticationToken(demoJwtUserLoginDTO,JWT.decode(token),null);
                log.info("decode token= " + token );
                authResult = this.getAuthenticationManager().authenticate(authToken);
                //authResult = this.getAuthenticationManager().authenticate(demoUserAuthenticationToken);
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
