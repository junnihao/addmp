package com.example.demo.system.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.addmp.security.filter.JwtAuthentication1Filter ;

import java.util.Set;

@Slf4j
// 这个filter是必须的，是在处理OPTIONS 403必须的,与web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
@Component
public class CorsConfigFilter extends JwtAuthentication1Filter {
    @Value("${allowedOrigins}")
    Set<String> allowedOrigins;

    private Set<String> getAllowedOrigins(){
        return this.allowedOrigins ;
    }

    public CorsConfigFilter(){
        super.setAllowedOrigins(this.getAllowedOrigins());
        log.info(" config allowedOrigins is:"+ this.getAllowedOrigins());
    }
}
