package com.example.demo.system.security.dto;

import com.addmp.security.dto.JwtUserLoginDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class DemoJwtUserLoginDTO extends JwtUserLoginDTO {

    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_PASSWORD = "roleName";

    private String username;
    private String password;

    public DemoJwtUserLoginDTO(String username,String password){
        super.setNickname(username);
        this.username = username ;
        this.password = password ;
    }
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
                .withClaim(FIELD_USER_ID, username)
                .withClaim(FIELD_USERNAME, username)
                .withClaim(FIELD_PASSWORD, password)
                .withExpiresAt(expireDate)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }
}
