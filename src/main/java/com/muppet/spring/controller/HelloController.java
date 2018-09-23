package com.muppet.spring.controller;

import com.muppet.spring.AppMain;
import com.muppet.spring.model.Hello;
import com.muppet.spring.model.User;
import com.muppet.spring.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@EnableAutoConfiguration
@org.springframework.web.bind.annotation.RestController
public class HelloController {

    @Autowired
    UserMapper um;

    @RequestMapping("/hello")
    public User hello() {
        User user = new User();
        user.setPassword("1");
        user.setPhone("3");
        Integer userId = new Random().nextInt();
        user.setUserId(userId);
        user.setUserName("yuhaiqiang");

        um.insert(user);
        user = um.selectByPrimaryKey(userId);
        return user;
    }
}
