package com.example.demo.system.security.filter;

import com.addmp.security.filter.UserAuthenticationFilter;
import com.addmp.security.request.UserLoginRequest;
import com.addmp.security.token.UserAuthenticationToken;
import com.alibaba.fastjson.JSON;
import com.example.demo.system.entity.User;
import com.example.demo.system.parameter.UserLoginParameter;
import com.example.demo.system.service.impl.UserService;
import com.example.demo.system.util.ApplicationUtil;
import com.example.demo.system.util.Response;
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
        //获取
        UserService userService = (UserService) ApplicationUtil.getBean("userService");

        // TODO 这里的逻辑主要有两个作用，一个是进行初步的校验，一个是组装待认证的Token，举几个例子：
        // 1.微信授权登录：客户端会传过来一些加密串，这里逻辑主要解密这些加密串的数据获取unionId、openId、手机号以及用户昵称头像等基本信息，
        // 然后组装Token传给Provider进行下一步认证，如果这里报错直接就返回异常，不会进行下一步认证。
        // 2.手机短信验证码登录：这里主要验证短信验证码的正确性，然后组装Token传给Provider进行下一步认证，如果短信验证码错误直接抛异常
        // 3.账号密码图形验证码登录：这里主要验证图形验证码的正确性，然后组装Token传给Provider进行下一步认证，如果图形验证码错误直接抛异常
        // ...
        // =================================================== 示例 ===============================================
        String body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> parameter is: "+body);

		String username = null, password = null;

		if(StringUtils.hasText(body)) {
            UserLoginParameter loginRequest = JSON.parseObject(body, UserLoginParameter.class);
            username = loginRequest.getUsername();
			password = loginRequest.getPassword();
		}

        User user = userService.findUser(username,password) ;
        if (user == null) {
            log.info("授权成功之后返回json字符串 ");
            response.setStatus(200);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().append("{\"success\":false,\"message\":\"用户名或者密码不正确!\",\"data\":\"{}\"}");
            return null ;
        }
        log.info("find user id is :"+user.getUserName()+ "  and id is : " + user.getId()) ;

        // TODO 这里验证图形验证码 verifyCode 是否正确

		UserAuthenticationToken token = new UserAuthenticationToken(
				null, username, password);

        // 这里进行下一步认证，会走到我们定义的 UserAuthenticationProvider 中
        //return this.getAuthenticationManager().authenticate(token);
        log.info("step one is : attemptAuthentication ................................") ;
        /*UserAuthenticationToken token = new UserAuthenticationToken(
                null, "11111", "2222");*/
        return token;
    }
}
