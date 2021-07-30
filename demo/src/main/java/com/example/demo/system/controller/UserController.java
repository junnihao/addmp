package com.example.demo.system.controller;

import com.example.demo.system.entity.User;
import com.example.demo.system.parameter.UserLoginParameter;
import com.example.demo.system.service.iface.IUserService;
import com.example.demo.system.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
//import java.util.Objects;
//import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/demo/user/")
public class UserController {

    @Autowired
    IUserService userService ;

    @PostMapping(value="/login",produces = "application/json;charset=UTF-8")
    public Response login(@RequestBody UserLoginParameter loginParameter, HttpSession session) {
        log.info("input name is: "+ loginParameter.getUsername()) ;
        log.info("input pass is: "+ loginParameter.getPassword()) ;
        log.info(" Test session parameter status : "+session.getId()) ;
        User user = userService.findUser(loginParameter.getUsername(),loginParameter.getPassword()) ;
        log.info("find user id is :"+user.getName()+ "  and id is : " + user.getId()) ;
        if (user == null) {
            return Response.no("用户名不存在！");
        }
        session.setAttribute("user", user);
        return Response.yes(user);
       //return Response.no("用户名不存在！");
    }

    @PostMapping("/loginbak")
    public Response loginBak(@RequestParam(value="userName",required = false) String userName,
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
