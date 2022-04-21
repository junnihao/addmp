package com.example.demo.system.security.provider;

import com.addmp.security.config.SecurityConfig;
import com.addmp.security.exception.LoginAuthenticationException;
import com.addmp.security.provider.JwtAuthenticationProvider;
import com.addmp.security.token.JwtAuthenticationToken;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.system.security.dto.DemoJwtUserLoginDTO;
import com.example.demo.system.security.token.DemoJwtAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Calendar;
import java.util.Collections;

@Slf4j
public class DemoJwtAuthenticationProvider extends JwtAuthenticationProvider {
    public DemoJwtAuthenticationProvider(SecurityConfig securityConfig) {
        super(securityConfig);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info(" start verify token ......") ;
        DecodedJWT jwt = ((JwtAuthenticationToken) authentication).getToken();
        // 令牌过期
        if(jwt.getExpiresAt().before(Calendar.getInstance().getTime())) {
            throw LoginAuthenticationException.JWT_EXPIRED;
        }

        try {
            // 校验令牌的合法性
            Algorithm algorithm = Algorithm.HMAC256(securityConfig.getTokenEncryptSalt());
            /* 这里是解析 token的逻辑  */
            log.info("this is the logic for parse token" ) ;
            DemoJwtUserLoginDTO loginResultDTO = DemoJwtUserLoginDTO.fromDecodeJWT(jwt, algorithm);
            log.info("after verify......................" + loginResultDTO.getRoleName()) ;
            log.info("这里可以加入逻辑：对解析出来的token:用户名、密码等进行再次验证" ) ;
            return new DemoJwtAuthenticationToken(loginResultDTO, jwt,
                    Collections.singletonList(new SimpleGrantedAuthority(loginResultDTO.getRoleName())));
            /*return new DemoJwtAuthenticationToken(loginResultDTO, jwt,
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_OPERATOR")));*/
        } catch (Exception e) {
            log.info("varify error message=" + e.getMessage()) ;
            throw new BadCredentialsException("JWT token verify fail", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(DemoJwtAuthenticationToken.class);
    }
}
