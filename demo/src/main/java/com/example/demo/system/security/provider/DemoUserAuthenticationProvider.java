package com.example.demo.system.security.provider;

import com.addmp.security.dto.JwtUserLoginDTO;
import com.addmp.security.dto.UserInfoDTO;
import com.addmp.security.exception.LoginAuthenticationException;
import com.addmp.security.provider.UserAuthenticationProvider;
import com.addmp.security.token.JwtAuthenticationToken;
import com.addmp.security.token.UserAuthenticationToken;
import com.example.demo.system.entity.User;
import com.example.demo.system.security.dto.DemoJwtUserLoginDTO;
import com.example.demo.system.security.dto.DemoUserInfoDTO;
import com.example.demo.system.security.exception.DemoAuthenticationException;
import com.example.demo.system.security.token.DemoJwtAuthenticationToken;
import com.example.demo.system.security.token.DemoUserAuthenticationToken;
import com.example.demo.system.service.impl.UserService;
import com.example.demo.system.util.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Objects;

@Slf4j
public class DemoUserAuthenticationProvider extends UserAuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("step2 ------------") ;
        // TODO 这里主要进行一个数据库层面的认证，比如账号密码的正确性，比如该账号是否被拉黑有什么权限等，都认证成功之后会组装一个认证通过的 Token

        // 这里认证成功返回之后会跑到成功处理器：UserLoginSuccessHandler
        // 只要整个认证（包括前面的校验）中有一个地方抛出异常都会调用失败处理器：HttpStatusLoginFailureHandler

        // =================================================== 示例 ===============================================

        /*DemoUserAuthenticationToken token = (DemoUserAuthenticationToken) authentication;*/
        DemoJwtAuthenticationToken token = (DemoJwtAuthenticationToken) authentication;
        DemoJwtUserLoginDTO demoJwtUserLoginDTO = (DemoJwtUserLoginDTO)token.getPrincipal() ;
        log.info(" "+token.getDetails() +"  "+demoJwtUserLoginDTO.getUsername()) ;

        // 校验账号密码是否正确，同时返回用户信息
        DemoUserInfoDTO userInfo = this.checkAndGetUserInfo(demoJwtUserLoginDTO.getUsername(), demoJwtUserLoginDTO.getPassword());
        if(userInfo == null){
            log.info("账号或者密码不正确.................") ;
            throw new DemoAuthenticationException(" 没有权限访问 ...  ") ;
        }

        // 查询用户角色，假设这里是从数据库中查询出的该用户角色
        String roleName = "ROLE_BUYER";

        // 组装并返回认证成功的 Token
        DemoJwtUserLoginDTO jwtUserLoginDTO = new DemoJwtUserLoginDTO(userInfo.getUsername(), userInfo.getPassword());

        // return new JwtAuthenticationToken(jwtUserLoginDTO, null, null);
        return new DemoJwtAuthenticationToken(jwtUserLoginDTO, null, null);
    }

    private DemoUserInfoDTO checkAndGetUserInfo(String username, String password) {

        UserService userService = (UserService) ApplicationUtil.getBean("userService");
        User user = userService.findUser(username,password) ;
        if(user!=null){
            return new DemoUserInfoDTO(user.getUserName(),user.getPasswd()) ;
        }
        return null;
    }

    /**
     * 表示这个 Provider 支持认证的 Token（这里是 UserAuthenticationToken）
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        //return authentication.isAssignableFrom(UserAuthenticationToken.class);
        return true;
    }
}
