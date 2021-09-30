package com.example.demo.system.security.handler;

import com.addmp.security.config.SecurityConfig;
import com.addmp.security.dto.JwtUserLoginDTO;
import com.addmp.security.handler.UserLoginSuccessHandler;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.system.security.dto.DemoJwtUserLoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class DemoUserLoginSuccessHandler extends UserLoginSuccessHandler {

    public DemoUserLoginSuccessHandler(SecurityConfig securityConfig) {
        super(securityConfig);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info(">>>>>>>>>>>>>>>>>>>>> step3 ") ;
        log.info("logging success---  授权成功之后返回json字符串 ");
        // TODO 走到这里说明认证成功，可以组装一些响应头的信息给到客户端，比如生成JWT令牌，或者加一些业务上的需求，比如登录送积分等等

        // =================================================== 示例 ===============================================

        // 这里的逻辑是生成JWT令牌（很多公司也会用Session），将生成的JWT返回给前端
		Date expiredDate = new Date(System.currentTimeMillis() + securityConfig.getTokenExpireTimeInSecond() * 1000);
		Algorithm algorithm = Algorithm.HMAC256(securityConfig.getTokenEncryptSalt());
        DemoJwtUserLoginDTO jwtUserLoginDTO = (DemoJwtUserLoginDTO) authentication.getPrincipal();

		log.info(" algorithm-1 = "+ algorithm+"   expiredDate-1 ="+expiredDate) ;
        log.info(" algorithm = idddddddddddddddddddddd  "+  jwtUserLoginDTO.getUserId()) ;


		String token = jwtUserLoginDTO.sign(algorithm, expiredDate);
		log.info("token = ---------- "+ token);

        //设置请求头，将JWT令牌以请求头的方式返回给前端
        response.addHeader(HEADER_SET_ACCESS_TOKEN, token);
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().append("{\"success\":true,\"message\":\"登录成功!\",\"data\":\"{}\"}");
    }
}
