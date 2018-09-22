package com.muppet.spring.controller;

import com.muppet.spring.AppMain;
import com.muppet.spring.model.Hello;
import com.muppet.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableAutoConfiguration
@org.springframework.web.bind.annotation.RestController
public class HelloController {

    @RequestMapping("/hello")
    public User hello() {
        new Hello().init();
        return new User().setAge(1).setEmail("email").setName("yuhaiqiang");
    }
}
