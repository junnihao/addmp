package com.example.demo.system.security.dto;

import com.addmp.security.dto.JwtUserLoginDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

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

    public DemoJwtUserLoginDTO(){}

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

    public void setRoleName(String roleName){ this.roleName=roleName;}
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

    /**
     * 验证令牌有效性并返回用户对象
     *
     * @param jwt
     * @param algorithm
     * @return
     * @throws
     */
    public static DemoJwtUserLoginDTO fromDecodeJWT(DecodedJWT jwt, Algorithm algorithm) {
        log.info("verify logic is here........");
        Assert.isTrue(StringUtils.isNotBlank(jwt.getSubject()), "Invalid Token");
        Assert.isTrue(jwt.getClaim(DemoJwtUserLoginDTO.FIELD_USERNAME) != null, "Invalid Token");
        Assert.isTrue(jwt.getClaim(DemoJwtUserLoginDTO.FIELD_PASSWORD) != null, "Invalid Token");
        Assert.isTrue(jwt.getClaim(DemoJwtUserLoginDTO.FIELD_ROLENAME) != null, "Invalid Token");

        String username = jwt.getClaim(DemoJwtUserLoginDTO.FIELD_USERNAME).asString();
        String password = jwt.getClaim(DemoJwtUserLoginDTO.FIELD_PASSWORD).asString();
        String roleName = jwt.getClaim(DemoJwtUserLoginDTO.FIELD_ROLENAME).asString();
        log.info("username = " + username ) ;
        log.info("password = " + password ) ;
        log.info("roleName = " + roleName ) ;
        JWTVerifier verifier = JWT.require(algorithm)
                .withClaim(DemoJwtUserLoginDTO.FIELD_USERNAME, username)
                .withClaim(DemoJwtUserLoginDTO.FIELD_PASSWORD,password )
                .withClaim(DemoJwtUserLoginDTO.FIELD_ROLENAME, roleName)
                .build();

        verifier.verify(jwt.getToken());

        DemoJwtUserLoginDTO jwtUserLoginDTO = new DemoJwtUserLoginDTO();
        jwtUserLoginDTO.setUsername( username);
        jwtUserLoginDTO.setPassword(password );
        jwtUserLoginDTO.setRoleName(roleName);

        return jwtUserLoginDTO;
    }
}
