package com.example.demo.system.security.token;

import com.addmp.security.dto.JwtUserLoginDTO;
import com.addmp.security.token.JwtAuthenticationToken;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.system.security.dto.DemoJwtUserLoginDTO;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class DemoJwtAuthenticationToken extends JwtAuthenticationToken {

    private DemoJwtUserLoginDTO demoJwtUserLoginDTO;

    public DemoJwtAuthenticationToken(DemoJwtUserLoginDTO demoJwtUserLoginDTO, DecodedJWT token, Collection<? extends GrantedAuthority> authorities) {
        super(demoJwtUserLoginDTO,token,authorities);
        this.demoJwtUserLoginDTO = demoJwtUserLoginDTO ;
    }

    @Override
    public Object getPrincipal() {
        return demoJwtUserLoginDTO;
    }
}
