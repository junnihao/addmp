package com.example.demo.system.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.addmp.security.filter.JwtAuthentication1Filter ;

@Slf4j
@Component
public class AuthenticationFilter extends JwtAuthentication1Filter {

}
