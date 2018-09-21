package com.muppet.spring.model;

import com.muppet.spring.AppMain;
import com.muppet.spring.aspect.ControllerAspect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Configurable(preConstruction = true, autowire = Autowire.BY_TYPE)
public class Hello {
    Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private ControllerAspect aspect;

    public Hello() {
        logger.error("Hello is initialize");
    }

    public void init() {
        logger.error("ah");
        logger.error("Hello is {}", aspect.getClass().getName());
    }
}
