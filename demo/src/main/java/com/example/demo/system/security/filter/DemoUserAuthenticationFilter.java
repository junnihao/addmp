package com.example.demo.system.security.filter;

import com.addmp.security.filter.UserAuthenticationFilter;
import com.addmp.security.request.UserLoginRequest;
import com.addmp.security.token.JwtAuthenticationToken;
import com.addmp.security.token.UserAuthenticationToken;
import com.alibaba.fastjson.JSON;
import com.example.demo.system.entity.User;
import com.example.demo.system.parameter.UserLoginParameter;
import com.example.demo.system.security.dto.DemoJwtUserLoginDTO;
import com.example.demo.system.security.exception.DemoAuthenticationException;
import com.example.demo.system.security.token.DemoJwtAuthenticationToken;
import com.example.demo.system.security.token.DemoUserAuthenticationToken;
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

        //从数据库校验用户名和密码的真确性
        String body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        log.info("parameter is: "+body);

		String username = null, password = null;

		if(StringUtils.hasText(body)) {
            UserLoginParameter loginRequest = JSON.parseObject(body, UserLoginParameter.class);
            username = loginRequest.getUsername();
			password = loginRequest.getPassword();
		}

        User user = userService.findUser(username,password) ;
		//如果校验不正确，不用在这里返回，只要抛出异常，就会在定义的授权失败的handler里面去处理
        /*if (user == null) {
            log.info("用户名或者密码不正确,返回错误信息 ");
            response.setStatus(403);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().append("{\"success\":false,\"message\":\"用户名或者密码不正确,请重新输入!\",\"data\":\"{}\"}");
            return null ;
        }*/
        if (user == null) {
            //log.info("用户名或者密码不正确,返回错误信息 ");
            //throw new DemoAuthenticationException("用户名或者密码不正确");
        }
        //log.info("find user id is :"+user.getUserName()+ "  and id is : " + user.getId()) ;
        // TODO 这里验证图形验证码 verifyCode 是否正确
        /*DemoUserAuthenticationToken token = new DemoUserAuthenticationToken(
				null, username, password); */

        DemoJwtUserLoginDTO demoJwtUserLoginDTO = new DemoJwtUserLoginDTO(username,password);
        DemoJwtAuthenticationToken demoJwtAuthenticationToken = new DemoJwtAuthenticationToken(demoJwtUserLoginDTO,null,null);
        log.info(" step1..... ") ;
        // 这里进行下一步认证，会走到我们定义的 UserAuthenticationProvider 中
        // return this.getAuthenticationManager().authenticate(token);
        return this.getAuthenticationManager().authenticate(demoJwtAuthenticationToken);
    }
}
