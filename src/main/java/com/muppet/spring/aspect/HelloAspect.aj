package com.muppet.spring.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
public aspect HelloAspect {

    pointcut hello(Object entity): target(entity) && execution(com.muppet.spring.model.*.new(..));

    public HelloAspect() {
        logger.error("HelloAspect is init");
    }

    Logger logger = LogManager.getLogger(this.getClass());
    after(Object entity) returning : hello(entity) {
        logger.error("hello after");
    }


}
