package com.example.demo.system.controller;

import com.example.demo.system.entity.User;
import com.example.demo.system.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
//import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/demo/user/")
public class UserController {
    @PostMapping("/login")
    public Response login(@RequestParam(value="userName",required = false) String userName,
                          @RequestParam(value="userPass",required = false) String userPass,
                          HttpSession session) {
        log.info("request parameter is: "+ userName) ;
        log.info("request parameter is: "+ userPass) ;
        // User user = userService.findByUserName(userName);
        // User user = userService.findByUserNameNoMapper(userName);
        User user = new User() ;
        if (user == null) {
            return Response.no("用户名不存在！");
        }
        /*if (!Objects.equals(user.getUserPass(), userPass)) {
            return Response.no("密码不正确！");
        }*/
        session.setAttribute("user", user);
        //return Response.yes(user);
        return Response.no("用户名不存在！");
    }
}
