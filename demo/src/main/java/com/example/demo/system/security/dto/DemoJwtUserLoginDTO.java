package com.example.demo.system.security.dto;

import com.addmp.security.dto.JwtUserLoginDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class DemoJwtUserLoginDTO extends JwtUserLoginDTO {

    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_ROLENAME = "rolename";

    private String username;
    private String password;
    private String roleName;

    public DemoJwtUserLoginDTO(String username,String password,String roleName){
        super.setNickname(username);
        this.username = username ;
        this.password = password ;
        this.roleName = roleName;
    }

    public String getUsername(){
        return this.username ;
    }

    public String getPassword(){
        return this.password;
    }

    public void setUsername(String username){
        this.username = username ;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getRoleName(){return this.roleName;}
    /**
     * 签名，生成JWT令牌
     *
     * @param algorithm
     * @param expireDate
     * @return
     */
    @Override
    public String sign(Algorithm algorithm, Date expireDate) {
        return JWT.create()
                .withSubject("subject_" + username)
                .withClaim(FIELD_USERNAME, username)
                .withClaim(FIELD_PASSWORD, password)
                .withClaim(FIELD_ROLENAME, roleName)
                .withExpiresAt(expireDate)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }
}
