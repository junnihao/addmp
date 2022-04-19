package com.example.demo.system.security.token;

import com.addmp.security.token.UserAuthenticationToken;
import com.example.demo.system.security.dto.DemoJwtUserLoginDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Slf4j
@Data
public class DemoUserAuthenticationToken extends UserAuthenticationToken {
    /**
     * 登录账号/手机号
     */
    private String username;

    /**
     * 登录密码
     */
    private String password;




    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     * @param username
     * @param password
     */
    public DemoUserAuthenticationToken(Collection<? extends GrantedAuthority> authorities,String username, String password) {
         super(authorities);
         this.username = username ;
         this.password = password ;
    }

    public String getUserName() {
        return this.username;
    }

    public String getPassword(){
        return this.password ;
    }
    @Override
    public Object getCredentials() {
        return username;
    }

    @Override
    public Object getPrincipal() { return username;}
}
