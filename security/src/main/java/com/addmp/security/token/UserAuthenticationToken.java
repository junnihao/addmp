package com.addmp.security.token;

import lombok.Data;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 登录认证Token
 *
 * @author HuaDong
 * @since 2021/4/24 20:29
 */
@Data
public class UserAuthenticationToken extends AbstractAuthenticationToken {

    public UserAuthenticationToken(Collection<? extends GrantedAuthority> authorities){
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
