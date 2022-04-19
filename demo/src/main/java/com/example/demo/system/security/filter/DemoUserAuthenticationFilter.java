package com.example.demo.system.security.filter;

import com.addmp.security.filter.UserAuthenticationFilter;
import com.alibaba.fastjson.JSON;
import com.example.demo.system.parameter.UserLoginParameter;
import com.example.demo.system.security.token.DemoUserAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class DemoUserAuthenticationFilter extends UserAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        // TODO 这里的逻辑主要有两个作用，一个是进行初步的校验，一个是组装待认证的Token，举几个例子：
        // 1.微信授权登录：客户端会传过来一些加密串，这里逻辑主要解密这些加密串的数据获取unionId、openId、手机号以及用户昵称头像等基本信息，
        // 然后组装Token传给Provider进行下一步认证，如果这里报错直接就返回异常，不会进行下一步认证。
        // 2.手机短信验证码登录：这里主要验证短信验证码的正确性，然后组装Token传给Provider进行下一步认证，如果短信验证码错误直接抛异常
        // 3.账号密码图形验证码登录：这里主要验证图形验证码的正确性，然后组装Token传给Provider进行下一步认证，如果图形验证码错误直接抛异常
        // ...

        //从request里面获取参数
        String body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        log.info("step2 从request解析请求参数(用户名和密码): "+body);

		String username = null, password = null;

		if(StringUtils.hasText(body)) {
            UserLoginParameter loginRequest = JSON.parseObject(body, UserLoginParameter.class);
            username = loginRequest.getUsername();
			password = loginRequest.getPassword();
		}

        //将用户登录信息
        log.info("step3 将用户名和密码等登录信息组装成 ->JwtUserLoginDTO 对象 -> JwtAuthenticationToken,  ");
        DemoUserAuthenticationToken demoUserAuthenticationToken = new DemoUserAuthenticationToken(null,username,password);

        // 这里进行下一步认证，会走到我们定义的 UserAuthenticationProvider 中
        log.info("step4 将DemoUserAuthenticationToken 传递给UserAuthenticationProvider 继续校验 ");
        return this.getAuthenticationManager().authenticate(demoUserAuthenticationToken);
    }
}
