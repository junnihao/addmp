package com.example.demo.system.security.provider;

import com.addmp.security.provider.UserAuthenticationProvider;
import com.addmp.security.token.UserAuthenticationToken;
import com.example.demo.system.entity.User;
import com.example.demo.system.security.config.DemoWebSecurityConfig;
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

@Slf4j
public class DemoUserAuthenticationProvider extends UserAuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO 这里主要进行一个数据库层面的认证，比如账号密码的正确性，比如该账号是否被拉黑有什么权限等，都认证成功之后会组装一个认证通过的 Token
        // 这里认证成功返回之后会跑到成功处理器：UserLoginSuccessHandler
        // 只要整个认证（包括前面的校验）中有一个地方抛出异常都会调用失败处理器：HttpStatusLoginFailureHandler

        DemoUserAuthenticationToken token = (DemoUserAuthenticationToken) authentication;
        log.info("step5 从上一步传递进来的token中获取 用户登录信息: name=" +token.getUserName() +" password = "+ token.getPassword());

        log.info("step6 从数据库中校验登录用户的用户名和密码的正确性.") ;
        DemoUserInfoDTO userInfo = this.checkAndGetUserInfo(token.getUserName(), token.getPassword());
        /*模拟从数据库中取出了 登录用户的角色名 ,这里可以将角色名放到DTO对象中，在组装token时将登录用户的角色也组装到token中
        * 当用户请求API时会带上token，解析token获取角色，并将角色用于组装 JwtAuthenticationToken，Spring会根据配置和用户角色名允许或限制资源的访问
        * */
        String roleName = "ROLE_OPERATOR" ;
        log.info("user roleName = "+ roleName) ;
        if(userInfo == null){
            log.info("step6-1 如果用户名和密码户正确，则在这里抛出异常；") ;
            log.info("        如果DemoWebSecurityConfig 中configure(AuthenticationManagerBuilder auth) 只配置了一个AuthenticationProvider ，则进入到失败页面") ;
            log.info("        如果配置了多个AuthenticationProvider ，则进入到下一个AuthenticationProvider") ;
            throw new DemoAuthenticationException(" 用户名和密码不正确 ...  ") ;
        }
        // 组装并返回认证成功的 Token
        log.info("step7 从数据库中校验用户名和密码正确后,进入到登录成功处理逻辑") ;
        return token ;
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
        return authentication.isAssignableFrom(DemoUserAuthenticationToken.class);
        //return true;
    }
}
