package com.muppet.spring.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.muppet.Email;
import com.muppet.EmailService;
import com.muppet.spring.model.User;
import com.muppet.spring.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Random;

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

    @Reference(version = "1.0.0")
    EmailService es;


    @RequestMapping(value = "/email", method = RequestMethod.PUT)
    public String sendEmail() {
        es.send(new Email("email is send"));
        return "ok";
    }

}
