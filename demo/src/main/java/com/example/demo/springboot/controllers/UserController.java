/*package com.example.demo.springboot.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@RestController
@RequestMapping("/demo/user/")
public class UserController {
    @PostMapping("/login")
    public Response login(@RequestParam("userName") String userName,
                          @RequestParam("userPass") String userPass,
                          HttpSession session) {
        User user = userService.findByUserName(userName);
        //User user = userService.findByUserNameNoMapper(userName);
        if (user == null) {
            return Response.no("用户名不存在！");
        }
        if (!Objects.equals(user.getUserPass(), userPass)) {
            return Response.no("密码不正确！");
        }
        session.setAttribute("user", user);
        return Response.yes(user);
    }
}*/
