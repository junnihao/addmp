package com.example.demo.system.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.addmp.security.filter.JwtAuthentication1Filter ;

import java.util.Set;

@Slf4j
// 这个filter是必须的，是在处理OPTIONS 403必须的
@Component
public class AuthenticationFilter extends JwtAuthentication1Filter {
    @Value("${allowedOrigins}")
    Set<String> allowedOrigins;

    public AuthenticationFilter(){
        super.setAllowedOrigins(this.allowedOrigins);
        log.info(" config allowedOrigins is:"+ allowedOrigins);
    }
}
