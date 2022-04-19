package com.example.demo.system.security.utils;

import com.addmp.security.utils.TokenUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.io.Serializable;
import java.util.Date;

public class DemoTokenUtil extends TokenUtil implements Serializable {

    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_PASSWORD = "password";

    private String username;
    private String password;

    public DemoTokenUtil(String username ,String password){
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
    public String sign(Algorithm algorithm, Date expireDate) {
        return JWT.create()
                .withSubject("subject_" + username)
                .withClaim(FIELD_USERNAME, username)
                .withClaim(FIELD_PASSWORD, password)
                .withExpiresAt(expireDate)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    public static String getUserName(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("MEICLOUD1");
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String username = jwt.getClaim("username").asString();
            return username;
        } catch (Exception e){
            return null;
        }
    }

}
