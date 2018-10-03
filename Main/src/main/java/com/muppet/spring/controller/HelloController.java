package com.muppet.spring.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.muppet.Email;
import com.muppet.EmailResponse;
import com.muppet.EmailService;
import com.muppet.spring.model.User;
import com.muppet.spring.model.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Future;

@org.springframework.web.bind.annotation.RestController
public class HelloController {


    private Logger logger = LogManager.getLogger(this.getClass());

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

    @Reference(version = "1.0.0", stub = "com.muppet.spring.service.EmailServiceStub")
    EmailService es;

    @Reference(version = "v1.0.0", generic = true)
    GenericService service;

    @Reference(version = "1.0.0", async = true, timeout = 10000)
    EmailService asyncEmailService;


    @RequestMapping(value = "/email", method = RequestMethod.PUT)
    public String sendEmail() {
        RpcContext.getContext().setAttachment("name", "yuhaiqinang");
        EmailResponse response = es.send(new Email("email is send"));
        return response.getMsg();
    }

    @RequestMapping(value = "/email2", method = RequestMethod.PUT)
    public String sendEmail2() {
        Map<String, Object> params = new HashMap() {
            {
                put("message", "email is send version 2");
            }
        };
        Object object = service.$invoke("send", new String[]{"com.muppet.Email"}, new Object[]{params});
        logger.warn(object.getClass());
        return "ok";
    }

    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    public String echo() {
        EchoService echoService = (EchoService) es;
        Object status = echoService.$echo(new Email("hi"));
        logger.info(status.getClass());
        return "ok";
    }


    /**
     * TODO 异步调用失败
     */
    @Deprecated
    @RequestMapping(value = "/email3", method = RequestMethod.PUT)
    public String sendEmail3() {
        Object object = asyncEmailService.send(new Email("hello"));
        logger.info("async result:{}", object);
        Future<EmailResponse> future = RpcContext.getContext().getFuture();

        try {
            EmailResponse response = future.get();
            logger.info("response:{}", response.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return "ok";
    }

}
