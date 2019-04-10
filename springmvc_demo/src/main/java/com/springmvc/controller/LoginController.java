package com.springmvc.controller;

import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public String login(String username,String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.save(user);

        return "hello";
    }
}
