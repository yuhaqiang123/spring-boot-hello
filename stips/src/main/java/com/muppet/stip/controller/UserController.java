package com.muppet.stip.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements ApplicationContextAware {


    private Logger logger = LogManager.getLogger(this.getClass());
    ApplicationContext ac;

    @RequestMapping("/echo")
    public String echo() {
        logger.info("getBean2:{}", ac.getBean("uuidService").getClass());
        return "ok";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ac = applicationContext;
    }
}
